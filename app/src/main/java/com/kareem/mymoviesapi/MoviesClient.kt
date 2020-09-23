package com.kareem.mymoviesapi

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesClient {
    val baseUrl = "https://api.themoviedb.org/3/"
    val service: ApiService

    init{
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ApiService::class.java)
    }



    fun fetchANimatedMovies(page:Int = 1, onSuccess: (moviesList: ArrayList<MovieModel>) -> Unit, onError: () -> Unit){

        service.getAnimationMovies(page = page).enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onError.invoke()
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if(response.body() != null)
                {
                    onSuccess.invoke(response.body()!!.movies)

                }
                else
                {
                    onError.invoke()
                }
            }
        })

    }




}
