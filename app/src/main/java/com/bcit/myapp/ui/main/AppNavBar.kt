package com.bcit.myapp.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem (
    val icon: ImageVector,
    val route: String
)

@Composable
fun AppNavBar(navController: NavController) {
    val navItems = listOf(
        NavItem(Icons.Default.Home, "home"),
        NavItem(Icons.Default.Search, "search")
    )

    NavigationBar(
        containerColor = Color.Transparent
    ) {
        val navBackStackEntry by navController
            .currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.
            destination?.
            route

        navItems.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route)
                },
                icon = {
                    Icon(it.icon,
                        contentDescription = it.route)
                }
            )
        }
    }
}
