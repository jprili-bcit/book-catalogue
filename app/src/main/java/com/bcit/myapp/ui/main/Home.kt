package com.bcit.myapp.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.serialization.internal.throwMissingFieldException


@Composable
fun Home(localBooksState: LocalBooksState, navController: NavController) {
    if (localBooksState.localBooks.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("No books added.", fontSize = 24.sp)
                Text("Add a book to start.", fontSize = 24.sp)
            }
        }
    } else {
        LazyColumn(Modifier.fillMaxHeight()) {
            items(localBooksState.localBooks.size) {
                BookCard(localBooksState.localBooks[it], navController)
            }
        }
    }
}
