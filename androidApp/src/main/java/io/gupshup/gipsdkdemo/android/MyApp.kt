package io.gupshup.gipsdkdemo.android

import android.app.Application
import io.gupshup.gipsdkdemo.di.initKoinAndroid
import io.gupshup.gipsdkdemo.platform.AndroidApplicationComponent
import org.koin.android.ext.koin.androidContext

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(
            appComponent = AndroidApplicationComponent()
        ) {
            androidContext(this@MyApp)
        }
    }
    
}