package org.sirekanyan.imageloader

import java.util.concurrent.TimeUnit

internal const val TAG = "ImageLoader"
internal const val IMAGE_QUALITY = 300
internal const val CACHE_PREFIX = "org.sirekanyan.imageloader.cache."
internal val CACHE_LIFETIME = TimeUnit.HOURS.toMillis(4)
