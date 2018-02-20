package tech.thdev.photolibrary.base.adapter.holder

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterNavigator

/**
 * Created by Taehwan on 20/02/2018.
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseImageLoaderViewHolder<out VIEW_MODEL : ImageLoaderAdapterNavigator,
        in ITEM : Any>(
        val viewModel: VIEW_MODEL,
        @LayoutRes layoutRes: Int, parent: ViewGroup?)
    : AndroidBaseViewHolder(LayoutInflater.from(viewModel.context).inflate(layoutRes, parent, false)) {

    init {
        containerView.setOnClickListener {
            viewModel.onUpdateSelectItem(adapterPosition)
        }
    }

    fun onBindViewHolder(item: Any?) {
        try {
            onViewCreated(item as? ITEM?)
        } catch (e: Exception) {
            containerView.visibility = View.GONE
        }
    }

    abstract fun onViewCreated(item: ITEM?)
}