package com.example.pokedex
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val nome: String,
    val stripes: Stripes
)

data class Stripes(
    @SerializedName("name") val nome: String,
    @SerializedName("front_default") val imagem: String
)

