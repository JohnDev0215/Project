package org.chromium.device.bluetooth;

import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
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

    public static ChromeBluetoothRemoteGattDescriptor create(long j, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattDescriptor(j, wrappers$BluetoothGattDescriptorWrapper, chromeBluetoothDevice);
    }

    private String getUUID() {
        return this.b.a.getUuid().toString();
    }

    private void onBluetoothRemoteGattDescriptorAndroidDestruction() {
        this.a = 0L;
        this.c.f.remove(this.b);
    }

    private boolean readRemoteDescriptor() {
        if (this.c.c.a.readDescriptor(this.b.a)) {
            return true;
        }
        Ts.d("Bluetooth", "readRemoteDescriptor readDescriptor failed.", new Object[0]);
        return false;
    }

    private boolean writeRemoteDescriptor(byte[] bArr) {
        if (!this.b.a.setValue(bArr)) {
            Ts.d("Bluetooth", "writeRemoteDescriptor setValue failed.", new Object[0]);
            return false;
        }
        if (this.c.c.a.writeDescriptor(this.b.a)) {
            return true;
        }
        Ts.d("Bluetooth", "writeRemoteDescriptor writeDescriptor failed.", new Object[0]);
        return false;
    }
}
