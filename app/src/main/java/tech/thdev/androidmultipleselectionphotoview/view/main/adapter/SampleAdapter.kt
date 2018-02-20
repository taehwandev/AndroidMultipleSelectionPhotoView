package tech.thdev.androidmultipleselectionphotoview.view.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.androidmultipleselectionphotoview.view.main.adapter.holder.ImageViewHolder
import tech.thdev.photolibrary.base.adapter.ImageLoaderRecyclerViewAdapter
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterNavigator
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterViewModel

/**
 * Created by Taehwan on 12/02/2018.
 */
class SampleAdapter(viewModel: ImageLoaderAdapterViewModel) : ImageLoaderRecyclerViewAdapter<ImageLoaderAdapterNavigator>(viewModel) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(parent, viewModel)
    }
}