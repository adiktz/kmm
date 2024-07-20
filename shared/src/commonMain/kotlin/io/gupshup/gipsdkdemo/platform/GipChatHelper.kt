package io.gupshup.gipsdkdemo.platform

interface GipChatHelper {
    fun setAppId(appId: String)
    fun setUserName(userName: String)
    fun setUserId(userId: String)
    fun initialize(initialized:(Boolean) -> Unit)
    fun show()
}