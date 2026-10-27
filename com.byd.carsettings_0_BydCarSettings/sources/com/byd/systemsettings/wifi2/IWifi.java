package com.byd.systemsettings.wifi2;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public interface IWifi {
    void close();

    void connect(String str, String str2);

    void createHotspot(String str, String str2);

    void createWifiLock();

    void disconnect();

    void init(Activity activity);

    void onRelease();

    void open();

    void startScan();

    void stopScan();
}
