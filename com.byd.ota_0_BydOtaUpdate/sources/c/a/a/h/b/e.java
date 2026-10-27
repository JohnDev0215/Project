package c.a.a.h.b;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import c.a.b.d.l;

/* JADX INFO: compiled from: UpdateDirectState.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends c.a.a.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2247a = "e";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.a.g.i f2250d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2251e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Runnable f2252f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Runnable f2253g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AbsBYDAutoOtaListener f2254h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbsBYDAutoBodyworkListener f2255i;

    /* JADX INFO: compiled from: UpdateDirectState.java */
    public class a extends AbsMessageCallback {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAndroidOtaState(int i2) {
            super.onAndroidOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateDirectState->onAndroidOtaState resultCode = " + i2);
        }
    }

    /* JADX INFO: compiled from: UpdateDirectState.java */
    public class b extends AbsMessageCallback {
        public b() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onQnxOtaState(int i2) {
            super.onQnxOtaState(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "UpdateDirectState->onQnxOtaState resultCode = " + i2);
            e.this.s();
            e.this.f2250d.n(true);
            e.this.f2250d.A();
        }
    }

    /* JADX INFO: compiled from: UpdateDirectState.java */
    public class c extends AbsBYDAutoBodyworkListener {
        public c() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int i2, int i3) {
            super.onDoorStateChanged(i2, i3);
            c.a.b.d.e.c(AbsBYDAutoBodyworkListener.TAG, "onDoorStateChanged->area = " + i2 + ", state = " + i3);
            if (i3 == 1) {
                e.this.u(60000L);
            }
        }

        public /* synthetic */ c(e eVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateDirectState.java */
    public class d extends AbsBYDAutoOtaListener {
        public d() {
        }

        @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
        public void onLFDoorLockStatus(int i2) {
            super.onLFDoorLockStatus(i2);
            c.a.b.d.e.c(AbsBYDAutoOtaListener.TAG, "onLFDoorLockStatus->state = " + i2);
            if (i2 != 2 || e.this.f2252f == null) {
                return;
            }
            e.this.t();
            e.this.f2250d.s0("UpdateDirectState onLFDoorLockStatus");
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
                e.this.u(60000L);
            }
        }

        public /* synthetic */ d(e eVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: c.a.a.h.b.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: UpdateDirectState.java */
    public class RunnableC0045e implements Runnable {
        public RunnableC0045e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.e(142);
            c.a.b.d.e.b(e.f2247a, "PowerOffTimeoutRunnable 接口回调超时");
            if (e.this.f2253g != null) {
                e.this.f2253g = null;
            }
        }

        public /* synthetic */ RunnableC0045e(e eVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: UpdateDirectState.java */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.f2250d.s0("UpdateDirectState ScreenOffRunnable");
            if (e.this.f2252f != null) {
                e.this.f2252f = null;
            }
        }

        public /* synthetic */ f(e eVar, a aVar) {
            this();
        }
    }

    public e(Context context, int i2, c.a.a.g.i iVar, boolean z) {
        this.f2248b = context;
        this.f2249c = i2;
        this.f2250d = iVar;
        this.f2251e = z;
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3 || i2 == 5) {
                r();
                return;
            } else if (i2 != 6 && i2 != 21) {
                return;
            }
        }
        if (iVar.k()) {
            iVar.m(true, "UpdateDirectState updateMode is on or off");
            iVar.t0();
        }
    }

    @Override // c.a.a.h.a.a
    public int b(int i2) {
        c.a.b.d.e.c(f2247a, "curJudgeState = " + i2 + ", msg = " + a(i2));
        if (i2 == 231) {
            return 240;
        }
        if (i2 == 241) {
            return 250;
        }
        if (i2 == 251) {
            return 1005;
        }
        if (i2 != 1003) {
            return i2;
        }
        return 230;
    }

    @Override // c.a.a.h.a.a
    public void c(c.a.a.h.a.b bVar) {
        int i2 = bVar.f2227a;
        c.a.b.d.e.c(f2247a, "nextJudgeState = " + i2 + ", msg = " + a(i2));
        if (i2 == 230) {
            this.f2250d.y();
        }
        if (i2 == 240) {
            BYDAutoOtaDevice.getInstance(this.f2248b).setAndroidOtaState(1, new a());
            BYDAutoOtaDevice.getInstance(this.f2248b).setQnxOtaState(1, new b());
            o(5000L);
            return;
        }
        if (i2 == 250) {
            this.f2250d.B();
            return;
        }
        switch (i2) {
            case 1004:
                int i3 = this.f2249c;
                if (i3 == 3 || i3 == 5) {
                    x();
                }
                if (this.f2249c != 4) {
                    this.f2250d.m(true, "UpdateDirectState startActualUpgrade");
                }
                this.f2250d.z();
                break;
            case 1005:
                this.f2250d.C(this.f2251e);
                break;
            case 1006:
                this.f2250d.F();
                break;
        }
    }

    @Override // c.a.a.h.a.a
    public void d(int i2) {
    }

    public final void o(long j) {
        if (this.f2253g != null) {
            return;
        }
        c.a.b.d.e.c(f2247a, "powerOffTimeoutDelayed 开启退电接口回调定时");
        this.f2253g = new RunnableC0045e(this, null);
        l.d().c(this.f2253g, j);
    }

    public final void p() {
        if (this.f2255i == null) {
            c.a.b.d.e.c(f2247a, "registerBYDAutoBodyworkListener");
            this.f2255i = new c(this, null);
            BYDAutoBodyworkDevice.getInstance(this.f2248b).registerListener(this.f2255i);
        }
    }

    public final void q() {
        if (this.f2254h == null) {
            c.a.b.d.e.c(f2247a, "registerBYDAutoOtaListener");
            this.f2254h = new d(this, null);
            BYDAutoOtaDevice.getInstance(this.f2248b).registerListener(this.f2254h);
        }
    }

    public final void r() {
        q();
        p();
    }

    public final void s() {
        if (this.f2253g != null) {
            c.a.b.d.e.c(f2247a, "powerOffTimeoutDelayed 移除退电接口回调定时");
            l.d().i(this.f2253g);
            this.f2253g = null;
        }
    }

    public final void t() {
        if (this.f2252f != null) {
            l.d().i(this.f2252f);
            this.f2252f = null;
        }
    }

    public final void u(long j) {
        if (this.f2252f != null) {
            return;
        }
        this.f2250d.t0();
        this.f2252f = new f(this, null);
        l.d().c(this.f2252f, j);
    }

    public final void v() {
        if (this.f2255i != null) {
            c.a.b.d.e.c(f2247a, "unregisterBYDAutoBodyworkListener");
            BYDAutoBodyworkDevice.getInstance(this.f2248b).unregisterListener(this.f2255i);
            this.f2255i = null;
        }
    }

    public final void w() {
        if (this.f2254h != null) {
            c.a.b.d.e.c(f2247a, "unregisterBYDAutoOtaListener");
            BYDAutoOtaDevice.getInstance(this.f2248b).unregisterListener(this.f2254h);
            this.f2254h = null;
        }
    }

    public final void x() {
        w();
        v();
        t();
    }
}
