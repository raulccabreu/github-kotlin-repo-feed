package com.example.githubktrepofeed.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.githubktrepofeed.data.RepositoriesData
import com.example.githubktrepofeed.data.database.asDomainModel
import com.example.githubktrepofeed.domain.models.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoriesViewModel(private val repositoriesData: RepositoriesData) : ViewModel() {
    val pagingDataFlow: Flow<PagingData<Repository>>

    init {
        pagingDataFlow = fetchRepositories()
    }

    private fun fetchRepositories(): Flow<PagingData<Repository>> {
        return repositoriesData.fetchRepositories().map {
            pagingData -> pagingData.map {
                it.asDomainModel()
            }
        }.cachedIn(viewModelScope)
    }
}

class RepositoriesViewModelFactory(private val repositoriesData: RepositoriesData)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoriesViewModel::class.java)) {
            return RepositoriesViewModel(repositoriesData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}