package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.material

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.material.ui.theme.JetpackComposeGeliştirmeKursuTheme

class LazyStructureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PageTransaction()
                }
            }
        }
    }
}

@Composable
fun PageTransaction() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            LazyListExample(navController)
        }
        composable("detail/{countryName}", arguments = listOf(
            navArgument("countryName") { type = NavType.StringType }
        )) {
            val countryName = it.arguments?.getString("countryName")!!
            DetailCountryPage(countryName = countryName)
        }
    }
}

@Composable
fun LazyListExample(navController: NavController) {

    val countryList = remember {
        mutableStateListOf("Turkey", "Italy", "Greek", "Germany", "Japan", "China")
    }

    LazyColumn {
        items(
            count = countryList.count(),
            itemContent = {
                val country = countryList[it]

                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        // @OptIn(ExperimentalFoundationApi::class)
                        //.combinedClickable(onClick = {})
                ) {
                    // This row for click.
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                Log.e("TAG", "$country Seçildi.")
                                navController.navigate("detail/$country")
                            }
                    ) {
                        // This row for design.
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = country, modifier = Modifier.padding(5.dp))
                            OutlinedButton(onClick = {
                                Log.e("TAG", "$country selected with button click.")
                            }) {
                                Text(text = "Ülke Seç")
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LazyListExamplePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        PageTransaction()
    }
}