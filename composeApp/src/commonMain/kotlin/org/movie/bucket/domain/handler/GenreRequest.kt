package org.movie.bucket.domain.handler

import org.movie.bucket.domain.utility.LocalError
import org.movie.bucket.domain.utility.Result

fun genreRequest(genreIds: List<Int>?): Result<List<String>, LocalError> {
    val genreMap = mapOf (
        12 to "Adventure",
        14 to "Fantasy",
        16 to "Animation",
        18 to "Drama",
        27 to "Horror",
        28 to "Action",
        35 to "Comedy",
        36 to "History",
        37 to "Western",
        53 to "Thriller",
        80 to "Crime",
        99 to "Documentary",
        878 to "Science Fiction",
        9648 to "Mystery",
        10402 to "Music",
        10749 to "Romance",
        10751 to "Family",
        10752 to "War",
        10770 to "TV Movie"
    )
    val mappedGenres = genreIds?.mapNotNull { genreMap[it] }

    return when {
        mappedGenres == null -> Result.Error(LocalError.LOCAL_ERROR_GENRE_REQUEST)
        mappedGenres.isEmpty() -> Result.Error(LocalError.LOCAL_ERROR_GENRE_REQUEST)
        mappedGenres.size < genreIds.size -> Result.Success(mappedGenres) // Partial success
        else -> Result.Success(mappedGenres) // Full success
    }

}