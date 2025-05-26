package com.example.totkbase.modelos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Equipo(
    @SerialName("category")         val categoria: String,
    @SerialName("common_locations") val localizacionesComunes: ArrayList<String>?   = arrayListOf(),
    @SerialName("description")      val descripcion: String,
    @SerialName("dlc")              val dlc: Boolean,
    @SerialName("id")               val identificador: Long,
    @SerialName("image")            val imagen: String,
    @SerialName("name")             val nombre: String,
    @SerialName("type")             val tipo: String,
    @SerialName("properties")       val propiedades: Properties,
    var discovered: Boolean = false
)

