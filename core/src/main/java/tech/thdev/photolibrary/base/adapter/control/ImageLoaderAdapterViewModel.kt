package tech.thdev.photolibrary.base.adapter.control

import android.content.Context
import tech.thdev.photolibrary.data.AdapterInfo
import tech.thdev.photolibrary.data.ViewHolderInfo
import tech.thdev.photolibrary.data.source.PhotoItem

/**
 * Created by Taehwan on 20/02/2018.
 */
class ImageLoaderAdapterViewModel(override val context: Context?,
                                  override val selectLimitCount: Int = -1) : ImageLoaderAdapterNavigator {

    private val itemList = mutableListOf<Pair<PhotoItem, AdapterInfo>>()

    private val selectList = mutableListOf<Pair<PhotoItem, AdapterInfo>>()

    override lateinit var showAlertSelectedLimit: (selectLimitCount: Int) -> Unit
    override lateinit var notifyDataSetChanged: () -> Unit
    override lateinit var notifyItemChanged: (Int) -> Unit

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return getViewHolderInfo(position).let {
            when (it) {
                is ViewHolderInfo -> it.viewType
            }
        }
    }

    private fun getViewHolderInfo(position: Int): AdapterInfo =
            itemList[position].second

    override fun getItem(position: Int) =
            itemList[position].first

    override fun addItem(item: PhotoItem?) =
            addItem(item, -1)

    override fun addItem(item: PhotoItem?, viewType: Int): Int {
        return item?.let {
            val addItem = Pair(item, ViewHolderInfo(viewType))
            itemList.add(addItem)
            itemList.indexOf(addItem)
        } ?: -1
    }

    override fun getSelectList(): MutableList<PhotoItem> {
        val itemList = mutableListOf<PhotoItem>()
        selectList.forEach {
            itemList.add(it.first)
        }
        return itemList
    }

    override fun onUpdateSelectItem(position: Int) {
        itemList[position].let {
            if (selectLimitCount > -1) {
                if (isChecked(it.second)) {
                    // unchecked
                    selectItem(it, position)

                } else {
                    // check
                    if (selectList.size < selectLimitCount) {
                        selectItem(it, position)

                    } else {
                        showAlertSelectedLimit(selectLimitCount)
                    }
                }

            } else {
                selectItem(it, position)
            }
        }
    }

    private fun selectItem(item: Pair<PhotoItem, AdapterInfo>, position: Int) {
        selectItem(item)
        notifyItemChanged(position)
    }

    private fun isChecked(adapterInfo: AdapterInfo) =
            when (adapterInfo) {
                is ViewHolderInfo -> adapterInfo.isSelected
            }

    private fun AdapterInfo.updateChecked() {
        when (this) {
            is ViewHolderInfo -> this.isSelected = !this.isSelected
        }
    }

    private fun AdapterInfo.updateNumber(number: Int) {
        (this as ViewHolderInfo).number = number
    }

    private fun selectItem(item: Pair<PhotoItem, AdapterInfo>) {
        item.second.updateChecked()
        if (isChecked(item.second)) {
            item.second.updateNumber(selectList.size + 1)
            selectList.add(item)
        } else {
            val number = (item.second as ViewHolderInfo).number
            item.second.updateNumber(-1)
            selectList.remove(item)

            updateNumber(number)
        }
    }

    private fun updateNumber(defaultNumber: Int) {
        selectList.forEach { pair ->
            (pair.second as ViewHolderInfo).let {
                if (it.number > defaultNumber) {
                    updateNumber(it.number - 1)
                    notifyItemChanged(itemList.indexOf(pair))
                }
            }
        }
    }
}