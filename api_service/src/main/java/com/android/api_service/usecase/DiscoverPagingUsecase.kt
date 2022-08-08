package com.android.api_service.usecase

import com.android.api_service.paging.DiscoverMoviePager
import com.android.api_service.service.DiscoverService

class DiscoverPagingUsecase(private val discoverService: DiscoverService) {
    operator fun invoke(genres: String) = DiscoverMoviePager.createPager(
        10, discoverService, genres
    ).flow
}