package com.bcit.myapp.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bcit.myapp.data.LocalBook

@Composable
fun Search(booksState: BooksState, navController: NavController) {
    Column {
        val dummyLocalBooks: List<LocalBook>? = booksState
            .books
            ?.results
            ?.map {
                book ->
                LocalBook(
                    id = book.id,
                    title = book.title,
                    displayAuthorName = book.authors
                        .map { it.name }
                        .getOrElse(0) {
                            "unknown"
                        },
                    null
                )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = booksState.queryFlow.collectAsState().value,
            singleLine = true,
            placeholder = { Text("search for books...") },
            onValueChange = {
                booksState.queryFlow.value = it
            }
        )

        if (dummyLocalBooks == null || dummyLocalBooks.isEmpty()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No books found.", fontSize = 24.sp)
            }
        } else {
            LazyColumn {
                items(dummyLocalBooks.size) {
                    BookCard(dummyLocalBooks[it], navController)
                }
            }
        }
    }
}