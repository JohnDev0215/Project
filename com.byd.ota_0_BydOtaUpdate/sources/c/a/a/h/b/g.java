package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;
import c.a.b.d.n;
import com.byd.common.constants.ResultCodeConsts;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: UpdateFactorySilenceState.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2278a = "g";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Timer f2279b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2280c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2281d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.b.c.a f2282e = c.a.b.c.a.k();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f2283f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.a.g.i f2284g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Runnable f2285h;

    /* JADX INFO: compiled from: UpdateFactorySilenceState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateFactorySilenceState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateFactorySilenceState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateFactorySilenceState->onQnxOtaState resultCode = " + i2);
            g.this.n();
            g.this.f2284g.n(true);
            g.this.f2284g.m0();
        }
    }

    /* JADX INFO: compiled from: UpdateFactorySilenceState.java */
    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.a.b.d.e.c(g.f2278a, "UpdateFactorySilenceState->TimerTask run, judgeState = " + g.this.f2281d);
        }
    }

    /* JADX INFO: compiled from: UpdateFactorySilenceState.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(g.f2278a, "PowerOffTimeoutRunnable 接口回调超时");
            g.this.e(142);
            if (g.this.f2285h != null) {
                g.this.f2285h = null;
            }
        }

        public /* synthetic */ d(g gVar, a aVar) {
            this();
        }
    }

    public g(Context context, c.a.a.g.i iVar) {
        this.f2283f = context;
        this.f2284g = iVar;
        l();
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2278a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 11:
                return 90;
            case 23:
                return 220;
            case 31:
                return 160;
            case 52:
            case 61:
                return 70;
            case 72:
            case 81:
                break;
            case 91:
                return 150;
            case 151:
                return 30;
            case 161:
                return 170;
            case 171:
                return 180;
            case 181:
                return 200;
            case 201:
                return 210;
            case 211:
                if (!this.f2284g.J()) {
                    return 50;
                }
                break;
            case 222:
                return 10;
            case 1000:
                return 20;
            default:
                return i2;
        }
        return 1002;
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        this.f2281d = i2;
        c.a.b.d.e.c(f2278a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 10:
                this.f2284g.M();
                break;
            case 12:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CAR_UNLOCK, false);
                e(1001);
                break;
            case 20:
                this.f2284g.R();
                break;
            case 21:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_FACTORY_UPDATE_POWER_LEVEL_NOT_OFF, false);
                e(1001);
                break;
            case 30:
                this.f2284g.Q();
                break;
            case 32:
                this.f2284g.q0(null, true);
                e(1001);
                break;
            case 50:
                this.f2284g.T();
                break;
            case 51:
                this.f2284g.n0();
                break;
            case 62:
                if (this.f2284g.I()) {
                    this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                } else if (this.f2284g.H()) {
                    this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM, false);
                }
                e(1001);
                break;
            case 70:
                this.f2284g.U();
                break;
            case 71:
                this.f2284g.o0();
                break;
            case 82:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING, false);
                e(1001);
                break;
            case 90:
                this.f2284g.L();
                break;
            case 92:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE, false);
                e(1001);
                break;
            case 150:
                BYDAutoOtaDevice.getInstance(this.f2283f).setAndroidOtaState(1, new a());
                BYDAutoOtaDevice.getInstance(this.f2283f).setQnxOtaState(1, new b());
                m(5000L);
                break;
            case 152:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE, false);
                e(1001);
                break;
            case 160:
                this.f2284g.k0();
                break;
            case 162:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE, false);
                e(1001);
                break;
            case 170:
                this.f2284g.O();
                break;
            case 172:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE, false);
                e(1001);
                break;
            case 180:
                this.f2284g.N();
                break;
            case 182:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_DC_REDUCE_VOLTAGE_FAILURE, false);
                e(1001);
                break;
            case 200:
                this.f2284g.D();
                break;
            case 202:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_OTA_PACKAGES_NONEXISTENT, false);
                e(1001);
                break;
            case 210:
                this.f2284g.S(this.f2280c);
                break;
            case 212:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_FACTORY_UPDATE_TIME_EXPIRED, false);
                e(1001);
                break;
            case 220:
                this.f2284g.K();
                break;
            case 221:
                this.f2284g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_FACTORY_UPDATE_SCREEN_ON, false);
                e(1001);
                break;
            case 1001:
                Timer timer = this.f2279b;
                if (timer != null) {
                    timer.cancel();
                    this.f2279b = null;
                }
                this.f2284g.V();
                break;
            case 1002:
                this.f2284g.w0();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }

    public final void l() {
        int iO = this.f2282e.o();
        if (iO > 0) {
            Calendar calendar = Calendar.getInstance();
            String str = f2278a;
            c.a.b.d.e.c(str, "TimerTask before = " + n.d("yyyy-MM-dd HH:mm:ss", calendar.getTimeInMillis()));
            calendar.add(12, iO);
            c.a.b.d.e.c(str, "TimerTask after = " + n.d("yyyy-MM-dd HH:mm:ss", calendar.getTimeInMillis()));
            this.f2280c = calendar.getTimeInMillis();
            Timer timer = this.f2279b;
            if (timer != null) {
                timer.cancel();
                this.f2279b = null;
            }
            Timer timer2 = new Timer("UpdateFactorySilenceState");
            this.f2279b = timer2;
            timer2.schedule(new c(), new Date(this.f2280c));
        }
    }

    public final void m(long j) {
        if (this.f2285h != null) {
            return;
        }
        c.a.b.d.e.c(f2278a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.f2285h = new d(this, null);
        l.d().c(this.f2285h, j);
    }

    public final void n() {
        if (this.f2285h != null) {
            c.a.b.d.e.c(f2278a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.f2285h);
            this.f2285h = null;
        }
    }
}
