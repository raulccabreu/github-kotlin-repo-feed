package com.example.githubktrepofeed.ui.repofeed

import androidx.lifecycle.*
import com.example.githubktrepofeed.data.RepositoriesData
import com.example.githubktrepofeed.data.network.NetworkService
import kotlinx.coroutines.launch

class RepositoriesViewModel(networkService: NetworkService) : ViewModel() {
    private val repositoriesData = RepositoriesData(networkService)
    private val _repositories = MutableLiveData<String>()
    val repositories: LiveData<String>
        get() = _repositories

    init {
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch {
            try {
                val repositories = repositoriesData.getRepositories("language:kotlin", "stars", 1)
                _repositories.value = repositories
            } catch (e: Exception) {
                _repositories.value = "Failure: ${e.message}"
            }
        }
    }
}

class RepositoriesViewModelFactory(private val networkService: NetworkService)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoriesViewModel::class.java)) {
            return RepositoriesViewModel(networkService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}