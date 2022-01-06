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

fun DatabaseRepository.asDomainModel(): Repository {
    return Repository(
        id = this.id,
        name = this.name,
        stars = this.stars,
        forks = this.forks,
        authorName = this.authorName,
        authorAvatarUrl = this.authorAvatarUrl)
}