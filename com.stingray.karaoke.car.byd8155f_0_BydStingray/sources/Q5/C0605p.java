package Q5;

import D.InterfaceC0123e;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0605p implements InterfaceC2785n {
    public final /* synthetic */ float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D.L f7007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0123e f7008n;
    public final /* synthetic */ b0.b o;

    public C0605p(float f10, D.L l, InterfaceC0123e interfaceC0123e, b0.b bVar) {
        this.l = f10;
        this.f7007m = l;
        this.f7008n = interfaceC0123e;
        this.o = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.h(g0.l.f14684a, this.l), this.f7007m);
            D.S sB = D.P.b(this.f7008n, g0.b.f14670v, c0761q, 48);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarJ);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                AbstractC1347d.l(i7, c0761q, i7, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            this.o.g(D.T.f1050a, c0761q, 6);
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}
