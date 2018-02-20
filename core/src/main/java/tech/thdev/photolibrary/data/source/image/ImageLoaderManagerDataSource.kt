package tech.thdev.photolibrary.data.source.image

import android.content.Context
import tech.thdev.photolibrary.data.FolderItem
import tech.thdev.photolibrary.data.source.PhotoItem

/**
 * Created by Taehwan on 20/02/2018.
 */
interface ImageLoaderManagerDataSource {

    fun loadImageByFolder(context: Context): List<FolderItem>

    fun loadImage(context: Context, selectFolderName: String): List<PhotoItem>

    fun loadImage(context: Context): List<PhotoItem>
}