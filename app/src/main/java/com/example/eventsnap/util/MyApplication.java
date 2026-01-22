package com.example.eventsnap.util;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MyApplication extends Application {
    private static FirebaseAnalytics analytics;

    @Override
    public void onCreate() {
        super.onCreate();
        analytics = FirebaseAnalytics.getInstance(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "event_channel",
                    "Event Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager =
                    getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }

    public static FirebaseAnalytics getAnalytics() {
        return analytics;
    }

}
