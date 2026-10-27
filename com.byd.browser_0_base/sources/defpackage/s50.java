package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.v30;
import defpackage.w20;
import defpackage.xn2;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.webapps.WebApkUpdateManager;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.chrome.browser.webapps.e;

/* JADX INFO: compiled from: DaggerChromeAppComponent.java */
/* JADX INFO: loaded from: classes.dex */
public final class s50 implements mp {
    public final op a;
    public final a8 b;
    public volatile c c;
    public volatile jg3 d;
    public volatile c g;
    public volatile c o;
    public volatile c q;
    public volatile c s;
    public volatile Object e = new pe1();
    public volatile Object f = new pe1();
    public volatile Object h = new pe1();
    public volatile Object i = new pe1();
    public volatile Object j = new pe1();
    public volatile Object k = new pe1();
    public volatile Object l = new pe1();
    public volatile Object m = new pe1();
    public volatile Object n = new pe1();
    public volatile Object p = new pe1();
    public volatile Object r = new pe1();

    /* JADX INFO: compiled from: DaggerChromeAppComponent.java */
    public final class a implements jd {
        public volatile C0167a A;
        public volatile C0167a A0;
        public volatile C0167a B;
        public volatile C0167a C0;
        public volatile C0167a D0;
        public volatile C0167a G;
        public volatile C0167a G0;
        public volatile kp3 H;
        public volatile C0167a K;
        public volatile C0167a M;
        public volatile C0167a N;
        public volatile C0167a Y;
        public final hp a;
        public volatile C0167a a0;
        public final kd b;
        public volatile a8 b0;
        public volatile C0167a e0;
        public volatile C0167a g;
        public volatile C0167a g0;
        public volatile w20.b i;
        public volatile C0167a j0;
        public volatile C0167a k;
        public volatile C0167a l;
        public volatile pq3 m;
        public volatile C0167a o;
        public volatile C0167a s;
        public volatile C0167a s0;
        public volatile C0167a v0;
        public volatile C0167a x;
        public volatile C0167a x0;
        public volatile Object c = new pe1();
        public volatile Object d = new pe1();
        public volatile Object e = new pe1();
        public volatile Object f = new pe1();
        public volatile Object h = new pe1();
        public volatile Object j = new pe1();
        public volatile Object n = new pe1();
        public volatile Object p = new pe1();
        public volatile Object q = new pe1();
        public volatile Object r = new pe1();
        public volatile Object t = new pe1();
        public volatile Object u = new pe1();
        public volatile Object v = new pe1();
        public volatile Object w = new pe1();
        public volatile Object y = new pe1();
        public volatile Object z = new pe1();
        public volatile Object C = new pe1();
        public volatile Object D = new pe1();
        public volatile Object E = new pe1();
        public volatile Object F = new pe1();
        public volatile Object I = new pe1();

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public volatile Object f95J = new pe1();
        public volatile Object L = new pe1();
        public volatile Object O = new pe1();
        public volatile Object P = new pe1();
        public volatile Object Q = new pe1();
        public volatile Object R = new pe1();
        public volatile Object S = new pe1();
        public volatile Object T = new pe1();
        public volatile Object U = new pe1();
        public volatile Object V = new pe1();
        public volatile Object W = new pe1();
        public volatile Object X = new pe1();
        public volatile Object Z = new pe1();
        public volatile Object c0 = new pe1();
        public volatile Object d0 = new pe1();
        public volatile Object f0 = new pe1();
        public volatile Object h0 = new pe1();
        public volatile Object i0 = new pe1();
        public volatile Object k0 = new pe1();
        public volatile Object l0 = new pe1();
        public volatile Object m0 = new pe1();
        public volatile Object n0 = new pe1();
        public volatile Object o0 = new pe1();
        public volatile Object p0 = new pe1();
        public volatile Object q0 = new pe1();
        public volatile Object r0 = new pe1();
        public volatile Object t0 = new pe1();
        public volatile Object u0 = new pe1();
        public volatile Object w0 = new pe1();
        public volatile Object y0 = new pe1();
        public volatile Object z0 = new pe1();
        public volatile Object B0 = new pe1();
        public volatile Object E0 = new pe1();
        public volatile Object F0 = new pe1();
        public volatile Object H0 = new pe1();
        public volatile Object I0 = new pe1();
        public volatile Object J0 = new pe1();
        public volatile Object K0 = new pe1();

        /* JADX INFO: renamed from: s50$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DaggerChromeAppComponent.java */
        public final class C0167a<T> implements c72<T> {
            public final int e;

            public C0167a(int i) {
                this.e = i;
            }

            @Override // defpackage.c72
            public final T get() {
                Object b40Var;
                Object ephemeralTabCoordinator;
                Object xg3Var;
                Object gq3Var;
                switch (this.e) {
                    case 0:
                        a aVar = a.this;
                        Object obj = aVar.f;
                        if (obj instanceof pe1) {
                            synchronized (obj) {
                                b40Var = aVar.f;
                                if (b40Var instanceof pe1) {
                                    Context contextV = z7.V(s50.this.a);
                                    oj ojVar = aVar.b.a;
                                    a8.n(ojVar);
                                    b40Var = new b40(contextV, ojVar, n3.W0());
                                    sb0.b(aVar.f, b40Var);
                                    aVar.f = b40Var;
                                }
                                break;
                            }
                            obj = b40Var;
                        }
                        return (T) ((b40) obj);
                    case 1:
                        return (T) a.this.I();
                    case 2:
                        T t = (T) ((CompositorViewHolder) a.this.a.l.get());
                        a8.n(t);
                        return t;
                    case 3:
                        return (T) a.this.B();
                    case 4:
                        T t2 = (T) a.this.a.k;
                        a8.n(t2);
                        return t2;
                    case 5:
                        T t3 = (T) a.this.a.d;
                        a8.n(t3);
                        return t3;
                    case 6:
                        a aVar2 = a.this;
                        Object obj2 = aVar2.z;
                        if (obj2 instanceof pe1) {
                            synchronized (obj2) {
                                ephemeralTabCoordinator = aVar2.z;
                                if (ephemeralTabCoordinator instanceof pe1) {
                                    AppCompatActivity appCompatActivity = aVar2.a.a;
                                    a8.n(appCompatActivity);
                                    g3 g3Var = aVar2.a.k;
                                    a8.n(g3Var);
                                    View decorView = aVar2.a.a.getWindow().getDecorView();
                                    a8.n(decorView);
                                    z2 z2Var = aVar2.a.j;
                                    a8.n(z2Var);
                                    nw2<fz2> nw2Var = aVar2.a.n;
                                    a8.n(nw2Var);
                                    ji jiVar = aVar2.a.b.get();
                                    a8.n(jiVar);
                                    ephemeralTabCoordinator = new EphemeralTabCoordinator(appCompatActivity, g3Var, decorView, z2Var, nw2Var, jiVar, !aVar2.a.o.get().booleanValue());
                                    sb0.b(aVar2.z, ephemeralTabCoordinator);
                                    aVar2.z = ephemeralTabCoordinator;
                                }
                                break;
                            }
                            obj2 = ephemeralTabCoordinator;
                        }
                        return (T) ((EphemeralTabCoordinator) obj2);
                    case 7:
                        T t4 = (T) ((wp2) a.this.a.i.get());
                        a8.n(t4);
                        return t4;
                    case 8:
                        return (T) a.this.u();
                    case 9:
                        a aVar3 = a.this;
                        Object obj3 = aVar3.f95J;
                        if (obj3 instanceof pe1) {
                            synchronized (obj3) {
                                xg3Var = aVar3.f95J;
                                if (xg3Var instanceof pe1) {
                                    xg3Var = new xg3(aVar3.u(), aVar3.Y());
                                    sb0.b(aVar3.f95J, xg3Var);
                                    aVar3.f95J = xg3Var;
                                }
                                break;
                            }
                            obj3 = xg3Var;
                        }
                        return (T) ((xg3) obj3);
                    case 10:
                        return (T) a.this.N();
                    case 11:
                        return (T) a.this.a();
                    case 12:
                        a aVar4 = a.this;
                        Object obj4 = aVar4.X;
                        if (obj4 instanceof pe1) {
                            synchronized (obj4) {
                                gq3Var = aVar4.X;
                                if (gq3Var instanceof pe1) {
                                    oj ojVar2 = aVar4.b.a;
                                    a8.n(ojVar2);
                                    gq3Var = new gq3(ojVar2);
                                    sb0.b(aVar4.X, gq3Var);
                                    aVar4.X = gq3Var;
                                }
                                break;
                            }
                            obj4 = gq3Var;
                        }
                        return (T) ((gq3) obj4);
                    case 13:
                        return (T) a.c(a.this);
                    case 14:
                        return (T) a.h(a.this);
                    case 15:
                        return (T) a.e(a.this);
                    case 16:
                        return (T) a.f(a.this);
                    case 17:
                        T t5 = (T) ((org.chromium.chrome.browser.metrics.a) a.this.a.s.get());
                        a8.n(t5);
                        return t5;
                    case 18:
                        return (T) a.i(a.this);
                    case 19:
                        return (T) a.g(a.this);
                    case 20:
                        return (T) a.this.v();
                    case 21:
                        return (T) a.d(a.this);
                    case 22:
                        a aVar5 = a.this;
                        Context contextV2 = z7.V(s50.this.a);
                        hp hpVar = aVar5.a;
                        Resources resources = hpVar.a.getResources();
                        a8.n(resources);
                        so1 so1Var = hpVar.q.get();
                        a8.n(so1Var);
                        ag3 ag3VarW = aVar5.W();
                        q2 q2Var = hpVar.h;
                        a8.n(q2Var);
                        return (T) new qa0(contextV2, resources, so1Var, ag3VarW, q2Var);
                    case 23:
                        a aVar6 = a.this;
                        sq2 sq2VarG = aVar6.G();
                        hp hpVar2 = aVar6.a;
                        AppCompatActivity appCompatActivity2 = hpVar2.a;
                        a8.n(appCompatActivity2);
                        a8.n(hpVar2.k);
                        uq2 uq2VarA = s50.a(s50.this);
                        oj ojVar3 = aVar6.b.a;
                        a8.n(ojVar3);
                        return (T) new ah3(sq2VarG, appCompatActivity2, uq2VarA, ojVar3);
                    default:
                        throw new AssertionError(this.e);
                }
            }
        }

        public a(hp hpVar, kd kdVar) {
            this.a = hpVar;
            this.b = kdVar;
        }

        public static x3 c(a aVar) {
            Object x3Var;
            Object obj = aVar.Z;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    x3Var = aVar.Z;
                    if (x3Var instanceof pe1) {
                        oj ojVar = aVar.b.a;
                        a8.n(ojVar);
                        x3Var = new x3(ojVar);
                        sb0.b(aVar.Z, x3Var);
                        aVar.Z = x3Var;
                    }
                }
                obj = x3Var;
            }
            return (x3) obj;
        }

        public static ra0 d(a aVar) {
            Object obj;
            Object obj2 = aVar.B0;
            if (obj2 instanceof pe1) {
                synchronized (obj2) {
                    obj = aVar.B0;
                    if (obj instanceof pe1) {
                        Resources resources = aVar.a.a.getResources();
                        a8.n(resources);
                        C0167a c0167a = aVar.B;
                        if (c0167a == null) {
                            c0167a = aVar.new C0167a(7);
                            aVar.B = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        ag3 ag3VarW = aVar.W();
                        q2 q2Var = aVar.a.h;
                        a8.n(q2Var);
                        ra0 ra0Var = new ra0(resources, p61VarA, ag3VarW, q2Var);
                        sb0.b(aVar.B0, ra0Var);
                        aVar.B0 = ra0Var;
                        obj = ra0Var;
                    }
                }
                obj2 = obj;
            }
            return (ra0) obj2;
        }

        public static oh0 e(a aVar) {
            Object oh0Var;
            Object obj = aVar.f0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    oh0Var = aVar.f0;
                    if (oh0Var instanceof pe1) {
                        oh0Var = new oh0();
                        sb0.b(aVar.f0, oh0Var);
                        aVar.f0 = oh0Var;
                    }
                }
                obj = oh0Var;
            }
            return (oh0) obj;
        }

        public static lx0 f(a aVar) {
            Object lx0Var;
            Object obj = aVar.i0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    lx0Var = aVar.i0;
                    if (lx0Var instanceof pe1) {
                        q2 q2Var = aVar.a.h;
                        a8.n(q2Var);
                        AppCompatActivity appCompatActivity = aVar.a.a;
                        a8.n(appCompatActivity);
                        g3 g3Var = aVar.a.k;
                        a8.n(g3Var);
                        lx0Var = new lx0(q2Var, appCompatActivity, g3Var);
                        sb0.b(aVar.i0, lx0Var);
                        aVar.i0 = lx0Var;
                    }
                }
                obj = lx0Var;
            }
            return (lx0) obj;
        }

        public static y01 g(a aVar) {
            Object obj;
            Object obj2 = aVar.w0;
            if (obj2 instanceof pe1) {
                synchronized (obj2) {
                    obj = aVar.w0;
                    if (obj instanceof pe1) {
                        Context contextV = z7.V(s50.this.a);
                        aVar.b.getClass();
                        y01 y01Var = new y01(contextV, new z01());
                        sb0.b(aVar.w0, y01Var);
                        aVar.w0 = y01Var;
                        obj = y01Var;
                    }
                }
                obj2 = obj;
            }
            return (y01) obj2;
        }

        public static ch3 h(a aVar) {
            Object ch3Var;
            Object obj = aVar.d0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    ch3Var = aVar.d0;
                    if (ch3Var instanceof pe1) {
                        q2 q2Var = aVar.a.h;
                        a8.n(q2Var);
                        oj ojVar = aVar.b.a;
                        a8.n(ojVar);
                        a8 a8Var = aVar.b0;
                        if (a8Var == null) {
                            aVar.b.getClass();
                            a8Var = new a8();
                            aVar.b0 = a8Var;
                        }
                        a30 a30VarM = aVar.M();
                        eu euVarK = aVar.k();
                        s50.this.b.getClass();
                        zi0 zi0Var = zi0.b;
                        a8.n(zi0Var);
                        ch3Var = new ch3(q2Var, ojVar, a8Var, a30VarM, euVarK, zi0Var);
                        sb0.b(aVar.d0, ch3Var);
                        aVar.d0 = ch3Var;
                    }
                }
                obj = ch3Var;
            }
            return (ch3) obj;
        }

        public static WebApkUpdateManager i(a aVar) {
            Object webApkUpdateManager;
            Object obj = aVar.u0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    webApkUpdateManager = aVar.u0;
                    if (webApkUpdateManager instanceof pe1) {
                        z2 z2Var = aVar.a.j;
                        a8.n(z2Var);
                        q2 q2Var = aVar.a.h;
                        a8.n(q2Var);
                        webApkUpdateManager = new WebApkUpdateManager(z2Var, q2Var);
                        sb0.b(aVar.u0, webApkUpdateManager);
                        aVar.u0 = webApkUpdateManager;
                    }
                }
                obj = webApkUpdateManager;
            }
            return (WebApkUpdateManager) obj;
        }

        public final h20 A() {
            Object h20Var;
            Object obj = this.c;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    h20Var = this.c;
                    if (h20Var instanceof pe1) {
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        o23 o23VarK = K();
                        a30 a30VarM = M();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        h20Var = new h20(q2Var, o23VarK, a30VarM, ojVar, P());
                        sb0.b(this.c, h20Var);
                        this.c = h20Var;
                    }
                }
                obj = h20Var;
            }
            return (h20) obj;
        }

        public final s30 B() {
            Object s30Var;
            Object obj = this.E;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    s30Var = this.E;
                    if (s30Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        w20 w20VarE = E();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        this.b.getClass();
                        dz0 dz0VarA = dz0.a();
                        a8.n(dz0VarA);
                        g3 g3Var = this.a.k;
                        a8.n(g3Var);
                        s30Var = new s30(appCompatActivity, ojVar, w20VarE, q2Var, dz0VarA, new wx0(g3Var));
                        sb0.b(this.E, s30Var);
                        this.E = s30Var;
                    }
                }
                obj = s30Var;
            }
            return (s30) obj;
        }

        public final e40 C() {
            Object e40Var;
            Object obj = this.P;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    e40Var = this.P;
                    if (e40Var instanceof pe1) {
                        a8.n(this.b.a);
                        dt2 dt2Var = this.a.p;
                        a8.n(dt2Var);
                        e40Var = new e40(dt2Var);
                        sb0.b(this.P, e40Var);
                        this.P = e40Var;
                    }
                }
                obj = e40Var;
            }
            return (e40) obj;
        }

        public final v30 D() {
            Object v30Var;
            Object obj = this.L;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    v30Var = this.L;
                    if (v30Var instanceof pe1) {
                        a30 a30VarM = M();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        w30 w30VarQ = q();
                        v30.a aVar = this.b.d;
                        a8.n(aVar);
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        v30Var = new v30(a30VarM, ojVar, w30VarQ, aVar, appCompatActivity);
                        sb0.b(this.L, v30Var);
                        this.L = v30Var;
                    }
                }
                obj = v30Var;
            }
            return (v30) obj;
        }

        public final w20 E() {
            Object w20Var;
            Object obj = this.j;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    w20Var = this.j;
                    if (w20Var instanceof pe1) {
                        x20 x20VarH = H();
                        a30 a30VarM = M();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        n3.W0();
                        C0167a c0167a = this.g;
                        if (c0167a == null) {
                            c0167a = new C0167a(0);
                            this.g = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        lu luVarL = l();
                        s50.this.a.getClass();
                        sp spVarA = sp.a();
                        a8.n(spVarA);
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        w20.b bVar = this.i;
                        if (bVar == null) {
                            bVar = this.b.f;
                            a8.n(bVar);
                            this.i = bVar;
                        }
                        w20Var = new w20(x20VarH, a30VarM, ojVar, p61VarA, luVarL, spVarA, appCompatActivity, q2Var, bVar);
                        sb0.b(this.j, w20Var);
                        this.j = w20Var;
                    }
                }
                obj = w20Var;
            }
            return (w20) obj;
        }

        public final d40 F() {
            Object d40Var;
            Object obj = this.O;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    d40Var = this.O;
                    if (d40Var instanceof pe1) {
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        M();
                        C0167a c0167a = this.M;
                        if (c0167a == null) {
                            c0167a = new C0167a(10);
                            this.M = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        C0167a c0167a2 = this.N;
                        if (c0167a2 == null) {
                            c0167a2 = new C0167a(11);
                            this.N = c0167a2;
                        }
                        p61 p61VarA2 = sb0.a(c0167a2);
                        v30 v30VarD = D();
                        CustomTabsConnection customTabsConnectionW0 = n3.W0();
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        d40Var = new d40(ojVar, p61VarA, p61VarA2, v30VarD, customTabsConnectionW0, appCompatActivity, q2Var, s50.this.i());
                        sb0.b(this.O, d40Var);
                        this.O = d40Var;
                    }
                }
                obj = d40Var;
            }
            return (d40) obj;
        }

        public final sq2 G() {
            Object sq2Var;
            Object obj = this.K0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    sq2Var = this.K0;
                    if (sq2Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        sq2Var = new sq2(appCompatActivity, q2Var, K(), s(), b(), M(), sb0.a(n()));
                        sb0.b(this.K0, sq2Var);
                        this.K0 = sq2Var;
                    }
                }
                obj = sq2Var;
            }
            return (sq2) obj;
        }

        public final x20 H() {
            Object x20Var;
            z20 z20Var;
            Object obj = this.q;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    x20Var = this.q;
                    if (x20Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        C0167a c0167a = this.k;
                        if (c0167a == null) {
                            c0167a = new C0167a(1);
                            this.k = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        CustomTabsConnection customTabsConnectionW0 = n3.W0();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        z2 z2Var = this.a.j;
                        a8.n(z2Var);
                        o23 o23VarK = K();
                        p61 p61VarA2 = sb0.a(n());
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        s50.this.a.getClass();
                        zo3 zo3VarA = zo3.a();
                        a8.n(zo3VarA);
                        h40 h40VarL = L();
                        z20 z20VarJ = J();
                        C0167a c0167a2 = this.g;
                        if (c0167a2 == null) {
                            c0167a2 = new C0167a(0);
                            this.g = c0167a2;
                        }
                        p61 p61VarA3 = sb0.a(c0167a2);
                        pq3 pq3Var = this.m;
                        if (pq3Var == null) {
                            pq3Var = new pq3();
                            this.m = pq3Var;
                        }
                        pq3 pq3Var2 = pq3Var;
                        y30 y30VarR = r();
                        a30 a30VarM = M();
                        d70 d70Var = new d70();
                        C0167a c0167a3 = this.o;
                        if (c0167a3 == null) {
                            c0167a3 = new C0167a(3);
                            this.o = c0167a3;
                        }
                        p61 p61VarA4 = sb0.a(c0167a3);
                        s50 s50Var = s50.this;
                        c cVar = s50Var.s;
                        if (cVar == null) {
                            z20Var = z20VarJ;
                            cVar = s50Var.new c(4);
                            s50Var.s = cVar;
                        } else {
                            z20Var = z20VarJ;
                        }
                        p61 p61VarA5 = sb0.a(cVar);
                        nw2<Bundle> nw2Var = this.a.x;
                        a8.n(nw2Var);
                        g3 g3Var = this.a.k;
                        a8.n(g3Var);
                        s13 s13Var = this.a.A;
                        a8.n(s13Var);
                        S();
                        x20Var = new x20(appCompatActivity, p61VarA, customTabsConnectionW0, ojVar, z2Var, o23VarK, p61VarA2, q2Var, zo3VarA, h40VarL, z20Var, p61VarA3, pq3Var2, y30VarR, a30VarM, d70Var, p61VarA4, p61VarA5, nw2Var, g3Var, s13Var);
                        sb0.b(this.q, x20Var);
                        this.q = x20Var;
                    }
                }
                obj = x20Var;
            }
            return (x20) obj;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x00cb  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00cf A[Catch: all -> 0x0117, TryCatch #0 {, blocks: (B:5:0x0009, B:7:0x000f, B:9:0x0043, B:10:0x004b, B:12:0x0088, B:14:0x0095, B:21:0x00c1, B:32:0x00e2, B:25:0x00cf, B:27:0x00d5, B:28:0x00d8, B:17:0x00b5, B:19:0x00bd, B:33:0x0114), top: B:40:0x0009 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final defpackage.o30 I() {
            /*
                Method dump skipped, instruction units count: 285
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: s50.a.I():o30");
        }

        public final z20 J() {
            Object z20Var;
            Object obj = this.t;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    z20Var = this.t;
                    if (z20Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        h40 h40VarL = L();
                        qr qrVarJ = j();
                        C0167a c0167a = this.s;
                        if (c0167a == null) {
                            c0167a = new C0167a(4);
                            this.s = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        C0167a c0167a2 = this.k;
                        if (c0167a2 == null) {
                            c0167a2 = new C0167a(1);
                            this.k = c0167a2;
                        }
                        p61 p61VarA2 = sb0.a(c0167a2);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        s50 s50Var = s50.this;
                        c cVar = s50Var.s;
                        if (cVar == null) {
                            cVar = s50Var.new c(4);
                            s50Var.s = cVar;
                        }
                        p61 p61VarA3 = sb0.a(cVar);
                        gz2 gz2Var = this.a.m;
                        a8.n(gz2Var);
                        nw2<y13> nw2Var = this.a.c;
                        a8.n(nw2Var);
                        nw2<CompositorViewHolder> nw2Var2 = this.a.l;
                        a8.n(nw2Var2);
                        z20Var = new z20(appCompatActivity, h40VarL, qrVarJ, p61VarA, p61VarA2, ojVar, p61VarA3, gz2Var, nw2Var, nw2Var2);
                        sb0.b(this.t, z20Var);
                        this.t = z20Var;
                    }
                }
                obj = z20Var;
            }
            return (z20) obj;
        }

        public final o23 K() {
            Object o23Var;
            Object obj = this.V;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    o23Var = this.V;
                    if (o23Var instanceof pe1) {
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        a30 a30VarM = M();
                        p();
                        o23Var = new o23(q2Var, a30VarM);
                        sb0.b(this.V, o23Var);
                        this.V = o23Var;
                    }
                }
                obj = o23Var;
            }
            return (o23) obj;
        }

        public final h40 L() {
            Object h40Var;
            Object obj = this.J0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    h40Var = this.J0;
                    if (h40Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        nw2<Bundle> nw2Var = this.a.x;
                        a8.n(nw2Var);
                        h40Var = new h40(appCompatActivity, nw2Var);
                        sb0.b(this.J0, h40Var);
                        this.J0 = h40Var;
                    }
                }
                obj = h40Var;
            }
            return (h40) obj;
        }

        public final a30 M() {
            Object a30Var;
            Object obj = this.u;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    a30Var = this.u;
                    if (a30Var instanceof pe1) {
                        a30Var = new a30();
                        sb0.b(this.u, a30Var);
                        this.u = a30Var;
                    }
                }
                obj = a30Var;
            }
            return (a30) obj;
        }

        public final u40 N() {
            Object u40Var;
            Object obj = this.T;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    u40Var = this.T;
                    if (u40Var instanceof pe1) {
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        a30 a30VarM = M();
                        CustomTabsConnection customTabsConnectionW0 = n3.W0();
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        Context contextV = z7.V(s50.this.a);
                        H();
                        C0167a c0167a = this.x;
                        if (c0167a == null) {
                            c0167a = new C0167a(5);
                            this.x = c0167a;
                        }
                        u40Var = new u40(ojVar, a30VarM, customTabsConnectionW0, appCompatActivity, contextV, sb0.a(c0167a), E(), m(), o(), z(), t());
                        sb0.b(this.T, u40Var);
                        this.T = u40Var;
                    }
                }
                obj = u40Var;
            }
            return (u40) obj;
        }

        public final xf3 O() {
            Object xf3Var;
            Object obj = this.I0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    xf3Var = this.I0;
                    if (xf3Var instanceof pe1) {
                        il2 il2VarT = T();
                        V();
                        w();
                        X();
                        h20 h20VarA = A();
                        C0167a c0167a = this.G0;
                        if (c0167a == null) {
                            c0167a = new C0167a(23);
                            this.G0 = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        jg3 jg3VarL = s50.this.l();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        pk pkVarX = x();
                        eu euVarK = k();
                        CustomTabsConnection customTabsConnectionW0 = n3.W0();
                        y();
                        xf3Var = new xf3(il2VarT, h20VarA, p61VarA, ojVar, jg3VarL, q2Var, pkVarX, euVarK, customTabsConnectionW0);
                        sb0.b(this.I0, xf3Var);
                        this.I0 = xf3Var;
                    }
                }
                obj = xf3Var;
            }
            return (xf3) obj;
        }

        public final il3 P() {
            kd kdVar = this.b;
            C0167a c0167a = this.Y;
            if (c0167a == null) {
                c0167a = new C0167a(12);
                this.Y = c0167a;
            }
            p61 p61VarA = sb0.a(c0167a);
            C0167a c0167a2 = this.a0;
            if (c0167a2 == null) {
                c0167a2 = new C0167a(13);
                this.a0 = c0167a2;
            }
            p61 p61VarA2 = sb0.a(c0167a2);
            C0167a c0167a3 = this.e0;
            if (c0167a3 == null) {
                c0167a3 = new C0167a(14);
                this.e0 = c0167a3;
            }
            p61 p61VarA3 = sb0.a(c0167a3);
            C0167a c0167a4 = this.g0;
            if (c0167a4 == null) {
                c0167a4 = new C0167a(15);
                this.g0 = c0167a4;
            }
            p61 p61VarA4 = sb0.a(c0167a4);
            int i = kdVar.b;
            il3 il3Var = i != 2 ? i != 3 ? i != 4 ? (il3) p61VarA4.get() : (il3) p61VarA.get() : (il3) p61VarA2.get() : (il3) p61VarA3.get();
            a8.n(il3Var);
            return il3Var;
        }

        public final org.chromium.chrome.browser.webapps.a Q() {
            Object aVar;
            Object obj = this.y0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    aVar = this.y0;
                    if (aVar instanceof pe1) {
                        e eVarB0 = b0();
                        c0();
                        v();
                        Z();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        C0167a c0167a = this.v0;
                        if (c0167a == null) {
                            c0167a = new C0167a(18);
                            this.v0 = c0167a;
                        }
                        aVar = new org.chromium.chrome.browser.webapps.a(eVarB0, q2Var, ojVar, sb0.a(c0167a), x());
                        sb0.b(this.y0, aVar);
                        this.y0 = aVar;
                    }
                }
                obj = aVar;
            }
            return (org.chromium.chrome.browser.webapps.a) obj;
        }

        public final org.chromium.chrome.browser.webapps.c R() {
            Object obj;
            Object obj2 = this.o0;
            if (obj2 instanceof pe1) {
                synchronized (obj2) {
                    obj = this.o0;
                    if (obj instanceof pe1) {
                        T();
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        z2 z2Var = this.a.j;
                        a8.n(z2Var);
                        h20 h20VarA = A();
                        sq2 sq2VarG = G();
                        AppCompatActivity appCompatActivity2 = this.a.a;
                        a8.n(appCompatActivity2);
                        o23 o23VarK = K();
                        oj ojVar2 = this.b.a;
                        a8.n(ojVar2);
                        new rs3(sq2VarG, appCompatActivity2, o23VarK, ojVar2);
                        e eVarB0 = b0();
                        Object objA0 = a0();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        org.chromium.chrome.browser.webapps.c cVar = new org.chromium.chrome.browser.webapps.c(appCompatActivity, ojVar, z2Var, h20VarA, eVarB0, q2Var);
                        sb0.b(this.o0, cVar);
                        this.o0 = cVar;
                        obj = cVar;
                    }
                }
                obj2 = obj;
            }
            return (org.chromium.chrome.browser.webapps.c) obj2;
        }

        public final fj2 S() {
            Object fj2Var;
            Object obj = this.p;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    fj2Var = this.p;
                    if (fj2Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        g3 g3Var = this.a.k;
                        a8.n(g3Var);
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        CustomTabsConnection customTabsConnectionW0 = n3.W0();
                        C0167a c0167a = this.k;
                        if (c0167a == null) {
                            c0167a = new C0167a(1);
                            this.k = c0167a;
                        }
                        fj2Var = new fj2(appCompatActivity, g3Var, q2Var, ojVar, customTabsConnectionW0, sb0.a(c0167a), J(), sb0.a(n()));
                        sb0.b(this.p, fj2Var);
                        this.p = fj2Var;
                    }
                }
                obj = fj2Var;
            }
            return (fj2) obj;
        }

        public final il2 T() {
            Object il2Var;
            Object obj = this.l0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    il2Var = this.l0;
                    if (il2Var instanceof pe1) {
                        h20 h20VarA = A();
                        il3 il3VarP = P();
                        w20 w20VarE = E();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        t40 t40VarT = t();
                        e40 e40VarC = C();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        jf3 jf3VarU = U();
                        C0167a c0167a = this.j0;
                        if (c0167a == null) {
                            c0167a = new C0167a(16);
                            this.j0 = c0167a;
                        }
                        il2Var = new il2(h20VarA, il3VarP, w20VarE, ojVar, t40VarT, e40VarC, q2Var, jf3VarU, sb0.a(c0167a), s());
                        sb0.b(this.l0, il2Var);
                        this.l0 = il2Var;
                    }
                }
                obj = il2Var;
            }
            return (il2) obj;
        }

        public final jf3 U() {
            Object jf3Var;
            Object obj = this.h0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    jf3Var = this.h0;
                    if (jf3Var instanceof pe1) {
                        o23 o23VarK = K();
                        a30 a30VarM = M();
                        u40 u40VarN = N();
                        mu muVarM = m();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        jf3Var = new jf3(o23VarK, a30VarM, u40VarN, muVarM, ojVar);
                        sb0.b(this.h0, jf3Var);
                        this.h0 = jf3Var;
                    }
                }
                obj = jf3Var;
            }
            return (jf3) obj;
        }

        public final yf3 V() {
            Object pjVar;
            Object obj = this.z0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    pjVar = this.z0;
                    if (pjVar instanceof pe1) {
                        s50.this.a.getClass();
                        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                        a8.n(sharedPreferencesManager);
                        pjVar = new pj(sharedPreferencesManager);
                        sb0.b(this.z0, pjVar);
                        this.z0 = pjVar;
                    }
                }
                obj = pjVar;
            }
            pj pjVar2 = (pj) obj;
            ag3 ag3VarW = W();
            q2 q2Var = this.a.h;
            a8.n(q2Var);
            return new yf3(pjVar2, ag3VarW, q2Var, A(), s50.this.l(), k());
        }

        public final ag3 W() {
            Object ag3Var;
            Object obj = this.p0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    ag3Var = this.p0;
                    if (ag3Var instanceof pe1) {
                        ag3Var = new ag3();
                        sb0.b(this.p0, ag3Var);
                        this.p0 = ag3Var;
                    }
                }
                obj = ag3Var;
            }
            return (ag3) obj;
        }

        public final bg3 X() {
            Object obj;
            Object obj2 = this.F0;
            if (obj2 instanceof pe1) {
                synchronized (obj2) {
                    obj = this.F0;
                    if (obj instanceof pe1) {
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        h20 h20VarA = A();
                        jg3 jg3VarL = s50.this.l();
                        z2 z2Var = this.a.j;
                        a8.n(z2Var);
                        bg3 bg3Var = new bg3(q2Var, h20VarA, jg3VarL, z2Var);
                        sb0.b(this.F0, bg3Var);
                        this.F0 = bg3Var;
                        obj = bg3Var;
                    }
                }
                obj2 = obj;
            }
            return (bg3) obj2;
        }

        public final zg3 Y() {
            Object zg3Var;
            Object obj = this.I;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    zg3Var = this.I;
                    if (zg3Var instanceof pe1) {
                        a30 a30VarM = M();
                        w20 w20VarE = E();
                        kp3 kp3Var = this.H;
                        if (kp3Var == null) {
                            this.b.getClass();
                            kp3Var = new kp3();
                            this.H = kp3Var;
                        }
                        zg3Var = new zg3(a30VarM, w20VarE, kp3Var, P(), s50.this.l());
                        sb0.b(this.I, zg3Var);
                        this.I = zg3Var;
                    }
                }
                obj = zg3Var;
            }
            return (zg3) obj;
        }

        public final org.chromium.chrome.browser.webapps.b Z() {
            Object bVar;
            Object obj = this.t0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    bVar = this.t0;
                    if (bVar instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        sq2 sq2VarG = G();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        e eVarB0 = b0();
                        C0167a c0167a = this.s0;
                        if (c0167a == null) {
                            c0167a = new C0167a(17);
                            this.s0 = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        nw2<Bundle> nw2Var = this.a.x;
                        a8.n(nw2Var);
                        bVar = new org.chromium.chrome.browser.webapps.b(appCompatActivity, ojVar, sq2VarG, q2Var, eVarB0, p61VarA, nw2Var);
                        sb0.b(this.t0, bVar);
                        this.t0 = bVar;
                    }
                }
                obj = bVar;
            }
            return (org.chromium.chrome.browser.webapps.b) obj;
        }

        @Override // defpackage.jd
        public final d30 a() {
            Object d30Var;
            Object obj = this.v;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    d30Var = this.v;
                    if (d30Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        g3 g3Var = this.a.k;
                        a8.n(g3Var);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        jj jjVar = this.a.e;
                        a8.n(jjVar);
                        zp1<Integer> zp1Var = this.a.y;
                        a8.n(zp1Var);
                        a40 a40Var = this.b.c;
                        a8.n(a40Var);
                        s50.this.a.getClass();
                        fy2 fy2VarA = fy2.a();
                        a8.n(fy2VarA);
                        d30Var = new d30(appCompatActivity, g3Var, ojVar, jjVar, zp1Var, a40Var, fy2VarA, M(), z());
                        sb0.b(this.v, d30Var);
                        this.v = d30Var;
                    }
                }
                obj = d30Var;
            }
            return (d30) obj;
        }

        public final Object a0() {
            Object obj;
            Object obj2 = this.n0;
            if (!(obj2 instanceof pe1)) {
                return obj2;
            }
            synchronized (obj2) {
                obj = this.n0;
                if (obj instanceof pe1) {
                    a30 a30VarM = M();
                    oj ojVar = this.b.a;
                    a8.n(ojVar);
                    q2 q2Var = this.a.h;
                    a8.n(q2Var);
                    zr3 zr3Var = new zr3(a30VarM, ojVar, q2Var);
                    sb0.b(this.n0, zr3Var);
                    this.n0 = zr3Var;
                    obj = zr3Var;
                }
            }
            return obj;
        }

        @Override // defpackage.jd
        public final vg3 b() {
            Object vg3Var;
            Object obj = this.W;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    vg3Var = this.W;
                    if (vg3Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        vg3Var = new vg3(appCompatActivity, ojVar, n3.W0());
                        sb0.b(this.W, vg3Var);
                        this.W = vg3Var;
                    }
                }
                obj = vg3Var;
            }
            return (vg3) obj;
        }

        public final e b0() {
            Object eVar;
            Object obj = this.m0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    eVar = this.m0;
                    if (eVar instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        eVar = new e(appCompatActivity, ojVar);
                        sb0.b(this.m0, eVar);
                        this.m0 = eVar;
                    }
                }
                obj = eVar;
            }
            return (e) obj;
        }

        public final is3 c0() {
            Object is3Var;
            Object obj = this.q0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    is3Var = this.q0;
                    if (is3Var instanceof pe1) {
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        e eVarB0 = b0();
                        ag3 ag3VarW = W();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        is3Var = new is3(ojVar, eVarB0, ag3VarW, q2Var, A());
                        sb0.b(this.q0, is3Var);
                        this.q0 = is3Var;
                    }
                }
                obj = is3Var;
            }
            return (is3) obj;
        }

        public final qr j() {
            Object qrVar;
            Object obj = this.r;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    qrVar = this.r;
                    if (qrVar instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        qrVar = new qr(appCompatActivity);
                        sb0.b(this.r, qrVar);
                        this.r = qrVar;
                    }
                }
                obj = qrVar;
            }
            return (qr) obj;
        }

        public final eu k() {
            Object euVar;
            Object obj = this.c0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    euVar = this.c0;
                    if (euVar instanceof pe1) {
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        CustomTabsConnection customTabsConnectionW0 = n3.W0();
                        nw2<Bundle> nw2Var = this.a.x;
                        a8.n(nw2Var);
                        euVar = new eu(q2Var, ojVar, customTabsConnectionW0, nw2Var);
                        sb0.b(this.c0, euVar);
                        this.c0 = euVar;
                    }
                }
                obj = euVar;
            }
            return (eu) obj;
        }

        public final lu l() {
            Object luVar;
            Object obj = this.h;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    luVar = this.h;
                    if (luVar instanceof pe1) {
                        x20 x20VarH = H();
                        a30 a30VarM = M();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        luVar = new lu(x20VarH, a30VarM, ojVar);
                        sb0.b(this.h, luVar);
                        this.h = luVar;
                    }
                }
                obj = luVar;
            }
            return (lu) obj;
        }

        public final mu m() {
            Object muVar;
            Object obj = this.R;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    muVar = this.R;
                    if (muVar instanceof pe1) {
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        muVar = new mu(ojVar);
                        sb0.b(this.R, muVar);
                        this.R = muVar;
                    }
                }
                obj = muVar;
            }
            return (mu) obj;
        }

        public final c72<CompositorViewHolder> n() {
            C0167a c0167a = this.l;
            if (c0167a != null) {
                return c0167a;
            }
            C0167a c0167a2 = new C0167a(2);
            this.l = c0167a2;
            return c0167a2;
        }

        public final g30 o() {
            Object g30Var;
            Object obj = this.y;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    g30Var = this.y;
                    if (g30Var instanceof pe1) {
                        C0167a c0167a = this.x;
                        if (c0167a == null) {
                            c0167a = new C0167a(5);
                            this.x = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        a8.n(this.a.j);
                        g30Var = new g30(p61VarA);
                        sb0.b(this.y, g30Var);
                        this.y = g30Var;
                    }
                }
                obj = g30Var;
            }
            return (g30) obj;
        }

        public final l30 p() {
            Object l30Var;
            Object obj = this.U;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    l30Var = this.U;
                    if (l30Var instanceof pe1) {
                        l30Var = new l30(M());
                        sb0.b(this.U, l30Var);
                        this.U = l30Var;
                    }
                }
                obj = l30Var;
            }
            return (l30) obj;
        }

        public final w30 q() {
            kd kdVar = this.b;
            C0167a c0167a = this.G;
            if (c0167a == null) {
                c0167a = new C0167a(8);
                this.G = c0167a;
            }
            p61 p61VarA = sb0.a(c0167a);
            C0167a c0167a2 = this.K;
            if (c0167a2 == null) {
                c0167a2 = new C0167a(9);
                this.K = c0167a2;
            }
            p61 p61VarA2 = sb0.a(c0167a2);
            int i = kdVar.b;
            w30 w30Var = (i == 2 || i == 4) ? (w30) p61VarA2.get() : (w30) p61VarA.get();
            a8.n(w30Var);
            return w30Var;
        }

        public final y30 r() {
            Object y30Var;
            Object obj = this.n;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    y30Var = this.n;
                    if (y30Var instanceof pe1) {
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        y30Var = new y30(ojVar, n3.W0());
                        sb0.b(this.n, y30Var);
                        this.n = y30Var;
                    }
                }
                obj = y30Var;
            }
            return (y30) obj;
        }

        public final c40 s() {
            Object c40Var;
            Object obj = this.k0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    c40Var = this.k0;
                    if (c40Var instanceof pe1) {
                        g3 g3Var = this.a.k;
                        a8.n(g3Var);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        c40Var = new c40(g3Var, ojVar);
                        sb0.b(this.k0, c40Var);
                        this.k0 = c40Var;
                    }
                }
                obj = c40Var;
            }
            return (c40) obj;
        }

        public final t40 t() {
            Object t40Var;
            Object obj = this.S;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    t40Var = this.S;
                    if (t40Var instanceof pe1) {
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        a30 a30VarM = M();
                        o23 o23VarK = K();
                        xd3 xd3Var = this.b.e;
                        a8.n(xd3Var);
                        t40Var = new t40(ojVar, appCompatActivity, a30VarM, o23VarK, xd3Var);
                        sb0.b(this.S, t40Var);
                        this.S = t40Var;
                    }
                }
                obj = t40Var;
            }
            return (t40) obj;
        }

        public final p70 u() {
            Object p70Var;
            Object obj = this.F;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    p70Var = this.F;
                    if (p70Var instanceof pe1) {
                        a30 a30VarM = M();
                        w20 w20VarE = E();
                        y30 y30VarR = r();
                        C0167a c0167a = this.g;
                        if (c0167a == null) {
                            c0167a = new C0167a(0);
                            this.g = c0167a;
                        }
                        p70Var = new p70(a30VarM, w20VarE, y30VarR, sb0.a(c0167a));
                        sb0.b(this.F, p70Var);
                        this.F = p70Var;
                    }
                }
                obj = p70Var;
            }
            return (p70) obj;
        }

        public final pa0 v() {
            Object obj;
            Object obj2 = this.r0;
            if (obj2 instanceof pe1) {
                synchronized (obj2) {
                    obj = this.r0;
                    if (obj instanceof pe1) {
                        Resources resources = this.a.a.getResources();
                        a8.n(resources);
                        C0167a c0167a = this.B;
                        if (c0167a == null) {
                            c0167a = new C0167a(7);
                            this.B = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        ag3 ag3VarW = W();
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        pa0 pa0Var = new pa0(resources, p61VarA, ag3VarW, q2Var);
                        sb0.b(this.r0, pa0Var);
                        this.r0 = pa0Var;
                        obj = pa0Var;
                    }
                }
                obj2 = obj;
            }
            return (pa0) obj2;
        }

        public final sa0 w() {
            Object sa0Var;
            Object obj = this.E0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    sa0Var = this.E0;
                    if (sa0Var instanceof pe1) {
                        C0167a c0167a = this.A0;
                        if (c0167a == null) {
                            c0167a = new C0167a(20);
                            this.A0 = c0167a;
                        }
                        p61 p61VarA = sb0.a(c0167a);
                        C0167a c0167a2 = this.C0;
                        if (c0167a2 == null) {
                            c0167a2 = new C0167a(21);
                            this.C0 = c0167a2;
                        }
                        p61 p61VarA2 = sb0.a(c0167a2);
                        C0167a c0167a3 = this.D0;
                        if (c0167a3 == null) {
                            c0167a3 = new C0167a(22);
                            this.D0 = c0167a3;
                        }
                        p61 p61VarA3 = sb0.a(c0167a3);
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        so1 so1Var = this.a.q.get();
                        a8.n(so1Var);
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        sa0Var = new sa0(p61VarA, p61VarA2, p61VarA3, ojVar, so1Var, q2Var);
                        sb0.b(this.E0, sa0Var);
                        this.E0 = sa0Var;
                    }
                }
                obj = sa0Var;
            }
            return (sa0) obj;
        }

        public final pk x() {
            Context contextV = z7.V(s50.this.a);
            f02 f02VarH = s50.this.h();
            C0167a c0167a = this.x0;
            if (c0167a == null) {
                c0167a = new C0167a(19);
                this.x0 = c0167a;
            }
            return new pk(contextV, f02VarH, sb0.a(c0167a));
        }

        public final n82 y() {
            Object n82Var;
            Object obj = this.H0;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    n82Var = this.H0;
                    if (n82Var instanceof pe1) {
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        z7.W(this.a);
                        o23 o23VarK = K();
                        oj ojVar = this.b.a;
                        a8.n(ojVar);
                        n82Var = new n82(appCompatActivity, o23VarK, ojVar, n3.W0(), P(), k(), s50.this.l());
                        sb0.b(this.H0, n82Var);
                        this.H0 = n82Var;
                    }
                }
                obj = n82Var;
            }
            return (n82) obj;
        }

        public final k30 z() {
            Object k30Var;
            Object obj = this.w;
            if (obj instanceof pe1) {
                synchronized (obj) {
                    k30Var = this.w;
                    if (k30Var instanceof pe1) {
                        q2 q2Var = this.a.h;
                        a8.n(q2Var);
                        AppCompatActivity appCompatActivity = this.a.a;
                        a8.n(appCompatActivity);
                        p61 p61VarA = sb0.a(n());
                        zp1<TabContentManager> zp1Var = this.a.r;
                        a8.n(zp1Var);
                        CompositorViewHolder.e eVar = this.a.t;
                        a8.n(eVar);
                        xd3 xd3Var = this.b.e;
                        a8.n(xd3Var);
                        k30Var = new k30(q2Var, appCompatActivity, p61VarA, zp1Var, eVar, xd3Var);
                        sb0.b(this.w, k30Var);
                        this.w = k30Var;
                    }
                }
                obj = k30Var;
            }
            return (k30) obj;
        }
    }

    /* JADX INFO: compiled from: DaggerChromeAppComponent.java */
    public final class b implements ip {
    }

    /* JADX INFO: compiled from: DaggerChromeAppComponent.java */
    public final class c<T> implements c72<T> {
        public final int e;

        public c(int i) {
            this.e = i;
        }

        @Override // defpackage.c72
        public final T get() {
            Object obj;
            int i = this.e;
            if (i == 0) {
                s50.this.a.getClass();
                T t = (T) SharedPreferencesManager.getInstance();
                a8.n(t);
                return t;
            }
            if (i != 1) {
                if (i == 2) {
                    return (T) s50.a(s50.this);
                }
                if (i == 3) {
                    return (T) n3.W0();
                }
                if (i != 4) {
                    throw new AssertionError(this.e);
                }
                s50.this.a.getClass();
                T t2 = (T) h9.a;
                a8.n(t2);
                return t2;
            }
            s50 s50Var = s50.this;
            Object obj2 = s50Var.f;
            if (obj2 instanceof pe1) {
                synchronized (obj2) {
                    obj = s50Var.f;
                    if (obj instanceof pe1) {
                        a11 a11VarB = s50Var.b();
                        s50Var.a.getClass();
                        xn2 xn2Var = xn2.a.a;
                        a8.n(xn2Var);
                        jo1 jo1Var = new jo1(a11VarB, xn2Var);
                        sb0.b(s50Var.f, jo1Var);
                        s50Var.f = jo1Var;
                        obj = jo1Var;
                    }
                }
                obj2 = obj;
            }
            return (T) ((jo1) obj2);
        }
    }

    public s50(op opVar, a8 a8Var) {
        this.a = opVar;
        this.b = a8Var;
    }

    public static uq2 a(s50 s50Var) {
        Object uq2Var;
        Object obj = s50Var.n;
        if (obj instanceof pe1) {
            synchronized (obj) {
                uq2Var = s50Var.n;
                if (uq2Var instanceof pe1) {
                    uq2Var = new uq2();
                    sb0.b(s50Var.n, uq2Var);
                    s50Var.n = uq2Var;
                }
            }
            obj = uq2Var;
        }
        return (uq2) obj;
    }

    public final a11 b() {
        Object obj;
        Object obj2 = this.e;
        if (obj2 instanceof pe1) {
            synchronized (obj2) {
                obj = this.e;
                if (obj instanceof pe1) {
                    this.a.getClass();
                    obj = WebappRegistry.b.a.d;
                    a8.n(obj);
                    sb0.b(this.e, obj);
                    this.e = obj;
                }
            }
            obj2 = obj;
        }
        return (a11) obj2;
    }

    public final z91 c() {
        Object z91Var;
        Object obj = this.j;
        if (obj instanceof pe1) {
            synchronized (obj) {
                z91Var = this.j;
                if (z91Var instanceof pe1) {
                    z91Var = new z91(g(), j(), l());
                    sb0.b(this.j, z91Var);
                    this.j = z91Var;
                }
            }
            obj = z91Var;
        }
        return (z91) obj;
    }

    public final dp1 d() {
        Object dp1Var;
        Object obj = this.i;
        if (obj instanceof pe1) {
            synchronized (obj) {
                dp1Var = this.i;
                if (dp1Var instanceof pe1) {
                    dp1Var = new dp1(g(), j());
                    sb0.b(this.i, dp1Var);
                    this.i = dp1Var;
                }
            }
            obj = dp1Var;
        }
        return (dp1) obj;
    }

    public final tt e() {
        c cVar = this.c;
        if (cVar == null) {
            cVar = new c(0);
            this.c = cVar;
        }
        p61 p61VarA = sb0.a(cVar);
        this.a.getClass();
        sp spVarA = sp.a();
        a8.n(spVarA);
        return new tt(p61VarA, spVarA, l());
    }

    public final w40 f() {
        Object w40Var;
        Object obj = this.p;
        if (obj instanceof pe1) {
            synchronized (obj) {
                w40Var = this.p;
                if (w40Var instanceof pe1) {
                    Context contextV = z7.V(this.a);
                    c cVar = this.o;
                    if (cVar == null) {
                        cVar = new c(2);
                        this.o = cVar;
                    }
                    w40Var = new w40(contextV, sb0.a(cVar));
                    sb0.b(this.p, w40Var);
                    this.p = w40Var;
                }
            }
            obj = w40Var;
        }
        return (w40) obj;
    }

    public final org.chromium.chrome.browser.browserservices.permissiondelegation.a g() {
        Object aVar;
        Object obj = this.h;
        if (obj instanceof pe1) {
            synchronized (obj) {
                aVar = this.h;
                if (aVar instanceof pe1) {
                    Context contextV = z7.V(this.a);
                    a11 a11VarB = b();
                    c cVar = this.g;
                    if (cVar == null) {
                        cVar = new c(1);
                        this.g = cVar;
                    }
                    aVar = new org.chromium.chrome.browser.browserservices.permissiondelegation.a(contextV, a11VarB, sb0.a(cVar), l());
                    sb0.b(this.h, aVar);
                    this.h = aVar;
                }
            }
            obj = aVar;
        }
        return (org.chromium.chrome.browser.browserservices.permissiondelegation.a) obj;
    }

    public final f02 h() {
        Object f02Var;
        Object obj = this.k;
        if (obj instanceof pe1) {
            synchronized (obj) {
                f02Var = this.k;
                if (f02Var instanceof pe1) {
                    f02Var = new f02(g(), d(), c());
                    sb0.b(this.k, f02Var);
                    this.k = f02Var;
                }
            }
            obj = f02Var;
        }
        return (f02) obj;
    }

    public final bj2 i() {
        Object bj2Var;
        Object obj = this.r;
        if (obj instanceof pe1) {
            synchronized (obj) {
                bj2Var = this.r;
                if (bj2Var instanceof pe1) {
                    c cVar = this.q;
                    if (cVar == null) {
                        cVar = new c(3);
                        this.q = cVar;
                    }
                    bj2Var = new bj2(sb0.a(cVar));
                    sb0.b(this.r, bj2Var);
                    this.r = bj2Var;
                }
            }
            obj = bj2Var;
        }
        return (bj2) obj;
    }

    public final sf3 j() {
        Object sf3Var;
        Object obj = this.m;
        if (obj instanceof pe1) {
            synchronized (obj) {
                sf3Var = this.m;
                if (sf3Var instanceof pe1) {
                    sf3Var = new sf3(k(), g(), l());
                    sb0.b(this.m, sf3Var);
                    this.m = sf3Var;
                }
            }
            obj = sf3Var;
        }
        return (sf3) obj;
    }

    public final eg3 k() {
        Object obj;
        Object obj2 = this.l;
        if (obj2 instanceof pe1) {
            synchronized (obj2) {
                obj = this.l;
                if (obj instanceof pe1) {
                    eg3 eg3Var = new eg3(z7.V(this.a));
                    sb0.b(this.l, eg3Var);
                    this.l = eg3Var;
                    obj = eg3Var;
                }
            }
            obj2 = obj;
        }
        return (eg3) obj2;
    }

    public final jg3 l() {
        jg3 jg3Var = this.d;
        if (jg3Var != null) {
            return jg3Var;
        }
        op opVar = this.a;
        opVar.getClass();
        jg3 jg3Var2 = new jg3(new np(opVar));
        this.d = jg3Var2;
        return jg3Var2;
    }
}
