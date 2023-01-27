package id.my.okisulton.moviemvvm.ui.favorite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.okisulton.moviemvvm.data.local.repository.FavoriteMovieRepository
import javax.inject.Inject

/**
 * Created by Oki Sulton on 27/01/2023.
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : ViewModel() {
    val movies = favoriteMovieRepository.getFavoriteMovies()
}