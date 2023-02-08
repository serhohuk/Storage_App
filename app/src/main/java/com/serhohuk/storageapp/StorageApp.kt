package com.serhohuk.storageapp

import android.app.Application
import com.serhohuk.storageapp.di.appModule
import com.serhohuk.storageapp.di.screenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StorageApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StorageApp)
            modules(appModule, screenModule)
        }
    }
}