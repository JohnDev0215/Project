package c.a.b;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import android.util.Log;
import com.byd.constant.StateConstant$SCO_STATE;
import com.byd.info.BluetoothDeviceInfo;
import com.byd.info.BtCallInfo;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: HfpBluetoothManage.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a */
    private static volatile b f2601a;
    private final int A;
    private final int B;
    private final int C;
    private final int D;
    private final int E;
    private final int F;
    private final int G;
    private final int H;
    private final int I;
    private final int J;
    private final int K;
    private final int L;
    private final int M;
    private final int N;
    private final int O;
    private final int P;
    private final int Q;
    private final int R;
    private final int S;
    private final int T;
    private final int U;
    private int V;
    private boolean W;
    private TelecomManager X;
    private Sync$SyncState Y;
    private BluetoothProfile.ServiceListener Z;
    private Handler a0;
    private int b0;

    /* JADX INFO: renamed from: c */
    private Context f2603c;
    private boolean c0;

    /* JADX INFO: renamed from: d */
    private BluetoothManager f2604d;
    private List<CallLogInfo> d0;

    /* JADX INFO: renamed from: e */
    private BluetoothAdapter f2605e;
    private final String e0;
    private BluetoothHeadsetClient f;
    private String f0;
    private BtCallInfo i;
    private Sync$SyncState n;
    private BluetoothDevice o;
    private BluetoothDevice p;
    private int q;
    private String r;
    private int s;
    private String t;
    private String u;
    private String v;
    private String w;
    private int x;
    private int y;
    private StateConstant$SCO_STATE z;

    /* JADX INFO: renamed from: b */
    private final String f2602b = b.class.getSimpleName();
    private CopyOnWriteArrayList<BluetoothDeviceInfo> g = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<BtCallInfo> h = new CopyOnWriteArrayList<>();
    private ArrayList<c.a.a.c> j = new ArrayList<>();
    private ArrayList<c.a.a.b> k = new ArrayList<>();
    private RemoteCallbackList<com.byd.bluetoothservice.a> l = new RemoteCallbackList<>();
    private int m = 0;

    /* JADX INFO: compiled from: HfpBluetoothManage.java */
    class a implements BluetoothProfile.ServiceListener {
        a() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i == 16) {
                Log.d("HfpBlueTooth", "onServiceConnected === BT INIT FINISHED + 4500");
                b.this.f = (BluetoothHeadsetClient) bluetoothProfile;
                b.this.a0.sendEmptyMessageDelayed(BYDAutoStatisticDevice.MILEAGE_MAX, 300L);
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 16) {
                b.this.f = null;
            }
        }
    }

    /* JADX INFO: renamed from: c.a.b.b$b */
    /* JADX INFO: compiled from: HfpBluetoothManage.java */
    class HandlerC0067b extends Handler {
        HandlerC0067b(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = 0;
            switch (message.what) {
                case BYDAutoStatisticDevice.MILEAGE_MAX /* 99999 */:
                    if (c.a.b.a.K().U()) {
                        b.this.g.clear();
                        b.this.g.addAll(b.this.Y());
                        b.this.f();
                        b bVar = b.this;
                        bVar.g(bVar.e0(), true);
                        while (i < b.this.j.size()) {
                            ((c.a.a.c) b.this.j.get(i)).initFinished();
                            i++;
                        }
                    } else {
                        b.this.a0.sendEmptyMessageDelayed(BYDAutoStatisticDevice.MILEAGE_MAX, 300L);
                    }
                    break;
                case 100001:
                    b.this.Q(1);
                    break;
                case 100002:
                    b.this.O();
                    break;
                case 100003:
                    if (c.t().y(null) == null) {
                        c.t().a();
                    } else if (c.t().y(null).size() <= 0) {
                        c.t().a();
                    } else {
                        c cVarT = c.t();
                        Sync$SyncType sync$SyncType = Sync$SyncType.ALL_OF_CALL_LOG;
                        if (cVarT.w(sync$SyncType) == null) {
                            c.t().e();
                        } else if (c.t().w(sync$SyncType).size() <= 0) {
                            c.t().e();
                        } else {
                            c.a.e.b.c(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, "1");
                            b.this.I0();
                            while (i < b.this.k.size()) {
                                ((c.a.a.b) b.this.k.get(i)).Database_contact_already_exists(true);
                                i++;
                            }
                        }
                    }
                    break;
                case 100004:
                    for (int i2 = 0; i2 < b.this.j.size(); i2++) {
                        ((c.a.a.c) b.this.j.get(i2)).connectStateChange(b.this.o, b.this.q);
                    }
                    b bVar2 = b.this;
                    bVar2.m = bVar2.l.beginBroadcast();
                    if (b.this.m > 0) {
                        while (i < b.this.m) {
                            try {
                                ((com.byd.bluetoothservice.a) b.this.l.getBroadcastItem(i)).V(b.this.q);
                                i++;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    b.this.l.finishBroadcast();
                    break;
                case 100005:
                    for (int i3 = 0; i3 < b.this.k.size(); i3++) {
                        ((c.a.a.b) b.this.k.get(i3)).CallState_Change(b.this.h);
                    }
                    b bVar3 = b.this;
                    bVar3.m = bVar3.l.beginBroadcast();
                    if (b.this.m > 0) {
                        while (i < b.this.m) {
                            try {
                                ((com.byd.bluetoothservice.a) b.this.l.getBroadcastItem(i)).CallState_Change(b.this.h);
                                i++;
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    b.this.l.finishBroadcast();
                    break;
                case 100006:
                    for (int i4 = 0; i4 < b.this.k.size(); i4++) {
                        ((c.a.a.b) b.this.k.get(i4)).Update_Call_Channel(b.this.z);
                    }
                    b bVar4 = b.this;
                    bVar4.m = bVar4.l.beginBroadcast();
                    if (b.this.m > 0) {
                        while (i < b.this.m) {
                            try {
                                com.byd.bluetoothservice.a aVar = (com.byd.bluetoothservice.a) b.this.l.getBroadcastItem(i);
                                b bVar5 = b.this;
                                aVar.T(bVar5.j(bVar5.z));
                                i++;
                            } catch (RemoteException e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                    b.this.l.finishBroadcast();
                    break;
                case 100007:
                    for (int i5 = 0; i5 < b.this.j.size(); i5++) {
                        ((c.a.a.c) b.this.j.get(i5)).update_BatLevel(b.this.V, (BluetoothDevice) message.obj);
                    }
                    b bVar6 = b.this;
                    bVar6.m = bVar6.l.beginBroadcast();
                    if (b.this.m > 0) {
                        while (i < b.this.m) {
                            try {
                                ((com.byd.bluetoothservice.a) b.this.l.getBroadcastItem(i)).updateBattery(b.this.V);
                                i++;
                            } catch (RemoteException e5) {
                                e5.printStackTrace();
                            }
                        }
                    }
                    b.this.l.finishBroadcast();
                    break;
                case 100008:
                    b.this.Q(2);
                    break;
                case 100009:
                    boolean zBooleanValue = ((Boolean) message.obj).booleanValue();
                    for (int i6 = 0; i6 < b.this.k.size(); i6++) {
                        ((c.a.a.b) b.this.k.get(i6)).WidgetKeyBoardShow(zBooleanValue);
                    }
                    b bVar7 = b.this;
                    bVar7.m = bVar7.l.beginBroadcast();
                    if (b.this.m > 0) {
                        while (i < b.this.m) {
                            try {
                                ((com.byd.bluetoothservice.a) b.this.l.getBroadcastItem(i)).B(zBooleanValue);
                                i++;
                            } catch (RemoteException e6) {
                                e6.printStackTrace();
                            }
                        }
                    }
                    b.this.l.finishBroadcast();
                    break;
                case 100010:
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).WidgetCardShow(b.this.W);
                        i++;
                    }
                    break;
                case 100011:
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).BT_Card_Slides_up();
                        i++;
                    }
                    break;
                case 100012:
                    boolean zBooleanValue2 = ((Boolean) message.obj).booleanValue();
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).Call_Activity_is_Top(zBooleanValue2);
                        i++;
                    }
                    break;
                case 100013:
                    b bVar8 = b.this;
                    bVar8.m = bVar8.l.beginBroadcast();
                    if (b.this.m > 0) {
                        while (i < b.this.m) {
                            try {
                                com.byd.bluetoothservice.a aVar2 = (com.byd.bluetoothservice.a) b.this.l.getBroadcastItem(i);
                                b bVar9 = b.this;
                                aVar2.h(bVar9.h0(bVar9.n));
                                i++;
                            } catch (RemoteException e7) {
                                e7.printStackTrace();
                            }
                        }
                    }
                    b.this.l.finishBroadcast();
                    break;
                case 100014:
                    String str = (String) message.obj;
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).endCall(str);
                        i++;
                    }
                    break;
                case 100015:
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).BlueSwitch(message.arg1);
                        i++;
                    }
                    break;
                case 100016:
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).NewTopTaskInfo((String) message.obj);
                        i++;
                    }
                    break;
                case 100017:
                    while (i < b.this.k.size()) {
                        ((c.a.a.b) b.this.k.get(i)).InvalidCallNumber((String) message.obj);
                        i++;
                    }
                    break;
            }
        }
    }

    public b() {
        Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_END;
        this.n = sync$SyncState;
        this.q = 0;
        this.r = "";
        this.s = -1;
        this.t = "";
        this.u = "";
        this.v = "";
        this.w = "";
        this.x = 0;
        this.y = 0;
        this.z = StateConstant$SCO_STATE.CHANNEL_BT;
        this.A = 100001;
        this.B = 100002;
        this.C = 100003;
        this.D = 100004;
        this.E = 100005;
        this.F = 100006;
        this.G = 100007;
        this.H = 100008;
        this.I = 100009;
        this.J = 100010;
        this.K = 100011;
        this.L = 100012;
        this.M = 100013;
        this.N = 100014;
        this.O = 100015;
        this.P = 100016;
        this.Q = 100017;
        this.R = BYDAutoStatisticDevice.MILEAGE_MAX;
        this.S = 1;
        this.T = 2;
        this.U = 359999;
        this.V = -1;
        this.W = false;
        this.Y = sync$SyncState;
        this.Z = new a();
        this.a0 = new HandlerC0067b(Looper.myLooper());
        this.b0 = -1;
        this.c0 = false;
        this.d0 = new ArrayList();
        this.e0 = "vendor.ts.battery.mac";
        this.f0 = "";
    }

    private void D0() {
        boolean z;
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                if (this.g.get(i).c() == 2) {
                    this.o = this.g.get(i).f();
                    this.q = 2;
                    if (this.a0.hasMessages(100003)) {
                        this.a0.removeMessages(100003);
                    }
                    this.a0.sendEmptyMessageDelayed(100003, 100L);
                    z = true;
                } else {
                    this.g.get(i).i(-1);
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            this.q = 0;
            this.p = this.o;
            this.o = null;
        }
        J0();
    }

    private void J0() {
        this.a0.sendEmptyMessage(100004);
    }

    private void M(String str, int i, int i2, String str2, boolean z) {
        int i3 = 0;
        if (j0(str) != -1) {
            while (true) {
                if (i3 >= this.h.size()) {
                    break;
                }
                if (str.equals(this.h.get(i3).m())) {
                    this.h.get(i3).v(i2);
                    break;
                }
                i3++;
            }
        } else {
            for (int i4 = 0; i4 < this.h.size(); i4++) {
                if (this.h.get(i4).p()) {
                    this.h.remove(i4);
                }
            }
            if (this.h.size() > 2) {
                return;
            }
            BtCallInfo btCallInfo = new BtCallInfo();
            int i5 = 1;
            while (i3 < this.h.size()) {
                if (this.h.get(i3).k() == 1) {
                    i5 = 2;
                } else if (this.h.get(i3).k() == 2) {
                    i5 = 1;
                }
                i3++;
            }
            btCallInfo.r(i5);
            btCallInfo.t(a0(str));
            btCallInfo.u(str);
            btCallInfo.w(i);
            btCallInfo.v(i2);
            btCallInfo.s(z);
            btCallInfo.q(str2);
            this.h.add(btCallInfo);
        }
        this.a0.sendEmptyMessage(100005);
    }

    private void P0(BluetoothDevice bluetoothDevice, int i) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (this.g.get(i2).f().getAddress().equals(bluetoothDevice.getAddress())) {
                this.g.get(i2).j(i);
                return;
            }
        }
    }

    public void Q(int i) {
        int iI0 = i0(i);
        if (iI0 == -1) {
            return;
        }
        if (this.h.size() <= 0) {
            this.h.clear();
            this.b0 = -1;
            this.f0 = "";
            this.a0.removeMessages(100001);
            this.a0.removeMessages(100008);
            return;
        }
        if (i == 1) {
            this.a0.removeMessages(100001);
            this.a0.sendEmptyMessageDelayed(100001, 1000L);
        } else if (i == 2) {
            this.a0.removeMessages(100008);
            this.a0.sendEmptyMessageDelayed(100008, 1000L);
        }
        String strM = this.h.get(iI0).m();
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if (strM.equals(this.h.get(i2).m())) {
                int iN = this.h.get(i2).n();
                if (iN == 0) {
                    int iO = this.h.get(iI0).o();
                    if (359999 == iO) {
                        iO = 0;
                    }
                    BtCallInfo btCallInfo = this.h.get(iI0);
                    this.i = btCallInfo;
                    btCallInfo.w(iO + 1000);
                    for (int i3 = 0; i3 < this.k.size(); i3++) {
                        this.k.get(i3).Update_Call_Time(this.i);
                    }
                    int iBeginBroadcast = this.l.beginBroadcast();
                    this.m = iBeginBroadcast;
                    if (iBeginBroadcast > 0) {
                        for (int i4 = 0; i4 < this.m; i4++) {
                            try {
                                ((com.byd.bluetoothservice.a) this.l.getBroadcastItem(i4)).Update_Call_Time(this.i);
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    this.l.finishBroadcast();
                } else if (iN == 1) {
                    int iO2 = this.h.get(iI0).o();
                    if (359999 == iO2) {
                        iO2 = 0;
                    }
                    this.h.get(iI0).w(iO2 + 1000);
                    if (this.h.size() == 1) {
                        for (int i5 = 0; i5 < this.k.size(); i5++) {
                            this.k.get(i5).Update_Call_Time(this.i);
                        }
                        int iBeginBroadcast2 = this.l.beginBroadcast();
                        this.m = iBeginBroadcast2;
                        if (iBeginBroadcast2 > 0) {
                            for (int i6 = 0; i6 < this.m; i6++) {
                                try {
                                    ((com.byd.bluetoothservice.a) this.l.getBroadcastItem(i6)).Update_Call_Time(this.i);
                                } catch (RemoteException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        this.l.finishBroadcast();
                    }
                } else if (iN == 7) {
                    this.h.get(iI0).w(0);
                }
            }
        }
    }

    private void R() {
        this.V = -1;
        CopyOnWriteArrayList<BtCallInfo> copyOnWriteArrayList = this.h;
        if (copyOnWriteArrayList != null) {
            this.b0 = -1;
            copyOnWriteArrayList.clear();
            this.f0 = "";
            this.a0.sendEmptyMessage(100005);
        }
        this.n = Sync$SyncState.SYNC_END;
    }

    private void U(BluetoothDevice bluetoothDevice) {
        this.o = bluetoothDevice;
        this.p = null;
        this.q = 2;
        g(bluetoothDevice, false);
        if (this.a0.hasMessages(100003)) {
            this.a0.removeMessages(100003);
        }
        this.a0.sendEmptyMessageDelayed(100003, 100L);
        J0();
    }

    private List<BluetoothHeadsetClientCall> d0() {
        ArrayList arrayList = new ArrayList();
        BydLogHelper.logD("getHeadsetClientCallList = ", " = mHeadsetClient = " + this.f);
        return (this.f == null || e0() == null) ? arrayList : this.f.getCurrentCalls(e0());
    }

    public void f() {
        Log.d("DANDAN", "InitConnect == " + this.g.size());
        int i = 0;
        while (true) {
            if (i >= this.g.size()) {
                break;
            }
            if (this.g.get(i).c() == 2) {
                if (this.q == 0 && this.o == null) {
                    this.q = 2;
                    this.o = this.g.get(i).f();
                }
                Log.d("DANDAN", "mConnectDevice == " + this.o.getName());
            } else {
                i++;
            }
        }
        this.a0.sendEmptyMessage(100004);
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (c.a.b.a.K().a(this.g.get(i2).f())) {
                c.a.b.a.K().h(this.g.get(i2).f(), 2);
                return;
            }
        }
    }

    public static b f0() {
        if (f2601a == null) {
            synchronized (b.class) {
                if (f2601a == null) {
                    f2601a = new b();
                }
            }
        }
        return f2601a;
    }

    public void g(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice != null) {
            Log.d("BTService", "InitaCallState = 1111");
            BluetoothHeadsetClient bluetoothHeadsetClient = this.f;
            if (bluetoothHeadsetClient != null) {
                List currentCalls = bluetoothHeadsetClient.getCurrentCalls(bluetoothDevice);
                if (currentCalls != null && currentCalls.size() > 0) {
                    BluetoothHeadsetClientCall bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall) this.f.getCurrentCalls(bluetoothDevice).get(0);
                    if (bluetoothHeadsetClientCall != null) {
                        o(bluetoothHeadsetClientCall.getState(), bluetoothHeadsetClientCall.getNumber(), true);
                        return;
                    }
                    return;
                }
                if (z) {
                    if (c.t().y(null) == null) {
                        c.t().a();
                    } else if (c.t().y(null).size() <= 0) {
                        c.t().a();
                    }
                }
            }
        }
    }

    private String g0(String str) {
        return str.replaceAll("[^+0-9]", "");
    }

    public int h0(Sync$SyncState sync$SyncState) {
        if (sync$SyncState == Sync$SyncState.SYNC_START) {
            return 1;
        }
        if (sync$SyncState == Sync$SyncState.SYNC_ING) {
            return 2;
        }
        if (sync$SyncState == Sync$SyncState.SYNC_FAILD) {
            return 3;
        }
        return sync$SyncState == Sync$SyncState.SYNC_SUCCEED ? 4 : 0;
    }

    private int i0(int i) {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if (this.h.get(i2).k() == i) {
                return i2;
            }
        }
        return -1;
    }

    public int j(StateConstant$SCO_STATE stateConstant$SCO_STATE) {
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_BT) {
            return 2;
        }
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_SWITCHING) {
            return 1;
        }
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_PHONE) {
        }
        return 0;
    }

    private int j0(String str) {
        for (int i = 0; i < this.h.size(); i++) {
            if (this.h.get(i).m().length() == str.length()) {
                if (this.h.get(i).m().equals(str)) {
                    return i;
                }
            } else if (this.h.get(i).m().length() > str.length()) {
                if (str.length() >= 8 && this.h.get(i).m().contains(str)) {
                    return i;
                }
            } else if (this.h.get(i).m().length() >= 8 && str.contains(this.h.get(i).m())) {
                return i;
            }
        }
        return -1;
    }

    private String k0() {
        return Long.toString(System.currentTimeMillis());
    }

    private void s0(BluetoothDevice bluetoothDevice, int i) {
        if (i == 2 || !bluetoothDevice.getAddress().equals(this.o.getAddress())) {
            return;
        }
        R();
        D0();
    }

    private void t0(BluetoothDevice bluetoothDevice, int i) {
        BluetoothDevice bluetoothDevice2 = this.o;
        if (bluetoothDevice2 != null && this.q == 2) {
            s0(bluetoothDevice, i);
        } else {
            if (bluetoothDevice2 != null || this.q == 2) {
                return;
            }
            u0(bluetoothDevice, i);
        }
    }

    private void u0(BluetoothDevice bluetoothDevice, int i) {
        if (i == 2) {
            U(bluetoothDevice);
        }
    }

    public void A0(Context context) {
        this.f2603c = context;
        this.f2605e = BluetoothAdapter.getDefaultAdapter();
        if (this.f2604d == null && Build.VERSION.SDK_INT >= 18) {
            this.f2604d = (BluetoothManager) context.getSystemService("bluetooth");
        }
        this.f2605e.getProfileProxy(this.f2603c, this.Z, 16);
        this.r = X();
        this.X = (TelecomManager) context.getSystemService(TelecomManager.class);
    }

    public boolean B0() {
        List<BluetoothHeadsetClientCall> listD0 = d0();
        Log.d("BYD_BlueToothService", "isCalling = = calls = " + listD0);
        if (listD0 == null) {
            return false;
        }
        Log.d("BYD_BlueToothService", "isCalling = = calls = " + listD0.size());
        return listD0.size() > 0;
    }

    public boolean C0(BluetoothDevice bluetoothDevice) {
        BluetoothHeadsetClient bluetoothHeadsetClient;
        return (bluetoothDevice == null || (bluetoothHeadsetClient = this.f) == null || bluetoothHeadsetClient.getConnectionState(bluetoothDevice) != 2) ? false : true;
    }

    public void E0(c.a.a.b bVar) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.k.size()) {
                break;
            }
            if (this.k.get(i) == bVar) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.k.add(bVar);
    }

    public void F0(c.a.a.c cVar) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.j.size()) {
                break;
            }
            if (this.j.get(i) == cVar) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.j.add(cVar);
    }

    public void G0() {
        if (this.f == null || e0() == null) {
            return;
        }
        this.f.rejectCall(e0());
    }

    public void H0(com.byd.bluetoothservice.a aVar) {
        this.l.unregister(aVar);
    }

    public void I0() {
        if (this.f2603c != null) {
            Intent intent = new Intent();
            intent.setAction("com.byd.pbapclient.update.data");
            this.f2603c.sendBroadcast(intent);
            Log.d(this.f2602b, "sendBroadCast2YY ===== ");
        }
    }

    public boolean K0(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.f2605e;
        if (bluetoothAdapter == null) {
            return false;
        }
        return z ? bluetoothAdapter.enable() : bluetoothAdapter.disable();
    }

    public void L0(boolean z) {
        Message messageObtainMessage = this.a0.obtainMessage();
        messageObtainMessage.what = 100012;
        messageObtainMessage.obj = Boolean.valueOf(z);
        this.a0.sendMessage(messageObtainMessage);
    }

    public void M0(Sync$SyncState sync$SyncState) {
        this.Y = sync$SyncState;
    }

    public void N(com.byd.bluetoothservice.a aVar) {
        RemoteCallbackList<com.byd.bluetoothservice.a> remoteCallbackList = this.l;
        if (remoteCallbackList == null) {
            BydLogHelper.logD(this.f2602b, "mRemoteCallbackList==null");
        } else {
            remoteCallbackList.register(aVar);
        }
    }

    public void N0(c.a.a.b bVar) {
        for (int i = 0; i < this.k.size(); i++) {
            if (this.k.get(i) == bVar) {
                this.k.remove(i);
                return;
            }
        }
    }

    public void O() {
        if (this.f == null || e0() == null) {
            return;
        }
        this.f.acceptCall(e0(), 0);
    }

    public void O0(c.a.a.c cVar) {
        for (int i = 0; i < this.j.size(); i++) {
            if (this.j.get(i) == cVar) {
                this.j.remove(i);
                return;
            }
        }
    }

    public void P() {
        if (this.f == null || e0() == null) {
            return;
        }
        this.f.acceptCall(e0(), 1);
    }

    public void Q0(String str) {
        this.f0 = str;
    }

    public void S() {
        this.p = null;
    }

    public boolean T(String str) {
        if (this.f == null) {
            BydLogHelper.logD(this.f2602b, "compareLocalNumber hfp client is null");
            return false;
        }
        if ("10000000".equals(str)) {
            BydLogHelper.logD(this.f2602b, "Same with Vivo we chat number without sim card");
            return true;
        }
        if ("10000001".equals(str)) {
            BydLogHelper.logD(this.f2602b, "Same with Vivo we chat number without sim card");
            return true;
        }
        if ("00000000000".equals(str)) {
            BydLogHelper.logD(this.f2602b, "Same with HuaWei P30 we chat number");
            return true;
        }
        Bundle currentAgEvents = this.f.getCurrentAgEvents(this.o);
        if (currentAgEvents != null) {
            for (String str2 : currentAgEvents.keySet()) {
                if (str2.equals("android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO")) {
                    String string = currentAgEvents.getString(str2);
                    BydLogHelper.logD(this.f2602b, "local number is : " + c.a.e.c.b(string));
                    BydLogHelper.logD(this.f2602b, "mLoacalNumber is : " + c.a.e.c.b(this.f0));
                    if (!TextUtils.isEmpty(string)) {
                        if (!str.equals(string)) {
                            if (!str.equals("+" + string) && (str.length() < 11 || string.length() < 11 || (!str.endsWith(string) && !string.endsWith(str)))) {
                            }
                        }
                        return true;
                    }
                    if (!TextUtils.isEmpty(this.f0)) {
                        if (!str.equals(this.f0)) {
                            if (!str.equals("+" + this.f0) && (str.length() < 11 || this.f0.length() < 11 || (!str.endsWith(this.f0) && !this.f0.endsWith(str)))) {
                            }
                        }
                        return true;
                    }
                    continue;
                }
            }
        }
        return false;
    }

    public int V() {
        Log.d("DANDAN", "getBluetoothDeviceBattery = mBatLevel = " + this.V);
        if (this.V == -1) {
            String strA = c.a.e.b.a("vendor.ts.battery.mac", "");
            if (!TextUtils.isEmpty(strA)) {
                String[] strArrSplit = strA.split("_");
                if (strArrSplit.length > 0) {
                    String[] strArrSplit2 = strArrSplit[0].split("\\s+");
                    if (strArrSplit2.length >= 2) {
                        this.V = Integer.parseInt(strArrSplit2[1]);
                        Log.d("DANDAN", "getBluetoothDeviceBattery = battery_and_mac = " + this.V);
                    }
                }
            }
        }
        return this.V;
    }

    public boolean W() {
        BluetoothAdapter bluetoothAdapter = this.f2605e;
        if (bluetoothAdapter == null) {
            return false;
        }
        return bluetoothAdapter.isEnabled();
    }

    public String X() {
        BluetoothAdapter bluetoothAdapter = this.f2605e;
        return bluetoothAdapter == null ? this.r : bluetoothAdapter.getName();
    }

    public List<BluetoothDeviceInfo> Y() {
        ArrayList arrayList = new ArrayList();
        try {
            Set<BluetoothDevice> bondedDevices = this.f2605e.getBondedDevices();
            if (bondedDevices != null && bondedDevices.size() > 0) {
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    arrayList.add(new BluetoothDeviceInfo(bluetoothDevice, C0(bluetoothDevice) ? 2 : 0, -1));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    public String Z(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String strB = c.a.e.b.b(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        List<ContactsInfo> listD = c.t().d(str);
        if (listD == null || listD.size() == 0) {
            List<CallLogInfo> listC = c.t().c(str);
            if (listC != null && listC.size() != 0) {
                for (int i = 0; i < listC.size(); i++) {
                    if (str.equals(listC.get(i).o())) {
                        return listC.get(i).n();
                    }
                }
            } else if (str.length() > 5 && str.startsWith("+")) {
                if (!TextUtils.isEmpty(strB) && !strB.equals("unknown")) {
                    List<ContactsInfo> listV = c.t().v(str.substring(("+" + strB).length()));
                    if (listV != null && listV.size() != 0) {
                        for (int i2 = 0; i2 < listV.size(); i2++) {
                            for (String str2 : listV.get(i2).k().split("and")) {
                                if (str.endsWith(str2)) {
                                    return listV.get(i2).j();
                                }
                            }
                        }
                    }
                }
                return str;
            }
        } else {
            for (int i3 = 0; i3 < listD.size(); i3++) {
                String[] strArrSplit = listD.get(i3).k().split("and");
                for (int i4 = 0; i4 < strArrSplit.length; i4++) {
                    if (str.equals(strArrSplit[i4])) {
                        return listD.get(i3).j();
                    }
                    if (TextUtils.isEmpty(strB) || strB.equals("unknown")) {
                        return str;
                    }
                    String str3 = "+" + strB;
                    if (strArrSplit[i4].startsWith(str3)) {
                        if (strArrSplit[i4].substring(str3.length()).equals(str)) {
                            return listD.get(i3).j();
                        }
                    }
                }
            }
        }
        return "";
    }

    public void a() {
        this.a0.sendEmptyMessage(100011);
    }

    public String a0(String str) {
        String strB = c.a.e.b.b(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        List<ContactsInfo> listD = c.t().d(str);
        int i = 0;
        if (listD == null || listD.size() == 0) {
            List<CallLogInfo> listC = c.t().c(str);
            if (listC != null && listC.size() != 0) {
                while (true) {
                    if (i >= listC.size()) {
                        break;
                    }
                    if (!str.equals(listC.get(i).o())) {
                        i++;
                    } else if (!TextUtils.isEmpty(listC.get(i).n())) {
                        return listC.get(i).n();
                    }
                }
            } else if (str.length() > 5 && str.startsWith("+") && !TextUtils.isEmpty(strB) && !strB.equals("unknown")) {
                List<ContactsInfo> listV = c.t().v(str.substring(("+" + strB).length()));
                if (listV != null && listV.size() != 0) {
                    for (int i2 = 0; i2 < listV.size(); i2++) {
                        for (String str2 : listV.get(i2).k().split("and")) {
                            if (str.endsWith(str2)) {
                                return listV.get(i2).j();
                            }
                        }
                    }
                }
            }
        } else {
            for (int i3 = 0; i3 < listD.size(); i3++) {
                String[] strArrSplit = listD.get(i3).k().split("and");
                for (int i4 = 0; i4 < strArrSplit.length; i4++) {
                    if (str.equals(strArrSplit[i4])) {
                        return listD.get(i3).j();
                    }
                    if (TextUtils.isEmpty(strB) || strB.equals("unknown")) {
                        return str;
                    }
                    String str3 = "+" + strB;
                    if (strArrSplit[i4].startsWith(str3)) {
                        if (strArrSplit[i4].substring(str3.length()).equals(str)) {
                            return listD.get(i3).j();
                        }
                    }
                }
            }
        }
        return str;
    }

    public void b(BluetoothDevice bluetoothDevice, int i) {
        if (this.g != null) {
            P0(bluetoothDevice, i);
        }
        t0(bluetoothDevice, i);
    }

    public StateConstant$SCO_STATE b0() {
        if (this.f != null && e0() != null) {
            try {
                return this.f.getAudioState(e0()) == 2 ? StateConstant$SCO_STATE.CHANNEL_BT : this.f.getAudioState(e0()) == 1 ? StateConstant$SCO_STATE.CHANNEL_SWITCHING : StateConstant$SCO_STATE.CHANNEL_PHONE;
            } catch (Exception e2) {
                Log.d(this.f2602b, "e = " + e2.toString());
            }
        }
        return StateConstant$SCO_STATE.CHANNEL_PHONE;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains("*") || str.contains("#")) {
            Message messageObtainMessage = this.a0.obtainMessage();
            messageObtainMessage.obj = str;
            messageObtainMessage.what = 100017;
            this.a0.sendMessage(messageObtainMessage);
            return;
        }
        if (this.f == null || j0(g0(str)) != -1) {
            return;
        }
        this.f.dial(e0(), g0(str));
    }

    public BluetoothDevice c0() {
        return c.a.b.a.K().L();
    }

    public boolean d() {
        return c.a.b.a.K().L() != null;
    }

    public boolean e() {
        return e0() != null;
    }

    public BluetoothDevice e0() {
        return this.o;
    }

    public void h(String str) {
        Message messageObtainMessage = this.a0.obtainMessage();
        messageObtainMessage.obj = str;
        messageObtainMessage.what = 100016;
        this.a0.sendMessage(messageObtainMessage);
    }

    public void i(BluetoothDevice bluetoothDevice, int i) {
        boolean z;
        if (bluetoothDevice == null) {
            return;
        }
        if (i != 12) {
            if (i == 10) {
                c.t().s(bluetoothDevice.getAddress());
                return;
            }
            return;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.g.size()) {
                z = false;
                break;
            } else {
                if (bluetoothDevice.getAddress().equals(this.g.get(i2).f().getAddress())) {
                    z = true;
                    break;
                }
                i2++;
            }
        }
        if (z) {
            return;
        }
        this.g.add(new BluetoothDeviceInfo(bluetoothDevice, C0(bluetoothDevice) ? 2 : 0, -1));
    }

    public void k(String str) {
        if (TextUtils.isEmpty(str) || this.f == null || e0() == null) {
            return;
        }
        this.f.sendDTMF(e0(), str.getBytes()[0]);
    }

    public boolean l(StateConstant$SCO_STATE stateConstant$SCO_STATE) {
        try {
            Log.d(this.f2602b, "Switch_channel111 == " + stateConstant$SCO_STATE);
            if (this.f == null || e0() == null) {
                return false;
            }
            Log.d(this.f2602b, "Switch_channel == " + stateConstant$SCO_STATE);
            if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_PHONE) {
                return this.f.disconnectAudio(e0());
            }
            if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_BT) {
                return this.f.connectAudio(e0());
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public List<BtCallInfo> l0() {
        return this.h;
    }

    public void m(int i, BluetoothDevice bluetoothDevice) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.g.size()) {
                break;
            }
            if (this.g.get(i2).f().getAddress().equals(bluetoothDevice.getAddress())) {
                this.g.get(i2).i(i);
                break;
            }
            i2++;
        }
        BluetoothDevice bluetoothDevice2 = this.o;
        if (bluetoothDevice2 == null || bluetoothDevice == null || !bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
            return;
        }
        Message messageObtainMessage = this.a0.obtainMessage();
        messageObtainMessage.what = 100007;
        this.V = i;
        messageObtainMessage.obj = bluetoothDevice;
        this.a0.sendMessage(messageObtainMessage);
        c.a.b.a.K().i(bluetoothDevice, i);
    }

    public String m0() {
        return this.t;
    }

    public void n(int i) {
        if (i != 12) {
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                this.g.get(i2).j(0);
                this.g.get(i2).i(-1);
            }
            CopyOnWriteArrayList<BtCallInfo> copyOnWriteArrayList = this.h;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
                this.b0 = -1;
                this.f0 = "";
                this.a0.sendEmptyMessage(100005);
            }
            this.q = 0;
            this.p = this.o;
            this.o = null;
            c.a.b.a.K().b();
            this.a0.sendEmptyMessage(100004);
        }
    }

    public int n0() {
        return this.q;
    }

    public void o(int i, String str, boolean z) {
        boolean z2;
        boolean z3;
        if (str.equals("null_number")) {
            if (i != 0 && i != 7) {
                this.b0 = i;
            } else if (i == 7) {
                this.b0 = -1;
            }
            if (i == 0 && this.b0 == -1) {
                this.c0 = true;
            } else {
                this.c0 = false;
            }
        } else if (T(str)) {
            this.c0 = true;
        } else {
            this.c0 = false;
        }
        if (!TextUtils.isEmpty(str)) {
            Log.d("ZXCVBN", "isVIOP == " + this.c0);
            if (!this.c0) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.d0.size()) {
                        z3 = false;
                        break;
                    } else {
                        if (str.equals(this.d0.get(i2).o())) {
                            z3 = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (!z3 && this.o != null) {
                    CallLogInfo callLogInfo = new CallLogInfo();
                    if (str.equals("null_number")) {
                        callLogInfo.x("");
                    } else {
                        callLogInfo.x(str);
                    }
                    callLogInfo.v(this.o.getAddress());
                    this.d0.add(callLogInfo);
                }
            }
        }
        String str2 = str.equals("null_number") ? "" : str;
        if (i != 0) {
            if (i == 1) {
                M(str2, 0, i, j0(str2) == -1 ? k0() : null, this.c0);
            } else if (i == 2 || i == 3) {
                M(str2, 0, i, j0(str2) == -1 ? k0() : null, this.c0);
                int i3 = 0;
                while (true) {
                    if (i3 >= this.d0.size()) {
                        break;
                    }
                    if (str2.equals(this.d0.get(i3).o())) {
                        this.d0.get(i3).z(System.currentTimeMillis());
                        this.d0.get(i3).A("DIALED");
                        break;
                    }
                    i3++;
                }
            } else if (i == 4 || i == 5) {
                M(str2, 0, i, j0(str2) == -1 ? k0() : null, this.c0);
                int i4 = 0;
                while (true) {
                    if (i4 >= this.d0.size()) {
                        break;
                    }
                    if (str2.equals(this.d0.get(i4).o())) {
                        this.d0.get(i4).z(System.currentTimeMillis());
                        this.d0.get(i4).A("MISSED");
                        break;
                    }
                    i4++;
                }
            } else if (i == 7) {
                this.c0 = false;
                int iJ0 = j0(str2);
                if (iJ0 != -1) {
                    if (this.h.get(iJ0).k() == 1) {
                        this.a0.removeMessages(100001);
                    } else if (this.h.get(iJ0).k() == 2) {
                        this.a0.removeMessages(100008);
                    }
                    this.h.remove(iJ0);
                    Message messageObtainMessage = this.a0.obtainMessage();
                    messageObtainMessage.what = 100014;
                    messageObtainMessage.obj = str2;
                    this.a0.sendMessage(messageObtainMessage);
                }
                this.a0.sendEmptyMessageDelayed(100005, 500L);
                Log.d("ZXCVBN", "2222222222 == " + this.d0.size());
                int i5 = 0;
                while (true) {
                    if (i5 >= this.d0.size()) {
                        break;
                    }
                    if (str2.equals(this.d0.get(i5).o())) {
                        Sync$SyncState sync$SyncState = this.Y;
                        if ((sync$SyncState == Sync$SyncState.SYNC_END || sync$SyncState == Sync$SyncState.SYNC_FAILD) && this.d0.get(i5).q() > 0) {
                            this.w = Z(this.d0.get(i5).o());
                            this.d0.get(i5).w(this.w);
                            c.t().f(this.d0.get(i5));
                        }
                        this.d0.remove(i5);
                    } else {
                        i5++;
                    }
                }
            }
            z2 = false;
        } else {
            M(str2, 0, i, j0(str2) == -1 ? k0() : null, this.c0);
            int iJ02 = j0(str2);
            if (iJ02 != -1) {
                if (this.h.get(iJ02).o() == 0 && this.h.get(iJ02).k() == 1) {
                    this.a0.removeMessages(100001);
                    this.a0.sendEmptyMessage(100001);
                } else if (this.h.get(iJ02).o() == 0 && this.h.get(iJ02).k() == 2) {
                    this.a0.removeMessages(100008);
                    this.a0.sendEmptyMessage(100008);
                }
                int i6 = 0;
                while (true) {
                    if (i6 >= this.d0.size()) {
                        break;
                    }
                    if (str2.equals(this.d0.get(i6).o())) {
                        if (this.d0.get(i6).r().equals("MISSED") || this.d0.get(i6).r().equals("unknown")) {
                            this.d0.get(i6).A("RECEIVED");
                        }
                        if (this.d0.get(i6).q() == 0) {
                            this.d0.get(i6).z(System.currentTimeMillis());
                        }
                    } else {
                        i6++;
                    }
                }
            }
            z2 = false;
        }
        this.c0 = z2;
    }

    public Sync$SyncState o0() {
        return this.n;
    }

    public void p(int i) {
        if (i == 2) {
            this.z = StateConstant$SCO_STATE.CHANNEL_BT;
        } else if (i == 1) {
            this.z = StateConstant$SCO_STATE.CHANNEL_SWITCHING;
        } else {
            this.z = StateConstant$SCO_STATE.CHANNEL_PHONE;
        }
        this.a0.sendEmptyMessage(100006);
    }

    public BluetoothDevice p0() {
        return this.p;
    }

    public void q(Sync$SyncState sync$SyncState) {
        this.n = sync$SyncState;
        this.a0.sendEmptyMessage(100013);
    }

    public List<BluetoothDeviceInfo> q0() {
        return this.g;
    }

    public void r(boolean z) {
        this.W = z;
        this.a0.sendEmptyMessage(100010);
    }

    public RemoteCallbackList<com.byd.bluetoothservice.a> r0() {
        return this.l;
    }

    public void v0() {
        w0(e0());
    }

    public void w0(BluetoothDevice bluetoothDevice) {
        BluetoothHeadsetClient bluetoothHeadsetClient = this.f;
        if (bluetoothHeadsetClient == null || bluetoothDevice == null) {
            return;
        }
        if (bluetoothHeadsetClient.getCurrentCalls(bluetoothDevice) == null || this.f.getCurrentCalls(bluetoothDevice).size() <= 0) {
            o(7, m0(), false);
        } else {
            BluetoothHeadsetClient bluetoothHeadsetClient2 = this.f;
            bluetoothHeadsetClient2.terminateCall(bluetoothDevice, (BluetoothHeadsetClientCall) bluetoothHeadsetClient2.getCurrentCalls(bluetoothDevice).get(0));
        }
    }

    public void x0(BluetoothDevice bluetoothDevice, String str) {
        BluetoothHeadsetClient bluetoothHeadsetClient = this.f;
        if (bluetoothHeadsetClient == null || bluetoothDevice == null) {
            return;
        }
        if (bluetoothHeadsetClient.getCurrentCalls(bluetoothDevice) == null || this.f.getCurrentCalls(bluetoothDevice).size() <= 0) {
            o(7, m0(), false);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            BluetoothHeadsetClient bluetoothHeadsetClient2 = this.f;
            bluetoothHeadsetClient2.terminateCall(bluetoothDevice, (BluetoothHeadsetClientCall) bluetoothHeadsetClient2.getCurrentCalls(bluetoothDevice).get(0));
            return;
        }
        List currentCalls = this.f.getCurrentCalls(bluetoothDevice);
        if (currentCalls != null) {
            for (int i = 0; i < currentCalls.size(); i++) {
                if (((BluetoothHeadsetClientCall) currentCalls.get(i)).getNumber().equals(str)) {
                    BluetoothHeadsetClient bluetoothHeadsetClient3 = this.f;
                    bluetoothHeadsetClient3.terminateCall(bluetoothDevice, (BluetoothHeadsetClientCall) bluetoothHeadsetClient3.getCurrentCalls(bluetoothDevice).get(i));
                    return;
                }
            }
        }
    }

    public void y0(String str) {
        x0(e0(), str);
    }

    public void z0() {
        if (this.f == null || e0() == null) {
            return;
        }
        this.f.holdCall(e0());
    }
}
