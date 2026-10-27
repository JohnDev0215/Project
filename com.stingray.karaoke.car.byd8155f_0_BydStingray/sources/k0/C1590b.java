package k0;

import A7.m;
import A7.o;
import E0.AbstractC0156f;
import E0.H;
import E0.InterfaceC0165o;
import E0.f0;
import a1.k;
import d8.C1134u;
import g0.n;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: k0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1590b extends n implements f0, InterfaceC1589a, InterfaceC0165o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC2782k f17612A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C1591c f17613y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f17614z;

    public C1590b(C1591c c1591c, InterfaceC2782k interfaceC2782k) {
        this.f17613y = c1591c;
        this.f17612A = interfaceC2782k;
        c1591c.l = this;
    }

    @Override // g0.n
    public final void A0() {
    }

    public final void H0() {
        this.f17614z = false;
        this.f17613y.f17615m = null;
        AbstractC0156f.m(this);
    }

    @Override // E0.f0
    public final void L() {
        H0();
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [A7.o, z7.k] */
    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        boolean z6 = this.f17614z;
        C1591c c1591c = this.f17613y;
        if (!z6) {
            c1591c.f17615m = null;
            AbstractC0156f.q(this, new C1134u(this, 1, c1591c));
            if (c1591c.f17615m == null) {
                l9.d.i0("DrawResult not defined, did you forget to call onDraw?");
                throw null;
            }
            this.f17614z = true;
        }
        e7.f fVar = c1591c.f17615m;
        m.c(fVar);
        ((o) fVar.f14480m).b(h10);
    }

    @Override // k0.InterfaceC1589a
    public final long e() {
        return H7.H.S(AbstractC0156f.r(this, 128).f872n);
    }

    @Override // k0.InterfaceC1589a
    public final a1.b getDensity() {
        return AbstractC0156f.t(this).f1464C;
    }

    @Override // k0.InterfaceC1589a
    public final k getLayoutDirection() {
        return AbstractC0156f.t(this).f1465D;
    }

    @Override // E0.InterfaceC0165o
    public final void n0() {
        H0();
    }
}
