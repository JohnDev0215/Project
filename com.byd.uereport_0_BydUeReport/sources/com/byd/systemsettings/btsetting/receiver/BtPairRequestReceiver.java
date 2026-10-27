package com.byd.systemsettings.btsetting.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.systemsettings.service.ReceiverActionService;
import java.lang.reflect.InvocationTargetException;
import m4.d;
import x3.a;
import y2.b;

/* JADX INFO: loaded from: classes2.dex */
public class BtPairRequestReceiver extends BroadcastReceiver {
    private static final String TAG = "BtPairRequestReceiver";

    private boolean cancelPairDevice(BluetoothDevice bluetoothDevice) {
        Boolean bool;
        Boolean bool2 = null;
        try {
            bool = (Boolean) BluetoothDevice.class.getMethod("cancelBondProcess", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        } catch (IllegalAccessException e8) {
            e = e8;
        } catch (NoSuchMethodException e9) {
            e = e9;
        } catch (InvocationTargetException e10) {
            e = e10;
        }
        try {
            a.a(TAG, "---cancelPairDevice---result: " + bool + ", device: " + d.f(bluetoothDevice.getName()));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e11) {
            e = e11;
            bool2 = bool;
            a.a(TAG, "---cancelPairDevice---e: " + e.toString());
            bool = bool2;
        }
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a.a(TAG, "---onReceive---action = " + action + ", in = " + intent);
        if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (!e3.a.b(bluetoothDevice)) {
                cancelPairDevice(bluetoothDevice);
            } else {
                if (b.o.a().Y()) {
                    return;
                }
                ReceiverActionService.startBtAction(context, (Intent) intent.clone());
            }
        }
    }
}
