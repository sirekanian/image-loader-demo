package org.sirekanyan.imageloader.internal.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import org.sirekanyan.imageloader.R

internal fun ImageView.hasUrlTag(url: String): Boolean =
    getTag(R.id.image_loader_url_tag) == url

internal fun ImageView.setUrlTag(url: String) {
    setTag(R.id.image_loader_url_tag, url)
}

internal fun ImageView.setImageResource(@DrawableRes resId: Int?) {
    if (resId == null) {
        setImageDrawable(null)
    } else {
        setImageResource(resId)
    }
}
