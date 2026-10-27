package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public interface BluetoothFeatureProvider {
    Uri getBluetoothDeviceSettingsUri(BluetoothDevice bluetoothDevice);
}
