package com.example.photoviewer.ui.photolist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Photo
import com.example.domain.interactors.GetPhotosUseCaseImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.launch

class PhotoListViewModel(private val getPhotos: GetPhotosUseCaseImpl) : ViewModel(){

    val disposables = CompositeDisposable()

    val photosRx: BehaviorSubject<List<Photo>> = BehaviorSubject.create()
    val navigateToSelectedItemRx: PublishSubject<Photo> = PublishSubject.create()

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
       viewModelScope.launch {
           photosRx.onNext(getPhotos.invoke())
       }
    }

    fun refresh()  {
        fetchPhotos()
    }

    fun displayPhoto(photo: Photo) {
        navigateToSelectedItemRx.onNext(photo)
    }

    fun clearPhotos() {
        photosRx.onNext(ArrayList())
      }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
