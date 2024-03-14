package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppDataStore(var context: Context) {
    val Context.ds : DataStore<Preferences> by preferencesDataStore("data")

    companion object {
        val NAME_KEY = stringPreferencesKey("NAME")
        val COUNTER_KEY = intPreferencesKey("COUNTER")
        // List
        val DATA_LIST_KEY = stringSetPreferencesKey("LIST")
    }

    suspend fun registerName(name : String) {
        context.ds.edit {
            it[NAME_KEY] = name
        }
    }

    suspend fun readName() : String {
        val p = context.ds.data.first()
        return p[NAME_KEY] ?: "No Name!"
    }

    suspend fun removeName() {
        context.ds.edit {
            it.remove(NAME_KEY)
        }
    }

    suspend fun registerCounter(count: Int) {
        context.ds.edit {
            it[COUNTER_KEY] = count
        }
    }

    suspend fun readCounter() : Int {
        val p = context.ds.data.first()
        return p[COUNTER_KEY] ?: 0
    }

    // Listeler sadece "Set" olarak "String" tipinde kaydedilebilmektedir.
    suspend fun registerList(list: Set<String>) {
        context.ds.edit {
            it[DATA_LIST_KEY] = list
        }
    }

    suspend fun readList() : Set<String> {
        val p = context.ds.data.first()
        return p[DATA_LIST_KEY] ?: setOf()
    }
}