package com.example.notification_kotlin

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hotchemi.android.rate.AppRate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChanel: NotificationChannel
    lateinit var bulder: Notification.Builder
    private var channelId = "com.example.notification_kotlin"
    private var des = "Test Notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn_notification.setOnClickListener {
            val intent = Intent(this, LauncherActivity::class.java)
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChanel =
                    NotificationChannel(channelId, des, NotificationManager.IMPORTANCE_HIGH)
                notificationChanel.enableLights(true)
                notificationChanel.lightColor = Color.GREEN
                notificationChanel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChanel)
                bulder = Notification.Builder(this, channelId)
                    .setContentTitle("Brainvire Info Tech")
                    .setContentText("Welcome to Brainvire")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_baseline_notifications_24
                        )
                    )
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234, bulder.build())
        }

        AppRate.with(this)
            .setInstallDays(0)
            .setLaunchTimes(2)
            .setRemindInterval(2)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)
        AppRate.with(this).clearAgreeShowDialog()
        AppRate.with(this).shouldShowRateDialog()

    }
}
