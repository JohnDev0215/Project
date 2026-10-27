package com.byd.uereport;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import b5.h;
import b5.j;
import com.byd.carsettings.db.model.EnergyBean;
import com.byd.smarttravel.PlanBean;
import com.byd.systemsettings.SettingsCallDispatcher;
import com.byd.trafficmonitor.model.TrafficInfo;
import f3.e;
import g5.g;
import java.util.LinkedHashMap;
import java.util.List;
import l3.b;
import q4.c;
import u4.b;

/* JADX INFO: loaded from: classes2.dex */
public class MultiModuleService extends Service {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public u4.d f3042i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public q4.a f3043j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f3.d f3044k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public l3.a f3045l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public n4.a f3046m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public u2.a f3047n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public k3.a f3048o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public SettingsCallDispatcher f3049p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public BYDAutoPowerDevice f3051r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f3052s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3053t;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3037d = "com.byd.traffic.service.action";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3038e = "com.byd.aidl";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f3039f = "com.byd.charge.service.action";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f3040g = "com.byd.screensaver.service.action";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3041h = "com.byd.digitalkey.action";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Handler f3050q = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public b.a f3054u = new a();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public c.a f3055v = new b();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public e.a f3056w = new c();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public b.a f3057x = new d();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AbsBYDAutoPowerListener f3058y = new e();

    public class a extends b.a {
        public a() {
        }

        @Override // u4.b
        public void S(int i8, int i9) throws RemoteException {
            MultiModuleService.this.f3042i.P(i8, i9);
        }

        @Override // u4.b
        public List<TrafficInfo> T() throws RemoteException {
            return MultiModuleService.this.f3042i.y();
        }
    }

    public class b extends c.a {
        public b() {
        }

        @Override // q4.c
        public void C(q4.b bVar) throws RemoteException {
            MultiModuleService.this.f3043j.n(null);
        }

        @Override // q4.c
        public void Y(int i8) throws RemoteException {
            if (i8 == 1) {
                r4.c.t(MultiModuleService.this.getApplicationContext(), "TYPE_ALARM_MAIN");
            }
        }

        @Override // q4.c
        public void m(q4.b bVar) throws RemoteException {
            MultiModuleService.this.f3043j.n(bVar);
        }

        @Override // q4.c
        public PlanBean m0() throws RemoteException {
            return r4.d.a(MultiModuleService.this.getApplicationContext());
        }

        @Override // q4.c
        public void x0(PlanBean planBean) throws Throwable {
            r4.d.d(MultiModuleService.this.getApplicationContext(), planBean);
        }
    }

    public class c extends e.a {
        public c() {
        }

        @Override // f3.e
        public List<EnergyBean> l0() throws RemoteException {
            return f3.c.i().l(1, 251, true);
        }

        @Override // f3.e
        public void n() throws RemoteException {
            f3.c.i().f();
        }

        @Override // f3.e
        public List<EnergyBean> y() throws RemoteException {
            return f3.c.i().l(2, 251, true);
        }
    }

    public class d extends b.a {
        public d() {
        }

        @Override // l3.b
        public void N(String str) throws RemoteException {
            g5.a.i("DigitalKey, remove key json:" + str);
            MultiModuleService.this.f3045l.d(str);
        }
    }

    public class e extends AbsBYDAutoPowerListener {
        public e() {
        }

        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerLevelChanged(int i8) {
            super.onPowerLevelChanged(i8);
            g5.a.i("module service receiver power change , power level :" + i8);
            if (i8 != 2) {
                if (i8 != 0 || MultiModuleService.this.f3046m == null) {
                    return;
                }
                MultiModuleService.this.f3046m.A();
                return;
            }
            if (MultiModuleService.this.f3042i != null) {
                MultiModuleService.this.f3042i.v();
            }
            if (MultiModuleService.this.f3046m != null) {
                MultiModuleService.this.f3046m.B();
            }
            if (MultiModuleService.this.f3047n != null) {
                MultiModuleService.this.f3047n.a();
            }
            if (MultiModuleService.this.f3048o != null) {
                g5.a.i("power on , dismiss cpd window .");
                MultiModuleService.this.f3048o.f();
            }
        }
    }

    public final void g() {
        x3.a.c("MultiModuleService", "---initService---");
        u4.a.c(getApplicationContext()).e();
        u4.d dVarW = u4.d.w(getApplicationContext());
        this.f3042i = dVarW;
        dVarW.E();
        q4.a aVarK = q4.a.k(getApplicationContext());
        this.f3043j = aVarK;
        aVarK.m();
        f3.d dVarH = f3.d.h(getApplicationContext());
        this.f3044k = dVarH;
        dVarH.i();
        this.f3052s = g.a();
        this.f3053t = getResources().getConfiguration().uiMode & 48;
        l3.a aVarC = l3.a.c(getApplicationContext());
        this.f3045l = aVarC;
        aVarC.g();
        n4.a aVarZ = n4.a.z(getApplicationContext());
        this.f3046m = aVarZ;
        aVarZ.J();
        BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(this);
        this.f3051r = bYDAutoPowerDevice;
        bYDAutoPowerDevice.registerListener(this.f3058y);
        u2.a aVarB = u2.a.b(getApplicationContext());
        this.f3047n = aVarB;
        aVarB.d();
        SettingsCallDispatcher settingsCallDispatcher = SettingsCallDispatcher.Singleton.get();
        this.f3049p = settingsCallDispatcher;
        settingsCallDispatcher.init(this);
        k3.a aVarG = k3.a.g(getApplicationContext());
        this.f3048o = aVarG;
        aVarG.i();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        x3.a.c("MultiModuleService", "---onBind---action: " + action);
        if ("com.byd.traffic.service.action".equals(action)) {
            return this.f3054u;
        }
        if ("com.byd.aidl".equals(action)) {
            return this.f3056w;
        }
        if ("com.byd.charge.service.action".equals(action)) {
            return this.f3055v;
        }
        if ("com.byd.digitalkey.action".equals(action)) {
            return this.f3057x;
        }
        if (SettingsCallDispatcher.ACTION.equals(action)) {
            return this.f3049p.getSystemSettingsInterface();
        }
        return null;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) throws Throwable {
        super.onConfigurationChanged(configuration);
        x3.a.c("MultiModuleService", "---onConfigurationChanged---");
        int i8 = configuration.uiMode & 48;
        String strA = g.a();
        if (!this.f3052s.equals(strA)) {
            this.f3052s = strA;
            r4.c.o(getApplicationContext());
        }
        if (this.f3053t != i8) {
            this.f3053t = i8;
            r4.c.o(getApplicationContext());
        }
        SettingsCallDispatcher settingsCallDispatcher = this.f3049p;
        if (settingsCallDispatcher != null) {
            settingsCallDispatcher.onConfigurationChanged(configuration);
        }
        LinkedHashMap<Integer, b5.g> linkedHashMapD = h.e(getApplicationContext()).d();
        if (linkedHashMapD != null && linkedHashMapD.size() > 0) {
            j.b(getApplicationContext()).c(linkedHashMapD);
        }
        h.e(getApplicationContext()).f();
    }

    @Override // android.app.Service
    public void onCreate() {
        x3.a.c("MultiModuleService", "---onCreate---");
        r4.a.d(this);
        super.onCreate();
        g();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        x3.a.c("MultiModuleService", "---onDestroy---");
        u4.d dVar = this.f3042i;
        if (dVar != null) {
            dVar.N();
        }
        q4.a aVar = this.f3043j;
        if (aVar != null) {
            aVar.p();
        }
        f3.d dVar2 = this.f3044k;
        if (dVar2 != null) {
            dVar2.j();
        }
        l3.a aVar2 = this.f3045l;
        if (aVar2 != null) {
            aVar2.h();
        }
        n4.a aVar3 = this.f3046m;
        if (aVar3 != null) {
            aVar3.R();
        }
        u2.a aVar4 = this.f3047n;
        if (aVar4 != null) {
            aVar4.e();
        }
        k3.a aVar5 = this.f3048o;
        if (aVar5 != null) {
            aVar5.j();
        }
        u4.a.c(getApplicationContext()).f();
        this.f3051r.unregisterListener(this.f3058y);
        SettingsCallDispatcher settingsCallDispatcher = this.f3049p;
        if (settingsCallDispatcher != null) {
            settingsCallDispatcher.onServiceDestroy();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        return super.onStartCommand(intent, i8, i9);
    }
}
