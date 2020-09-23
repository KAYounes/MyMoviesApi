package com.kareem.mymoviesapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_card.view.*

class MoviesAdapter(private var moviesArrayList: MutableList<MovieModel>?, private var clickListener: CardClickListener): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(cardView: View): RecyclerView.ViewHolder(cardView){

        fun onBind(movie: MovieModel, action:CardClickListener){
            itemView.moveTitle.text = movie.movieTitle
            itemView.movieOverview.text = movie.movieOverview
            itemView.movieYear.text = movie.movieYear.split("-")[0]
            itemView.movieGenres.text = movie.movieGenres
            itemView.movieDuration.text = movie.movieDuration
            itemView.movieRating.text = (movie.movieRating*10).toInt().toString() + "%"

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/${movie.moviePoster}")
                .into(itemView.moviePoster)

            itemView.setOnClickListener{
                action.onCardClick(movie, adapterPosition)
            }
        }



        fun init(card: MovieModel, action:CardClickListener){

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false))

    override fun getItemCount(): Int = moviesArrayList!!.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = moviesArrayList!![position]
        holder.onBind(movie, clickListener)
    }

     fun nextPage(movies: ArrayList<MovieModel>){
         this.moviesArrayList?.addAll(movies)
         notifyItemRangeChanged(this.moviesArrayList!!.size, (moviesArrayList!!.size) -1)
     }

}

interface CardClickListener{
    fun onCardClick(card: MovieModel, position: Int)
}