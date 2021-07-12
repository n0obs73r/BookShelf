package com.example.booklibrary

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    val allBooks: Flow<List<Book>> = bookDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insertAll(book)
    }

    fun searchBookByName(name: String) : Flow<List<Book>>{
        return bookDao.findByName(name)
    }
}
