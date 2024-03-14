package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.datastore.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.datastore.ui.theme.JetpackComposeGeliştirmeKursuTheme

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
}

@Preview(showBackground = true)
@Composable
fun RoomExamplePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        RoomExample()
    }
}