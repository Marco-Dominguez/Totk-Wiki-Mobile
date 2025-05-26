package com.example.totkbase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.totkbase.navigation.Screen
import com.exyte.animatednavbar.AnimatedNavigationBar

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Screen.Home,
        Screen.Monstruos,
        Screen.Equipo,
        Screen.Noticias
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedIndex = items.indexOfFirst { it.route == currentRoute }.let {
        if (it < 0) 0 else it
    }

    AnimatedNavigationBar(
        selectedIndex = selectedIndex,
        barColor = MaterialTheme.colorScheme.primary,
        ballColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .height(72.dp)
    ) {
        items.forEach { screen ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = screen.title,
                    tint = if (currentRoute == screen.route)
                        MaterialTheme.colorScheme.surface
                    else
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Preview(
    name = "Bottom Navigation Bar",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 80
)
@Composable
fun BottomNavigationBarPreview() {
    MaterialTheme {
        val mockNavController = rememberNavController()

        BottomNavigationBar(navController = mockNavController)
    }
}