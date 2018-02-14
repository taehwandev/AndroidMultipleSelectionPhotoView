package tech.thdev.androidmultipleselectionphotoview.view.main.adapter.holder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image_view.*
import tech.thdev.androidmultipleselectionphotoview.R
import tech.thdev.androidmultipleselectionphotoview.base.adapter.holder.BaseViewHolder
import tech.thdev.androidmultipleselectionphotoview.data.PhotoItem

/**
 * Created by Taehwan on 12/02/2018.
 */
class ImageViewHolder(context: Context, parent: ViewGroup?,
                      private val onClickItem: (position: Int) -> Unit) : BaseViewHolder<PhotoItem>(context, R.layout.item_image_view, parent) {

    init {
        containerView.setOnClickListener {
            onClickItem(adapterPosition)
        }
    }

    override fun onViewCreated(item: PhotoItem?) {
        Glide.with(itemView).load(item?.imagePath).into(img_view)

        img_check.visibility = View.VISIBLE.takeIf { item?.isCheck == true } ?: View.GONE
        tv_selected_number.run {
            text = item?.number.toString()
            visibility = View.VISIBLE.takeIf { item?.isCheck == true } ?: View.GONE
        }
    }
}