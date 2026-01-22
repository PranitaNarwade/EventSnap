package com.example.eventsnap.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isNetworkAvailable(Context mContext) {

        boolean isResult = Boolean.FALSE;
        final ConnectivityManager connManager = (ConnectivityManager) mContext.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        if (netInfo == null) {
            // Toast.makeText(mContext,"Slow or no internet connection.Please check your internet settings.", Toast.LENGTH_LONG).show();
        } else {
            if (netInfo.isConnected() && netInfo.isAvailable() ||
                    (netInfo.getType() != ConnectivityManager.TYPE_WIFI
                            && netInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                isResult = true;
            }
        }

        return isResult;
    }
}

