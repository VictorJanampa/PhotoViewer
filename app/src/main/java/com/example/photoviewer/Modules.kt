package com.example.photoviewer

import com.example.photoviewer.network.PhotoApiService
import com.example.photoviewer.network.provideMoshi
import com.example.photoviewer.network.provideRetrofit
import com.example.photoviewer.photolist.PhotoListViewModel
import com.example.photoviewer.repository.PhotoRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { PhotoListViewModel(get()) }
}

val repositoryModule = module {
    single { PhotoRepository(get()) }
}
val apiModule = module {

    fun provideApi(retrofit: Retrofit): PhotoApiService {
        return retrofit.create(PhotoApiService::class.java)
    }
    single { provideApi(get()) }
}

val retrofitModule = module {
    single { provideMoshi() }
    single { provideRetrofit(get()) }
}


