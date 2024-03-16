package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose3widget

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose3widget.ui.theme.JetpackComposeGeliştirmeKursuTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

class MaterialComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MatPage()
                }
            }
        }
    }
}

@Composable
fun MatPage() {

    val context =  LocalContext.current

    val tf = remember { mutableStateOf("") }
    val data = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gelen Veri: ${data.value}",
            modifier = Modifier
                .padding(5.dp)
                .border(BorderStroke(1.dp, Color.Magenta))
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.Start),
            color = Color.Yellow,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                background = Color.Gray
            )
        )

        OutlinedTextField(
            value = tf.value,
            onValueChange = {
                tf.value = it
            },
            label = { Text(text = "Veri Giriniz!") },
            colors = TextFieldDefaults.colors(
                //setting the text field background when it is focused
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                //setting the text field background when it is unfocused or initial state
                unfocusedContainerColor = Color.Red,
                //setting the text field background when it is disabled
                disabledContainerColor = MaterialTheme.colorScheme.onError,
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedButton(
            onClick = {
                data.value = tf.value
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.Magenta,
            ),
            border = BorderStroke(1.dp, Color.Green),
            shape = RoundedCornerShape(20)
        ) {
            Text(text = "Veri Al!")
        }

        ExtendedFloatingActionButton(
            onClick = { Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show() },
            containerColor = Color.Magenta,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "")
            Text(text = "Ürün Ekle", modifier = Modifier.padding(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatPagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        MatPage()
    }
}