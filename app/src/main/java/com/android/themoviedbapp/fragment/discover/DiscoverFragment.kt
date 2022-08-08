package com.android.themoviedbapp.fragment.discover

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.common.BaseFragment
import com.android.themoviedbapp.R
import com.android.themoviedbapp.databinding.DiscoverListLayoutBinding
import com.android.themoviedbapp.view_model.DiscoverViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment: BaseFragment<DiscoverViewModel, DiscoverListLayoutBinding>() {
    override val vm: DiscoverViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.discover_list_layout

    val discoverArgs: DiscoverFragmentArgs by navArgs()
    val adapter = DiscoverAdapter{
        findNavController().navigate(
            DiscoverFragmentDirections.discoverFragmentToMovieDetailFragment(it)
        )
    }

    override fun initBinding(binding: DiscoverListLayoutBinding) {
        super.initBinding(binding)
        binding.rvDiscover.adapter = adapter

        observeLiveData()
    }
}