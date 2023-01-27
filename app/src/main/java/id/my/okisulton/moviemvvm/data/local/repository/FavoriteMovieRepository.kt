package id.my.okisulton.moviemvvm.data.local.repository

import id.my.okisulton.moviemvvm.data.local.model.FavoriteMovie
import id.my.okisulton.moviemvvm.data.local.room.FavoriteMovieDao
import javax.inject.Inject

/**
 * Created by Oki Sulton on 26/01/2023.
 */

class FavoriteMovieRepository @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie) =
        favoriteMovieDao.addToFavorite(favoriteMovie)

    fun getFavoriteMovies() = favoriteMovieDao.getFavoriteMovie()
    suspend fun checkMovie(id: String) = favoriteMovieDao.checkMovie(id)
    suspend fun deleteFavorite(id: String) {
        favoriteMovieDao.removeFromFavorite(id)
    }
}