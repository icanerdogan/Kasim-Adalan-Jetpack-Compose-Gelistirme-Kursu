package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.datastore.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class],
    version = 1
)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun dao() : PersonDao

    companion object {
        var INSTANCE: PersonDatabase? = null

        fun connectDatabase(context: Context) : PersonDatabase? {
            if (INSTANCE == null) {
                synchronized(PersonDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "person.sqlite"
                    ).createFromAsset("person.sqlite").build()
                }
            }

            return INSTANCE
        }

    }
}