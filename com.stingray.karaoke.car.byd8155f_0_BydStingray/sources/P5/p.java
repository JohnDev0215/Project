package P5;

import A.EnumC0017h0;
import E.D;
import S8.AbstractC0727y;
import S8.k0;
import T.C0735d;
import T.C0738e0;
import T.S;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import r7.EnumC2303a;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X8.c f5380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5381b;
    public final InterfaceC2785n c;
    public final InterfaceC2782k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2785n f5382e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y f5383f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0738e0 f5384g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final U8.l f5385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final U8.l f5386i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0738e0 f5387j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f5388k;
    public k0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f5389m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f5390n;
    public final D o;

    public p(D d, X8.c cVar, float f10, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n2, y yVar) {
        A7.m.f(d, "listState");
        A7.m.f(yVar, "dragCancelledAnimation");
        A7.m.f(yVar, "dragCancelledAnimation");
        this.f5380a = cVar;
        this.f5381b = f10;
        this.c = interfaceC2785n;
        this.d = interfaceC2782k;
        this.f5382e = interfaceC2785n2;
        this.f5383f = yVar;
        S s6 = S.f8224q;
        this.f5384g = C0735d.O(null, s6);
        this.f5385h = AbstractC2673a.a(0, 7, null);
        this.f5386i = AbstractC2673a.a(0, 7, null);
        this.f5387j = C0735d.O(new m0.c(0L), s6);
        this.f5388k = C0735d.O(null, s6);
        this.f5389m = new ArrayList();
        this.f5390n = new ArrayList();
        this.o = d;
    }

    public final float a(float f10, long j3) {
        float fG;
        float fJ;
        float fD;
        float fS;
        if (i() == null) {
            return 0.0f;
        }
        if (q()) {
            fG = h() + o(r0);
            fJ = j((E.u) r0) + fG;
            fD = m0.c.e(e());
        } else {
            fG = g() + m(r0);
            fJ = (q() ? 0 : ((E.u) r0).f1434q) + fG;
            fD = m0.c.d(e());
        }
        if (fD > 0.0f) {
            float f11 = fJ - this.o.h().l;
            fS = f11 == 0.0f ? 50.0f : AbstractC2673a.r(f11, 0.0f);
        } else if (fD < 0.0f) {
            float f12 = fG - this.o.h().f1416k;
            fS = f12 == 0.0f ? -50.0f : AbstractC2673a.s(f12, 0.0f);
        } else {
            fS = 0.0f;
        }
        int i7 = (int) (fJ - fG);
        if (fS == 0.0f) {
            return 0.0f;
        }
        float fMin = Math.min(1.0f, 15.0f / i7);
        float fSignum = Math.signum(fS) * f10;
        float f13 = 1;
        float f14 = f13 - fMin;
        float f15 = (f13 - (((f14 * f14) * f14) * f14)) * fSignum;
        float f16 = j3 > 1 ? 1.0f : j3 / 1;
        float f17 = f16 * f16 * f16 * f16 * f16 * f15;
        return f17 == 0.0f ? fS > 0.0f ? 1.0f : -1.0f : f17;
    }

    public final Object b(Object obj, ArrayList arrayList, int i7, int i10) {
        int iAbs;
        int iAbs2;
        int iM;
        int iAbs3;
        int iN;
        int iAbs4;
        A7.m.f(arrayList, "items");
        E.u uVar = (E.u) obj;
        int i11 = (q() ? 0 : uVar.f1434q) + i7;
        int iJ = j(uVar) + i10;
        int iM2 = i7 - m(obj);
        int iO = i10 - o(obj);
        int size = arrayList.size();
        Object obj2 = null;
        int i12 = -1;
        for (int i13 = 0; i13 < size; i13++) {
            Object obj3 = arrayList.get(i13);
            if (iM2 > 0 && (iN = n(obj3) - i11) < 0 && n(obj3) > n(obj) && (iAbs4 = Math.abs(iN)) > i12) {
                obj2 = obj3;
                i12 = iAbs4;
            }
            if (iM2 < 0 && (iM = m(obj3) - i7) > 0 && m(obj3) < m(obj) && (iAbs3 = Math.abs(iM)) > i12) {
                obj2 = obj3;
                i12 = iAbs3;
            }
            if (iO < 0) {
                E.u uVar2 = (E.u) obj3;
                int iJ2 = ((j(uVar2) / 2) + o(obj3)) - i10;
                if (iJ2 > 0) {
                    if ((j(uVar2) / 2) + o(obj3) < o(obj) && (iAbs2 = Math.abs(iJ2)) > i12) {
                        obj2 = obj3;
                        i12 = iAbs2;
                    }
                }
            }
            if (iO > 0) {
                E.u uVar3 = (E.u) obj3;
                int iD = (d(obj3) - (j(uVar3) / 2)) - iJ;
                if (iD < 0 && d(obj3) - (j(uVar3) / 2) > d(obj) && (iAbs = Math.abs(iD)) > i12) {
                    obj2 = obj3;
                    i12 = iAbs;
                }
            }
        }
        return obj2;
    }

    public final ArrayList c(int i7, int i10, Object obj) {
        int i11;
        p pVar = this;
        ArrayList arrayList = pVar.f5389m;
        arrayList.clear();
        ArrayList arrayList2 = pVar.f5390n;
        arrayList2.clear();
        int iM = pVar.m(obj) + i7;
        int iN = pVar.n(obj) + i7;
        int iO = pVar.o(obj) + i10;
        int iD = pVar.d(obj) + i10;
        int i12 = (iM + iN) / 2;
        int i13 = (iO + iD) / 2;
        List listP = p();
        int size = listP.size();
        int i14 = 0;
        while (i14 < size) {
            Object obj2 = listP.get(i14);
            int iK = pVar.k(obj2);
            Integer numF = f();
            if ((numF != null && iK == numF.intValue()) || pVar.d(obj2) < iO || pVar.o(obj2) > iD || pVar.n(obj2) < iM || pVar.m(obj2) > iN) {
                i11 = iD;
            } else {
                InterfaceC2782k interfaceC2782k = pVar.d;
                if (interfaceC2782k != null) {
                    i11 = iD;
                    if (!((Boolean) interfaceC2782k.b(new g(pVar.k(obj2), pVar.l(obj2)))).booleanValue()) {
                    }
                } else {
                    i11 = iD;
                }
                int iAbs = Math.abs(i12 - ((pVar.n(obj2) + pVar.m(obj2)) / 2));
                int iAbs2 = Math.abs(i13 - ((pVar.d(obj2) + pVar.o(obj2)) / 2));
                int i15 = (iAbs2 * iAbs2) + (iAbs * iAbs);
                int size2 = arrayList.size();
                int i16 = 0;
                for (int i17 = 0; i17 < size2 && i15 > ((Number) arrayList2.get(i17)).intValue(); i17++) {
                    i16++;
                }
                arrayList.add(i16, obj2);
                arrayList2.add(i16, Integer.valueOf(i15));
            }
            i14++;
            pVar = this;
            iD = i11;
        }
        return arrayList;
    }

    public final int d(Object obj) {
        E.u uVar = (E.u) obj;
        A7.m.f(uVar, "<this>");
        if (!q()) {
            return 0;
        }
        D d = this.o;
        return d.h().f1418n ? ((int) (d.h().f() & 4294967295L)) - uVar.f1433p : uVar.f1433p + uVar.f1434q;
    }

    public final long e() {
        return ((m0.c) this.f5387j.getValue()).f18880a;
    }

    public final Integer f() {
        return (Integer) this.f5384g.getValue();
    }

    public final float g() {
        if (i() == null) {
            return 0.0f;
        }
        return (m0.c.d(e()) + (this.f5388k.getValue() != null ? m(r1) : 0)) - m(r0);
    }

    public final float h() {
        if (i() == null) {
            return 0.0f;
        }
        return (m0.c.e(e()) + (this.f5388k.getValue() != null ? o(r1) : 0)) - o(r0);
    }

    public final Object i() {
        for (Object obj : p()) {
            int iK = k(obj);
            Integer numF = f();
            if (numF != null && iK == numF.intValue()) {
                return obj;
            }
        }
        return null;
    }

    public final int j(E.u uVar) {
        A7.m.f(uVar, "<this>");
        if (q()) {
            return uVar.f1434q;
        }
        return 0;
    }

    public final int k(Object obj) {
        E.u uVar = (E.u) obj;
        A7.m.f(uVar, "<this>");
        return uVar.f1422a;
    }

    public final Object l(Object obj) {
        E.u uVar = (E.u) obj;
        A7.m.f(uVar, "<this>");
        return uVar.l;
    }

    public final int m(Object obj) {
        E.u uVar = (E.u) obj;
        A7.m.f(uVar, "<this>");
        if (q()) {
            return 0;
        }
        D d = this.o;
        return d.h().f1418n ? (((int) (d.h().f() >> 32)) - uVar.f1433p) - uVar.f1434q : uVar.f1433p;
    }

    public final int n(Object obj) {
        E.u uVar = (E.u) obj;
        A7.m.f(uVar, "<this>");
        if (q()) {
            return 0;
        }
        D d = this.o;
        return d.h().f1418n ? ((int) (d.h().f() >> 32)) - uVar.f1433p : uVar.f1433p + uVar.f1434q;
    }

    public final int o(Object obj) {
        E.u uVar = (E.u) obj;
        A7.m.f(uVar, "<this>");
        if (!q()) {
            return 0;
        }
        D d = this.o;
        return d.h().f1418n ? (((int) (d.h().f() & 4294967295L)) - uVar.f1433p) - uVar.f1434q : uVar.f1433p;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.List] */
    public final List p() {
        return this.o.h().f1415j;
    }

    public final boolean q() {
        return this.o.h().o == EnumC0017h0.l;
    }

    public final void r(int i7, int i10, boolean z6) {
        Object objI;
        long jA;
        Object value = this.f5388k.getValue();
        if (value == null || (objI = i()) == null) {
            return;
        }
        float fH = h() + o(objI);
        if (fH <= 0.0f) {
            jA = h6.e.a(m0.c.d(e()) + i7, (m0.c.e(e()) + i10) - fH);
        } else {
            E.u uVar = (E.u) objI;
            jA = j(uVar) + fH >= ((float) this.o.h().l) ? h6.e.a(m0.c.d(e()) + i7, (m0.c.e(e()) + i10) - ((j(uVar) + fH) - r6.h().l)) : h6.e.a(m0.c.d(e()) + i7, m0.c.e(e()) + i10);
        }
        this.f5387j.setValue(new m0.c(jA));
        float fG = g() + m(objI);
        Object obj = (E.u) value;
        ArrayList arrayListC = q() ? c(0, (int) m0.c.e(e()), obj) : c((int) m0.c.d(e()), 0, obj);
        int i11 = (int) fG;
        int i12 = (int) fH;
        Object obj2 = (E.u) objI;
        A7.m.f(arrayListC, "items");
        E.u uVar2 = q() ? (E.u) b(obj2, arrayListC, 0, i12) : (E.u) b(obj2, arrayListC, i11, 0);
        Integer numValueOf = uVar2 != null ? Integer.valueOf(k(uVar2)) : null;
        X8.c cVar = this.f5380a;
        if (numValueOf != null) {
            int iK = k(uVar2);
            D d = this.o;
            if (iK == d.d.f1443b.i() || k(objI) == d.d.f1443b.i()) {
                AbstractC0727y.w(cVar, null, null, new t(this, objI, uVar2, null), 3);
            } else {
                InterfaceC2785n interfaceC2785n = this.c;
                if (interfaceC2785n != null) {
                    interfaceC2785n.invoke(new g(k(objI), l(objI)), new g(k(uVar2), l(uVar2)));
                }
            }
            this.f5384g.setValue(Integer.valueOf(k(uVar2)));
        }
        float fA = a(this.f5381b, 0L);
        if (fA != 0.0f && z6) {
            if (fA == 0.0f) {
                k0 k0Var = this.l;
                if (k0Var != null) {
                    k0Var.g(null);
                }
                this.l = null;
                return;
            }
            k0 k0Var2 = this.l;
            if (k0Var2 == null || !k0Var2.b()) {
                this.l = AbstractC0727y.w(cVar, null, null, new s(fA, this, null), 3);
            }
        }
    }

    public final void s() throws IllegalAccessException, InvocationTargetException {
        Integer numF = f();
        C0738e0 c0738e0 = this.f5388k;
        if (numF != null) {
            int iIntValue = numF.intValue();
            Object value = c0738e0.getValue();
            AbstractC0727y.w(this.f5380a, null, null, new u(this, new g(iIntValue, value != null ? l(value) : null), h6.e.a(g(), h()), null), 3);
        }
        Object value2 = c0738e0.getValue();
        Integer numValueOf = value2 != null ? Integer.valueOf(k(value2)) : null;
        Integer numF2 = f();
        Object value3 = c0738e0.getValue();
        Object objL = value3 != null ? l(value3) : null;
        Object value4 = c0738e0.getValue();
        Object objL2 = value4 != null ? l(value4) : null;
        c0738e0.setValue(null);
        this.f5387j.setValue(new m0.c(0L));
        this.f5384g.setValue(null);
        k0 k0Var = this.l;
        if (k0Var != null) {
            k0Var.g(null);
        }
        this.l = null;
        InterfaceC2785n interfaceC2785n = this.f5382e;
        if (interfaceC2785n == null || numValueOf == null || numF2 == null) {
            return;
        }
        interfaceC2785n.invoke(new g(numValueOf.intValue(), objL), new g(numF2.intValue(), objL2));
    }

    public final boolean t(int i7, int i10) {
        Object obj;
        Object next;
        Iterator it = p().iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            int iM = m(next);
            if (i7 <= n(next) && iM <= i7) {
                int iO = o(next);
                if (i10 <= d(next) && iO <= i10) {
                    break;
                }
            }
        }
        if (next != null) {
            this.f5388k.setValue(next);
            this.f5384g.setValue(Integer.valueOf(k(next)));
            obj = next;
        }
        return obj != null;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, java.util.List] */
    public final Object u(int i7, boolean z6, s7.i iVar) throws Throwable {
        D d = this.o;
        boolean zIsEmpty = d.h().f1415j.isEmpty();
        m7.q qVar = m7.q.f19267a;
        if (!zIsEmpty) {
            int i10 = -((((int) (d.h().f() & 4294967295L)) / 2) - (j((E.u) AbstractC1998m.Y(d.h().f1415j)) / 2));
            if (z6) {
                Object objF = d.f(i7, i10, iVar);
                return objF == EnumC2303a.l ? objF : qVar;
            }
            Object objJ = d.j(i7, i10, iVar);
            if (objJ == EnumC2303a.l) {
                return objJ;
            }
        }
        return qVar;
    }
}
