package com.example.eventsnap.util;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;

public class AnalyticsLogger {

    public static void logEventAdded(Context context, String id, String name) {
        Bundle bundle = new Bundle();
        bundle.putString("event_id", id);
        bundle.putString("event_name", name);

        FirebaseAnalytics.getInstance(context)
                .logEvent("event_added", bundle);
    }

    public static void logEventViewed(Context context,String id, String name) {
        Bundle bundle = new Bundle();
        bundle.putString("event_id", id);
        bundle.putString("event_name", name);
        MyApplication.getAnalytics().logEvent("event_viewed", bundle);
    }

}
