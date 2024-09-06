package org.sirekanyan.imageloader.internal.extensions

import java.util.UUID

internal fun String.toUuid(): String =
    UUID.nameUUIDFromBytes(toByteArray()).toString()
