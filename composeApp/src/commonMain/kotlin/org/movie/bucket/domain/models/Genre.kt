package org.movie.bucket.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    @SerialName("name")
    val genre: String
)
