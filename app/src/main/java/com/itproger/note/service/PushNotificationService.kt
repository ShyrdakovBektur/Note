package com.itproger.note.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.itproger.note.R

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class PushNotificationService: FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val chennel = NotificationChannel(
            CHENNEL_ID,
            "Heads Up Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(chennel)
   val notification = Notification.Builder(this, CHENNEL_ID)
    notification.setSmallIcon(R.mipmap.ic_launcher)
        notification.setContentTitle(message.notification?.title)
        notification.setContentText(message.notification?.body)
        notification.setAutoCancel(false)
    }

    companion object{
        const val CHENNEL_ID = "HEADS_UP_NOTIFICATION"
    }
}