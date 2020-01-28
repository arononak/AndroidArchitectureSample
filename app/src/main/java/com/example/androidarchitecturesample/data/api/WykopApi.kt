package com.example.androidarchitecturesample.data.api

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://a.wykop.pl/"

interface WykopApi {

    @GET("links/promoted/appkey/{appkey}/page/{page}")
    suspend fun fetchPromoted(
        @Path("page") page: Int,
        @Path("appkey") appkey: String = "gnr9daRtLW"
    ): Response<List<Promoted>>

    @GET("stream/entries/appkey/{appkey}/page/{page}")
    suspend fun fetchEntries(
        @Path("page") page: Int,
        @Path("appkey") appkey: String = "gnr9daRtLW"
    ): Response<List<Entry>>

    companion object {

        @Volatile
        private var instance: WykopApi? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: create(HttpUrl.parse(BASE_URL)!!).also { instance = it }
            }

        private fun create(httpUrl: HttpUrl): WykopApi {

            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })

            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(WykopApi::class.java)
        }
    }
}