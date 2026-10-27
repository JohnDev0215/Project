package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public interface LocalBluetoothProfile {
    int getConnectionStatus(BluetoothDevice bluetoothDevice);

    int getDrawableResource(BluetoothClass bluetoothClass);

    int getProfileId();

    boolean isProfileReady();

    boolean setEnabled(BluetoothDevice bluetoothDevice, boolean z);
}
