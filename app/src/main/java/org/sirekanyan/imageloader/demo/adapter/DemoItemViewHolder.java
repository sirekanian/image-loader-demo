package org.sirekanyan.imageloader.demo.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.sirekanyan.imageloader.ImageLoader;
import org.sirekanyan.imageloader.demo.R;
import org.sirekanyan.imageloader.demo.databinding.DemoItemViewBinding;

public class DemoItemViewHolder extends RecyclerView.ViewHolder {

    private final DemoItemViewBinding binding;

    public DemoItemViewHolder(@NonNull DemoItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(@NotNull DemoItemModel model) {
        binding.imageNumber.setText(String.valueOf(model.getId()));
        Glide.with(binding.getRoot().getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_error)
                .into(binding.imageView1);
        ImageLoader
                .load(model.getImageUrl())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_error)
                .into(binding.imageView2);
    }
}
