package com.example.githubktrepofeed.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubktrepofeed.domain.models.Repository

@Entity(tableName = "repository")
data class DatabaseRepository constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val stars: Int,
    val forks: Int,
    @ColumnInfo(name = "author_name")
    val authorName: String,
    @ColumnInfo(name = "author_avatar_url")
    val authorAvatarUrl: String)

fun List<DatabaseRepository>.asDomainModel(): List<Repository> {
    return map {
        Repository(
            id = it.id,
            name = it.name,
            stars = it.stars,
            forks = it.forks,
            authorName = it.authorName,
            authorAvatarUrl = it.authorAvatarUrl)
    }
}