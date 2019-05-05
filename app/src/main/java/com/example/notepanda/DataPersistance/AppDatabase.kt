package com.example.notepanda.DataPersistance

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(ListEntity::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    companion object {
        var DatabaseInstance : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            return DatabaseInstance ?: createDatabase(context)
        }

        private fun createDatabase(context: Context): AppDatabase{
            DatabaseInstance = Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .build()
            return DatabaseInstance as AppDatabase
        }
    }
    abstract fun listDao(): ListDAO
}