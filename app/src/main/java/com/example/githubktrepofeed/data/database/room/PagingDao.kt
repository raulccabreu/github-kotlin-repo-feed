package com.example.githubktrepofeed.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubktrepofeed.data.database.DatabasePaging

@Dao
interface PagingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(paging: List<DatabasePaging>)
    @Query("SELECT * FROM paging WHERE last_item_id = :lastItemId")
    suspend fun getByLastItemId(lastItemId: String): DatabasePaging?
}