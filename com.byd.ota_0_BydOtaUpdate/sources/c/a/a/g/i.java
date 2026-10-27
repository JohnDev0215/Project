package c.a.a.g;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import c.a.b.d.l;
import com.byd.common.constants.ResultCodeConsts;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ControlPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements c.a.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2208a = "i";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f2209b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile boolean f2210c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f2211d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile boolean f2212e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile boolean f2213f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Context f2214g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.a.b f2215h;
    public OtaUpgradeManager j;
    public BYDAutoOtaDevice k;
    public BYDAutoBodyworkDevice l;
    public BYDAutoGearboxDevice m;
    public BYDAutoChargingDevice n;
    public HashSet<ResultCodeConsts.NormalCodeEnum> o;
    public c.a.a.h.a.a p;
    public int q = 0;
    public Handler r = new a(Looper.getMainLooper());
    public AbsBYDAutoOtaListener s = new g();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c.a.b.c.a f2216i = c.a.b.c.a.k();

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            i.this.p.e(141);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d() {
            i.this.p.e(151);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    i.p(i.this);
                    if (i.this.q <= 30) {
                        int batteryManagementDeviceState = i.this.n.getBatteryManagementDeviceState();
                        c.a.b.d.e.c(i.f2208a, "getBatteryManagementDeviceState = " + batteryManagementDeviceState);
                        boolean z2 = batteryManagementDeviceState == 0 || batteryManagementDeviceState == 1 || batteryManagementDeviceState == 13;
                        int dischargeState = i.this.k.getDischargeState(1);
                        c.a.b.d.e.c(i.f2208a, "getDischargeState(1) = " + dischargeState);
                        boolean z3 = dischargeState == 0 || dischargeState == 1;
                        int dischargeState2 = i.this.k.getDischargeState(2);
                        c.a.b.d.e.c(i.f2208a, "getDischargeState(2) = " + dischargeState2);
                        z = dischargeState2 == 0 || dischargeState2 == 1;
                        if (z2 || z3 || z) {
                            sendEmptyMessageDelayed(1, 200L);
                        } else {
                            i.this.p.e(61);
                        }
                    } else {
                        i.this.p.e(62);
                    }
                    break;
                case 2:
                    i.p(i.this);
                    if (i.this.q <= 30) {
                        int dischargeMainContactorState = i.this.k.getDischargeMainContactorState();
                        c.a.b.d.e.c(i.f2208a, "getDischargeMainContactorState = " + dischargeMainContactorState);
                        if (!(dischargeMainContactorState == 0)) {
                            sendEmptyMessageDelayed(2, 200L);
                        } else {
                            i.this.p.e(81);
                        }
                    } else {
                        i.this.p.e(82);
                    }
                    break;
                case 3:
                    i.p(i.this);
                    if (i.this.q <= 30) {
                        z = i.this.k.getBcmOtaState() == 1;
                        c.a.b.d.e.c(i.f2208a, "getBcmOtaState = " + i.this.k.getBcmOtaState());
                        if (!z) {
                            sendEmptyMessageDelayed(3, 200L);
                        } else {
                            boolean unused = i.f2209b = true;
                            i.this.p.e(91);
                        }
                    } else {
                        i.this.p.e(92);
                    }
                    break;
                case 4:
                    i.p(i.this);
                    if (i.this.q <= 30) {
                        z = i.this.k.getTurnOffState() == 1;
                        c.a.b.d.e.c(i.f2208a, "getTurnOffState = " + i.this.k.getTurnOffState());
                        if (!z) {
                            sendEmptyMessageDelayed(4, 200L);
                        } else {
                            l.d().c(new Runnable() { // from class: c.a.a.g.c
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f2201c.b();
                                }
                            }, 8000L);
                        }
                    } else {
                        i.this.p.e(142);
                    }
                    break;
                case 5:
                    i.p(i.this);
                    if (i.this.q <= 30) {
                        z = i.this.k.getIg3RelayState() == 2;
                        c.a.b.d.e.c(i.f2208a, "getIg3RelayState = " + i.this.k.getIg3RelayState());
                        if (!z) {
                            sendEmptyMessageDelayed(5, 200L);
                        } else {
                            l.d().c(new Runnable() { // from class: c.a.a.g.b
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f2200c.d();
                                }
                            }, 5000L);
                        }
                    } else {
                        i.this.p.e(152);
                    }
                    break;
                case 6:
                    i.p(i.this);
                    if (i.this.q <= 30) {
                        z = i.this.k.getDcState() == 2;
                        c.a.b.d.e.c(i.f2208a, "getDcState = " + i.this.k.getDcState());
                        if (!z) {
                            sendEmptyMessageDelayed(6, 200L);
                        } else {
                            i.this.p.e(181);
                        }
                    } else {
                        i.this.p.e(182);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class b extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2218a;

        public b(boolean z) {
            this.f2218a = z;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onUpdateFlag(int i2) {
            super.onUpdateFlag(i2);
            boolean unused = i.f2213f = this.f2218a;
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onSetOtaCtlScreen->resultCode = " + i2 + ", isOtaCtlScreen = " + i.f2213f);
        }
    }

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class c extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2220a;

        public c(int i2) {
            this.f2220a = i2;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onQnxOtaState->state = " + this.f2220a + ", resultCode = " + i2);
            i.this.n(this.f2220a == 1);
        }
    }

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class d extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2222a;

        public d(int i2) {
            this.f2222a = i2;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onAndroidOtaState->state = " + this.f2222a + ", resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class e implements c.a.a.d {
        public e() {
        }

        @Override // c.a.a.d
        public void a() {
            i.this.p.e(111);
        }

        @Override // c.a.a.d
        public void b() {
            i.this.p.e(111);
        }

        @Override // c.a.a.d
        public void c() {
            i.this.p.e(112);
        }
    }

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class f implements c.a.a.c {
        public f() {
        }

        @Override // c.a.a.c
        public void a(String str) {
            c.a.b.d.e.b(i.f2208a, "onDownloadFactoryPackageFail->text = " + str);
            i.this.p.e(202);
        }

        @Override // c.a.a.c
        public void b() {
            c.a.b.d.e.c(i.f2208a, "onDownloadFactoryPackageSuccess");
            i.this.p.e(201);
        }
    }

    /* JADX INFO: compiled from: ControlPresenter.java */
    public class g extends AbsBYDAutoOtaListener {
        public g() {
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void on7dfExistState(boolean z) {
            super.on7dfExistState(z);
            c.a.b.d.e.c(AbsBYDAutoOtaListener.TAG, "on7dfExistState->exist = " + z);
        }
    }

    public i(Context context, c.a.a.b bVar, OtaUpgradeManager otaUpgradeManager) {
        this.f2214g = context;
        this.f2215h = bVar;
        this.j = otaUpgradeManager;
        this.k = BYDAutoOtaDevice.getInstance(context);
        this.l = BYDAutoBodyworkDevice.getInstance(context);
        this.m = BYDAutoGearboxDevice.getInstance(context);
        this.n = BYDAutoChargingDevice.getInstance(context);
        this.k.registerListener(this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void X() {
        this.p.e(231);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Z() {
        this.p.e(241);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b0() {
        this.p.e(251);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d0() {
        this.p.e(171);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f0() {
        this.p.e(161);
    }

    public static /* synthetic */ int p(i iVar) {
        int i2 = iVar.q;
        iVar.q = i2 + 1;
        return i2;
    }

    public void A() {
        c.a.b.d.e.c(f2208a, "setOtaPower(1)");
        this.k.setOtaPower(1);
        l.d().c(new Runnable() { // from class: c.a.a.g.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f2202c.Z();
            }
        }, 8000L);
    }

    public void B() {
        c.a.b.d.e.c(f2208a, "setOtaPower(2)");
        this.k.setOtaPower(2);
        l.d().c(new Runnable() { // from class: c.a.a.g.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f2199c.b0();
            }
        }, 8000L);
    }

    public void C(boolean z) {
        c.a.b.d.e.c(f2208a, "directUpdateStartActualUpgrade->isRepairUpgrade = " + z);
        j0();
        if (z) {
            E("repairUpdate", 1);
        } else {
            this.j.startUpdate(null);
        }
    }

    public void D() {
        this.f2215h.B(new f());
    }

    public void E(String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(str, i2);
        this.j.executeCMD(bundle);
    }

    public void F() {
        c.a.b.d.e.c(f2208a, "getStatus");
        j0();
        E("getStatus", 1);
    }

    public boolean G() {
        return f2212e;
    }

    public boolean H() {
        c.a.b.d.e.c(f2208a, "isVehicleTypeDM = " + c.a.b.d.b.c(this.l.getAutoType()));
        return false;
    }

    public boolean I() {
        c.a.b.d.e.c(f2208a, "isVehicleTypeEV = " + c.a.b.d.b.b(this.l.getAutoType()));
        return true;
    }

    public boolean J() {
        return false;
    }

    public void K() {
        int backLightState = this.k.getBackLightState();
        c.a.b.d.e.c(f2208a, "getBackLightState = " + backLightState);
        if (backLightState == 1) {
            this.p.e(221);
        } else if (backLightState == 2) {
            this.p.e(222);
        } else {
            this.p.e(222);
        }
    }

    public void L() {
        E("otaModel", 1);
        this.q = 0;
        this.r.sendEmptyMessage(3);
    }

    public void M() {
        int antiTheftStatus = this.l.getAntiTheftStatus();
        c.a.b.d.e.c(f2208a, "getAntiTheftStatus = " + antiTheftStatus);
        if (antiTheftStatus == 2) {
            this.p.e(11);
        } else {
            this.p.e(12);
        }
    }

    public void N() {
        this.q = 0;
        this.r.sendEmptyMessage(6);
    }

    public void O() {
        c.a.b.d.e.c(f2208a, "setHighVoltageCmd(6)");
        this.k.setHighVoltageCmd(6);
        l.d().c(new Runnable() { // from class: c.a.a.g.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f2206c.d0();
            }
        }, 1000L);
    }

    public void P() {
        if ((I() || H()) ? x(true, true, true, false, true, true, false) : J() ? x(true, true, false, true, true, true, true) : true) {
            this.p.e(121);
        } else {
            this.p.e(122);
        }
    }

    public void Q() {
        if ((I() || H()) ? x(true, true, true, false, true, true, false) : J() ? x(true, true, false, true, true, true, true) : true) {
            this.p.e(31);
        } else {
            this.p.e(32);
        }
    }

    public void R() {
        int powerLevel = this.l.getPowerLevel();
        c.a.b.d.e.c(f2208a, "getPowerLevel = " + powerLevel);
        if (powerLevel == 2) {
            this.p.e(21);
        } else if (powerLevel == 0) {
            this.p.e(23);
        } else if (powerLevel == 1) {
            this.p.e(24);
        }
    }

    public void S(long j) {
        if (Calendar.getInstance().getTimeInMillis() < j) {
            this.p.e(211);
        } else {
            this.p.e(212);
        }
    }

    public void T() {
        int batteryManagementDeviceState = this.n.getBatteryManagementDeviceState();
        String str = f2208a;
        c.a.b.d.e.c(str, "getBatteryManagementDeviceState = " + batteryManagementDeviceState);
        boolean z = batteryManagementDeviceState == 0 || batteryManagementDeviceState == 1 || batteryManagementDeviceState == 13;
        int dischargeState = this.k.getDischargeState(1);
        c.a.b.d.e.c(str, "getDischargeState(1) = " + dischargeState);
        boolean z2 = dischargeState == 0 || dischargeState == 1;
        int dischargeState2 = this.k.getDischargeState(2);
        c.a.b.d.e.c(str, "getDischargeState(2) = " + dischargeState2);
        boolean z3 = dischargeState2 == 0 || dischargeState2 == 1;
        int chargeAndDischargeSystemState = this.k.getChargeAndDischargeSystemState();
        c.a.b.d.e.c(str, "getChargeAndDischargeSystemState = " + chargeAndDischargeSystemState);
        boolean z4 = chargeAndDischargeSystemState == 6 || chargeAndDischargeSystemState == 7;
        if (I()) {
            if (z || z3 || z4) {
                this.p.e(51);
                return;
            } else {
                this.p.e(52);
                return;
            }
        }
        if (H()) {
            if (z || z3 || z2) {
                this.p.e(51);
            } else {
                this.p.e(52);
            }
        }
    }

    public void U() {
        boolean z = false;
        int smartChargeState = this.k.getSmartChargeState(0);
        String str = f2208a;
        c.a.b.d.e.c(str, "getSmartChargeState(0) = " + smartChargeState);
        int smartChargeState2 = this.k.getSmartChargeState(1);
        c.a.b.d.e.c(str, "getSmartChargeState(1) = " + smartChargeState2);
        if (smartChargeState < 0 || smartChargeState > 3 ? smartChargeState2 == 1 : !(smartChargeState != 0 && smartChargeState != 1)) {
            z = true;
        }
        if (z) {
            this.p.e(71);
        } else {
            this.p.e(72);
        }
    }

    public void V() {
        f();
    }

    @Override // c.a.a.a
    public void a() {
        if (this.f2215h != null) {
            this.f2215h = null;
        }
        if (this.f2214g != null) {
            this.f2214g = null;
        }
        AbsBYDAutoOtaListener absBYDAutoOtaListener = this.s;
        if (absBYDAutoOtaListener != null) {
            this.k.unregisterListener(absBYDAutoOtaListener);
        }
    }

    @Override // c.a.a.a
    public boolean b() {
        int bcmOtaState = this.k.getBcmOtaState();
        c.a.b.d.e.c(f2208a, "isProcessingOta->bcmOtaState = " + bcmOtaState + ", isOtaState = " + f2209b);
        return bcmOtaState == 1 || f2209b;
    }

    @Override // c.a.a.a
    public void c(final int i2) {
        u0(i2);
        l.d().c(new Runnable() { // from class: c.a.a.g.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f2203c.h0(i2);
            }
        }, 2000L);
    }

    @Override // c.a.a.a
    public void d() {
        c.a.b.d.e.c(f2208a, "rebootQuitOtaModel");
        this.k.setHighVoltageCmd(7);
        this.k.setOtaPower(3);
        E("otaModel", 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0082  */
    @Override // c.a.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(int r4) {
        /*
            r3 = this;
            java.lang.String r0 = c.a.a.g.i.f2208a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "setOtaUpdateMode->updateMode = "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r2 = ", isOtaUpdate = "
            r1.append(r2)
            boolean r2 = c.a.a.g.i.f2210c
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            c.a.b.d.e.c(r0, r1)
            boolean r0 = c.a.a.g.i.f2210c
            if (r0 == 0) goto L25
            return
        L25:
            r0 = 1
            c.a.a.g.i.f2210c = r0
            c.a.a.b r0 = r3.f2215h
            android.content.Context r1 = r3.f2214g
            int r2 = c.a.a.e.f2195a
            java.lang.String r1 = r1.getString(r2)
            r0.F(r1)
            c.a.a.b r0 = r3.f2215h
            r0.q(r4)
            r0 = 9
            if (r4 == r0) goto L82
            r0 = 21
            if (r4 == r0) goto L78
            switch(r4) {
                case 1: goto L6e;
                case 2: goto L64;
                case 3: goto L82;
                case 4: goto L5a;
                case 5: goto L50;
                case 6: goto L46;
                default: goto L45;
            }
        L45:
            goto L8e
        L46:
            c.a.a.h.b.j r4 = new c.a.a.h.b.j
            android.content.Context r0 = r3.f2214g
            r4.<init>(r0, r3)
            r3.p = r4
            goto L8e
        L50:
            c.a.a.h.b.f r4 = new c.a.a.h.b.f
            android.content.Context r0 = r3.f2214g
            r4.<init>(r0, r3)
            r3.p = r4
            goto L8e
        L5a:
            c.a.a.h.b.g r4 = new c.a.a.h.b.g
            android.content.Context r0 = r3.f2214g
            r4.<init>(r0, r3)
            r3.p = r4
            goto L8e
        L64:
            c.a.a.h.b.h r4 = new c.a.a.h.b.h
            android.content.Context r0 = r3.f2214g
            r4.<init>(r0, r3)
            r3.p = r4
            goto L8e
        L6e:
            c.a.a.h.b.i r4 = new c.a.a.h.b.i
            android.content.Context r0 = r3.f2214g
            r4.<init>(r0, r3)
            r3.p = r4
            goto L8e
        L78:
            c.a.a.h.b.d r4 = new c.a.a.h.b.d
            android.content.Context r0 = r3.f2214g
            r4.<init>(r0, r3)
            r3.p = r4
            goto L8e
        L82:
            c.a.a.h.b.c r0 = new c.a.a.h.b.c
            android.content.Context r1 = r3.f2214g
            r0.<init>(r1, r3)
            r3.p = r0
            r0.d(r4)
        L8e:
            c.a.a.h.a.a r4 = r3.p
            r0 = 1000(0x3e8, float:1.401E-42)
            r4.e(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.g.i.e(int):void");
    }

    @Override // c.a.a.a
    public void f() {
        c.a.b.d.e.c(f2208a, "quitOtaModel");
        f2210c = false;
        f2209b = false;
        this.k.setHighVoltageCmd(7);
        this.k.setOtaPower(3);
        E("otaModel", 0);
        p0(0);
    }

    @Override // c.a.a.a
    public boolean g() {
        c.a.b.d.e.c(f2208a, "getQnxOta->isQnxOtaState = " + f2211d);
        return f2211d;
    }

    @Override // c.a.a.a
    public void h(boolean z) {
        f2212e = z;
    }

    @Override // c.a.a.a
    public void i(int i2, boolean z) {
        c.a.b.d.e.c(f2208a, "setOtaDirectUpdateMode->updateMode = " + i2 + ", isRepairUpgrade = " + z + ", isOtaUpdate = " + f2210c);
        if (f2210c) {
            return;
        }
        f2210c = true;
        this.f2215h.F(this.f2214g.getString(c.a.a.e.f2195a));
        this.f2215h.q(i2);
        c.a.a.h.b.e eVar = new c.a.a.h.b.e(this.f2214g, i2, this, z);
        this.p = eVar;
        eVar.e(1003);
    }

    public void i0() {
        this.f2215h.F(this.f2214g.getString(c.a.a.e.f2196b));
        this.p.e(131);
    }

    @Override // c.a.a.a
    public void j(int i2) {
        c.a.b.d.e.c(f2208a, "setOtaUpdateGetStatus->updateMode = " + i2 + ", isOtaUpdate = " + f2210c);
        if (f2210c) {
            return;
        }
        f2210c = true;
        c.a.a.h.b.e eVar = new c.a.a.h.b.e(this.f2214g, i2, this, false);
        this.p = eVar;
        eVar.e(1006);
    }

    public final void j0() {
        c.a.b.d.e.c(f2208a, "registerUpdateListener");
        this.f2215h.z();
        this.j.registerListener(new c.a.a.f.a(this.f2215h));
    }

    @Override // c.a.a.a
    public boolean k() {
        return this.l.getPowerLevel() != 2;
    }

    public void k0() {
        c.a.b.d.e.c(f2208a, "setHighVoltageCmd(0)");
        this.k.setHighVoltageCmd(0);
        l.d().c(new Runnable() { // from class: c.a.a.g.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f2205c.f0();
            }
        }, 1000L);
    }

    @Override // c.a.a.a
    public void l() {
        c.a.b.d.e.c(f2208a, "controlPre");
    }

    public void l0() {
        c.a.b.d.e.c(f2208a, "setOtaPower(1)");
        this.k.setOtaPower(1);
        this.q = 0;
        this.r.sendEmptyMessage(4);
    }

    @Override // c.a.a.a
    public void m(boolean z, String str) {
        c.a.b.d.e.c(f2208a, "setOtaCtlScreen->reason = " + str + ", flag = " + z + ", before isOtaCtlScreen = " + f2213f);
        this.k.setUpdateFlag(z ? 1 : 0, new b(z));
    }

    public void m0() {
        c.a.b.d.e.c(f2208a, "setOtaPower(2)");
        this.k.setOtaPower(2);
        this.q = 0;
        this.r.sendEmptyMessage(5);
    }

    @Override // c.a.a.a
    public void n(boolean z) {
        c.a.b.d.e.c(f2208a, "setQnxOta->state = " + z);
        f2211d = z;
    }

    public void n0() {
        c.a.b.d.e.c(f2208a, "setQuitChargeAndDischarge");
        this.k.setQuitChargeAndDischarge(1);
        this.q = 0;
        this.r.sendEmptyMessage(1);
    }

    public void o0() {
        c.a.b.d.e.c(f2208a, "setQuitSmartCharge");
        this.k.setQuitSmartCharge(1);
        this.q = 0;
        this.r.sendEmptyMessage(2);
    }

    public void p0(int i2) {
        c.a.b.d.e.c(f2208a, "setAndroidOtaState->state = " + i2);
        this.k.setAndroidOtaState(i2, new d(i2));
    }

    public void q0(ResultCodeConsts.NormalCodeEnum normalCodeEnum, boolean z) {
        if (!z) {
            this.f2215h.D(normalCodeEnum);
            return;
        }
        HashSet<ResultCodeConsts.NormalCodeEnum> hashSet = this.o;
        if (hashSet == null || hashSet.size() <= 0) {
            return;
        }
        Iterator<ResultCodeConsts.NormalCodeEnum> it = this.o.iterator();
        if (it.hasNext()) {
            this.f2215h.D(it.next());
        }
    }

    /* JADX INFO: renamed from: r0, reason: merged with bridge method [inline-methods] */
    public void h0(int i2) {
        c.a.b.d.e.c(f2208a, "setQnxOtaState->state = " + i2);
        this.k.setQnxOtaState(i2, new c(i2));
    }

    public void s0(String str) {
        if (k()) {
            c.a.b.d.e.c(f2208a, "setScreenOff->reason = " + str);
            this.k.setBackLightState(2);
        }
    }

    public void t0() {
        c.a.b.d.e.c(f2208a, "setScreenOn");
        this.k.setBackLightState(1);
    }

    public void u0(int i2) {
        this.k.notifyTboxOtaState(i2);
    }

    public void v0() {
        this.f2215h.o(new e());
    }

    public void w0() {
        boolean zD = c.a.b.d.h.d();
        c.a.b.d.e.c(f2208a, "startActualUpgrade->otaMasterTest = " + zD);
        if (zD) {
            this.f2215h.e();
        } else {
            j0();
            this.j.startUpdate(null);
        }
    }

    public final boolean x(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        boolean z8;
        HashSet<ResultCodeConsts.NormalCodeEnum> hashSet = this.o;
        if (hashSet != null) {
            hashSet.clear();
        } else {
            this.o = new HashSet<>();
        }
        String str = f2208a;
        c.a.b.d.e.c(str, "getVehicleSpeed = " + this.k.getVehicleSpeed());
        if (!z || this.k.getVehicleSpeed() == 0.0f) {
            z8 = true;
        } else {
            this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_EPB_NOT_UP_OR_GEAR_NOT_P);
            z8 = false;
        }
        c.a.b.d.e.c(str, "getEPBState = " + this.m.getEPBState());
        if (z2 && this.m.getEPBState() != 3) {
            this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_EPB_NOT_UP_OR_GEAR_NOT_P);
            z8 = false;
        }
        c.a.b.d.e.c(str, "getSocValue = " + this.k.getSocValue());
        if (z3 && (TextUtils.isEmpty(this.f2216i.x()) || this.k.getSocValue() <= Integer.valueOf(this.f2216i.x()).intValue())) {
            this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SOC_NOT_SATISFIED);
            z8 = false;
        }
        if (z4) {
            if (TextUtils.isEmpty(this.f2216i.z())) {
                this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_BATTERY_VOLTAGE_NOT_SATISFIED);
            } else {
                int batteryVoltage = this.k.getBatteryVoltage(0);
                c.a.b.d.e.c(str, "getBatteryVoltage(0) = " + batteryVoltage);
                int batteryVoltage2 = this.k.getBatteryVoltage(1);
                c.a.b.d.e.c(str, "getBatteryVoltage(1) = " + batteryVoltage2);
                int iIntValue = Integer.valueOf(this.f2216i.z()).intValue();
                if (batteryVoltage > 0 && batteryVoltage <= iIntValue) {
                    this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_BATTERY_VOLTAGE_NOT_SATISFIED);
                } else if (batteryVoltage2 > 0 && batteryVoltage <= iIntValue) {
                    this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_BATTERY_VOLTAGE_NOT_SATISFIED);
                }
            }
            z8 = false;
        }
        c.a.b.d.e.c(str, "getGear = " + this.m.getGear());
        if (z5 && this.m.getGear() != 1) {
            this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_EPB_NOT_UP_OR_GEAR_NOT_P);
            z8 = false;
        }
        c.a.b.d.e.c(str, "getDoorState(BYDAutoBodyworkDevice.BODYWORK_CMD_DOOR_HOOD) = " + this.l.getDoorState(5));
        if (z6 && this.l.getDoorState(5) == 1) {
            this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_ENGINE_HOOD_OPEN);
            z8 = false;
        }
        if (z7) {
            int batteryTemp = this.k.getBatteryTemp(0);
            c.a.b.d.e.c(str, "getBatteryTemp(0) = " + batteryTemp);
            int batteryTemp2 = this.k.getBatteryTemp(1);
            c.a.b.d.e.c(str, "getBatteryTemp(1) = " + batteryTemp2);
            int iIntValue2 = Integer.valueOf(this.f2216i.y()).intValue();
            if (batteryTemp != 255 && batteryTemp <= iIntValue2) {
                this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_TEMPERATURE);
                return false;
            }
            if (batteryTemp2 != 255 && batteryTemp2 <= iIntValue2) {
                this.o.add(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_TEMPERATURE);
                return false;
            }
        }
        return z8;
    }

    public void y() {
        f2209b = true;
        E("otaModel", 1);
        l.d().c(new Runnable() { // from class: c.a.a.g.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f2207c.X();
            }
        }, 4000L);
    }

    public void z() {
        f();
    }
}
