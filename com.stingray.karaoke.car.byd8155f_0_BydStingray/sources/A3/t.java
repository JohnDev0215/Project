package A3;

import C0.InterfaceC0115w;
import C0.J;
import C0.L;
import C0.M;
import C0.V;
import V8.AbstractC0828q;
import V8.f0;

/* JADX INFO: loaded from: classes.dex */
public final class t implements L3.h, InterfaceC0115w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f404a = AbstractC0828q.c(new a1.a(z.f408a));

    @Override // C0.InterfaceC0115w
    public final L g(M m10, J j3, long j10) {
        a1.a aVar = new a1.a(j10);
        f0 f0Var = this.f404a;
        f0Var.getClass();
        f0Var.m(null, aVar);
        V vB = j3.b(j10);
        return m10.m0(vB.l, vB.f871m, n7.v.l, new r(vB, 0));
    }

    @Override // L3.h
    public final Object i(z3.f fVar) {
        return AbstractC0828q.j(new n(this.f404a, 1), fVar);
    }
}
