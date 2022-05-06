package com.example.photoviewer.ui.photodetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Photo
import com.example.data.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(private val repository: PhotoRepository, photo: Photo) : ViewModel() {
    private val _selectedProperty = MutableLiveData<Photo>()
    val selectedProperty: LiveData<Photo>
        get() = _selectedProperty

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        _selectedProperty.value = photo
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            _photos.value = repository.getAllPhotos()
        }
    }

    fun getPosition(): Int {
        Log.i("Andrio","${_selectedProperty.value?.id ?: 0}")
        return (_selectedProperty.value?.id ?: 0) -1
    }
}