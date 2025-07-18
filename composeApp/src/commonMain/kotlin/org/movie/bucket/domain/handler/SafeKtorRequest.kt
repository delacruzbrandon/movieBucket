package org.movie.bucket.domain.handler

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.movie.bucket.domain.utility.Constants.API_KEY
import org.movie.bucket.domain.utility.Constants.APP_JSON
import org.movie.bucket.domain.utility.Result
import util.NetworkError

suspend inline fun <reified T> safeKtorRequest(httpClient: HttpClient, requestBuilder: HttpRequestBuilder.() -> Unit): Result<T, NetworkError>? {
    val response =
        try {
            httpClient.get {
                headers {
                    append(HttpHeaders.Accept, APP_JSON)
                    append(HttpHeaders.Authorization, API_KEY)
                }
                requestBuilder()
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
    return when (response.status.value) {
        in 200..299 -> {
            Result.Success(response.body<T>())
        }
        401 -> Result.Error(NetworkError.UNAUTHORIZED)
        409 -> Result.Error(NetworkError.CONFLICT)
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> {
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}