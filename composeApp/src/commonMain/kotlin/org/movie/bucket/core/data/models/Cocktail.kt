package org.movie.bucket.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    val idDrink: Int,
    val strDrink: String,
    val strTags: String? = "Null",
)