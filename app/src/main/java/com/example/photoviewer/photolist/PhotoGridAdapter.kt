package com.example.photoviewer.photolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.photoviewer.databinding.PhotoListFullItemBinding
import com.example.photoviewer.databinding.PhotoListItem2Binding
import com.example.photoviewer.databinding.PhotoListItemBinding
import com.example.photoviewer.repository.Photo

class PhotoGridAdapter(private val onClickListener: OnClickListener ) :
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

        return when(viewType) {
            0 -> PhotoItem1ViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
            1 -> PhotoItem2ViewHolder(PhotoListItem2Binding.inflate(LayoutInflater.from(parent.context),parent, false))
            else -> PhotoItem1ViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    class OnClickListener(val clickListener: (photo:Photo) -> Unit) {
        fun onClick(photo:Photo) = clickListener(photo)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photoDetail = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photoDetail)
        }
        holder.bind(photoDetail)
    }
}