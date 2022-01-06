package com.example.githubktrepofeed.data.network

interface NetworkService {
    suspend fun getRepositories(page: Int): NetworkRepositories
}