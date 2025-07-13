package org.movie.bucket.core.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.movie.bucket.core.data.models.Cocktail
import org.movie.bucket.core.data.models.CocktailList
import org.movie.bucket.utility.Result
import util.NetworkError

class CocktailClient(
    private val httpClient: HttpClient
) {
    suspend fun getRandomCocktail(): Result<List<Cocktail>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://www.thecocktaildb.com/api/json/v1/1/random.php"
            ) {
//                parameter("text", uncensored)
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val cocktailList = response.body<CocktailList>()
                Result.Success(cocktailList.cocktailList)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}