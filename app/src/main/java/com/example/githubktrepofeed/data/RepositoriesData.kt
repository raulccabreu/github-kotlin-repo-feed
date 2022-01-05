package com.example.githubktrepofeed.data

import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.data.database.DatabaseService
import com.example.githubktrepofeed.data.network.NetworkService
import com.example.githubktrepofeed.data.network.asDatabaseModel
import kotlinx.coroutines.flow.Flow

//Repository Pattern
class RepositoriesData(private val networkService: NetworkService,
                       private val databaseService: DatabaseService) {
    suspend fun refreshRepositories(keywords: String, sort: String, page: Int) {
        databaseService.saveAllRepositories(
            networkService.getRepositories(keywords, sort, page).asDatabaseModel())
    }

    fun getRepositories() : Flow<List<DatabaseRepository>> {
        return databaseService.getAllRepositories();
    }
}