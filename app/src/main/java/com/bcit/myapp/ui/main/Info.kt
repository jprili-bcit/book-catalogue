package com.bcit.myapp.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bcit.myapp.data.Book
import com.bcit.myapp.data.LocalBook

@Composable
fun Info(bookId: Int,
         booksState: BooksState,
         localBooksState: LocalBooksState
) {
    LaunchedEffect(booksState) {
        booksState.getBook(id = bookId)
    }
    val book: Book? = booksState.book
    if (book != null) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            item {
                Box(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = book.coverImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                }
            }

            item {
                Column(Modifier.padding(bottom = 14.dp)) {
                    Text(book.title, fontSize = 24.sp)
                    Row {
                        println(book)
                        Text(
                            book.authors[0].name
                                    + if (book.authors.size > 1)
                                        " et al." else ""
                        )
                    }
                    Row {
                        val uriHandler = LocalUriHandler.current
                        OutlinedIconButton(
                            onClick = {
                                localBooksState.onClick(
                                    LocalBook(
                                        book.id,
                                        book.title,
                                        book.authors[0].name,
                                        book.coverImage
                                    )
                                )
                            }
                        ) {
                            if (localBooksState.contains(book.id)) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.Red,
                                )
                            } else {
                                Icon(imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                )
                            }
                        }
                        OutlinedIconButton(onClick = {
                           uriHandler.openUri(book.formats.html)
                        }) {
                            Icon(imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
            item {
                Box(Modifier.fillMaxSize()) {
                    Text(book.summary ?: "No description found.")
                }
            }
        }
    }
}

