package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.mvvmstructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.mvvmstructure.ui.theme.JetpackComposeGeliştirmeKursuTheme

class ExampleMVVMActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                CalculatorPage()
            }
        }
    }
}

@Composable
fun CalculatorPage() {
    val textField1 = remember { mutableStateOf("") }
    val textField2 = remember { mutableStateOf("") }
    val viewModel: CalculateViewModel = viewModel()

    // val result = remember { mutableIntStateOf(0) }
    val result = viewModel.resultData.observeAsState(0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = result.value.toString(), fontSize = 50.sp)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = textField1.value,
            onValueChange = {
                textField1.value = it
            },
            label = { Text(text = "Number First") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = textField2.value,
            onValueChange = {
                textField2.value = it
            },
            label = { Text(text = "Number Two") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            modifier = Modifier
                .size(200.dp, 50.dp)
                .fillMaxWidth()
                .padding(1.dp, 1.dp, 1.dp, 1.dp),
            onClick = {
                viewModel.calculateSum(textField1.value, textField2.value)

                // result.intValue = viewModel.result
            }) {
            Text(text = "Toplama")
        }

        Button(
            modifier = Modifier
                .size(200.dp, 50.dp)
                .fillMaxWidth()
                .padding(1.dp, 1.dp, 1.dp, 1.dp),
            onClick = {
                viewModel.calculateMultiplication(textField1.value, textField2.value)

                // result.intValue = viewModel.result
            }) {
            Text(text = "Çarpma")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        CalculatorPage()
    }
}