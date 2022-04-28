package com.example.photoviewer.photodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoviewer.repository.Photo

class PhotoDetailsViewModel(photo: Photo) : ViewModel() {
    private val _selectedProperty = MutableLiveData<Photo>()
    val selectedProperty: LiveData<Photo>
        get() = _selectedProperty

    init {
        _selectedProperty.value = photo
    }
}