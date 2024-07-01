package com.example.scesi_project_marvel_mobile
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("comics")
    suspend fun getComics(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Response<ComicDataWrapper>
}