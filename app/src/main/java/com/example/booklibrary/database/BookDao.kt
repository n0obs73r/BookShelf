package com.example.booklibrary.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM bookstore")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM bookstore WHERE book_id IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Book>

    @Query("SELECT * FROM bookstore WHERE book_name LIKE :name")
    suspend fun findByName(name: String): List<Book>

    @Insert
    suspend fun insertAll(vararg users: Book)

    @Delete
    suspend fun delete(user: Book)

    @Query("DELETE FROM bookstore")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM bookstore where book_id = :bookId")
    suspend fun exists(bookId: Int): Int
}