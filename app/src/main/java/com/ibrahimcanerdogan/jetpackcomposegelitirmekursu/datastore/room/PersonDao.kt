package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.datastore.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    suspend fun getAllPerson() : List<Person>
}