package com.example.totkbase.navigation

import com.example.totkbase.R

sealed class Screen(val route: String, val title: String, val icon: Int) {
    object Home : Screen("home", "Home", R.drawable.ic_home)
    object Monstruos : Screen("monstruos", "Monstruos", R.drawable.ic_monster)
    object Equipo : Screen("equipo", "Equipo", R.drawable.ic_sword)
    object Noticias : Screen("noticias", "Noticias", R.drawable.ic_news)
}
