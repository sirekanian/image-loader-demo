package org.sirekanyan.imageloader.demo.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import org.sirekanyan.imageloader.ImageLoader
import org.sirekanyan.imageloader.demo.R
import org.sirekanyan.imageloader.demo.databinding.DemoItemViewBinding

class DemoItemViewHolder(
    private val binding: DemoItemViewBinding,
) : ViewHolder(binding.root) {

    fun bind(model: DemoItemModel) {
        binding.imageNumber.text = "${model.id}"
        Glide.with(binding.root.context)
            .load(model.imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_image_error)
            .into(binding.imageView1)
        ImageLoader.loadImage(
            url = model.imageUrl,
            view = binding.imageView2,
            placeholder = R.drawable.ic_image_placeholder,
            error = R.drawable.ic_image_error,
        )
    }
}
