package com.android.server.telecom;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothHeadsetProxy {
    private BluetoothHeadset mBluetoothHeadset;

    public BluetoothHeadsetProxy(BluetoothHeadset bluetoothHeadset) {
        this.mBluetoothHeadset = bluetoothHeadset;
    }

    public void clccResponse(int i, int i2, int i3, int i4, boolean z, String str, int i5) {
        this.mBluetoothHeadset.clccResponse(i, i2, i3, i4, z, str, i5);
    }

    public void phoneStateChanged(int i, int i2, int i3, String str, int i4, String str2) {
        this.mBluetoothHeadset.phoneStateChanged(i, i2, i3, str, i4, str2);
    }

    public int getAudioState(BluetoothDevice bluetoothDevice) {
        return this.mBluetoothHeadset.getAudioState(bluetoothDevice);
    }

    public boolean connectAudio() {
        return this.mBluetoothHeadset.connectAudio();
    }

    public BluetoothDevice getActiveDevice() {
        return this.mBluetoothHeadset.getActiveDevice();
    }

    public boolean isAudioOn() {
        return this.mBluetoothHeadset.isAudioOn();
    }

    public boolean disconnectAudio() {
        return this.mBluetoothHeadset.disconnectAudio();
    }

    public boolean isInbandRingingEnabled() {
        return this.mBluetoothHeadset.isInbandRingingEnabled();
    }
}
