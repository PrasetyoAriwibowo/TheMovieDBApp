package com.android.themoviedbapp.fragment.movie_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.common.BaseFragment
import com.android.themoviedbapp.R
import com.android.themoviedbapp.databinding.MovieDetailLayoutBinding
import com.android.themoviedbapp.fragment.movie_review.MovieReviewAdapter
import com.android.themoviedbapp.view_model.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment: BaseFragment<MovieDetailViewModel, MovieDetailLayoutBinding>() {
    override val vm: MovieDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.movie_detail_layout

    val movieDetailArgs: MovieDetailFragmentArgs by navArgs()
    val adapter = MovieReviewAdapter()

    override fun initBinding(binding: MovieDetailLayoutBinding) {
        super.initBinding(binding)

        vm.getMovieDetail(movieDetailArgs.discover)
        observeLiveData()

        binding.rvReview.adapter = adapter
    }
}