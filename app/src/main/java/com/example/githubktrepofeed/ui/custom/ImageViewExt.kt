package com.example.githubktrepofeed.ui.custom

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["url"])
fun ImageView.bindUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Picasso.get().load(url).into(this)
    }
}