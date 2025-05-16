package com.example.totkbase.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.totkbase.R
import com.example.totkbase.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val historySectionPosition = remember { 400.dp }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Banner Hero con título y botón flotante
            HeroBanner(
                onStartClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(historySectionPosition.value.toInt())
                    }
                }
            )

            // Resumen Histórico
            HistorySection()

            // Carrusel de Juegos por Época
            GamesCarouselSection()

            // Personajes Clave
            CharactersSection()

            // Enlaces Rápidos
            QuickLinksSection(navController)

            // Galería de Arte o Fondos
            ArtGallerySection()

            // Sección de Curiosidades
            TriviaSection()

            // Footer con Créditos y Enlaces
            FooterSection()
        }
    }
}

@Composable
fun HeroBanner(onStartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        // Imagen de fondo (placeholder por ahora)
        AsyncImage(
            model = "file:///android_asset/images/placeholder.svg",
            contentDescription = "Zelda Hero Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay gradiente para mejorar legibilidad del texto
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0f,
                        endY = 900f
                    )
                )
        )

        // Título centrado
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Zelda Encyclopedia",
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        // Botón flotante para comenzar
        FloatingActionButton(
            onClick = onStartClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-16).dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Comenzar",
                tint = Color.White
            )
        }
    }
}

@Composable
fun HistorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "La Leyenda Comienza",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "La saga The Legend of Zelda nació en 1986, creada por Shigeru Miyamoto y Takashi Tezuka para Nintendo Entertainment System. El juego original revolucionó la industria al introducir un mundo abierto con libertad de exploración, estableciendo las bases para un género completamente nuevo.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "A lo largo de más de tres décadas, la serie ha evolucionado constantemente, manteniendo elementos icónicos como la Trifuerza, la princesa Zelda y el héroe Link, mientras introduce innovaciones en cada entrega. De los píxeles de la NES a los vastos mundos 3D de Nintendo Switch, Zelda ha definido la aventura en videojuegos.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Con cada nueva consola de Nintendo, la saga ha presentado experiencias que redefinen lo que un juego de aventuras puede ser, ganando innumerables premios y estableciendo a Zelda como una de las franquicias más influyentes y queridas en la historia de los videojuegos.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun GamesCarouselSection() {
    val consoleEras = listOf(
        "NES/SNES" to listOf(
            GameInfo("The Legend of Zelda", 1986, "file:///android_asset/images/placeholder.svg"),
            GameInfo("Zelda II: The Adventure of Link", 1987, "file:///android_asset/images/placeholder.svg"),
            GameInfo("A Link to the Past", 1991, "file:///android_asset/images/placeholder.svg")
        ),
        "N64/GC" to listOf(
            GameInfo("Ocarina of Time", 1998, "file:///android_asset/images/placeholder.svg"),
            GameInfo("Majora's Mask", 2000, "file:///android_asset/images/placeholder.svg"),
            GameInfo("Wind Waker", 2002, "file:///android_asset/images/placeholder.svg"),
            GameInfo("Twilight Princess", 2006, "file:///android_asset/images/placeholder.svg")
        ),
        "Wii/WiiU" to listOf(
            GameInfo("Skyward Sword", 2011, "file:///android_asset/images/placeholder.svg"),
            GameInfo("Breath of the Wild", 2017, "file:///android_asset/images/placeholder.svg")
        ),
        "Switch" to listOf(
            GameInfo("Link's Awakening (Remake)", 2019, "file:///android_asset/images/placeholder.svg"),
            GameInfo("Tears of the Kingdom", 2023, "file:///android_asset/images/placeholder.svg")
        )
    )

    var selectedGame by remember { mutableStateOf<GameInfo?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Juegos por Época",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        consoleEras.forEach { (console, games) ->
            Text(
                text = console,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(games) { game ->
                    GameCard(
                        game = game,
                        onClick = { selectedGame = game }
                    )
                }
            }
        }
    }

    // Mostrar el modal de detalle del juego
    selectedGame?.let { game ->
        GameDetailModal(
            game = game,
            onDismiss = { selectedGame = null }
        )
    }
}

@Composable
fun GameCard(game: GameInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(220.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = game.imageUrl,
                contentDescription = game.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = game.year.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun CharactersSection() {
    val characters = listOf(
        CharacterInfo("Link", "El héroe elegido por las diosas, portador de la Trifuerza del Valor. A lo largo de las distintas épocas de Hyrule, Link ha renacido para enfrentar el mal y proteger la tierra. Conocido por su valentía y determinación, este guerrero silencioso ha empuñado la Espada Maestra para sellar la oscuridad en innumerables ocasiones.", "file:///android_asset/images/placeholder.svg"),
        CharacterInfo("Zelda", "Princesa de Hyrule y poseedora de la Trifuerza de la Sabiduría. Descendiente de la diosa Hylia, Zelda posee poderes místicos que se manifiestan en momentos de necesidad. Su sabiduría y liderazgo han sido fundamentales para la protección del reino a través de generaciones, trabajando junto a Link para mantener la paz.", "file:///android_asset/images/placeholder.svg"),
        CharacterInfo("Ganondorf", "Rey de los Gerudo y manifestación del mal, portador de la Trifuerza del Poder. Su ambición por dominar Hyrule lo ha llevado a reencarnar a través de los siglos. Conocido también como Ganon en su forma bestial, representa la amenaza constante contra la que Link y Zelda deben unir fuerzas.", "file:///android_asset/images/placeholder.svg"),
        CharacterInfo("Midna", "Princesa del Reino del Crepúsculo y guía de Link en Twilight Princess. Inicialmente burlona y manipuladora, Midna desarrolla un fuerte vínculo con Link durante su aventura. Su historia trágica como gobernante destronada la convierte en uno de los personajes más complejos y queridos de la saga.", "file:///android_asset/images/placeholder.svg"),
        CharacterInfo("Impa", "Guardiana de la familia real y líder de los Sheikah. A través de las distintas eras, Impa ha servido como protectora, consejera y mentora tanto para la princesa Zelda como para Link. Sus conocimientos ancestrales y habilidades ninja han sido cruciales en momentos decisivos para la historia de Hyrule.", "file:///android_asset/images/placeholder.svg")
    )

    var selectedCharacter by remember { mutableStateOf<CharacterInfo?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Personajes Clave",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(characters) { character ->
                CharacterAvatar(
                    character = character,
                    onClick = { selectedCharacter = character }
                )
            }
        }
    }

    // Mostrar el modal de detalle del personaje
    selectedCharacter?.let { character ->
        CharacterDetailModal(
            character = character,
            onDismiss = { selectedCharacter = null }
        )
    }
}

@Composable
fun CharacterAvatar(character: CharacterInfo, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = character.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun QuickLinksSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Explora Más",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickLinkButton(
                title = "Monstruos",
                icon = painterResource(id = R.drawable.ic_monster),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Screen.Monstruos.route) }
            )

            Spacer(modifier = Modifier.width(16.dp))

            QuickLinkButton(
                title = "Equipo",
                icon = painterResource(id = R.drawable.ic_sword),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Screen.Equipo.route) }
            )

            Spacer(modifier = Modifier.width(16.dp))

            QuickLinkButton(
                title = "Noticias",
                icon = painterResource(id = R.drawable.ic_news),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Screen.Noticias.route) }
            )
        }
    }
}

@Composable
fun QuickLinkButton(title: String, icon: Painter, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ElevatedCard(
        modifier = modifier
            .height(100.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun TriviaSection() {
    val triviaItems = listOf(
        "El nombre de Link proviene de su función como 'enlace' entre el jugador y el mundo del juego",
        "The Wind Waker inicialmente fue criticado por su estilo, pero luego fue reconocido como atemporal",
        "Miyamoto se inspiró en sus exploraciones de cuevas y bosques durante su infancia para crear la serie",
        "Breath of the Wild ganó más de 260 premios 'Juego del Año' a nivel mundial"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "¿Sabías que...?",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        triviaItems.forEach { trivia ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = trivia,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun FooterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Zelda Encyclopedia 2023",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Datos obtenidos de fuentes oficiales de Nintendo",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Iconos por Material Design",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Text(
            text = "Desarrollado con Jetpack Compose",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ArtGallerySection() {
    val artworks = List(6) { "file:///android_asset/images/placeholder.svg" }
    var selectedArtIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Galería de Arte",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.height(280.dp)
        ) {
            items(artworks.indices.toList()) { index ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(130.dp)
                        .clickable { selectedArtIndex = index }
                ) {
                    AsyncImage(
                        model = artworks[index],
                        contentDescription = "Arte conceptual ${index + 1}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    // Mostrar el modal de galería cuando se selecciona una imagen
    selectedArtIndex?.let { index ->
        ArtGalleryModal(
            artworks = artworks,
            initialIndex = index,
            onDismiss = { selectedArtIndex = null }
        )
    }
}
