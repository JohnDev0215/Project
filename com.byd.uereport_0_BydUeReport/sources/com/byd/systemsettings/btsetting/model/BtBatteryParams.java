package com.byd.systemsettings.btsetting.model;

import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.BTPowerLevel;
import com.byd.mvp.model.BydData;
import m4.d;

/* JADX INFO: loaded from: classes2.dex */
public class BtBatteryParams extends BydData {
    public BluetoothDevice device;
    public BTPowerLevel level;

    public BtBatteryParams(BluetoothDevice bluetoothDevice, BTPowerLevel bTPowerLevel) {
        this.device = bluetoothDevice;
        this.level = bTPowerLevel;
    }

    public String toString() {
        return "BtBatteryParams{device=" + d.c(this.device) + ", level=" + this.level + '}';
    }
}
