package com.serhohuk.storageapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_table")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val teamId: Int,
    val name: String
)