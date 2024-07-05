package com.example.scesi_project_marvel_mobile

import com.example.scesi_project_marvel_mobile.retrofit.MarvelApiService

class MarvelRepository(private val apiService: MarvelApiService) {

    suspend fun getAllCharacters() = webClient.getAllCharacters()

    suspend fun saveCharacter(character: Character) = dao.insertCharacter(character)

    suspend fun getAllCharacterDao() = dao.getAllCharacter()

}
