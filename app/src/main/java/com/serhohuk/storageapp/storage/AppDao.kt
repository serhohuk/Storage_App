package com.serhohuk.storageapp.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.serhohuk.storageapp.models.Player
import com.serhohuk.storageapp.models.Team
import com.serhohuk.storageapp.models.TeamWithPlayers

@Dao
interface AppDao {

    @Query("SELECT * FROM team_table")
    suspend fun getAllTeams() : List<Team>

    @Transaction
    @Query("SELECT * FROM team_table WHERE teamId=:teamId")
    suspend fun getTeamWithPlayersById(teamId: Int) : TeamWithPlayers

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTeams(vararg team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlayers(vararg player: Player)

}