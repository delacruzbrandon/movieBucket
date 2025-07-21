package org.movie.bucket.presentation.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.movie.bucket.domain.handler.genreRequest
import org.movie.bucket.domain.models.Movie
import org.movie.bucket.domain.repositories.MovieRepository
import org.movie.bucket.domain.utility.LocalError
import org.movie.bucket.domain.utility.onError
import org.movie.bucket.domain.utility.onSuccess
import util.NetworkError

class HomeViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _movieList: MutableStateFlow<Array<Movie>> =
        MutableStateFlow(emptyArray())
    val movieList: StateFlow<Array<Movie>> = _movieList

    private val _genreList: MutableStateFlow<String> =
            MutableStateFlow("")
    val genreList: StateFlow<String> = _genreList

    private val _randomMovie: MutableStateFlow<Movie?> =
        MutableStateFlow(null)
    val randomMovie: StateFlow<Movie?> = _randomMovie

    private var _networkErrorMessage by mutableStateOf(NetworkError.UNKNOWN)
    private var _localErrorMessage by mutableStateOf(LocalError.UNKNOWN)

    suspend fun getPopularMovies() {
        movieRepository.getPopularMovies(
            language = "en-US",
            page = 1
        )?.onSuccess { movies ->
            _movieList.value = movies.results
        }?.onError { error ->
            _networkErrorMessage = error
        }
    }

    fun getRandomMovieGenres() {
        if (_randomMovie.value?.genres != null || _randomMovie.value?.genres?.isNotEmpty() == true)
        genreRequest(
            _randomMovie.value!!.genres
        ).onSuccess { genres ->
            _genreList.value = genres.joinToString(", ")
        }.onError { error ->
            _localErrorMessage = error
        }
    }

    fun getRandomMovie() {
        if (_movieList.value.isNotEmpty()) {
            _randomMovie.value = _movieList.value.random()
        }
    }

    fun getErrorMessage(): NetworkError {
        return _networkErrorMessage
    }
}