package com.example.photoviewer.ui.photodetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Photo
import com.example.domain.interactors.GetPhotosUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val getPhotos: GetPhotosUseCaseImpl
    ) : ViewModel() {

    val disposables = CompositeDisposable()

    val photosRx: BehaviorSubject<List<Photo>> = BehaviorSubject.create()

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            photosRx.onNext(getPhotos.invoke())
        }
    }

//    fun getPosition(): Int {
//        Log.i("Andrio","${photo.id}")
//        return (photo.id) -1
//    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}