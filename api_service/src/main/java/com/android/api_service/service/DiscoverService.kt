package com.android.api_service.service

import com.android.api_service.Constants.API_KEY
import com.android.entity.discover.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {
    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genres") genre: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<DiscoverMovieResponse>
}