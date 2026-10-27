package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class LocalBluetoothAdapter {
    private static LocalBluetoothAdapter sInstance;
    private final BluetoothAdapter mAdapter;
    private LocalBluetoothProfileManager mProfileManager;
    private int mState = Integer.MIN_VALUE;

    private LocalBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
        this.mAdapter = bluetoothAdapter;
    }

    void setProfileManager(LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mProfileManager = localBluetoothProfileManager;
    }

    static synchronized LocalBluetoothAdapter getInstance() {
        BluetoothAdapter defaultAdapter;
        if (sInstance == null && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            sInstance = new LocalBluetoothAdapter(defaultAdapter);
        }
        return sInstance;
    }

    public Set<BluetoothDevice> getBondedDevices() {
        return this.mAdapter.getBondedDevices();
    }

    void setBluetoothStateInt(int i) {
        LocalBluetoothProfileManager localBluetoothProfileManager;
        synchronized (this) {
            if (this.mState == i) {
                return;
            }
            this.mState = i;
            if (i != 12 || (localBluetoothProfileManager = this.mProfileManager) == null) {
                return;
            }
            localBluetoothProfileManager.setBluetoothStateOn();
        }
    }

    public BluetoothDevice getRemoteDevice(String str) {
        return this.mAdapter.getRemoteDevice(str);
    }
}
