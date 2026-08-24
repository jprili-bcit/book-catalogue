package com.bcit.myapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocalBookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<LocalBook>

    @Delete
    fun remove(localBook: LocalBook)

    @Insert
    fun add(localBook: LocalBook)
}