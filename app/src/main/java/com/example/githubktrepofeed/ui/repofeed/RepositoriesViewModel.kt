package com.example.githubktrepofeed.ui.repofeed

import android.util.Log
import androidx.lifecycle.*
import com.example.githubktrepofeed.data.RepositoriesData
import com.example.githubktrepofeed.data.network.NetworkService
import com.example.githubktrepofeed.domain.models.Repository
import kotlinx.coroutines.launch

class RepositoriesViewModel(private val repositoriesData: RepositoriesData) : ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = _repositories
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val repositories = repositoriesData.getRepositories("language:kotlin", "stars", 1)
                _repositories.value = repositories
            } catch (e: Exception) {
                Log.e("RepositoriesViewModel", "${e.message}")
                _repositories.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
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