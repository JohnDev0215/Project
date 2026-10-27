package com.byd.systemsettings.btsetting.model;

import android.bluetooth.BluetoothDevice;
import com.byd.mvp.model.BydData;
import m4.d;

/* JADX INFO: loaded from: classes2.dex */
public class BondFailedParams extends BydData {
    public BluetoothDevice device;
    public int errCode;

    public BondFailedParams(BluetoothDevice bluetoothDevice, int i8) {
        this.device = bluetoothDevice;
        this.errCode = i8;
    }

    public String toString() {
        return "BondFailedParams{device=" + d.c(this.device) + ", errCode=" + this.errCode + '}';
    }
}
