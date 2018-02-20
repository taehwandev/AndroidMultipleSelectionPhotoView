package tech.thdev.photolibrary.base

/**
 * Created by Taehwan on 20/02/2018.
 */
interface ImageLoaderNavigator {

    /**
     * ImageLoad
     */
    fun loadImage()

    /**
     * Empty image list view.
     */
    var showEmptyImageList: () -> Unit
}