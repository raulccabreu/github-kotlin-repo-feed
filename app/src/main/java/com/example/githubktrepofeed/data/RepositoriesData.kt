package com.example.githubktrepofeed.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.data.database.DatabaseService
import com.example.githubktrepofeed.data.network.NetworkRepository
import com.example.githubktrepofeed.data.network.NetworkService
import kotlinx.coroutines.flow.Flow

private const val GITHUB_PAGE_SIZE = 30

//Repository Pattern
class RepositoriesData(private val networkService: NetworkService,
                       private val databaseService: DatabaseService) {
    fun fetchRepositories(): Flow<PagingData<NetworkRepository>> {
        return  Pager(
            config = PagingConfig(
                pageSize = GITHUB_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RepositoriesPagingSource(networkService) }
        ).flow
    }

    fun getRepositories() : Flow<List<DatabaseRepository>> {
        return databaseService.getAllRepositories();
    }
}