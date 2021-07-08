package com.example.booklibrary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Book::class], version = 1)
abstract class  AppDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.bookDao())
                }
            }
        }

        fun populateDatabase(bookDao: BookDao) {
            bookDao.deleteAll()

            val book = Book(1, "ABC",  100, "XYZ")
            val book1 = Book(2, "DEF", 200, "MNO")
            val book2 = Book(3, "GHI", 300,  "UVW")

            bookDao.insertAll(book, book1, book2)
        }
    }

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
