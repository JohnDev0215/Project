package A;

import C0.C0117y;
import D.AbstractC0127i;
import D.C0138u;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import Q.AbstractC0500d;
import Q5.C0602o0;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0657j;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import U.C0800a;
import U.C0801b;
import e1.C1190c;
import e1.C1191d;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.List;
import n3.C1945j;
import o3.C2035h;
import o3.C2041n;
import w.C2576g;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class L extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f66m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f67n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ L(Object obj, int i7, Object obj2) {
        super(2);
        this.f66m = i7;
        this.f67n = obj;
        this.o = obj2;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f66m) {
            case 0:
                long j3 = ((m0.c) obj2).f18880a;
                h6.q.k((z0.c) this.f67n, (y0.q) obj);
                U8.l lVar = ((V) this.o).f123E;
                if (lVar != null) {
                    lVar.p(new C0047x(j3));
                }
                return m7.q.f19267a;
            case 1:
                float fFloatValue = ((Number) obj).floatValue();
                ((Number) obj2).floatValue();
                A7.w wVar = (A7.w) this.f67n;
                float f10 = wVar.l;
                wVar.l = ((InterfaceC0033p0) this.o).a(fFloatValue - f10) + f10;
                return m7.q.f19267a;
            case 2:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    Boolean bool = (Boolean) ((C0117y) this.f67n).f924f.getValue();
                    boolean zBooleanValue = bool.booleanValue();
                    c0761q.T(bool);
                    boolean zG = c0761q.g(zBooleanValue);
                    c0761q.Q(-869707859);
                    if (zBooleanValue) {
                        ((InterfaceC2785n) this.o).invoke(c0761q, 0);
                    } else {
                        if (!(c0761q.f8313k == 0)) {
                            C0735d.y("No nodes can be emitted before calling dactivateToEndGroup");
                            throw null;
                        }
                        if (!c0761q.f8303O) {
                            if (zG) {
                                T.y0 y0Var = c0761q.f8294F;
                                int i7 = y0Var.f8393g;
                                int i10 = y0Var.f8394h;
                                C0801b c0801b = c0761q.f8300L;
                                c0801b.getClass();
                                c0801b.d(false);
                                C0800a c0800a = c0801b.f8944b;
                                c0800a.getClass();
                                c0800a.d.V(U.f.c);
                                C0735d.s(c0761q.f8318r, i7, i10);
                                c0761q.f8294F.m();
                            } else {
                                c0761q.J();
                            }
                        }
                    }
                    c0761q.p(false);
                    if (c0761q.f8324x && c0761q.f8294F.f8395i == c0761q.f8325y) {
                        c0761q.f8325y = -1;
                        c0761q.f8324x = false;
                    }
                    c0761q.p(false);
                }
                return m7.q.f19267a;
            case 3:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    ((b0.b) this.f67n).g((C0138u) this.o, c0761q2, 0);
                }
                return m7.q.f19267a;
            case 4:
                C0.g0 g0Var = (C0.g0) obj;
                long j10 = ((a1.a) obj2).f11027a;
                return ((C0.K) this.f67n).h(g0Var, g0Var.W(m7.q.f19267a, new b0.b(-1945019079, new L((b0.b) this.o, 3, new C0138u(g0Var, j10)), true)), j10);
            case 5:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    G.G g7 = (G.G) this.f67n;
                    G.I i11 = (G.I) g7.f2504b.a();
                    G.F f11 = (G.F) this.o;
                    int iA = f11.c;
                    int iB = i11.b();
                    Object obj3 = f11.f2500a;
                    if ((iA >= iB || !i11.c(iA).equals(obj3)) && (iA = i11.a(obj3)) != -1) {
                        f11.c = iA;
                    }
                    int i12 = iA;
                    if (i12 != -1) {
                        c0761q3.Q(-660479623);
                        G.E.d(i11, g7.f2503a, i12, f11.f2500a, c0761q3, 0);
                        c0761q3.p(false);
                    } else {
                        c0761q3.Q(-660272047);
                        c0761q3.p(false);
                    }
                    boolean zH = c0761q3.h(f11);
                    Object objG = c0761q3.G();
                    if (zH || objG == C0753m.f8270a) {
                        objG = new F(f11, 17);
                        c0761q3.a0(objG);
                    }
                    C0735d.d(obj3, (InterfaceC2782k) objG, c0761q3);
                }
                return m7.q.f19267a;
            case 6:
                return (C0.L) ((InterfaceC2785n) this.o).invoke(new G.M((G.G) this.f67n, (C0.g0) obj), new a1.a(((a1.a) obj2).f11027a));
            case 7:
                C0761q c0761q4 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    d0.h hVarS = AbstractC2673a.S(c0761q4);
                    G.e0 e0Var = (G.e0) this.f67n;
                    e0Var.f2555b.setValue(hVarS);
                    ((b0.b) this.o).g(e0Var, c0761q4, 0);
                }
                return m7.q.f19267a;
            case 8:
                C0761q c0761q5 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q5.x()) {
                    c0761q5.K();
                } else {
                    g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.a(g0.l.f14684a, AbstractC0500d.f5852b, AbstractC0500d.c), (D.L) this.f67n);
                    D.S sB = D.P.b(AbstractC0127i.d, g0.b.f14670v, c0761q5, 54);
                    int i13 = c0761q5.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q5.m();
                    g0.o oVarD = g0.a.d(c0761q5, oVarJ);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q5.U();
                    if (c0761q5.f8303O) {
                        c0761q5.l(c0159i);
                    } else {
                        c0761q5.d0();
                    }
                    C0735d.V(c0761q5, sB, C0160j.f1679e);
                    C0735d.V(c0761q5, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q5.f8303O || !A7.m.a(c0761q5.G(), Integer.valueOf(i13))) {
                        AbstractC1347d.l(i13, c0761q5, i13, c0158h);
                    }
                    C0735d.V(c0761q5, oVarD, C0160j.c);
                    ((b0.b) this.o).g(D.T.f1050a, c0761q5, 6);
                    c0761q5.p(true);
                }
                return m7.q.f19267a;
            case 9:
                C0761q c0761q6 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q6.x()) {
                    c0761q6.K();
                } else {
                    Q.c1.a(((Q.h1) this.f67n).f5917j, (b0.b) this.o, c0761q6, 0);
                }
                return m7.q.f19267a;
            case 10:
                C0761q c0761q7 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q7.x()) {
                    c0761q7.K();
                } else {
                    ((InterfaceC2786o) this.f67n).g((ArrayList) this.o, c0761q7, 0);
                }
                return m7.q.f19267a;
            case 11:
                return new F.b(((F.b) ((C0602o0) this.f67n).invoke((F.t) obj, ((List) this.o).get(((Number) obj2).intValue()))).f1864a);
            case 12:
                float fFloatValue2 = ((Number) obj).floatValue();
                float fFloatValue3 = ((Number) obj2).floatValue();
                R.w wVar2 = ((R.u) this.f67n).f7323a;
                wVar2.f7332i.j(fFloatValue2);
                wVar2.f7333j.j(fFloatValue3);
                ((A7.w) this.o).l = fFloatValue2;
                return m7.q.f19267a;
            case 13:
                C0761q c0761q8 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q8.x()) {
                    c0761q8.K();
                } else {
                    Z0.a.c((String) this.f67n, (String) this.o, c0761q8, new Object[0]);
                }
                return m7.q.f19267a;
            case 14:
                C0761q c0761q9 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q9.x()) {
                    c0761q9.K();
                } else {
                    g0.o oVarA = L0.k.a(g0.l.f14684a, false, C1190c.f13768q);
                    e1.x xVar = (e1.x) this.f67n;
                    boolean zH2 = c0761q9.h(xVar);
                    Object objG2 = c0761q9.G();
                    if (zH2 || objG2 == C0753m.f8270a) {
                        objG2 = new e1.k(xVar, 1);
                        c0761q9.a0(objG2);
                    }
                    g0.o oVarE = h6.n.e(androidx.compose.ui.layout.a.e(oVarA, (InterfaceC2782k) objG2), xVar.getCanCalculatePosition() ? 1.0f : 0.0f);
                    b0.b bVarB = b0.g.b(606497925, new C1191d((T.X) this.o, 2), c0761q9);
                    e1.f fVar = e1.f.c;
                    int i14 = c0761q9.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M2 = c0761q9.m();
                    g0.o oVarD2 = g0.a.d(c0761q9, oVarE);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i2 = C0160j.f1678b;
                    c0761q9.U();
                    if (c0761q9.f8303O) {
                        c0761q9.l(c0159i2);
                    } else {
                        c0761q9.d0();
                    }
                    C0735d.V(c0761q9, fVar, C0160j.f1679e);
                    C0735d.V(c0761q9, interfaceC0744h0M2, C0160j.d);
                    C0158h c0158h2 = C0160j.f1680f;
                    if (c0761q9.f8303O || !A7.m.a(c0761q9.G(), Integer.valueOf(i14))) {
                        AbstractC1347d.l(i14, c0761q9, i14, c0158h2);
                    }
                    C0735d.V(c0761q9, oVarD2, C0160j.c);
                    A7.k.o(6, bVarB, c0761q9, true);
                }
                return m7.q.f19267a;
            case 15:
                C0761q c0761q10 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q10.x()) {
                    c0761q10.K();
                } else {
                    ((C2041n) this.f67n).f20479v.g((C1945j) this.o, c0761q10, 0);
                }
                return m7.q.f19267a;
            case 16:
                C0761q c0761q11 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q11.x()) {
                    c0761q11.K();
                } else {
                    h6.j.g((d0.h) this.f67n, (b0.b) this.o, c0761q11, 0);
                }
                return m7.q.f19267a;
            case 17:
                C0761q c0761q12 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q12.x()) {
                    c0761q12.K();
                } else {
                    C1945j c1945j = (C1945j) this.f67n;
                    n3.y yVar = c1945j.f19865m;
                    A7.m.d(yVar, "null cannot be cast to non-null type androidx.navigation.compose.ComposeNavigator.Destination");
                    ((C2035h) yVar).f20460u.o((C2576g) this.o, c1945j, c0761q12, 0);
                }
                return m7.q.f19267a;
            default:
                return Boolean.valueOf(A7.m.a((InterfaceC0657j) obj, (InterfaceC0649b) this.f67n) && A7.m.a((InterfaceC0657j) obj2, (InterfaceC0649b) this.o));
        }
    }
}
