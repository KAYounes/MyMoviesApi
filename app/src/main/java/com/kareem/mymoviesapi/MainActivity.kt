package com.kareem.mymoviesapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CardClickListener {
    var currentPageNumber = 1
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var linLayMan:  LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ab: ActionBar? = getSupportActionBar()
        if (ab != null) {
            ab.hide()
        }

        moviesAdapter = MoviesAdapter(mutableListOf(),this)
        linLayMan = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        movieListRecyclerView.adapter = moviesAdapter
        movieListRecyclerView.layoutManager = linLayMan
        getAnimationMovies()

//        movieListRecyclerView = MoviesAdapter()

//        movieListRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    fun getAnimationMovies(){
        MoviesClient.fetchANimatedMovies(currentPageNumber, ::onAnimatedMoviesFetched, ::onError)
    }

    private fun onError() {
        Toast.makeText(this,"E R R O R occurred while calling the API", Toast.LENGTH_SHORT).show()

    }

    private fun onAnimatedMoviesFetched(arrayList: ArrayList<MovieModel>) {
        moviesAdapter.nextPage(arrayList)
        attachOnScrollListener()
    }

    fun attachOnScrollListener(){
        movieListRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = linLayMan.itemCount
                val visibleItemsCount = linLayMan.childCount
                val firstVisibleItem = linLayMan.findLastVisibleItemPosition()

                if(firstVisibleItem + visibleItemsCount >= totalItems/2) {
                    movieListRecyclerView.removeOnScrollListener(this)
                    currentPageNumber++
                    getAnimationMovies()
                }
            }
        })
    }

//        MoviesClient.service.getAnimationMovies().enqueue(object : Callback<MoviesResponse>{
//            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//                Toast.makeText(applicationContext,"Error Calling API", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(
//                call: Call<MoviesResponse>,
//                response: Response<MoviesResponse>
//            ) {
//                if(response.body() != null){
//                    var cardList = ArrayList<MovieModel>()
//                    Log.d("anim", "Movies: ${response.body()?.movies}")
//                }else{
//                    Log.d("anim", "nullnull nullnull null KAREEEEEEEEEEm")
//                }
//                movieListRecyclerView.adapter = MoviesAdapter(response.body()?.movies)
//                movieListRecyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
//            }
//
//        })
//
//    }

    override fun onCardClick(movie: MovieModel, position: Int) {
//        println(card.cardTitle)
//        Toast.makeText(this, "Image Title:\n" + card.cardTitle, Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "Image By:\n" + card.cardOwner, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MovieDetails::class.java)
        intent.putExtra("movieTitle", movie.movieTitle)
        intent.putExtra("movieOverview", movie.movieOverview)
        intent.putExtra("moviePoster", movie.moviePoster)
        intent.putExtra("movieRating", (movie.movieRating*10).toInt().toString())
//        intent.putExtra("movieRating", movie.movieRating.toString())
        intent.putExtra("movieYear", movie.movieYear)
        intent.putExtra("movieGenres", movie.movieGenres)
        intent.putExtra("movieDuration", movie.movieDuration)
        startActivity(intent)
    }

}

