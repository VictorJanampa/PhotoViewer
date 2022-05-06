package com.example.photoviewer

import com.example.photoviewer.database.provideDao
import com.example.photoviewer.database.provideDataBase
import com.example.photoviewer.network.PhotoApiService
import com.example.photoviewer.network.provideMoshi
import com.example.photoviewer.network.provideRetrofit
import com.example.photoviewer.photodetails.PhotoDetailsViewModel
import com.example.photoviewer.photolist.PhotoListViewModel
import com.example.photoviewer.repository.Photo
import com.example.photoviewer.repository.PhotoRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { PhotoListViewModel(get()) }
    viewModel { (photo: Photo) -> PhotoDetailsViewModel(get(), photo) }
}

val repositoryModule = module {
    single { PhotoRepository(get(),get()) }
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

val databaseModule = module {
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}


