package org.movie.bucket.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.movie.bucket.domain.models.MovieListResponse
import org.movie.bucket.domain.repositories.MovieRepository
import org.movie.bucket.domain.utility.Constants.API_KEY
import org.movie.bucket.domain.utility.Constants.APP_JSON
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
        val response =
            try {
                httpClient.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = BASE_URL
                        path("3/movie/popular")
                    }
                    parameters {
                        append("language", language)
                        append("page", page.toString())
                    }
                    headers {
                        append(HttpHeaders.Accept, APP_JSON)
                        append(HttpHeaders.Authorization, API_KEY)
                    }
                }
            } catch (e: UnresolvedAddressException) {
                return Result.Error(NetworkError.NO_INTERNET)
            } catch (e: SerializationException) {
                return Result.Error(NetworkError.SERIALIZATION)
            }
        return when (response.status.value) {
            in 200..299 -> {
                val movieList = response.body<MovieListResponse>()
                Result.Success(movieList)
            }

            else -> {
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}