package com.bcit.myapp.ui.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainContent(localBooksState: LocalBooksState) {
    val navController = rememberNavController()
    val booksState: BooksState = viewModel(
        LocalActivity.current as ComponentActivity
    )
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        bottomBar = {
            AppNavBar(navController)
        }
    ) {
        paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "home"
        ) {
            composable(route = "home") {
                Home(localBooksState, navController)
            }
            composable(route = "info/{id}") {
                val id = it.arguments?.getString("id")?.toInt()
                Info(id ?: 0, booksState, localBooksState)
            }
            composable(route = "search") {
                Search(booksState, navController)
            }
        }
    }
}