package com.bcit.myapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalBook::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localBookDao(): LocalBookDao
}