package com.bcit.myapp.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.gson.gson

val client = HttpClient {
    install(ContentNegotiation) {
        gson()
    }

    defaultRequest {
        header("X-RapidAPI-Key", API_KEY)
        header("X-RapidAPI-Host", API_HOST)
    }
}