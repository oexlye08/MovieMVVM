package id.my.okisulton.moviemvvm.ui.movie

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.okisulton.moviemvvm.data.remote.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by Oki Sulton on 12/09/2022.
 */
@HiltViewModel
class MovieViewModel @Inject constructor (
    private val repository: MovieRepository
) : ViewModel() {
    val movies = repository.getNowPlayingMovies()
}