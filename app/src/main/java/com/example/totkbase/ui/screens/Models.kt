package com.example.totkbase.ui.screens

data class CharacterInfo(
    val name: String,
    val description: String,
    val imageUrl: String
)

data class GameInfo(
    val title: String,
    val year: Int,
    val imageUrl: String
)

data class TimelineEvent(
    val year: String,
    val description: String
)

data class ArtworkInfo(
    val imageUrl: String,
    val description: String
)
