package org.sirekanyan.imageloader

import android.app.Application
import android.widget.ImageView
import androidx.annotation.CheckResult
import org.sirekanyan.imageloader.internal.ImageCacheImpl
import org.sirekanyan.imageloader.internal.ImageLoaderDelegate
import org.sirekanyan.imageloader.internal.ImageLoaderDelegateImpl
import org.sirekanyan.imageloader.internal.ImageRequesterImpl
import org.sirekanyan.imageloader.internal.extensions.checkMainThread

object ImageLoader {

    private var delegate: ImageLoaderDelegate? = null

    @JvmStatic
    fun register(app: Application) {
        checkMainThread()
        val delegate = ImageLoaderDelegateImpl(ImageCacheImpl(app.cacheDir), ImageRequesterImpl())
        app.registerActivityLifecycleCallbacks(delegate)
        this.delegate = delegate
    }

    @JvmStatic
    fun loadImage(url: String, view: ImageView, placeholder: Int, error: Int) {
        checkMainThread()
        val delegate = checkRegistered()
        delegate.loadImage(url, view, placeholder, error)
    }

    @JvmStatic
    @CheckResult
    fun load(url: String): ImageLoaderBuilder {
        checkMainThread()
        val delegate = checkRegistered()
        return ImageLoaderBuilder(delegate, url)
    }

    fun clearCache() {
        checkMainThread()
        delegate?.clearCache()
    }

    private fun checkRegistered(): ImageLoaderDelegate =
        checkNotNull(delegate) {
            "ImageLoader is not registered, call ImageLoader.register() from your Application class"
        }
}
