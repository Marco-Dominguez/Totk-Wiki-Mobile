package com.example.totkbase.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.totkbase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailModal(game: GameInfo, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scrollState = rememberScrollState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        scrimColor = Color.Black.copy(alpha = 0.4f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = game.imageUrl,
                    contentDescription = game.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                ),
                                startY = 0f,
                                endY = 400f
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = stringResource(R.string.game_release) + game.year,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.game_about),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val description = when (game.title) {
                "The Legend of Zelda" -> "El juego que comenzó todo. Un mundo abierto en forma de cuadrícula donde la exploración y el descubrimiento fueron las claves de su éxito revolucionario. Link debe recolectar los 8 fragmentos de la Trifuerza de la Sabiduría para derrotar a Ganon y rescatar a la Princesa Zelda."
                "Zelda II: The Adventure of Link" -> "Una desviación de la fórmula original, introduce elementos de RPG y combate lateral. En esta aventura, Link debe encontrar los Palacios de Hyrule y despertar a la Princesa Zelda de un sueño eterno mientras evita que la sangre de Link sea usada para resucitar a Ganon."
                "A Link to the Past" -> "Regreso a las raíces con una visión isométrica mejorada. Esta precuela establece muchos elementos icónicos de la saga como la Master Sword y el paralelismo entre dos mundos. Link debe rescatar a siete doncellas descendientes de los sabios para poder enfrentarse a Ganon."
                "Ocarina of Time" -> "La primera aventura 3D de Link revolucionó los videojuegos con su mundo abierto, sistema de combate con Z-targeting y viajes en el tiempo. Como el Héroe del Tiempo, Link debe viajar entre su niñez y su edad adulta para detener los planes del Rey Gerudo, Ganondorf."
                "Majora's Mask" -> "Una secuela directa de Ocarina of Time con un tono más oscuro. Link tiene solo 3 días para evitar que la Luna caiga sobre Termina, un mundo paralelo a Hyrule. Utilizando máscaras mágicas que transforman a Link, debe liberar a cuatro gigantes para detener al diabólico Skull Kid."
                "Wind Waker" -> "Con un innovador estilo cel-shading, Wind Waker sitúa a Link en un vasto océano después de que Hyrule fuera inundado. Navegando por los mares, Link debe encontrar a su hermana secuestrada y eventualmente enfrentarse a Ganondorf una vez más, con la ayuda de la Pirata Tetra."
                "Twilight Princess" -> "Un regreso a la estética más realista, donde Link puede transformarse en lobo. Cuando el Twilight Realm invade Hyrule, Link debe colaborar con la misteriosa Midna para restaurar la luz y detener al usurpador Zant y a su maestro, Ganondorf."
                "Skyward Sword" -> "Cronológicamente la primera historia en la línea temporal de Zelda. Con controles de movimiento innovadores, narra los orígenes de la Master Sword y la eterna batalla contra el mal. Link debe descender desde su hogar flotante, Skyloft, para rescatar a Zelda y enfrentarse al Demon King Demise."
                "Breath of the Wild" -> "Una reinvención revolucionaria de la serie que introduce un mundo verdaderamente abierto con física y química realistas. Link despierta después de 100 años para encontrar un Hyrule en ruinas, y debe recuperar sus recuerdos mientras se prepara para enfrentar a Calamity Ganon."
                "Link's Awakening (Remake)" -> "Remake del clásico de Game Boy con un encantador estilo visual. Link naufraga en la misteriosa Isla Koholint y debe despertar al Wind Fish para poder escapar, solo para descubrir que la isla y sus habitantes son parte de un sueño que desaparecerá cuando el Wind Fish despierte."
                "Tears of the Kingdom" -> "Secuela directa de Breath of the Wild que expande vertical y horizontalmente el mundo de Hyrule. Link debe explorar las islas flotantes en el cielo y las profundidades bajo tierra mientras enfrenta la amenaza del regresado Demon King Ganondorf y su intención de tomar la Trifuerza."
                else -> "Una de las legendarias aventuras de la saga The Legend of Zelda, donde Link emprende un viaje épico para salvar el reino de Hyrule."
            }

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.game_features),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val features = when (game.title) {
                "The Legend of Zelda" -> listOf("Primer juego de la franquicia", "Mundo abierto no lineal", "Introducción de objetos icónicos como el boomerang y la Master Sword")
                "Ocarina of Time" -> listOf("Primer juego 3D de la saga", "Sistema revolucionario de Z-targeting", "Viajes en el tiempo entre dos épocas", "Ocarina que permite aprender y tocar melodías mágicas")
                "Breath of the Wild" -> listOf("Mundo abierto completamente explorable", "Física y química realistas", "Libertad de aproximación a los retos", "Sistema dinámico de clima", "Cooking y crafting de objetos")
                "Tears of the Kingdom" -> listOf("Habilidad para crear vehículos y máquinas", "Exploración vertical en islas flotantes", "Nuevas habilidades como Ultrahand, Ascend y Recall", "Sistema de fusión de armas", "Mazmorras tradicionales")
                else -> listOf("Estilo artístico único", "Sistema de combate innovador", "Puzzles y mazmorras desafiantes", "Personajes carismáticos", "Historia profunda conectada al legendario universo de Zelda")
            }

            features.forEach { feature ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bullet_point),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = feature,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.game_fun_fact_header),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            val funFact = when (game.title) {
                "The Legend of Zelda" -> "El nombre 'Zelda' se inspiró en Zelda Fitzgerald, esposa del escritor F. Scott Fitzgerald, cuyo nombre fascinó a Shigeru Miyamoto."
                "Ocarina of Time" -> "Originalmente iba a ser jugado completamente en primera persona, pero el equipo cambió a tercera persona para que los jugadores pudieran ver a Link y conectar más con el personaje."
                "Majora's Mask" -> "Fue desarrollado en solo 1 año, un tiempo récord para un juego de Zelda, reutilizando el motor de Ocarina of Time."
                "Wind Waker" -> "Su estilo artístico causó controversia inicial entre los fans, pero ahora es considerado uno de los estilos visuales más atemporales de la saga."
                "Breath of the Wild" -> "El juego fue inspirado parcialmente por el diseño original de The Legend of Zelda de 1986, volviendo a las raíces de exploración libre."
                "Tears of the Kingdom" -> "La habilidad Ultrahand se inspiró en un juego de Nintendo Switch llamado Nintendo Labo, que permite crear construcciones con cartón."
                else -> "Durante el desarrollo, el equipo suele jugar versiones anteriores de Zelda para mantener la esencia y el espíritu de la saga."
            }

            Text(
                text = funFact,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}