package A;

import F0.AbstractC0227s0;
import K.C0343h0;
import S0.AbstractC0703o;
import S0.InterfaceC0702n;
import T.C0735d;
import T.C0741g;
import T.C0753m;
import T.C0761q;
import x.C2650p;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class E extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ E(Object obj, int i7) {
        super(3);
        this.f15m = i7;
        this.f16n = obj;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        T.S s6 = C0753m.f8270a;
        n7.v vVar = n7.v.l;
        int i7 = 1;
        Object obj4 = this.f16n;
        switch (this.f15m) {
            case 0:
                long j3 = ((m0.c) obj3).f18880a;
                ((C0343h0) obj4).f3287n.c(((y0.q) obj2).c);
                return m7.q.f19267a;
            case 1:
                long j10 = ((a1.a) obj3).f11027a;
                long j11 = ((K.F0) obj4).f3156f;
                C0.V vB = ((C0.J) obj2).b(a1.a.a(j10, AbstractC2673a.v((int) (j11 >> 32), a1.a.j(j10), a1.a.h(j10)), 0, AbstractC2673a.v((int) (j11 & 4294967295L), a1.a.i(j10), a1.a.g(j10)), 0, 10));
                return ((C0.M) obj).m0(vB.l, vB.f871m, vVar, new C0.X(vB, 6));
            case 2:
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                c0761q.Q(1582736677);
                a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
                InterfaceC0702n interfaceC0702n = (InterfaceC0702n) c0761q.k(AbstractC0227s0.f2262i);
                a1.k kVar = (a1.k) c0761q.k(AbstractC0227s0.l);
                N0.L l = (N0.L) obj4;
                boolean zF = c0761q.f(l) | c0761q.f(kVar);
                Object objG = c0761q.G();
                if (zF || objG == s6) {
                    objG = P8.n.T(l, kVar);
                    c0761q.a0(objG);
                }
                N0.L l10 = (N0.L) objG;
                boolean zF2 = c0761q.f(interfaceC0702n) | c0761q.f(l10);
                Object objG2 = c0761q.G();
                if (zF2 || objG2 == s6) {
                    N0.D d = l10.f4358a;
                    AbstractC0703o abstractC0703o = d.f4327f;
                    S0.A a10 = d.c;
                    if (a10 == null) {
                        a10 = S0.A.f7800s;
                    }
                    S0.w wVar = d.d;
                    int i10 = wVar != null ? wVar.f7860a : 0;
                    S0.x xVar = d.f4326e;
                    objG2 = ((S0.q) interfaceC0702n).b(abstractC0703o, a10, i10, xVar != null ? xVar.f7861a : 1);
                    c0761q.a0(objG2);
                }
                T.Q0 q02 = (T.Q0) objG2;
                Object objG3 = c0761q.G();
                Object obj5 = objG3;
                if (objG3 == s6) {
                    Object value = q02.getValue();
                    K.F0 f02 = new K.F0();
                    f02.f3153a = kVar;
                    f02.f3154b = bVar;
                    f02.c = interfaceC0702n;
                    f02.d = l;
                    f02.f3155e = value;
                    f02.f3156f = K.s0.a(l, bVar, interfaceC0702n, K.s0.f3382a, 1);
                    c0761q.a0(f02);
                    obj5 = f02;
                }
                K.F0 f03 = (K.F0) obj5;
                Object value2 = q02.getValue();
                if (kVar != f03.f3153a || !A7.m.a(bVar, f03.f3154b) || !A7.m.a(interfaceC0702n, f03.c) || !A7.m.a(l10, f03.d) || !A7.m.a(value2, f03.f3155e)) {
                    f03.f3153a = kVar;
                    f03.f3154b = bVar;
                    f03.c = interfaceC0702n;
                    f03.d = l10;
                    f03.f3155e = value2;
                    f03.f3156f = K.s0.a(l10, bVar, interfaceC0702n, K.s0.f3382a, 1);
                }
                g0.l lVar = g0.l.f14684a;
                boolean zH = c0761q.h(f03);
                Object objG4 = c0761q.G();
                if (zH || objG4 == s6) {
                    objG4 = new E(f03, i7);
                    c0761q.a0(objG4);
                }
                g0.o oVarB = androidx.compose.ui.layout.a.b(lVar, (InterfaceC2786o) objG4);
                c0761q.p(false);
                return oVarB;
            case 3:
                g0.o oVar = (g0.o) obj;
                C0761q c0761q2 = (C0761q) obj2;
                ((Number) obj3).intValue();
                c0761q2.Q(1980580247);
                a1.b bVar2 = (a1.b) c0761q2.k(AbstractC0227s0.f2259f);
                Object objG5 = c0761q2.G();
                if (objG5 == s6) {
                    objG5 = C0735d.O(new a1.j(0L), T.S.f8224q);
                    c0761q2.a0(objG5);
                }
                T.X x8 = (T.X) objG5;
                O.K k10 = (O.K) obj4;
                boolean zH2 = c0761q2.h(k10);
                Object objG6 = c0761q2.G();
                if (zH2 || objG6 == s6) {
                    objG6 = new B8.A(k10, 18, x8);
                    c0761q2.a0(objG6);
                }
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG6;
                boolean zF3 = c0761q2.f(bVar2);
                Object objG7 = c0761q2.G();
                if (zF3 || objG7 == s6) {
                    objG7 = new O.P(bVar2, x8, i7);
                    c0761q2.a0(objG7);
                }
                C2650p c2650p = O.C.f4892a;
                g0.o oVarB2 = g0.a.b(oVar, new N(interfaceC2772a, 2, (InterfaceC2782k) objG7));
                c0761q2.p(false);
                return oVarB2;
            default:
                C0.V vB2 = ((C0.J) obj2).b(((a1.a) obj3).f11027a);
                return ((C0.M) obj).m0(vB2.l, vB2.f871m, vVar, new C0741g(vB2, 23, (w.u) obj4));
        }
    }
}
