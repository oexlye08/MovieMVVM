package id.my.okisulton.moviemvvm.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.savedstate.SavedStateRegistryOwner
import id.my.okisulton.moviemvvm.data.remote.model.MovieResponse
import id.my.okisulton.moviemvvm.ui.movie.MovieViewModel

/**
 * Created by Oki Sulton on 12/09/2022.
 */
object Constants {
    const val STARTING_PAGING_INDEX = 1
    const val CURRENT_QUERY = "current_query"
    const val EMPTY_QUERY = ""
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val ROOM_DB_NAME = "movie_db"

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

    @Suppress("UNCHECKED_CAST")
    fun movieFactory(
        assistedFactory: MovieViewModel.MovieFactory,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null,
    ): AbstractSavedStateViewModelFactory =
        object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return assistedFactory.crate(handle) as T
            }
        }
}