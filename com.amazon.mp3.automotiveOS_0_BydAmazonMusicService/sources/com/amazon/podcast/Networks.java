package com.amazon.podcast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* JADX INFO: loaded from: classes5.dex */
public final class Networks {
    public static boolean isNetworkConnected(final Context context) {
        ConnectivityManager connectivityManager;
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        if (context == null || (activeNetwork = (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")).getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(2);
    }
}
