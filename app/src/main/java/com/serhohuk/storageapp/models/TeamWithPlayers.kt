package com.serhohuk.storageapp.models

import androidx.room.Embedded
import androidx.room.Relation

data class TeamWithPlayers(
    @Embedded
    val team: Team,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "playerId"
    )
    val players: List<Player>
)
