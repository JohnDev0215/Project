package com.ts.androidauto.aap.bluetooth;

import android.bluetooth.BluetoothSocket;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface RfCommConnectionListener {
    List<AndroidAutoDevice> getCurrConnectedDevice();

    void onConnectedTo(BluetoothSocket bluetoothSocket, boolean z);
}
