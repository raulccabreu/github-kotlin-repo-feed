package com.example.githubktrepofeed.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubktrepofeed.data.database.DatabasePaging
import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.data.database.DatabaseService
import com.example.githubktrepofeed.data.network.NetworkRepositories
import com.example.githubktrepofeed.data.network.NetworkService
import kotlinx.coroutines.flow.Flow

private const val GITHUB_PAGE_SIZE = 30

//Repository Pattern
class RepositoriesData(private val networkService: NetworkService,
                       private val databaseService: DatabaseService) {
    @OptIn(ExperimentalPagingApi::class)
    fun fetchRepositories(): Flow<PagingData<DatabaseRepository>> {
        return Pager(
            config = PagingConfig(
                pageSize = GITHUB_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = RepositoriesMediator(this),
            pagingSourceFactory = { databaseService.getAllRepositories() }
        ).flow
    }

    suspend fun fetchRepositories(page: Int): NetworkRepositories {
        return networkService.getRepositories(page)
    }

    suspend fun saveAllRepositories(repositories: List<DatabaseRepository>) {
        databaseService.saveAllRepositories(repositories)
    }

    suspend fun saveAllLastPaging(lastPaging: List<DatabasePaging>) {
        databaseService.saveAllLastPaging(lastPaging)
    }

    suspend fun getLastPaging(lastItemId: String): DatabasePaging? {
        return databaseService.getLastPaging(lastItemId)
    }
}