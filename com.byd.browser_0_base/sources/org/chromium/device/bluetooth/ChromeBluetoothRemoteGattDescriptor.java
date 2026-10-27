package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.util.Log;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothRemoteGattDescriptor {
    public long a;
    public final Wrappers$BluetoothGattDescriptorWrapper b;
    public final ChromeBluetoothDevice c;

    public ChromeBluetoothRemoteGattDescriptor(long j, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.a = j;
        this.b = wrappers$BluetoothGattDescriptorWrapper;
        this.c = chromeBluetoothDevice;
        chromeBluetoothDevice.f.put(wrappers$BluetoothGattDescriptorWrapper, this);
    }

    @CalledByNative
    public static ChromeBluetoothRemoteGattDescriptor create(long j, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattDescriptor(j, wrappers$BluetoothGattDescriptorWrapper, chromeBluetoothDevice);
    }

    @CalledByNative
    public final String getUUID() {
        return this.b.a.getUuid().toString();
    }

    @CalledByNative
    public final void onBluetoothRemoteGattDescriptorAndroidDestruction() {
        this.a = 0L;
        this.c.f.remove(this.b);
    }

    @CalledByNative
    public final boolean readRemoteDescriptor() {
        if (((BluetoothGatt) this.c.c.a).readDescriptor(this.b.a)) {
            return true;
        }
        Log.i("cr_Bluetooth", "readRemoteDescriptor readDescriptor failed.");
        return false;
    }

    @CalledByNative
    public final boolean writeRemoteDescriptor(byte[] bArr) {
        Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper = this.b;
        if (!wrappers$BluetoothGattDescriptorWrapper.a.setValue(bArr)) {
            Log.i("cr_Bluetooth", "writeRemoteDescriptor setValue failed.");
            return false;
        }
        if (((BluetoothGatt) this.c.c.a).writeDescriptor(wrappers$BluetoothGattDescriptorWrapper.a)) {
            return true;
        }
        Log.i("cr_Bluetooth", "writeRemoteDescriptor writeDescriptor failed.");
        return false;
    }
}
