package id.my.okisulton.moviemvvm.util

import androidx.recyclerview.widget.DiffUtil
import id.my.okisulton.moviemvvm.data.remote.model.MovieResponse

/**
 * Created by Oki Sulton on 12/09/2022.
 */
object Constants {
    const val STARTING_PAGING_INDEX = 1
    const val CURRENT_QUERY = "current_query"
    const val EMPTY_QUERY = ""

    val COMPARATOR = object : DiffUtil.ItemCallback<MovieResponse.ResultsItem>() {
        override fun areItemsTheSame(
            oldItem: MovieResponse.ResultsItem,
            newItem: MovieResponse.ResultsItem
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieResponse.ResultsItem,
            newItem: MovieResponse.ResultsItem
        ): Boolean =
            oldItem == newItem

    }
}