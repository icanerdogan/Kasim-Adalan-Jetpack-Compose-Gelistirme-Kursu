package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.material.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.material.foodapp.ui.theme.JetpackComposeGeliştirmeKursuTheme

class FoodAppActivity : ComponentActivity() {
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

@Preview(showBackground = true)
@Composable
fun FoodMainPagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        PageTransaction()
    }
}

@Composable
fun PageTransaction() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainPage") {
        composable("mainPage") {
            FoodMainPage(navController)
        }
        composable("detailPage/{food}", arguments = listOf(
            navArgument("food") {type = NavType.StringType}
        )) {
            val json = it.arguments?.getString("food")
            val food = Gson().fromJson(json, Food::class.java)
            FoodDetailPage(food = food)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodMainPage(navController: NavController) {
    val foodList = remember { mutableStateListOf<Food>() }

    // This function run when app start.
    LaunchedEffect(key1 = true) {
        val y1 = Food(1, "Köfte", "kofte", 100)
        val y2 = Food(2, "Ayran", "ayran", 53)
        val y3 = Food(3, "Fanta", "fanta", 34)
        val y4 = Food(4, "Makarna", "makarna", 456)
        val y5 = Food(5, "Kadayıf", "kadayif", 41)
        val y6 = Food(6, "Baklava", "baklava", 99)

        foodList.add(y1)
        foodList.add(y2)
        foodList.add(y3)
        foodList.add(y4)
        foodList.add(y5)
        foodList.add(y6)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Food") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.White
                )
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(
                count = foodList.count(),
                itemContent = { id ->
                    val food = foodList[id]

                    Card(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val foodJson = Gson().toJson(food)
                                    navController.navigate("detailPage/$foodJson")
                                }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(all = 10.dp)
                                    .fillMaxWidth()
                            ) {
                                val context = LocalContext.current
                                Image(
                                    bitmap = ImageBitmap.imageResource(
                                        id = context.resources.getIdentifier(
                                            food.foodImage,
                                            "drawable",
                                            context.packageName
                                        )
                                    ),
                                    contentDescription = "",
                                    modifier = Modifier.size(100.dp)
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier.fillMaxHeight()
                                    ) {
                                        Text(text = food.foodName, fontSize = 20.sp)
                                        Spacer(modifier = Modifier.size(30.dp))
                                        Text(text = "${food.foodPrice}", color = Color.Blue)
                                    }
                                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}