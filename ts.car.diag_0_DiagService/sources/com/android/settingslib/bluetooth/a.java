package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f1341b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BluetoothAdapter f1342a;

    private a(BluetoothAdapter bluetoothAdapter) {
        this.f1342a = bluetoothAdapter;
    }

    static synchronized a a() {
        BluetoothAdapter defaultAdapter;
        if (f1341b == null && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            f1341b = new a(defaultAdapter);
        }
        return f1341b;
    }

    public int b() {
        return this.f1342a.getState();
    }

    public void c(int i) {
        this.f1342a.setScanMode(i);
    }
}
