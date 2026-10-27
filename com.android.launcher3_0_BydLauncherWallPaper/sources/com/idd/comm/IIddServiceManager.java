package com.idd.comm;

import android.content.Context;
import com.idd.bluetooth.IIddBluetooth;
import com.idd.location.IIddLocation;
import com.idd.wifi.IIddWifi;

/* JADX INFO: loaded from: classes4.dex */
public interface IIddServiceManager {
    IIddBluetooth getBluetooth();

    IIddLocation getLocation();

    IIddWifi getWifi();

    void init(Context context);

    void uninit();
}
