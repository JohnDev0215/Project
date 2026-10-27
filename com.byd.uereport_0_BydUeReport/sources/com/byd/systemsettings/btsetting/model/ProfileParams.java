package com.byd.systemsettings.btsetting.model;

import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.ConnectState;
import com.byd.mvp.model.BydData;
import m4.d;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileParams extends BydData {
    public BluetoothDevice device;
    public int profile;
    public ConnectState state;

    public ProfileParams(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState) {
        this.profile = i8;
        this.device = bluetoothDevice;
        this.state = connectState;
    }

    public String toString() {
        return "ProfileParams{profile=" + this.profile + ", device=" + d.c(this.device) + ", state=" + this.state + '}';
    }
}
