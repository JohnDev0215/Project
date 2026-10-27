package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;
import c.a.b.d.n;
import com.byd.common.constants.ResultCodeConsts;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2262a = "f";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Timer f2263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.b.c.a f2266e = c.a.b.c.a.k();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f2267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.a.g.i f2268g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Runnable f2269h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbsBYDAutoOtaListener f2270i;
    public AbsBYDAutoBodyworkListener j;
    public Runnable k;

    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateFactoryNoSilenceState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateFactoryNoSilenceState->onQnxOtaState resultCode = " + i2);
            f.this.v();
            f.this.f2268g.n(true);
            f.this.f2268g.m0();
        }
    }

    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.a.b.d.e.c(f.f2262a, "UpdateFactoryNoSilenceState->TimerTask run, judgeState = " + f.this.f2265d);
            f.this.f2266e.w();
        }
    }

    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class d extends AbsBYDAutoBodyworkListener {
        public d() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int i2, int i3) {
            super.onDoorStateChanged(i2, i3);
            c.a.b.d.e.c(AbsBYDAutoBodyworkListener.TAG, "onDoorStateChanged->area = " + i2 + ", state = " + i3);
            if (i3 == 1) {
                f.this.x(60000L);
            }
        }

        public /* synthetic */ d(f fVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class e extends AbsBYDAutoOtaListener {
        public e() {
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void onLFDoorLockStatus(int i2) {
            super.onLFDoorLockStatus(i2);
            c.a.b.d.e.c(AbsBYDAutoOtaListener.TAG, "onLFDoorLockStatus->state = " + i2);
            if (i2 != 2 || f.this.f2269h == null) {
                return;
            }
            f.this.w();
            f.this.f2268g.s0("factoryNoSilence onLFDoorLockStatus");
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
                f.this.x(60000L);
            }
        }

        public /* synthetic */ e(f fVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: c.a.a.h.b.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class RunnableC0046f implements Runnable {
        public RunnableC0046f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2262a, "PowerOffTimeoutRunnable 接口回调超时");
            f.this.e(142);
            if (f.this.k != null) {
                f.this.k = null;
            }
        }

        public /* synthetic */ RunnableC0046f(f fVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateFactoryNoSilenceState.java */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.f2268g.s0("factoryNoSilence ScreenOffRunnable");
            if (f.this.f2269h != null) {
                f.this.f2269h = null;
            }
        }

        public /* synthetic */ g(f fVar, a aVar) {
            this();
        }
    }

    public f(Context context, c.a.a.g.i iVar) {
        this.f2267f = context;
        this.f2268g = iVar;
        q();
        u();
    }

    public final void A() {
        z();
        y();
        w();
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2262a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
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
                if (!this.f2268g.J()) {
                    return 50;
                }
                break;
            case 222:
                return 90;
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
        this.f2265d = i2;
        String str = f2262a;
        c.a.b.d.e.c(str, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        switch (i2) {
            case 20:
                this.f2268g.R();
                break;
            case 21:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_FACTORY_UPDATE_POWER_LEVEL_NOT_OFF, false);
                e(1001);
                break;
            case 30:
                this.f2268g.Q();
                break;
            case 32:
                this.f2268g.q0(null, true);
                e(1001);
                break;
            case 50:
                this.f2268g.T();
                break;
            case 51:
                if (this.f2268g.I()) {
                    int socValue = BYDAutoOtaDevice.getInstance(this.f2267f).getSocValue();
                    c.a.b.d.e.c(str, "soc = " + socValue);
                    if (socValue <= 85) {
                        this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                        e(1001);
                    } else {
                        this.f2268g.n0();
                    }
                } else if (this.f2268g.H()) {
                    this.f2268g.n0();
                }
                break;
            case 62:
                if (this.f2268g.I()) {
                    this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING, false);
                } else if (this.f2268g.H()) {
                    this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM, false);
                }
                e(1001);
                break;
            case 70:
                this.f2268g.U();
                break;
            case 71:
                this.f2268g.o0();
                break;
            case 82:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING, false);
                e(1001);
                break;
            case 90:
                this.f2268g.L();
                break;
            case 92:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE, false);
                e(1001);
                break;
            case 150:
                BYDAutoOtaDevice.getInstance(this.f2267f).setAndroidOtaState(1, new a());
                BYDAutoOtaDevice.getInstance(this.f2267f).setQnxOtaState(1, new b());
                r(5000L);
                break;
            case 152:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE, false);
                e(1001);
                break;
            case 160:
                this.f2268g.k0();
                break;
            case 162:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE, false);
                e(1001);
                break;
            case 170:
                this.f2268g.O();
                break;
            case 172:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE, false);
                e(1001);
                break;
            case 180:
                this.f2268g.N();
                break;
            case 182:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.OTA_CONDITION_UNMATCHED_DC_REDUCE_VOLTAGE_FAILURE, false);
                e(1001);
                break;
            case 200:
                this.f2268g.D();
                break;
            case 202:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_OTA_PACKAGES_NONEXISTENT, false);
                e(1001);
                break;
            case 210:
                this.f2268g.S(this.f2264c);
                break;
            case 212:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_ENTER_FACTORY_UPDATE_TIME_EXPIRED, false);
                e(1001);
                break;
            case 220:
                this.f2268g.K();
                break;
            case 221:
                this.f2268g.q0(ResultCodeConsts.NormalCodeEnum.FAIL_FACTORY_UPDATE_SCREEN_ON, false);
                e(1001);
                break;
            case 1001:
                Timer timer = this.f2263b;
                if (timer != null) {
                    timer.cancel();
                    this.f2263b = null;
                }
                if (this.f2268g.k()) {
                    this.f2268g.s0("factoryNoSilence STATE_JUDGE_QUIT");
                }
                A();
                this.f2268g.V();
                break;
            case 1002:
                this.f2268g.m(true, "UpdateFactoryNoSilenceState startActualUpgrade");
                this.f2268g.w0();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }

    public final void q() {
        int iO = this.f2266e.o();
        if (iO > 0) {
            Calendar calendar = Calendar.getInstance();
            String str = f2262a;
            c.a.b.d.e.c(str, "TimerTask before = " + n.d("yyyy-MM-dd HH:mm:ss", calendar.getTimeInMillis()));
            calendar.add(12, iO);
            c.a.b.d.e.c(str, "TimerTask after = " + n.d("yyyy-MM-dd HH:mm:ss", calendar.getTimeInMillis()));
            this.f2264c = calendar.getTimeInMillis();
            Timer timer = this.f2263b;
            if (timer != null) {
                timer.cancel();
                this.f2263b = null;
            }
            Timer timer2 = new Timer("UpdateFactoryNoSilenceState");
            this.f2263b = timer2;
            timer2.schedule(new c(), new Date(this.f2264c));
        }
    }

    public final void r(long j) {
        if (this.k != null) {
            return;
        }
        c.a.b.d.e.c(f2262a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.k = new RunnableC0046f(this, null);
        l.d().c(this.k, j);
    }

    public final void s() {
        if (this.j == null) {
            c.a.b.d.e.c(f2262a, "registerBYDAutoBodyworkListener");
            this.j = new d(this, null);
            BYDAutoBodyworkDevice.getInstance(this.f2267f).registerListener(this.j);
        }
    }

    public final void t() {
        if (this.f2270i == null) {
            c.a.b.d.e.c(f2262a, "registerBYDAutoOtaListener");
            this.f2270i = new e(this, null);
            BYDAutoOtaDevice.getInstance(this.f2267f).registerListener(this.f2270i);
        }
    }

    public final void u() {
        t();
        s();
    }

    public final void v() {
        if (this.k != null) {
            c.a.b.d.e.c(f2262a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.k);
            this.k = null;
        }
    }

    public final void w() {
        if (this.f2269h != null) {
            l.d().i(this.f2269h);
            this.f2269h = null;
        }
    }

    public final void x(long j) {
        if (this.f2269h != null) {
            return;
        }
        this.f2268g.t0();
        this.f2269h = new g(this, null);
        l.d().c(this.f2269h, j);
    }

    public final void y() {
        if (this.j != null) {
            c.a.b.d.e.c(f2262a, "unregisterBYDAutoBodyworkListener");
            BYDAutoBodyworkDevice.getInstance(this.f2267f).unregisterListener(this.j);
            this.j = null;
        }
    }

    public final void z() {
        if (this.f2270i != null) {
            c.a.b.d.e.c(f2262a, "unregisterBYDAutoOtaListener");
            BYDAutoOtaDevice.getInstance(this.f2267f).unregisterListener(this.f2270i);
            this.f2270i = null;
        }
    }
}
