package com.android.themoviedbapp.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.android.api_service.usecase.MovieDetailVideoUsecase
import com.android.api_service.usecase.MovieReviewPagingUsecase
import com.android.common.AppResponse
import com.android.common.BaseViewModel
import com.android.entity.movie_detail.MovieDetailsResponse
import com.android.entity.movie_reviews.Review
import com.android.entity.movie_video.MovieVideoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    application: Application,
    val movieDetailVideoUsecase: MovieDetailVideoUsecase,
    val movieReviewPagingUsecase: MovieReviewPagingUsecase
): BaseViewModel(application) {

    val movieDetailData =
        MutableLiveData<AppResponse<Pair<MovieDetailsResponse, MovieVideoResponse>>>()

    val movieReviewData = MutableLiveData<PagingData<Review>>()

    fun getMovieDetail(movieId: Int){

        viewModelScope.launch {
//            delay(1000)
            movieDetailVideoUsecase(movieId).collect{
                movieDetailData.postValue(it)
            }

            movieReviewPagingUsecase(movieId).collect{
                movieReviewData.postValue(it)
            }
        }

    }
}