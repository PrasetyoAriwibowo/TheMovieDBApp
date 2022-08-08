package com.android.api_service.service

import com.android.api_service.Constants.API_KEY
import com.android.entity.movie_video.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieVideoService {
    @GET("movie/{id}/videos")
    suspend fun getMovieVideo(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieVideoResponse>
}