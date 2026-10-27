package com.ts.car.dcm.common.controller;

import android.net.wifi.SoftApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public interface IWirelessControllerCallback extends IControllerCallback {
    void notifyBtOobPairComplete(String str, int i);

    void onBtAudioStateChanged(String str, boolean z);

    void onBtCallStateChanged(String str, boolean z);

    void onBtConnected(String str, int i);

    void onBtDeleted(String str, int i);

    void onBtDisconnected(String str, int i);

    void onBtPairStateChanged(String str, int i);

    void onBtProfileChanged(String str, int i, int i2);

    void onBtStateChanged(int i);

    void onCheckCpwlCapability(String str);

    void onDisconnectedOtherBtDevices(String str, int i);

    void onWifiApInfo(SoftApConfiguration softApConfiguration, int i);

    void onWifiConnectStatus(String str, int i);

    void onWirelessProjectionFound(String str);
}
