package com.example.pokemonexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemonexplorer.ui.theme.PokemonExplorerTheme
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.pokemonexplorer.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import com.example.pokemonexplorer.data.Pokemon
import kotlinx.coroutines.selects.select
import coil3.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonExplorerTheme {
                PokemonSearchScreen()
            }
        }
        lifecycleScope.launch {
            val pokemon = RetrofitInstance.api.getPokemon("pikachu")
            Log.d("PokemonExplorer", "Pokemon: $pokemon")
        }
    }
}

@Composable
fun PokemonWelcome() {
    Text("Welcome to Pokémon Explorer!")
}
@Composable
fun PokemonSearchScreen() {
    var searchText by remember { mutableStateOf("")}
    val scope = rememberCoroutineScope()
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }
    Column {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Pokémon Name")}
        )
        Button(
            onClick = {
                println("Button Clicked!")
                scope.launch {
                    try {
                        val pokemon = RetrofitInstance.api.getPokemon(searchText)
                        Log.d("PokemonExplorer", "Searching for $searchText")
                        Log.d("PokemonExplorer", "Pokemon: $pokemon")
                        Log.d("PokemonExplorer", "Sprite URL: ${pokemon.sprites.frontDefault}")
                        selectedPokemon = pokemon
                    } catch (e: Exception) {
                        Log.e("PokemonExplorer", "Request failed", e)
                    }
                }
            }
        ) {
            Text("Search")
        }
        selectedPokemon?.let { pokemon ->
            AsyncImage(
                model = pokemon.sprites.frontDefault,
                contentDescription = "Sprite of ${pokemon.name}"
            )
            Text("Name: ${pokemon.name}")
            Text("ID: ${pokemon.id}")
            Text("Height (meters): ${pokemon.height/10.0}")
            pokemon.types.forEach { pokemonType ->
                Text("Type: ${pokemonType.type.name}")
            }
        }
    }
}