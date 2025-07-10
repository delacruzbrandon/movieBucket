package org.movie.bucket.domain.utility

sealed interface Error {
    enum class Remote: Error {
        CLIENT_ERROR,
        SERVER_ERROR,
        REDIRECTION_ERROR,
        AUTHENTICATION_ERROR,
        INTERNET_CONNECTION_ERROR,
        SERIALIZATION_ERROR,
        UNKNOWN
    }

    enum class Local: Error {
        NOT_FOUND,
        UNKNOWN
    }
}

data class RemoteErrorCode(
    val error: Error,
    val code: Int = 0
): Error