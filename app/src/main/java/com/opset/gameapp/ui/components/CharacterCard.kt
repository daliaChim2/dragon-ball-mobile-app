package com.opset.gameapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.opset.gameapp.model.Character

/**
 * Project: gameApp
 * From: com.opset.gameapp.ui.components
 * Created by: alvar
 * On: 22/09/2026
 * All rights reserved: 2026
 */

@Composable
fun CharacterCard(
    character: Character,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    // Obtiene la raza directamente de la API
    val raceText = character.race.ifBlank { "Desconocido" }

    // determinar el color base de forma dinámica
    val baseColor = when (raceText.lowercase()) {
        "saiyan" -> Color(0xFFFF9100)   // Naranja
        "namekian" -> Color(0xFF00E676) // Verde
        "human" -> Color(0xFF0288D1)    // Azul
        else -> Color(raceText.hashCode() or 0xFF000000.toInt()) // Algoritmo dinámico para cualquier otra raza de la API
    }

    // Crea los tres estilos
    val badgeBg = baseColor.copy(alpha = 0.2f)
    val badgeBorder = baseColor
    val badgeText = baseColor

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF111827)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Sección Superior: Imagen + Badges
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .background(Color(0xFF090E17))
                    .padding(10.dp)
            ) {
                // Badge de Raza (Arriba Izquierda)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(12.dp))
                        .background(badgeBg)
                        .border(1.dp, badgeBorder.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = raceText,
                        color = badgeText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Botón de Favorito(Arriba Derecha)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xBB000000))
                        .clickable { onFavoriteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (isFavorite) Color(0xFFFF9100) else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Imagen del Personaje
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 28.dp, bottom = 4.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Nombre y Ki Base
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF131C2E))
                    .padding(14.dp)
            ) {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Ki base:",
                    fontSize = 12.sp,
                    color = Color(0xFF8A99AD)
                )

                Text(
                    text = character.ki.ifBlank { "0" },
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF9100)
                )
            }
        }
    }
}