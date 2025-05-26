package com.example.totkbase.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.res.stringResource
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
import com.example.totkbase.ui.components.BodyText
import com.example.totkbase.ui.components.DescriptionText
import com.example.totkbase.ui.components.SmallText
import com.example.totkbase.ui.components.TitleText
import com.example.totkbase.ui.theme.HyliaSerifFamily
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val historySectionPosition = remember { 900.dp }

    val showStickyHeader = scrollState.value > 700

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            HeroBanner(
                onStartClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(historySectionPosition.value.toInt())
                    }
                }
            )

            HistorySection()

            CharactersSection()

            QuickLinksSection(navController)

            ArtGallerySection()

            GamesCarouselSection()

            TriviaSection()

            FooterSection()
        }

        AnimatedVisibility(
            visible = showStickyHeader,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 4.dp
            ) {
                Text(
                    text = stringResource(id = R.string.hero_banner_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = HyliaSerifFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
fun HeroBanner(onStartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
    ) {
        AsyncImage(
            model = "https://static1.thegamerimages.com/wordpress/wp-content/uploads/2022/09/Fci4XyPXoAAfLyV.jpg",
            contentDescription = stringResource(id = R.string.hero_banner_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

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

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.hero_banner_title),
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = HyliaSerifFamily
            )
        }

        FloatingActionButton(
            onClick = onStartClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-16).dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = stringResource(id = R.string.hero_banner_button_content_description),
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
        BodyText(
            text = stringResource(id = R.string.home_history_description),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun GamesCarouselSection() {
    val consoleEras = listOf(
        "NES/SNES" to listOf(
            GameInfo("The Legend of Zelda", 1986, "https://upload.wikimedia.org/wikipedia/en/4/41/Legend_of_zelda_cover_%28with_cartridge%29_gold.png"),
            GameInfo("Zelda II: The Adventure of Link", 1987, "https://assets-prd.ignimgs.com/2022/01/23/zelda2aol-sq-1642982095651.jpg"),
            GameInfo("A Link to the Past", 1991, "https://m.media-amazon.com/images/I/71wfmH16Z8L.jpg")
        ),
        "N64/GC" to listOf(
            GameInfo("Ocarina of Time", 1998, "https://upload.wikimedia.org/wikipedia/en/5/57/The_Legend_of_Zelda_Ocarina_of_Time.jpg"),
            GameInfo("Majora's Mask", 2000, "https://upload.wikimedia.org/wikipedia/en/6/60/The_Legend_of_Zelda_-_Majora%27s_Mask_Box_Art.jpg"),
            GameInfo("Wind Waker", 2002, "https://i.ytimg.com/vi/-4vNnk3gWPA/hqdefault.jpg"),
            GameInfo("Twilight Princess", 2006, "https://preview.redd.it/14l51q71ym961.png?auto=webp&s=d5b9f0440f53b8fd638dada6a919bb2fdad3e150")
        ),
        "Wii/WiiU" to listOf(
            GameInfo("Skyward Sword", 2011, "https://howlongtobeat.com/games/10042_The_Legend_of_Zelda_Skyward_Sword.jpg"),
            GameInfo("Breath of the Wild", 2017, "https://assets.nintendo.com/image/upload/q_auto/f_auto/ncom/software/switch/70010000000025/7137262b5a64d921e193653f8aa0b722925abc5680380ca0e18a5cfd91697f58")
        ),
        "Switch" to listOf(
            GameInfo("Link's Awakening (Remake)", 2019, "https://assets.nintendo.com/image/upload/q_auto/f_auto/ncom/software/switch/70010000020033/df825d5fc6747b66b051f5d3ba6a412fe625ea7878b6a10c93bb8312195682b1"),
            GameInfo("Tears of the Kingdom", 2023, "https://locosxlosjuegos.com/wp-content/uploads/2023/07/tokt1.jpg")
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
        CharacterInfo("Link", "El héroe elegido por las diosas, portador de la Trifuerza del Valor. A lo largo de las distintas épocas de Hyrule, Link ha renacido para enfrentar el mal y proteger la tierra. Conocido por su valentía y determinación, este guerrero silencioso ha empuñado la Espada Maestra para sellar la oscuridad en innumerables ocasiones.", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/the-legend-of-zelda-breath-of-the-wild-2/3/38/Link2.png"),
        CharacterInfo("Zelda", "Princesa de Hyrule y poseedora de la Trifuerza de la Sabiduría. Descendiente de la diosa Hylia, Zelda posee poderes místicos que se manifiestan en momentos de necesidad. Su sabiduría y liderazgo han sido fundamentales para la protección del reino a través de generaciones, trabajando junto a Link para mantener la paz.", "https://i.blogs.es/5269c3/zelda-zelda-tears-of-the-kingdom/1366_2000.jpeg"),
        CharacterInfo("Ganondorf", "Rey de los Gerudo y manifestación del mal, portador de la Trifuerza del Poder. Su ambición por dominar Hyrule lo ha llevado a reencarnar a través de los siglos. Conocido también como Ganon en su forma bestial, representa la amenaza constante contra la que Link y Zelda deben unir fuerzas.", "https://static.wikia.nocookie.net/zelda/images/b/b2/Ganondorf_The_Legend_of_Zelda_Tears_of_The_Kingdom_Perfil.jpg/revision/latest?cb=20230414230715&path-prefix=es"),
        CharacterInfo("Paya", "Paya (Apaya en Hispanoamérica; Paya en inglés), es un personaje de The Legend of Zelda: Breath of the Wild. Es una Sheikah y puede ser encontrada en Kakariko, en la región de Necluda occidental. Es la nieta de Impa.", "https://noko-box.b-cdn.net/wp-content/uploads/2023/04/Paya_TOTK.jpg"),
        CharacterInfo("Impa", "Guardiana de la familia real y líder de los Sheikah. A través de las distintas eras, Impa ha servido como protectora, consejera y mentora tanto para la princesa Zelda como para Link. Sus conocimientos ancestrales y habilidades ninja han sido cruciales en momentos decisivos para la historia de Hyrule.", "https://pbs.twimg.com/media/FzJEcimaUAApG8_?format=jpg&name=large"),
        CharacterInfo("Purah", "Purah es un personaje de la serie The Legend of Zelda de Nintendo. Apareció originalmente en The Legend of Zelda: Breath of the Wild, donde a pesar de ser anciana aparece como una niña debido a un percance científico.", "https://noko-box.b-cdn.net/wp-content/uploads/2023/04/Purah_TOTK.jpg")
    )

    var selectedCharacter by remember { mutableStateOf<CharacterInfo?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_characters_section),
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
            contentDescription = stringResource(id = R.string.character_image_description, character.name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
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
            text = stringResource(id = R.string.home_explore_more),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickLinkButton(
                title = stringResource(id = R.string.nav_monsters),
                icon = painterResource(id = R.drawable.monsters),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Screen.Monstruos.route) }
            )

            Spacer(modifier = Modifier.width(16.dp))

            QuickLinkButton(
                title = stringResource(id = R.string.nav_equipment),
                icon = painterResource(id = R.drawable.equipment),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Screen.Equipo.route) }
            )

            Spacer(modifier = Modifier.width(16.dp))

            QuickLinkButton(
                title = stringResource(id = R.string.nav_news),
                icon = painterResource(id = R.drawable.noticias),
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
fun ArtGallerySection() {
    val artworks = listOf(
        ArtworkInfo(
            imageUrl = "https://images.nintendolife.com/67998e3c3f5ba/zelda-tears-of-the-kingdom.large.jpg",
            description = "Link"
        ),
        ArtworkInfo(
            imageUrl = "https://cloudfront-eu-central-1.images.arcpublishing.com/diarioas/LR65MOYRQVH6DMMYSIJPHWDJLA.jpg",
            description = "Makeela Riju"
        ),
        ArtworkInfo(
            imageUrl = "https://cloudfront-eu-central-1.images.arcpublishing.com/diarioas/F24LCQEFKZHN5DSIV7PA7K72UA.jpg",
            description = "Tulin"
        ),
        ArtworkInfo(
            imageUrl = "https://nintendoeverything.com/wp-content/uploads/Zelda-Tears-of-the-Kingdom-art-book-1.jpg",
            description = "Link - Arte conceptual"
        ),
        ArtworkInfo(
            imageUrl = "https://cdn.mall.adeptmind.ai/https%3A%2F%2Fmedia.gamestop.com%2Fi%2Fgamestop%2F20005393%2FThe-Legend-of-Zelda-Tears-of-the-Kingdom-Link-with-Sword-24-in-x-36-in-Rolled-Poster_large.webp",
            description = "Link"
        ),
        ArtworkInfo(
            imageUrl = "https://cloudfront-eu-central-1.images.arcpublishing.com/diarioas/ZX4HLOUJV5DOJCF2TCHFA23MDU.jpg",
            description = "Link"
        ),
        ArtworkInfo(
            imageUrl = "https://cl2.buscafs.com/www.levelup.com/public/uploads/images/850340/850340.jpg",
            description = "Zelda - Arte conceptual"
        ),
        ArtworkInfo(
            imageUrl = "https://i.etsystatic.com/11277520/r/il/5f9f83/4209642661/il_570xN.4209642661_t390.jpg",
            description = "Banner TOTK"
        )
    )

    var selectedArtworkIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_art_gallery),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(300.dp)
            ) {
                items(artworks) { artwork ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .clickable { selectedArtworkIndex = artworks.indexOf(artwork) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = artwork.imageUrl,
                                contentDescription = artwork.description,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp)
                                    .align(Alignment.BottomCenter)
                                    .background(Color.Black.copy(alpha = 0.6f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = artwork.description,
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                                MaterialTheme.colorScheme.background
                            ),
                            startY = 0f,
                            endY = 100f
                        )
                    )
            )
        }
    }

    selectedArtworkIndex?.let { index ->
        ArtGalleryModal(
            artworks = artworks,
            initialIndex = index,
            onDismiss = { selectedArtworkIndex = null }
        )
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
        TitleText(
            text = stringResource(id = R.string.home_did_you_know),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        triviaItems.forEach { trivia ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                BodyText(
                    text = trivia,
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
            text = stringResource(id = R.string.home_footer),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.home_credits),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

