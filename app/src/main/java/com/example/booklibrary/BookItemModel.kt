package com.example.booklibrary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookItemModel : ViewModel() {
    val bookItem = MutableLiveData<ArrayList<BookItemModel>>()

//    init {
//        viewModelScope.launch {
//            bookItem.value
//        }
//    }

}