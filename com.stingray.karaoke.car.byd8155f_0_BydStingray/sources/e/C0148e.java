package E;

import A.EnumC0017h0;
import G.X;
import G.a0;
import r7.EnumC2303a;

/* JADX INFO: renamed from: E.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0148e implements X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ D f1349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f1350b;

    public C0148e(D d, boolean z6) {
        this.f1349a = d;
        this.f1350b = z6;
    }

    @Override // G.X
    public final int a() {
        D d = this.f1349a;
        return (int) (d.h().o == EnumC0017h0.l ? d.h().f() & 4294967295L : d.h().f() >> 32);
    }

    @Override // G.X
    public final float b() {
        D d = this.f1349a;
        return (d.d.f1443b.i() * 500) + d.d.c.i();
    }

    @Override // G.X
    public final L0.b c() {
        return this.f1350b ? new L0.b(-1, 1) : new L0.b(1, -1);
    }

    @Override // G.X
    public final int d() {
        D d = this.f1349a;
        return (-d.h().f1416k) + d.h().f1419p;
    }

    @Override // G.X
    public final float e() {
        D d = this.f1349a;
        int i7 = d.d.f1443b.i();
        int i10 = d.d.c.i();
        return d.c() ? (i7 * 500) + i10 + 100 : (i7 * 500) + i10;
    }

    @Override // G.X
    public final Object f(int i7, a0 a0Var) throws Throwable {
        io.sentry.internal.debugmeta.c cVar = D.f1301x;
        Object objJ = this.f1349a.j(i7, 0, a0Var);
        return objJ == EnumC2303a.l ? objJ : m7.q.f19267a;
    }
}
