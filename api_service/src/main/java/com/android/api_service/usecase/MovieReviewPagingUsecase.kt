package com.android.api_service.usecase

import com.android.api_service.paging.MovieReviewPager
import com.android.api_service.service.MovieReviewService

class MovieReviewPagingUsecase(
    private val movieReviewService: MovieReviewService
) {
    operator fun invoke(movieId: Int) =
        MovieReviewPager.createPager(10, movieReviewService, movieId).flow
}