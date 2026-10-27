package com.android.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

/* JADX INFO: loaded from: classes.dex */
public interface IObexConnectionHandler {
    void onAcceptFailed();

    boolean onConnect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket);
}
