package com.serhohuk.storageapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.storageapp.model.Family
import com.serhohuk.storageapp.model.Person
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.coroutines.launch

class PersonsViewModel(
    private val realm: Realm,
    private val familyId: Int
) : ViewModel() {

    private val _members = MutableLiveData<List<Person>>()
    val members : LiveData<List<Person>>
        get() = _members

    init {
        getFamilyPersons()
    }

    private fun getFamilyPersons() {
        viewModelScope.launch {
            realm.executeTransaction { realm ->
                val family = realm.where(Family::class.java).equalTo("familyId", familyId).findFirst()
                _members.value = realm.copyFromRealm(family?.persons?: emptyList())
            }
        }
    }

    fun saveFamilyPerson(name: String, surname: String) {
        viewModelScope.launch {
            realm.executeTransaction { realm ->
                val person = Person(familyId= familyId, name = name, surname = surname)
                realm.insertOrUpdate(person)
                val family = realm.where(Family::class.java).equalTo("familyId", familyId).findFirst()
                family?.persons?.add(person)
                realm.copyToRealmOrUpdate(family)
                _members.value = realm.copyFromRealm(family?.persons?: emptyList())
            }
        }
    }
}