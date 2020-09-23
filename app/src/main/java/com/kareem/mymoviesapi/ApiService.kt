package com.kareem.mymoviesapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")

    fun getAnimationMovies(@Query("api_key") apiKEy: String = "92568f5f424951634746366e3c0750ed",
                           @Query("page") page: Int = 1,
                           @Query("with_genres") genre: String = "16"
    ): Call<MoviesResponse>


}