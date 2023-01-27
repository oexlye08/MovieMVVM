package id.my.okisulton.moviemvvm.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * Created by Oki Sulton on 26/01/2023.
 */
@Entity(tableName = "favorite_movies")
@Parcelize
class FavoriteMovie(
    val backdropPath: String,
    val overview: String,
    val originalTitle: String,
    var idMovie: String,
    val title: String,
    val posterPath: String?
) : Serializable, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}