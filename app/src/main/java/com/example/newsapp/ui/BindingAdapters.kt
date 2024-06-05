package com.example.newsapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ImageRequest


@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl?.let {
        load(imageUrl)
    }
}
