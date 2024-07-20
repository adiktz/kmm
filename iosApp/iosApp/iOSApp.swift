import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
		KoinInit_iosKt.doInitKoinIos(
            appComponent: IosApplicationComponent(gipChatHelper: IosGipChatHelper())
        )
    }

    var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}