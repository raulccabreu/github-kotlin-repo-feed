package com.example.githubktrepofeed.data.network.retrofit

import com.example.githubktrepofeed.data.network.NetworkRepositories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun search(@Query("page") page: Int): NetworkRepositories
}