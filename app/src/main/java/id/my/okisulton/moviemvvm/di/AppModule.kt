package id.my.okisulton.moviemvvm.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.my.okisulton.moviemvvm.data.local.room.FavoriteMovieDatabase
import id.my.okisulton.moviemvvm.data.remote.retrofit.ApiEndpoint
import id.my.okisulton.moviemvvm.util.Constants.ROOM_DB_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Oki Sulton on 12/09/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val base_url = "https://api.themoviedb.org/3/"
    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    private val gsonBuilder = GsonBuilder().apply {
        this.setLenient()
    }.create()


    /*
    Langkah 1
    Membuat rerofit object
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()

    /*
    Langkah 2
    Inject class endpoint
     */
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): ApiEndpoint =
        retrofit.create(ApiEndpoint::class.java)

    // DI Room
    @Provides
    @Singleton
    fun provideFavMovieDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteMovieDatabase::class.java,
        ROOM_DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideFavMovieDao(db: FavoriteMovieDatabase) = db.getFavoriteMovieDao()
}