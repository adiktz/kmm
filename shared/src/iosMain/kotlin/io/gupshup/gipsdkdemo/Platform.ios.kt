package io.gupshup.gipsdkdemo

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val title: String
        get() = "GipKit Demo"
}

actual fun getPlatform(): Platform = IOSPlatform()