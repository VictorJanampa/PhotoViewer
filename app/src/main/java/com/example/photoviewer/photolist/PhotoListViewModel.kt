package com.example.photoviewer.photolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photoviewer.repository.Photo
import com.example.photoviewer.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoListViewModel(private val repository: PhotoRepository) : ViewModel(){

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _navigateToSelectedItem = MutableLiveData<Photo?>()
    val navigateToSelectedProperty: MutableLiveData<Photo?>
        get() = _navigateToSelectedItem


    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _photos.value = repository.getAllPhotos()

            } catch (e: Exception) {
                _photos.value = ArrayList()
            }
        }
    }

    fun displayPhoto(photo: Photo) {
        _navigateToSelectedItem.value = photo
    }

    fun displayPhotoComplete() {
        _navigateToSelectedItem.value = null
    }

    fun greet() {
        Log.i("Andrio", "Hola")
    }
}