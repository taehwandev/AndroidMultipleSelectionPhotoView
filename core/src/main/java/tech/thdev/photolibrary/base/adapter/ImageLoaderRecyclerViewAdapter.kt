package tech.thdev.photolibrary.base.adapter

import android.support.v7.widget.RecyclerView
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterNavigator
import tech.thdev.photolibrary.base.adapter.holder.BaseImageLoaderViewHolder

/**
 * Created by Taehwan on 20/02/2018.
 */
abstract class ImageLoaderRecyclerViewAdapter<out VIEW_MODEL : ImageLoaderAdapterNavigator>(val viewModel: VIEW_MODEL)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        viewModel.notifyDataSetChanged = {
            notifyDataSetChanged()
        }

        viewModel.notifyItemChanged = {
            notifyItemChanged(it)
        }
    }

    override fun getItemCount() = viewModel.getItemCount()

    override fun getItemViewType(position: Int) = viewModel.getItemViewType(position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? BaseImageLoaderViewHolder<*, *>)?.onBindViewHolder(viewModel.getItem(position))
    }
}