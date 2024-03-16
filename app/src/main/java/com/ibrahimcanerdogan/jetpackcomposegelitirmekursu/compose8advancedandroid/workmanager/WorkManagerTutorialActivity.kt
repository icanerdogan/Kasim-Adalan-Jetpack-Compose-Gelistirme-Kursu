package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose8advancedandroid.workmanager

import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose8advancedandroid.workmanager.ui.theme.JetpackComposeGeliştirmeKursuTheme
import java.util.concurrent.TimeUnit

class WorkManagerTutorialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                WorkManagerTutorialPage()
            }
        }
    }
}
/*
Eğer uygulama arka plandan uygulama silinirse;
 - Bildirim,
 - Mesajlaşma işlemlerine izin verilmektedir.
Eğer silinmemişse herhangi bir kısıtlama bulunmamaktadır.

*/
@Composable
fun WorkManagerTutorialPage() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            /*
            // Çalışma koşulu ile birlikte worker şartlı çalıştırılabilir.
            val workCondition = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val request = OneTimeWorkRequestBuilder<ManagerWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(workCondition)
                .build()
            */

            // Değer minimum 15 dakika olarak seçilebilmektedir.
            val request = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                // 10 saniye sonra başlayıp, 15 dakikada bir devam edevektir.
                // repeatInterval ve duration aynı olması gerekmektedir.
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }) {
            Text(text = "Work!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkManagerTutorialPagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        WorkManagerTutorialPage()
    }
}