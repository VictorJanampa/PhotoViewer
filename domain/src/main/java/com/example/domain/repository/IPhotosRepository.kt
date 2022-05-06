package com.example.domain.repository

import com.example.domain.models.Photo

interface IPhotosRepository {
    suspend fun getAllPhotos():List<Photo>
}