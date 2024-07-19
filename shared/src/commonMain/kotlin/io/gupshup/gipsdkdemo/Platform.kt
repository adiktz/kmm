package io.gupshup.gipsdkdemo

interface Platform {
    val name: String
    val title: String
}

expect fun getPlatform(): Platform