package com.example.totkbase.db

import android.content.Context
import com.example.totkbase.modelos.Equipo
import com.example.totkbase.modelos.Monstruo
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

object Datos {
    private val jsonObject = Json{ignoreUnknownKeys=true}

    fun getMonsters(context : Context?) : List<Monstruo>{
        return try {
            val jsonString = readJsonAsset(context, "monsters.json")
            jsonObject.decodeFromString<List<Monstruo>>(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getEquipment(context : Context?) : Map<String,List<Equipo>>{
        return try{
            val jsonString = readJsonAsset(context, "equipment.json")
            jsonObject.decodeFromString<List<Equipo>>(jsonString)
                .groupBy(keySelector = { it.tipo ?: "Unknown" }, valueTransform = {it})
        } catch(e: Exception){
            emptyMap()
        }
    }

    private fun readJsonAsset(context: Context?, path: String) : String{
        return try {
            val file = context?.assets?.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}