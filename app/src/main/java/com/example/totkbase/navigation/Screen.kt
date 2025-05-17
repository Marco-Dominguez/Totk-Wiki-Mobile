package com.example.totkbase.navigation

import com.example.totkbase.R

sealed class Screen(val route: String, val title: String, val icon: Int) {
    object Home : Screen("home", "Home", R.drawable.home)
    object Monstruos : Screen("monstruos", "Monstruos", R.drawable.monsters)
    object Equipo : Screen("equipo", "Equipo", R.drawable.equipment)
    object Noticias : Screen("noticias", "Noticias", R.drawable.noticias)

    // Rutas con parámetros
    object MonstruoDetail : Screen("monstruoDetail/{monstruoId}", "Detalle Monstruo", 0) {
        fun createRoute(monstruoId: String) = "monstruoDetail/$monstruoId"
        const val ARG_MONSTRUO_ID = "monstruoId"
    }

    object EquipoDetail : Screen("equipoDetail/{equipoId}", "Detalle Equipo", 0) {
        fun createRoute(equipoId: String) = "equipoDetail/$equipoId"
        const val ARG_EQUIPO_ID = "equipoId"
    }
}
