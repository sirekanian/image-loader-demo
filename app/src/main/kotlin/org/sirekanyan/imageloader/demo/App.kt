package org.sirekanyan.imageloader.demo

import android.app.Application
import android.content.Context
import org.sirekanyan.imageloader.ImageLoader
import org.sirekanyan.imageloader.demo.repository.ImageRepository
import org.sirekanyan.imageloader.demo.repository.ImageRepositoryImpl

fun Context.app(): App =
    applicationContext as App

class App : Application() {

    val repository: ImageRepository = ImageRepositoryImpl()

    override fun onCreate() {
        super.onCreate()
        ImageLoader.register(this)
    }
}
