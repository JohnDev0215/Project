package K;

import F0.d1;
import F0.e1;
import T.C0738e0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class A extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3113m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3114n;
    public final /* synthetic */ d1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ O.K f3115p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T0.y f3116q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T0.s f3117r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(C0331b0 c0331b0, boolean z6, d1 d1Var, O.K k10, T0.y yVar, T0.s sVar) {
        super(1);
        this.f3113m = c0331b0;
        this.f3114n = z6;
        this.o = d1Var;
        this.f3115p = k10;
        this.f3116q = yVar;
        this.f3117r = sVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        T0.E e10;
        C0.r rVar;
        C0.r rVar2;
        C0.r rVar3 = (C0.r) obj;
        C0331b0 c0331b0 = this.f3113m;
        c0331b0.f3225h = rVar3;
        G0 g0D = c0331b0.d();
        if (g0D != null) {
            g0D.f3162b = rVar3;
        }
        if (this.f3114n) {
            P pA = c0331b0.a();
            P p10 = P.f3194m;
            T0.y yVar = this.f3116q;
            O.K k10 = this.f3115p;
            C0738e0 c0738e0 = c0331b0.o;
            if (pA == p10) {
                if (((Boolean) c0331b0.l.getValue()).booleanValue() && ((e1) this.o).a()) {
                    k10.o();
                } else {
                    k10.k();
                }
                c0331b0.f3229m.setValue(Boolean.valueOf(l9.l.U(k10, true)));
                c0331b0.f3230n.setValue(Boolean.valueOf(l9.l.U(k10, false)));
                c0738e0.setValue(Boolean.valueOf(N0.K.b(yVar.f8482b)));
            } else if (c0331b0.a() == P.f3195n) {
                c0738e0.setValue(Boolean.valueOf(l9.l.U(k10, true)));
            }
            T0.s sVar = this.f3117r;
            X.t(c0331b0, yVar, sVar);
            G0 g0D2 = c0331b0.d();
            if (g0D2 != null && (e10 = c0331b0.f3222e) != null && c0331b0.b() && (rVar = g0D2.f3162b) != null && rVar.D() && (rVar2 = g0D2.c) != null) {
                A.F f10 = new A.F(rVar, 20);
                m0.d dVarO0 = io.sentry.config.a.o0(rVar);
                m0.d dVarO = rVar.o(rVar2, false);
                if (A7.m.a((T0.E) e10.f8423a.f8484b.get(), e10)) {
                    e10.f8424b.d(yVar, sVar, g0D2.f3161a, f10, dVarO0, dVarO);
                }
            }
        }
        return m7.q.f19267a;
    }
}
