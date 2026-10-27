package com.android.settingslib.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class BluetoothDiscoverableTimeoutReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null || !intent.getAction().equals("android.bluetooth.intent.DISCOVERABLE_TIMEOUT")) {
            return;
        }
        a aVarA = a.a();
        if (aVarA == null || aVarA.b() != 12) {
            Log.e("BluetoothDiscoverableTimeoutReceiver", "localBluetoothAdapter is NULL!!");
        } else {
            Log.d("BluetoothDiscoverableTimeoutReceiver", "Disable discoverable...");
            aVarA.c(21);
        }
    }
}
