package com.example.photoviewer.ui.photolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Photo
import com.example.data.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoListViewModel(private val repository: PhotoRepository) : ViewModel(){

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _navigateToSelectedItem = MutableLiveData<Photo?>()
    val navigateToSelectedProperty: MutableLiveData<Photo?>
        get() = _navigateToSelectedItem

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
       viewModelScope.launch {
           _photos.value = repository.getAllPhotos()
       }
    }

    fun refresh(): Boolean {
        fetchPhotos()
        return false
    }

    fun clearPhotos() {
        _photos.value = repository.clearDatabase()
    }

    fun displayPhoto(photo: Photo) {
        _navigateToSelectedItem.value = photo
    }

    fun displayPhotoComplete() {
        _navigateToSelectedItem.value = null
    }
}