package com.example.githubktrepofeed.data.network

interface NetworkService {
    suspend fun getRepositories(keywords: String, sort: String, page: Int): NetworkRepositories
}