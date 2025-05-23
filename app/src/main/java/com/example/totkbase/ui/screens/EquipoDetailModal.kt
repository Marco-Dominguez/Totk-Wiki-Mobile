package com.example.totkbase.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.totkbase.R
import com.example.totkbase.db.Datos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipoDetailModal(equipoId: String?, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val id = equipoId?.toLongOrNull()

    val allEquipment = Datos.getEquipment(context).values.flatten()
    val equipo = id?.let { equipoId ->
        allEquipment.find { it.identificador == equipoId }
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scrollState = rememberScrollState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        scrimColor = Color.Black.copy(alpha = 0.4f),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = equipo?.nombre ?: stringResource(R.string.equipment_not_found),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (equipo?.dlc == true) {
                    Icon(
                        painter = painterResource(id = R.drawable.dlc_item),
                        contentDescription = stringResource(R.string.dlc),
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(24.dp)
                    )
                }
            }

            equipo?.let { item ->
                AsyncImage(
                    model = item.imagen,
                    contentDescription = item.nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.equipment_type_prefix) + item.tipo,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = stringResource(R.string.equipment_category_prefix) + item.categoria,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.equipment_stats),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.atk_dmg),
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.equipment_attack, item.propiedades.ataque),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.defense),
                                contentDescription = null,
                                tint = Color.Blue,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.equipment_defense, item.propiedades.defensa),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        if (item.propiedades.efecto != null && item.propiedades.efecto.isNotEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.effect),
                                    contentDescription = null,
                                    tint = Color.Green,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.equipment_effect, item.propiedades.efecto),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        if (item.tipo != null && item.tipo.isNotEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                if (item.tipo == stringResource(R.string.one_handed_weapon) || item.tipo == stringResource(R.string.spear)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.one_handed_weapon),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else if (item.tipo == stringResource(R.string.two_handed_weapon)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.two_handed_weapon),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else if (item.tipo == stringResource(R.string.bow)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.bow),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                else if (item.tipo == stringResource(R.string.shield)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.shield),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else if (item.tipo == stringResource(R.string.arrow)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.equipment_weapon_type, item.tipo),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(R.string.equipment_description_label),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = item.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Localizaciones con icono de pin
                if (!item.localizacionesComunes.isNullOrEmpty()) {
                    Text(
                        text = stringResource(R.string.equipment_common_locations),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    item.localizacionesComunes.forEach { location ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.pink_pin),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = location,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
