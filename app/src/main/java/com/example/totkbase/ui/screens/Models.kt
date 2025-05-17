package com.example.totkbase.ui.screens


data class GameInfo(
    val title: String,
    val year: Int,
    val imageUrl: String,
    val description: String = "Juego de la saga The Legend of Zelda"
)


data class CharacterInfo(
    val name: String,
    val description: String,
    val imageUrl: String
)

data class ArtworkInfo(
    val imageUrl: String,
    val description: String
)
