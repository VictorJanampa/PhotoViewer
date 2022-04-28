package com.example.photoviewer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.photoviewer.repository.Photo


@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract val photoDatabaseDao: PhotoDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoDatabase? = null
        fun getInstance(context: Context): PhotoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhotoDatabase::class.java,
                        "photo_database"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
