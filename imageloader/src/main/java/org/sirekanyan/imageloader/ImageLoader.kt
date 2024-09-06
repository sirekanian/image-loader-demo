package org.sirekanyan.imageloader

import android.app.Application
import android.widget.ImageView
import org.sirekanyan.imageloader.internal.ImageLoaderDelegate
import org.sirekanyan.imageloader.internal.ImageLoaderDelegateImpl
import org.sirekanyan.imageloader.internal.ImageRequesterImpl
import org.sirekanyan.imageloader.internal.extensions.checkMainThread

object ImageLoader {

    private var delegate: ImageLoaderDelegate? = null

    @JvmStatic
    fun register(app: Application) {
        checkMainThread()
        val delegate = ImageLoaderDelegateImpl(app.cacheDir, ImageRequesterImpl())
        app.registerActivityLifecycleCallbacks(delegate)
        this.delegate = delegate
    }

    @JvmStatic
    fun loadImage(url: String, view: ImageView) {
        checkMainThread()
        checkRegistered()
        delegate?.loadImage(url, view)
    }

    private fun checkRegistered() {
        checkNotNull(delegate) {
            "ImageLoader is not registered, call ImageLoader.register() from your Application class"
        }
    }
}
