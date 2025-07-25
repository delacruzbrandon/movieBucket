package org.movie.bucket.domain.repositories

import org.movie.bucket.domain.models.MovieListResponse
import org.movie.bucket.domain.utility.Result
import util.NetworkError

interface MovieRepository {
    suspend fun getPopularMovies(
        language: String,
        page: Int
    ): Result<MovieListResponse, NetworkError>?
}