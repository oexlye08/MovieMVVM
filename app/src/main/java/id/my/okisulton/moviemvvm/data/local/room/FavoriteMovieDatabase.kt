package id.my.okisulton.moviemvvm.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.my.okisulton.moviemvvm.data.local.model.FavoriteMovie

/**
 * Created by Oki Sulton on 26/01/2023.
 */
@Database(
    entities = [FavoriteMovie::class],
    version = 1
)
abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun getFavoriteMovieDao(): FavoriteMovieDao
}