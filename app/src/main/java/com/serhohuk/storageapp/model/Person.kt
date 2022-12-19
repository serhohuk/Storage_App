package com.serhohuk.storageapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class Person(
    @PrimaryKey
    var personId: Int = ObjectId().timestamp,
    var familyId: Int = 0,
    @Required
    var name: String = "",
    @Required
    var surname: String = ""
) : RealmObject()