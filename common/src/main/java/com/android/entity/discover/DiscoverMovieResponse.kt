package com.android.entity.discover


import com.android.entity.discover.DiscoverMovie
import com.google.gson.annotations.SerializedName

data class DiscoverMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val discoverMovies: ArrayList<DiscoverMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)