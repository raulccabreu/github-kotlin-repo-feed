package com.example.githubktrepofeed.data.network.retrofit

import com.example.githubktrepofeed.data.network.NetworkRepositories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {
    @GET("search/repositories")
    suspend fun search(@Query("q") keywords: String,
                       @Query("sort") sort: String,
                       @Query("page") page: Int): NetworkRepositories
}