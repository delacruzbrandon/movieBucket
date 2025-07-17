package org.movie.bucket.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.movie.bucket.core.data.HttpClientEngineFactory
import org.movie.bucket.domain.builder.createHttpClient

@Module
class AppModule {

    @Single
    @NoAuthHttpClient
    fun noAuthHttpClient(engine: HttpClientEngine): HttpClient = createHttpClient(engine)

    @Factory
    fun httpClientEngine(): HttpClientEngine = HttpClientEngineFactory().getHttpEngine()
}

@Named
annotation class NoAuthHttpClient