package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose8advancedandroid.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.R

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        createNotification(applicationContext)
        return Result.success()
    }

    private fun createNotification(context: Context) {
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
}