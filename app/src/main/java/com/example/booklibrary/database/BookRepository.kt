package com.example.booklibrary.database

import androidx.annotation.WorkerThread

class BookRepository(private val bookDao: BookDao) {

    suspend fun getAll(): List<Book> {
        return bookDao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insertAll(book)
    }

    suspend fun searchBookByName(name: String) : List<Book> {
        return bookDao.findByName(name)
    }
}
