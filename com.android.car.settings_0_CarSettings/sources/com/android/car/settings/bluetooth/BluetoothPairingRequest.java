package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public final class BluetoothPairingRequest extends BroadcastReceiver {
    private static final String TAG = "BluetoothPairingRequest";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive action: " + action);
        if (!action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
            return;
        }
        BluetoothPairingService.getPairingDialogIntent(context, intent);
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (device != null) {
            device.getAddress();
        }
        if (device != null) {
            device.getName();
        }
        Log.d(TAG, "don`t shouldShowDialog");
    }
}
