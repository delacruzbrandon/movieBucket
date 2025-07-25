package org.movie.bucket.core.di

import io.ktor.client.HttpClient
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.movie.bucket.data.network.KtorMovieClient
import org.movie.bucket.domain.repositories.MovieRepository
import org.movie.bucket.presentation.features.home.HomeViewModel

@Module
class MovieModule {
    @Factory(binds = [MovieRepository::class])
    fun provideMovieRepository(@NoAuthHttpClient httpClient: HttpClient) = KtorMovieClient(httpClient)

    @KoinViewModel
//    @Movie
    fun provideHomeViewModel(movieRepository: MovieRepository) = HomeViewModel(movieRepository)
}

//@Named
//annotation class Movie