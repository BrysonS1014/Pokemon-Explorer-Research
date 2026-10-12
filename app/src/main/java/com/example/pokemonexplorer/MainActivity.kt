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
import androidx.compose.ui.unit.sp
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
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import com.example.pokemonexplorer.data.Pokemon
import kotlinx.coroutines.selects.select
import coil3.compose.AsyncImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment

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
fun PokemonSearchScreen() {
    var searchText by remember { mutableStateOf("")}
    val scope = rememberCoroutineScope()
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null)}
    var isLoading by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Pokémon Explorer",
            fontSize = 24.sp
        )
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Pokémon Name")}
        )
        Button(
            onClick = {
                isLoading = true
                scope.launch {
                    try {
                        val pokemon = RetrofitInstance.api.getPokemon(searchText)
                        Log.d("PokemonExplorer", "Searching for $searchText")
                        Log.d("PokemonExplorer", "Pokemon: $pokemon")
                        Log.d("PokemonExplorer", "Sprite URL: ${pokemon.sprites.frontDefault}")
                        selectedPokemon = pokemon
                        errorMessage = null
                    } catch (e: Exception) {
                        Log.e("PokemonExplorer", "Request failed", e)
                        selectedPokemon = null
                        errorMessage = "Unable to retrieve Pokémon"
                    }
                    finally {
                        isLoading = false;
                    }
                }
            },
            enabled = !isLoading && searchText.isNotBlank()
        ) {
            Text("Search")
        }
        if (isLoading) {
            CircularProgressIndicator()
        }

        errorMessage?.let { eMessage ->
            Text(eMessage)
        }

        selectedPokemon?.let { pokemon ->
            Card {
                Column (
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = pokemon.sprites.frontDefault,
                        contentDescription = "Sprite of ${pokemon.name}",
                        modifier = Modifier.size(256.dp).align(Alignment.CenterHorizontally)
                    )
                    Text("Name: ${pokemon.name}")
                    Text("ID: ${pokemon.id}")
                    Text("Height (meters): ${pokemon.height / 10.0}")
                    pokemon.types.forEach { pokemonType ->
                        Text("Type: ${pokemonType.type.name}")
                    }
                }
            }
        }
    }
}