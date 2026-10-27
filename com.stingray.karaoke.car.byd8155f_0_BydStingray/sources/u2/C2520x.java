package u2;

import h2.AbstractC1346c;

/* JADX INFO: renamed from: u2.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2520x extends o0 {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e2.e0 f22910m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e2.c0 f22911n;
    public C2518v o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C2517u f22912p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f22913q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f22914r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f22915s;

    public C2520x(AbstractC2498a abstractC2498a, boolean z6) {
        super(abstractC2498a);
        this.l = z6 && abstractC2498a.i();
        this.f22910m = new e2.e0();
        this.f22911n = new e2.c0();
        e2.f0 f0VarG = abstractC2498a.g();
        if (f0VarG == null) {
            this.o = new C2518v(new C2519w(abstractC2498a.h()), e2.e0.f14075q, C2518v.f22906h);
        } else {
            this.o = new C2518v(f0VarG, null, null);
            this.f22915s = true;
        }
    }

    @Override // u2.o0
    public final void A() {
        if (this.l) {
            return;
        }
        this.f22913q = true;
        x(null, this.f22886k);
    }

    @Override // u2.AbstractC2498a
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final C2517u b(C2492C c2492c, x2.e eVar, long j3) {
        C2517u c2517u = new C2517u(c2492c, eVar, j3);
        AbstractC1346c.j(c2517u.o == null);
        AbstractC2498a abstractC2498a = this.f22886k;
        c2517u.o = abstractC2498a;
        if (this.f22914r) {
            Object obj = this.o.f22908g;
            Object obj2 = c2492c.f22663a;
            if (obj != null && obj2.equals(C2518v.f22906h)) {
                obj2 = this.o.f22908g;
            }
            c2517u.f(c2492c.a(obj2));
        } else {
            this.f22912p = c2517u;
            if (!this.f22913q) {
                this.f22913q = true;
                x(null, abstractC2498a);
            }
        }
        return c2517u;
    }

    public final boolean C(long j3) {
        C2517u c2517u = this.f22912p;
        int iB = this.o.b(c2517u.l.f22663a);
        if (iB == -1) {
            return false;
        }
        C2518v c2518v = this.o;
        e2.c0 c0Var = this.f22911n;
        c2518v.f(iB, c0Var, false);
        long j10 = c0Var.d;
        if (j10 != -9223372036854775807L && j3 >= j10) {
            j3 = Math.max(0L, j10 - 1);
        }
        c2517u.f22905r = j3;
        return true;
    }

    @Override // u2.AbstractC2498a
    public final boolean a(e2.F f10) {
        return this.f22886k.a(f10);
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void j() {
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
        C2517u c2517u = (C2517u) interfaceC2490A;
        if (c2517u.f22903p != null) {
            AbstractC2498a abstractC2498a = c2517u.o;
            abstractC2498a.getClass();
            abstractC2498a.n(c2517u.f22903p);
        }
        if (interfaceC2490A == this.f22912p) {
            this.f22912p = null;
        }
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void p() {
        this.f22914r = false;
        this.f22913q = false;
        super.p();
    }

    @Override // u2.o0, u2.AbstractC2498a
    public final void s(e2.F f10) {
        if (this.f22915s) {
            C2518v c2518v = this.o;
            this.o = new C2518v(new n2.Y(this.o.f22896e, f10), c2518v.f22907f, c2518v.f22908g);
        } else {
            this.o = new C2518v(new C2519w(f10), e2.e0.f14075q, C2518v.f22906h);
        }
        this.f22886k.s(f10);
    }

    @Override // u2.o0
    public final C2492C y(C2492C c2492c) {
        Object obj = c2492c.f22663a;
        Object obj2 = this.o.f22908g;
        if (obj2 != null && obj2.equals(obj)) {
            obj = C2518v.f22906h;
        }
        return c2492c.a(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // u2.o0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z(e2.f0 r15) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.C2520x.z(e2.f0):void");
    }
}
