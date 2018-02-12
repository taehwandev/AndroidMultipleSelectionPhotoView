package tech.thdev.androidmultipleselectionphotoview.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.androidmultipleselectionphotoview.base.adapter.holder.BaseViewHolder
import tech.thdev.androidmultipleselectionphotoview.data.PhotoItem
import tech.thdev.androidmultipleselectionphotoview.view.main.adapter.holder.ImageViewHolder

/**
 * Created by Taehwan on 12/02/2018.
 */
class SampleAdapter(private val context: Context) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    lateinit var onClickItem: (position: Int) -> Unit

    val itemList = mutableListOf<PhotoItem>()

    val selectList = mutableListOf<PhotoItem>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
        return ImageViewHolder(context, parent, onClickItem)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>?, position: Int) {
        holder?.onBindViewHolder(itemList[position])
    }

    fun updateSelectItem(position: Int) {
        itemList[position].let {
            it.isCheck = !it.isCheck
            if (it.isCheck) {
                selectList.add(it)
            }
        }
    }
}