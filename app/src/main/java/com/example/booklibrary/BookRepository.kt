package com.example.booklibrary

import androidx.annotation.WorkerThread
import java.util.concurrent.Flow


class BookRepository(private val bookDao: BookDao) {
    val allBook: Flow<List<BookInfo>> = bookDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(book: BookInfo) {
        bookDao.insertAll(book)
    }
}
