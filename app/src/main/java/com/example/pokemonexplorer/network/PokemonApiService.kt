package com.example.pokemonexplorer.network

import com.example.pokemonexplorer.data.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name")  pokemonName: String
    ): Pokemon
}