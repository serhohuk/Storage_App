package com.serhohuk.storageapp.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId


open class Family(
    @PrimaryKey
    var familyId : Int = ObjectId().timestamp,
    @Required
    var familyName : String = "",
    var persons: RealmList<Person> = RealmList<Person>()
) : RealmObject()