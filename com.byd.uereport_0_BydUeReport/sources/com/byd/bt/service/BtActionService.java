package com.byd.bt.service;

import a3.a;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.text.TextUtils;
import b3.b;
import b3.c;
import b3.e;
import b3.f;
import b3.h;
import b3.i;
import b3.j;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;
import com.byd.mvp.service.BydIntentService;
import m4.d;

/* JADX INFO: loaded from: classes.dex */
public class BtActionService extends BydIntentService {
    public BtActionService() {
        super("BtActionService");
    }

    public final ConnectState a(int i8) {
        return i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 3 ? ConnectState.DISCONNECTED : ConnectState.DISCONNECTING : ConnectState.CONNECTED : ConnectState.CONNECTING : ConnectState.DISCONNECTED;
    }

    public final void b(Intent intent) {
        b bVar = (b) a.c("aa_new_device_pair");
        if (bVar == null) {
            return;
        }
        int intExtra = intent.getIntExtra("op", -1);
        bVar.onNewDevicePair(intExtra);
        x3.a.a("BtActionService", "onAANewDevicePair", "state = " + intExtra);
    }

    public final void c(Intent intent) {
        c cVar = (c) a.c("bt_battery_changed");
        if (cVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.headsetclient.extra.BATTERY_LEVEL", -1);
        if (intExtra != -1) {
            x3.a.a("BtActionService", "onAgEventChanged", ", level: " + intExtra + ", device: " + d.c(bluetoothDevice));
            cVar.onBatteryChanged(bluetoothDevice, intExtra);
        }
    }

    public final void d(Intent intent) {
        b3.a aVarC = a.c("bt_connect_state");
        if (aVarC == null || !(aVarC instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        x3.a.a("BtActionService", "onBtAclConnected", ", device: " + d.c(bluetoothDevice));
        ((e) aVarC).onConnectStateChanged(bluetoothDevice, ConnectState.CONNECTED);
    }

    public final void e(Intent intent) {
        b3.a aVarC = a.c("bt_connect_state");
        if (aVarC == null || !(aVarC instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        x3.a.a("BtActionService", "onBtAclDisconnected", ", device: " + d.c(bluetoothDevice));
        ((e) aVarC).onConnectStateChanged(bluetoothDevice, ConnectState.DISCONNECTED);
    }

    public final void f(Intent intent) {
        b3.a aVarC = a.c("bt_bond_sate");
        if (aVarC == null || !(aVarC instanceof b3.d)) {
            return;
        }
        b3.d dVar = (b3.d) aVarC;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
        x3.a.c("BtActionService", "onBtBondStateChanged", ", state = " + intExtra + ", device: " + d.c(bluetoothDevice));
        switch (intExtra) {
            case 10:
                int i8 = intent.getExtras().getInt("android.bluetooth.device.extra.REASON");
                x3.a.a("BtActionService", "onBtBondStateChanged", ", BOND_NONE--reason: " + i8);
                if (i8 != 9) {
                    dVar.onBtBondFailed(bluetoothDevice, i8);
                } else {
                    dVar.onBtBondNone(bluetoothDevice);
                }
                break;
            case 11:
                dVar.onBtBonding(bluetoothDevice);
                break;
            case 12:
                dVar.onBtBonded(bluetoothDevice);
                break;
        }
    }

    public final void g(Intent intent) {
        b3.a aVarC = a.c("bt_connect_state");
        if (aVarC == null || !(aVarC instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
        x3.a.c("BtActionService", "onBtConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state = " + intExtra);
    }

    public final void h(Intent intent) {
        f fVar = (f) a.c("bt_discover");
        if (fVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        short s7 = intent.getExtras().getShort("android.bluetooth.device.extra.RSSI", (short) -1);
        if (!TextUtils.isEmpty(bluetoothDevice.getName()) && e3.a.b(bluetoothDevice)) {
            BTDevice bTDevice = new BTDevice(bluetoothDevice);
            bTDevice.setRssi(s7);
            bTDevice.setConnected(((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue());
            fVar.a(bTDevice);
        }
    }

    public final void i() {
        f fVar = (f) a.c("bt_discover");
        if (fVar == null) {
            return;
        }
        x3.a.a("BtActionService", "onBtDiscoveryFinished", "---onBtDiscoveryFinished--");
        fVar.onDiscoveryFinished();
    }

    public final void j(Intent intent) {
        b3.a aVarC = a.c("bt_profile_connect_state");
        if (aVarC == null || !(aVarC instanceof i)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        x3.a.c("BtActionService", "onBtMediaConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state = " + intExtra);
        ((i) aVarC).onConnectedChanged(11, bluetoothDevice, a(intExtra));
    }

    public final void k(Intent intent) {
        e eVar = (e) a.c("bt_connect_state");
        if (eVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        short s7 = intent.getExtras().getShort("android.bluetooth.device.extra.RSSI", (short) -1);
        if (!TextUtils.isEmpty(bluetoothDevice.getName()) && e3.a.b(bluetoothDevice) && ((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue()) {
            x3.a.a("BtActionService", "onBtNameChanged", "device: " + d.c(bluetoothDevice) + ", rssi: " + ((int) s7));
            BTDevice bTDevice = new BTDevice(bluetoothDevice);
            bTDevice.setRssi(s7);
            bTDevice.setConnected(true);
            eVar.onConnectedBtNameChanged(bTDevice);
        }
    }

    public final void l(Intent intent) {
        b3.a aVarC = a.c("bt_profile_connect_state");
        if (aVarC == null || !(aVarC instanceof i)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        x3.a.c("BtActionService", "onBtPanConnectStateChanged", "device: " + d.c(bluetoothDevice) + ", state: " + intExtra);
        ((i) aVarC).onConnectedChanged(5, bluetoothDevice, a(intExtra));
    }

    public final void m(Intent intent) {
        b3.a aVarC = a.c("bt_profile_connect_state");
        if (aVarC == null || !(aVarC instanceof i)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        x3.a.c("BtActionService", "onBtPhoneConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state = " + intExtra);
        ((i) aVarC).onConnectedChanged(16, bluetoothDevice, a(intExtra));
    }

    public final void n(Intent intent) {
        j jVar = (j) a.c("bt_sate");
        if (jVar == null) {
        }
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
        x3.a.c("BtActionService", "onBtStateChanged", "state = " + intExtra);
        switch (intExtra) {
            case 10:
                jVar.onBtOff();
                break;
            case 11:
                jVar.onBtTurningOn();
                break;
            case 12:
                jVar.onBtOn();
                break;
            case 13:
                jVar.onBtTurningOff();
                break;
        }
    }

    public final void o(Intent intent) {
        b3.a aVarC = a.c("bt_bond_sate");
        if (aVarC == null || !(aVarC instanceof b3.d)) {
            return;
        }
        String stringExtra = intent.getStringExtra("from");
        String stringExtra2 = intent.getStringExtra("btAddr");
        x3.a.a("BtActionService", "onOobBondStateChanged", "---from: " + stringExtra + ", mac: " + d.d(stringExtra2));
        ((b3.d) aVarC).onOobBonded(stringExtra, stringExtra2);
    }

    @Override // com.byd.mvp.service.BydIntentService
    public void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        x3.a.a("BtActionService", "onHandleIntent_bt", "action: " + action);
        action.hashCode();
        switch (action) {
            case "android.bluetooth.adapter.action.DISCOVERY_FINISHED":
                i();
                break;
            case "android.bluetooth.adapter.action.STATE_CHANGED":
                n(intent);
                break;
            case "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED":
                l(intent);
                break;
            case "android.bluetooth.device.action.ACL_CONNECTED":
                d(intent);
                break;
            case "android.bluetooth.device.action.PAIRING_REQUEST":
                onReceivedBtPairRequest(intent);
                break;
            case "android_auto_new_device_pair":
                b(intent);
                break;
            case "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED":
                j(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED":
                m(intent);
                break;
            case "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED":
                g(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.AG_EVENT":
                c(intent);
                break;
            case "android.bluetooth.device.action.FOUND":
                h(intent);
                break;
            case "android.bluetooth.device.action.ACL_DISCONNECTED":
                e(intent);
                break;
            case "com.ts.carplay.action.OOB_BONDED_FOR_BT":
                o(intent);
                break;
            case "android.bluetooth.device.action.NAME_CHANGED":
                k(intent);
                break;
            case "android.bluetooth.device.action.BOND_STATE_CHANGED":
                f(intent);
                break;
        }
    }

    public final void onReceivedBtPairRequest(Intent intent) {
        h hVar = (h) a.c("bt_pair_request");
        if (hVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        boolean z7 = false;
        x3.a.a("BtActionService", "onReceivedBtPairRequest", "---device: " + d.c(bluetoothDevice));
        if (bluetoothDevice == null) {
            return;
        }
        if (!e3.a.b(bluetoothDevice)) {
            x3.a.b("BtActionService", "onReceivedBtPairRequest", ", device not Support. device: " + d.c(bluetoothDevice));
            bluetoothDevice.setPairingConfirmation(false);
            return;
        }
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        if (intExtra != 2 && intExtra != 4 && intExtra != 5) {
            z7 = true;
        }
        hVar.onReceivedPairRequest(bluetoothDevice, z7, intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE));
    }
}
