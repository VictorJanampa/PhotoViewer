package com.example.domain.di

import com.example.domain.interactors.GetPhotosUseCase
import com.example.domain.interactors.GetPhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
@Module
abstract class UseCasesModule {
    @Binds
    abstract fun bindUseCase(impl: GetPhotosUseCaseImpl): GetPhotosUseCase
}