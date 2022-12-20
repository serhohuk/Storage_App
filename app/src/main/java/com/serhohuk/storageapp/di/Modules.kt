package com.serhohuk.storageapp.di

import androidx.room.Room
import com.serhohuk.storageapp.storage.AppDatabase
import com.serhohuk.storageapp.viewmodel.PlayersViewModel
import com.serhohuk.storageapp.viewmodel.TeamViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "football"
        ).build()
    }

    single {
        val database = get<AppDatabase>()
        database.appDao()
    }
}

val screenModule = module {
    viewModel { TeamViewModel(get()) }
    viewModel { (teamId: Int) -> PlayersViewModel(get(), teamId) }
}