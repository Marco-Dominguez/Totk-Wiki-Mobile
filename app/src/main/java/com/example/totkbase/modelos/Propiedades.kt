package com.example.totkbase.modelos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("attack")   val ataque: Long    = 0,
    @SerialName("defense")  val defensa: Long   = 0,
    @SerialName("effect")   val efecto: String? = null,
)
