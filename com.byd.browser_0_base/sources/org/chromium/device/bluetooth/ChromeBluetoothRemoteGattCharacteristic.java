package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import defpackage.um0;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothRemoteGattCharacteristic {
    public long a;
    public final Wrappers$BluetoothGattCharacteristicWrapper b;
    public final String c;
    public final ChromeBluetoothDevice d;

    public ChromeBluetoothRemoteGattCharacteristic(long j, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.a = j;
        this.b = wrappers$BluetoothGattCharacteristicWrapper;
        this.c = str;
        this.d = chromeBluetoothDevice;
        chromeBluetoothDevice.e.put(wrappers$BluetoothGattCharacteristicWrapper, this);
    }

    @CalledByNative
    public static ChromeBluetoothRemoteGattCharacteristic create(long j, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattCharacteristic(j, wrappers$BluetoothGattCharacteristicWrapper, str, chromeBluetoothDevice);
    }

    @CalledByNative
    public final void createDescriptors() {
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = this.b;
        List<BluetoothGattDescriptor> descriptors = wrappers$BluetoothGattCharacteristicWrapper.a.getDescriptors();
        ArrayList<Wrappers$BluetoothGattDescriptorWrapper> arrayList = new ArrayList(descriptors.size());
        for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
            Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = wrappers$BluetoothGattCharacteristicWrapper.b;
            Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper = wrappers$BluetoothDeviceWrapper.c.get(bluetoothGattDescriptor);
            if (wrappers$BluetoothGattDescriptorWrapper == null) {
                wrappers$BluetoothGattDescriptorWrapper = new Wrappers$BluetoothGattDescriptorWrapper(bluetoothGattDescriptor);
                wrappers$BluetoothDeviceWrapper.c.put(bluetoothGattDescriptor, wrappers$BluetoothGattDescriptorWrapper);
            }
            arrayList.add(wrappers$BluetoothGattDescriptorWrapper);
        }
        int i = 0;
        for (Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper2 : arrayList) {
            N.MkqvrLoV(this.a, this, this.c + "/" + wrappers$BluetoothGattDescriptorWrapper2.a.getUuid().toString() + ";" + i, wrappers$BluetoothGattDescriptorWrapper2, this.d);
            i++;
        }
    }

    @CalledByNative
    public final int getProperties() {
        return this.b.a.getProperties();
    }

    @CalledByNative
    public final String getUUID() {
        return this.b.a.getUuid().toString();
    }

    @CalledByNative
    public final void onBluetoothRemoteGattCharacteristicAndroidDestruction() {
        ChromeBluetoothDevice chromeBluetoothDevice = this.d;
        um0 um0Var = chromeBluetoothDevice.c;
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = this.b;
        if (um0Var != null) {
            ((BluetoothGatt) um0Var.a).setCharacteristicNotification(wrappers$BluetoothGattCharacteristicWrapper.a, false);
        }
        this.a = 0L;
        chromeBluetoothDevice.e.remove(wrappers$BluetoothGattCharacteristicWrapper);
    }

    @CalledByNative
    public final boolean readRemoteCharacteristic() {
        if (((BluetoothGatt) this.d.c.a).readCharacteristic(this.b.a)) {
            return true;
        }
        Log.i("cr_Bluetooth", "readRemoteCharacteristic readCharacteristic failed.");
        return false;
    }

    @CalledByNative
    public final boolean setCharacteristicNotification(boolean z) {
        return ((BluetoothGatt) this.d.c.a).setCharacteristicNotification(this.b.a, z);
    }

    @CalledByNative
    public final boolean writeRemoteCharacteristic(byte[] bArr, int i) {
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = this.b;
        if (!wrappers$BluetoothGattCharacteristicWrapper.a.setValue(bArr)) {
            Log.i("cr_Bluetooth", "writeRemoteCharacteristic setValue failed.");
            return false;
        }
        if (i != 0) {
            wrappers$BluetoothGattCharacteristicWrapper.a.setWriteType(i);
        }
        if (((BluetoothGatt) this.d.c.a).writeCharacteristic(wrappers$BluetoothGattCharacteristicWrapper.a)) {
            return true;
        }
        Log.i("cr_Bluetooth", "writeRemoteCharacteristic writeCharacteristic failed.");
        return false;
    }
}
