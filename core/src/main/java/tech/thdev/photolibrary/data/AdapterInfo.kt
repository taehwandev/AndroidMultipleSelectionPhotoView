package tech.thdev.photolibrary.data

/**
 * Created by Taehwan on 20/02/2018.
 */
sealed class AdapterInfo

data class ViewHolderInfo(val viewType: Int,
                          var isSelected: Boolean = false,
                          var number: Int = -1) : AdapterInfo()