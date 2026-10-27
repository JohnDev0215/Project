package com.android.bluetooth.auto;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class DockEventReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int btState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (btState == 10) {
                Intent intentStop = new Intent(context, (Class<?>) BluetoothConnectService.class);
                context.stopService(intentStop);
                return;
            }
            return;
        }
        if (Constant.LINK_LOSS_DETECTED.equals(action)) {
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Intent linkLoss = new Intent(intent);
            linkLoss.setClass(context, BluetoothConnectService.class);
            linkLoss.putExtra("android.bluetooth.device.extra.DEVICE", device);
            context.startService(linkLoss);
        }
    }
}
