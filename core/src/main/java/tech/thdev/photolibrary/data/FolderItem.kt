package tech.thdev.photolibrary.data

import tech.thdev.photolibrary.data.source.PhotoItem
import java.util.*

/**
 * Created by Taehwan on 20/02/2018.
 */
data class FolderItem(val name: String,
                      val imageList: ArrayList<PhotoItem>)