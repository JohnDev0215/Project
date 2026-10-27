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
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class I extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u0 f7248m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f7249n;
    public final /* synthetic */ N0.L o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7250p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(u0 u0Var, long j3, N0.L l, InterfaceC2785n interfaceC2785n) {
        super(3);
        this.f7248m = u0Var;
        this.f7249n = j3;
        this.o = l;
        this.f7250p = interfaceC2785n;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        g0.o oVar = (g0.o) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(oVar) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            u0 u0Var = this.f7248m;
            boolean zF = c0761q.f(u0Var);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new H(u0Var, 0);
                c0761q.a0(objG);
            }
            g0.o oVarA = androidx.compose.ui.graphics.a.a(oVar, (InterfaceC2782k) objG);
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
            O.b(this.f7249n, this.o, this.f7250p, c0761q, 0);
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}
