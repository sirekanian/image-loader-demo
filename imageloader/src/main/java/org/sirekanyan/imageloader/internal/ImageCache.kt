package org.sirekanyan.imageloader.internal

import android.util.Log
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.sirekanyan.imageloader.CACHE_PREFIX
import org.sirekanyan.imageloader.TAG
import java.io.File
import java.io.FileFilter
import java.lang.System.currentTimeMillis

interface ImageCache {

    fun newFile(filename: String): File
    suspend fun cleanup(lifetime: Long = 0)
}

class ImageCacheImpl(private val cacheDir: File) : ImageCache {

    private val fileFilter = FileFilter { it.name.startsWith(CACHE_PREFIX) }

    override fun newFile(filename: String): File =
        File(cacheDir, "$CACHE_PREFIX$filename")

    override suspend fun cleanup(lifetime: Long) {
        withContext(IO) {
            cacheDir.listFiles(fileFilter)?.forEach { file ->
                runCatching {
                    deleteFile(file, maxLastModified = currentTimeMillis() - lifetime)
                }.onFailure {
                    Log.w(TAG, "Cannot delete cache file ${file.name}")
                }
            }
        }
    }

    private fun deleteFile(file: File, maxLastModified: Long) {
        if (file.lastModified() < maxLastModified) {
            Log.d(TAG, "Cleanup ${file.name}")
            check(file.delete()) { "Cannot delete file" }
        }
    }
}
