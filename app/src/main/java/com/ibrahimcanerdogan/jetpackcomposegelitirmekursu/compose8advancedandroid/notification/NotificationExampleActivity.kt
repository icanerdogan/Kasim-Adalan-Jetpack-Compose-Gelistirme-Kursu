package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose8advancedandroid.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.R
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose8advancedandroid.notification.ui.theme.JetpackComposeGeliştirmeKursuTheme

class NotificationExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                NotificationExamplePage()
            }
        }
    }
}

@Composable
fun NotificationExamplePage() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            createNotification(context)
        }) {
            Text(text = "Bildirim Oluştur!")
        }
    }
}

fun createNotification(context: Context) {
    val builder: NotificationCompat.Builder
    val builderManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Kanal yapısı ile bildirimler gruplandırılabilmektedir.
        val channelID = "channelID"
        val channelName = "channelName"
        val channelDescription = "channelDescription"
        val channelPriority = NotificationManager.IMPORTANCE_HIGH

        var channel: NotificationChannel? = builderManager.getNotificationChannel(channelID)

        if (channel == null) {
            channel = NotificationChannel(channelID, channelName, channelPriority)
            channel.description = channelDescription
            builderManager.createNotificationChannel(channel)
        }

        builder = NotificationCompat.Builder(context, channelID)
        builder.setContentTitle("Notification Title")
            .setContentText("Notification Content")
            .setSmallIcon(R.drawable.icon_notification)
            .setAutoCancel(true)

    } else {
        builder = NotificationCompat.Builder(context)
        builder.setContentTitle("Notification Title")
            .setContentText("Notification Content")
            .setSmallIcon(R.drawable.icon_notification)
            .setAutoCancel(true)
            .priority = Notification.PRIORITY_HIGH

    }

    builderManager.notify(1, builder.build())


}

@Preview(showBackground = true)
@Composable
fun NotificationExamplePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        NotificationExamplePage()
    }
}