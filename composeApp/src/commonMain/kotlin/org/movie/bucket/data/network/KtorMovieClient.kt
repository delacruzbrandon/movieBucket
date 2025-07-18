package org.movie.bucket.data.network

import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path
import org.movie.bucket.domain.handler.safeKtorRequest
import org.movie.bucket.domain.models.Genre
import org.movie.bucket.domain.models.MovieListResponse
import org.movie.bucket.domain.repositories.MovieRepository
import org.movie.bucket.domain.utility.Constants.BASE_URL
import org.movie.bucket.domain.utility.Result
import util.NetworkError

class KtorMovieClient(
    private val httpClient: HttpClient
): MovieRepository {

    override suspend fun getPopularMovies(
        language: String,
        page: Int
    ): Result<MovieListResponse, NetworkError>? {
        return safeKtorRequest(
            httpClient
        ) {
//            httpClient.get {}
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    path("3/movie/popular")
                }
                parameters {
                    append("language", language)
                    append("page", page.toString())
                }
        }
    }

    override suspend fun getGenres(language: String?): Result<List<Genre>, NetworkError>? {
        return safeKtorRequest(
            httpClient
        ) {
//            httpClient.get {}
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("3/genre/movie/list")
            }
            parameters {
                append("language", language ?: "en")
            }
        }
    }
}