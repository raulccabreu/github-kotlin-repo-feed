package com.example.githubktrepofeed.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.githubktrepofeed.data.database.DatabasePaging
import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.data.network.asDatabaseModel
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class RepositoriesMediator(
    private val repositoriesData: RepositoriesData
) : RemoteMediator<Int, DatabaseRepository>() {

    private suspend fun getPagingForLastItem(state: PagingState<Int, DatabaseRepository>):
            DatabasePaging? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repository ->
                repositoriesData.getLastPaging(repository.id)
            }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, DatabaseRepository>):
            MediatorResult {

        val lastPage: Int = when (loadType) {
            LoadType.REFRESH -> {
                STARTING_PAGE
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val databasePaging = getPagingForLastItem(state)
                val page = databasePaging?.lastPage
                if (page == null) {
                    return MediatorResult.Success(endOfPaginationReached = databasePaging != null)
                }
                page
            }
        }

        try {
            val networkRepositories = repositoriesData.fetchRepositories(lastPage)
            val repositories = networkRepositories.asDatabaseModel()
            val endOfPaginationReached = repositories.isEmpty()

            coroutineScope {
                val page = if (endOfPaginationReached) null else lastPage + 1
                val databasePagings = repositories.map {
                    DatabasePaging(lastItemId = it.id, lastPage = page)
                }
                repositoriesData.saveAllLastPaging(databasePagings)
                repositoriesData.saveAllRepositories(repositories)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}