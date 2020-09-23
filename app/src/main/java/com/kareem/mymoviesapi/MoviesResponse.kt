package com.kareem.mymoviesapi

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results") val movies : ArrayList<MovieModel>,
    @SerializedName("page") val currentPage : Int,
    @SerializedName("total_pages") val totalPages: Int

)