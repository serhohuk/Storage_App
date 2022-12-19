package com.serhohuk.storageapp.di

import com.serhohuk.storageapp.viewModel.OtMViewModel
import com.serhohuk.storageapp.viewModel.PersonsViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screen = module {

    viewModel { OtMViewModel(get()) }
    viewModel { (familyId: Int) -> PersonsViewModel(get(), familyId)}
}


val app = module {

    single {
        RealmConfiguration.Builder()
            .name("realm")
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .compactOnLaunch()
            .build()
    }

    single { Realm.getInstance(get()) }
}