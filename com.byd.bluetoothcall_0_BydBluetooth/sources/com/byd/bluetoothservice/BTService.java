package com.byd.bluetoothservice;

import android.app.Service;
import android.bluetooth.BluetoothCardEntry;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothPbapClient;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.text.TextUtils;
import android.util.Log;
import com.byd.blockingqueue.Bt_function;
import com.byd.blockingqueue.Priority;
import com.byd.bluetoothservice.b;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BTService extends Service implements c.a.a.a {
    private Sync$SyncState A;
    private boolean B;
    private BluetoothPbapClient.IPbabClientStateListener C;
    BluetoothProfile.ServiceListener D;
    private Handler E;
    private Class<?> l;
    private BluetoothPbapClient m;
    private Sync$SyncState s;
    private final int t;
    private final int u;
    private final int v;
    private final int w;
    private Sync$SyncState x;
    private Sync$SyncState y;
    private Sync$SyncState z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3497b = "BTService";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.blockingqueue.f f3498c = new com.byd.blockingqueue.f(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3499d = "android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3500e = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    private String f = "";
    private long g = 0;
    PhoneStateListener h = new c();
    private BroadcastReceiver i = new d();
    private b.a j = new e();
    private String k = "PbapService";
    private com.byd.blockingqueue.f n = new com.byd.blockingqueue.f(1);
    private int o = 0;
    private List<CallLogInfo> p = new ArrayList();
    private List<ContactsInfo> q = new ArrayList();
    private Sync$SyncType r = Sync$SyncType.PHONE_BOOK;

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3501b;

        /* JADX INFO: renamed from: com.byd.bluetoothservice.BTService$a$a, reason: collision with other inner class name */
        class RunnableC0089a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ List f3503b;

            RunnableC0089a(List list) {
                this.f3503b = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.a.b.c.t().j(this.f3503b);
            }
        }

        a(String str) {
            this.f3501b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.d.a.f2633e.execute(new RunnableC0089a(com.byd.database.db.b.d(BTService.this).m(this.f3501b)));
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3505b;

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ List f3507b;

            a(List list) {
                this.f3507b = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.blockingqueue.c cVar = new com.byd.blockingqueue.c(2);
                cVar.e(this.f3507b);
                BTService.this.n.a(cVar);
            }
        }

        b(String str) {
            this.f3505b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.d.a.f2633e.execute(new a(com.byd.database.db.b.d(BTService.this).l(this.f3505b)));
        }
    }

    class c extends PhoneStateListener {
        c() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
        }
    }

    class d extends BroadcastReceiver {

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ BluetoothDevice f3511b;

            /* JADX INFO: renamed from: com.byd.bluetoothservice.BTService$d$a$a, reason: collision with other inner class name */
            class RunnableC0090a implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ List f3513b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ List f3514c;

                RunnableC0090a(List list, List list2) {
                    this.f3513b = list;
                    this.f3514c = list2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    c.a.b.c.t().j(this.f3513b);
                    c.a.b.c.t().l(this.f3514c);
                    BTService bTService = BTService.this;
                    Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_FAILD;
                    bTService.y = sync$SyncState;
                    c.a.b.b.f0().M0(BTService.this.y);
                    BTService.this.g0(Sync$SyncType.PHONE_BOOK, sync$SyncState);
                    BTService.this.f0(sync$SyncState);
                    BTService.this.g0(Sync$SyncType.ALL_OF_CALL_LOG, sync$SyncState);
                    BTService.this.B = false;
                    c.a.e.b.c(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, "-1");
                    c.a.b.b.f0().I0();
                }
            }

            a(BluetoothDevice bluetoothDevice) {
                this.f3511b = bluetoothDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.a.d.a.f2633e.execute(new RunnableC0090a(com.byd.database.db.b.d(BTService.this).m(this.f3511b.getAddress()), com.byd.database.db.b.d(BTService.this).l(this.f3511b.getAddress())));
            }
        }

        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                BydLogHelper.logD(BTService.this.f3497b, "receiver", "action:" + action);
                if (action == null) {
                    return;
                }
                try {
                    if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                        BydLogHelper.logD("DANDAN", "state = " + intExtra);
                        if (intExtra == 0) {
                            BydLogHelper.logD("DANDAN", "STATE_DISCONNECTED");
                            return;
                        } else if (intExtra == 1) {
                            BydLogHelper.logD("DANDAN", "STATE_CONNECTING");
                            return;
                        } else {
                            if (intExtra != 2) {
                                return;
                            }
                            BydLogHelper.logD("DANDAN", "STATE_CONNECTED");
                            return;
                        }
                    }
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                        com.byd.blockingqueue.d dVar = new com.byd.blockingqueue.d(Bt_function.SETTING);
                        dVar.g(intExtra2);
                        BTService.this.f3498c.a(dVar);
                        return;
                    }
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        BydLogHelper.logD(BTService.this.f3497b, "BluetoothDevice.ACTION_BOND_STATE_CHANGED");
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        int bondState = bluetoothDevice.getBondState();
                        com.byd.blockingqueue.d dVar2 = new com.byd.blockingqueue.d(Bt_function.PAIRD);
                        dVar2.h(bluetoothDevice);
                        dVar2.g(bondState);
                        BTService.this.f3498c.a(dVar2);
                        return;
                    }
                    if (action.equals("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED")) {
                        int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        int intExtra4 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                        BydLogHelper.logD(BTService.this.f3497b, "a2dpSinkConnectionState = " + intExtra3 + "== preblueState == " + intExtra4);
                        if (intExtra4 == 1 && intExtra3 == 0) {
                            return;
                        }
                        BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        com.byd.blockingqueue.d dVar3 = new com.byd.blockingqueue.d(Bt_function.A2DPCONNECT);
                        dVar3.h(bluetoothDevice2);
                        dVar3.g(intExtra3);
                        BTService.this.f3498c.a(dVar3);
                        return;
                    }
                    if (action.equals("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED")) {
                        int intExtra5 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                        int intExtra6 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 0);
                        BydLogHelper.logD("DANDAN", "blueState =========== " + intExtra5 + "== preblueState == " + intExtra6);
                        if (intExtra6 == 1 && intExtra5 == 0) {
                            return;
                        }
                        BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        com.byd.blockingqueue.d dVar4 = new com.byd.blockingqueue.d(Bt_function.CONNECT);
                        dVar4.h(bluetoothDevice3);
                        dVar4.g(intExtra5);
                        BTService.this.f3498c.a(dVar4);
                        return;
                    }
                    if (action.equals("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED")) {
                        BluetoothHeadsetClientCall parcelableExtra = intent.getParcelableExtra("android.bluetooth.headsetclient.extra.CALL");
                        if (parcelableExtra == null) {
                            BydLogHelper.logD("DANDAN", "mCall is null");
                            return;
                        }
                        BydLogHelper.logD("DANDAN", "callState = " + parcelableExtra.getState() + " number == " + c.a.e.c.b(parcelableExtra.getNumber()));
                        com.byd.blockingqueue.d dVar5 = new com.byd.blockingqueue.d(Bt_function.CALL);
                        dVar5.g(parcelableExtra.getState());
                        if (TextUtils.isEmpty(parcelableExtra.getNumber())) {
                            BydLogHelper.logD("DANDAN", "number is null");
                            dVar5.i("null_number");
                        } else {
                            dVar5.i(parcelableExtra.getNumber());
                        }
                        dVar5.d(Priority.Immediately);
                        BTService.this.f3498c.a(dVar5);
                        return;
                    }
                    if (action.equals("android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED")) {
                        c.a.b.b.f0().p(intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1));
                        return;
                    }
                    if (action.equals("android.bluetooth.headsetclient.profile.action.AG_EVENT")) {
                        BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (bluetoothDevice4 != null) {
                            int intExtra7 = intent.getIntExtra("android.bluetooth.headsetclient.extra.BATTERY_LEVEL", -1);
                            if (intExtra7 != -1) {
                                BydLogHelper.logD("DANDAN", "BatLevel = " + intExtra7 + " = device = " + bluetoothDevice4.getName());
                                com.byd.blockingqueue.d dVar6 = new com.byd.blockingqueue.d(Bt_function.BATLEVEL);
                                dVar6.g(intExtra7);
                                dVar6.h(bluetoothDevice4);
                                BTService.this.f3498c.a(dVar6);
                            }
                            String stringExtra = intent.getStringExtra("android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO");
                            if (TextUtils.isEmpty(stringExtra)) {
                                Log.d(BTService.this.f3497b, "localnumber = null");
                                return;
                            }
                            Log.d(BTService.this.f3497b, "localnumber = " + c.a.e.c.b(stringExtra));
                            c.a.b.b.f0().Q0(stringExtra);
                            return;
                        }
                        return;
                    }
                    if (action.equals("android.bluetooth.avrcp-controller.profile.action.TRACK_EVENT")) {
                        return;
                    }
                    if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                        Intent intent2 = (Intent) intent.clone();
                        intent2.setClassName("com.byd.carsettings", "com.byd.dialogservice.DialogService");
                        context.startService(intent2);
                        return;
                    }
                    if (action.equals("com.android.phone.NotificationMgr.MissedCall_intent")) {
                        BydLogHelper.logD("DANDAN", "mMissCallCount =========== " + intent.getExtras().getInt("MissedCallNumber"));
                        return;
                    }
                    if (action.equals("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED") && BTService.this.B) {
                        int intExtra8 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                        int intExtra9 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        BluetoothDevice bluetoothDevice5 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        Log.d(BTService.this.k, "privious_state = " + intExtra8 + " =state = " + intExtra9);
                        if (bluetoothDevice5 == null || intExtra9 == 1 || intExtra9 == 2 || intExtra9 != 3 || intExtra8 != 1) {
                            return;
                        }
                        c.a.d.a.g.execute(new a(bluetoothDevice5));
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    class e extends b.a {
        e() {
        }

        @Override // com.byd.bluetoothservice.b
        public String A() throws RemoteException {
            BluetoothDevice bluetoothDeviceE0 = c.a.b.b.f0().e0();
            if (bluetoothDeviceE0 != null) {
                return bluetoothDeviceE0.getName();
            }
            return null;
        }

        @Override // com.byd.bluetoothservice.b
        public void BT_Card_Slides_up() throws RemoteException {
            c.a.b.b.f0().a();
        }

        @Override // com.byd.bluetoothservice.b
        public void C(com.byd.bluetoothservice.a aVar) throws RemoteException {
            c.a.b.b.f0().N(aVar);
        }

        @Override // com.byd.bluetoothservice.b
        public void D() throws RemoteException {
            c.a.b.a.K().d();
        }

        @Override // com.byd.bluetoothservice.b
        public String E() throws RemoteException {
            return c.a.b.a.K().P();
        }

        @Override // com.byd.bluetoothservice.b
        public boolean G() throws RemoteException {
            return c.a.b.b.f0().d();
        }

        @Override // com.byd.bluetoothservice.b
        public int I() throws RemoteException {
            Log.d(BTService.this.f3497b, "getSyncContactsState");
            int i = 4;
            if (c.a.b.b.f0().o0() == Sync$SyncState.SYNC_START) {
                i = 1;
            } else if (c.a.b.b.f0().o0() == Sync$SyncState.SYNC_ING) {
                i = 2;
            } else if (c.a.b.b.f0().o0() == Sync$SyncState.SYNC_FAILD) {
                i = 3;
            } else if (c.a.b.b.f0().o0() != Sync$SyncState.SYNC_SUCCEED && (c.a.b.b.f0().o0() != Sync$SyncState.SYNC_END || (c.a.b.c.t().y(null) != null && c.a.b.c.t().y(null).size() <= 0))) {
                i = 0;
            }
            Log.d(BTService.this.f3497b, "getSyncContactsState = " + i);
            return i;
        }

        @Override // com.byd.bluetoothservice.b
        public int J() throws RemoteException {
            return c.a.b.a.K().Q();
        }

        @Override // com.byd.bluetoothservice.b
        public BluetoothDevice K() throws RemoteException {
            return c.a.b.b.f0().e0();
        }

        @Override // com.byd.bluetoothservice.b
        public boolean L() throws RemoteException {
            return c.a.b.b.f0().W();
        }

        @Override // com.byd.bluetoothservice.b
        public String O() throws RemoteException {
            return c.a.b.a.K().O();
        }

        @Override // com.byd.bluetoothservice.b
        public int P() throws RemoteException {
            return c.a.b.b.f0().l0().size() > 0 ? 1 : 0;
        }

        @Override // com.byd.bluetoothservice.b
        public void Q() throws RemoteException {
            c.a.b.a.K().g();
        }

        @Override // com.byd.bluetoothservice.b
        public String R() throws RemoteException {
            BluetoothDevice bluetoothDeviceC0 = c.a.b.b.f0().c0();
            if (bluetoothDeviceC0 != null) {
                return bluetoothDeviceC0.getName();
            }
            return null;
        }

        @Override // com.byd.bluetoothservice.b
        public int W() throws RemoteException {
            return c.a.b.b.f0().V();
        }

        @Override // com.byd.bluetoothservice.b
        public void WidgetCardShow(boolean z) throws RemoteException {
            c.a.b.b.f0().r(z);
        }

        @Override // com.byd.bluetoothservice.b
        public void X() throws RemoteException {
            c.a.b.a.K().e();
        }

        @Override // com.byd.bluetoothservice.b
        public void Z(boolean z) throws RemoteException {
            c.a.b.b.f0().K0(z);
        }

        @Override // com.byd.bluetoothservice.b
        public long j() throws RemoteException {
            return c.a.b.a.K().R();
        }

        @Override // com.byd.bluetoothservice.b
        public BluetoothDevice k() throws RemoteException {
            return c.a.b.b.f0().c0();
        }

        @Override // com.byd.bluetoothservice.b
        public void m() throws RemoteException {
            c.a.b.a.K().c();
        }

        @Override // com.byd.bluetoothservice.b
        public boolean n() throws RemoteException {
            return c.a.b.b.f0().e();
        }

        @Override // com.byd.bluetoothservice.b
        public void o() throws RemoteException {
            BydLogHelper.logD("DANDAN", "StartSyncContacts=====================================");
            c.a.b.c.t().g();
        }

        @Override // com.byd.bluetoothservice.b
        public String q() throws RemoteException {
            return c.a.b.a.K().N();
        }

        @Override // com.byd.bluetoothservice.b
        public long s() throws RemoteException {
            return c.a.b.a.K().S();
        }

        @Override // com.byd.bluetoothservice.b
        public void u() throws RemoteException {
            c.a.b.a.K().f();
        }

        @Override // com.byd.bluetoothservice.b
        public String w() throws RemoteException {
            return c.a.b.b.f0().X();
        }

        @Override // com.byd.bluetoothservice.b
        public void y(com.byd.bluetoothservice.a aVar) throws RemoteException {
            c.a.b.b.f0().H0(aVar);
        }
    }

    class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3517b;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BTService bTService = BTService.this;
                Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_END;
                bTService.x = sync$SyncState;
                if (c.a.b.b.f0().e0() == null || !f.this.f3517b.equals(c.a.b.b.f0().e0().getAddress())) {
                    return;
                }
                f fVar = f.this;
                BTService.this.b0(fVar.f3517b);
                BTService.this.y = sync$SyncState;
                c.a.b.b.f0().M0(BTService.this.y);
                if (BTService.this.o < 1) {
                    BTService.this.f0(Sync$SyncState.SYNC_FAILD);
                    c.a.e.b.c(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, "-1");
                } else {
                    BTService.this.f0(Sync$SyncState.SYNC_SUCCEED);
                    c.a.e.b.c(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, "1");
                }
                BTService.this.g0(Sync$SyncType.PHONE_BOOK, sync$SyncState);
                c.a.b.b.f0().I0();
            }
        }

        f(String str) {
            this.f3517b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<String> listR = com.byd.database.db.b.d(BTService.this).r();
            if (listR != null && listR.size() >= 10) {
                for (int i = 0; i < listR.size() - 9; i++) {
                    com.byd.database.db.b.d(BTService.this).c(listR.get(i));
                }
            }
            com.byd.database.db.b.d(BTService.this).i(BTService.this.q);
            c.a.d.a.f2633e.execute(new a());
        }
    }

    class g implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3520b;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.a.b.b.f0().e0() == null || !g.this.f3520b.equals(c.a.b.b.f0().e0().getAddress())) {
                    return;
                }
                BTService bTService = BTService.this;
                Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_END;
                bTService.z = sync$SyncState;
                g gVar = g.this;
                BTService.this.a0(gVar.f3520b);
                BTService.this.g0(Sync$SyncType.ALL_OF_CALL_LOG, sync$SyncState);
            }
        }

        g(String str) {
            this.f3520b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BTService.this.p.size() > 0) {
                com.byd.database.db.b.d(BTService.this).h(BTService.this.p);
            }
            c.a.d.a.f2633e.execute(new a());
        }
    }

    class h implements BluetoothPbapClient.IPbabClientStateListener {

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f3524b;

            /* JADX INFO: renamed from: com.byd.bluetoothservice.BTService$h$a$a, reason: collision with other inner class name */
            class RunnableC0091a implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ List f3526b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ List f3527c;

                RunnableC0091a(List list, List list2) {
                    this.f3526b = list;
                    this.f3527c = list2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    c.a.b.c.t().j(this.f3526b);
                    c.a.b.c.t().l(this.f3527c);
                    BTService bTService = BTService.this;
                    Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_FAILD;
                    bTService.y = sync$SyncState;
                    c.a.b.b.f0().M0(BTService.this.y);
                    BTService.this.g0(Sync$SyncType.PHONE_BOOK, sync$SyncState);
                    BTService.this.f0(sync$SyncState);
                    BTService.this.n();
                    c.a.e.b.c(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, "2");
                    c.a.b.b.f0().I0();
                }
            }

            a(String str) {
                this.f3524b = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.a.d.a.f2633e.execute(new RunnableC0091a(com.byd.database.db.b.d(BTService.this).m(this.f3524b), com.byd.database.db.b.d(BTService.this).l(this.f3524b)));
            }
        }

        h() {
        }

        public void onGetCalllogComplete(String str, List<BluetoothCardEntry> list, String str2) {
            if (c.a.b.b.f0().e0() == null || !str2.equals(c.a.b.b.f0().e0().getAddress())) {
                return;
            }
            Log.d(BTService.this.k, "onGetCalllogComplete s == " + str + "==list == " + list.size());
            BTService.this.X(list, c.a.b.b.f0().e0());
        }

        public void onGetCalllogState(String str, int i, String str2) {
            if (c.a.b.b.f0().e0() == null || !str2.equals(c.a.b.b.f0().e0().getAddress())) {
                return;
            }
            Log.d(BTService.this.k, "onGetCalllogState == S==" + str + "== i =  " + i);
            if (i == 1) {
                BTService.this.g0(Sync$SyncType.ALL_OF_CALL_LOG, Sync$SyncState.SYNC_START);
                return;
            }
            if (i == 2) {
                BTService.this.d0(str2);
                BTService.this.g0(Sync$SyncType.ALL_OF_CALL_LOG, Sync$SyncState.SYNC_SUCCEED);
                BTService.this.n();
            } else {
                if (i != 3) {
                    return;
                }
                BTService.this.g0(Sync$SyncType.ALL_OF_CALL_LOG, Sync$SyncState.SYNC_FAILD);
                BTService.this.n();
            }
        }

        public void onGetPhoneBookComplete(String str, List<BluetoothCardEntry> list, String str2) {
            Log.d(BTService.this.k, "onGetPhoneBookComplete s == " + str + "==list == " + list.size());
            if (c.a.b.b.f0().e0() == null || !str2.equals(c.a.b.b.f0().e0().getAddress())) {
                return;
            }
            BTService.this.Z(list, str2);
        }

        public void onGetPhoneBookSize(String str, int i, String str2) {
            Log.d(BTService.this.f3497b, "totle = " + i + "== s == " + str);
            BTService.this.o = i;
            BTService bTService = BTService.this;
            bTService.y(bTService.o);
        }

        public void onGetPhoneBookState(String str, int i, String str2) {
            Log.d(BTService.this.k, "onGetPhoneBookState == S==" + str + "== i ==" + i);
            if (c.a.b.b.f0().e0() == null) {
                BTService.this.w();
                return;
            }
            if (str2.equals(c.a.b.b.f0().e0().getAddress())) {
                Log.d(BTService.this.k, "onGetPhoneBookState ==== i === " + i);
                if (i == 1) {
                    BTService bTService = BTService.this;
                    Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_START;
                    bTService.y = sync$SyncState;
                    c.a.b.b.f0().M0(BTService.this.y);
                    BTService.this.g0(Sync$SyncType.PHONE_BOOK, sync$SyncState);
                    BTService.this.f0(sync$SyncState);
                    BTService.this.q.clear();
                    BTService.this.p.clear();
                    BTService.this.h(str2);
                    c.a.e.b.c(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, "0");
                    return;
                }
                if (i != 2) {
                    if (i != 3) {
                        return;
                    }
                    c.a.d.a.g.execute(new a(str2));
                } else {
                    BTService bTService2 = BTService.this;
                    Sync$SyncState sync$SyncState2 = Sync$SyncState.SYNC_SUCCEED;
                    bTService2.y = sync$SyncState2;
                    c.a.b.b.f0().M0(BTService.this.y);
                    BTService.this.g0(Sync$SyncType.PHONE_BOOK, sync$SyncState2);
                    BTService.this.c0(str2);
                }
            }
        }
    }

    class i implements BluetoothProfile.ServiceListener {
        i() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            Log.d(BTService.this.k, "连接上了：" + i);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            Log.d(BTService.this.k, "onServiceDisconnected === " + i);
        }
    }

    class j extends Handler {
        j() {
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            switch (message.what) {
                case 10000:
                    BTService.this.c();
                    break;
                case 10001:
                    BTService.this.d();
                    break;
                case 10002:
                    BTService.this.c();
                    break;
                case 10004:
                    BTService.this.x();
                    break;
                case 10007:
                    BTService.this.A = Sync$SyncState.SYNC_END;
                    BTService bTService = BTService.this;
                    bTService.g0(Sync$SyncType.WHOLE_OF_THEM, bTService.A);
                    break;
                case 10008:
                    BTService.this.m(c.a.b.b.f0().e0());
                    break;
            }
        }
    }

    public BTService() {
        Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_END;
        this.s = sync$SyncState;
        this.t = 10001;
        this.u = 10002;
        this.v = 10004;
        this.w = 10007;
        this.x = sync$SyncState;
        this.y = sync$SyncState;
        this.z = sync$SyncState;
        this.A = sync$SyncState;
        this.B = false;
        this.C = new h();
        this.D = new i();
        this.E = new j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(String str) {
        c.a.d.a.g.execute(new f(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(String str) {
        c.a.d.a.g.execute(new g(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(Sync$SyncState sync$SyncState) {
        c.a.b.b.f0().q(sync$SyncState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g0(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState) {
        c.a.b.c.t().n(sync$SyncType, sync$SyncState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(BluetoothDevice bluetoothDevice) {
        Log.d(this.k, "ConectPbap == " + this.m);
        if (this.m == null || bluetoothDevice == null) {
            return;
        }
        Log.d(this.k, "client.getConnectionState(hfpConnectedDevice) === " + this.m.getConnectionState(bluetoothDevice));
        if (this.m.getConnectionState(bluetoothDevice) != 0) {
            this.B = false;
            if (this.m.getConnectionState(bluetoothDevice) == 2 || this.m.getConnectionState(bluetoothDevice) == 1) {
                this.m.disconnect(bluetoothDevice);
            }
            if (this.E.hasMessages(10008)) {
                this.E.removeMessages(10008);
            }
            this.E.sendEmptyMessageDelayed(10008, 500L);
            return;
        }
        this.q.clear();
        this.p.clear();
        h(bluetoothDevice.getAddress());
        BluetoothPbapClient bluetoothPbapClient = this.m;
        if (bluetoothPbapClient != null) {
            bluetoothPbapClient.registerPbabClientCallback(this.C);
        }
        this.B = true;
        boolean zConnect = this.m.connect(bluetoothDevice);
        Log.d(this.k, "result == " + zConnect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        List connectedDevices;
        Log.d(this.k, "DisConnectPbap == ");
        this.B = false;
        BluetoothPbapClient bluetoothPbapClient = this.m;
        if (bluetoothPbapClient != null) {
            bluetoothPbapClient.registerPbabClientCallback((BluetoothPbapClient.IPbabClientStateListener) null);
        }
        BluetoothPbapClient bluetoothPbapClient2 = this.m;
        if (bluetoothPbapClient2 == null || (connectedDevices = bluetoothPbapClient2.getConnectedDevices()) == null) {
            return;
        }
        for (int i2 = 0; i2 < connectedDevices.size(); i2++) {
            this.m.disconnect((BluetoothDevice) connectedDevices.get(i2));
        }
    }

    private void o() {
        try {
            Class<?> cls = Class.forName("android.bluetooth.BluetoothPbapClient");
            this.l = cls;
            this.m = (BluetoothPbapClient) cls.getDeclaredConstructor(Context.class, BluetoothProfile.ServiceListener.class).newInstance(this, this.D);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    private CallLogInfo r(String str) {
        return com.byd.database.db.b.d(this).n(str);
    }

    private void s() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_EVENT");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.android.phone.NotificationMgr.MissedCall_intent");
        intentFilter.addAction("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        registerReceiver(this.i, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        Sync$SyncState sync$SyncState = this.y;
        if (sync$SyncState == Sync$SyncState.SYNC_START || sync$SyncState == Sync$SyncState.SYNC_ING) {
            Sync$SyncState sync$SyncState2 = Sync$SyncState.SYNC_END;
            this.y = sync$SyncState2;
            c.a.b.b.f0().M0(this.y);
            g0(Sync$SyncType.PHONE_BOOK, sync$SyncState2);
            f0(sync$SyncState2);
            g0(Sync$SyncType.ALL_OF_CALL_LOG, sync$SyncState2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i2) {
        c.a.b.c.t().h(i2);
    }

    public void X(List<BluetoothCardEntry> list, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        this.z = Sync$SyncState.SYNC_ING;
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                CallLogInfo callLogInfo = new CallLogInfo();
                String strReplace = c.a.e.c.d(list.get(i2).getNumber()).replace("-", "");
                String displayName = TextUtils.isEmpty(strReplace) ? "" : list.get(i2).getDisplayName();
                if (TextUtils.isEmpty(displayName)) {
                    displayName = Y(strReplace, bluetoothDevice.getAddress());
                }
                long dateTime = list.get(i2).getDateTime();
                int type = list.get(i2).getType();
                if (type == 1) {
                    callLogInfo.A("RECEIVED");
                } else if (type == 2) {
                    callLogInfo.A("DIALED");
                } else if (type == 3) {
                    callLogInfo.A("MISSED");
                }
                callLogInfo.w(displayName);
                callLogInfo.x(strReplace);
                callLogInfo.z(dateTime);
                callLogInfo.v(bluetoothDevice.getAddress());
                this.p.add(callLogInfo);
            }
        }
        Log.d(this.k, "getCallLogList == " + this.p.size());
    }

    public String Y(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String strB = c.a.e.b.b(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        List<ContactsInfo> listD = c.a.b.c.t().d(str);
        if (listD == null || listD.size() == 0) {
            List<CallLogInfo> listC = c.a.b.c.t().c(str);
            if (listC != null && listC.size() != 0) {
                for (int i2 = 0; i2 < listC.size(); i2++) {
                    if (str.equals(listC.get(i2).o())) {
                        return listC.get(i2).n();
                    }
                }
            } else if (str.length() > 5 && str.startsWith("+")) {
                if (!TextUtils.isEmpty(strB) && !strB.equals("unknown")) {
                    List<ContactsInfo> listV = c.a.b.c.t().v(str.substring(("+" + strB).length()));
                    if (listV != null && listV.size() != 0) {
                        for (int i3 = 0; i3 < listV.size(); i3++) {
                            for (String str3 : listV.get(i3).k().split("and")) {
                                if (str.endsWith(str3)) {
                                    return listV.get(i3).j();
                                }
                            }
                        }
                    }
                }
                return str;
            }
        } else {
            for (int i4 = 0; i4 < listD.size(); i4++) {
                String[] strArrSplit = listD.get(i4).k().split("and");
                for (int i5 = 0; i5 < strArrSplit.length; i5++) {
                    if (str.equals(strArrSplit[i5])) {
                        return listD.get(i4).j();
                    }
                    if (TextUtils.isEmpty(strB) || strB.equals("unknown")) {
                        return str;
                    }
                    String str4 = "+" + strB;
                    if (strArrSplit[i5].startsWith(str4)) {
                        if (strArrSplit[i5].substring(str4.length()).equals(str)) {
                            return listD.get(i4).j();
                        }
                    }
                }
            }
        }
        return "";
    }

    public void Z(List<BluetoothCardEntry> list, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.x = Sync$SyncState.SYNC_ING;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ContactsInfo contactsInfo = new ContactsInfo();
            contactsInfo.s(list.get(i2).getNumber());
            String strSubstring = contactsInfo.k().contains("and") ? contactsInfo.k().substring(0, contactsInfo.k().indexOf("and")) : contactsInfo.k();
            if (TextUtils.isEmpty(list.get(i2).getDisplayName()) || list.get(i2).getDisplayName().equals(strSubstring)) {
                contactsInfo.r(strSubstring.replace("-", ""));
            } else {
                contactsInfo.r(list.get(i2).getDisplayName());
            }
            if (!TextUtils.isEmpty(contactsInfo.j())) {
                contactsInfo.p(list.get(i2).getKatakana());
                contactsInfo.n(c.a.e.c.c(contactsInfo.j(), 1));
                String strA = c.a.e.a.a(contactsInfo.j());
                if (!TextUtils.isEmpty(strA)) {
                    contactsInfo.t(strA);
                    contactsInfo.u(c.a.e.a.c(c.a.e.a.b(strA)));
                }
                contactsInfo.q(str);
                this.q.add(contactsInfo);
                c.a.b.c.t().m(contactsInfo, this.o, this.q.size());
            }
        }
        Log.d(this.k, "contactInfos == " + this.q.size());
    }

    @Override // c.a.a.a
    public List<ContactsInfo> a(String str, String str2) {
        BluetoothDevice bluetoothDeviceE0 = c.a.b.b.f0().e0();
        if (bluetoothDeviceE0 == null) {
            return null;
        }
        return e0(str, bluetoothDeviceE0.getAddress());
    }

    public void a0(String str) {
        c.a.d.a.g.execute(new b(str));
    }

    @Override // c.a.a.a
    public void b(CallLogInfo callLogInfo) {
        com.byd.database.db.b.d(this).g(callLogInfo.n(), callLogInfo.o(), callLogInfo.m(), callLogInfo.r(), callLogInfo.q());
        com.byd.blockingqueue.c cVar = new com.byd.blockingqueue.c(3);
        cVar.h(callLogInfo);
        this.n.a(cVar);
    }

    public void b0(String str) {
        c.a.d.a.g.execute(new a(str));
    }

    @Override // c.a.a.a
    public void c() {
        BluetoothDevice bluetoothDeviceE0 = c.a.b.b.f0().e0();
        Log.d(this.k, "SyncCallLog ================= ConectPbap ==  == " + this.m);
        if (this.m == null || bluetoothDeviceE0 == null) {
            return;
        }
        Log.d(this.k, "client.getConnectionState(hfpConnectedDevice) === " + this.m.getConnectionState(bluetoothDeviceE0));
        if (this.m.getConnectionState(bluetoothDeviceE0) != 0) {
            if (this.m.getConnectionState(bluetoothDeviceE0) == 2 || this.m.getConnectionState(bluetoothDeviceE0) == 1) {
                this.m.disconnect(bluetoothDeviceE0);
                return;
            }
            return;
        }
        BluetoothPbapClient bluetoothPbapClient = this.m;
        if (bluetoothPbapClient != null) {
            bluetoothPbapClient.registerPbabClientCallback(this.C);
        }
        boolean zConnectFromScene = this.m.connectFromScene(bluetoothDeviceE0, 2);
        Log.d(this.k, "SyncCallLog ============  result == " + zConnectFromScene);
    }

    @Override // c.a.a.a
    public void d() {
        f();
    }

    @Override // c.a.a.a
    public List<CallLogInfo> e(String str, String str2) {
        return t(str, str2);
    }

    public List<ContactsInfo> e0(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.byd.database.db.b.d(this).p(str, str2);
    }

    @Override // c.a.a.a
    public void f() {
        Log.d(this.k, "ConnectPbap=====" + this.y);
        Sync$SyncState sync$SyncState = this.y;
        if (sync$SyncState == Sync$SyncState.SYNC_END || sync$SyncState == Sync$SyncState.SYNC_FAILD || sync$SyncState == Sync$SyncState.SYNC_SUCCEED) {
            m(c.a.b.b.f0().e0());
        } else {
            g0(Sync$SyncType.PHONE_BOOK, sync$SyncState);
        }
    }

    @Override // c.a.a.a
    public CallLogInfo g(String str, String str2) {
        return u(str, str2);
    }

    @Override // c.a.a.a
    public void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.byd.database.db.b.d(this).b(str);
    }

    @Override // c.a.a.a
    public List<ContactsInfo> i(String str, String str2) {
        return v(str, str2);
    }

    @Override // c.a.a.a
    public List<ContactsInfo> j(String str, String str2) {
        return q(str, str2);
    }

    @Override // c.a.a.a
    public CallLogInfo k(String str) {
        return r(str);
    }

    @Override // c.a.a.a
    public List<CallLogInfo> l(Sync$SyncType sync$SyncType, String str) {
        return p(c.a.e.c.a(sync$SyncType), str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        BydLogHelper.logD(this.f3497b, "onBind", "onBind:intent = " + intent.toString());
        return this.j;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD(this.f3497b, "onCreate", " == onCreate ===");
        c.a.b.b.f0().A0(this);
        c.a.b.a.K().T(this);
        c.a.b.c.t().u(this);
        c.a.b.c.t().z(this);
        this.n.b();
        o();
        this.f3498c.b();
        s();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logD(this.f3497b, this.f3497b + " == onDestroy ===");
        unregisterReceiver(this.i);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public List<CallLogInfo> p(int i2, String str) {
        return i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? new ArrayList() : com.byd.database.db.b.d(this).t(str) : com.byd.database.db.b.d(this).s(str) : com.byd.database.db.b.d(this).q(str) : com.byd.database.db.b.d(this).l(str);
    }

    public List<ContactsInfo> q(String str, String str2) {
        return (str == null || TextUtils.isEmpty(str)) ? com.byd.database.db.b.d(this).m(str2) : com.byd.database.db.b.d(this).o(str, str2);
    }

    public List<CallLogInfo> t(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.byd.database.db.b.d(this).u(str, str2);
    }

    public CallLogInfo u(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.byd.database.db.b.d(this).a(str, str2);
    }

    public List<ContactsInfo> v(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.byd.database.db.b.d(this).v(str, str2);
    }

    public void x() {
    }
}
