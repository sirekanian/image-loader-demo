package org.sirekanyan.imageloader.demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sirekanyan.imageloader.demo.databinding.DemoItemViewBinding

class DemoItemAdapter : ListAdapter<DemoItemModel, DemoItemViewHolder>(DemoItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DemoItemViewHolder(DemoItemViewBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: DemoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
