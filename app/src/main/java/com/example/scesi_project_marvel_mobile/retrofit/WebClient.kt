package com.example.scesi_project_marvel_mobile.retrofit

class WebClient(
    private val services: AvengersService = AppRetrofit().services
) {

    suspend fun getAllCharacters() = services.getAllCharacters().body()

}