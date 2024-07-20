package io.gupshup.gipsdkdemo.di

import io.gupshup.gipsdkdemo.platform.GipChatHelper
import io.gupshup.gipsdkdemo.platform.IosApplicationComponent
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    single<GipChatHelper> { get<IosApplicationComponent>().gipChatHelper}
}