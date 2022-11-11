package id.my.okisulton.moviemvvm.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import id.my.okisulton.moviemvvm.data.remote.model.MoviePagingSource
import id.my.okisulton.moviemvvm.data.remote.retrofit.ApiEndpoint
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Oki Sulton on 12/09/2022.
 */
@Singleton
class MovieRepository @Inject constructor(private val apiEndpoint: ApiEndpoint) {
    fun getNowPlayingMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    apiEndpoint,
                    null
                )
            }
        ).liveData

    fun getSearchMovie(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    apiEndpoint,
                    query
                )
            }
        ).liveData
}