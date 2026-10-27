package com.byd.systemsettings.wifi2.callback;

import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import com.byd.bt.model.ConnectState;

/* JADX INFO: loaded from: classes2.dex */
public interface IWifiConnectStateCallback extends WifiCallback {
    default void onCipherTypeErr() {
    }

    default void onConnectFailed(int i8) {
    }

    void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo);

    default void onSupplicantStateChanged(SupplicantState supplicantState, int i8) {
    }
}
