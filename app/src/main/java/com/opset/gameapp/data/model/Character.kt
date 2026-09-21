package com.opset.gameapp.data.model

/**
 * Project: gameApp
 * From: com.opset.gameapp.data.model
 * Created by: usuario
 * On: 9/20/2026
 * All rights reserved: 2026
 */

data class Character(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val originPlanet: Planet? = null,
    val transformations: List<Transformation> = emptyList()
)

data class Planet(
    val id: Int,
    val name: String,
    val image: String
)

data class Transformation(
    val id: Int,
    val name: String,
    val ki: String,
    val image: String
)