package com.bcit.myapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class BooksRepository(val client: HttpClient) {
    suspend fun queryBooks(query: String): Books {
        val response = client.get {
            url(BOOKS)
            parameter("q", query)
        }
        return response.body<Books>()
    }

    suspend fun getBook(id: Int): Book? {
        val books = client.get("${BOOKS}/$id").body<Books>()
        return if (books.results.isEmpty()) {
            null
        } else {
            books.results[0]
        }
    }
}