package com.example.githubktrepofeed.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubktrepofeed.data.database.DatabaseRepository
import com.example.githubktrepofeed.data.database.DatabaseService
import kotlinx.coroutines.flow.Flow

@Database(entities = [DatabaseRepository::class], version = 1)
abstract class RepositoriesDatabase: RoomDatabase() {
    abstract val repositoryDao: RepositoryDao
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

    override fun getAllRepositories(): Flow<List<DatabaseRepository>> {
        return repositoriesDatabase.repositoryDao.getAll()
    }
}