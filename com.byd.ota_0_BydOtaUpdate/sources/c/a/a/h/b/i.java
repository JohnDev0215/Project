package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;
import com.byd.common.constants.ResultCodeConsts;

/* JADX INFO: compiled from: UpdateOnState.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2297a = "i";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.g.i f2299c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f2300d;

    /* JADX INFO: compiled from: UpdateOnState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateOnState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateOnState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateOnState->onQnxOtaState resultCode = " + i2);
            i.this.l();
            i.this.f2299c.n(true);
            i.this.f2299c.l0();
        }
    }

    /* JADX INFO: compiled from: UpdateOnState.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(i.f2297a, "PowerOffTimeoutRunnable 接口回调超时");
            i.this.e(142);
            if (i.this.f2300d != null) {
                i.this.f2300d = null;
            }
        }

        public /* synthetic */ c(i iVar, a aVar) {
            this();
        }
    }

    public i(Context context, c.a.a.g.i iVar) {
        this.f2298b = context;
        this.f2299c = iVar;
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2297a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 21:
                return 30;
            case 31:
                return 90;
            case 52:
            case 61:
                return 70;
            case 72:
            case 81:
                break;
            case 91:
                if (!this.f2299c.J()) {
                    return 50;
                }
                break;
            case 111:
                return 120;
            case 121:
                return 130;
            case 131:
                return 140;
            case 141:
                return 150;
            case 151:
                return 1002;
            case 1000:
                return 20;
            default:
                return i2;
        }
        return 110;
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        c.a.b.d.e.c(f2297a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 20:
                this.f2299c.R();
                break;
            case 23:
                this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_LEVEL_NOT_ON, false);
                e(1001);
                break;
            case 30:
                this.f2299c.Q();
                break;
            case 32:
                this.f2299c.q0(null, true);
                e(1001);
                break;
            case 50:
                this.f2299c.T();
                break;
            case 51:
                if (this.f2299c.I()) {
                    this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                    e(1001);
                } else if (this.f2299c.H()) {
                    this.f2299c.n0();
                }
                break;
            case 62:
                if (this.f2299c.I()) {
                    this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                } else if (this.f2299c.H()) {
                    this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM, false);
                }
                e(1001);
                break;
            case 70:
                this.f2299c.U();
                break;
            case 71:
                this.f2299c.o0();
                break;
            case 82:
                this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING, false);
                e(1001);
                break;
            case 90:
                this.f2299c.L();
                break;
            case 92:
                this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE, false);
                e(1001);
                break;
            case 110:
                this.f2299c.v0();
                break;
            case 112:
                e(1001);
                this.f2299c.c(0);
                break;
            case 120:
                this.f2299c.P();
                break;
            case 122:
                this.f2299c.q0(null, true);
                e(1001);
                break;
            case 130:
                this.f2299c.i0();
                break;
            case 140:
                BYDAutoOtaDevice.getInstance(this.f2298b).setAndroidOtaState(1, new a());
                BYDAutoOtaDevice.getInstance(this.f2298b).setQnxOtaState(1, new b());
                k(5000L);
                break;
            case 142:
                this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_OFF_FAILURE, false);
                e(1001);
                break;
            case 150:
                this.f2299c.m0();
                break;
            case 152:
                this.f2299c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE, false);
                e(1001);
                break;
            case 1001:
                this.f2299c.V();
                break;
            case 1002:
                this.f2299c.m(true, "UpdateOnState startActualUpgrade");
                this.f2299c.t0();
                this.f2299c.w0();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }

    public final void k(long j) {
        if (this.f2300d != null) {
            return;
        }
        c.a.b.d.e.c(f2297a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.f2300d = new c(this, null);
        l.d().c(this.f2300d, j);
    }

    public final void l() {
        if (this.f2300d != null) {
            c.a.b.d.e.c(f2297a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.f2300d);
            this.f2300d = null;
        }
    }
}
