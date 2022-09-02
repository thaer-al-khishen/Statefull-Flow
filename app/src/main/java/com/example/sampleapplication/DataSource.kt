package com.example.sampleapplication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class DataSource {

    val pokemonList = ArrayList<Pokemon>()

    init {
        pokemonList.add(Pokemon(0, "Pikachu", "Lightning"))
        pokemonList.add(Pokemon(1, "Charmander", "Fire"))
        pokemonList.add(Pokemon(2, "Squirtle", "Water"))
        pokemonList.add(Pokemon(3, "Bulbasaur", "Nature"))
    }

    fun getPokemonList(): Flow<StatefulData<List<Pokemon>>> = flow {
//        emit(StatefulData.Success<List<Pokemon>>(pokemonList))
//        emit(StatefulData.Error("Couldn't retrieve list"))
        emit(pokemonList as List<Pokemon>)
    }.asStatefulData()

    fun getSafePokemonList(): List<Pokemon>  {
        return pokemonList
    }

    fun getPokemonListFlow(): Flow<StatefulData<List<Pokemon>>> = statefulFlow {
        getSafePokemonList()
    }

}
