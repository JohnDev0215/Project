package com.ts.car.dcm.common.controller;

import android.hardware.usb.UsbDevice;

/* JADX INFO: loaded from: classes.dex */
public abstract class UsbConnectionController extends ConnectionController {
    private static final String TAG = "UsbConnectionController";

    public abstract boolean connectUsbDevice(String str);

    public abstract boolean deleteUsbConnection(String str);

    public abstract boolean disconnectUsbDevice(String str);

    public abstract boolean doUsbRoleSwitch(String str, int i, int i2);

    public abstract boolean enableUsbAoA(String str, int i, int i2);

    public abstract boolean enableUsbAoA(String str, String str2, int i, int i2);

    public abstract UsbDevice getUsbConnectedDevices(String str);

    public abstract boolean removeUsbDevice(String str);

    public abstract boolean resetUsbBus(String str);

    public abstract void updateCarlifeSupportStatus(int i, boolean z);
}
