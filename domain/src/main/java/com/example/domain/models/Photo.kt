package com.example.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
@Entity(tableName = "photo_table")
data class Photo @Inject constructor(
    @ColumnInfo(name = "user_id")
    val albumId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String) : Parcelable