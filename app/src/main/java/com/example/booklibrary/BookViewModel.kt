package com.example.booklibrary

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    var allBooks = MutableLiveData<List<Book>>(listOf<Book>())

    init {
        allBooks.value = repository.allBooks.asLiveData().value
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
    fun searchByName(bookName: String) {
        allBooks.value = repository.searchBookByName(bookName).asLiveData().value
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