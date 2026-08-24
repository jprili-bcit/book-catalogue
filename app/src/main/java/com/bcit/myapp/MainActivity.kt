package com.bcit.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bcit.myapp.data.BooksRepository
import com.bcit.myapp.data.LocalBooksRepository
import com.bcit.myapp.data.LocalBookDatabase
import com.bcit.myapp.data.client
import com.bcit.myapp.ui.main.BooksState
import com.bcit.myapp.ui.main.LocalBooksState
import com.bcit.myapp.ui.main.MainContent

class MainActivity : ComponentActivity() {

    private val db by lazy {
        LocalBookDatabase.getDatabase(applicationContext)
    }
    private val localBooksRepository by lazy {
        LocalBooksRepository(db.localBookDao())
    }

    private val booksRepository by lazy {
        BooksRepository(client)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val localBooksState = remember {
                LocalBooksState(localBooksRepository)
            }

            viewModel {
                BooksState(booksRepository)
            }

            MainContent(localBooksState)
        }
    }
}
