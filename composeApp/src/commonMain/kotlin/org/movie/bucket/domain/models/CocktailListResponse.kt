package org.movie.bucket.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CocktailListResponse(
    val cocktailList: List<Cocktail>
)
