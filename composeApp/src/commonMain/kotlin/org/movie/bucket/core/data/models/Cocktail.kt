package org.movie.bucket.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    @SerialName("idDrink")
    val id: Int,

    @SerialName("strDrink")
    val name: String,

    @SerialName("strDrinkThumb")
    val thumbnail: String,

    @SerialName("strTags")
    val tags: String? = "Null",

    @SerialName("strInstructions")
    val instructions: String? = "Null",
)