package com.example.data.network

import com.example.domain.models.Photo
import retrofit2.http.GET

interface PhotoApiService {
    @GET("photos?albumId=1&&albumId=2")
    suspend fun getPhotos(): List<Photo>
}