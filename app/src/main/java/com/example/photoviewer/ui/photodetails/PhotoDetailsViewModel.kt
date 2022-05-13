package com.example.photoviewer.ui.photodetails

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

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}