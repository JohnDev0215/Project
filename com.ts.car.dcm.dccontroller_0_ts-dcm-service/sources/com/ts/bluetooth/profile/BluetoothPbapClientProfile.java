package com.ts.bluetooth.profile;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPbapClientProfile extends BaseBluetoothProfileImpl<BluetoothProfile> {
    private static final int PBAP = 6;

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ boolean connect(BluetoothDevice bluetoothDevice) {
        return super.connect(bluetoothDevice);
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ boolean disconnect(BluetoothDevice bluetoothDevice) {
        return super.disconnect(bluetoothDevice);
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ int getPriority(BluetoothDevice bluetoothDevice) {
        return super.getPriority(bluetoothDevice);
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ boolean isPreferred(BluetoothDevice bluetoothDevice) {
        return super.isPreferred(bluetoothDevice);
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, android.bluetooth.BluetoothProfile.ServiceListener
    public /* bridge */ /* synthetic */ void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        super.onServiceConnected(i, bluetoothProfile);
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, android.bluetooth.BluetoothProfile.ServiceListener
    public /* bridge */ /* synthetic */ void onServiceDisconnected(int i) {
        super.onServiceDisconnected(i);
    }

    @Override // com.ts.bluetooth.profile.BaseBluetoothProfileImpl, com.ts.bluetooth.profile.IBluetoothProfile
    public /* bridge */ /* synthetic */ boolean setPriority(BluetoothDevice bluetoothDevice, int i) {
        return super.setPriority(bluetoothDevice, i);
    }

    BluetoothPbapClientProfile(Context context) {
        super(context);
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public int getProfileCode() {
        return 6;
    }
}
