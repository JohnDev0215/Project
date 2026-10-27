package com.android.car.developeroptions.development;

import android.bluetooth.BluetoothA2dp;

/* JADX INFO: loaded from: classes.dex */
public interface BluetoothServiceConnectionListener {
    void onBluetoothCodecUpdated();

    void onBluetoothServiceConnected(BluetoothA2dp bluetoothA2dp);

    void onBluetoothServiceDisconnected();
}
