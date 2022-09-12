package id.my.okisulton.moviemvvm.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import id.my.okisulton.moviemvvm.data.remote.repository.MovieRepository

/**
 * Created by Oki Sulton on 12/09/2022.
 */
class MovieViewModel @ViewModelInject constructor (private val repository: MovieRepository) : ViewModel() {
}