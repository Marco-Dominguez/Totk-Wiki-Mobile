package com.example.totkbase.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.totkbase.R
import com.example.totkbase.ui.components.TitleText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class NoticiaItem(
    val id: Int,
    val titulo: String,
    val fechaPublicacion: Date,
    val resumen: String,
    val contenido: String,
    val imagenUrl: String,
    val fuente: String,
    val tipo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticiasScreen() {
    val noticiasList = remember {
        listOf(
            NoticiaItem(
                id = 1,
                titulo = "Adiós a la damisela en apuros: La princesa Zelda, al rescate del guerrero",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("10:26 06/10/2024"),
                resumen = "‘Echoes of Wisdom’ sublima la ‘cara B’ de los juegos de la saga con una propuesta tan imaginativa como certera en la que por fin controlamos a la princesa que da nombre a la saga",
                contenido = "Decir que la saga de The Legend of Zelda es una de las que mejor salud tiene dentro de los videojuegos es algo que va de suyo. Pero también es de justicia señalar que es una de las que mejores frutos ha dado en los últimos años y que sus juegos han sido los más influyentes de la última década: si Dark Souls monopolizó el imaginario colectivo de 2011 a 2021, podemos decir que Breath of the Wild y Tears of the Kingdom hacen (y harán) lo propio en el período 2017-2027. Y, bueno, eso sin contar que el Tears es quizá el mejor videojuego jamás creado; mecánicamente revolucionario, profundo a diferentes niveles y (y esto no es algo menor) accesible para un público masivo. The legend of Zelda no es solo una colección de grandes juegos, sino también un perfecto embajador de los videojuegos por el mundo y un catálogo de lo que el medio pude ofrecer en cuestiones de interactividad.\n" +
                        "\n" +
                        "Y, sin embargo, la saga tiene una cara B. Paralelo a las obras mayores, generalmente asociadas a consolas de sobremesa (Ocarina of Time, Wind Waker, Skyward Sword, Breath of the Wild…), Nintendo siempre ha reservado un espacio para propuestas más contenidas en lo visual (y más baratas) y en las que el objetivo no era crear un acontecimiento mundial sino ampliar las fronteras del universo Zelda por otros márgenes. Generalmente, en dispositivos portátiles (Phantom Hourglass, Minish cap, Spirit Tracks, Links awakening...), y, generalmente, con una estética más cartoon que alejaba a esos juegos de toda gravedad narrativa y los acercaba a un público más infantil. Echoes of the Wisdom, a la venta desde el pasado día 26 de septiembre, es la sublimación de esa cara B de la saga. Bien por Nintendo, que ya el año pasado sublimó la Cara B de Mario con Wonder.\n" +
                        "\n" +
                        "Habrá quien discuta la forma en la que Nintendo ha optado por encarar una cita ineludible (un juego en el que el jugador controle por fin a la princesa Zelda y no a Link) a través de esta cara B y no a través de una superprodución, pero se comprende en parte: ya es bastante revolucionario que Nintendo (que no deja de ser una empresa japonesa) ceda el protagonismo de su saga estrella a una mujer, y en vista de los recientes descalabros de películas o juegos que apuestan por la inclusividad, y de que la industria pasa por un momento peliagudo, pensarán que quizá no es el mejor momento para ponerse revolucionario.",
                imagenUrl = "https://imagenes.elpais.com/resizer/v2/7IIYV4BLABDD3ISNFDB3A3FLJQ.jpg?auth=6d9d4700b49531ec163f87f55172ffc3e19067dba3d10c39685c25920910290c&width=980",
                fuente = "Jorge Morla - EL PAÍS",
                tipo = "Juegos"
            ),
            NoticiaItem(
                id = 2,
                titulo = "El próximo videojuego de la saga de Zelda será protagonizado por primera vez por la princesa que le da nombre",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("19:56 19/6/2024"),
                resumen = "Nintendo anuncia que ‘Echoes of Wisdom’, previsto para septiembre, intentará “romper las convenciones” de la legendaria serie tanto en la trama, que suele liderar el héroe masculino Link, como en las dinámicas de la obra",
                contenido = "Durante siglos las princesas esperaron. Primero, a que algún señor las liberara. Y, luego, a que alguien se dignara a escribir también historias distintas, donde no solo les tocara aguardar la salvación encerradas en una torre. La joven Zelda, para más paradoja, ha encabezado durante décadas el título de una de las sagas de videojuegos más famosas y aplaudidas. El protagonismo, sin embargo, siempre fue para Link, el muchacho que debía rescatarla. Hasta ahora. Casi 40 años después del lanzamiento del primer The Legend of Zelda, allá por 1986, por fin los jugadores controlarán al personaje de la princesa en Echoes of Wisdom, la nueva entrega de la serie, que saldrá a la venta el próximo 26 de septiembre para la consola Switch, tal y como anunció Nintendo, la compañía responsable de la obra.\n" +
                        "\n “Esta vez Link ha desaparecido. Ahora le toca a la princesa Zelda asumir el rol de protagonista. Puede que os estéis preguntando: ¿entonces Zelda combatirá con una espada? Queríamos crear un nuevo modelo de juego que rompiera las convenciones vistas en viejos títulos la la serie”, ha asegurado Eiji Aonuma, productor del videojuego, en un vídeo difundido por Nintendo. En su arranque, el pequeño avance publicado por la compañía muestra cómo Link salva por enésima vez a la princesa, justo antes de volatilizarse en una especie de portal que se abre bajo sus pies. Junto con Tri, una pequeña hada, Zelda se lanzará a encontrarle. “Salva el reino de Hyrule, en esta ocasión con la ayuda de la sabiduría de la princesa Zelda. Los habitantes de Hyrule se están desvaneciendo poco a poco a causa de extrañas brechas que han surgido de repente y, entre los desaparecidos, se encuentra cierto conocido espadachín. Ahora está en manos de la princesa Zelda salvar su reino”, apunta la sinopsis del juego en la web de Nintendo.",
                imagenUrl = "https://imagenes.elpais.com/resizer/v2/QYD3EFZE6FDXFBLUKHNCCHLFQU.png?auth=20c1ebbd0540bc4fb37278c512ec8a32db4bba777047ad174e27b0395ef5d08e&width=1200",
                fuente = "EL PAÍS",
                tipo = "Juegos"
            ),
            NoticiaItem(
                id = 3,
                titulo = "Nintendo anunció una película live-action de ‘The Legend of Zelda’",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("19:55 08/11/2023"),
                resumen = "El anuncio se produce después del inusitado éxito de The Super Mario Bros. Movie, que no solo marcó un hito en la taquilla, sino que también ayudó a disparar las ventas vinculadas a esta franquicia.",
                contenido = "La desarrolladora y distribuidora japonesa de videojuegos Nintendo anunció este miércoles que se encuentra desarrollando una película live-action de la franquicia The Legend of Zelda.\n" +
                        "\n" +
                        "La película estará producida por el creador de ‘Super Mario’, Shigeru Miyamoto, y por Avi Arad, productor de cintas como Spider-Man: Into the Spider-Verse, según detalló la empresa con sede en Kioto en un comunicado, donde no adelantó una posible fecha de estreno.\n" +
                        "\n" +
                        "La película estará cofinanciada por Nintendo y Sony, aunque la primera estará a cargo de más del 50 % de la financiación, mientras que la distribución la llevara a cabo Sony, recogió dicho texto.\n" +
                        "\n" +
                        "\"Al involucrarse profundamente en la producción cinematográfica con el objetivo de hacer sonreír a todos a través del entretenimiento, Nintendo continuará sus esfuerzos para producir entretenimiento único y ofrecérselo a la mayor cantidad de personas posible\", dijo la empresa en dicho comunicado.",
                imagenUrl = "https://f.rpp-noticias.io/2023/11/08/584958_1499761.jpg?imgdimension=look",
                fuente = "Redacción - RPP",
                tipo = "General"
            ),
            NoticiaItem(
                id = 4,
                titulo = "Las vidrieras de la Nintendo Store de Celestial Park muestran a Link, Zelda y Ganondorf",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("10:26 14/5/2025"),
                resumen = "Universal Studios es la encargada de dar cobijo a las secciones de Super Nintendo World, entre las cuales se cuentan la de Super Mario y Donkey Kong, [...]",
                contenido = "Universal Studios es la encargada de dar cobijo a las secciones de Super Nintendo World, entre las cuales se cuentan la de Super Mario y Donkey Kong, por el momento. Sin embargo, las primeras pinceladas de The Legend of Zelda se vieron en el parque temático de Orlando hace un tiempo, pero ahora la cosa va más allá.\n" +
                        "\n" +
                        "La Nintendo Store de Celestial Park de Orlando, siendo uno de los lugares regentados por Universal Studios, nos muestra su bóveda para ilustrar no solo unas constelaciones basadas en personajes del Reino Champiñón, sino también algo inaudito hasta ahora, puesto que aún carece de sección propia: The Legend of Zelda. En las vidrieras se puede apreciar a Link, el Héroe del Crepúsculo, a la princesa del reino: Zelda, y al villano de aquella época: Ganondorf. \n" + "Los personajes representados en las vidrieras están basados en su versión de Twilight Princess. Como indica la publicación, por algún motivo, la ilustración de Link está invertida, haciendo que sea diestro, en lugar de zurdo, como es canónicamente en Twilight Princess. Como dato adicional, el suelo del sitio está protagonizado por motivos que recuerdan al escudo hyliano.\n" +
                        "\n" +
                        "A falta de su propia sección en el parque temático, el elenco protagonista de The Legend of Zelda tendrá que esperar hasta que Nintendo anuncie oficialmente la apertura del mismo. Para entonces, estaremos aquí para informar.",
                imagenUrl = "https://universozelda.com/wp-content/uploads/2025/05/Vidrieras.jpg",
                fuente = "Universo Zelda",
                tipo = "General"
            ),
            NoticiaItem(
                id = 5,
                titulo = "Nintendo muestra más metraje de The Wind Waker en Nintendo Switch 2",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("10:26 11/5/2025"),
                resumen = "Como ya es sabido por muchos, la versión original de The Wind Waker llegará al servicio de suscripción Nintendo Switch Online + Paquete de expansión [...] ",
                contenido = "Como ya es sabido por muchos, la versión original de The Wind Waker llegará al servicio de suscripción Nintendo Switch Online + Paquete de expansión el mismo día que se lanza la consola: el 5 de junio, y dicha entrega contará con resolución full HD, algo nunca antes visto en la edición de lanzamiento para GameCube.\n" +
                        "\n" +
                        "Nintendo tiene la intención de dejarnos ver un poco más cómo se verá cuando lo juguemos, así que ha preparado un vídeo breve en su app de Nintendo Today! donde ilustra algunas escenas más. Gracias a la cuenta bot TodayNews (que exporta el contenido de la app a Twitter/X) podemos ver el juego en movimiento.\n" + "Queremos recordar que, aunque dicho juego llegue a la sucesora de Nintendo Switch, la propia compañía ha dicho que The Wind Waker HD sigue siendo una opción viable para Nintendo Switch 2, por lo que no deis nada por descartado todavía. El viento arrastra rumores y podría traer nuevas inesperadas en cualquier momento…",
                imagenUrl = "https://universozelda.com/wp-content/uploads/2024/12/The-Wind-Waker-logo-3.jpg",
                fuente = "Universo Zelda",
                tipo = "Juegos"
            ),
            NoticiaItem(
                id = 6,
                titulo = "Nuevas deportivas inspiradas por The Legend of Zelda de Bull Airs",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("10:26 07/4/2025"),
                resumen = "La marca de zapatillas deportivas Bull Airs presenta su nueva colección inspirada en The Legend of Zelda, a la cual ha llamado \"Kingdom Collection\". [...] ",
                contenido = "La marca de zapatillas deportivas Bull Airs presenta su nueva colección inspirada en The Legend of Zelda, a la cual ha llamado “Kingdom Collection“.\n" +
                        "\n" +
                        "Hay que destacar que no están afiliados con Nintendo ni con The Legend of Zelda, por lo que no se trata de un producto oficial ni una colaboración por parte de ambas empresas, sino que Bull Airs se ha tomado la libertad creativa de gestar dos tipos de deportivas que recuerdan ampliamente al azulado Breath of the Wild y a los antiguos títulos verdes de The Legend of Zelda:\n" + "Se trata de dos pares de deportivas que han sido diseñadas usando como base unas zapatillas Nike SB Dunk y que luego han sido modificadas a mano para asemejarse a las ropas verde del héroe de los antiguos juegos de The Legend of Zelda, que estaba compuesta por telas boscosas, combinadas con los marrones de sus botas y el amarillo del chaleco de cadenas, o también adaptadas a la túnica del elegido, que además de ser azul, mezcla la estética de la tecnología ancestral que usaron los sheikah en el pasado de hace 10.000 años de Breath of the Wild.\n" +
                        "\n" +
                        "Sea cual sea vuestra elección, ¡también podéis calzar una en cada pie para representar a toda la serie! Ya están a la venta y podéis encontrar aquí las zapatillas, sumadas de otros artículos como chaquetas o camisetas, también inspiradas en The Legend of Zelda.",
                imagenUrl = "https://universozelda.com/wp-content/uploads/2025/04/Kingdom-Collection-deportivas-zapatillas-Zelda-Bull-Airs-1.jpg",
                fuente = "Universo Zelda",
                tipo = "General"
            ),
            NoticiaItem(
                id = 7,
                titulo = "He aquí el peso del paquete de mejora de Breath of the Wild y Tears of the Kingdom para Nintendo Switch 2",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("10:26 14/5/2025"),
                resumen = "Nintendo Switch 2 está a la vuelta de la esquina, y gracias a su retrocompatibilidad, igual te hará falta saber el espacio de memoria que necesitarás [...] ",
                contenido = "Nintendo Switch 2 está a la vuelta de la esquina, y gracias a su retrocompatibilidad, igual te hará falta saber el espacio de memoria que necesitarás para poner a punto tus juegos. Por ello, y gracias a la página web de venta oficial de Breath of the Wild y Tears of the Kingdom, ya sabemos cuánto pesa el paquete de mejora de Nintendo Switch 2 de cada título:\n" +
                        "\n" +
                        "Breath of the Wild – Nintendo Switch 2 Edition: 9.7GB\n" +
                        "Tears of the Kingdom – Nintendo Switch 2 Edition: 3.6GB\n" +
                        "Hacemos hincapié en que eso solo es el paquete de mejora. Breath of the Wild seguirá pesando 14.4GB (dando un total de 24.1GB al añadirle el paquete de mejora) y Tears of the Kingdom pesará hasta 19.9GB (16.3GB del juego base + 3.6GB del paquete de mejora). Hay que recalcar que Breath of the Wild no incluirá el DLC (Las pruebas legendarias y La balada de los elegidos, sendos vendidos como DLC en el mismo pase de expansión) y dicho contenido se seguirá vendiendo por separado. \n" + "Para quien tenga interés en hacerse con los paquetes de mejora, quizá quiera saber lo que incluye:\n" +
                        "\n" +
                        "HDR (sistema de colores más vivos): Ahora los juegos tendrán mejores reflejos y tonos más coloridos.\n" +
                        "Tasa de imágenes por segundo aumentada a 60: Originalmente, los dos juegos contaban con 30 imágenes por segundo y, de vez en cuando, había caídas de los mismos, ante todo en el Bosque Kolog. Dicho problema parece haberse arreglado en esa área.\n" +
                        "Resolución 4K: Ahora podrás disfrutar de una claridad y nitidez sin precedentes en las que no se apreciará ningún píxel en tu monitor o televisor 4K.\n" +
                        "Nueva ranura de guardado: Si tienes una partida principal, podrás comenzar una nueva partida secundaria en una nueva ranura de guardado que no perjudique a la original. Dos aventuras paralelas en el mismo mundo.\n" +
                        "Transferencia de archivo de guardado: Si ya cuentas con una partida en Nintendo Switch, podrás transferirla a Nintendo Switch 2 y guardar todo tu progreso para disfrutarlo gracias a las mejoras técnicas o empezar una partida de cero en la ranura nueva de guardado para revivir las dos épicas aventuras de Link en el Hyrule de la era de lo salvaje.\n" +
                        "Tiempos de carga menores: Al teletransportarte de un lugar a otro, los tiempos de carga se verán reducidos, lo cual incrementa la fluidez del juego.\n" +
                        "Podéis obtener el paquete de mejora de Breath of the Wild desde aquí y el de Tears of the Kingdom desde aquí. ¡No olvidéis darle clic a “Paquete de mejora” en la sección de compra!",
                imagenUrl = "https://universozelda.com/wp-content/uploads/2025/05/image.png",
                fuente = "Universo Zelda",
                tipo = "Switch 2"
            ),
        )
    }

    var noticias by remember { mutableStateOf(noticiasList) }

    var nextRefreshItemIndex by remember { mutableStateOf(0) }

    val noticiasToRefresh = remember {
        listOf(
            NoticiaItem(
                id = 8,
                titulo = "La precuela de Zelda TOTK 'no se limitará a las batallas', explican los creadores del nuevo Hyrule Warriors",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("10:26 21/5/2025"),
                resumen = "Ryota Matsushita y Yosuke Hayashi explican cómo crearon una nueva historia ambientada en el reino de Hyrule para Hyrule Warriors: La era del destierro, destacando las capacidades técnicas de Switch 2 y prometiendo explorar la vida en Hyrule tras un viaje en el tiempo de la princesa Zelda.",
                contenido = "En el video, Ryota Matsushita (productor) y Yosuke Hayashi (director de AAA Games Studio de Koei Tecmo) explican cómo crearon una nueva historia ambientada en el reino de Hyrule, y además poder ver nuevas escenas de gameplay. Según explican, el título mostrará las intensas batallas de la Guerra del Destierro desde la perspectiva propia de los juegos Warriors, al mismo tiempo que explorará la vida en Hyrule tras un viaje en el tiempo de la princesa Zelda. Aquí, conoceremos a los cuatro sabios enmascarados de The Legend of Zelda: Tears of the Kingdom, en un mundo antiguo que 'encierra muchos secretos'. El equipo también destaca las capacidades técnicas de Switch 2, que han permitido representar combates multitudinarios con más enemigos y 'una frecuencia de imágenes más alta que en Nintendo Switch', por lo que parece que el juego tendrá un rendimiento mejor que el de las anteriores entregas. En el video publicado por Nintendo, Hyrule Warriors: La era del destierro se mueve a 60 FPS, por lo que parece que apunta a esta tasa de imágenes por segundo.",
                imagenUrl = "https://media.vandal.net/i/1280x720/5-2025/21/202552110182179_1.jpg.webp",
                fuente = "El Español - Vandal",
                tipo = "Switch 2"
            ),
            NoticiaItem(
                id = 9,
                titulo = "Nuevos datos: Conocerás el Hyrule del pasado en La era del destierro y más",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("19:56 20/5/2025"),
                resumen = "Ryota Matsushita y Yosuke Hayashi explican cómo crearon una nueva historia ambientada en el reino de Hyrule para Hyrule Warriors: La era del destierro, destacando las capacidades técnicas de Switch 2 y prometiendo explorar la vida en Hyrule tras un viaje en el tiempo de la princesa Zelda.",
                contenido = "A meses de su estreno en invierno, Nintendo ha compartido un nuevo vídeo donde aparecen los desarrolladores del juego explicando los detalles del juego y del cual hemos hecho una recopilación de todo lo nuevo e importante que aparece destacado en el mismo:\n" +
                        "\n" +
                        "Vivirás todos los sucesos acontecidos desde que Zelda llega al pasado, por lo que se debe entender que habrá segmentos nunca antes vistos que revelarán nuevos detalles.\n" +
                        "Conocerás a los cuatro sabios enmascarados de los tiempos arcaicos, por lo que quizá se incida en su personalidad, nombre e identidad.\n" +
                        "La historia se centrará en revelar cómo las gentes del antiguo reino hicieron frente a la amenaza que suponía Ganondorf.\n" +
                        "Los desarrolladores dicen que “el mundo del pasado encierra muchos secretos”, animándonos a jugar al título.\n" +
                        "La jugabilidad ofrecerá el trazado de estrategias para decidir cómo abatir a vuestros enemigos.\n" +
                        "El juego cuenta con una tasa de imágenes (fps) de 60 por segundo mientras se lucha contra hordas de enemigos, algo que solo se podía conseguir en Nintendo Switch 2.",
                imagenUrl = "https://universozelda.com/wp-content/uploads/2025/05/Hyrule-Warriors.jpg",
                fuente = "Universo Zelda",
                tipo = "Switch 2"
            ),
            NoticiaItem(
                id = 10,
                titulo = "Se detallan las mejoras en Switch 2 de Link’s Awakening y Echoes of Wisdom",
                fechaPublicacion = SimpleDateFormat("HH:mm dd/M/yyyy", Locale.getDefault()).parse("19:55 16/5/2025"),
                resumen = "Durante el mes de abril se detalló oficialmente Nintendo Switch 2 y algunas de sus características, así como la retrocompatibilidad de la que algunos [...] ",
                contenido = "Durante el mes de abril se detalló oficialmente Nintendo Switch 2 y algunas de sus características, así como la retrocompatibilidad de la que algunos juegos gozarían una segunda vida gracias al paquete de mejora, así como Breath of the Wild y Tears of the Kingdom (cuya mejora tendrá un coste adicional para quien la quisiera obtener), pero al mismo tiempo algunos juegos antiguos recibirían una actualización gratuita para adecentarlos a la nueva generación.\n" +
                        "\n" +
                        "Dos pequeñas joyas de Nintendo Switch: Link’s Awakening y Echoes of Wisdom forman parte de la parrilla de juegos que Nintendo tiene en mente para actualizar sin coste alguno, aunque no se sabía exactamente qué añadiría hasta hace unas horas." +
                        "Ahora ya sabemos los nuevos aspectos que se incluyen en la actualización gratuita para Link’s Awakening y Echoes of Wisdom que se lanzarán el mismo día que la consola al mercado: el 5 de junio:\n" +
                        "\n" +
                        "Aspecto visual: Optimización para la pantalla de Nintendo Switch 2 y para televisores de alta resolución para ofrecer una mejor calidad de imagen.\n" +
                        "Compatibilidad con el alto rango dinámico (HDR) (Se requiere una pantalla compatible con el alto rango dinámico (HDR) al jugar en el modo televisor.)\n" +
                        "Por desgracia, no se especifica si habrá una mejora para la tasa de imágenes (fps/frames por segundo) de ambos juegos, ya que sendos pecaban de la misma falla en la consola para que se lanzaron.",
                imagenUrl = "https://universozelda.com/wp-content/uploads/2025/04/Actualizacion-gratuita-Nintendo-Switch-2-Links-Awakening-Echoes-of-Wisdom.jpg",
                fuente = "Universo Zelda",
                tipo = "Switch"
            ),
        )
    }

    var filtroSeleccionado by remember { mutableStateOf<String?>(null) }

    var isRefreshing by remember { mutableStateOf(false) }

    val onRefresh: () -> Unit = {
        isRefreshing = true

        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            if (nextRefreshItemIndex < noticiasToRefresh.size) {
                val itemToAdd = noticiasToRefresh[nextRefreshItemIndex]
                noticias = listOf(itemToAdd) + noticias
                nextRefreshItemIndex++
            }

            isRefreshing = false
        }, 500)
    }

    val tiposNoticias = remember(noticias) {
        noticias.map { it.tipo }.distinct()
    }

    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    val noticiasFiltradas = remember(noticias, filtroSeleccionado) {
        if (filtroSeleccionado == null) {
            noticias
        } else {
            noticias.filter { it.tipo == filtroSeleccionado }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                TitleText(text = stringResource(id = R.string.news_title))

                Spacer(modifier = Modifier.height(8.dp))

                FiltrosNoticias(
                    tipos = tiposNoticias,
                    filtroSeleccionado = filtroSeleccionado,
                    onFiltroSeleccionado = { filtroSeleccionado = it }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

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

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier.weight(1f)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(noticiasFiltradas) { noticia ->
                    NoticiaCard(noticia, dateFormatter)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticiaCard(noticia: NoticiaItem, dateFormatter: SimpleDateFormat) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                AsyncImage(
                    model = noticia.imagenUrl,
                    contentDescription = noticia.titulo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = noticia.titulo,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dateFormatter.format(noticia.fechaPublicacion),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = stringResource(id = R.string.news_source, noticia.fuente),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = noticia.resumen,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            if (expandido) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = noticia.contenido,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            TextButton(
                onClick = { expandido = !expandido },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = if (expandido) R.string.read_less else R.string.read_more),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun FiltrosNoticias(
    tipos: List<String>,
    filtroSeleccionado: String?,
    onFiltroSeleccionado: (String?) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            FilterChip(
                selected = filtroSeleccionado == null,
                onClick = { onFiltroSeleccionado(null) },
                label = { Text(stringResource(id = R.string.all)) },
                modifier = Modifier.padding(end = 1.dp)
            )
        }

        items(tipos) { tipo ->
            FilterChip(
                selected = filtroSeleccionado == tipo,
                onClick = { onFiltroSeleccionado(tipo) },
                label = { Text(tipo) },
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }
}

@Preview(
    name = "Noticias Screen Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    device = "id:pixel_6_pro"
)
@Composable
fun NoticiasScreenPreview() {
    MaterialTheme {
        NoticiasScreen()
    }
}






