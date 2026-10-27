package com.byd.systemsettings.wifi2.model;

import android.net.NetworkInfo;
import com.byd.bt.model.ConnectState;
import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class WifiConnectParams extends BydData {
    public NetworkInfo info;
    public ConnectState state;

    public WifiConnectParams(ConnectState connectState, NetworkInfo networkInfo) {
        this.state = connectState;
        this.info = networkInfo;
    }

    public String toString() {
        return "WifiConnectParams{state=" + this.state + ", info=" + this.info + '}';
    }
}
