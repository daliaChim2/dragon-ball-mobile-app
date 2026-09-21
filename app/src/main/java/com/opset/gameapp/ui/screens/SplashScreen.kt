package com.opset.gameapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opset.gameapp.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

/**
 * Project: gameApp
 * From: com.opset.gameapp.navigation
 * Created by: alvar
 * On: 21/09/2026
 * All rights reserved: 2026
 */

@Composable
fun SplashScreen(onStartClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "floating")
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float"
    )
// Definir la fuente
    val PixelFont = FontFamily(
        Font(R.font.press_start_2p)
    )
    val buttonScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "buttonScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF221105),
                        Color(0xFF0F0F18),
                        Color(0xFF080811)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        GridBackground()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "UNIVERSO 7",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA882FF),
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            //cambiar por el logo
            Image(
                painter = painterResource(id = R.drawable.dragon_ball_4star),
                contentDescription = "Esfera de 4 estrellas",
                modifier = Modifier
                    .offset(y = floatOffset.dp)
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "SAIYAN GO",
                fontFamily = PixelFont,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF9100),
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = buildAnnotatedString {
                    append("La enciclopedia de\nguerreros de ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                        append("Dragon Ball")
                    }
                },
                fontSize = 18.sp,
                color = Color(0xFFD0D3DC),
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onStartClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7000)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .scale(buttonScale)
                    .height(52.dp)
                    .width(210.dp)
                    .shadow(12.dp, RoundedCornerShape(16.dp), ambientColor = Color(0xFFFF7000), spotColor = Color(0xFFFF7000))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "PRESS START",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = ">",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Datos por dragonball-api.com",
                fontSize = 12.sp,
                color = Color(0xFF8D99AE)
            )
        }
    }
}

@Composable
fun GridBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val gridSpacing = 40.dp.toPx()
        val width = size.width
        val height = size.height
        val gridColor = Color(0xFFFFFFFF).copy(alpha = 0.03f)

        var x = 0f
        while (x < width) {
            drawLine(
                color = gridColor,
                start = Offset(x, 0f),
                end = Offset(x, height),
                strokeWidth = 1f
            )
            x += gridSpacing
        }

        var y = 0f
        while (y < height) {
            drawLine(
                color = gridColor,
                start = Offset(0f, y),
                end = Offset(width, y),
                strokeWidth = 1f
            )
            y += gridSpacing
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onStartClick = {})
}