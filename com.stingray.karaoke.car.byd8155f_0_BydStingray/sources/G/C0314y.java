package G;

import A.I0;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import n0.InterfaceC1897z;
import q0.C2164c;
import x.C2629d;
import x.E0;
import x.InterfaceC2615D;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: G.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0314y {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final long f2617q = AbstractC2673a.d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ int f2618r = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X8.c f2619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC1897z f2620b;
    public final I0 c;
    public InterfaceC2615D d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f2622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0738e0 f2623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0738e0 f2624h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0738e0 f2625i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f2626j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f2627k;
    public C2164c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2629d f2628m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2629d f2629n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f2630p;

    public C0314y(X8.c cVar, InterfaceC1897z interfaceC1897z, I0 i02) {
        this.f2619a = cVar;
        this.f2620b = interfaceC1897z;
        this.c = i02;
        Boolean bool = Boolean.FALSE;
        T.S s6 = T.S.f8224q;
        this.f2622f = C0735d.O(bool, s6);
        this.f2623g = C0735d.O(bool, s6);
        this.f2624h = C0735d.O(bool, s6);
        this.f2625i = C0735d.O(bool, s6);
        long j3 = f2617q;
        this.f2626j = j3;
        this.f2627k = 0L;
        Object obj = null;
        this.l = interfaceC1897z != null ? interfaceC1897z.a() : null;
        int i7 = 12;
        this.f2628m = new C2629d(new a1.h(0L), E0.f23400g, obj, i7);
        this.f2629n = new C2629d(Float.valueOf(1.0f), E0.f23396a, obj, i7);
        this.o = C0735d.O(new a1.h(0L), s6);
        this.f2630p = j3;
    }

    public final void a() {
        C2164c c2164c = this.l;
        ((Boolean) this.f2623g.getValue()).booleanValue();
        if (b()) {
            if (c2164c != null) {
                c2164c.f(1.0f);
            }
            AbstractC0727y.w(this.f2619a, null, null, new C0308s(this, null), 3);
        }
    }

    public final boolean b() {
        return ((Boolean) this.f2624h.getValue()).booleanValue();
    }

    public final void c() {
        InterfaceC1897z interfaceC1897z;
        boolean zBooleanValue = ((Boolean) this.f2622f.getValue()).booleanValue();
        X8.c cVar = this.f2619a;
        if (zBooleanValue) {
            d(false);
            AbstractC0727y.w(cVar, null, null, new C0311v(this, null), 3);
        }
        if (((Boolean) this.f2623g.getValue()).booleanValue()) {
            this.f2623g.setValue(false);
            AbstractC0727y.w(cVar, null, null, new C0312w(this, null), 3);
        }
        if (b()) {
            this.f2624h.setValue(false);
            AbstractC0727y.w(cVar, null, null, new C0313x(this, null), 3);
        }
        this.f2621e = false;
        e(0L);
        this.f2626j = f2617q;
        C2164c c2164c = this.l;
        if (c2164c != null && (interfaceC1897z = this.f2620b) != null) {
            interfaceC1897z.b(c2164c);
        }
        this.l = null;
        this.d = null;
    }

    public final void d(boolean z6) {
        this.f2622f.setValue(Boolean.valueOf(z6));
    }

    public final void e(long j3) {
        this.o.setValue(new a1.h(j3));
    }
}
