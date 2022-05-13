package com.example.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.models.Photo


@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDatabaseDao: PhotoDatabaseDao
}

fun provideDataBase(application: Application): PhotoDatabase {
    return Room.databaseBuilder(application, PhotoDatabase::class.java, "photos_database") // TODO: move this string to a constant
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDao(photoDatabase: PhotoDatabase): PhotoDatabaseDao {
    return photoDatabase.photoDatabaseDao
}
