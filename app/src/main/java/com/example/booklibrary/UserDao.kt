package com.example.booklibrary

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<database>

    @Query("SELECT * FROM user WHERE BookNo IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<database>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): database

    @Insert
    fun insertAll(vararg users: database)

    @Delete
    fun delete(user: database)
}