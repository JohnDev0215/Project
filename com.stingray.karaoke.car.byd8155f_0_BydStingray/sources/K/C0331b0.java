package K;

import F0.R0;
import N0.AbstractC0412g;
import N0.C0411f;
import T.C0735d;
import T.C0738e0;
import T.C0754m0;
import n0.AbstractC1861K;
import n0.C1878g;
import n0.C1891t;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0331b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0353m0 f3220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0754m0 f3221b;
    public final R0 c;
    public final M.t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public T0.E f3222e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f3223f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0738e0 f3224g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C0.r f3225h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0738e0 f3226i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C0411f f3227j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f3228k;
    public final C0738e0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0738e0 f3229m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f3230n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3231p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0738e0 f3232q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Y f3233r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public InterfaceC2782k f3234s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0365x f3235t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0365x f3236u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C1878g f3237v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f3238w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0738e0 f3239x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C0738e0 f3240y;

    public C0331b0(C0353m0 c0353m0, C0754m0 c0754m0, R0 r02) {
        this.f3220a = c0353m0;
        this.f3221b = c0754m0;
        this.c = r02;
        M.t tVar = new M.t();
        C0411f c0411f = AbstractC0412g.f4375a;
        long j3 = N0.K.f4356b;
        T0.y yVar = new T0.y(c0411f, j3, (N0.K) null);
        tVar.l = yVar;
        tVar.f4151m = new T0.j(c0411f, yVar.f8482b);
        this.d = tVar;
        Boolean bool = Boolean.FALSE;
        T.S s6 = T.S.f8224q;
        this.f3223f = C0735d.O(bool, s6);
        this.f3224g = C0735d.O(new a1.e(0), s6);
        this.f3226i = C0735d.O(null, s6);
        this.f3228k = C0735d.O(P.l, s6);
        this.l = C0735d.O(bool, s6);
        this.f3229m = C0735d.O(bool, s6);
        this.f3230n = C0735d.O(bool, s6);
        this.o = C0735d.O(bool, s6);
        this.f3231p = true;
        this.f3232q = C0735d.O(Boolean.TRUE, s6);
        this.f3233r = new Y(r02);
        this.f3234s = C0340g.f3256q;
        this.f3235t = new C0365x(this, 5);
        this.f3236u = new C0365x(this, 4);
        this.f3237v = AbstractC1861K.h();
        this.f3238w = C1891t.f19452k;
        this.f3239x = C0735d.O(new N0.K(j3), s6);
        this.f3240y = C0735d.O(new N0.K(j3), s6);
    }

    public final P a() {
        return (P) this.f3228k.getValue();
    }

    public final boolean b() {
        return ((Boolean) this.f3223f.getValue()).booleanValue();
    }

    public final C0.r c() {
        C0.r rVar = this.f3225h;
        if (rVar == null || !rVar.D()) {
            return null;
        }
        return rVar;
    }

    public final G0 d() {
        return (G0) this.f3226i.getValue();
    }

    public final void e(long j3) {
        this.f3240y.setValue(new N0.K(j3));
    }

    public final void f(long j3) {
        this.f3239x.setValue(new N0.K(j3));
    }
}
