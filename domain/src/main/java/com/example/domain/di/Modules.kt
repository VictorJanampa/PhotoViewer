package com.example.domain.di

import com.example.domain.interactors.GetPhotosUseCase
import com.example.domain.interactors.GetPhotosUseCaseImpl
import org.koin.dsl.module

var useCasesModule = module {
    factory<GetPhotosUseCase> {GetPhotosUseCaseImpl(get())}
}