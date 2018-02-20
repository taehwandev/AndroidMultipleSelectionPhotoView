package tech.thdev.photolibrary.data.source.image

import android.content.Context
import tech.thdev.photolibrary.data.FolderItem
import tech.thdev.photolibrary.data.source.PhotoItem

/**
 * Created by Taehwan on 20/02/2018.
 */
class ImageLoaderManagerRepository : ImageLoaderManagerDataSource {

    private val localDataSource: ImageLoaderManagerLocalDataSource by lazy {
        ImageLoaderManagerLocalDataSource()
    }

    override fun loadImageByFolder(context: Context) =
            localDataSource.loadImage(context)?.let {
                return@let ArrayList<FolderItem>(it.values)
            } ?: mutableListOf<FolderItem>()

    override fun loadImage(context: Context, selectFolderName: String) =
            localDataSource.loadImage(context)?.getValue(selectFolderName)?.let {
                return@let ArrayList<PhotoItem>(it.imageList)
            } ?: mutableListOf<PhotoItem>()

    override fun loadImage(context: Context): List<PhotoItem> {
        val imageList = mutableListOf<PhotoItem>()
        localDataSource.loadImage(context, imageList)
        return imageList
    }
}