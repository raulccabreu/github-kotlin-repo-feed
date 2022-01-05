package com.example.githubktrepofeed.ui.custom

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["loading"])
fun ProgressBar.progressVisibility(loading: Boolean) {
    isVisible = loading
}