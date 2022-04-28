package com.example.photoviewer

import android.app.Application
import androidx.annotation.experimental.Experimental
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}