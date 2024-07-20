package com.example.scesi_project_marvel_mobile.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object MarvelHandler {
    private const val MARVEL_BASE_URL = "https://gateway.marvel.com:443/v1/public/"

    val service: MarvelService = Retrofit.Builder()
        .baseUrl(MARVEL_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(MarvelService::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(addKeyAndHash())
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }


        private fun addKeyAndHash(): Interceptor = Interceptor { chain ->
        val params = mapOf(
            "apikey" to "996927ca470f08c9924720b357b00001",
            "hash" to "b0ed8cb7e03ebfb5888028692f03c271",
            "ts" to "1"
        )
        val originalRequest = chain.request()
        val urlWithParams = originalRequest.url().newBuilder()
            .apply { params.forEach { addQueryParameter(it.key, it.value) } }
            .build()
        val newRequest = originalRequest.newBuilder().url(urlWithParams).build()
        chain.proceed(newRequest)
    }
}
