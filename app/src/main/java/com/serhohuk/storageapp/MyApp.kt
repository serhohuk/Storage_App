package com.serhohuk.storageapp

import android.app.Application
import com.serhohuk.storageapp.di.app
import com.serhohuk.storageapp.di.screen
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin {
            androidContext(this@MyApp)
            modules(screen, app)
        }
    }
}