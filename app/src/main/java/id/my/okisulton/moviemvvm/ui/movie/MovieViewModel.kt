package id.my.okisulton.moviemvvm.ui.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import id.my.okisulton.moviemvvm.data.remote.repository.MovieRepository
import id.my.okisulton.moviemvvm.util.Constants.CURRENT_QUERY
import id.my.okisulton.moviemvvm.util.Constants.EMPTY_QUERY

/**
 * Created by Oki Sulton on 12/09/2022.
 */
//@HiltViewModel
//class MovieViewModel @Inject constructor(
//@HiltViewModel
class MovieViewModel @AssistedInject constructor(
    private val repository: MovieRepository,
    @Assisted handle: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface MovieFactory {
        fun crate(handle: SavedStateHandle) : MovieViewModel
    }




    private val currentQuery = handle.getLiveData(CURRENT_QUERY, EMPTY_QUERY)

//    val movies = repository.getNowPlayingMovies()

    val movies = currentQuery.switchMap { query ->
        if (query.isNotEmpty()) {
            repository.getSearchMovie(query).cachedIn(viewModelScope)
        } else {
            repository.getNowPlayingMovies().cachedIn(viewModelScope)
        }
    }

    fun searchMovie(query: String) {
        currentQuery.value = query
    }

}