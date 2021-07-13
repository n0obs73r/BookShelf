package com.example.booklibrary

import androidx.lifecycle.*
import com.example.booklibrary.database.Book
import com.example.booklibrary.database.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    var allBooks = MutableLiveData<List<Book>>(listOf())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allBooks.postValue(repository.getAll())
            }
        }
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }

    fun searchByName(bookName: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            allBooks.postValue(repository.searchBookByName(bookName))
        }
    }
}

class BookViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}