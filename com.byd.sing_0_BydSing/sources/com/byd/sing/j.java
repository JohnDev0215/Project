package com.byd.sing;

import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.util.Log;

/* JADX INFO: compiled from: SingContext.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile j f3251a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f3254d;
    private int e;
    private int[] k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean q;
    private int i = 1;
    private boolean j = false;
    private int p = 196;
    private boolean r = false;
    private boolean s = true;
    private e o = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BYDAutoAudioDevice f3252b = BYDAutoAudioDevice.getInstance(SingApplication.a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BYDAutoGearboxDevice f3253c = BYDAutoGearboxDevice.getInstance(SingApplication.a());
    private int f = i.d("key_remix", 4);
    private int g = i.d("key_mic", 8);
    private int h = i.d("key_sound", 1);

    private j() {
        d();
        n();
    }

    private void d() {
        int iB = b.a.a.a(SingApplication.a()).b();
        Log.w("Sing", "uiConfig = " + iB);
        if (iB == 0) {
            this.s = true;
        } else if (iB == 1) {
            this.s = false;
        } else if (iB == 2) {
            this.s = false;
        }
    }

    public static j f() {
        if (f3251a == null) {
            synchronized (j.class) {
                if (f3251a == null) {
                    f3251a = new j();
                }
            }
        }
        return f3251a;
    }

    private void n() {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", -1);
        Log.w("Sing", "isRightRudder steer = " + i);
        if (1 == i) {
            this.r = true;
        } else {
            this.r = false;
        }
    }

    public void A() {
        a.a("reShowAll");
        for (String str : this.o.d().keySet()) {
            a.a("key = " + str);
            this.o.e(str).m();
        }
    }

    public void B() {
        for (String str : this.o.d().keySet()) {
            a.a("key = " + str);
            com.byd.sing.view.a.g gVarE = this.o.e(str);
            if (gVarE instanceof com.byd.sing.view.a.c) {
                ((com.byd.sing.view.a.c) gVarE).I();
            }
        }
    }

    public void C() {
        i.f("key_agree_notice", true);
        this.f3254d = Boolean.TRUE;
    }

    public void D(boolean z) {
        if (!z) {
            U();
            return;
        }
        if (k.e()) {
            T();
        } else {
            b();
        }
        z();
    }

    public void E(int i, int i2) {
        if (this.k == null) {
            this.k = new int[2];
        }
        int[] iArr = this.k;
        iArr[0] = i;
        iArr[1] = i2;
    }

    public void F(int[] iArr) {
        this.k = iArr;
    }

    public void G(boolean z) {
        this.l = z;
    }

    public void H(boolean z) {
        this.m = z;
    }

    public void I(boolean z) {
        this.n = z;
    }

    public void J(int i) {
        this.p = i;
    }

    public void K() {
        a.a("setOnConnected");
        I(true);
        g.e(this.g);
        g.f(this.f);
        g.c(this.h);
    }

    public void L() {
        a.a("setOnConnected ts");
        H(true);
        m.e(this.g);
        m.f(this.f);
        m.c(this.h);
    }

    public void M(boolean z) {
        this.j = z;
    }

    public void N(int i) {
        this.i = i;
    }

    public void O(int i) {
        if (v()) {
            return;
        }
        B();
        if (i < 0 || i > 4) {
            return;
        }
        this.h = i;
        i.h("key_sound", i);
        if (this.n) {
            g.c(this.h);
        } else if (this.m) {
            m.c(this.h);
        } else {
            a.n("no mic connected");
        }
    }

    public void P(int i) {
        if (v()) {
            return;
        }
        B();
        if (i < 0 || i > 10) {
            return;
        }
        this.g = i;
        i.h("key_mic", i);
        if (this.n) {
            g.e(this.g);
        } else if (this.m) {
            m.e(this.g);
        } else {
            a.n("no mic connected");
        }
    }

    public void Q(int i) {
        if (v()) {
            return;
        }
        B();
        if (i < 0 || i > 10) {
            return;
        }
        this.f = i;
        i.h("key_remix", i);
        if (this.n) {
            g.f(this.f);
        } else if (this.m) {
            m.f(this.f);
        } else {
            a.n("no mic connected");
        }
    }

    public void R(int i) {
        B();
        if (i < 0 || i > 39) {
            return;
        }
        this.e = i;
        this.f3252b.setVolume(i);
    }

    public void S(com.byd.sing.view.a.g gVar) {
        String simpleName = gVar.getClass().getSimpleName();
        a.a("show " + simpleName);
        com.byd.sing.view.a.g gVarE = this.o.e(simpleName);
        if (gVarE != null) {
            gVarE.a();
        }
        this.o.a(simpleName, gVar);
        gVar.p();
    }

    public void T() {
        a.a("showMinimized");
        String simpleName = com.byd.sing.view.a.h.class.getSimpleName();
        this.o.c(simpleName);
        if (this.o.f(simpleName)) {
            return;
        }
        com.byd.sing.view.a.h hVar = new com.byd.sing.view.a.h();
        this.o.a(simpleName, hVar);
        hVar.p();
    }

    public void U() {
        if (f().r()) {
            a.a("mic unMute  muteFlag:" + this.q);
            if (this.q) {
                int iK = k();
                g.e(iK);
                m.e(iK);
                this.q = false;
            }
        }
    }

    public void a(com.byd.sing.view.a.g gVar) {
        String simpleName = gVar.getClass().getSimpleName();
        a.a("dismiss " + simpleName);
        this.o.g(simpleName);
    }

    public void b() {
        a.a("dismissAll");
        this.o.b();
    }

    public void c() {
        if (k.e()) {
            T();
        } else {
            b();
        }
    }

    public com.byd.sing.view.a.g e(String str) {
        return this.o.e(str);
    }

    public int[] g() {
        return this.k;
    }

    public int h() {
        a.a("getModel model:" + this.p);
        return this.p;
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        a.a("mVolumeMic = " + this.g);
        return this.g;
    }

    public int l() {
        a.a("mVolumeRemix = " + this.f);
        return this.f;
    }

    public int m() {
        return this.f3252b.getVolume();
    }

    public boolean o() {
        if (this.f3254d == null) {
            this.f3254d = Boolean.valueOf(i.b("key_agree_notice", false));
        }
        return this.f3254d.booleanValue();
    }

    public boolean p() {
        return y();
    }

    public boolean q() {
        return this.s;
    }

    public boolean r() {
        return this.l;
    }

    public boolean s() {
        return this.m;
    }

    public boolean t() {
        return this.n;
    }

    public boolean u() {
        return this.j;
    }

    public boolean v() {
        try {
            int gear = this.f3253c.getGear();
            a.a("gearbox = " + gear);
            return gear == 2;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean w() {
        return this.r;
    }

    public boolean x(String str) {
        return this.o.f(str);
    }

    public boolean y() {
        TelephonyManager telephonyManager = (TelephonyManager) SingApplication.a().getSystemService("phone");
        boolean z = true;
        if (2 != telephonyManager.getCallState() && 1 != telephonyManager.getCallState()) {
            z = false;
        }
        a.a("isCalling = " + z);
        return z;
    }

    public void z() {
        if (f().r()) {
            a.a("mic mute");
            this.q = true;
            g.e(0);
            m.e(0);
        }
    }
}
