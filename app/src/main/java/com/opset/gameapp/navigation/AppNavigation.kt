package com.opset.gameapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.opset.gameapp.model.Character
import com.opset.gameapp.ui.screens.DetailScreen
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
    object Detail : Screen("detail_screen/{characterId}") {
        fun createRoute(characterId: Int) = "detail_screen/$characterId"
    }
}

@Composable
fun AppNavigation(
    charactersList: List<Character> = emptyList()
) {
    val navController = rememberNavController()
    var favoriteIds by remember { mutableStateOf(setOf<Int>()) }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Pantalla Splash
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onStartClick = {
                    // Navega a Home manteniendo el Splash en el historial de navegación
                    navController.navigate(Screen.Home.route)
                }
            )
        }

        // Pantalla Home
        composable(route = Screen.Home.route) {
            HomeScreen(
                characters = charactersList,
                favorites = favoriteIds,
                onFavoriteToggle = { characterId ->
                    favoriteIds = if (favoriteIds.contains(characterId)) {
                        favoriteIds - characterId
                    } else {
                        favoriteIds + characterId
                    }
                },
                onCharacterSelect = { characterId ->
                    navController.navigate(Screen.Detail.createRoute(characterId))
                },
                onBackClick = {
                    // Regresa de forma natural a la pantalla anterior
                    navController.popBackStack()
                }
            )
        }

        // Pantalla Detalle
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            val selectedCharacter = charactersList.find { it.id == characterId }

            if (selectedCharacter != null) {
                DetailScreen(
                    character = selectedCharacter,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}