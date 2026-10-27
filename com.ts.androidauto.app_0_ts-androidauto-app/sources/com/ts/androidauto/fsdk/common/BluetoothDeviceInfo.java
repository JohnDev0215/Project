package com.ts.androidauto.fsdk.common;

import android.bluetooth.BluetoothDevice;
import com.ts.androidauto.fsdk.common.BluetoothConst;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothDeviceInfo {
    private boolean mA2dpConnected;
    private String mAddress;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothConst.BtPhoneStatus mCallState;
    private boolean mHfpConnected;
    private boolean mIsPaired;
    private String mName;

    public BluetoothConst.BtPhoneStatus getCallState() {
        return this.mCallState;
    }

    public void setCallState(BluetoothConst.BtPhoneStatus callState) {
        this.mCallState = callState;
    }

    public boolean isA2dpConnected() {
        return this.mA2dpConnected;
    }

    public void setA2dpConnected(boolean a2dpConnected) {
        this.mA2dpConnected = a2dpConnected;
    }

    public boolean isHfpConnected() {
        return this.mHfpConnected;
    }

    public void setHfpConnected(boolean hfpConnected) {
        this.mHfpConnected = hfpConnected;
    }

    public boolean isPaired() {
        return this.mIsPaired;
    }

    public void setPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.mBluetoothDevice = bluetoothDevice;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
