package com.alian.application.avatarapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnectionValidator {
    public static boolean checkConnectionError(Throwable t) {
        if (t == null) {
            return false;
        }

        if (t.getCause() != null && t.getCause().toString().contains("EAI_NODATA")) {
            return true;
        }

        if (t.getMessage() != null && t.getMessage().contains("No address associated with hostname")) {
            return true;
        }

        return t.getMessage() != null && t.getMessage().contains("Network is unreachable");

    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();    }
}
