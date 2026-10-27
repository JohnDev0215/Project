package R;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import x.u0;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7251m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ u0 f7252n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ N0.L f7253p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7254q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ J(u0 u0Var, long j3, N0.L l, InterfaceC2785n interfaceC2785n, int i7) {
        super(2);
        this.f7251m = i7;
        this.f7252n = u0Var;
        this.o = j3;
        this.f7253p = l;
        this.f7254q = interfaceC2785n;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7251m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    g0.l lVar = g0.l.f14684a;
                    u0 u0Var = this.f7252n;
                    boolean zF = c0761q.f(u0Var);
                    Object objG = c0761q.G();
                    if (zF || objG == C0753m.f8270a) {
                        objG = new H(u0Var, 1);
                        c0761q.a0(objG);
                    }
                    g0.o oVarA = androidx.compose.ui.graphics.a.a(lVar, (InterfaceC2782k) objG);
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
                    O.b(this.o, this.f7253p, this.f7254q, c0761q, 0);
                    c0761q.p(true);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    g0.l lVar2 = g0.l.f14684a;
                    u0 u0Var2 = this.f7252n;
                    boolean zF2 = c0761q2.f(u0Var2);
                    Object objG2 = c0761q2.G();
                    if (zF2 || objG2 == C0753m.f8270a) {
                        objG2 = new H(u0Var2, 2);
                        c0761q2.a0(objG2);
                    }
                    g0.o oVarA2 = androidx.compose.ui.graphics.a.a(lVar2, (InterfaceC2782k) objG2);
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
                    O.b(this.o, this.f7253p, this.f7254q, c0761q2, 0);
                    c0761q2.p(true);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
