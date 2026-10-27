package com.ts.dm.lib.devicemanager.bluetooth;

import android.content.Context;
import android.os.Looper;
import com.ts.car.sdk.dm.data.DmDevice;
import java.util.List;
import ts.car.dm.fsdk.interfaces.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothDeviceManager {

    public interface OnBluetoothDeviceStatusChangedListener {
        void onBluetoothStateChanged(int i);

        void onDeviceBondStatusChanged(BluetoothDevice bluetoothDevice, int i);

        void onDeviceNameChanged(BluetoothDevice bluetoothDevice);

        void onDeviceProfileStatusChanged(BluetoothDevice bluetoothDevice, int i);
    }

    void checkProfileConnectStatus(DmDevice dmDevice);

    int connectProfile(DmDevice dmDevice, int i);

    int disconnectProfile(DmDevice dmDevice, int i);

    List<BluetoothDevice> getBondedDevices();

    void registerOnBluetoothDeviceStatusChangedListener(OnBluetoothDeviceStatusChangedListener onBluetoothDeviceStatusChangedListener);

    boolean removeBond(String str);

    void unregisterOnBluetoothDeviceStatusChangedListener(OnBluetoothDeviceStatusChangedListener onBluetoothDeviceStatusChangedListener);

    static IBluetoothDeviceManager createDefaultInstance(Context context, Looper looper) {
        return new BluetoothDeviceManager(context, looper);
    }
}
