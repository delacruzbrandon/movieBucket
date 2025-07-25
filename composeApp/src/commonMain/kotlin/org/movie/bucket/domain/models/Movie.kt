package org.movie.bucket.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,

    @SerialName("poster_path")
    val thumbnail: String,

    @SerialName("overview")
    val description: String?,

    @SerialName("genre_ids")
    val genres: List<Int> = emptyList(),

    @SerialName("vote_average")
    val rating: Double?,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("original_language")
    val language: String,
)
