package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.ui.theme.JetpackComposeGeliştirmeKursuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPage()
                }
            }
        }
    }
}

@Composable
fun MainPage() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Magenta)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Red)
                .weight(50f)
        )
        Spacer(modifier = Modifier.size(100.dp, 200.dp))
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Green)
                .weight(30f)
        )
        Column(
            modifier = Modifier.weight(10f)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Yellow)
            )
        }
        Text(
            text = "İbrahim Can Erdoğan",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(30.dp)
                .weight(40f)
        )
    }
}

@Preview(showBackground = true, locale = "tr")
@Composable
fun MainPagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        MainPage()
    }
}