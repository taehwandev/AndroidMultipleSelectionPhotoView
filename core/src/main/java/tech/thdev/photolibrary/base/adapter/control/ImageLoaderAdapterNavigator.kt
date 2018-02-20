package tech.thdev.photolibrary.base.adapter.control

import android.content.Context
import tech.thdev.photolibrary.data.source.PhotoItem

/**
 * Created by Taehwan on 20/02/2018.
 */
interface ImageLoaderAdapterNavigator {

    val context: Context?

    /**
     * Select limit count
     *
     * Default value -1 : unlimited.
     */
    val selectLimitCount: Int

    /**
     * select count limit listener
     */
    var showAlertSelectedLimit: (selectLimitCount: Int) -> Unit

    /**
     * All data notify
     */
    var notifyDataSetChanged: () -> Unit

    /**
     * Position data notify
     */
    var notifyItemChanged: (Int) -> Unit

    /**
     * itemCount return
     */
    fun getItemCount(): Int

    /**
     * ItemViewType return
     */
    fun getItemViewType(position: Int): Int

    /**
     * list item return
     */
    fun getItem(position: Int): PhotoItem

    /**
     * Item add.
     *
     * @param item : PhotoItem
     */
    fun addItem(item: PhotoItem?): Int

    /**
     * Item add.
     *
     * @param item : PhotoItem
     * @param viewType : moreView.
     */
    fun addItem(item: PhotoItem?, viewType: Int): Int

    /**
     * All select item list.
     */
    fun getSelectList(): MutableList<PhotoItem>

    /**
     * Update selectItem
     */
    fun onUpdateSelectItem(position: Int)
}