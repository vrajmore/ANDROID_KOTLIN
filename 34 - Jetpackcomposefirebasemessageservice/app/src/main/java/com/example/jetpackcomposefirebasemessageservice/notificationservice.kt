package com.example.jetpackcomposefirebasemessageservice

import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.remoteMessage

class notificationservice:FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAGTOKEN", "onNewToken: $token")

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
//            shownotification(this,it?.title.toString(),it?.body.toString(),it?.icon.hashCode())
            Log.d("TAG", "onMessageReceived: ${it.title}")
            Log.d("TAG", "onMessageReceived: ${it.body}")
            shownotification(this,it.title.toString(),it.body.toString())
        }
    }


}