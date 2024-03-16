package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose6datastore.room

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose6datastore.ui.theme.JetpackComposeGeliştirmeKursuTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                RoomExample()
            }
        }
    }
}

@Composable
fun RoomExample() {
    val context = LocalContext.current
    val database = PersonDatabase.connectDatabase(context)!!

    // Read Data (SELECT)
    LaunchedEffect(key1 = true) {
        // Ekleme ve ardından okuma işlemi ardışık olması durumunda asenkron yapıdan dolayı dikkate edilmelidir.
        // addPersonData(database)
        // updatePersonData(database)
        // deletePersonData(database)
        // getRandomPerson(database)
        // searchPersonData(database)
        // allPersonData(database)
        //getPersonDataWithID(database)
        //getCountPersonDataByName(database)
    }
}

fun allPersonData(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        val list = personDatabase.dao().getAllPerson()

        list.forEachIndexed {index, it ->
            Log.i("RoomExampleActivity", "$index. Person ID: ${it.personID} \n" +
                    "$index. Person Name: ${it.personName} \n" +
                    "$index. Person Phone Number: ${it.personPhoneNumber} \n")
        }
    }
}

fun addPersonData(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        val newPerson = Person(0, "Ibrahim", "111111")

        personDatabase.dao().addPerson(newPerson)
    }
}

fun updatePersonData(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        // Primary Key değeri aynı olan veri eklendiğinde update işlemi gerçekleştirilir.
        val updatePerson = Person(1, "Ibrahim Can Erdoğan", "123123123")

        personDatabase.dao().updatePerson(updatePerson)
    }
}

fun deletePersonData(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        val deletePerson = Person(6, "", "")

        personDatabase.dao().deletePerson(deletePerson)
    }
}

fun getRandomPerson(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        val randomPerson = personDatabase.dao().randomPerson()

        randomPerson.forEach {
            Log.i("RoomExampleActivity", "Person ID: ${it.personID} \n" +
                    "Person Name: ${it.personName} \n" +
                    "Person Phone Number: ${it.personPhoneNumber} \n")
        }
    }
}

fun searchPersonData(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        // İsim içerisinde "ğ" içeren person verilerini getiriyor.
        val searchPerson = personDatabase.dao().searchPerson("ğ")

        searchPerson.forEach {
            Log.i("RoomExampleActivity", "Person ID: ${it.personID} \n" +
                    "Person Name: ${it.personName} \n" +
                    "Person Phone Number: ${it.personPhoneNumber} \n")
        }
    }
}

fun getPersonDataWithID(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        val person = personDatabase.dao().getPersonWithID(5)

        Log.i(
            "RoomExampleActivity", "Person ID: ${person.personID} \n" +
                    "Person Name: ${person.personName} \n" +
                    "Person Phone Number: ${person.personPhoneNumber} \n"
        )
    }
}

fun getCountPersonDataByName(personDatabase: PersonDatabase) {
    CoroutineScope(Dispatchers.Main).launch {
        // İçerisinde Can geçen isimlerin sayısını almak istiyoruz.
        val personCount = personDatabase.dao().countPersonByName("Ibrahim")

        Log.i("RoomExampleActivity", "Person Count: $personCount")
    }
}

@Preview(showBackground = true)
@Composable
fun RoomExamplePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        RoomExample()
    }
}