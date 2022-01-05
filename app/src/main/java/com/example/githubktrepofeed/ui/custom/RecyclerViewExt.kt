package com.example.githubktrepofeed.ui.custom

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["adapter"])
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}