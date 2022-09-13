package id.my.okisulton.moviemvvm.data.remote.retrofit

import android.icu.text.Transliterator
import android.telecom.Call
import id.my.okisulton.moviemvvm.BuildConfig
import id.my.okisulton.moviemvvm.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by Oki Sulton on 07/09/2022.
 */
interface ApiEndpoint {
    companion object {
        const val API_KEY: String = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies (
//        @Query("page") position: Int
        @QueryMap parameter: HashMap<String, String>
    ): MovieResponse

}