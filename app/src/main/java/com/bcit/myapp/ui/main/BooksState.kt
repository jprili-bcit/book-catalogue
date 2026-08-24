package com.bcit.myapp.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcit.myapp.data.Book
import com.bcit.myapp.data.BooksRepository
import com.bcit.myapp.data.Books
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class BooksState(private val repository: BooksRepository) : ViewModel() {
    var books by mutableStateOf<Books?>(null)
    var book  by mutableStateOf<Book?>(null)
    var queryFlow = MutableStateFlow("")

    init {
        collectSearchInputs()
    }

    suspend fun queryBooks(query: String) {
        books = repository.queryBooks(query)
    }

    suspend fun getBook(id: Int) {
        book = repository.getBook(id)
    }

    @OptIn(FlowPreview::class)
    fun collectSearchInputs() {
        viewModelScope.launch {
            queryFlow
                .debounce(1000L)
                .collect {
                    books = repository.queryBooks(it)
                }
        }
    }
}