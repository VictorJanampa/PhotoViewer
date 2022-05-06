package com.example.photoviewer.repository

import com.example.photoviewer.database.PhotoDatabaseDao
import com.example.photoviewer.network.PhotoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoRepository (private val api: PhotoApiService, private val database: PhotoDatabaseDao) {
    private suspend fun getFromNetwork() = api.getPhotos()
    private fun getFromDatabase() = database.getAllPhotos()

    suspend fun getAllPhotos(): List<Photo> {
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

    fun clearDatabase(): ArrayList<Photo>{
        CoroutineScope(Dispatchers.IO).launch {
            database.clearPhotos()
        }
        return ArrayList()
    }
}