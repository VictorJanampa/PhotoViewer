package com.example.photoviewer.photolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.photoviewer.databinding.PhotoListItemBinding
import com.example.photoviewer.repository.Photo

class PhotoGridAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<Photo, PhotoGridAdapter.PhotoDetailViewHolder>(DiffCallback) {

    class PhotoDetailViewHolder(private var binding: PhotoListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PhotoDetailViewHolder {
        return PhotoDetailViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoDetailViewHolder, position: Int) {
        val photoDetail = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photoDetail)
        }
        holder.bind(photoDetail)
    }

    class OnClickListener(val clickListener: (marsProperty:Photo) -> Unit) {
        fun onClick(marsProperty:Photo) = clickListener(marsProperty)
    }
}
