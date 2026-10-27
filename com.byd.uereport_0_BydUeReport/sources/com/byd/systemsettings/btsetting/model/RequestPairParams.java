package com.byd.systemsettings.btsetting.model;

import android.bluetooth.BluetoothDevice;
import com.byd.mvp.model.BydData;
import m4.d;

/* JADX INFO: loaded from: classes2.dex */
public class RequestPairParams extends BydData {
    public BluetoothDevice device;
    public boolean needToInput;
    public int pairCode;

    public RequestPairParams(BluetoothDevice bluetoothDevice, boolean z7, int i8) {
        this.device = bluetoothDevice;
        this.needToInput = z7;
        this.pairCode = i8;
    }

    public String toString() {
        return "RequestPairParams{device=" + d.c(this.device) + ", needToInput=" + this.needToInput + ", pairCode=" + this.pairCode + '}';
    }
}
