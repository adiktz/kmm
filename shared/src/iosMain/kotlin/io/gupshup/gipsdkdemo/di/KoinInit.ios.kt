package io.gupshup.gipsdkdemo.di

import io.gupshup.gipsdkdemo.platform.IosApplicationComponent
import org.koin.dsl.module

fun initKoinIos(appComponent: IosApplicationComponent) {
    initKoin(
        listOf(module { single { appComponent } })
    )
}