package org.sirekanyan.imageloader.demo.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback

object DemoItemCallback : ItemCallback<DemoItemModel>() {

    override fun areItemsTheSame(oldItem: DemoItemModel, newItem: DemoItemModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DemoItemModel, newItem: DemoItemModel): Boolean =
        oldItem == newItem
}
