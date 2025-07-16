package org.movie.bucket.presentation.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.movie.bucket.domain.models.Movie
import org.movie.bucket.domain.repositories.MovieRepository
import org.movie.bucket.domain.utility.onError
import org.movie.bucket.domain.utility.onSuccess
import util.NetworkError
import kotlin.collections.emptyList

class HomeViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _movieList: MutableStateFlow<Array<Movie>> =
        MutableStateFlow(emptyArray())
    val movieList: StateFlow<Array<Movie>> = _movieList

    private val _randomMovie: MutableStateFlow<Movie?> =
        MutableStateFlow(null)
    val randomMovie: StateFlow<Movie?> = _randomMovie

    private var _errorMessage by mutableStateOf(NetworkError.UNKNOWN)

    suspend fun getPopularMovies() {
        movieRepository.getPopularMovies(
            language = "en-US",
            page = 1
        )?.onSuccess { movies ->
            _movieList.value = movies.results
        }?.onError { error ->
            _errorMessage = error
        }
    }

    fun getRandomMovie() {
        if (_movieList.value.isNotEmpty()) {
            _randomMovie.value = _movieList.value.random()
        }
    }

    fun getErrorMessage(): NetworkError {
        return _errorMessage
    }
}