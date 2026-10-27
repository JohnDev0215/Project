package com.ts.car.dcm.common.controller;

import com.ts.car.dcm.common.data.LinkDevice;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public abstract class WirelessConnectionController extends ConnectionController {
    private static final String TAG = "WirelessConnectionController";

    public abstract boolean connectBtDevices(String str);

    public abstract boolean connectBtProfile(String str, int i);

    public abstract boolean deleteBtConnection(String str);

    public abstract boolean deleteBtDevice(String str);

    public abstract boolean disconnectBtDevice(String str);

    public abstract boolean disconnectBtProfile(String str, int i);

    public abstract boolean disconnectOtherBtDevices(String str);

    public abstract boolean enableBluetooth(boolean z);

    public abstract boolean enableWifiAp(boolean z, WifiApConfiguration wifiApConfiguration);

    public abstract BtOobInfo getBtOobInfo();

    public abstract LinkDevice getWifiLinkDeviceByIpaddr(String str);

    public abstract boolean isBluetoothEnabled();

    public abstract boolean reattachWifiDevice(String str);

    public abstract boolean removeWifiDevice(String str);

    public abstract boolean requestBluetoothPairing(String str);

    public abstract boolean setBtOobInfo(String str, String str2, String str3, int i);
}
