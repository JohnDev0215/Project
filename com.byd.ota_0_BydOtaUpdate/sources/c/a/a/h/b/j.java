package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;
import com.byd.common.constants.ResultCodeConsts;

/* JADX INFO: compiled from: UpdateVehicleCycleTestState.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2304a = "j";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2305b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.g.i f2306c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f2307d;

    /* JADX INFO: compiled from: UpdateVehicleCycleTestState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateCycleTestState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateVehicleCycleTestState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateCycleTestState->onQnxOtaState resultCode = " + i2);
            j.this.l();
            j.this.f2306c.n(true);
            j.this.f2306c.R();
        }
    }

    /* JADX INFO: compiled from: UpdateVehicleCycleTestState.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(j.f2304a, "PowerOffTimeoutRunnable 接口回调超时");
            j.this.e(142);
            if (j.this.f2307d != null) {
                j.this.f2307d = null;
            }
        }

        public /* synthetic */ c(j jVar, a aVar) {
            this();
        }
    }

    public j(Context context, c.a.a.g.i iVar) {
        this.f2305b = context;
        this.f2306c = iVar;
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2304a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 21:
                return 140;
            case 23:
            case 141:
                return 150;
            case 31:
                if (!this.f2306c.G()) {
                    return 160;
                }
                if (this.f2306c.J()) {
                    return 1002;
                }
                break;
            case 52:
            case 61:
                return 70;
            case 72:
            case 81:
                return 1002;
            case 91:
                return 20;
            case 111:
                return 130;
            case 131:
                return 90;
            case 151:
                return 30;
            case 161:
                return 170;
            case 171:
                return 180;
            case 181:
                return 200;
            case 201:
                if (this.f2306c.J()) {
                    return 1002;
                }
                break;
            case 1000:
                this.f2306c.m(true, "UpdateVehicleCycleTestState startActualUpgrade");
                this.f2306c.t0();
                return 110;
            default:
                return i2;
        }
        return 50;
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        c.a.b.d.e.c(f2304a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 20:
                BYDAutoOtaDevice.getInstance(this.f2305b).setAndroidOtaState(1, new a());
                BYDAutoOtaDevice.getInstance(this.f2305b).setQnxOtaState(1, new b());
                k(5000L);
                break;
            case 30:
                this.f2306c.Q();
                break;
            case 32:
                this.f2306c.q0(null, true);
                e(1001);
                break;
            case 50:
                this.f2306c.T();
                break;
            case 51:
                if (this.f2306c.I()) {
                    this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                    e(1001);
                } else if (this.f2306c.H()) {
                    this.f2306c.n0();
                }
                break;
            case 62:
                if (this.f2306c.I()) {
                    this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                } else if (this.f2306c.H()) {
                    this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM, false);
                }
                e(1001);
                break;
            case 70:
                this.f2306c.U();
                break;
            case 71:
                this.f2306c.o0();
                break;
            case 82:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING, false);
                e(1001);
                break;
            case 90:
                this.f2306c.L();
                break;
            case 92:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE, false);
                e(1001);
                break;
            case 110:
                this.f2306c.v0();
                break;
            case 112:
                e(1001);
                this.f2306c.c(0);
                break;
            case 130:
                this.f2306c.i0();
                break;
            case 140:
                this.f2306c.l0();
                break;
            case 142:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_OFF_FAILURE, false);
                e(1001);
                break;
            case 150:
                this.f2306c.m0();
                break;
            case 152:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE, false);
                e(1001);
                break;
            case 160:
                this.f2306c.k0();
                break;
            case 162:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE, false);
                e(1001);
                break;
            case 170:
                this.f2306c.O();
                break;
            case 172:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE, false);
                e(1001);
                break;
            case 180:
                this.f2306c.N();
                break;
            case 182:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_DC_REDUCE_VOLTAGE_FAILURE, false);
                e(1001);
                break;
            case 200:
                this.f2306c.D();
                break;
            case 202:
                this.f2306c.q0(ResultCodeConsts.NormalCodeEnum.FAIL_OTA_PACKAGES_NONEXISTENT, false);
                e(1001);
                break;
            case 1001:
                this.f2306c.V();
                break;
            case 1002:
                this.f2306c.m(true, "UpdateVehicleCycleTestState startActualUpgrade");
                this.f2306c.t0();
                this.f2306c.w0();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }

    public final void k(long j) {
        if (this.f2307d != null) {
            return;
        }
        c.a.b.d.e.c(f2304a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.f2307d = new c(this, null);
        l.d().c(this.f2307d, j);
    }

    public final void l() {
        if (this.f2307d != null) {
            c.a.b.d.e.c(f2304a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.f2307d);
            this.f2307d = null;
        }
    }
}
