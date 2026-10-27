package com.micphone.service;

/* JADX INFO: loaded from: classes.dex */
public interface IUsbDeviceEventListener {
    void onDongleEvent(int i, int i2, int i3);

    void onEndpointEvent(int i, int i2, int i3);

    void onKeyEvent(int i, int i2);

    void onLoopbackEvent(int i, int i2, int i3);

    void onOtaEvent(int i, int i2, int i3);
}
