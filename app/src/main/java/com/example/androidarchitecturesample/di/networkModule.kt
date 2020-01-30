package com.example.androidarchitecturesample.di

import android.util.Log
import com.example.androidarchitecturesample.data.api.WykopApi
import com.example.androidarchitecturesample.ui.entry.EntryDataSource
import com.example.androidarchitecturesample.ui.promoted.PromotedDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideBaseUrl() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideLoggingInterceptor() }
    single { provideWykopApi(get()) }
    factory { EntryDataSource(get()) }
    factory { PromotedDataSource(get()) }
}

fun provideBaseUrl(): String = "https://a.wykop.pl/"

fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("API", it) })
        .also { it.level = HttpLoggingInterceptor.Level.BASIC }

fun provideWykopApi(retrofit: Retrofit): WykopApi = retrofit.create(WykopApi::class.java)
