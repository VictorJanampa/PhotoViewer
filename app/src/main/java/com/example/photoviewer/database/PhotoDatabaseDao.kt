package com.example.photoviewer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoviewer.repository.Photo

@Dao
interface PhotoDatabaseDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhoto(photo: Photo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllPhotos(photos: List<Photo>)

    @Query("SELECT * from photo_table")
    fun getAllPhotos(): List<Photo>

    @Query("DELETE FROM photo_table")
    fun clearPhotos()
}