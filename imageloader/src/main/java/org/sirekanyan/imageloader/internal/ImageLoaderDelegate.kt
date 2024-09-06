package org.sirekanyan.imageloader.internal

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sirekanyan.imageloader.TAG
import org.sirekanyan.imageloader.internal.extensions.MyLifecycleCallbacks
import org.sirekanyan.imageloader.internal.extensions.decodeSampledBitmap
import org.sirekanyan.imageloader.internal.extensions.hasUrlTag
import org.sirekanyan.imageloader.internal.extensions.setImageResource
import org.sirekanyan.imageloader.internal.extensions.setUrlTag
import org.sirekanyan.imageloader.internal.extensions.toUuid
import java.io.File
import kotlin.coroutines.CoroutineContext

internal interface ImageLoaderDelegate {

    fun loadImage(url: String, view: ImageView, placeholder: Int?, error: Int?)
}

internal class ImageLoaderDelegateImpl(
    private val cacheDir: File,
    private val requester: ImageRequester,
) : CoroutineScope, MyLifecycleCallbacks, ImageLoaderDelegate {

    private val supervisorJob = SupervisorJob()
    private var isActivityStarted = false

    override val coroutineContext: CoroutineContext =
        supervisorJob + Dispatchers.Main + CoroutineExceptionHandler { _, exception ->
            Log.e(TAG, "Uncaught exception", exception)
        }

    override fun onActivityStarted(activity: Activity) {
        isActivityStarted = true
    }

    override fun onActivityStopped(activity: Activity) {
        isActivityStarted = false
        supervisorJob.cancelChildren()
    }

    override fun loadImage(url: String, view: ImageView, placeholder: Int?, error: Int?) {
        if (!isActivityStarted) {
            Log.w(TAG, "The image cannot be loaded before the activity is started")
            return
        }
        launch {
            view.setImageResource(placeholder)
            view.setUrlTag(url)
            val bitmap = withContext(Dispatchers.IO) {
                runCatching {
                    val uuid = url.toUuid()
                    val cached = File(cacheDir, uuid)
                    if (cached.exists()) {
                        decodeSampledBitmap(cached)
                    } else {
                        val temp = File(cacheDir, "$uuid.tmp")
                        requester.request(url).copyAndClose(temp.writeChannel())
                        decodeSampledBitmap(temp).also {
                            if (it != null) {
                                temp.renameTo(cached)
                            }
                        }
                    }
                }.onFailure { exception ->
                    Log.e(TAG, "Cannot load bitmap", exception)
                }.getOrNull()
            }
            if (view.hasUrlTag(url)) {
                if (bitmap == null) {
                    view.setImageResource(error)
                } else {
                    view.setImageBitmap(bitmap)
                }
            }
        }
    }
}
