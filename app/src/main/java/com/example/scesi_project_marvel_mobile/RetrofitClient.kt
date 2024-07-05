package com.example.scesi_project_marvel_mobile
import com.example.scesi_project_marvel_mobile.retrofit.MarvelApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    fun create(): MarvelApiService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apikey", "YOUR_PUBLIC_KEY")
                    .addQueryParameter("ts", "1")  // Timestamp
                    .addQueryParameter("hash", "GENERATED_HASH")
                    .build()
                val request = chain.request().newBuilder().url(url).build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }
}