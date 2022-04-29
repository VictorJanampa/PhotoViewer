package com.example.photoviewer.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.photoviewer.repository.Photo


@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDatabaseDao: PhotoDatabaseDao
}

fun provideDataBase(application: Application): PhotoDatabase {
    return Room.databaseBuilder(application, PhotoDatabase::class.java, "photos_database")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDao(photoDatabase: PhotoDatabase): PhotoDatabaseDao {
    return photoDatabase.photoDatabaseDao
}
