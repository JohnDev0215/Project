package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.os.Build;
import defpackage.EE;
import defpackage.Is;
import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Wrappers$BluetoothAdapterWrapper {
    public final BluetoothAdapter a;
    public final Context b;
    public EE c;

    public Wrappers$BluetoothAdapterWrapper(BluetoothAdapter bluetoothAdapter, Context context) {
        this.a = bluetoothAdapter;
        this.b = context;
    }

    public static Wrappers$BluetoothAdapterWrapper createWithDefaultAdapter() {
        int i = Build.VERSION.SDK_INT;
        if (!(i >= 23)) {
            Ts.d("Bluetooth", "BluetoothAdapterWrapper.create failed: SDK version (%d) too low.", Integer.valueOf(i));
            return null;
        }
        if (!(Is.a.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0 && Is.a.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0)) {
            Ts.f("Bluetooth", "BluetoothAdapterWrapper.create failed: Lacking Bluetooth permissions.", new Object[0]);
            return null;
        }
        if (!(Is.a.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))) {
            Ts.d("Bluetooth", "BluetoothAdapterWrapper.create failed: No Low Energy support.", new Object[0]);
            return null;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return new Wrappers$BluetoothAdapterWrapper(defaultAdapter, Is.a);
        }
        Ts.d("Bluetooth", "BluetoothAdapterWrapper.create failed: Default adapter not found.", new Object[0]);
        return null;
    }

    public EE a() {
        BluetoothLeScanner bluetoothLeScanner = this.a.getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            return null;
        }
        if (this.c == null) {
            this.c = new EE(bluetoothLeScanner);
        }
        return this.c;
    }
}
