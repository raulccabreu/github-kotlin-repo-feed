package com.example.githubktrepofeed.data.database

import androidx.paging.PagingSource

interface DatabaseService {
    suspend fun saveAllRepositories(repositories: List<DatabaseRepository>)
    fun getAllRepositories() : PagingSource<Int, DatabaseRepository>
    suspend fun saveAllLastPaging(lastPaging: List<DatabasePaging>)
    suspend fun getLastPaging(lastItemId: String): DatabasePaging?
}