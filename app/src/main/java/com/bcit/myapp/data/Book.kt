package com.bcit.myapp.data

import com.google.gson.annotations.SerializedName

data class Author(
    val id: Int,
    val name: String
)

data class BookFormat(
    @SerializedName("text/html")
    val html: String
)

data class Book(
    val id: Int,
    val title:   String,
    val authors: List<Author>,

    @SerializedName("cover_image")
    val coverImage: String,
    val formats: BookFormat,
    val summary: String?
)

data class Books(
    val results: List<Book>
)
