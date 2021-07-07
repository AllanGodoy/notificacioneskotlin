package com.example.notificaciones

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.stream.IntStream

class MainActivity : AppCompatActivity() {

   private val CHANNEL_ID = "com.example.myapplication"
    private val notificicationId =101
    private var  builder = NotificationCompat.Builder(this  , CHANNEL_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        var varbutton: Button = findViewById(R.id.button)


        varbutton.setOnClickListener(View.OnClickListener {
            sendNotification()

            Toast.makeText(this , "apresionaste", Toast.LENGTH_LONG).show()

        })
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "notification title"
            val descriptionText ="Notification Description"
            val importance : Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText

            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification(){
        val intent = Intent(this, MainActivity::class.java).apply{
            flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0, intent,0)

        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.facebook)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.facebook)




       val builder =NotificationCompat.Builder(this, CHANNEL_ID)
               .setSmallIcon(R.drawable.ic_launcher_foreground)
               .setContentTitle("Exemplo titles")
               .setContentText("Example Description")
               .setLargeIcon(bitmapLargeIcon)
               .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
               .setContentIntent(pendingIntent)
               .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificicationId, builder.build())

        }


    }

}