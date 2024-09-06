package org.sirekanyan.imageloader.internal.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.sirekanyan.imageloader.IMAGE_QUALITY
import java.io.File
import kotlin.math.min

fun decodeSampledBitmap(file: File, quality: Int = IMAGE_QUALITY): Bitmap? {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    file.inputStream().use { BitmapFactory.decodeStream(it, null, options)?.recycle() }
    options.inJustDecodeBounds = false
    options.inSampleSize = min(options.outWidth, options.outHeight) / quality
    return file.inputStream().use { BitmapFactory.decodeStream(it, null, options) }
}
