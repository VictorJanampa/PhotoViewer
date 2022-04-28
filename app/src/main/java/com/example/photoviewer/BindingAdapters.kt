package com.example.photoviewer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.photoviewer.photolist.PhotoGridAdapter
import com.example.photoviewer.repository.Photo

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val url = GlideUrl(
            it, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(this)
            .load(url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}
/*
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
**/