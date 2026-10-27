package com.byd.bt.service;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.UserHandle;
import android.text.TextUtils;
import b.b.a.f.h;
import b.b.d.e.b;
import b.b.d.e.c;
import b.b.d.e.d;
import b.b.d.e.e;
import b.b.d.e.g;
import b.b.d.e.i;
import b.b.m.g.f;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;
import com.byd.mvp.service.BydIntentService;

/* JADX INFO: loaded from: classes.dex */
public class BtActionService extends BydIntentService {

    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Intent f2545a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f2546b;

        public a(Intent intent, Context context) {
            this.f2545a = intent;
            this.f2546b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Parcel parcelObtain = Parcel.obtain();
            this.f2545a.setComponent(new ComponentName(this.f2546b, (Class<?>) BtActionService.class));
            b.b.m.g.a.c(this.f2546b, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, new Object[]{this.f2545a, UserHandle.readFromParcel(parcelObtain)});
            parcelObtain.recycle();
            b.b.m.c.a.g("BtActionService", "---startWifiAction--start service completed. threadName: " + Thread.currentThread().getName() + ", action: " + this.f2545a.getAction());
        }
    }

    public BtActionService() {
        super("BtActionService");
    }

    public static void r(Context context, Intent intent) {
        b.b.m.c.a.d("BtActionService", "--startBtAction--action: " + intent.getAction());
        h.a().j(new a(intent, context));
    }

    public final ConnectState a(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? ConnectState.DISCONNECTED : ConnectState.DISCONNECTING : ConnectState.CONNECTED : ConnectState.CONNECTING : ConnectState.DISCONNECTED;
    }

    public final ConnectState b(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? ConnectState.DISCONNECTED : ConnectState.DISCONNECTING : ConnectState.CONNECTED : ConnectState.CONNECTING : ConnectState.DISCONNECTED;
    }

    public final void c(Intent intent) {
        b bVar = (b) b.b.d.d.a.b("aa_new_device_pair");
        if (bVar == null) {
            return;
        }
        int intExtra = intent.getIntExtra("op", -1);
        bVar.p(intExtra);
        b.b.m.c.a.a("BtActionService", "onAANewDevicePair", "state = " + intExtra);
    }

    public final void d(Intent intent) {
        c cVar = (c) b.b.d.d.a.b("bt_battery_changed");
        if (cVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.headsetclient.extra.BATTERY_LEVEL", -1);
        if (intExtra != -1) {
            b.b.m.c.a.a("BtActionService", "onAgEventChanged", ", level: " + intExtra + ", device: " + f.d(bluetoothDevice));
            cVar.e(bluetoothDevice, intExtra);
        }
    }

    public final void e(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_connect_state");
        if (aVarB == null || !(aVarB instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        b.b.m.c.a.a("BtActionService", "onBtAclConnected", ", device: " + f.d(bluetoothDevice));
        ((e) aVarB).k(bluetoothDevice, ConnectState.CONNECTED, true);
    }

    public final void f(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_connect_state");
        if (aVarB == null || !(aVarB instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        b.b.m.c.a.a("BtActionService", "onBtAclDisconnected", ", device: " + f.d(bluetoothDevice));
        ((e) aVarB).k(bluetoothDevice, ConnectState.DISCONNECTED, true);
    }

    public final void g(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_bond_sate");
        if (aVarB == null || !(aVarB instanceof d)) {
            return;
        }
        d dVar = (d) aVarB;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
        b.b.m.c.a.d("BtActionService", "onBtBondStateChanged", ", state = " + intExtra + ", device: " + f.d(bluetoothDevice));
        switch (intExtra) {
            case 10:
                int i = intent.getExtras().getInt("android.bluetooth.device.extra.REASON");
                b.b.m.c.a.a("BtActionService", "onBtBondStateChanged", ", BOND_NONE--reason: " + i);
                if (i != 9) {
                    dVar.d(bluetoothDevice, i);
                } else {
                    dVar.j(bluetoothDevice);
                }
                break;
            case 11:
                dVar.i(bluetoothDevice);
                break;
            case 12:
                dVar.m(bluetoothDevice);
                break;
        }
    }

    public final void h(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_connect_state");
        if (aVarB == null || !(aVarB instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
        b.b.m.c.a.d("BtActionService", "onBtConnectStateChanged", ", device: " + f.d(bluetoothDevice) + ", state = " + intExtra);
        ((e) aVarB).k(bluetoothDevice, a(intExtra), false);
    }

    public final void i(Intent intent) {
        b.b.d.e.f fVar = (b.b.d.e.f) b.b.d.d.a.b("bt_discover");
        if (fVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        short s = intent.getExtras().getShort("android.bluetooth.device.extra.RSSI", (short) -1);
        if (!TextUtils.isEmpty(bluetoothDevice.getName()) && b.b.d.g.a.b(bluetoothDevice)) {
            BTDevice bTDevice = new BTDevice(bluetoothDevice);
            bTDevice.setRssi(s);
            bTDevice.setConnected(((Boolean) b.b.m.g.a.a(bluetoothDevice, "isConnected")).booleanValue());
            fVar.q(bTDevice);
        }
    }

    public final void j() {
        b.b.d.e.f fVar = (b.b.d.e.f) b.b.d.d.a.b("bt_discover");
        if (fVar == null) {
            return;
        }
        b.b.m.c.a.a("BtActionService", "onBtDiscoveryFinished", "---onBtDiscoveryFinished--");
        fVar.h();
    }

    public final void k(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_profile_connect_state");
        if (aVarB == null || !(aVarB instanceof b.b.d.e.h)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        b.b.m.c.a.d("BtActionService", "onBtMediaConnectStateChanged", ", device: " + f.d(bluetoothDevice) + ", state = " + intExtra);
        ((b.b.d.e.h) aVarB).a(11, bluetoothDevice, b(intExtra));
    }

    public final void l(Intent intent) {
        e eVar = (e) b.b.d.d.a.b("bt_connect_state");
        if (eVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        short s = intent.getExtras().getShort("android.bluetooth.device.extra.RSSI", (short) -1);
        if (!TextUtils.isEmpty(bluetoothDevice.getName()) && b.b.d.g.a.b(bluetoothDevice) && ((Boolean) b.b.m.g.a.a(bluetoothDevice, "isConnected")).booleanValue()) {
            b.b.m.c.a.a("BtActionService", "onBtNameChanged", "device: " + f.d(bluetoothDevice) + ", rssi: " + ((int) s));
            BTDevice bTDevice = new BTDevice(bluetoothDevice);
            bTDevice.setRssi(s);
            bTDevice.setConnected(true);
            eVar.n(bTDevice);
        }
    }

    public final void m(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_profile_connect_state");
        if (aVarB == null || !(aVarB instanceof b.b.d.e.h)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        b.b.m.c.a.d("BtActionService", "onBtPanConnectStateChanged", "device: " + f.d(bluetoothDevice) + ", state: " + intExtra);
        ((b.b.d.e.h) aVarB).a(5, bluetoothDevice, b(intExtra));
    }

    public final void n(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_profile_connect_state");
        if (aVarB == null || !(aVarB instanceof b.b.d.e.h)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        b.b.m.c.a.d("BtActionService", "onBtPhoneConnectStateChanged", ", device: " + f.d(bluetoothDevice) + ", state = " + intExtra);
        ((b.b.d.e.h) aVarB).a(16, bluetoothDevice, b(intExtra));
    }

    public final void o(Intent intent) {
        i iVar = (i) b.b.d.d.a.b("bt_sate");
        if (iVar == null) {
        }
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
        b.b.m.c.a.d("BtActionService", "onBtSateChanged", "state = " + intExtra);
        switch (intExtra) {
            case 10:
                iVar.c();
                break;
            case 11:
                iVar.o();
                break;
            case 12:
                iVar.f();
                break;
            case 13:
                iVar.l();
                break;
        }
    }

    @Override // com.byd.mvp.service.BydIntentService
    public void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "android.bluetooth.adapter.action.DISCOVERY_FINISHED":
                j();
                break;
            case "android.bluetooth.adapter.action.STATE_CHANGED":
                o(intent);
                break;
            case "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED":
                m(intent);
                break;
            case "android.bluetooth.device.action.ACL_CONNECTED":
                e(intent);
                break;
            case "android.bluetooth.device.action.PAIRING_REQUEST":
                q(intent);
                break;
            case "android_auto_new_device_pair":
                c(intent);
                break;
            case "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED":
                k(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED":
                n(intent);
                break;
            case "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED":
                h(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.AG_EVENT":
                d(intent);
                break;
            case "android.bluetooth.device.action.FOUND":
                i(intent);
                break;
            case "android.bluetooth.device.action.ACL_DISCONNECTED":
                f(intent);
                break;
            case "com.ts.carplay.action.OOB_BONDED_FOR_BT":
                p(intent);
                break;
            case "android.bluetooth.device.action.NAME_CHANGED":
                l(intent);
                break;
            case "android.bluetooth.device.action.BOND_STATE_CHANGED":
                g(intent);
                break;
        }
    }

    public final void p(Intent intent) {
        b.b.d.e.a aVarB = b.b.d.d.a.b("bt_bond_sate");
        if (aVarB == null || !(aVarB instanceof d)) {
            return;
        }
        String stringExtra = intent.getStringExtra("from");
        String stringExtra2 = intent.getStringExtra("btAddr");
        b.b.m.c.a.a("BtActionService", "onOobBondStateChanged", "---from: " + stringExtra + ", mac: " + f.e(stringExtra2));
        ((d) aVarB).g(stringExtra, stringExtra2);
    }

    public final void q(Intent intent) {
        g gVar = (g) b.b.d.d.a.b("bt_pair_request");
        if (gVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        boolean z = false;
        b.b.m.c.a.a("BtActionService", "onReceivedBtPairRequest", "---device: " + f.d(bluetoothDevice));
        if (bluetoothDevice == null) {
            return;
        }
        if (!b.b.d.g.a.b(bluetoothDevice)) {
            b.b.m.c.a.c("BtActionService", "onReceivedBtPairRequest", ", device not Support. device: " + f.d(bluetoothDevice));
            bluetoothDevice.setPairingConfirmation(false);
            return;
        }
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        if (intExtra != 2 && intExtra != 4 && intExtra != 5) {
            z = true;
        }
        gVar.b(bluetoothDevice, z, intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE));
    }
}
