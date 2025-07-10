package org.movie.bucket.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(
    engine: HttpClientEngine
): HttpClient {
    val apiKey = "**********"
    val appJson = "application/json"

    val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("______________KTOR______________")
                    println(message)
                    println("______________END_______________")
                }

            }
        }

//        defaultRequest {
//            contentType(ContentType.Application.Json)
//            header(HttpHeaders.Authorization, apiKey)
//            header(HttpHeaders.Accept, appJson)
//        }
    }

    return client
}