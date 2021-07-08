package com.example.booklibrary

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(database::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}