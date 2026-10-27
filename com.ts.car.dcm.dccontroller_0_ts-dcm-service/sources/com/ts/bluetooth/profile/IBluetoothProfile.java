package com.ts.bluetooth.profile;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothProfile {
    public static final int PRIORITY_OFF = 0;
    public static final int PRIORITY_ON = 100;

    void close();

    boolean connect(BluetoothDevice bluetoothDevice);

    boolean disconnect(BluetoothDevice bluetoothDevice);

    int getPriority(BluetoothDevice bluetoothDevice);

    int getProfileCode();

    boolean isAvailable();

    boolean isPreferred(BluetoothDevice bluetoothDevice);

    boolean setPriority(BluetoothDevice bluetoothDevice, int i);
}
