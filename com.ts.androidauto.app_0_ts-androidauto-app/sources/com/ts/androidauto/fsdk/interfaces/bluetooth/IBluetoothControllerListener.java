package com.ts.androidauto.fsdk.interfaces.bluetooth;

import android.bluetooth.BluetoothDevice;
import com.ts.androidauto.fsdk.common.BluetoothConst;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothControllerListener {
    void onBluetoothBondStateChanged(int i, BluetoothDevice bluetoothDevice);

    void onBtDisabled();

    void onBtEnabled();

    boolean onPairRequest(String str, String str2);

    void onPhoneStatusChange(String str, BluetoothConst.BtPhoneStatus btPhoneStatus);

    void onProfileChange(String str, String str2, BluetoothConst.ProfileStatus profileStatus);
}
