package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose7weboperations.firebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose7weboperations.firebase.ui.theme.JetpackComposeGeliştirmeKursuTheme

class FirebaseRealtimeDatabaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                FirebaseRealtimePage()
            }
        }
    }
}

@Composable
fun FirebaseRealtimePage() {
    queryLimitPerson()
}

fun addPersonFirebaseDatabase() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    val newPerson = FirebasePerson("Can Erdoğan", 23)
    personReference.push().setValue(newPerson)
}

fun getAllPersonFirebaseDatabase() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    personReference.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach {
                val person = it.getValue(FirebasePerson::class.java)

                if (person != null) {
                    Log.i("FirebaseRealtimeDatabaseActivity", "Person Key: ${it.key} \n" +
                            "Person Name: ${person.personName} \n" +
                            "Person Age: ${person.personAge}")
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseRealtimeDatabaseActivity", error.message)
        }

    })
}

fun deletePersonFirebaseDatabase() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    // Veri silinmesi için key bilgisi gerekmektedir.
    personReference.child("-Nt5WAqrq3V5T2zTGH86").removeValue()
}

fun updatePersonFirebaseDatabase() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    val updatePerson = HashMap<String, Any>()
    updatePerson["personName"] = "New Can Erdogan"
    updatePerson["personAge"] = 100

    // Veri güncellenmesi için key bilgisi gerekmektedir.
    personReference.child("-Nt5WqWycBaKRdWED5kc").updateChildren(updatePerson)
}

fun queryEqualToPerson() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    // Alan içerisinde istenen değeri aratılabilecek sorgudur.
    val query = personReference.orderByChild("personName").equalTo("Ahmet")

    query.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { data ->
                val person = data.getValue(FirebasePerson::class.java)

                person?.let {
                    Log.i(
                        "FirebaseRealtimeDatabaseActivity", "Person Key: ${data.key} \n" +
                                "Person Name: ${person.personName} \n" +
                                "Person Age: ${person.personAge}"
                    )
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseRealtimeDatabaseActivity", error.message)
        }
    })
}

fun queryValueRangePerson() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    // Alan içerisinde istenen değer aralığında veri aratılabilecek sorgudur.
    val query = personReference.orderByChild("personAge").startAt(20.0).endAt(100.0)

    query.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { data ->
                val person = data.getValue(FirebasePerson::class.java)

                person?.let {
                    Log.i(
                        "FirebaseRealtimeDatabaseActivity", "Person Key: ${data.key} \n" +
                                "Person Name: ${person.personName} \n" +
                                "Person Age: ${person.personAge}"
                    )
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseRealtimeDatabaseActivity", error.message)
        }
    })
}

fun queryLimitPerson() {
    val database = FirebaseDatabase.getInstance()
    val personReference = database.getReference("person")

    // Veritabanı içerisinde sınırlı sayıda veri alınmasını sağlayan sorgudur.
    //val query = personReference.limitToFirst(1)
    val query = personReference.limitToLast(2)

    query.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { data ->
                val person = data.getValue(FirebasePerson::class.java)

                person?.let {
                    Log.i(
                        "FirebaseRealtimeDatabaseActivity", "Person Key: ${data.key} \n" +
                                "Person Name: ${person.personName} \n" +
                                "Person Age: ${person.personAge}"
                    )
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseRealtimeDatabaseActivity", error.message)
        }
    })
}

@Preview(showBackground = true)
@Composable
fun FirebaseRealtimeDatabasePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        FirebaseRealtimePage()
    }
}