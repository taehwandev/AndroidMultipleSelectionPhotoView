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

    var selectLimitCount = -1

    lateinit var onClickItem: (position: Int) -> Unit
    lateinit var onSelectedLimit: (selectLimitCount: Int) -> Unit

    val itemList = mutableListOf<PhotoItem>()

    val selectList = mutableListOf<PhotoItem>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
        return ImageViewHolder(context, parent, onClickItem)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>?, position: Int) {
        holder?.onBindViewHolder(itemList[position])
    }

    fun updateSelectItem(position: Int): Boolean {
        var isChange = false
        itemList[position].let {
            if (selectLimitCount > -1) {
                if (it.isCheck) {
                    // unchecked
                    selectItem(it)
                    isChange = true
                } else {
                    if (selectList.size < selectLimitCount) {
                        selectItem(it)
                        isChange = true
                    } else {
                        onSelectedLimit(selectLimitCount)
                    }
                }
            } else {
                selectItem(it)
                isChange = true
            }
        }
        return isChange
    }

    private fun selectItem(item: PhotoItem) {
        item.isCheck = !item.isCheck
        if (item.isCheck) {
            item.number = selectList.size + 1
            selectList.add(item)
        } else {
            val number = item.number
            item.number = -1
            selectList.remove(item)

            updateNumber(number)
        }
    }

    private fun updateNumber(defaultNumber: Int) {
        selectList.forEach {
            if (it.number > defaultNumber) {
                it.number = it.number - 1

                notifyItemChanged(itemList.indexOf(it))
            }
        }
    }
}