package org.sirekanyan.imageloader.internal.extensions

import android.os.Looper

internal fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "ImageLoader must be used from the main thread"
    }
}
