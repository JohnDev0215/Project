package com.byd.bluetoothcall.c;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.UiModeManager;
import android.app.WindowConfiguration;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import c.a.c.a;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.byd.constant.StateConstant$SCO_STATE;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import com.bydux.api.common.tools.py.BydPyHelper;
import com.bydux.api.common.tools.py.BydT9Helper;
import com.bydux.api.common.tools.search.BydMatchHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.data.CallStateInfo;

/* JADX INFO: compiled from: Manage.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f3246a;
    private boolean A;
    private int B;
    private int C;
    private boolean D;
    private boolean E;
    private boolean F;
    private c.a.c.a G;
    private boolean H;
    private String I;
    private int J;
    private InputMethodManager K;
    public boolean L;
    private g M;
    private h N;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3247b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ICarPlayAppManager f3248c;
    private String u;
    private Sync$SyncState x;
    private Sync$SyncState y;
    private String z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3249d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3250e = false;
    private volatile List<CallStateInfo> f = new ArrayList();
    private int g = 0;
    private boolean h = true;
    private boolean i = false;
    private CopyOnWriteArrayList<com.byd.bluetoothcall.b.d> j = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<com.byd.bluetoothcall.b.b> k = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<com.byd.bluetoothcall.b.a> l = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ContactsInfo> m = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<CallLogInfo> n = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<CallLogInfo> o = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<CallLogInfo> p = new CopyOnWriteArrayList<>();
    private int q = 0;
    private boolean r = false;
    private int s = 0;
    private int t = 0;
    private String v = "";
    private boolean w = false;

    /* JADX INFO: renamed from: com.byd.bluetoothcall.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Manage.java */
    class C0078a implements a.c {
        C0078a() {
        }

        @Override // c.a.c.a.c
        public void a(boolean z) {
            Log.d("DANDAN", "----onServiceConnectChanged----connected: " + z);
        }
    }

    /* JADX INFO: compiled from: Manage.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.h();
            a.this.g(true);
        }
    }

    /* JADX INFO: compiled from: Manage.java */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.M != null) {
                a.this.M.d();
            }
        }
    }

    /* JADX INFO: compiled from: Manage.java */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.M != null) {
                a.this.M.h();
            }
        }
    }

    /* JADX INFO: compiled from: Manage.java */
    class e implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3255b;

        /* JADX INFO: renamed from: com.byd.bluetoothcall.c.a$e$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Manage.java */
        class RunnableC0079a implements Runnable {
            RunnableC0079a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (a.this.N != null) {
                    h hVar = a.this.N;
                    e eVar = e.this;
                    hVar.a(eVar.f3255b, a.this.j);
                }
            }
        }

        e(String str) {
            this.f3255b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String strMatchT9;
            a.this.j.clear();
            if (a.this.l.size() > 0) {
                a.this.L = true;
                for (int i = 0; i < a.this.l.size(); i++) {
                    a aVar = a.this;
                    if (!aVar.L) {
                        return;
                    }
                    if (!TextUtils.isEmpty(aVar.l.get(i).a()) && (strMatchT9 = BydMatchHelper.matchT9(this.f3255b, a.this.l.get(i).c())) != null) {
                        com.byd.bluetoothcall.b.d dVar = new com.byd.bluetoothcall.b.d();
                        dVar.j(a.this.l.get(i).a());
                        dVar.k(a.this.l.get(i).b());
                        dVar.i(strMatchT9);
                        CallLogInfo callLogInfoB = c.a.b.c.t().b(dVar.g());
                        if (callLogInfoB == null) {
                            dVar.l(0L);
                        } else {
                            dVar.l(callLogInfoB.q());
                        }
                        a.this.q(this.f3255b, dVar);
                    }
                    String strMatch = BydMatchHelper.match(this.f3255b, a.this.l.get(i).b());
                    if (strMatch != null) {
                        com.byd.bluetoothcall.b.d dVar2 = new com.byd.bluetoothcall.b.d();
                        dVar2.j(a.this.l.get(i).a());
                        dVar2.k(a.this.l.get(i).b());
                        dVar2.i(strMatch);
                        CallLogInfo callLogInfoB2 = c.a.b.c.t().b(dVar2.g());
                        if (callLogInfoB2 == null) {
                            dVar2.l(0L);
                        } else {
                            dVar2.l(callLogInfoB2.q());
                        }
                        a.this.q(this.f3255b, dVar2);
                    }
                }
            }
            if (a.this.k.size() > 0) {
                a.this.L = true;
                for (int i2 = 0; i2 < a.this.k.size(); i2++) {
                    a aVar2 = a.this;
                    if (!aVar2.L) {
                        return;
                    }
                    String strMatchT92 = BydMatchHelper.matchT9(this.f3255b, aVar2.k.get(i2).m());
                    if (strMatchT92 != null) {
                        List<com.byd.bluetoothcall.b.c> listJ = a.this.k.get(i2).j();
                        for (int i3 = 0; i3 < listJ.size(); i3++) {
                            com.byd.bluetoothcall.b.d dVar3 = new com.byd.bluetoothcall.b.d();
                            dVar3.j(a.this.k.get(i2).i());
                            dVar3.k(listJ.get(i3).a());
                            dVar3.i(strMatchT92);
                            CallLogInfo callLogInfoB3 = c.a.b.c.t().b(listJ.get(i3).a());
                            if (callLogInfoB3 == null) {
                                dVar3.l(0L);
                            } else {
                                dVar3.l(callLogInfoB3.q());
                            }
                            a.this.q(this.f3255b, dVar3);
                        }
                    } else {
                        List<com.byd.bluetoothcall.b.c> listJ2 = a.this.k.get(i2).j();
                        for (int i4 = 0; i4 < listJ2.size(); i4++) {
                            String strMatch2 = BydMatchHelper.match(this.f3255b, listJ2.get(i4).a());
                            if (strMatch2 != null) {
                                com.byd.bluetoothcall.b.d dVar4 = new com.byd.bluetoothcall.b.d();
                                dVar4.j(a.this.k.get(i2).i());
                                dVar4.k(listJ2.get(i4).a());
                                dVar4.i(strMatch2);
                                CallLogInfo callLogInfoB4 = c.a.b.c.t().b(listJ2.get(i4).a());
                                if (callLogInfoB4 == null) {
                                    dVar4.l(0L);
                                } else {
                                    dVar4.l(callLogInfoB4.q());
                                }
                                a.this.q(this.f3255b, dVar4);
                            }
                        }
                    }
                }
            }
            if (a.this.j.size() == 0) {
                com.byd.bluetoothcall.e.a.f3272e.execute(new RunnableC0079a());
            }
        }
    }

    /* JADX INFO: compiled from: Manage.java */
    class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3258b;

        f(String str) {
            this.f3258b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.j.size() <= 0 || a.this.N == null) {
                return;
            }
            a.this.N.a(this.f3258b, a.this.j);
        }
    }

    /* JADX INFO: compiled from: Manage.java */
    public interface g {
        void a();

        void b();

        void c(boolean z);

        void d();

        void e();

        void f(int i);

        void g(int i, int i2);

        void h();

        void i(int i);
    }

    /* JADX INFO: compiled from: Manage.java */
    public interface h {
        void a(String str, List<com.byd.bluetoothcall.b.d> list);
    }

    public a() {
        Sync$SyncState sync$SyncState = Sync$SyncState.SYNC_END;
        this.x = sync$SyncState;
        this.y = sync$SyncState;
        this.z = "";
        this.A = false;
        this.B = -1;
        this.C = 0;
        this.D = false;
        this.E = false;
        this.F = false;
        this.H = false;
        this.I = "";
        this.J = -1;
        this.L = false;
    }

    public static a F() {
        if (f3246a == null) {
            synchronized (a.class) {
                if (f3246a == null) {
                    f3246a = new a();
                }
            }
        }
        return f3246a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(String str, com.byd.bluetoothcall.b.d dVar) {
        Iterator<com.byd.bluetoothcall.b.d> it = this.j.iterator();
        while (it.hasNext()) {
            if (it.next().equals(dVar)) {
                return;
            }
        }
        this.j.add(dVar);
        Collections.sort(this.j);
        com.byd.bluetoothcall.e.a.f3272e.execute(new f(str));
    }

    private List<com.byd.bluetoothcall.b.a> w(List<CallLogInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (CallLogInfo callLogInfo : list) {
            com.byd.bluetoothcall.b.a aVar = new com.byd.bluetoothcall.b.a();
            aVar.d(callLogInfo.n());
            aVar.e(callLogInfo.o());
            aVar.g(BydT9Helper.getT9Code(callLogInfo.n()));
            aVar.f(BydPyHelper.getContactPinyin(callLogInfo.n()));
            arrayList.add(aVar);
        }
        return arrayList;
    }

    public boolean A() {
        return this.D;
    }

    public void A0(boolean z) {
        this.A = z;
    }

    public List<CallLogInfo> B() {
        return g0(this.n);
    }

    public void B0(boolean z) {
        this.E = z;
    }

    public ICarPlayAppManager C() {
        return this.f3248c;
    }

    public void C0(int i, int i2) {
        this.C = i2;
    }

    public List<com.byd.bluetoothcall.b.b> D() {
        return this.k;
    }

    public void D0(int i) {
        this.B = i;
    }

    public String E() {
        return this.u;
    }

    public void E0(boolean z) {
        this.L = z;
    }

    public void F0(boolean z) {
        this.f3250e = z;
    }

    public boolean G() {
        return BYDAutoAudioDevice.getInstance(this.f3247b).getBtCall_MicMute() == 1;
    }

    public void G0(String str) {
        this.z = str;
    }

    public List<CallLogInfo> H() {
        return h0(this.p);
    }

    public void H0(String str) {
        this.v = str;
    }

    public int I() {
        return this.s;
    }

    public void I0(Sync$SyncState sync$SyncState) {
        this.y = sync$SyncState;
    }

    public boolean J() {
        ActivityManager.RunningTaskInfo runningTaskInfoM = M();
        if (runningTaskInfoM != null) {
            String packageName = runningTaskInfoM.baseActivity.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                int i = 0;
                while (true) {
                    String[] strArr = com.byd.bluetoothcall.f.c.f3274a;
                    if (i >= strArr.length) {
                        break;
                    }
                    if (packageName.equals(strArr[i])) {
                        return true;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    public void J0(int i) {
        g gVar = this.M;
        if (gVar != null) {
            gVar.f(i);
        }
    }

    public boolean K() {
        return (this.f3247b.getResources().getConfiguration().uiMode & 48) == 32 || ((UiModeManager) this.f3247b.getSystemService("uimode")).getNightMode() == 2;
    }

    public void K0(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return;
        }
        try {
            inputMethodManager.toggleSoftInput(2, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Sync$SyncState L() {
        return this.x;
    }

    public void L0() {
        if (this.M != null) {
            this.M = null;
        }
    }

    public ActivityManager.RunningTaskInfo M() {
        return N(3);
    }

    public void M0() {
        if (this.N != null) {
            this.N = null;
        }
    }

    public ActivityManager.RunningTaskInfo N(@WindowConfiguration.ActivityType int i) {
        try {
            List filteredTasks = ActivityTaskManager.getService().getFilteredTasks(1, false);
            if (filteredTasks.isEmpty()) {
                return null;
            }
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) filteredTasks.get(0);
            if (runningTaskInfo == null || runningTaskInfo.displayId == 0) {
                return (ActivityManager.RunningTaskInfo) filteredTasks.get(0);
            }
            Log.w("DANDAN", "getRunningTask tasks displayId not 0! info = " + runningTaskInfo);
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public int O() {
        return this.C;
    }

    public int P() {
        return this.B;
    }

    public List<CallStateInfo> Q() {
        return this.f;
    }

    public int R() {
        return this.t;
    }

    public String S() {
        return this.z;
    }

    public int T() {
        return this.q;
    }

    public String U() {
        return this.v;
    }

    public void V(Context context, View view) {
        InputMethodManager inputMethodManager;
        if (context == null || view == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null || view.getWindowToken() == null) {
            return;
        }
        try {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void W(Context context) {
        this.f3247b = context;
        this.K = (InputMethodManager) BlueToothApplication.a().getApplicationContext().getSystemService("input_method");
        this.G = c.a.c.a.l(context, new C0078a());
        x0(this.f3247b.getResources().getConfiguration().orientation);
        k(c.a.b.b.f0().n0() == 2);
    }

    public boolean X() {
        return this.H;
    }

    public boolean Y() {
        return this.F;
    }

    public boolean Z() {
        return this.i;
    }

    public void a() {
        StateConstant$SCO_STATE stateConstant$SCO_STATEB0 = c.a.b.b.f0().b0();
        StateConstant$SCO_STATE stateConstant$SCO_STATE = StateConstant$SCO_STATE.CHANNEL_PHONE;
        if (stateConstant$SCO_STATEB0 == stateConstant$SCO_STATE) {
            Log.d("DANDAN", "ChangeCallAudio = TO _BT == " + c.a.b.b.f0().l(StateConstant$SCO_STATE.CHANNEL_BT));
            return;
        }
        if (c.a.b.b.f0().b0() == StateConstant$SCO_STATE.CHANNEL_BT) {
            Log.d("DANDAN", "ChangeCallAudio = getCallMuteState() == " + BYDAutoAudioDevice.getInstance(this.f3247b).getCallMuteState());
            if (BYDAutoAudioDevice.getInstance(this.f3247b).getCallMuteState() != 1) {
                BYDAutoAudioDevice.getInstance(this.f3247b).setCallMuteState(1);
            }
            boolean zL = c.a.b.b.f0().l(stateConstant$SCO_STATE);
            Log.d("DANDAN", "ChangeCallAudio = TO _PHONE == " + zL);
            if (zL || BYDAutoAudioDevice.getInstance(this.f3247b).getCallMuteState() == 0) {
                return;
            }
            BYDAutoAudioDevice.getInstance(this.f3247b).setCallMuteState(0);
        }
    }

    public boolean a0() {
        return this.A;
    }

    public void b() {
        this.j.clear();
        h hVar = this.N;
        if (hVar != null) {
            hVar.a("", this.j);
        }
    }

    public boolean b0() {
        ActivityManager.RunningTaskInfo runningTaskInfoM = M();
        if (runningTaskInfoM == null) {
            return false;
        }
        int windowingMode = runningTaskInfoM.getConfiguration().windowConfiguration.getWindowingMode();
        return windowingMode == 3 || windowingMode == 4;
    }

    public void c() {
        g gVar = this.M;
        if (gVar != null) {
            gVar.b();
        }
    }

    public boolean c0() {
        return this.E;
    }

    public void d() {
        Log.d("DANDAN", "CloseAllDiaLog == " + this.G);
        c.a.c.a aVar = this.G;
        if (aVar != null) {
            aVar.j();
        }
    }

    public boolean d0() {
        ActivityManager.RunningTaskInfo runningTaskInfoM = M();
        if (runningTaskInfoM == null) {
            return false;
        }
        String packageName = runningTaskInfoM.baseActivity.getPackageName();
        Log.d("UFYSDFDF", "pkg ===== " + packageName);
        return !TextUtils.isEmpty(packageName) && packageName.equals("com.byd.bluetoothcall");
    }

    public void e() {
        PackageManager packageManager = this.f3247b.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        if (packageManager.queryIntentServices(intent, 0).isEmpty()) {
            return;
        }
        this.f3247b.startService(intent);
    }

    public boolean e0() {
        return this.f3250e;
    }

    public void f() {
        Log.d("DANDAN", "settingsApi == " + this.G);
        c.a.c.a aVar = this.G;
        if (aVar != null) {
            aVar.r();
        }
    }

    public boolean f0() {
        return this.h;
    }

    public void g(boolean z) {
        this.n.clear();
        List<CallLogInfo> listW = c.a.b.c.t().w(Sync$SyncType.ALL_OF_CALL_LOG);
        if (listW != null) {
            Log.d("DANDAN", "alllog_list =================== " + listW.size());
            this.n.addAll(listW);
            B();
        }
        this.p.clear();
        List<CallLogInfo> listW2 = c.a.b.c.t().w(Sync$SyncType.MISSED_LOG);
        if (listW2 != null) {
            this.p.addAll(listW2);
        }
        i(w(this.n), z);
    }

    public List<CallLogInfo> g0(List<CallLogInfo> list) {
        Log.d("DANDAN", "mergeCallLog =================== " + list.size());
        ArrayList<CallLogInfo> arrayList = new ArrayList();
        this.o.clear();
        if (list.size() > 0) {
            for (CallLogInfo callLogInfo : list) {
                boolean z = false;
                if (arrayList.size() > 0) {
                    for (CallLogInfo callLogInfo2 : arrayList) {
                        if (callLogInfo.equals(callLogInfo2)) {
                            callLogInfo2.s(callLogInfo2.j() + 1);
                            if (callLogInfo.q() > callLogInfo2.q()) {
                                callLogInfo2.z(callLogInfo.q());
                            }
                            z = true;
                        }
                    }
                }
                if (!z) {
                    CallLogInfo callLogInfo3 = new CallLogInfo();
                    callLogInfo3.t(callLogInfo.k());
                    callLogInfo3.w(callLogInfo.n());
                    callLogInfo3.x(callLogInfo.o());
                    callLogInfo3.A(callLogInfo.r());
                    callLogInfo3.y(callLogInfo.p());
                    callLogInfo3.v(callLogInfo.m());
                    callLogInfo3.u(callLogInfo.l());
                    callLogInfo3.z(callLogInfo.q());
                    callLogInfo3.s(callLogInfo.j());
                    arrayList.add(callLogInfo3);
                    this.o.add(callLogInfo3);
                }
            }
        }
        return arrayList;
    }

    public void h() {
        this.m.clear();
        List<ContactsInfo> listY = c.a.b.c.t().y(null);
        if (listY != null) {
            this.m.addAll(listY);
        }
        j(x(this.m));
    }

    public List<CallLogInfo> h0(List<CallLogInfo> list) {
        ArrayList<CallLogInfo> arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (CallLogInfo callLogInfo : list) {
                boolean z = false;
                if (arrayList.size() > 0) {
                    for (CallLogInfo callLogInfo2 : arrayList) {
                        if (callLogInfo.equals(callLogInfo2)) {
                            callLogInfo2.s(callLogInfo2.j() + 1);
                            z = true;
                        }
                    }
                }
                if (!z) {
                    CallLogInfo callLogInfo3 = new CallLogInfo();
                    callLogInfo3.t(callLogInfo.k());
                    callLogInfo3.w(callLogInfo.n());
                    callLogInfo3.x(callLogInfo.o());
                    callLogInfo3.A(callLogInfo.r());
                    callLogInfo3.y(callLogInfo.p());
                    callLogInfo3.v(callLogInfo.m());
                    callLogInfo3.u(callLogInfo.l());
                    callLogInfo3.z(callLogInfo.q());
                    callLogInfo3.s(callLogInfo.j());
                    arrayList.add(callLogInfo3);
                }
            }
        }
        return arrayList;
    }

    public void i(List<com.byd.bluetoothcall.b.a> list, boolean z) {
        this.l.clear();
        this.l.addAll(list);
        com.byd.bluetoothcall.e.a.f3272e.execute(new c());
    }

    public void i0(g gVar) {
        this.M = gVar;
    }

    public void j(List<com.byd.bluetoothcall.b.b> list) {
        this.k.clear();
        this.k.addAll(list);
        com.byd.bluetoothcall.e.a.f3272e.execute(new d());
    }

    public void j0(h hVar) {
        this.N = hVar;
    }

    public void k(boolean z) {
        if (z) {
            com.byd.bluetoothcall.e.a.g.execute(new b());
            return;
        }
        r();
        u0(0);
        l();
    }

    public void k0(String str) {
        this.L = true;
        com.byd.bluetoothcall.e.a.g.execute(new e(str));
    }

    public void l() {
        Log.d("DANDAN", "UpdateUnReadMissedCalls === " + this.M + " == " + I());
        g gVar = this.M;
        if (gVar != null) {
            gVar.i(I());
        }
    }

    public void l0(int i) {
        this.f3249d = i;
    }

    public void m0(boolean z) {
        this.H = z;
    }

    public void n0(boolean z) {
        if (this.f3247b == null) {
            return;
        }
        if (!z && TextUtils.isEmpty(c.a.e.b.a("ts.vendor.audioon", ""))) {
            c.a.e.b.c("ts.vendor.audioon", "audio_on");
        }
        this.D = z;
    }

    public void o0(ICarPlayAppManager iCarPlayAppManager) {
        this.f3248c = iCarPlayAppManager;
    }

    public void p0(boolean z) {
        this.h = z;
    }

    public void q0(int i) {
        this.t = i;
    }

    public void r() {
        this.l.clear();
        this.k.clear();
        this.n.clear();
        this.m.clear();
        this.p.clear();
    }

    public void r0(String str) {
        this.u = str;
    }

    public void s() {
        g gVar = this.M;
        if (gVar != null) {
            gVar.a();
        }
    }

    public void s0() {
        Log.d("DANDAN", "setMicMute == " + BYDAutoAudioDevice.getInstance(this.f3247b).getBtCall_MicMute());
        if (BYDAutoAudioDevice.getInstance(this.f3247b).getBtCall_MicMute() == 1) {
            BYDAutoAudioDevice.getInstance(this.f3247b).setBtCall_MicMute(0);
        } else {
            BYDAutoAudioDevice.getInstance(this.f3247b).setBtCall_MicMute(1);
        }
    }

    public void t() {
        g gVar = this.M;
        if (gVar != null) {
            gVar.e();
        }
    }

    public void t0(int i) {
        if (BYDAutoAudioDevice.getInstance(this.f3247b).getBtCall_MicMute() != i) {
            BYDAutoAudioDevice.getInstance(this.f3247b).setBtCall_MicMute(i);
        }
    }

    public void u(int i) {
        g gVar = this.M;
        if (gVar != null) {
            gVar.g(8, i);
        }
    }

    public void u0(int i) {
        this.s = i;
    }

    public void v(int i) {
        g gVar = this.M;
        if (gVar != null) {
            gVar.g(10, i);
        }
    }

    public void v0(boolean z) {
        this.F = z;
    }

    public void w0(boolean z) {
        this.r = z;
    }

    public List<com.byd.bluetoothcall.b.b> x(List<ContactsInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (ContactsInfo contactsInfo : list) {
            com.byd.bluetoothcall.b.b bVar = new com.byd.bluetoothcall.b.b();
            bVar.s(contactsInfo.j());
            bVar.q(contactsInfo.j());
            bVar.o(contactsInfo.a());
            bVar.n(contactsInfo.m().equals("Z#") ? "#" : contactsInfo.m());
            String[] strArrSplit = contactsInfo.k().split("and");
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < strArrSplit.length; i++) {
                com.byd.bluetoothcall.b.c cVar = new com.byd.bluetoothcall.b.c();
                cVar.c(strArrSplit[i]);
                cVar.b(com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), strArrSplit[i]));
                arrayList2.add(cVar);
            }
            bVar.t(arrayList2);
            bVar.u(contactsInfo.l());
            bVar.w(BydT9Helper.getT9Code(contactsInfo.j()));
            bVar.v(BydPyHelper.getContactPinyin(contactsInfo.j()));
            arrayList.add(bVar);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public void x0(int i) {
        this.q = i;
    }

    public int y() {
        return this.f3249d;
    }

    public void y0(Sync$SyncState sync$SyncState) {
        this.x = sync$SyncState;
    }

    public List<CallLogInfo> z() {
        return this.o;
    }

    public void z0(boolean z) {
        this.i = z;
        g gVar = this.M;
        if (gVar != null) {
            gVar.c(z);
        }
    }
}
