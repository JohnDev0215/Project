package com.byd.systemsettings.wifi2.callback;

import android.net.NetworkInfo;
import com.byd.bt.model.ConnectState;

/* JADX INFO: loaded from: classes2.dex */
public interface IAPConnectStateCallback extends WifiCallback {
    void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo);
}
