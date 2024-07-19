package io.gupshup.gipsdkdemo

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    override val title: String
        get() = "GipSDK Demo"
}

actual fun getPlatform(): Platform = AndroidPlatform()