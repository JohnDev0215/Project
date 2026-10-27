package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;
import com.byd.common.constants.ResultCodeConsts;

/* JADX INFO: compiled from: UpdateAppointmentState.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2230a = "c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.g.i f2232c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f2233d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbsBYDAutoOtaListener f2234e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbsBYDAutoBodyworkListener f2235f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2236g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Runnable f2237h;

    /* JADX INFO: compiled from: UpdateAppointmentState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateAppointmentState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateAppointmentState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateAppointmentState->onQnxOtaState resultCode = " + i2);
            c.this.u();
            c.this.f2232c.n(true);
            c.this.f2232c.m0();
        }
    }

    /* JADX INFO: renamed from: c.a.a.h.b.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: UpdateAppointmentState.java */
    public class C0044c extends AbsBYDAutoBodyworkListener {
        public C0044c() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int i2, int i3) {
            super.onDoorStateChanged(i2, i3);
            c.a.b.d.e.c(AbsBYDAutoBodyworkListener.TAG, "onDoorStateChanged->area = " + i2 + ", state = " + i3);
            if (i3 == 1) {
                c.this.w(60000L);
            }
        }

        public /* synthetic */ C0044c(c cVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateAppointmentState.java */
    public class d extends AbsBYDAutoOtaListener {
        public d() {
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void onLFDoorLockStatus(int i2) {
            super.onLFDoorLockStatus(i2);
            c.a.b.d.e.c(AbsBYDAutoOtaListener.TAG, "onLFDoorLockStatus->state = " + i2);
            if (i2 != 2 || c.this.f2233d == null) {
                return;
            }
            c.this.v();
            c.this.f2232c.s0("appointment onLFDoorLockStatus");
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void onStartKeyState(int i2) {
            super.onStartKeyState(i2);
            c.a.b.d.e.c(AbsBYDAutoOtaListener.TAG, "onStartKeyState->state = " + i2);
            boolean z = true;
            if (i2 != 1 && i2 != 2 && i2 != 3) {
                z = false;
            }
            if (z) {
                c.this.w(60000L);
            }
        }

        public /* synthetic */ d(c cVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateAppointmentState.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(c.f2230a, "PowerOffTimeoutRunnable 接口回调超时");
            c.this.e(142);
            if (c.this.f2237h != null) {
                c.this.f2237h = null;
            }
        }

        public /* synthetic */ e(c cVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateAppointmentState.java */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f2232c.s0("appointment ScreenOffRunnable");
            if (c.this.f2233d != null) {
                c.this.f2233d = null;
            }
        }

        public /* synthetic */ f(c cVar, a aVar) {
            this();
        }
    }

    public c(Context context, c.a.a.g.i iVar) {
        this.f2231b = context;
        this.f2232c = iVar;
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p() {
        this.f2232c.Q();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0037, code lost:
    
        if (r4.f2232c.I() != false) goto L14;
     */
    @Override // c.a.a.h.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int b(int r5) {
        /*
            r4 = this;
            java.lang.String r0 = c.a.a.h.b.c.f2230a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "curJudgeState = "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = ", msg = "
            r1.append(r2)
            java.lang.String r2 = r4.a(r5)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            c.a.b.d.e.c(r0, r1)
            r0 = 50
            r1 = 30
            r2 = 1002(0x3ea, float:1.404E-42)
            r3 = 70
            switch(r5) {
                case 11: goto L76;
                case 31: goto L73;
                case 52: goto L60;
                case 61: goto L5e;
                case 72: goto L5c;
                case 81: goto L5c;
                case 91: goto L59;
                case 111: goto L56;
                case 131: goto L3a;
                case 151: goto L31;
                case 1000: goto L2e;
                default: goto L2d;
            }
        L2d:
            goto L78
        L2e:
            r5 = 10
            goto L78
        L31:
            c.a.a.g.i r5 = r4.f2232c
            boolean r5 = r5.I()
            if (r5 == 0) goto L68
            goto L4b
        L3a:
            c.a.a.g.i r1 = r4.f2232c
            boolean r1 = r1.I()
            if (r1 == 0) goto L43
            goto L5e
        L43:
            c.a.a.g.i r1 = r4.f2232c
            boolean r1 = r1.H()
            if (r1 == 0) goto L4d
        L4b:
            r5 = r0
            goto L78
        L4d:
            c.a.a.g.i r0 = r4.f2232c
            boolean r0 = r0.J()
            if (r0 == 0) goto L78
            goto L5c
        L56:
            r5 = 130(0x82, float:1.82E-43)
            goto L78
        L59:
            r5 = 150(0x96, float:2.1E-43)
            goto L78
        L5c:
            r5 = r2
            goto L78
        L5e:
            r5 = r3
            goto L78
        L60:
            c.a.a.g.i r0 = r4.f2232c
            boolean r0 = r0.I()
            if (r0 == 0) goto L6a
        L68:
            r5 = r1
            goto L78
        L6a:
            c.a.a.g.i r0 = r4.f2232c
            boolean r0 = r0.H()
            if (r0 == 0) goto L78
            goto L5e
        L73:
            r5 = 110(0x6e, float:1.54E-43)
            goto L78
        L76:
            r5 = 90
        L78:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.h.b.c.b(int):int");
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        c.a.b.d.e.c(f2230a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 10:
                this.f2232c.M();
                break;
            case 12:
                if (9 == this.f2236g) {
                    this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_NOW_CONDITION_UNMATCHED_CAR_UNLOCK, false);
                } else {
                    this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CAR_UNLOCK, false);
                }
                e(1001);
                break;
            case 30:
                l.d().c(new Runnable() { // from class: c.a.a.h.b.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2228c.p();
                    }
                }, 5000L);
                break;
            case 32:
                this.f2232c.q0(null, true);
                e(1001);
                break;
            case 50:
                this.f2232c.T();
                break;
            case 51:
                if (this.f2232c.I()) {
                    this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                    e(1001);
                } else if (this.f2232c.H()) {
                    this.f2232c.n0();
                }
                break;
            case 62:
                if (this.f2232c.I()) {
                    this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                } else if (this.f2232c.H()) {
                    this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM, false);
                }
                e(1001);
                break;
            case 70:
                this.f2232c.U();
                break;
            case 71:
                this.f2232c.o0();
                break;
            case 82:
                this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING, false);
                e(1001);
                break;
            case 90:
                this.f2232c.L();
                break;
            case 92:
                this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE, false);
                e(1001);
                break;
            case 110:
                this.f2232c.v0();
                break;
            case 112:
                e(1001);
                this.f2232c.c(0);
                break;
            case 130:
                this.f2232c.i0();
                break;
            case 150:
                BYDAutoOtaDevice.getInstance(this.f2231b).setAndroidOtaState(1, new a());
                BYDAutoOtaDevice.getInstance(this.f2231b).setQnxOtaState(1, new b());
                q(5000L);
                break;
            case 152:
                this.f2232c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE, false);
                e(1001);
                break;
            case 1001:
                z();
                this.f2232c.V();
                break;
            case 1002:
                this.f2232c.w0();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
        c.a.b.d.e.c(f2230a, "setUpdateMode mode = " + i2);
        this.f2236g = i2;
    }

    public final void q(long j) {
        if (this.f2237h != null) {
            return;
        }
        c.a.b.d.e.c(f2230a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.f2237h = new e(this, null);
        l.d().c(this.f2237h, j);
    }

    public final void r() {
        if (this.f2235f == null) {
            c.a.b.d.e.c(f2230a, "registerBYDAutoBodyworkListener");
            this.f2235f = new C0044c(this, null);
            BYDAutoBodyworkDevice.getInstance(this.f2231b).registerListener(this.f2235f);
        }
    }

    public final void s() {
        if (this.f2234e == null) {
            c.a.b.d.e.c(f2230a, "registerBYDAutoOtaListener");
            this.f2234e = new d(this, null);
            BYDAutoOtaDevice.getInstance(this.f2231b).registerListener(this.f2234e);
        }
    }

    public final void t() {
        s();
        r();
    }

    public final void u() {
        if (this.f2237h != null) {
            c.a.b.d.e.c(f2230a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.f2237h);
            this.f2237h = null;
        }
    }

    public final void v() {
        if (this.f2233d != null) {
            l.d().i(this.f2233d);
            this.f2233d = null;
        }
    }

    public final void w(long j) {
        if (this.f2233d != null) {
            return;
        }
        this.f2232c.t0();
        this.f2233d = new f(this, null);
        l.d().c(this.f2233d, j);
    }

    public final void x() {
        if (this.f2235f != null) {
            c.a.b.d.e.c(f2230a, "unregisterBYDAutoBodyworkListener");
            BYDAutoBodyworkDevice.getInstance(this.f2231b).unregisterListener(this.f2235f);
            this.f2235f = null;
        }
    }

    public final void y() {
        if (this.f2234e != null) {
            c.a.b.d.e.c(f2230a, "unregisterBYDAutoOtaListener");
            BYDAutoOtaDevice.getInstance(this.f2231b).unregisterListener(this.f2234e);
            this.f2234e = null;
        }
    }

    public final void z() {
        y();
        x();
        v();
    }
}
