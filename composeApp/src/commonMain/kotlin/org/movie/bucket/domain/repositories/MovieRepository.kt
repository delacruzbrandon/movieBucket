package org.movie.bucket.domain.repositories

import org.movie.bucket.domain.models.MovieListResponse

interface MovieRepository {
    fun getRandomMovies(page: Int): MovieListResponse?
}