package com.ts.car.dcm.wirelesscontroller.wifi;

import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
public interface WifiCallback {
    void onNetworkStateChanged(NetworkInfo.State state, WifiDeviceInfo wifiDeviceInfo);

    void onWifiApStateChanged(int i);
}
