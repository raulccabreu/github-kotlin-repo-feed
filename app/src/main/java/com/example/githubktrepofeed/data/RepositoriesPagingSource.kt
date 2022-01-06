package com.example.githubktrepofeed.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubktrepofeed.data.network.NetworkRepository
import com.example.githubktrepofeed.data.network.NetworkService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE = 1

class RepositoriesPagingSource(private val service: NetworkService)
    : PagingSource<Int, NetworkRepository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkRepository> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val repositories = service.getRepositories(page).items
            val nextKey = if (repositories.isEmpty()) {
                null
            } else {
                page + 1
            }
            LoadResult.Page(
                data = repositories,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, NetworkRepository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}