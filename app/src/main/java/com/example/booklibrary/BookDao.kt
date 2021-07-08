package com.example.booklibrary

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<BookInfo>>

    @Query("SELECT * FROM user WHERE BookNo IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<BookInfo>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): BookInfo

    @Insert
    fun insertAll(vararg users: BookInfo)

    @Delete
    fun delete(user: BookInfo)
}