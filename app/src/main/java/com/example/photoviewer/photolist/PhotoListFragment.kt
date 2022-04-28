package com.example.photoviewer.photolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel.greet()
        return binding.root
    }
}