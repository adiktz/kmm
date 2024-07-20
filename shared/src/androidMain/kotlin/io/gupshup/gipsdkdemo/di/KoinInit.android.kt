package io.gupshup.gipsdkdemo.di

import io.gupshup.gipsdkdemo.platform.AndroidApplicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoinAndroid(
    appComponent: AndroidApplicationComponent,
    appDeclaration: KoinAppDeclaration = {},
) {
    initKoin(
        listOf(module { single { appComponent } }),
        appDeclaration,
    )
}
