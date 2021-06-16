package com.example.witsoftwarechallenge.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.witsoftwarechallenge.R
import com.example.witsoftwarechallenge.util.BASE_URL

object BindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = "https://openweathermap.org/img/wn/$imgUrl.png".toUri()

            Glide.with(imgView.context)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imgView)
        }
    }
}