package com.example.totkbase.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.totkbase.ui.screens.*

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Monstruos.route) {
            MonstruosScreen(navController)
        }

        composable(Screen.Equipo.route) {
            EquipoScreen(navController)
        }

        composable(Screen.Noticias.route) {
            NoticiasScreen()
        }

        composable(
            route = "monstruoDetail/{monstruoId}",
            arguments = listOf(navArgument("monstruoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val monstruoId = backStackEntry.arguments?.getString("monstruoId")
            MonstruoDetailSheet(
                monstruoId = monstruoId,
                onDismiss = { navController.popBackStack() }
            )
        }

        composable(
            route = "equipoDetail/{equipoId}",
            arguments = listOf(navArgument("equipoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId")
            EquipoDetailSheet(
                equipoId = equipoId,
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}

