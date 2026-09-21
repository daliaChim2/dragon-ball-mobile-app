package com.opset.gameapp.data.local
import android.content.Context
/**
 * Project: gameApp
 * From: com.opset.gameapp.data.local
 * Created by: usuario
 * On: 9/20/2026
 * All rights reserved: 2026
 */

class FavoritesManager(context: Context) {
    private val prefs = context.getSharedPreferences("dbz_favorites_prefs", Context.MODE_PRIVATE)

    fun getFavoriteIds(): Set<String> {
        return prefs.getStringSet("fav_ids", emptySet()) ?: emptySet()
    }

    fun toggleFavorite(characterId: Int): Boolean {
        val currentFavs = getFavoriteIds().toMutableSet()
        val idString = characterId.toString()
        val isNowFavorite: Boolean

        if (currentFavs.contains(idString)) {
            currentFavs.remove(idString)
            isNowFavorite = false
        } else {
            currentFavs.add(idString)
            isNowFavorite = true
        }

        prefs.edit().putStringSet("fav_ids", currentFavs).apply()
        return isNowFavorite
    }

    fun isFavorite(characterId: Int): Boolean {
        return getFavoriteIds().contains(characterId.toString())
    }
}