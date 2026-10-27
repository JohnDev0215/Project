package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class Wrappers$BluetoothDeviceWrapper {
    public final BluetoothDevice a;
    public final HashMap<BluetoothGattCharacteristic, Wrappers$BluetoothGattCharacteristicWrapper> b = new HashMap<>();
    public final HashMap<BluetoothGattDescriptor, Wrappers$BluetoothGattDescriptorWrapper> c = new HashMap<>();

    public Wrappers$BluetoothDeviceWrapper(BluetoothDevice bluetoothDevice) {
        this.a = bluetoothDevice;
    }
}
