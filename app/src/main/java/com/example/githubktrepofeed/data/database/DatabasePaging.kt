package com.example.githubktrepofeed.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paging")
data class DatabasePaging(
    @PrimaryKey
    @ColumnInfo(name = "last_item_id")
    val lastItemId: String,
    @ColumnInfo(name = "last_page")
    val lastPage: Int?
)