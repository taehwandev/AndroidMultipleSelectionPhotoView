package tech.thdev.androidmultipleselectionphotoview.data

/**
 * Created by Taehwan on 12/02/2018.
 */
data class PhotoItem(val imagePath: String,
                     var number: Int = -1,
                     var isCheck: Boolean = false)