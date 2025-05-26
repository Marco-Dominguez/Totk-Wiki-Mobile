package com.example.totkbase.modelos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Monstruo (
    @SerialName("category")         val categoria: String,
    @SerialName("common_locations") val localizacionesComunes: ArrayList<String>?   = arrayListOf(),
    @SerialName("description")      val descripcion: String,
    @SerialName("dlc")              val dlc: Boolean,
    @SerialName("drops")            val proporciona: ArrayList<String>?   = arrayListOf(),
    @SerialName("id")               val identificador: Long,
    @SerialName("image")            val imagen: String,
    @SerialName("name")             val nombre: String,
    var discovered: Boolean = false
)

