package com.example.pokemonexplorer.data

import com.google.gson.annotations.SerializedName
data class Pokemon(
    val id: Int,
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val types: List<PokemonTypeSlot>,
)
