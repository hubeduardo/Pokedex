package com.example.pokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService {

    @GET("/{identificacao}")
    fun buscarPokemon(@Path("identificacao")identificacao: String) :
            Call<Pokemon>

}