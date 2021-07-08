package com.example.booklibrary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM bookstore")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * FROM bookstore WHERE book_id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Book>

    @Query("SELECT * FROM bookstore WHERE book_name LIKE :name LIMIT 1")
    fun findByName(name: String): Book

    @Insert
    fun insertAll(vararg users: Book)

    @Delete
    fun delete(user: Book)

    @Query("DELETE FROM bookstore")
    fun deleteAll()
}