package org.movie.bucket.domain.utility

sealed class Result<D, E: Error>(
    val data: D? = null,
    val error: org.movie.bucket.domain.utility.Error? = null
) {
    class Success<D>(
        data: D?
    ): Result<D, org.movie.bucket.domain.utility.Error>(data)

    class Error<D>(
        error: org.movie.bucket.domain.utility.Error
    ): Result<D, org.movie.bucket.domain.utility.Error>(null, error)
}