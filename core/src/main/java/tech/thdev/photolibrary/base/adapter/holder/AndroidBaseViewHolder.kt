package tech.thdev.photolibrary.base.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Taehwan on 20/02/2018.
 */
abstract class AndroidBaseViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer