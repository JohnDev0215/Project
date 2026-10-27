package com.byd.dilink.provider;

import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkBluetoothDeviceUtils {
    void deleteDevice(ContentResolver contentResolver, BluetoothDevice bluetoothDevice);

    boolean deviceExist(ContentResolver contentResolver, BluetoothDevice bluetoothDevice);

    void insertDevice(ContentResolver contentResolver, BluetoothDevice bluetoothDevice);

    boolean isMobile(BluetoothDevice bluetoothDevice);

    Queue<BluetoothDevice> queryAutoConnectDevice(ContentResolver contentResolver);

    int queryDeviceParam(ContentResolver contentResolver, BluetoothDevice bluetoothDevice, int i);

    HashMap<Integer, Integer> queryDeviceParams(ContentResolver contentResolver, BluetoothDevice bluetoothDevice);

    BluetoothDevice queryLastConnectDevice(ContentResolver contentResolver);

    List<BluetoothDevice> queryTimerConnectDevice(ContentResolver contentResolver);

    void updateDeviceParam(ContentResolver contentResolver, BluetoothDevice bluetoothDevice, int i, int i2);

    void updateTime(ContentResolver contentResolver, BluetoothDevice bluetoothDevice, String str);
}
