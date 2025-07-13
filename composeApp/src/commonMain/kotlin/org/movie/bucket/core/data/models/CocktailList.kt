package org.movie.bucket.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CocktailList(
    val cocktailList: List<Cocktail>
)
