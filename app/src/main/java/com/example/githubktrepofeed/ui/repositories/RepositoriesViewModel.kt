package com.example.githubktrepofeed.ui.repositories

import android.util.Log
import androidx.lifecycle.*
import com.example.githubktrepofeed.data.RepositoriesData
import com.example.githubktrepofeed.data.database.asDomainModel
import com.example.githubktrepofeed.domain.models.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoriesViewModel(private val repositoriesData: RepositoriesData) : ViewModel() {
    val repositories: LiveData<List<Repository>> =
        Transformations.map(repositoriesData.getRepositories().asLiveData()) {
            it.asDomainModel()
        }
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        refreshRepositories()
    }

    private fun refreshRepositories() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _loading.postValue(true)
                    repositoriesData.refreshRepositories("language:kotlin", "stars", 1)
                } catch (e: Exception) {
                    Log.e("RepositoriesViewModel", "${e.message}")
                } finally {
                    _loading.postValue(false)
                }
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