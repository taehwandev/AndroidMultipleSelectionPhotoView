package tech.thdev.photolibrary.rxjava.data.source.image

import android.content.Context
import rx.Observable
import tech.thdev.photolibrary.data.source.image.ImageLoaderManagerDataSource
import tech.thdev.photolibrary.data.source.image.ImageLoaderManagerRepository

/**
 * Created by Taehwan on 20/02/2018.
 *
 * RxJava mapping.
 */
object ImageLoaderManagerRxJavaRepository : ImageLoaderManagerRxJavaDataSorcue {

    private val imageLoadManager: ImageLoaderManagerDataSource by lazy {
        ImageLoaderManagerRepository()
    }

    override fun loadImageByFolder(context: Context) =
            Observable.just(imageLoadManager.loadImageByFolder(context))!!

    override fun loadImage(context: Context, selectFolderName: String) =
            Observable.just(imageLoadManager.loadImage(context, selectFolderName))!!

    override fun loadImage(context: Context) =
            Observable.just(imageLoadManager.loadImage(context))!!
}