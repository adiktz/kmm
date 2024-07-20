import Foundation
import shared

class IosGipChatHelper: GipChatHelper {
    func initialize() {
        print("Initialized on iOS:: ", Platform_iosKt.getPlatform().name)
    }
}