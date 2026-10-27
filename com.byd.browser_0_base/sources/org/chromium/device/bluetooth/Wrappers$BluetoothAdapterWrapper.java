package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import defpackage.xz;
import defpackage.yt3;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class Wrappers$BluetoothAdapterWrapper {
    public final BluetoothAdapter a;
    public final Context b;
    public yt3 c;

    public Wrappers$BluetoothAdapterWrapper(BluetoothAdapter bluetoothAdapter, Context context) {
        this.a = bluetoothAdapter;
        this.b = context;
    }

    @CalledByNative
    public static Wrappers$BluetoothAdapterWrapper createWithDefaultAdapter() {
        if (Build.VERSION.SDK_INT < 31) {
            if (!(xz.a.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0 && xz.a.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0)) {
                Log.w("cr_Bluetooth", "BluetoothAdapterWrapper.create failed: Lacking Bluetooth permissions.");
                return null;
            }
        }
        if (!xz.a.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            Log.i("cr_Bluetooth", "BluetoothAdapterWrapper.create failed: No Low Energy support.");
            return null;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return new Wrappers$BluetoothAdapterWrapper(defaultAdapter, xz.a);
        }
        Log.i("cr_Bluetooth", "BluetoothAdapterWrapper.create failed: Default adapter not found.");
        return null;
    }
}
