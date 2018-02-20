package tech.thdev.photolibrary.data.source.image

import android.content.Context
import android.provider.MediaStore
import tech.thdev.photolibrary.data.FolderItem
import tech.thdev.photolibrary.data.source.PhotoItem
import tech.thdev.photolibrary.util.createFile

/**
 * Created by Taehwan on 20/02/2018.
 */
class ImageLoaderManagerLocalDataSource {

    private val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

    /**
     * Load image.
     */
    fun loadImage(context: Context, imageList: MutableList<PhotoItem>? = null): HashMap<String, FolderItem>? = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, null, null, MediaStore.Images.Media.DATE_ADDED)?.let { cursor ->
        return@let HashMap<String, FolderItem>().apply {
            if (cursor.moveToLast()) {
                do {
                    val id = cursor.getLong(cursor.getColumnIndex(projection[0]))
                    val displayName = cursor.getString(cursor.getColumnIndex(projection[1]))
                    val data = cursor.getString(cursor.getColumnIndex(projection[2]))
                    val bucketDisplayName = cursor.getString(cursor.getColumnIndex(projection[3]))

                    data.createFile()?.takeIf { it.exists() }?.run {
                        val imageItem = PhotoItem(id, displayName, data)
                        imageList?.add(imageItem) ?: let {
                            get(bucketDisplayName) ?: let {
                                put(bucketDisplayName, FolderItem(bucketDisplayName, ArrayList()))
                            }?.imageList?.add(imageItem)
                        }
                    }
                } while (cursor.moveToPrevious())
            }

            cursor.close()
        }
    }
}