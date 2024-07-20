package io.gupshup.gipsdkdemo.platform

import android.content.Context
import io.gupshup.gipchat.GipChat
import io.gupshup.gipsdkdemo.getPlatform

class AndroidGipChatHelper(private val context: Context): GipChatHelper {

    private val gipChat = GipChat
    override fun setAppId(appId: String) {
        gipChat.setAppId(appId)
    }

    override fun setUserName(userName: String) {
        gipChat.setUserName(userName)
    }

    override fun setUserId(userId: String) {
        gipChat.setUserId(userId)
    }

    override fun initialize(initialized: (Boolean) -> Unit) {
        gipChat.initialize(context = context, initialized = initialized)
    }

    override fun show() {
        gipChat.show()
    }


}