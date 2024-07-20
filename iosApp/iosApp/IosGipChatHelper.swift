import Foundation
import shared
import GipKit

class IosGipChatHelper: GipChatHelper {

    let gipChat = GipChat.shared

    func setAppId(appId: String) {
        gipChat.setAppId(appId)
    }

    func setUserName(userName: String) {
        gipChat.setUserName(userName)
    }

    func setUserId(userId: String) {
        gipChat.setUserId(userId)
    }

    func initialize(initialized: @escaping (KotlinBoolean) -> Void) {
        gipChat.initialize { isInitialized in
            initialized(KotlinBoolean(bool: isInitialized))
        }
    }

    func show() {
        gipChat.show()
    }
}