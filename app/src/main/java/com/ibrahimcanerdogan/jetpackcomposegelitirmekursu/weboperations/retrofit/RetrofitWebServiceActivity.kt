package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.weboperations.retrofit

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.ColumnInfo
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.weboperations.retrofit.ui.theme.JetpackComposeGeliştirmeKursuTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitWebServiceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                RetrofitWebServicePage()
            }
        }
    }
}

@Composable
fun RetrofitWebServicePage() {

    LaunchedEffect(key1 = true) {
        updatePersonData(6, "İbrahim Erdoğan","028822222")
    }
}

fun allPersonData() {
    val dao = RetrofitClient.buildService()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = dao.getAllPerson()
            if(response.isSuccessful) {
                val list = response.body()?.personList
                list?.forEachIndexed { index, person ->
                    Log.i(
                        "RetrofitWebServiceActivity", "$index. Person ID: ${person.personID} \n" +
                                "$index. Person Name: ${person.personName} \n" +
                                "$index. Person Phone Number: ${person.personPhone} \n"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("RetrofitWebServiceActivity", e.message.toString())
        }
    }
}

fun searchPersonDataWithName(searchText: String) {
    val dao = RetrofitClient.buildService()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = dao.searchPerson(searchText)
            if(response.isSuccessful) {
                val list = response.body()?.personList
                list?.forEachIndexed { index, person ->
                    Log.i(
                        "RetrofitWebServiceActivity", "$index. Person ID: ${person.personID} \n" +
                                "$index. Person Name: ${person.personName} \n" +
                                "$index. Person Phone Number: ${person.personPhone} \n"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("RetrofitWebServiceActivity", e.message.toString())
        }
    }
}

fun deletePersonWithId() {
    val dao = RetrofitClient.buildService()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = dao.deletePerson(1)
            if(response.isSuccessful) {
                val data = response.body()
                Log.i(
                    "RetrofitWebServiceActivity", "Person Message: ${data?.message}"
                )
            }
        } catch (e: Exception) {
            Log.e("RetrofitWebServiceActivity", e.message.toString())
        }
    }
}

fun addPersonData(personName: String, personPhone: String) {
    val dao = RetrofitClient.buildService()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = dao.addPerson(personName, personPhone)
            if(response.isSuccessful) {
                val data = response.body()
                Log.i(
                    "RetrofitWebServiceActivity", "Person Message: ${data?.message}"
                )
            }
        } catch (e: Exception) {
            Log.e("RetrofitWebServiceActivity", e.message.toString())
        }
    }
}

fun updatePersonData(personId: Int, personName: String, personPhone: String) {
    val dao = RetrofitClient.buildService()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            // Girilen ID değerine ait olan "name" ve "phone" değerleri güncellenecektir.
            val response = dao.updatePerson(personId, personName, personPhone)
            if(response.isSuccessful) {
                val data = response.body()
                Log.i(
                    "RetrofitWebServiceActivity", "Person Message: ${data?.message}"
                )
            }
        } catch (e: Exception) {
            Log.e("RetrofitWebServiceActivity", e.message.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RetrofitWebServicePagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        RetrofitWebServicePage()
    }
}