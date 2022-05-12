package com.example.photoviewer.ui.photodetails


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.photoviewer.databinding.PhotoDetailsFragmentBinding
import com.example.photoviewer.ui.photolist.PhotoGridAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PhotoDetailsFragment : Fragment() {

    private val viewModel: PhotoDetailsViewModel by viewModel {parametersOf(PhotoDetailsFragmentArgs.fromBundle(requireArguments()).selectedPhoto)}
    private lateinit var binding: PhotoDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = PhotoDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.photosGrid.adapter = PhotoDetailsAdapter()

        viewModel.photosRx.subscribe { list ->
            Log.i("Andrio", "setPhotos: OnPhotoDetails")
            (binding.photosGrid.adapter as PhotoDetailsAdapter).submitList(list)
        }.also { disposable -> viewModel.disposables.add(disposable) }

        PagerSnapHelper().attachToRecyclerView(binding.photosGrid)
        binding.photosGrid.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.photosGrid.post {
            binding.photosGrid.scrollToPosition(viewModel.getPosition())
        }

        return binding.root
    }
}