package com.example.photoviewer

import android.app.Application
import com.example.data.di.apiModule
import com.example.data.di.databaseModule
import com.example.data.di.repositoryModule
import com.example.data.di.retrofitModule
import com.example.domain.di.useCasesModule
import com.example.photoviewer.di.useCaseModule
import com.example.photoviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule + dataModule + domainModule)
        }
    }
    private val appModule = listOf(viewModelModule)
    private val dataModule = listOf(repositoryModule,  retrofitModule, apiModule, databaseModule)
    private val domainModule = listOf(useCaseModule)
}