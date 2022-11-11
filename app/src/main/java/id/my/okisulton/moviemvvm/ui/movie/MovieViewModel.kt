package id.my.okisulton.moviemvvm.ui.movie

import android.os.Bundle
import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.okisulton.moviemvvm.data.remote.repository.MovieRepository
import id.my.okisulton.moviemvvm.util.Constants.CURRENT_QUERY
import id.my.okisulton.moviemvvm.util.Constants.EMPTY_QUERY
import javax.inject.Inject

/**
 * Created by Oki Sulton on 12/09/2022.
 */
//@HiltViewModel
//class MovieViewModel @Inject constructor(

class MovieViewModel @AssistedInject constructor(
    private val repository: MovieRepository
    ,
    @Assisted handle: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface MovieFactory {
        fun crate(handle: SavedStateHandle) : MovieViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: MovieFactory,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory = object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return assistedFactory.crate(handle) as T
            }

        }
    }

    private val currentQuery = handle.getLiveData(CURRENT_QUERY, EMPTY_QUERY)

//    val movies = repository.getNowPlayingMovies()

    val movies = currentQuery.switchMap { query ->
        if (query.isNotEmpty()) {
            repository.getSearchMovie(query)
        } else {
            repository.getNowPlayingMovies().cachedIn(viewModelScope)
        }
    }

    fun searchMovie(query: String) {
        currentQuery.value = query
    }

}