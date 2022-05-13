package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.models.Photo


@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDatabaseDao(): PhotoDatabaseDao
}
