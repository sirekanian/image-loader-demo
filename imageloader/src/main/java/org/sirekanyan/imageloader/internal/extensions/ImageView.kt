package org.sirekanyan.imageloader.internal.extensions

import android.widget.ImageView
import org.sirekanyan.imageloader.R

internal fun ImageView.hasUrlTag(url: String): Boolean =
    getTag(R.id.image_loader_url_tag) == url

internal fun ImageView.setUrlTag(url: String) {
    setTag(R.id.image_loader_url_tag, url)
}
