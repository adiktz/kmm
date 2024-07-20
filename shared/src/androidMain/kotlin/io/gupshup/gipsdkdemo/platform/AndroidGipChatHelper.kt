package io.gupshup.gipsdkdemo.platform

import io.gupshup.gipsdkdemo.getPlatform

class AndroidGipChatHelper: GipChatHelper {
    override fun initialize() {
        println("Initialized on android:: ${getPlatform().name}")
    }

}