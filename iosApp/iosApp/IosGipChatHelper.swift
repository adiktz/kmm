import Foundation
import shared
import GipKit

class IosGipChatHelper: GipChatHelper {

    let gipChat = GipChat.shared
    
    let delegate = Delegate { message in
    //    print(message)
    }
    
    init() {
        gipChat.setDelegate(delegate)
    }

    func setAppId(appId: String) {
        gipChat.setAppId(appId)
    }

    func setUserName(userName: String?) {
        gipChat.setUserName(userName)
    }

    func setUserId(userId: String?) {
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

    func onError(message: @escaping (String) -> Void) {
        delegate.errorMessage = message
    }
    
    class Delegate: GipChatDelegate {
        var errorMessage: (String) -> Void
        
        init(errorMessage: @escaping (String) -> Void) {
            self.errorMessage = errorMessage
        }
        
        func onError(message: GipChatException) {
            errorMessage(message.description)
        }
    }
}

