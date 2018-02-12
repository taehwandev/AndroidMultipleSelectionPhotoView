package tech.thdev.androidmultipleselectionphotoview.base.adapter.holder

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Taehwan on 12/02/2018.
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseViewHolder<in ITEM : Any>(
        val context: Context,
        @LayoutRes layoutRes: Int, parent: ViewGroup?)
    : AndroidBaseViewHolder(LayoutInflater.from(context).inflate(layoutRes, parent, false)) {

    fun onBindViewHolder(item: Any?) {
        try {
            onViewCreated(item as? ITEM?)
        } catch (e: Exception) {
            containerView.visibility = View.GONE
        }
    }

    abstract fun onViewCreated(item: ITEM?)
}