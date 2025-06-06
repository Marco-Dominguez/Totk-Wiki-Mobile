package com.example.totkbase.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            route = Screen.MonstruoDetail.route,
            arguments = listOf(navArgument(Screen.MonstruoDetail.ARG_MONSTRUO_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val monstruoId = backStackEntry.arguments?.getString(Screen.MonstruoDetail.ARG_MONSTRUO_ID)
            MonstruoDetailModal(
                monstruoId = monstruoId,
                onDismiss = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.EquipoDetail.route,
            arguments = listOf(navArgument(Screen.EquipoDetail.ARG_EQUIPO_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString(Screen.EquipoDetail.ARG_EQUIPO_ID)
            EquipoDetailModal(
                equipoId = equipoId,
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}

@Preview(
    name = "Navigation Graph Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    device = "id:pixel_6_pro"
)
@Composable
fun NavigationGraphPreview() {
    MaterialTheme {
        val mockNavController = rememberNavController()
        val paddingValues = PaddingValues(bottom = 64.dp)

        NavigationGraph(
            navController = mockNavController,
            paddingValues = paddingValues
        )
    }
}