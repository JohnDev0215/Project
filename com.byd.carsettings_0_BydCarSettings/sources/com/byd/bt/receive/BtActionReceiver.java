package com.byd.bt.receive;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import b.b.d.b;
import b.b.m.c.a;
import b.b.m.g.f;
import com.byd.bt.service.BtActionService;

/* JADX INFO: loaded from: classes.dex */
public class BtActionReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a.a("BtActionReceiver", "---onReceive--action:" + action);
        if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST") && !b.f.a().x()) {
            String strC = b.b.d.d.a.c("pairing_request_service");
            a.a("BtActionReceiver", "---onReceive--serviceCls: " + strC);
            if (strC != null) {
                Intent intent2 = (Intent) intent.clone();
                intent2.setComponent(new ComponentName(context.getPackageName(), strC));
                context.startService(intent2);
                return;
            }
            return;
        }
        BluetoothDevice bluetoothDeviceQ = b.f.a().q();
        if (bluetoothDeviceQ != null && "android.bluetooth.device.action.FOUND".equals(action)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            if (!bluetoothDeviceQ.getName().equals(name) || !bluetoothDeviceQ.getAddress().equals(address)) {
                return;
            }
            a.a("BtActionReceiver", "---onReceive--Target is found, device:" + f.g(bluetoothDevice.getName()));
        }
        BtActionService.r(context, (Intent) intent.clone());
    }
}
