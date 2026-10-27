package com.byd.systemsettings.wifi2.callback;

import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import com.byd.bt.model.ConnectState;

/* JADX INFO: loaded from: classes.dex */
public interface IWifiConnectStateCallback extends WifiCallback {
    default void onCipherTypeErr() {
    }

    default void onConnectFailed(int i) {
    }

    void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo);

    default void onSupplicantStateChanged(SupplicantState supplicantState, int i) {
    }
}
