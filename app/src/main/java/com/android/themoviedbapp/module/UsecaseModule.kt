package com.android.themoviedbapp.module

import com.android.api_service.service.*
import com.android.api_service.usecase.DiscoverPagingUsecase
import com.android.api_service.usecase.GenreUsecase
import com.android.api_service.usecase.MovieDetailVideoUsecase
import com.android.api_service.usecase.MovieReviewPagingUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UsecaseModule {

    @Provides
    fun provideGenreUsecase(genreService: GenreService) =
        GenreUsecase(genreService)

    @Provides
    fun provideDiscoverPagingUsecase(discoverService: DiscoverService) =
        DiscoverPagingUsecase(discoverService)

    @Provides
    fun provideMovieDetailVideoUsecase(movieDetailService: MovieDetailService,
                                       movieVideoService: MovieVideoService) =
        MovieDetailVideoUsecase(movieDetailService, movieVideoService)

    @Provides
    fun provideMovieReviewPagingUsecase(movieReviewService: MovieReviewService) =
        MovieReviewPagingUsecase(movieReviewService)
}