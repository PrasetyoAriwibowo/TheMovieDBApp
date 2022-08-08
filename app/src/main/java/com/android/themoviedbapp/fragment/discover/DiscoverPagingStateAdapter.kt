package com.android.themoviedbapp.fragment.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedbapp.databinding.DiscoverPagingStateLayoutBinding

class DiscoverPagingStateAdapter(val getDiscoverData: () -> Unit) :
    LoadStateAdapter<DiscoverPagingStateAdapter.DiscoverStateViewHolder>() {

    inner class DiscoverStateViewHolder(
        private val binding: DiscoverPagingStateLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Error -> {
                    binding.loadingProgress.visibility = View.GONE
                }
                is LoadState.Loading -> {
                    binding.loadingProgress.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.GONE
                }
                is LoadState.NotLoading -> {
                    binding.loadingProgress.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                }
            }
        }
    }

    override fun onBindViewHolder(holder: DiscoverStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DiscoverStateViewHolder =
        DiscoverStateViewHolder(
            DiscoverPagingStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            this.bind(loadState)
        }
}