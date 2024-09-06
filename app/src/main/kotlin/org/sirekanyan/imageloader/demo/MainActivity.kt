package org.sirekanyan.imageloader.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sirekanyan.imageloader.ImageLoader
import org.sirekanyan.imageloader.demo.adapter.DemoItemAdapter
import org.sirekanyan.imageloader.demo.adapter.DemoItemModel
import org.sirekanyan.imageloader.demo.databinding.MainActivityBinding
import org.sirekanyan.imageloader.demo.extensions.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private val repository by lazy { app().repository }
    private val adapter = DemoItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.demoRecyclerView.adapter = adapter
        binding.appToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.clear_image_cache -> onClearCacheClicked()
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            try {
                updateViews(hello = R.string.main_message_loading)
                val items = repository.getDemoItems()
                updateViews(items = items)
            } catch (exception: Exception) {
                Log.e("ImageLoaderDemo", "Unable to show content", exception)
                updateViews(hello = R.string.main_message_error)
            }
        }
    }

    private fun updateViews(
        @StringRes hello: Int? = null,
        items: List<DemoItemModel> = listOf(),
    ) {
        if (hello == null) {
            binding.helloMessage.visibility = View.GONE
        } else {
            binding.helloMessage.visibility = View.VISIBLE
            binding.helloMessage.setText(hello)
        }
        adapter.submitList(items)
    }

    private fun onClearCacheClicked(): Boolean {
        showToast(R.string.toast_clear_cache)
        ImageLoader.clearCache()
        return true
    }
}
