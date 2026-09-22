package com.opset.gameapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opset.gameapp.R
import com.opset.gameapp.model.Character
import com.opset.gameapp.ui.components.CharacterCard
import com.opset.gameapp.ui.theme.PixelFont
import androidx.compose.material.icons.filled.ExitToApp

/**
 * Project: gameApp
 * From: com.opset.gameapp.navigation
 * Created by: alvar
 * On: 21/09/2026
 * All rights reserved: 2026
 */

@Composable
fun HomeScreen(
    characters: List<Character> = emptyList(),
    favorites: Set<Int>,
    onFavoriteToggle: (Int) -> Unit,
    onCharacterSelect: (Int) -> Unit,
    onlyFavorites: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    var selectedTab by remember { mutableIntStateOf(if (onlyFavorites) 1 else 0) }

    val categories = remember(characters) {
        listOf("Todos") + characters.mapNotNull { it.race.takeIf { race -> race.isNotBlank() } }.distinct().sorted()
    }

    val isViewingFavorites = selectedTab == 1 || onlyFavorites

    val filteredCharacters = characters.filter { character ->
        val isFav = favorites.contains(character.id)
        val matchesSearch = character.name.contains(searchQuery, ignoreCase = true)
        val matchesCategory = if (selectedCategory == "Todos") true else character.race.equals(selectedCategory, ignoreCase = true)

        if (isViewingFavorites) {
            isFav && matchesSearch
        } else {
            matchesSearch && matchesCategory
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF131B2A)) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF9100),
                        selectedTextColor = Color(0xFFFF9100),
                        unselectedIconColor = Color(0xFF8A99AD),
                        unselectedTextColor = Color(0xFF8A99AD),
                        indicatorColor = Color(0xFF1D283D)
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
                    label = { Text("Favoritos (${favorites.size})") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF9100),
                        selectedTextColor = Color(0xFFFF9100),
                        unselectedIconColor = Color(0xFF8A99AD),
                        unselectedTextColor = Color(0xFF8A99AD),
                        indicatorColor = Color(0xFF1D283D)
                    )
                )
            }
        },
        containerColor = Color(0xFF070B12)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackClick?.invoke() },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF131B2A))
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Volver al Splash",
                        tint = Color(0xFFFF9100)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SAIYAN GO",
                        fontFamily = PixelFont,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9100)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.dragon_ball_4star),
                    contentDescription = "Esfera del Dragón",
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Título Principal
            Text(
                text = if (isViewingFavorites) "Mis Favoritos" else "Guerreros Z",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(
                text = if (isViewingFavorites) "Personajes que has guardado en tus favoritos." else "Explora personajes, filtra por raza y guarda tus favoritos.",
                fontSize = 13.sp,
                color = Color(0xFF8A99AD),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo de Busqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar guerrero... (Goku, Vegeta)", color = Color(0xFF536377), fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF536377)) },
                singleLine = true,
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF0F1724),
                    unfocusedContainerColor = Color(0xFF0F1724),
                    focusedBorderColor = Color(0xFFFF9100),
                    unfocusedBorderColor = Color(0xFF1A2638),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Chips de Filtro Por Raza
            if (!isViewingFavorites && categories.size > 1) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    items(categories) { category ->
                        val isSelected = selectedCategory == category
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = category },
                            label = { Text(category, fontSize = 13.sp, fontWeight = FontWeight.SemiBold) },
                            shape = RoundedCornerShape(20.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color(0xFF0F1724),
                                labelColor = Color(0xFF8A99AD),
                                selectedContainerColor = Color(0xFFA855F7),
                                selectedLabelColor = Color.White
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = Color(0xFF1A2638),
                                selectedBorderColor = Color(0xFFA855F7)
                            )
                        )
                    }
                }
            }

            // Grilla de Personajes
            if (filteredCharacters.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isViewingFavorites) "No tienes favoritos agregados." else "Cargando personajes...",
                        color = Color(0xFF8A99AD),
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(filteredCharacters) { character ->
                        val isFav = favorites.contains(character.id)
                        CharacterCard(
                            character = character,
                            isFavorite = isFav,
                            onFavoriteClick = { onFavoriteToggle(character.id) },
                            onClick = { onCharacterSelect(character.id) }
                        )
                    }
                }
            }
        }
    }
}