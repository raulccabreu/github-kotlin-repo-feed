package com.example.githubktrepofeed.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubktrepofeed.data.database.DatabaseRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {
    @Query("select * from repository")
    fun getAll(): Flow<List<DatabaseRepository>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repositories: List<DatabaseRepository>)
}