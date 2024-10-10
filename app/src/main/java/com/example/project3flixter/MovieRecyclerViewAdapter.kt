package com.example.project3flixter
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.project3flixter.OnListFragmentInteractionListener

class MovieRecyclerViewAdapter(private val movies: List<Movie>,private val mListener: OnListFragmentInteractionListener?): RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("Recycler","onCreateViewTest")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies, parent, false)
        return MovieViewHolder(view)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("Recycler","onBindViewtest")
        val movie = movies[position]
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/original/" + movie.poster.toString())
            .centerInside()
            .into(holder.mMovieImage)/*
        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
        holder.mMovieButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.url))
            startActivity(it.context, browserIntent, null)
        }*/
    }
    inner class MovieViewHolder(val mView: View): RecyclerView.ViewHolder(mView) {
        //Log.d("Recycler","Innerclass")
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.title) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.poster) as ImageView
        val mMovieDescription: TextView = mView.findViewById<View>(R.id.description) as TextView
        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
        }
    }
    override fun getItemCount(): Int {
        return movies.size
    }
}