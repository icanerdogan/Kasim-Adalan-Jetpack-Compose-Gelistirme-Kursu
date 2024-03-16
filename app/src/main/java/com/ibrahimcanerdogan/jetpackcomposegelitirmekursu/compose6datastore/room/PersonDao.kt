package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose6datastore.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    suspend fun getAllPerson() : List<Person>

    @Insert
    suspend fun addPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM person ORDER BY RANDOM() LIMIT 1")
    suspend fun randomPerson() : List<Person>

    @Query("SELECT * FROM person WHERE name like '%' || :searchText || '%'")
    suspend fun searchPerson(searchText: String): List<Person>

    @Query("SELECT * FROM person WHERE id=:personID")
    suspend fun getPersonWithID(personID: Int): Person

    @Query("SELECT count(*) FROM person WHERE name=:personName")
    suspend fun countPersonByName(personName: String): Int
}