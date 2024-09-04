package org.sirekanyan.imageloader.demo.extensions

import org.sirekanyan.imageloader.demo.adapter.DemoItemModel
import org.sirekanyan.imageloader.demo.repository.model.ImageModel

fun ImageModel.toDemoItemModel(): DemoItemModel =
    DemoItemModel(id, imageUrl)
