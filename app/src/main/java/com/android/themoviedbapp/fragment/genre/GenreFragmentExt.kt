package com.android.themoviedbapp.fragment.genre

import android.view.View
import androidx.appcompat.view.ActionMode
import androidx.navigation.fragment.findNavController
import com.android.entity.genre.Genre
import com.android.ext.toggle

var actionMode: ActionMode? = null

fun GenreFragment.observeLiveData() = with(vm) {

    observeResponseData(dataGenre,
        success = {
            adapter.submitData(it)
            binding.buttonGenre.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.retryButton.visibility = View.GONE
        },
        loading = {
            binding.buttonGenre.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        },
        error = {
            binding.buttonGenre.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getGenre()
            }
        })

    selection.observe(this@observeLiveData) {
        if (it.isEmpty()) {
            binding.buttonGenre.visibility = View.GONE
        } else {
            binding.buttonGenre.visibility = View.VISIBLE
        }
    }
}

fun GenreFragment.toggleClick(genre: Genre) {
    if (vm.selection?.value?.isNotEmpty() == true) {
        adapter.toggleSelection(genre) {
            vm.selection.toggle(genre)
        }
    } else {
        findNavController().navigate(
            GenreFragmentDirections.genreFragmentToDiscoverFragment(
                genre.id.toString()
            )
        )
    }
}

fun GenreFragment.starActionMode(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selection.toggle(genre)
    }
}