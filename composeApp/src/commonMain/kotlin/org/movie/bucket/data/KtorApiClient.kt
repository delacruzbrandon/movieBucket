//package org.movie.bucket.data
//
//import io.ktor.client.HttpClient
//import io.ktor.client.request.get
//import org.movie.bucket.domain.utility.Error
//import org.movie.bucket.domain.utility.Result
//
//class KtorApiClient(
//    val httpClient: HttpClient
//) {
//    private val baseUrl = "https://api.themoviedb.org/3/"
//    private val url = "${baseUrl}movie/popular?language=en-US&page=1"
//    suspend inline fun <reified R: Any> get(
//        route: String,
//        parameters: Map<String, Any> = emptyMap(),
//        header: Map<String, Any> = emptyMap(),
//    ): Result<R, Error> {
//        return httpClient.get {
//            url(baseUrl + route)
//        }
//    }
//}