package com.example.photoviewer.photolist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.photoviewer.databinding.PhotoListFullItemBinding
import com.example.photoviewer.databinding.PhotoListItem2Binding
import com.example.photoviewer.databinding.PhotoListItemBinding
import com.example.photoviewer.repository.Photo

abstract class ItemViewHolder (binding: View):
RecyclerView.ViewHolder(binding) {
    abstract fun bind(photo: Photo)
}
class PhotoItem1ViewHolder(private var binding: PhotoListItemBinding) : ItemViewHolder(binding.root){
    override fun bind(photo: Photo) {
        binding.photo = photo
        binding.executePendingBindings()
    }
}

class PhotoItem2ViewHolder(private var binding: PhotoListItem2Binding):
    ItemViewHolder(binding.root) {
    override fun bind(photo: Photo) {
        binding.photo = photo
        binding.executePendingBindings()
    }
}

class PhotoItemFullViewHolder(private var binding: PhotoListFullItemBinding) : ItemViewHolder(binding.root) {
    override fun bind(photo: Photo) {
        binding.photo = photo
        binding.executePendingBindings()
    }
}