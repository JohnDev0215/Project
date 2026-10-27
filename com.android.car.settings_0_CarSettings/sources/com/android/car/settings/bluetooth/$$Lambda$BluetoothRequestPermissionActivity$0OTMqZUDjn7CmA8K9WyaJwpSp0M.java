package com.android.car.settings.bluetooth;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$0OTMqZUDjn7CmA8K9WyaJwpSp0M, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class $$Lambda$BluetoothRequestPermissionActivity$0OTMqZUDjn7CmA8K9WyaJwpSp0M implements DialogInterface.OnClickListener {
    public final /* synthetic */ BluetoothRequestPermissionActivity f$0;

    public /* synthetic */ $$Lambda$BluetoothRequestPermissionActivity$0OTMqZUDjn7CmA8K9WyaJwpSp0M(BluetoothRequestPermissionActivity bluetoothRequestPermissionActivity) {
        this.f$0 = bluetoothRequestPermissionActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.onConfirmEnableBluetooth(dialogInterface, i);
    }
}
