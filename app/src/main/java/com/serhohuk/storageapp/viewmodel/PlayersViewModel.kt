package com.serhohuk.storageapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.storageapp.models.Player
import com.serhohuk.storageapp.storage.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayersViewModel(
    private val dao: AppDao,
    private val teamId: Int
) : ViewModel() {

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>>
        get() = _players

    init {
        getPlayers()
    }

    private fun getPlayers() {
        viewModelScope.launch {
            val data = dao.getTeamWithPlayersById(teamId)
            _players.postValue(data.players.toList())
        }
    }


    fun savePlayer(name: String, age: Int) {
        viewModelScope.launch {
            val player = Player(0, teamId, name, age)
            dao.insertAllPlayers(player)
            val data = dao.getTeamWithPlayersById(teamId)
            _players.postValue(data.players.toList())
        }
    }
}