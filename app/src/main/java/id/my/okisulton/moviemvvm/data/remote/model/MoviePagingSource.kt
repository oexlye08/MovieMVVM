package id.my.okisulton.moviemvvm.data.remote.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.my.okisulton.moviemvvm.data.remote.retrofit.ApiEndpoint
import id.my.okisulton.moviemvvm.util.Constants.STARTING_PAGING_INDEX
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Oki Sulton on 12/09/2022.
 */
class MoviePagingSource(
    private val apiEndpoint: ApiEndpoint,
    private val query: String?
) : PagingSource<Int, MovieResponse.ResultsItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponse.ResultsItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse.ResultsItem> {
        return try {
            val parameters = HashMap<String, String>()

            val position: Int = params.key ?: STARTING_PAGING_INDEX
            parameters["page"] = position.toString()
//            val response = apiEndpoint.getNowPlayingMovies(parameters)
            val response = if (query!=null) {
                parameters["query"] = query
                apiEndpoint.searchMovie(parameters)
            } else {
                apiEndpoint.getNowPlayingMovies(parameters)
            }
            val movies = response.results

            Log.d(TAG, "load: $movies")

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGING_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
    
    companion object {
        private const val TAG = "MoviePagingSource"
    }
}