package io.gupshup.gipsdkdemo.di

import io.gupshup.gipsdkdemo.platform.AndroidGipChatHelper
import io.gupshup.gipsdkdemo.platform.GipChatHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf<GipChatHelper>(::AndroidGipChatHelper)
}