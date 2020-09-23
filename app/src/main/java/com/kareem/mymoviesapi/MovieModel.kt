package com.kareem.mymoviesapi

import com.google.gson.annotations.SerializedName

data class MovieModel(

    @SerializedName("id") val id: Long,
    @SerializedName("title") val movieTitle: String,
    @SerializedName("overview") val movieOverview: String,
    @SerializedName("poster_path") val moviePoster: String,
    @SerializedName("vote_average") val movieRating: Float,
    @SerializedName("release_date") val movieYear: String,
    @SerializedName("genres") val movieGenres: String,
    @SerializedName("runtime") val movieDuration: String

)