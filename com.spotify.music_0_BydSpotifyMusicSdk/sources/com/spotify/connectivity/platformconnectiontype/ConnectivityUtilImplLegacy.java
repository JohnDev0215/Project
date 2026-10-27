package com.spotify.connectivity.platformconnectiontype;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.spotify.connectivity.connectiontype.ConnectionType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityUtilImplLegacy implements ConnectivityUtil {
    private static final int TYPE_COMPANION_PROXY = 16;
    private static final int TYPE_MOBILE_IMS = 11;
    private static final int TYPE_WIFI_P2P = 13;

    private static List<NetworkCapabilities> getAllInternetCapabilites(ConnectivityManager connectivityManager) {
        ArrayList arrayList = new ArrayList();
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (hasUnrestrictedInternetCapabilities(networkCapabilities)) {
                arrayList.add(networkCapabilities);
            }
        }
        return arrayList;
    }

    private static List<NetworkInfo> getConnectedNetworkInfos(ConnectivityManager connectivityManager) {
        ArrayList arrayList = new ArrayList();
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo != null && networkInfo.isConnected()) {
                arrayList.add(networkInfo);
            }
        }
        return arrayList;
    }

    private ConnectionType getMobileConnectionType(int i) {
        switch (i) {
            case 0:
                return ConnectionType.CONNECTION_TYPE_UNKNOWN;
            case 1:
            case 4:
            case 11:
                return ConnectionType.CONNECTION_TYPE_GPRS;
            case 2:
                return ConnectionType.CONNECTION_TYPE_EDGE;
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return ConnectionType.CONNECTION_TYPE_3G;
            case 13:
                return ConnectionType.CONNECTION_TYPE_4G;
            default:
                return ConnectionType.CONNECTION_TYPE_3G;
        }
    }

    private static boolean hasUnrestrictedInternetCapabilities(NetworkCapabilities networkCapabilities) {
        return networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(13);
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(Context context) {
        return getConnectionType((ConnectivityManager) context.getSystemService("connectivity"), (TelephonyManager) context.getSystemService("phone"));
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(ConnectivityManager connectivityManager) {
        return getConnectionType(connectivityManager, (TelephonyManager) null);
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(Context context, Network network) {
        return ConnectionType.CONNECTION_TYPE_NONE;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(NetworkCapabilities networkCapabilities) {
        return ConnectionType.CONNECTION_TYPE_NONE;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(ConnectivityManager connectivityManager, TelephonyManager telephonyManager) {
        if (connectivityManager == null) {
            return ConnectionType.CONNECTION_TYPE_NONE;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return getConnectionType(activeNetworkInfo, telephonyManager);
        }
        List<NetworkInfo> connectedNetworkInfos = getConnectedNetworkInfos(connectivityManager);
        if (!connectedNetworkInfos.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<NetworkInfo> it = connectedNetworkInfos.iterator();
            while (it.hasNext()) {
                arrayList.add(getConnectionType(it.next(), telephonyManager));
            }
            Collections.sort(arrayList, Collections.reverseOrder());
            return (ConnectionType) arrayList.get(0);
        }
        List<NetworkCapabilities> allInternetCapabilites = getAllInternetCapabilites(connectivityManager);
        if (!allInternetCapabilites.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<NetworkCapabilities> it2 = allInternetCapabilites.iterator();
            while (it2.hasNext()) {
                arrayList2.add(getConnectionType(it2.next(), telephonyManager));
            }
            Collections.sort(arrayList2, Collections.reverseOrder());
            return (ConnectionType) arrayList2.get(0);
        }
        return ConnectionType.CONNECTION_TYPE_NONE;
    }

    @SuppressLint({"MissingPermission"})
    private ConnectionType getConnectionType(NetworkCapabilities networkCapabilities, TelephonyManager telephonyManager) {
        if (!hasUnrestrictedInternetCapabilities(networkCapabilities)) {
            return ConnectionType.CONNECTION_TYPE_NONE;
        }
        if (networkCapabilities.hasTransport(3)) {
            return ConnectionType.CONNECTION_TYPE_ETHERNET;
        }
        if (networkCapabilities.hasTransport(1)) {
            return ConnectionType.CONNECTION_TYPE_WLAN;
        }
        if (!networkCapabilities.hasTransport(0)) {
            networkCapabilities.toString();
            return ConnectionType.CONNECTION_TYPE_3G;
        }
        if (telephonyManager != null) {
            return getMobileConnectionType(telephonyManager.getNetworkType());
        }
        return ConnectionType.CONNECTION_TYPE_3G;
    }

    @SuppressLint({"MissingPermission"})
    private ConnectionType getConnectionType(NetworkInfo networkInfo, TelephonyManager telephonyManager) {
        int type = networkInfo.getType();
        switch (type) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                if (telephonyManager != null) {
                    return getMobileConnectionType(telephonyManager.getNetworkType());
                }
                return ConnectionType.CONNECTION_TYPE_3G;
            case 1:
            case 13:
                return ConnectionType.CONNECTION_TYPE_WLAN;
            case 6:
            case 7:
            case 17:
                return ConnectionType.CONNECTION_TYPE_3G;
            case 8:
            case 10:
            case 12:
            case 14:
            case 15:
            default:
                if (ConnectivityManager.isNetworkTypeValid(type)) {
                    networkInfo.getTypeName();
                }
                return ConnectionType.CONNECTION_TYPE_UNKNOWN;
            case 9:
                return ConnectionType.CONNECTION_TYPE_ETHERNET;
            case 11:
                return ConnectionType.CONNECTION_TYPE_GPRS;
            case 16:
                return ConnectionType.CONNECTION_TYPE_COMPANION_PROXY;
        }
    }
}
