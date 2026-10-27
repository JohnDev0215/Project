package com.ts.androidauto.fsdk.interfaces.wifi;

/* JADX INFO: loaded from: classes2.dex */
public interface IWifiApControllerAdapter {
    void deInit();

    int[] getSupported5GWifiChannels();

    void init();

    void registWifiApListener(IWifiApControllerListener iWifiApControllerListener);

    void startWifiAp(int i, int i2, int i3);
}
