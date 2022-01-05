package com.example.githubktrepofeed.data

import com.example.githubktrepofeed.data.network.NetworkService

class RepositoriesData(private val networkService: NetworkService) {
    suspend fun getRepositories(keywords: String, sort: String, page: Int) : String {
        return networkService.getRepositories(keywords, sort, page)
    }
}