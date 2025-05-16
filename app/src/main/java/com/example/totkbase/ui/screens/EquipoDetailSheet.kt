package com.example.totkbase.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.totkbase.db.Datos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipoDetailSheet(equipoId: String?, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val id = equipoId?.toLongOrNull()

    // Obtener todos los equipos y encontrar el que coincida con el ID
    val allEquipment = Datos.getEquipment(context).values.flatten()
    val equipo = id?.let { equipoId ->
        allEquipment.find { it.identificador == equipoId }
    }

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = equipo?.nombre ?: "Equipo no encontrado",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            equipo?.let {
                Text(
                    text = "Tipo: ${it.tipo}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Categoría: ${it.categoria}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Descripción:",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = it.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (!it.localizacionesComunes.isNullOrEmpty()) {
                    Text(
                        text = "Localizaciones comunes:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    it.localizacionesComunes.forEach { location ->
                        Text(
                            text = "• $location",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
