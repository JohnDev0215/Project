package com.qtxa.monitor.bt;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class BtStateReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f76a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<String, Long> f77b = new HashMap<>();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
            if (intExtra == 12) {
                com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_STATE_CHANGED STATE_ON ");
                return;
            } else {
                if (intExtra == 10) {
                    com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_STATE_CHANGED STATE_OFF ");
                    return;
                }
                return;
            }
        }
        if (intent.getAction().equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE);
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            int type = bluetoothDevice.getType();
            if (intExtra2 == 0) {
                long jLongValue = this.f77b.get(address).longValue();
                this.f77b.remove(address);
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = jCurrentTimeMillis > jLongValue ? jCurrentTimeMillis - jLongValue : 0L;
                com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_CONNECTION_STATE_CHANGED STATE_DISCONNECTED");
                b.b().a(new a(jCurrentTimeMillis, address, name, this.f76a == 4, "bt_disconnected", j));
                return;
            }
            if (intExtra2 != 2) {
                return;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (this.f77b.get(address) == null) {
                this.f77b.put(address, Long.valueOf(jCurrentTimeMillis2));
            } else {
                jCurrentTimeMillis2 = this.f77b.get(address).longValue();
            }
            b.b().a(new a(jCurrentTimeMillis2, address, name, this.f76a == 4, "bt_connected", 0L));
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_CONNECTION_STATE_CHANGED device name:" + name + " addr: " + address + " type: " + type + " pair type(-1 means init.): " + this.f76a);
            return;
        }
        if (intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
            this.f76a = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_PAIRING_REQUEST pairtype: " + this.f76a);
            return;
        }
        if (intent.getAction().equals("android.bluetooth.device.action.ACL_CONNECTED")) {
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name2 = bluetoothDevice2.getName();
            String address2 = bluetoothDevice2.getAddress();
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            if (this.f77b.get(address2) == null) {
                this.f77b.put(address2, Long.valueOf(jCurrentTimeMillis3));
            } else {
                jCurrentTimeMillis3 = this.f77b.get(address2).longValue();
            }
            b.b().a(new a(jCurrentTimeMillis3, address2, name2, this.f76a == 4, "bt_connected", 0L));
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_ACL_CONNECTED: " + name2 + " addr: " + address2);
            return;
        }
        if (intent.getAction().equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
            BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name3 = bluetoothDevice3.getName();
            String address3 = bluetoothDevice3.getAddress();
            long jLongValue2 = this.f77b.get(address3).longValue();
            this.f77b.remove(address3);
            long jCurrentTimeMillis4 = System.currentTimeMillis();
            long j2 = jCurrentTimeMillis4 > jLongValue2 ? jCurrentTimeMillis4 - jLongValue2 : 0L;
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_CONNECTION_STATE_CHANGED STATE_DISCONNECTED");
            b.b().a(new a(jCurrentTimeMillis4, address3, name3, this.f76a == 4, "bt_disconnected", j2));
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_ACL_DISCONNECTED " + name3 + " addr: " + address3);
            return;
        }
        if (intent.getAction().equals("android.bluetooth.adapter.action.BLE_ACL_CONNECTED")) {
            BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name4 = bluetoothDevice4.getName();
            String address4 = bluetoothDevice4.getAddress();
            long jCurrentTimeMillis5 = System.currentTimeMillis();
            if (this.f77b.get(address4) == null) {
                this.f77b.put(address4, Long.valueOf(jCurrentTimeMillis5));
            } else {
                jCurrentTimeMillis5 = this.f77b.get(address4).longValue();
            }
            b.b().a(new a(jCurrentTimeMillis5, address4, name4, this.f76a == 4, "bt_connected", 0L));
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_BLE_ACL_CONNECTED ");
            return;
        }
        if (intent.getAction().equals("android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED")) {
            BluetoothDevice bluetoothDevice5 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String name5 = bluetoothDevice5.getName();
            String address5 = bluetoothDevice5.getAddress();
            long jLongValue3 = this.f77b.get(address5).longValue();
            this.f77b.remove(address5);
            long jCurrentTimeMillis6 = System.currentTimeMillis();
            long j3 = jCurrentTimeMillis6 > jLongValue3 ? jCurrentTimeMillis6 - jLongValue3 : 0L;
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_CONNECTION_STATE_CHANGED STATE_DISCONNECTED");
            b.b().a(new a(jCurrentTimeMillis6, address5, name5, this.f76a == 4, "bt_disconnected", j3));
            com.qtxa.monitor.c.a.a("BtStateReceiver", "BluetoothAdapter ACTION_BLE_ACL_DISCONNECTED ");
        }
    }
}
