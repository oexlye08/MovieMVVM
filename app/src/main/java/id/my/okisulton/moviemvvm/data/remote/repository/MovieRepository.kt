package id.my.okisulton.moviemvvm.data.remote.repository

import id.my.okisulton.moviemvvm.data.remote.retrofit.ApiEndpoint
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Oki Sulton on 12/09/2022.
 */
@Singleton
class MovieRepository @Inject constructor(private val apiEndpoint: ApiEndpoint) {
}