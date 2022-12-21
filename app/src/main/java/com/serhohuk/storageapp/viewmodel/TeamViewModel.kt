package com.serhohuk.storageapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.storageapp.models.Team
import com.serhohuk.storageapp.storage.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(
    private val appDao: AppDao
) : ViewModel() {

    private val _teams = MutableLiveData<List<Team>>()
    val teams : LiveData<List<Team>>
    get() = _teams

    init {
        getAllTeams()
    }

    fun getAllTeams() {
        viewModelScope.launch {
            _teams.postValue(appDao.getAllTeams().toList())
        }
    }

    fun saveTeam(name: String) {
        viewModelScope.launch {
            appDao.insertAllTeams(Team(0, name))
            _teams.postValue(appDao.getAllTeams().toList())
        }
    }

}