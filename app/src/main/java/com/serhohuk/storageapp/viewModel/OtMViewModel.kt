package com.serhohuk.storageapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.storageapp.model.Family
import com.serhohuk.storageapp.model.Person
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.launch

class OtMViewModel(
    private val realm: Realm
) : ViewModel() {


    private val _members = MutableLiveData<List<Family>>()
    val members : LiveData<List<Family>>
    get() = _members

    init {
        getAllMembers()
    }

    private fun getAllMembers() {
        viewModelScope.launch {
            realm.executeTransaction { transaction ->
                _members.value = transaction.where(Family::class.java).findAll().toList()
            }
        }
    }

    fun saveFamily(name: String) {
        viewModelScope.launch {
            realm.executeTransaction { r ->
                val family = Family(familyName = name)
                r.insert(family)
                _members.postValue(r.where(Family::class.java).findAll().toList())
            }
        }
    }



}