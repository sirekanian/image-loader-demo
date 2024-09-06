package org.sirekanyan.imageloader.demo.extensions

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.StringRes

fun Context.showToast(@StringRes messageId: Int) {
    Toast.makeText(this, messageId, LENGTH_SHORT).show()
}
