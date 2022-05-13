package com.example.domain.repository

import com.example.domain.models.Photo

interface PhotoRepository {
    suspend fun getAllPhotos():List<Photo>
}