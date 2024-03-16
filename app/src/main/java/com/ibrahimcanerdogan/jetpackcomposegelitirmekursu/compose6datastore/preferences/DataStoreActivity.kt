package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose6datastore.preferences

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose6datastore.ui.theme.JetpackComposeGeliştirmeKursuTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataStoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                DataStoreExample()
            }
        }
    }
}

@Composable
fun DataStoreExample() {
    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val countValue = remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = true) {
        val job = CoroutineScope(Dispatchers.Main).launch {

            // WRITE
            // Bir kere kaydedilmesi yeterlidir. Kalıcı olarak uygulama kaldırılıncaya kadar saklanır, silinmedipi sürece...
            appDataStore.registerName("Ibrahim Can Erdogan")

            val listData = HashSet<String>()
            listData.add("Can")
            listData.add("Erdogan")

            appDataStore.registerList(listData)

            // READ
            val registeredName = appDataStore.readName()
            Log.i("DataStoreActivity", registeredName)
            val registeredList = appDataStore.readList()
            registeredList.forEach {
                Log.i("DataStoreActivity", it)
            }


            appDataStore.removeName()
            val registeredNameNew = appDataStore.readName()

            Log.i("DataStoreActivity", registeredNameNew)

            // Counter
            var cValue = appDataStore.readCounter()
            countValue.intValue = ++cValue
            appDataStore.registerCounter(cValue)

        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Giriş Sayısı: ${countValue.intValue}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DataStoreExamplePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        DataStoreExample()
    }
}