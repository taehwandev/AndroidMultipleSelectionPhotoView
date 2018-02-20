package tech.thdev.photolibrary.rxjava.data.source.image

import android.content.Context
import rx.Observable
import tech.thdev.photolibrary.data.FolderItem
import tech.thdev.photolibrary.data.source.PhotoItem

/**
 * Created by Taehwan on 20/02/2018.
 */
interface ImageLoaderManagerRxJavaDataSorcue {

    fun loadImageByFolder(context: Context): Observable<List<FolderItem>>

    fun loadImage(context: Context, selectFolderName: String): Observable<List<PhotoItem>>

    fun loadImage(context: Context): Observable<List<PhotoItem>>
}