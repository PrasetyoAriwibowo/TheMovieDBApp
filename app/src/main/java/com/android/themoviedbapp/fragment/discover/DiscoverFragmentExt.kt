package com.android.themoviedbapp.fragment.discover

import android.view.View
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverFragment.observeLiveData() = with(vm){
    vm.getDiscover(discoverArgs.genre)

    vm.discoverDataPaging.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    val loadStateAdapterDiscover = DiscoverPagingStateAdapter(getDiscoverData = {
        vm.getDiscover(discoverArgs.genre)
    })

    adapter.addLoadStateListener {
        if (it.refresh is LoadState.Error || it.append is LoadState.Error ||
            it.prepend is LoadState.Error
        ) {
            binding.retryButton.visibility = View.VISIBLE
            binding.rvDiscover.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
        } else if (it.refresh is LoadState.Loading) {
            binding.retryButton.visibility = View.GONE
            binding.rvDiscover.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

        } else if (it.refresh is LoadState.NotLoading) {
            binding.retryButton.visibility = View.GONE
            binding.rvDiscover.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    binding.retryButton.setOnClickListener {
        adapter.retry()
    }

    binding.rvDiscover.adapter = adapter.withLoadStateFooter(loadStateAdapterDiscover)
}