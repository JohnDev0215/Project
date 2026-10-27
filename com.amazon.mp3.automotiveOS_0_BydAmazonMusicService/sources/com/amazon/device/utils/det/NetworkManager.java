package com.amazon.device.utils.det;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkManager {
    private static final List<Integer> TYPE_WAN_LIST = Arrays.asList(0, 4, 5, 2, 3, 6);
    private static NetworkManager sNetworkManager;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final NetworkBroadcastReceiver mNetworkBroadcastReceiver;
    private final PowerManager mPowerManager;
    private final WifiManager mWifiManager;

    private NetworkManager(Context context) {
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mNetworkBroadcastReceiver = new NetworkBroadcastReceiver(context);
    }

    public static synchronized NetworkManager instance(Context context) {
        if (sNetworkManager == null) {
            sNetworkManager = new NetworkManager(context);
        }
        return sNetworkManager;
    }

    public boolean isWifiConnected() {
        return isConnectedToType(this.mConnectivityManager.getActiveNetworkInfo(), 1);
    }

    public boolean isEthernetConnected() {
        return isConnectedToType(this.mConnectivityManager.getActiveNetworkInfo(), 9);
    }

    public boolean isWanConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        Iterator<Integer> it = TYPE_WAN_LIST.iterator();
        while (it.hasNext()) {
            if (isConnectedToType(activeNetworkInfo, it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean isConnectedToType(NetworkInfo networkInfo, Integer num) {
        return networkInfo != null && networkInfo.isConnected() && num.intValue() == networkInfo.getType();
    }
}
