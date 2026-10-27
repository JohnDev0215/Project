package com.ts.bluetooth;

import android.bluetooth.BluetoothDevice;
import com.ts.bluetooth.profile.Profile;

/* JADX INFO: loaded from: classes.dex */
public interface BluetoothCallback {
    void onBondedStateChanged(BluetoothDevice bluetoothDevice, int i);

    void onBtStateChanged(int i);

    void onProfileConnectedStateChanged(BluetoothDevice bluetoothDevice, Profile profile, boolean z);
}
