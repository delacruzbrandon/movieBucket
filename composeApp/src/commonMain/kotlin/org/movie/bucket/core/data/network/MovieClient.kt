package org.movie.bucket.core.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.movie.bucket.core.data.models.Movie
import org.movie.bucket.core.data.models.MovieList
import org.movie.bucket.utility.Result
import util.NetworkError

class MovieClient(
    private val httpClient: HttpClient
) {
    val apiKey = "Bearer *****"
/*
 REMOVED API KEY. Just add it again from your account from link
 https://developer.themoviedb.org/reference/movie-popular-list
 */
    val appJson = "application/json"
    suspend fun getPopularMovies(): Result<List<Movie>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://api.themoviedb.org/3/movie/popular"
            ) {
                headers {
                    append(HttpHeaders.Accept, appJson)
                    append(HttpHeaders.Authorization, apiKey)
                }
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val movieList = response.body<MovieList>()
                Result.Success(movieList.results)
            }

            else -> {
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}