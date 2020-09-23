package com.kareem.mymoviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.view.*
import kotlinx.android.synthetic.main.movie_card.view.*

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        var ab: ActionBar? = getSupportActionBar()
        if (ab != null) {
            ab.hide()
        }

        moveTitleDetails.text = (getIntent().getStringExtra("movieTitle"))
        movieOverviewDetails.text = getIntent().getStringExtra("movieOverview")
//        moviePosterWideCardView.moviePosterWide.setImageResource(getIntent().getStringExtra("moviePoster")!!.toInt())

        Glide.with(moviePosterWideCardView.moviePosterWide)
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/${getIntent().getStringExtra("moviePoster")}")
            .into(moviePosterWideCardView.moviePosterWide)

//        movieRatingDetails.text = (((getIntent().getStringExtra("movieRating"))?.toDouble())?.times(10))?.toInt().toString() + "%"
        movieRatingDetails.text = (getIntent().getStringExtra("movieRating")) + "%"
        movieYearDetails.text = (getIntent().getStringExtra("movieYear"))?.split("-")?.get(0)
        movieGenresDetails.text = getIntent().getStringExtra("movieGenres")
        movieDurationDetails.text = getIntent().getStringExtra("movieDuration")
    }
}