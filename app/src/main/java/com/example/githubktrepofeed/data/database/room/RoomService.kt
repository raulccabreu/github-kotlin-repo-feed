package com.example.githubktrepofeed.data.database.room

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubktrepofeed.data.database.DatabasePaging
import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.data.database.DatabaseService

@Database(entities = [DatabaseRepository::class, DatabasePaging::class], version = 1,
    exportSchema = false)
abstract class RepositoriesDatabase: RoomDatabase() {
    abstract val repositoryDao: RepositoryDao
    abstract val pagingDao: PagingDao
}

object RoomService : DatabaseService {
    private lateinit var repositoriesDatabase: RepositoriesDatabase

    fun initDatabase(context: Context) {
        repositoriesDatabase = Room.databaseBuilder(
            context.applicationContext,
            RepositoriesDatabase::class.java,
            "repository_database"
        ).build()
    }

    override suspend fun saveAllRepositories(repositories: List<DatabaseRepository>) {
        repositoriesDatabase.repositoryDao.insertAll(repositories)
    }

    override fun getAllRepositories(): PagingSource<Int, DatabaseRepository> {
        return repositoriesDatabase.repositoryDao.getAll()
    }

    override suspend fun saveAllLastPaging(lastPaging: List<DatabasePaging>) {
        repositoriesDatabase.pagingDao.insertAll(lastPaging)
    }

    override suspend fun getLastPaging(lastItemId: String): DatabasePaging? {
        return repositoriesDatabase.pagingDao.getByLastItemId(lastItemId)
    }
}