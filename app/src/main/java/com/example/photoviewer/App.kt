package com.example.photoviewer

import android.app.Application

import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@App)
//            modules(appModule + dataModule + domainModule)
//        }
    }
//    private val appModule = listOf(viewModelModule)
//    private val dataModule = listOf(repositoryModule, retrofitModule, apiModule, databaseModule)
//    private val domainModule = listOf(useCaseModule)
}