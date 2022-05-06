package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.models.Photo

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