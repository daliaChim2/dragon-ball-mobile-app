package com.opset.gameapp.data.remote

import com.opset.gameapp.data.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Project: gameApp
 * From: com.opset.gameapp.data.remote
 * Created by: usuario
 * On: 9/20/2026
 * All rights reserved: 2026
 */

interface DragonBallApiService {
    // Obtener lista general de personajes o filtrar por raza/nombre
    @GET("characters")
    suspend fun getCharacters(
        @Query("race") race: String? = null,
        @Query("name") name: String? = null
    ): List<Character>

    // Obtener información detallada de un personaje por ID (incluye planeta y transformaciones)
    @GET("characters/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): Character
}