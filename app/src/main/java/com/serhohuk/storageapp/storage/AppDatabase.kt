package com.serhohuk.storageapp.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serhohuk.storageapp.models.Player
import com.serhohuk.storageapp.models.Team

const val DATABASE_VERSION = 1

@Database(entities =[Team::class, Player::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao() : AppDao

}