package com.byd.bt.receive;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import m4.d;
import x3.a;
import y2.b;

/* JADX INFO: loaded from: classes.dex */
public class BtActionReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a.a("BtActionReceiver", "---onReceive--action:" + action);
        if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST") && !b.o.a().Y()) {
            String strD = a3.a.d("pairing_request_service");
            a.a("BtActionReceiver", "---onReceive--serviceCls: " + strD);
            if (strD != null) {
                Intent intent2 = (Intent) intent.clone();
                intent2.setComponent(new ComponentName(context.getPackageName(), strD));
                context.startService(intent2);
                return;
            }
            return;
        }
        BluetoothDevice bluetoothDeviceO = b.o.a().O();
        if (bluetoothDeviceO != null && "android.bluetooth.device.action.FOUND".equals(action)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            if (!bluetoothDeviceO.getName().equals(name) || !bluetoothDeviceO.getAddress().equals(address)) {
                return;
            }
            a.a("BtActionReceiver", "---onReceive--Target is found, device:" + d.f(bluetoothDevice.getName()));
        }
        d3.a.c().a((Intent) intent.clone());
    }
}
