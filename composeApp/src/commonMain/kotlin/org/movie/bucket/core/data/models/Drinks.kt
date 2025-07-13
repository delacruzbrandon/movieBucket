package org.movie.bucket.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Drinks(
    val drinks: List<Cocktail>
)
