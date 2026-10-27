package u2;

import h2.AbstractC1346c;
import java.util.ArrayList;

/* JADX INFO: renamed from: u2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2503f extends o0 {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22813m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f22814n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f22815p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f22816q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final e2.e0 f22817r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C2501d f22818s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C2502e f22819t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f22820u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f22821v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2503f(AbstractC2498a abstractC2498a, long j3, long j10, boolean z6, boolean z10, boolean z11) {
        super(abstractC2498a);
        abstractC2498a.getClass();
        AbstractC1346c.c(j3 >= 0);
        this.l = j3;
        this.f22813m = j10;
        this.f22814n = z6;
        this.o = z10;
        this.f22815p = z11;
        this.f22816q = new ArrayList();
        this.f22817r = new e2.e0();
    }

    public final void B(e2.f0 f0Var) {
        long j3;
        long j10;
        long j11;
        e2.e0 e0Var = this.f22817r;
        f0Var.n(0, e0Var);
        long j12 = e0Var.f14096p;
        C2501d c2501d = this.f22818s;
        ArrayList arrayList = this.f22816q;
        long j13 = this.f22813m;
        if (c2501d == null || arrayList.isEmpty() || this.o) {
            boolean z6 = this.f22815p;
            long j14 = this.l;
            if (z6) {
                long j15 = e0Var.l;
                j14 += j15;
                j3 = j15 + j13;
            } else {
                j3 = j13;
            }
            this.f22820u = j12 + j14;
            this.f22821v = j13 != Long.MIN_VALUE ? j12 + j3 : Long.MIN_VALUE;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                C2500c c2500c = (C2500c) arrayList.get(i7);
                long j16 = this.f22820u;
                long j17 = this.f22821v;
                c2500c.f22800p = j16;
                c2500c.f22801q = j17;
            }
            j10 = j14;
            j11 = j3;
        } else {
            long j18 = this.f22820u - j12;
            j11 = j13 != Long.MIN_VALUE ? this.f22821v - j12 : Long.MIN_VALUE;
            j10 = j18;
        }
        try {
            C2501d c2501d2 = new C2501d(f0Var, j10, j11);
            this.f22818s = c2501d2;
            m(c2501d2);
        } catch (C2502e e10) {
            this.f22819t = e10;
            for (int i10 = 0; i10 < arrayList.size(); i10++) {
                ((C2500c) arrayList.get(i10)).f22802r = this.f22819t;
            }
        }
    }

    @Override // u2.AbstractC2498a
    public final boolean a(e2.F f10) {
        AbstractC2498a abstractC2498a = this.f22886k;
        return abstractC2498a.h().f13880e.equals(f10.f13880e) && abstractC2498a.a(f10);
    }

    @Override // u2.AbstractC2498a
    public final InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3) {
        C2500c c2500c = new C2500c(this.f22886k.b(c2492c, eVar, j3), this.f22814n, this.f22820u, this.f22821v);
        this.f22816q.add(c2500c);
        return c2500c;
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void j() throws C2502e {
        C2502e c2502e = this.f22819t;
        if (c2502e != null) {
            throw c2502e;
        }
        super.j();
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
        ArrayList arrayList = this.f22816q;
        AbstractC1346c.j(arrayList.remove(interfaceC2490A));
        this.f22886k.n(((C2500c) interfaceC2490A).l);
        if (!arrayList.isEmpty() || this.o) {
            return;
        }
        C2501d c2501d = this.f22818s;
        c2501d.getClass();
        B(c2501d.f22896e);
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void p() {
        super.p();
        this.f22819t = null;
        this.f22818s = null;
    }

    @Override // u2.o0
    public final void z(e2.f0 f0Var) {
        if (this.f22819t != null) {
            return;
        }
        B(f0Var);
    }
}
