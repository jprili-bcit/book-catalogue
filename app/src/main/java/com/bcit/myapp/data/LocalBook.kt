package com.bcit.myapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class LocalBook(
    @PrimaryKey val id: Int,
    val title: String,
    @ColumnInfo(name = "display_author") val displayAuthorName: String,
    @ColumnInfo(name = "image_link")     val imageLink: String?
)