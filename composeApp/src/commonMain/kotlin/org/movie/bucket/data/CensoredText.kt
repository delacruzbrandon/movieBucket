package org.movie.bucket.data

import kotlinx.serialization.Serializable


@Serializable
data class CensoredText(
    val result: String
)
