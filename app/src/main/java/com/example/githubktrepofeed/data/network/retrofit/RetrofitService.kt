package com.example.githubktrepofeed.data.network.retrofit

import com.example.githubktrepofeed.data.network.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitService : NetworkService {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    object Repositories {
        val api : RepositoriesApi by lazy {
            retrofit.create(RepositoriesApi::class.java)
        }
    }

    override suspend fun getRepositories(keywords: String, sort: String, page: Int): String {
        return Repositories.api.search(keywords, sort, page)
    }
}