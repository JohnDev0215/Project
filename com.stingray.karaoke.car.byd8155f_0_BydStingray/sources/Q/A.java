package Q;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class A extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5486m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5487n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A(InterfaceC2785n interfaceC2785n, int i7) {
        super(2);
        this.f5486m = i7;
        this.f5487n = interfaceC2785n;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5486m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    g0.o oVarA = androidx.compose.foundation.layout.c.a(g0.l.f14684a, S.g.d, Float.NaN);
                    C0.K kE = AbstractC0133o.e(g0.b.l, false);
                    int i7 = c0761q.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                    g0.o oVarD = g0.a.d(c0761q, oVarA);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q.U();
                    if (c0761q.f8303O) {
                        c0761q.l(c0159i);
                    } else {
                        c0761q.d0();
                    }
                    C0735d.V(c0761q, kE, C0160j.f1679e);
                    C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                        AbstractC1347d.l(i7, c0761q, i7, c0158h);
                    }
                    C0735d.V(c0761q, oVarD, C0160j.c);
                    this.f5487n.invoke(c0761q, 0);
                    c0761q.p(true);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    g0.o oVarA2 = androidx.compose.foundation.layout.c.a(g0.l.f14684a, S.g.f7644e, Float.NaN);
                    C0.K kE2 = AbstractC0133o.e(g0.b.l, false);
                    int i10 = c0761q2.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M2 = c0761q2.m();
                    g0.o oVarD2 = g0.a.d(c0761q2, oVarA2);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i2 = C0160j.f1678b;
                    c0761q2.U();
                    if (c0761q2.f8303O) {
                        c0761q2.l(c0159i2);
                    } else {
                        c0761q2.d0();
                    }
                    C0735d.V(c0761q2, kE2, C0160j.f1679e);
                    C0735d.V(c0761q2, interfaceC0744h0M2, C0160j.d);
                    C0158h c0158h2 = C0160j.f1680f;
                    if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i10))) {
                        AbstractC1347d.l(i10, c0761q2, i10, c0158h2);
                    }
                    C0735d.V(c0761q2, oVarD2, C0160j.c);
                    this.f5487n.invoke(c0761q2, 0);
                    c0761q2.p(true);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    g0.o oVarC = androidx.compose.ui.layout.a.c(g0.l.f14684a, "Container");
                    C0.K kE3 = AbstractC0133o.e(g0.b.l, true);
                    int i11 = c0761q3.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M3 = c0761q3.m();
                    g0.o oVarD3 = g0.a.d(c0761q3, oVarC);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i3 = C0160j.f1678b;
                    c0761q3.U();
                    if (c0761q3.f8303O) {
                        c0761q3.l(c0159i3);
                    } else {
                        c0761q3.d0();
                    }
                    C0735d.V(c0761q3, kE3, C0160j.f1679e);
                    C0735d.V(c0761q3, interfaceC0744h0M3, C0160j.d);
                    C0158h c0158h3 = C0160j.f1680f;
                    if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i11))) {
                        AbstractC1347d.l(i11, c0761q3, i11, c0158h3);
                    }
                    C0735d.V(c0761q3, oVarD3, C0160j.c);
                    this.f5487n.invoke(c0761q3, 0);
                    c0761q3.p(true);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
