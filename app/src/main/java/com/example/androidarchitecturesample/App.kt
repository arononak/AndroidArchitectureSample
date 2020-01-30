package com.example.androidarchitecturesample

import android.app.Application
import com.example.androidarchitecturesample.di.networkModule
import com.example.androidarchitecturesample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(networkModule, viewModelModule))
        }
    }
}