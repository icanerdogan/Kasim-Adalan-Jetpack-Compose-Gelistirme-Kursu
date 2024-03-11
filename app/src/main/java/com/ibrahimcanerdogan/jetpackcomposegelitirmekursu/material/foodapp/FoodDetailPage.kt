package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.material.foodapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailPage(food: Food) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = food.foodName) },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.Magenta
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
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
                contentDescription = ""
            )
            Text(text = "${food.foodPrice}", color = Color.Blue, fontSize = 50.sp)
            Button(
                onClick = {
                    Log.e("Yemek", "${food.foodName} sipariş verildi.")
                },
                modifier = Modifier.size(250.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Magenta,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sipariş Ver!")
            }
        }
    }
}