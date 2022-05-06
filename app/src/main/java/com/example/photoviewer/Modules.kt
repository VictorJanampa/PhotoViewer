package com.example.photoviewer

import com.example.data.database.provideDao
import com.example.data.database.provideDataBase
import com.example.data.network.PhotoApiService
import com.example.data.network.provideMoshi
import com.example.data.network.provideRetrofit
import com.example.photoviewer.ui.photodetails.PhotoDetailsViewModel
import com.example.photoviewer.ui.photolist.PhotoListViewModel
import com.example.domain.models.Photo
import com.example.data.repository.PhotoRepository
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


