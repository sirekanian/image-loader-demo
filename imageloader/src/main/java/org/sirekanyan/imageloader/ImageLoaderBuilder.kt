package org.sirekanyan.imageloader

import android.widget.ImageView
import androidx.annotation.CheckResult
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import org.sirekanyan.imageloader.internal.ImageLoaderDelegate

@Keep // todo: move to proguard file
class ImageLoaderBuilder internal constructor(
    private val delegate: ImageLoaderDelegate,
    private val url: String,
) {

    private var placeholderResId: Int? = null
    private var errorResId: Int? = null

    @CheckResult
    fun placeholder(@DrawableRes resourceId: Int): ImageLoaderBuilder {
        this.placeholderResId = resourceId
        return this
    }

    @CheckResult
    fun error(@DrawableRes resourceId: Int): ImageLoaderBuilder {
        this.errorResId = resourceId
        return this
    }

    fun into(view: ImageView) {
        delegate.loadImage(url, view, placeholderResId, errorResId)
    }
}
