package com.example.domain.interactors

import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCaseImpl @Inject constructor(private val repository: PhotoRepository) : GetPhotosUseCase {
    override suspend operator fun invoke() = repository.getAllPhotos()
}
