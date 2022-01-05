package com.example.githubktrepofeed.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubktrepofeed.databinding.RepositoryBinding
import com.example.githubktrepofeed.domain.models.Repository

class RepositoriesAdapter : ListAdapter<Repository, RepositoriesAdapter.RepositoryViewHolder>
    (Companion) {

    class RepositoryViewHolder(val binding: RepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object: DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository):
                Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: Repository, newItem: Repository):
                Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepositoryBinding.inflate(layoutInflater)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding.repository = getItem(position)
        holder.binding.executePendingBindings()
    }
}
