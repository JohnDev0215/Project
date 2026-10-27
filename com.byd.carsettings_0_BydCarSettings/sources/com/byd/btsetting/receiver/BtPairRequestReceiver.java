package com.byd.btsetting.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import b.b.d.b;
import b.b.m.c.a;
import b.b.m.g.f;
import com.byd.dialogservice.DialogService;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class BtPairRequestReceiver extends BroadcastReceiver {
    public final boolean a(BluetoothDevice bluetoothDevice) {
        Boolean bool;
        Boolean bool2 = null;
        try {
            bool = (Boolean) BluetoothDevice.class.getMethod("cancelBondProcess", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            try {
                a.a("BtPairRequestReceiver", "---cancelPairDevice---result: " + bool + ", device: " + f.g(bluetoothDevice.getName()));
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                e = e2;
                bool2 = bool;
                a.a("BtPairRequestReceiver", "---cancelPairDevice---e: " + e.toString());
                bool = bool2;
            }
        } catch (IllegalAccessException e3) {
            e = e3;
        } catch (NoSuchMethodException e4) {
            e = e4;
        } catch (InvocationTargetException e5) {
            e = e5;
        }
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a.a("BtPairRequestReceiver", "---onReceive---action = " + action + ", in = " + intent);
        if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (!b.b.d.g.a.b(bluetoothDevice)) {
                a(bluetoothDevice);
            } else {
                if (b.f.a().x()) {
                    return;
                }
                Intent intent2 = (Intent) intent.clone();
                intent2.setComponent(new ComponentName(context.getPackageName(), DialogService.class.getName()));
                context.startService(intent2);
            }
        }
    }
}
