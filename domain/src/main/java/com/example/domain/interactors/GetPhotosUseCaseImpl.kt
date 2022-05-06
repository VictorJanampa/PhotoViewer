package com.example.domain.interactors

import com.example.domain.repository.IPhotosRepository

class GetPhotosUseCaseImpl(private val repository: IPhotosRepository) : GetPhotosUseCase {
    override suspend operator fun invoke() = repository.getAllPhotos()
}
