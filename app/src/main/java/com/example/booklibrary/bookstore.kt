package com.example.booklibrary

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class bookstore(@PrimaryKey val BookNo: Int,
                     @ColumnInfo(name = "book_name") val BookName: String?,
                     @ColumnInfo(name = "book_price") val BookPrice: Int?,
                     @ColumnInfo(name = "author_name") val AuthorName: String? )
