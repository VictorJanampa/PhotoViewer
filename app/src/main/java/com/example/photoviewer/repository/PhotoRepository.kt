package com.example.photoviewer.repository

import com.example.photoviewer.network.PhotoApiService

class PhotoRepository (private val api: PhotoApiService) {
    suspend fun getAllPhotos() = api.getPhotos()
}