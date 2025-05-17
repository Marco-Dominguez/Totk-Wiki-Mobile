package com.example.totkbase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailModal(character: CharacterInfo, onDismiss: () -> Unit) {
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
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de encabezado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)  // Aumentado de 200dp a 250dp para hacer la imagen más alta
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = "Imagen de ${character.name}",
                    contentScale = ContentScale.FillWidth,

                    modifier = Modifier.fillMaxWidth(),
                    alignment = Alignment.TopCenter

                )

                // Gradiente para mejorar la legibilidad del texto
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)  // Aumentado de 200dp a 250dp para coincidir con el contenedor
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

                // Nombre del personaje sobre la imagen
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Detalles del personaje
            Text(
                text = "Historia",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = character.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Información adicional ficticia para enriquecer el modal
            Text(
                text = "Apariciones destacadas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            val appearances = when (character.name) {
                "Link" -> listOf("The Legend of Zelda", "Ocarina of Time", "Breath of the Wild", "Tears of the Kingdom")
                "Zelda" -> listOf("The Legend of Zelda", "Ocarina of Time", "Wind Waker", "Tears of the Kingdom")
                "Ganondorf" -> listOf("Ocarina of Time", "Wind Waker", "Twilight Princess", "Tears of the Kingdom")
                "Paya" -> listOf("Breath of the Wild", "Tears of the Kingdom")
                "Impa" -> listOf("Ocarina of Time", "Skyward Sword", "Breath of the Wild", "Tears of the Kingdom")
                "Purah" -> listOf("Breath of the Wild", "Tears of the Kingdom")
                else -> listOf()
            }

            appearances.forEach { game ->
                Text(
                    text = "• $game",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 2.dp)
                )
            }

            // Espacio adicional al final
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

