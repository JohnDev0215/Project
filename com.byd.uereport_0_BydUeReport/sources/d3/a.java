package d3;

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
import m4.d;
import p2.g;

/* JADX INFO: compiled from: BtActionDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f4432a;

    /* JADX INFO: renamed from: d3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BtActionDispatcher.java */
    public class RunnableC0028a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Intent f4433d;

        public RunnableC0028a(Intent intent) {
            this.f4433d = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.b(this.f4433d);
            x3.a.e("BtActionDispatcher", "---dispatchBtAction--This action is dispatch completed. action: " + this.f4433d.getAction());
        }
    }

    public static a c() {
        if (f4432a == null) {
            synchronized (a.class) {
                f4432a = new a();
            }
        }
        return f4432a;
    }

    public void a(Intent intent) {
        x3.a.c("BtActionDispatcher", "--dispatchBtAction--action: " + intent.getAction());
        g.a().g(new RunnableC0028a(intent));
    }

    public void b(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        x3.a.e("BtActionDispatcher", "---doActionDispatch--action: " + intent.getAction());
        String action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "android.bluetooth.adapter.action.DISCOVERY_FINISHED":
                l();
                break;
            case "android.bluetooth.adapter.action.STATE_CHANGED":
                q(intent);
                break;
            case "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED":
                o(intent);
                break;
            case "android.bluetooth.device.action.ACL_CONNECTED":
                g(intent);
                break;
            case "android.bluetooth.device.action.PAIRING_REQUEST":
                s(intent);
                break;
            case "android_auto_new_device_pair":
                e(intent);
                break;
            case "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED":
                m(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED":
                p(intent);
                break;
            case "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED":
                j(intent);
                break;
            case "android.bluetooth.headsetclient.profile.action.AG_EVENT":
                f(intent);
                break;
            case "android.bluetooth.device.action.FOUND":
                k(intent);
                break;
            case "android.bluetooth.device.action.ACL_DISCONNECTED":
                h(intent);
                break;
            case "com.ts.carplay.action.OOB_BONDED_FOR_BT":
                r(intent);
                break;
            case "android.bluetooth.device.action.NAME_CHANGED":
                n(intent);
                break;
            case "android.bluetooth.device.action.BOND_STATE_CHANGED":
                i(intent);
                break;
        }
    }

    public final ConnectState d(int i8) {
        return i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 3 ? ConnectState.DISCONNECTED : ConnectState.DISCONNECTING : ConnectState.CONNECTED : ConnectState.CONNECTING : ConnectState.DISCONNECTED;
    }

    public final void e(Intent intent) {
        b bVar = (b) a3.a.c("aa_new_device_pair");
        if (bVar == null) {
            return;
        }
        int intExtra = intent.getIntExtra("op", -1);
        bVar.onNewDevicePair(intExtra);
        x3.a.a("BtActionDispatcher", "onAANewDevicePair", "state = " + intExtra);
    }

    public final void f(Intent intent) {
        c cVar = (c) a3.a.c("bt_battery_changed");
        if (cVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.headsetclient.extra.BATTERY_LEVEL", -1);
        if (intExtra != -1) {
            x3.a.a("BtActionDispatcher", "onAgEventChanged", ", level: " + intExtra + ", device: " + d.c(bluetoothDevice));
            cVar.onBatteryChanged(bluetoothDevice, intExtra);
        }
    }

    public final void g(Intent intent) {
        b3.a aVarC = a3.a.c("bt_connect_state");
        if (aVarC == null || !(aVarC instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        x3.a.a("BtActionDispatcher", "onBtAclConnected", ", device: " + d.c(bluetoothDevice));
        ((e) aVarC).onConnectStateChanged(bluetoothDevice, ConnectState.CONNECTED);
    }

    public final void h(Intent intent) {
        b3.a aVarC = a3.a.c("bt_connect_state");
        if (aVarC == null || !(aVarC instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        x3.a.a("BtActionDispatcher", "onBtAclDisconnected", ", device: " + d.c(bluetoothDevice));
        ((e) aVarC).onConnectStateChanged(bluetoothDevice, ConnectState.DISCONNECTED);
    }

    public final void i(Intent intent) {
        b3.a aVarC = a3.a.c("bt_bond_sate");
        if (aVarC == null || !(aVarC instanceof b3.d)) {
            return;
        }
        b3.d dVar = (b3.d) aVarC;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
        x3.a.c("BtActionDispatcher", "onBtBondStateChanged", ", state = " + intExtra + ", device: " + d.c(bluetoothDevice));
        switch (intExtra) {
            case 10:
                int i8 = intent.getExtras().getInt("android.bluetooth.device.extra.REASON");
                x3.a.a("BtActionDispatcher", "onBtBondStateChanged", ", BOND_NONE--reason: " + i8);
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

    public final void j(Intent intent) {
        b3.a aVarC = a3.a.c("bt_connect_state");
        if (aVarC == null || !(aVarC instanceof e)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
        x3.a.c("BtActionDispatcher", "onBtConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state = " + intExtra);
    }

    public final void k(Intent intent) {
        f fVar = (f) a3.a.c("bt_discover");
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

    public final void l() {
        f fVar = (f) a3.a.c("bt_discover");
        if (fVar == null) {
            return;
        }
        x3.a.a("BtActionDispatcher", "onBtDiscoveryFinished", "---onBtDiscoveryFinished--");
        fVar.onDiscoveryFinished();
    }

    public final void m(Intent intent) {
        b3.a aVarC = a3.a.c("bt_profile_connect_state");
        if (aVarC == null || !(aVarC instanceof i)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        x3.a.c("BtActionDispatcher", "onBtMediaConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state = " + intExtra);
        ((i) aVarC).onConnectedChanged(11, bluetoothDevice, d(intExtra));
    }

    public final void n(Intent intent) {
        e eVar = (e) a3.a.c("bt_connect_state");
        if (eVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        short s7 = intent.getExtras().getShort("android.bluetooth.device.extra.RSSI", (short) -1);
        if (!TextUtils.isEmpty(bluetoothDevice.getName()) && e3.a.b(bluetoothDevice) && ((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue()) {
            x3.a.a("BtActionDispatcher", "onBtNameChanged", "device: " + d.c(bluetoothDevice) + ", rssi: " + ((int) s7));
            BTDevice bTDevice = new BTDevice(bluetoothDevice);
            bTDevice.setRssi(s7);
            bTDevice.setConnected(true);
            eVar.onConnectedBtNameChanged(bTDevice);
        }
    }

    public final void o(Intent intent) {
        b3.a aVarC = a3.a.c("bt_profile_connect_state");
        if (aVarC == null || !(aVarC instanceof i)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        x3.a.c("BtActionDispatcher", "onBtPanConnectStateChanged", "device: " + d.c(bluetoothDevice) + ", state: " + intExtra);
        ((i) aVarC).onConnectedChanged(5, bluetoothDevice, d(intExtra));
    }

    public final void p(Intent intent) {
        b3.a aVarC = a3.a.c("bt_profile_connect_state");
        if (aVarC == null || !(aVarC instanceof i)) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        x3.a.c("BtActionDispatcher", "onBtPhoneConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state = " + intExtra);
        ((i) aVarC).onConnectedChanged(16, bluetoothDevice, d(intExtra));
    }

    public final void q(Intent intent) {
        j jVar = (j) a3.a.c("bt_sate");
        if (jVar == null) {
        }
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
        x3.a.c("BtActionDispatcher", "onBtSateChanged", "state = " + intExtra);
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

    public final void r(Intent intent) {
        b3.a aVarC = a3.a.c("bt_bond_sate");
        if (aVarC == null || !(aVarC instanceof b3.d)) {
            return;
        }
        String stringExtra = intent.getStringExtra("from");
        String stringExtra2 = intent.getStringExtra("btAddr");
        x3.a.a("BtActionDispatcher", "onOobBondStateChanged", "---from: " + stringExtra + ", mac: " + d.d(stringExtra2));
        ((b3.d) aVarC).onOobBonded(stringExtra, stringExtra2);
    }

    public final void s(Intent intent) {
        h hVar = (h) a3.a.c("bt_pair_request");
        if (hVar == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        boolean z7 = false;
        x3.a.a("BtActionDispatcher", "onReceivedBtPairRequest", "---device: " + d.c(bluetoothDevice));
        if (bluetoothDevice == null) {
            return;
        }
        if (!e3.a.b(bluetoothDevice)) {
            x3.a.b("BtActionDispatcher", "onReceivedBtPairRequest", ", device not Support. device: " + d.c(bluetoothDevice));
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
