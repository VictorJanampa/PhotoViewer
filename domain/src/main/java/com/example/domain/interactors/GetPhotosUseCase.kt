package com.example.domain.interactors

import com.example.domain.models.Photo

interface GetPhotosUseCase {
    suspend operator fun invoke(): List<Photo>
}