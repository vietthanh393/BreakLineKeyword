package com.vietthanh.tikidisplaykeyword.common.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtil {
    public static boolean isNetworkConnected(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
