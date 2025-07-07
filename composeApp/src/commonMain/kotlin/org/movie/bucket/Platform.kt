package org.movie.bucket

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform