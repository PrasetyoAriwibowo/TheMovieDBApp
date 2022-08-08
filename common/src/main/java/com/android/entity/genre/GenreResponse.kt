package com.android.entity.genre


import com.android.entity.genre.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: ArrayList<Genre>
)