package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.telephony.TelephonyManager;
import com.spotify.connectivity.connectiontype.ConnectionType;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectivityUtil {
    ConnectionType getConnectionType(Context context);

    ConnectionType getConnectionType(Context context, Network network);

    ConnectionType getConnectionType(ConnectivityManager connectivityManager);

    ConnectionType getConnectionType(ConnectivityManager connectivityManager, TelephonyManager telephonyManager);

    ConnectionType getConnectionType(NetworkCapabilities networkCapabilities);
}
