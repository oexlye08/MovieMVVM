package id.my.okisulton.moviemvvm.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.my.okisulton.moviemvvm.data.local.model.FavoriteMovie

/**
 * Created by Oki Sulton on 26/01/2023.
 */
@Dao
interface FavoriteMovieDao {
    @Insert
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovie(): LiveData<List<FavoriteMovie>>

    @Query("SELECT count(*) FROM favorite_movies WHERE favorite_movies.idMovie = :id")
    suspend fun checkMovie(id: String): Int

    @Query("DELETE FROM favorite_movies WHERE favorite_movies.idMovie = :id")
    suspend fun removeFromFavorite(id: String): Int
}