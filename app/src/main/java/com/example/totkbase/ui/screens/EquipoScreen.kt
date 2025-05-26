package com.example.totkbase.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.swipeactions.SwipeActionsRight
import com.example.swipeactions.Type
import com.example.totkbase.R
import com.example.totkbase.db.Datos
import com.example.totkbase.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EquipoScreen(navController: NavHostController) {
    val context = LocalContext.current
    val equipmentByType = remember { mutableStateOf(Datos.getEquipment(context)) }
    val expandedStates = remember { mutableStateMapOf<String, Boolean>() }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            equipmentByType.value.forEach { (tipo, equipos) ->
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Text(
                            text = tipo,
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .align(Alignment.BottomCenter)
                                .background(
                                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.0f)
                                        )
                                    )
                                )
                        )
                    }
                }

                items(equipos) { equipo ->
                    val isExpanded = expandedStates[equipo.identificador.toString()] ?: false

                    SwipeActionsRight(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                            .height(102.dp),
                        isExpanded = isExpanded,
                        onChangedCard = {
                            expandedStates[equipo.identificador.toString()] = it
                        },
                        actionOneImage = Icons.Default.Clear,
                        actionOneColor = Color.White,
                        actionOneBackColor = Color(0xFFFF3F3F),
                        actionOneText = "Desmarccar",
                        actionTwoImage = Icons.Default.Check,
                        actionTwoColor = Color.White,
                        actionTwoBackColor = Color(0xFF4CAF50),
                        actionTwoText = "Marcar",
                        type = Type.Icon,
                        cornerRadius = 12.dp,
                        actionOneClicked = {
                            val updatedEquipo = equipo.copy(discovered = false)
                            Datos.updateEquipment(context, updatedEquipo)
                            expandedStates[equipo.identificador.toString()] = false
                            equipmentByType.value = Datos.getEquipment(context)
                        },
                        actionTwoClicked = {
                            val updatedEquipo = equipo.copy(discovered = true)
                            Datos.updateEquipment(context, updatedEquipo)
                            expandedStates[equipo.identificador.toString()] = false
                            equipmentByType.value = Datos.getEquipment(context)
                        }
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Screen.EquipoDetail.createRoute(equipo.identificador.toString()))
                                }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (!equipo.discovered) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.bullet_point),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(16.dp)
                                            .align(Alignment.CenterEnd)
                                            .offset((-8).dp, (-4).dp),
                                        tint = Color(0xFFFF8C00)
                                    )
                                }

                                if (equipo.dlc) {
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

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = equipo.imagen,
                                        contentDescription = equipo.nombre,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = equipo.nombre,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = stringResource(R.string.equipment_category_prefix) + equipo.categoria,
                                            style = MaterialTheme.typography.bodyMedium
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = equipo.descripcion,
                                            style = MaterialTheme.typography.bodySmall,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
