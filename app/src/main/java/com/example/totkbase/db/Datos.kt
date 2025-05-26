package com.example.totkbase.db

import android.content.Context
import com.example.totkbase.modelos.Equipo
import com.example.totkbase.modelos.Monstruo
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

object Datos {
    private val jsonObject = Json{ignoreUnknownKeys=true}

    fun getMonsters(context: Context?): List<Monstruo> {
        return try {
            val jsonString = readJsonAsset(context, "monsters.json")
            val monsters = jsonObject.decodeFromString<List<Monstruo>>(jsonString)

            if (context != null) {
                val sharedPreferences = context.getSharedPreferences("monsters_data", Context.MODE_PRIVATE)

                monsters.map { monster ->
                    val savedDiscovered = sharedPreferences.getBoolean(
                        "monster_${monster.identificador}_discovered",
                        monster.discovered
                    )
                    monster.copy(discovered = savedDiscovered)
                }
            } else {
                monsters
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getEquipment(context: Context?): Map<String, List<Equipo>> {
        return try {
            val jsonString = readJsonAsset(context, "equipment.json")
            val equipment = jsonObject.decodeFromString<List<Equipo>>(jsonString)

            val updatedEquipment = if (context != null) {
                val sharedPreferences = context.getSharedPreferences("equipment_data", Context.MODE_PRIVATE)

                equipment.map { equipo ->
                    val savedDiscovered = sharedPreferences.getBoolean(
                        "equipment_${equipo.identificador}_discovered",
                        equipo.discovered
                    )
                    equipo.copy(discovered = savedDiscovered)
                }
            } else {
                equipment
            }

            updatedEquipment.groupBy(
                keySelector = { it.tipo ?: "Unknown" },
                valueTransform = { it }
            )
        } catch (e: Exception) {
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

    fun updateMonster(context: Context?, monster: Monstruo) {
        if (context == null) return

        val sharedPreferences = context.getSharedPreferences("monsters_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("monster_${monster.identificador}_discovered", monster.discovered)

        editor.apply()
    }

    fun updateEquipment(context: Context?, equipo: Equipo) {
        if (context == null) return

        val sharedPreferences = context.getSharedPreferences("equipment_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("equipment_${equipo.identificador}_discovered", equipo.discovered)

        editor.apply()
    }
}