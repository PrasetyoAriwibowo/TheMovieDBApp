package com.android.themoviedbapp.fragment.genre

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.common.BaseFragment
import com.android.themoviedbapp.R
import com.android.themoviedbapp.databinding.GenreListLayoutBinding
import com.android.themoviedbapp.view_model.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment: BaseFragment<GenreViewModel, GenreListLayoutBinding>() {
    override val vm: GenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.genre_list_layout

    val adapter = GenreAdapter(::starActionMode, {
        vm.selection.value.orEmpty()
    }) {
        toggleClick(it)
    }

    override fun initBinding(binding: GenreListLayoutBinding) {
        super.initBinding(binding)

        binding.rvGenre.adapter = adapter
        observeLiveData()

        binding.buttonGenre.setOnClickListener {
            findNavController().navigate(
                GenreFragmentDirections.genreFragmentToDiscoverFragment(
                    vm.selection.value.orEmpty().map { it.id }.joinToString(
                        separator = ","
                    )
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        actionMode = null
    }
}