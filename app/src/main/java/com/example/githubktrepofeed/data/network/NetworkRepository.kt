package com.example.githubktrepofeed.data.network

import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.domain.models.Repository
import com.google.gson.annotations.SerializedName

data class NetworkRepositories(@SerializedName("items") val items: List<NetworkRepository>)

fun NetworkRepositories.asDatabaseModel(): List<DatabaseRepository> {
    return items.map {
        DatabaseRepository(
            id = it.id,
            name = it.name,
            stars = it.stars,
            forks = it.forks,
            authorName = it.author.name,
            authorAvatarUrl = it.author.avatarUrl)
    }
}

fun NetworkRepository.asDomainModel(): Repository {
    return Repository(
        id = this.id,
        name = this.name,
        stars = this.stars,
        forks = this.forks,
        authorName = this.author.name,
        authorAvatarUrl = this.author.avatarUrl)
}

data class NetworkRepository(@SerializedName("node_id") val id: String,
                             val name: String,
                             @SerializedName("stargazers_count") val stars: Int,
                             @SerializedName("forks_count") val forks: Int,
                             @SerializedName("owner") val author: NetworkAuthor)

data class NetworkAuthor(@SerializedName("login") val name: String,
                         @SerializedName("avatar_url") val avatarUrl: String)