package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose6datastore.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val personID: Int,
    @ColumnInfo("name")
    val personName: String,
    @ColumnInfo("phone")
    val personPhoneNumber: String
)
