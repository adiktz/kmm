package io.gupshup.gipsdkdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform