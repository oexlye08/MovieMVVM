package id.my.okisulton.moviemvvm.ui.movie

import android.util.Log
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
import id.my.okisulton.moviemvvm.util.Constants.IMAGE_URL

/**
 * Created by Oki Sulton on 13/09/2022.
 */
class MovieAdapter (private val listener: OnItemClickListener) : PagingDataAdapter<
        MovieResponse.ResultsItem,
        MovieAdapter.MovieViewHolder
        >(COMPARATOR) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        Log.d(TAG, "onClick: $item")
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(movie: MovieResponse.ResultsItem) {
            with(binding) {
                Glide.with(itemView)
                    .load("${IMAGE_URL}${movie.backdropPath}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(moviePoster)

                tvTitle.text = movie.originalTitle
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: MovieResponse.ResultsItem)
    }

    companion object {
        private const val TAG = "MovieAdapter"
    }
}