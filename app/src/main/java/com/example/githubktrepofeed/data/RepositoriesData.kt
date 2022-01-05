package com.example.githubktrepofeed.data

import com.example.githubktrepofeed.data.network.NetworkService
import com.example.githubktrepofeed.data.network.asDomainModel
import com.example.githubktrepofeed.domain.models.Repository

class RepositoriesData(private val networkService: NetworkService) {
    suspend fun getRepositories(keywords: String, sort: String, page: Int) : List<Repository> {
        return networkService.getRepositories(keywords, sort, page).asDomainModel()
    }
}