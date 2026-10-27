package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothDevice;
import java.util.HashMap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Wrappers$BluetoothDeviceWrapper {
    public final BluetoothDevice a;
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();

    public Wrappers$BluetoothDeviceWrapper(BluetoothDevice bluetoothDevice) {
        this.a = bluetoothDevice;
    }

    public String a() {
        return this.a.getAddress();
    }
}
