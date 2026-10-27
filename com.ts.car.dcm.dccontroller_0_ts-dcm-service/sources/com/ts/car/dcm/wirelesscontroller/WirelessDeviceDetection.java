package com.ts.car.dcm.wirelesscontroller;

/* JADX INFO: loaded from: classes.dex */
public abstract class WirelessDeviceDetection {
    public abstract void detectWirelessDevice(String str, WirelessConnection wirelessConnection);

    public abstract void removeDetectDevice(String str);

    public abstract void startDetectDevice();

    public abstract void stopDetectDevice();
}
