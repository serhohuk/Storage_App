package com.serhohuk.storageapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val playerId: Int,
    val teamId: Int,
    val name: String,
    val age: Int
)