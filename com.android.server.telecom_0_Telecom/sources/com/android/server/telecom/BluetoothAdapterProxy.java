package com.android.server.telecom;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothAdapterProxy {
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public boolean getProfileProxy(Context context, BluetoothProfile.ServiceListener serviceListener, int i) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            return false;
        }
        return bluetoothAdapter.getProfileProxy(context, serviceListener, i);
    }

    public boolean setActiveDevice(BluetoothDevice bluetoothDevice, int i) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            return false;
        }
        if (bluetoothDevice != null) {
            return bluetoothAdapter.setActiveDevice(bluetoothDevice, i);
        }
        return bluetoothAdapter.removeActiveDevice(i);
    }
}
