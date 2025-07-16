package org.movie.bucket.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    val page: Int,
    val results: Array<Movie>,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("total_pages")
    val totalPages: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MovieListResponse

        if (page != other.page) return false
        if (totalResults != other.totalResults) return false
        if (totalPages != other.totalPages) return false
        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = page
        result = 31 * result + totalResults
        result = 31 * result + totalPages
        result = 31 * result + results.contentHashCode()
        return result
    }
}
