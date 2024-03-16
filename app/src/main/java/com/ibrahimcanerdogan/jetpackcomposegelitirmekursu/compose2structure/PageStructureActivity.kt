package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose2structure

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose2structure.ui.theme.JetpackComposeGeliştirmeKursuTheme

class PageStructureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onSecondary
                ) {
                    TransactionPage()
                }
            }
        }
    }
}
@Composable
fun TransactionPage() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "anasayfaA") {
        composable("anasayfaA") {
            PageStructureA(navController = navController)
        }
        /*
        composable("anasayfaB/{incomingData1}/{incomingData2}", arguments = listOf(
            navArgument("incomingData1") { type = NavType.StringType },
            navArgument("incomingData2") { type = NavType.FloatType }
        )) {
            val incomingData1 = it.arguments?.getString("incomingData1")
            val incomingData2 = it.arguments?.getFloat("incomingData2")
            PageStructureB(navController = navController, incomingData1!!, incomingData2!!)
        }
        */
        composable("anasayfaB/{person}", arguments = listOf(
            navArgument("person") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("person")
            val person = Gson().fromJson(json, Person::class.java)
            PageStructureB(navController = navController, person = person)
        }
        composable("anasayfaC") {
            PageStructureC(navController = navController)
        }
    }
}

@Composable
fun PageStructureA(navController: NavController) {

    val counter = remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(text = "Anasayfa A", fontSize = 50.sp)

        Button(onClick = {
            //val name = "İbrahim Can Erdoğan"
            //val number = 1.0f
            //navController.navigate("anasayfaB/$name/$number")

            val person = Person("İbrahim Can Erdoğan", 2.0f)
            val personJson = Gson().toJson(person)
            navController.navigate("anasayfaB/$personJson")

        }) {
            Text(text = "Diğer Sayfaya Git")
        }

        Text(text = "Count : ${counter.intValue}")

        Button(onClick = {
            counter.intValue += 1
        }) {
            Text(text = "TIKLA")
        }
    }
}

@Composable
fun PageStructureB(navController: NavController, person: Person) {
//fun PageStructureB(navController: NavController, incomingData1: String, incomingData2: Float) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(text = "Anasayfa B", fontSize = 50.sp)

        //Text(text = "Incoming Data 1: $incomingData1")
        //Text(text = "Incoming Data 2: $incomingData2")
        Text(text = "Incoming Data 1: ${person.name}")
        Text(text = "Incoming Data 2: ${person.number.toString()}")

        Button(onClick = {
            // When I came to Page C, delete Page B on backstack.
            navController.navigate("anasayfaC"){
                popUpTo("anasayfaB") { inclusive = true }
            }
        }) {
            Text(text = "Diğer Sayfaya Git")
        }
    }
}

@Composable
fun PageStructureC(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(text = "Anasayfa C", fontSize = 50.sp)

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Diğer Sayfaya Git")
        }
    }

    // Specialization back button!
    BackHandler(onBack = {
        Toast.makeText(context, "Clicked back!", Toast.LENGTH_SHORT).show()
        activity.finish()
    })
}

@Preview(showBackground = true)
@Composable
fun PageStructurePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        TransactionPage()
    }
}