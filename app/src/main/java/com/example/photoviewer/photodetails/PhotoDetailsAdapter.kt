package com.example.photoviewer.photodetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.photoviewer.databinding.PhotoListFullItemBinding
import com.example.photoviewer.photolist.ItemViewHolder
import com.example.photoviewer.photolist.PhotoItemFullViewHolder
import com.example.photoviewer.repository.Photo


class PhotoDetailsAdapter :
    ListAdapter<Photo, ItemViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override  fun onCreateViewHolder(parent: ViewGroup,
                                     viewType: Int): ItemViewHolder {

        return PhotoItemFullViewHolder(PhotoListFullItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photoDetail = getItem(position)
        holder.bind(photoDetail)
    }
}
