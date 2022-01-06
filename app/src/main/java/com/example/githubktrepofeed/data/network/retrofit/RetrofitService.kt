package com.example.githubktrepofeed.data.network.retrofit

import android.util.Log
import com.example.githubktrepofeed.data.network.NetworkRepositories
import com.example.githubktrepofeed.data.network.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService : NetworkService {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    object Repositories {
        val api : RepositoriesApi by lazy {
            retrofit.create(RepositoriesApi::class.java)
        }
    }

    override suspend fun getRepositories(page: Int): NetworkRepositories {
        return Repositories.api.search(page)
    }
}