package id.my.okisulton.moviemvvm.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.okisulton.moviemvvm.data.local.model.FavoriteMovie
import id.my.okisulton.moviemvvm.data.local.repository.FavoriteMovieRepository
import id.my.okisulton.moviemvvm.data.remote.model.MovieMove
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Oki Sulton on 26/01/2023.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: FavoriteMovieRepository,
) : ViewModel() {

    fun addToFavorite(movie: MovieMove) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
                FavoriteMovie(
                    backdropPath = movie.backdropPath.toString(),
                    overview = movie.overview.toString(),
                    originalTitle = movie.originalTitle.toString(),
                    idMovie = movie.id.toString(),
                    title = movie.title.toString(),
                    posterPath = movie.posterPath
                )
            )
        }
    }

    suspend fun checkMovie(id: String) = repository.checkMovie(id)

    fun removeFromFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(id)
        }
    }
}