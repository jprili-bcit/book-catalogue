package com.bcit.myapp.data

import android.content.Context
import androidx.room.Room

object LocalBookDatabase {
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "local_book_db"
        )
            .allowMainThreadQueries()
            .build()
    }
}