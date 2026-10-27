package F;

import A.EnumC0017h0;
import C0.L;
import G.X;
import G.a0;
import H7.H;
import r7.EnumC2303a;
import y.j0;

/* JADX INFO: loaded from: classes.dex */
public final class A implements X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y f1862a;

    public A(y yVar) {
        this.f1862a = yVar;
    }

    @Override // G.X
    public final int a() {
        long jC;
        y yVar = this.f1862a;
        if (yVar.g().f1924k == EnumC0017h0.l) {
            L l = yVar.g().f1926n;
            jC = H.c(l.e(), l.c()) & 4294967295L;
        } else {
            L l10 = yVar.g().f1926n;
            jC = H.c(l10.e(), l10.c()) >> 32;
        }
        return (int) jC;
    }

    @Override // G.X
    public final float b() {
        y yVar = this.f1862a;
        return (yVar.f1974b.f1443b.i() * 500) + yVar.f1974b.c.i();
    }

    @Override // G.X
    public final L0.b c() {
        return new L0.b(-1, -1);
    }

    @Override // G.X
    public final int d() {
        y yVar = this.f1862a;
        return (-yVar.g().f1921h) + yVar.g().l;
    }

    @Override // G.X
    public final float e() {
        y yVar = this.f1862a;
        int i7 = yVar.f1974b.f1443b.i();
        int i10 = yVar.f1974b.c.i();
        return yVar.c() ? (i7 * 500) + i10 + 100 : (i7 * 500) + i10;
    }

    @Override // G.X
    public final Object f(int i7, a0 a0Var) throws Throwable {
        io.sentry.internal.debugmeta.c cVar = y.f1972t;
        y yVar = this.f1862a;
        yVar.getClass();
        Object objE = yVar.e(j0.l, new x(yVar, i7, 0, null), a0Var);
        EnumC2303a enumC2303a = EnumC2303a.l;
        m7.q qVar = m7.q.f19267a;
        if (objE != enumC2303a) {
            objE = qVar;
        }
        return objE == enumC2303a ? objE : qVar;
    }
}
