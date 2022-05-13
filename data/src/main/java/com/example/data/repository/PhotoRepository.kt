package com.example.data.repository


import com.example.data.database.PhotoDatabaseDao
import com.example.data.network.PhotoApiService
import com.example.domain.models.Photo
import com.example.domain.repository.IPhotosRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoRepository (private val api: PhotoApiService, private val database: PhotoDatabaseDao):IPhotosRepository {
    private suspend fun getFromNetwork() = api.getPhotos()
    private fun getFromDatabase() = database.getAllPhotos()

    override suspend fun getAllPhotos(): List<Photo> {
        var photos: List<Photo>
        withContext(Dispatchers.IO) {
            try {
                photos = getFromDatabase()
                if (photos.isEmpty()) {
                    photos = getFromNetwork()
                    database.insertAllPhotos(photos)
                }
            } catch (e: Exception) {
                photos = ArrayList()
            }
        }
        return photos
    }

    fun clearDatabase(): ArrayList<Photo>{ //TODO:  please remove un used function
        CoroutineScope(Dispatchers.IO).launch {
            database.clearPhotos()
        }
        return ArrayList()
    }
}