package com.example.photoviewer.ui.photolist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.photoviewer.R
import com.example.photoviewer.databinding.PhotoListFragmentBinding
import com.example.photoviewer.ui.photodetails.PhotoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject


@AndroidEntryPoint
class PhotoListFragment : Fragment() {

    private val viewModel by viewModels<PhotoListViewModel>()
    //private val viewModel: PhotoListViewModel by viewModel()
    private lateinit var binding: PhotoListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhotoListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPhoto(it)
        })

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        viewModel.photosRx.subscribe { list ->
            Log.i("Andrio", "setPhotos")
            (binding.photosGrid.adapter as PhotoGridAdapter).submitList(list)
        }.also { disposable -> viewModel.disposables.add(disposable) }

        viewModel.navigateToSelectedItemRx.subscribe ({ photo ->
            Log.i("Andrio","showPhoto")
            this.findNavController()
                .navigate(PhotoListFragmentDirections.actionPhotoListToPhotoDetailsFragment(photo))
            }, {  }).also { disposable -> viewModel.disposables.add(disposable) }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_database -> viewModel.clearPhotos()
        }
        return true
    }
}