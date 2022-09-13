package id.my.okisulton.moviemvvm.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.my.okisulton.moviemvvm.R
import id.my.okisulton.moviemvvm.data.remote.model.MovieResponse
import id.my.okisulton.moviemvvm.databinding.ItemMovieBinding
import id.my.okisulton.moviemvvm.util.Constants.COMPARATOR

/**
 * Created by Oki Sulton on 13/09/2022.
 */
class MovieAdapter: PagingDataAdapter<MovieResponse.ResultsItem, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: MovieResponse.ResultsItem) {
                with(binding) {
                    Glide.with(itemView)
                        .load("${movie.baseUrl}${movie.posterPath}")
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(moviePoster)

                    tvTitle.text = movie.originalTitle
                }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MovieViewHolder (
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}