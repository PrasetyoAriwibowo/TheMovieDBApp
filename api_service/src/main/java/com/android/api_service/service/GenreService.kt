package com.android.api_service.service

import com.android.api_service.Constants.API_KEY
import com.android.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenreData(
        @Query("api_key") api_key : String = API_KEY
    ): Response<GenreResponse>
}