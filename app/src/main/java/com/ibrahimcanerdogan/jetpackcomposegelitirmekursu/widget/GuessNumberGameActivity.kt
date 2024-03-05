package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.widget

import android.app.GameManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.FinishComposingTextCommand
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.widget.ui.theme.JetpackComposeGeliştirmeKursuTheme
import kotlin.random.Random

class GuessNumberGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            Start(navController = navController)
        }
        composable("game"){
            Game(navController = navController)
        }
        composable("finish/{result}", arguments = listOf(
            navArgument("result") { type = NavType.BoolType },
        )) {
            val result = it.arguments?.getBoolean("result")
            Finish(navController = navController, result = result!!)
        }
    }
}

@Composable
fun Start(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tahmin Oyunu",
             fontSize = 40.sp
        )
        Icon(
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = "",
            modifier = Modifier.size(130.dp)
        )
        Button(onClick = { 
            navController.navigate("game")
        }) {
            Text(text = "Oyuna Başla")
        }
    }
}

@Composable
fun Game(navController: NavController) {
    val randomNumber = remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = true) {
        randomNumber.intValue = (0..100).random()
        Log.e("TAG", randomNumber.value.toString())
    }

    val helpText = remember { mutableStateOf("Sayı Girmeyi Dene!") }
    val dueCount = remember { mutableIntStateOf(5) }
    val guess = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kalan Hak: ${dueCount.intValue}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Yardım: ${helpText.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = guess.value,
            onValueChange = {
                guess.value = it
            },
            label = { Text(text = "Sayı Tahmini Giriniz!") },
            colors = TextFieldDefaults.colors(
                //setting the text field background when it is focused
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                //setting the text field background when it is unfocused or initial state
                unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                //setting the text field background when it is disabled
                disabledContainerColor = MaterialTheme.colorScheme.onError,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedButton(onClick = {
            dueCount.intValue -= 1

            if (randomNumber.intValue > guess.value.toInt()) {
                helpText.value = "Arttır"
            } else if(randomNumber.intValue < guess.value.toInt()) {
                helpText.value = "Azalt"
            } else {
                navController.navigate("finish/true") {
                    popUpTo("game") { inclusive = true }
                }
                return@OutlinedButton
            }

            if (dueCount.intValue == 0) {
                navController.navigate("finish/false") {
                    popUpTo("game") { inclusive = true }
                }
            }

            guess.value = ""
        }) {
            Text(text = "Tahmin Et")
        }
    }
}

@Composable
fun Finish(navController: NavController, result: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (result) {
            Text(
                text = "Kazandınız :)",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "Kaybettiniz :(",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeGeliştirmeKursuTheme {
        TransactionPage()
    }
}