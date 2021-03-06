package tech.thdev.androidmultipleselectionphotoview.view.sample.adapter.holder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_image_view.*
import tech.thdev.androidmultipleselectionphotoview.R
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterNavigator
import tech.thdev.photolibrary.base.adapter.holder.BaseImageLoaderViewHolder
import tech.thdev.photolibrary.data.source.PhotoItem

/**
 * Created by Taehwan on 12/02/2018.
 */
class ImageViewHolder(parent: ViewGroup?, viewModel: ImageLoaderAdapterNavigator) :
        BaseImageLoaderViewHolder<ImageLoaderAdapterNavigator, PhotoItem>(viewModel, R.layout.list_item_image_view, parent) {

    override fun onViewCreated(item: PhotoItem?) {
        Glide.with(itemView).load(item?.filePath).into(img_view)

        img_check.visibility = View.VISIBLE.takeIf { item?.isSelected == true } ?: View.GONE
        tv_selected_number.run {
            text = item?.number.toString()
            visibility = View.VISIBLE.takeIf { item?.isSelected == true } ?: View.GONE
        }
    }
}