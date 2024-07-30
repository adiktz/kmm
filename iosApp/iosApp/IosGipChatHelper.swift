import Foundation
import shared
import GipKit

class IosGipChatHelper: GipChatHelper {

    let gipChat = GipChat.shared
    
    let delegate = Delegate { message in
        print(message)
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
        print("In OnError...")
        gipChat.setDelegate(delegate)
    }
    
    class Delegate: GipChatDelegate {
        var errorMessage: (String) -> Void
        
        init(errorMessage: @escaping (String) -> Void) {
            self.errorMessage = errorMessage
        }
        
        func onError(message: GipChatException) {
            print("Delegate called...")
            errorMessage(message.description)
        }
    }
}

