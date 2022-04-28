package com.example.photoviewer.network

import com.example.photoviewer.repository.Photo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

fun provideMoshi() : Moshi{
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideRetrofit(moshi: Moshi) : Retrofit{
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
}

interface PhotoApiService {
    @GET("photos?albumId=1&&albumId=2")
    suspend fun getPhotos(): List<Photo>
}