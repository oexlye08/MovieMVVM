package id.my.okisulton.moviemvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import id.my.okisulton.moviemvvm.ui.movie.MovieViewModel
import javax.inject.Inject

/**
 * Created by Oki Sulton on 07/09/2022.
 */
@HiltAndroidApp
class MovieApplication : Application() {
//    @Inject lateinit var movieFactory: MovieViewModel.MovieFactory
}