package org.movie.bucket.core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)

        modules(
            AppModule().module, // If not showing... build to generate code
            MovieModule().module

        )
    }
}