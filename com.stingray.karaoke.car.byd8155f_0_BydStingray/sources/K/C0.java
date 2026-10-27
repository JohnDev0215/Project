package K;

import A.C0043v;
import A.EnumC0017h0;
import F0.AbstractC0227s0;
import Q.C0497b0;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class C0 extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3129m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C.k f3130n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3131p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0(C.k kVar, Q.U u4, boolean z6) {
        super(3);
        this.f3130n = kVar;
        this.f3131p = u4;
        this.o = z6;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.f3129m) {
            case 0:
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                c0761q.Q(805428266);
                boolean z6 = c0761q.k(AbstractC0227s0.l) == a1.k.f11038m;
                E0 e02 = (E0) this.f3131p;
                boolean z10 = ((EnumC0017h0) e02.f3144e.getValue()) == EnumC0017h0.l || !z6;
                boolean zF = c0761q.f(e02);
                Object objG = c0761q.G();
                T.S s6 = C0753m.f8270a;
                if (zF || objG == s6) {
                    objG = new A.F(e02, 21);
                    c0761q.a0(objG);
                }
                T.X xR = C0735d.R((InterfaceC2782k) objG, c0761q);
                Object objG2 = c0761q.G();
                if (objG2 == s6) {
                    C0043v c0043v = new C0043v(new A.L0(xR, 0));
                    c0761q.a0(c0043v);
                    objG2 = c0043v;
                }
                A.K0 k02 = (A.K0) objG2;
                boolean zF2 = c0761q.f(k02) | c0761q.f(e02);
                Object objG3 = c0761q.G();
                if (zF2 || objG3 == s6) {
                    objG3 = new B0(k02, e02);
                    c0761q.a0(objG3);
                }
                g0.o oVarB = androidx.compose.foundation.gestures.a.b(g0.l.f14684a, (B0) objG3, (EnumC0017h0) e02.f3144e.getValue(), null, this.o && e02.f3143b.i() != 0.0f, z10, null, this.f3130n, null);
                c0761q.p(false);
                return oVarB;
            default:
                ((Number) obj3).intValue();
                C0497b0.f5820a.a(this.f3130n, null, (Q.U) this.f3131p, this.o, 0L, (C0761q) obj2, 196608);
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0(E0 e02, boolean z6, C.k kVar) {
        super(3);
        this.f3131p = e02;
        this.o = z6;
        this.f3130n = kVar;
    }
}
