package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;
import com.byd.common.constants.ResultCodeConsts;

/* JADX INFO: compiled from: UpdateOffState.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2290a = "h";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.g.i f2292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f2293d;

    /* JADX INFO: compiled from: UpdateOffState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateOffState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateOffState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateOffState->onQnxOtaState resultCode = " + i2);
            h.this.n();
            h.this.f2292c.n(true);
            h.this.f2292c.m0();
        }
    }

    /* JADX INFO: compiled from: UpdateOffState.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(h.f2290a, "PowerOffTimeoutRunnable 接口回调超时");
            h.this.e(142);
            if (h.this.f2293d != null) {
                h.this.f2293d = null;
            }
        }

        public /* synthetic */ c(h hVar, a aVar) {
            this();
        }
    }

    public h(Context context, c.a.a.g.i iVar) {
        this.f2291b = context;
        this.f2292c = iVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l() {
        this.f2292c.L();
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2290a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        if (i2 != 31) {
            if (i2 == 52 || i2 == 61) {
                return 70;
            }
            if (i2 != 72 && i2 != 81) {
                if (i2 == 91) {
                    return 110;
                }
                if (i2 == 111) {
                    return 130;
                }
                if (i2 == 131) {
                    return 150;
                }
                if (i2 == 151) {
                    return 30;
                }
                if (i2 != 1000) {
                    return i2;
                }
                return 90;
            }
        } else if (!this.f2292c.J()) {
            return 50;
        }
        return 1002;
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        c.a.b.d.e.c(f2290a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        switch (bVar.f2227a) {
            case 30:
                this.f2292c.Q();
                break;
            case 32:
                this.f2292c.q0(null, true);
                e(1001);
                break;
            case 50:
                this.f2292c.T();
                break;
            case 51:
                if (this.f2292c.I()) {
                    this.f2292c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                    e(1001);
                } else if (this.f2292c.H()) {
                    this.f2292c.n0();
                }
                break;
            case 62:
                if (this.f2292c.I()) {
                    this.f2292c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                } else if (this.f2292c.H()) {
                    this.f2292c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM, false);
                }
                e(1001);
                break;
            case 70:
                this.f2292c.U();
                break;
            case 71:
                this.f2292c.o0();
                break;
            case 82:
                this.f2292c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING, false);
                e(1001);
                break;
            case 90:
                l.d().c(new Runnable() { // from class: c.a.a.h.b.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2229c.l();
                    }
                }, 3000L);
                break;
            case 92:
                this.f2292c.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE, false);
                e(1001);
                break;
            case 110:
                this.f2292c.v0();
                break;
            case 112:
                e(1001);
                this.f2292c.c(0);
                break;
            case 130:
                this.f2292c.i0();
                break;
            case 150:
                BYDAutoOtaDevice.getInstance(this.f2291b).setAndroidOtaState(1, new a());
                BYDAutoOtaDevice.getInstance(this.f2291b).setQnxOtaState(1, new b());
                m(5000L);
                break;
            case 152:
                this.f2292c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE, false);
                e(1001);
                break;
            case 1001:
                this.f2292c.V();
                break;
            case 1002:
                this.f2292c.m(true, "UpdateOffState startActualUpgrade");
                this.f2292c.t0();
                this.f2292c.w0();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }

    public final void m(long j) {
        if (this.f2293d != null) {
            return;
        }
        c.a.b.d.e.c(f2290a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.f2293d = new c(this, null);
        l.d().c(this.f2293d, j);
    }

    public final void n() {
        if (this.f2293d != null) {
            c.a.b.d.e.c(f2290a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.f2293d);
            this.f2293d = null;
        }
    }
}
