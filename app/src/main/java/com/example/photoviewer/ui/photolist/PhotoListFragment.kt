package com.example.photoviewer.ui.photolist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.photoviewer.R
import com.example.photoviewer.databinding.PhotoListFragmentBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoListFragment : Fragment() {

    private val viewModel: PhotoListViewModel by viewModel()
    private lateinit var binding: PhotoListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhotoListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPhoto(it)
        })

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = viewModel.refresh()
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(PhotoListFragmentDirections.actionPhotoListToPhotoDetailsFragment(it))
                viewModel.displayPhotoComplete()
            }
        }

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