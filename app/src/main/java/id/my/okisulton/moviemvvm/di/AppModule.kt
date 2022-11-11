package id.my.okisulton.moviemvvm.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.okisulton.moviemvvm.data.remote.retrofit.ApiEndpoint
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

    private val base_url = "https://api.themoviedb.org/3/"
    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    private val gsonBuilder = GsonBuilder().apply {
        this.setLenient()
    }.create()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): ApiEndpoint =
        retrofit.create(ApiEndpoint::class.java)
}