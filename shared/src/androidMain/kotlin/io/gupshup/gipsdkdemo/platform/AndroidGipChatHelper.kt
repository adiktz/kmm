package io.gupshup.gipsdkdemo.platform

import android.content.Context
import io.gupshup.gipchat.GipChat
import io.gupshup.gipchat.listener.GipChatListener
import io.gupshup.gipsdkdemo.getPlatform
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

class AndroidGipChatHelper(private val context: Context): GipChatHelper {

    private val gipChat = GipChat
    private val listener = Listener()

    init {
        gipChat.setListener(listener = listener)
    }
    override fun setAppId(appId: String) {
        gipChat.setAppId(appId)
    }

    override fun setUserName(userName: String?) {
        gipChat.setUserName(userName)
    }

    override fun setUserId(userId: String?) {
        gipChat.setUserId(userId)
    }

    override fun initialize(initialized: (Boolean) -> Unit) {
        gipChat.initialize(context = context, initialized = initialized)
    }

    override fun show() {
        gipChat.show()
    }

    override fun onError(message: (String) -> Unit) {
        listener.message = message
    }

    inner class Listener: GipChatListener {
        var message: (String) -> Unit = {}
        override fun onError(exception: Exception) {
            exception.message?.let {
                val json = Json.parseToJsonElement(it).jsonObject
                if (json["message"] != null) {
                    message(json["message"].toString().replace("\"", ""))
                    return
                }
            }
            message(exception.message ?: "Error occurred..")
        }
        override fun onInitialized() {
            println("Initialized")
        }

    }
}