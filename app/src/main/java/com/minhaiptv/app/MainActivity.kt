
package com.minhaiptv.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Red = Color(0xFFF20D19)
private val Background = Color(0xFF090909)
private val Panel = Color(0xFF151719)
private val Muted = Color(0xFF9CA0A5)

private data class Channel(
    val number: String,
    val name: String,
    val subtitle: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinhaIPTVApp()
        }
    }
}

@Composable
fun MinhaIPTVApp() {
    var selectedTab by remember { mutableStateOf("TV") }
    var selectedChannel by remember { mutableIntStateOf(0) }
    var message by remember {
        mutableStateOf("Bem-vindo ao Max Plus TV")
    }

    val tabs = listOf(
        "TV", "Jogos", "Destaques", "Filmes",
        "Séries", "Kids", "Anime", "Explorar"
    )

    val channels = listOf(
        Channel("001", "Canal de Notícias", "Jornal ao vivo"),
        Channel("002", "Canal Esportivo", "Esportes"),
        Channel("003", "Canal de Cinema", "Filmes"),
        Channel("004", "Canal de Variedades", "Entretenimento"),
        Channel("005", "Canal Infantil", "Programação infantil"),
        Channel("006", "Canal Musical", "Música e shows"),
        Channel("007", "Documentários", "Conhecimento"),
        Channel("008", "Notícias 24h", "Atualidades")
    )

    val categories = listOf(
        "TV AO VIVO", "FILMES", "SÉRIES",
        "KIDS", "ANIME", "EXPLORAR"
    )

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Red,
            background = Background,
            surface = Panel,
            onPrimary = Color.White,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF260407),
                            Background,
                            Background
                        )
                    )
                )
                .padding(12.dp)
        ) {
            // CABEÇALHO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "▶ MAX PLUS TV",
                    color = Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(Modifier.width(16.dp))

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tabs.forEach { tab ->
                        val active = selectedTab == tab

                        Text(
                            text = tab,
                            color = Color.White,
                            fontWeight = if (active)
                                FontWeight.Bold
                            else FontWeight.Normal,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .clip(RoundedCornerShape(9.dp))
                                .background(
                                    if (active) Red
                                    else Color.Transparent
                                )
                                .clickable {
                                    selectedTab = tab
                                    message = "Seção: $tab"
                                }
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 12.dp
                                )
                        )
                    }
                }

                IconButton(
                    onClick = {
                        message = "Pesquisa será implementada"
                    }
                ) {
                    Icon(Icons.Default.Search, "Pesquisar")
                }

                IconButton(
                    onClick = {
                        message = "Configurações"
                    }
                ) {
                    Icon(Icons.Default.Settings, "Configurações")
                }
            }

            Spacer(Modifier.height(8.dp))

            // CONTEÚDO PRINCIPAL
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // LISTA DE CANAIS
                Column(
                    modifier = Modifier
                        .widthIn(min = 170.dp, max = 270.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF101214))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF30090D))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.LiveTv,
                            null,
                            tint = Red
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Lista de Canais",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(7.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(5.dp)
                    ) {
                        itemsIndexed(channels) { index, channel ->
                            val active = index == selectedChannel

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (active) Red else Panel
                                    )
                                    .clickable {
                                        selectedChannel = index
                                        message =
                                            "Selecionado: ${channel.name}"
                                    }
                                    .padding(10.dp),
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {
                                Text(
                                    channel.number,
                                    color = Color.White,
                                    fontSize = 11.sp
                                )

                                Spacer(Modifier.width(8.dp))

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        channel.name,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Text(
                                        channel.subtitle,
                                        color = Color.LightGray,
                                        fontSize = 10.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                Icon(
                                    Icons.Default.ChevronRight,
                                    null,
                                    tint = Color.LightGray
                                )
                            }
                        }
                    }
                }

                // DESTAQUES E CATEGORIAS
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .horizontalScroll(rememberScrollState())
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 210.dp, max = 290.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFF071A2A),
                                            Color(0xFF28080D),
                                            Color(0xFF640A14)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    "MAX PLUS TV",
                                    color = Red,
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 3.sp
                                )

                                Spacer(Modifier.height(10.dp))

                                Text(
                                    "Seu entretenimento.",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Black
                                )

                                Text(
                                    "Canais, filmes e séries em um só lugar.",
                                    fontSize = 13.sp,
                                    color = Color.LightGray
                                )

                                Spacer(Modifier.height(16.dp))

                                Button(
                                    onClick = {
                                        message =
                                            "Conecte uma fonte autorizada para assistir"
                                    },
                                    colors = ButtonDefaults
                                        .buttonColors(
                                            containerColor = Red
                                        )
                                ) {
                                    Icon(
                                        Icons.Default.PlayArrow,
                                        null
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Text("Assistir agora")
                                }
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        Text(
                            "Explore as categorias",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Column(
                            verticalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {
                            categories.chunked(3).forEach { group ->
                                Row(
                                    horizontalArrangement =
                                        Arrangement.spacedBy(8.dp)
                                ) {
                                    group.forEachIndexed { index, category ->
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(90.dp)
                                                .clip(
                                                    RoundedCornerShape(10.dp)
                                                )
                                                .background(
                                                    listOf(
                                                        Color(0xFF8D1019),
                                                        Color(0xFF075BA7),
                                                        Color(0xFF6D1598),
                                                        Color(0xFF167B28),
                                                        Color(0xFFB74D0A),
                                                        Color(0xFF292D32)
                                                    )[categories.indexOf(category)]
                                                )
                                                .clickable {
                                                    selectedTab = category
                                                    message =
                                                        "Categoria: $category"
                                                }
                                                .padding(10.dp),
                                            contentAlignment =
                                                Alignment.BottomStart
                                        ) {
                                            Text(
                                                category,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Black
                                            )
                                        }
                                    }
                                    repeat(3 - group.size) {
                                        Spacer(Modifier.weight(1f))
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            "Lançamentos e destaques",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Row(
                            horizontalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "Ação", "Comédia", "Drama",
                                "Ficção", "Animação"
                            ).forEach { genre ->
                                Box(
                                    modifier = Modifier
                                        .width(110.dp)
                                        .height(85.dp)
                                        .clip(
                                            RoundedCornerShape(9.dp)
                                        )
                                        .background(Panel)
                                        .clickable {
                                            message =
                                                "Filtro: $genre"
                                        }
                                        .padding(10.dp),
                                    contentAlignment =
                                        Alignment.BottomStart
                                ) {
                                    Text(
                                        genre,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        Text(
                            message,
                            color = Muted,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

