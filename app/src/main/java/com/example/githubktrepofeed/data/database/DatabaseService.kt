package com.example.githubktrepofeed.data.database

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    suspend fun saveAllRepositories(repositories: List<DatabaseRepository>)
    fun getAllRepositories() : Flow<List<DatabaseRepository>>
}