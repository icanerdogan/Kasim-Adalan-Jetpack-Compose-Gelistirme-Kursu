package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose1design

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.ui.theme.JetpackComposeGeliştirmeKursuTheme
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.ui.theme.colorAlternative
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.ui.theme.colorMain
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.ui.theme.longText

class YemekTarifUygulamasi : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tarif()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarif() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Yazi("Yemek Tarifi") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorMain,
                    titleContentColor = Color.Black,
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .weight(50f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorMain,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        Log.i("TAG", "Beğenildi!")
                    }) {
                    Yazi(yazi = "Beğen")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .weight(50f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorAlternative,
                        contentColor = Color.Black
                    ),
                    onClick = { Log.i("TAG", "Yorum Yapıldı!") }) {
                    Yazi(yazi = "Yorum Yap")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Köfte",
                    color = colorMain,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Yazi(yazi = "Izgaraya Uygun")
                    Yazi(yazi = "7 Haziran")
                }
            }
            Text(
                text = longText,
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Yazi(yazi: String) {
    Text(text = yazi)
}

@Preview(showBackground = true)
@Composable
fun TarifPreview() {
    JetpackComposeGeliştirmeKursuTheme {
        Tarif()
    }
}