@file:JvmName("FileUtil")
@file:JvmMultifileClass

package tech.thdev.photolibrary.util

import java.io.File

/**
 * Created by Taehwan on 20/02/2018.
 */

/**
 * Create new File.
 */
fun String?.createFile(): File? = this.takeIf { !it.isNullOrEmpty() }?.let {
    try {
        return@let File(it)
    } catch (e: Exception) {
        return@let null
    }
}