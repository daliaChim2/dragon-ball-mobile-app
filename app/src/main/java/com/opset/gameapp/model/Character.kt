package com.opset.gameapp.model

/**
 * Project: gameApp
 * From: com.opset.gameapp.model
 * Created by: alvar
 * On: 22/09/2026
 * All rights reserved: 2026
 */



data class CharacterResponse(
    val items: List<Character>
)

data class OriginPlanet(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String
)

data class Transformation(
    val id: Int,
    val name: String,
    val ki: String,
    val image: String
)

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
    val originPlanet: OriginPlanet? = null,
    val transformations: List<Transformation> = emptyList()
)