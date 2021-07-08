package com.example.booklibrary

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BookApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy {
        AppDatabase.getDatabase(this, applicationScope)
    }
    val repository by lazy {
        BookRepository(database.bookDao())
    }
}