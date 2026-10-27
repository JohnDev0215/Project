package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.BluetoothGattDescriptor;
import defpackage.DE;
import defpackage.Ts;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
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

    public static ChromeBluetoothRemoteGattCharacteristic create(long j, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattCharacteristic(j, wrappers$BluetoothGattCharacteristicWrapper, str, chromeBluetoothDevice);
    }

    private void createDescriptors() {
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = this.b;
        List<BluetoothGattDescriptor> descriptors = wrappers$BluetoothGattCharacteristicWrapper.a.getDescriptors();
        ArrayList<Wrappers$BluetoothGattDescriptorWrapper> arrayList = new ArrayList(descriptors.size());
        for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
            Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper = (Wrappers$BluetoothGattDescriptorWrapper) wrappers$BluetoothGattCharacteristicWrapper.b.c.get(bluetoothGattDescriptor);
            if (wrappers$BluetoothGattDescriptorWrapper == null) {
                wrappers$BluetoothGattDescriptorWrapper = new Wrappers$BluetoothGattDescriptorWrapper(bluetoothGattDescriptor, wrappers$BluetoothGattCharacteristicWrapper.b);
                wrappers$BluetoothGattCharacteristicWrapper.b.c.put(bluetoothGattDescriptor, wrappers$BluetoothGattDescriptorWrapper);
            }
            arrayList.add(wrappers$BluetoothGattDescriptorWrapper);
        }
        int i = 0;
        for (Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper2 : arrayList) {
            N.MkqvrLoV(this.a, this, this.c + "/" + wrappers$BluetoothGattDescriptorWrapper2.a.getUuid().toString() + ";" + i, wrappers$BluetoothGattDescriptorWrapper2, this.d);
            i++;
        }
    }

    private int getProperties() {
        return this.b.a.getProperties();
    }

    private String getUUID() {
        return this.b.a.getUuid().toString();
    }

    private void onBluetoothRemoteGattCharacteristicAndroidDestruction() {
        DE de = this.d.c;
        if (de != null) {
            de.a.setCharacteristicNotification(this.b.a, false);
        }
        this.a = 0L;
        this.d.e.remove(this.b);
    }

    private boolean readRemoteCharacteristic() {
        if (this.d.c.a.readCharacteristic(this.b.a)) {
            return true;
        }
        Ts.d("Bluetooth", "readRemoteCharacteristic readCharacteristic failed.", new Object[0]);
        return false;
    }

    private boolean setCharacteristicNotification(boolean z) {
        return this.d.c.a.setCharacteristicNotification(this.b.a, z);
    }

    private boolean writeRemoteCharacteristic(byte[] bArr) {
        if (!this.b.a.setValue(bArr)) {
            Ts.d("Bluetooth", "writeRemoteCharacteristic setValue failed.", new Object[0]);
            return false;
        }
        if (this.d.c.a.writeCharacteristic(this.b.a)) {
            return true;
        }
        Ts.d("Bluetooth", "writeRemoteCharacteristic writeCharacteristic failed.", new Object[0]);
        return false;
    }
}
