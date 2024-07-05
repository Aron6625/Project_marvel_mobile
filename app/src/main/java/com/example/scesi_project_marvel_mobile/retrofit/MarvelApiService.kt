package com.example.scesi_project_marvel_mobile.retrofit


import com.example.scesi_project_marvel_mobile.ConexionApi.Companion.APIKEY
import com.example.scesi_project_marvel_mobile.ConexionApi.Companion.HASH_MD5
import com.example.scesi_project_marvel_mobile.ConexionApi.Companion.TIMES_TAMP
import com.example.scesi_project_marvel_mobile.model.HeadQuarters
import retrofit2.Response
import retrofit2.http.GET

interface AvengersService {

    @GET("/v1/public/characters?ts=${TIMES_TAMP}&apikey=${APIKEY}&hash=${HASH_MD5}")
    suspend fun getAllCharacters() : Response<HeadQuarters>

}