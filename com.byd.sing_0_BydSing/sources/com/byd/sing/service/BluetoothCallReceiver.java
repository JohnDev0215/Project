package com.byd.sing.service;

import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.sing.j;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothCallReceiver extends BroadcastReceiver {
    private void a(Intent intent) {
        BluetoothHeadsetClientCall bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall) intent.getExtra("android.bluetooth.headsetclient.extra.CALL");
        if (bluetoothHeadsetClientCall != null) {
            int state = bluetoothHeadsetClientCall.getState();
            com.byd.sing.a.a("callState = " + state);
            if (state == 4 || state == 2 || state == 0) {
                j.f().D(true);
            } else {
                j.f().D(false);
            }
        }
    }

    private void b(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", 0);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", 0);
        com.byd.sing.a.a("parseBluetoothConnect newConnState：" + intExtra);
        if (intExtra != 0) {
            return;
        }
        j.f().D(false);
    }

    private void c(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0);
        com.byd.sing.a.a("parseBluetoothState blueNewState：" + intExtra);
        if (intExtra != 10) {
            return;
        }
        j.f().D(false);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        com.byd.sing.a.a("action:" + action);
        action.hashCode();
        switch (action) {
            case "android.bluetooth.adapter.action.STATE_CHANGED":
                c(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED":
                a(intent);
                break;
            case "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED":
                b(intent);
                break;
        }
    }
}
