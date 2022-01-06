package com.example.githubktrepofeed.data.database.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubktrepofeed.data.database.DatabaseRepository

@Dao
interface RepositoryDao {
    @Query("select * from repository ORDER BY stars DESC")
    fun getAll(): PagingSource<Int, DatabaseRepository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repositories: List<DatabaseRepository>)
}