package com.example.photoviewer.di


import com.example.data.repository.PhotoRepository
import com.example.domain.interactors.GetPhotosUseCaseImpl
import com.example.photoviewer.ui.photodetails.PhotoDetailsViewModel
import com.example.photoviewer.ui.photolist.PhotoListViewModel
import com.example.domain.models.Photo

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PhotoListViewModel(get()) }
    viewModel { (photo: Photo) -> PhotoDetailsViewModel(get(), photo) }
}
val useCaseModule = module {
    fun provideGetPhotosUseCase(photoRepository: PhotoRepository): GetPhotosUseCaseImpl {
        return GetPhotosUseCaseImpl(photoRepository)
    }
    single{provideGetPhotosUseCase(get())}
}