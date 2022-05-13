package com.example.photoviewer.di

import com.example.data.repository.PhotoRepositoryImpl
import com.example.domain.interactors.GetPhotosUseCase
import com.example.domain.interactors.GetPhotosUseCaseImpl
import com.example.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


//val viewModelModule = module {
//    viewModel { PhotoListViewModel(get()) }
//    viewModel { (photo: Photo) -> PhotoDetailsViewModel(get(), photo) }
//}
//val useCaseModule = module {
//    fun provideGetPhotosUseCase(photoRepositoryImpl: PhotoRepositoryImpl): GetPhotosUseCaseImpl {
//        return GetPhotosUseCaseImpl(photoRepositoryImpl)
//    }
//    single{provideGetPhotosUseCase(get())}
//}