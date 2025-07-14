package org.movie.bucket.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CocktailList(
    val cocktailList: List<Cocktail>
)
