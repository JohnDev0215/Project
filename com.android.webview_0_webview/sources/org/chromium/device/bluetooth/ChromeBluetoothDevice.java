package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothDevice;
import defpackage.BE;
import defpackage.DE;
import defpackage.FE;
import defpackage.Is;
import defpackage.Ts;
import java.util.HashMap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeBluetoothDevice {
    public long a;
    public final Wrappers$BluetoothDeviceWrapper b;
    public DE c;
    public final BE d = new BE(this, null);
    public final HashMap e = new HashMap();
    public final HashMap f = new HashMap();

    public ChromeBluetoothDevice(long j, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.a = j;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    public static ChromeBluetoothDevice create(long j, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        return new ChromeBluetoothDevice(j, wrappers$BluetoothDeviceWrapper);
    }

    private void createGattConnectionImpl() {
        Ts.d("Bluetooth", "connectGatt", new Object[0]);
        DE de = this.c;
        if (de != null) {
            de.a.close();
        }
        Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = this.b;
        this.c = new DE(wrappers$BluetoothDeviceWrapper.a.connectGatt(Is.a, false, new FE(this.d, wrappers$BluetoothDeviceWrapper), 2), wrappers$BluetoothDeviceWrapper);
    }

    private void disconnectGatt() {
        Ts.d("Bluetooth", "BluetoothGatt.disconnect", new Object[0]);
        DE de = this.c;
        if (de != null) {
            de.a.disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAddress() {
        return this.b.a();
    }

    private int getBluetoothClass() {
        Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = this.b;
        BluetoothDevice bluetoothDevice = wrappers$BluetoothDeviceWrapper.a;
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null) {
            return 7936;
        }
        return wrappers$BluetoothDeviceWrapper.a.getBluetoothClass().getDeviceClass();
    }

    private String getName() {
        return this.b.a.getName();
    }

    private boolean isPaired() {
        return this.b.a.getBondState() == 12;
    }

    private void onBluetoothDeviceAndroidDestruction() {
        DE de = this.c;
        if (de != null) {
            de.a.close();
            this.c = null;
        }
        this.a = 0L;
    }
}
