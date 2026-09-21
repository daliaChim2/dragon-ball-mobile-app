package com.opset.gameapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opset.gameapp.ui.screens.HomeScreen
import com.opset.gameapp.ui.screens.SplashScreen

/**
 * Project: gameApp
 * From: com.opset.gameapp.navigation
 * Created by: alvar
 * On: 21/09/2026
 * All rights reserved: 2026
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Pantalla Splash
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onStartClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla Home
        composable(route = Screen.Home.route) {
            HomeScreen(
                onCharacterClick = { characterId ->
                    // Navegación al detalle del personaje
                }
            )
        }
    }
}


