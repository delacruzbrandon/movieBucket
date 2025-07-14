package org.movie.bucket.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)