package com.example.githubktrepofeed

import android.app.Application
import com.example.githubktrepofeed.data.RepositoriesData
import com.example.githubktrepofeed.data.database.room.RoomService
import com.example.githubktrepofeed.data.network.retrofit.RetrofitService

class RepositoriesApplication : Application() {
    val repositoriesData by lazy {
        RoomService.initDatabase(this)
        RepositoriesData(RetrofitService, RoomService)
    }
}