package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothRemoteGattService {
    public long a;
    public final Wrappers$BluetoothGattServiceWrapper b;
    public final String c;
    public final ChromeBluetoothDevice d;

    public ChromeBluetoothRemoteGattService(long j, Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.a = j;
        this.b = wrappers$BluetoothGattServiceWrapper;
        this.c = str;
        this.d = chromeBluetoothDevice;
    }

    @CalledByNative
    public static ChromeBluetoothRemoteGattService create(long j, Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattService(j, wrappers$BluetoothGattServiceWrapper, str, chromeBluetoothDevice);
    }

    @CalledByNative
    public final void createCharacteristics() {
        Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper = this.b;
        List<BluetoothGattCharacteristic> characteristics = wrappers$BluetoothGattServiceWrapper.a.getCharacteristics();
        ArrayList<Wrappers$BluetoothGattCharacteristicWrapper> arrayList = new ArrayList(characteristics.size());
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
            Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = wrappers$BluetoothGattServiceWrapper.b;
            Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = wrappers$BluetoothDeviceWrapper.b.get(bluetoothGattCharacteristic);
            if (wrappers$BluetoothGattCharacteristicWrapper == null) {
                wrappers$BluetoothGattCharacteristicWrapper = new Wrappers$BluetoothGattCharacteristicWrapper(bluetoothGattCharacteristic, wrappers$BluetoothDeviceWrapper);
                wrappers$BluetoothDeviceWrapper.b.put(bluetoothGattCharacteristic, wrappers$BluetoothGattCharacteristicWrapper);
            }
            arrayList.add(wrappers$BluetoothGattCharacteristicWrapper);
        }
        for (Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper2 : arrayList) {
            N.MM2CqKoK(this.a, this, this.c + "/" + wrappers$BluetoothGattCharacteristicWrapper2.a.getUuid().toString() + "," + wrappers$BluetoothGattCharacteristicWrapper2.a.getInstanceId(), wrappers$BluetoothGattCharacteristicWrapper2, this.d);
        }
    }

    @CalledByNative
    public final String getUUID() {
        return this.b.a.getUuid().toString();
    }

    @CalledByNative
    public final void onBluetoothRemoteGattServiceAndroidDestruction() {
        this.a = 0L;
    }
}
