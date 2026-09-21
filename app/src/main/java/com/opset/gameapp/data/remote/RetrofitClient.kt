package com.opset.gameapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Project: gameApp
 * From: com.opset.gameapp.data.remote
 * Created by: usuario
 * On: 9/20/2026
 * All rights reserved: 2026
 */

object RetrofitClient {
    private const val BASE_URL = "https://dragonball-api.com/api/"

    val apiService: DragonBallApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DragonBallApiService::class.java)
    }
}