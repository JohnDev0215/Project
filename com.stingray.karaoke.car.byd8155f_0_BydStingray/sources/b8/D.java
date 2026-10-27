package B8;

import F8.AbstractC0287w;
import F8.C0273h;
import F8.C0277l;
import F8.H;
import F8.K;
import F8.O;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.N;
import T.C0746i0;
import a.AbstractC0904a;
import g1.AbstractC1274a;
import h8.AbstractC1387d;
import io.sentry.C1489n1;
import j8.Q;
import j8.W;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import l8.InterfaceC1788f;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import o8.C2057b;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class D implements h8.m {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f747m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f748n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f749p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Object f750q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object f751r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Object f752s;

    public /* synthetic */ D() {
        this.l = 1;
    }

    public static F8.A a(F8.A a10, AbstractC0287w abstractC0287w) {
        N7.i iVarL = AbstractC1274a.L(a10);
        R7.h hVarK = a10.k();
        AbstractC0287w abstractC0287wM = C7.a.M(a10);
        List listJ = C7.a.J(a10);
        List listW = AbstractC1998m.W(C7.a.O(a10));
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listW, 10));
        Iterator it = listW.iterator();
        while (it.hasNext()) {
            arrayList.add(((O) it.next()).b());
        }
        return C7.a.C(iVarL, hVarK, abstractC0287wM, listJ, arrayList, abstractC0287w, true).L0(a10.F0());
    }

    public static final ArrayList e(Q q4, D d) {
        List list = q4.o;
        A7.m.e(list, "argumentList");
        Q qM = AbstractC1387d.m(q4, (C0746i0) ((k) d.f747m).d);
        Iterable iterableE = qM != null ? e(qM, d) : null;
        if (iterableE == null) {
            iterableE = n7.u.l;
        }
        return AbstractC1998m.n0(list, iterableE);
    }

    public static H f(List list, R7.h hVar, K k10, InterfaceC0657j interfaceC0657j) {
        H hK;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((C0277l) it.next()).getClass();
            if (hVar.isEmpty()) {
                H.f2437m.getClass();
                hK = H.f2438n;
            } else {
                C1489n1 c1489n1 = H.f2437m;
                List listJ = AbstractC1387d.j(new C0273h(hVar));
                c1489n1.getClass();
                hK = C1489n1.k(listJ);
            }
            arrayList.add(hK);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AbstractC2004s.L(arrayList2, (Iterable) it2.next());
        }
        H.f2437m.getClass();
        return C1489n1.k(arrayList2);
    }

    public static final InterfaceC0652e h(D d, Q q4, int i7) {
        C2057b c2057bD = AbstractC0904a.D((InterfaceC1788f) ((k) d.f747m).f780b, i7);
        P8.r rVarG0 = P8.m.g0(P8.m.d0(q4, new z(d, 2)), C.f746m);
        ArrayList arrayList = new ArrayList();
        Iterator it = rVarG0.f5484a.iterator();
        while (it.hasNext()) {
            arrayList.add(rVarG0.f5485b.b(it.next()));
        }
        int iZ = P8.m.Z(P8.m.d0(c2057bD, B.f745t));
        while (arrayList.size() < iZ) {
            arrayList.add(0);
        }
        return ((i) ((k) d.f747m).f779a).l.T(c2057bD, arrayList);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.Map] */
    public List b() {
        return AbstractC1998m.z0(this.f752s.values());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    public Q7.Q c(int i7) {
        Q7.Q q4 = (Q7.Q) this.f752s.get(Integer.valueOf(i7));
        if (q4 != null) {
            return q4;
        }
        D d = (D) this.f748n;
        if (d != null) {
            return d.c(i7);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03bf A[PHI: r3
  0x03bf: PHI (r3v9 F8.A) = (r3v7 F8.A), (r3v15 F8.A) binds: [B:156:0x03da, B:150:0x03b1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public F8.A d(j8.Q r27, boolean r28) {
        /*
            Method dump skipped, instruction units count: 1082
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.D.d(j8.Q, boolean):F8.A");
    }

    public AbstractC0287w g(Q q4) {
        A7.m.f(q4, "proto");
        if (!((q4.f17184n & 2) == 2)) {
            return d(q4, true);
        }
        k kVar = (k) this.f747m;
        String strN = ((InterfaceC1788f) kVar.f780b).n(q4.f17186q);
        F8.A aD = d(q4, true);
        C0746i0 c0746i0 = (C0746i0) kVar.d;
        A7.m.f(c0746i0, "typeTable");
        int i7 = q4.f17184n;
        Q qC = (i7 & 4) == 4 ? q4.f17187r : (i7 & 8) == 8 ? c0746i0.c(q4.f17188s) : null;
        A7.m.c(qC);
        return ((i) kVar.f779a).f769j.b(q4, strN, aD, d(qC, true));
    }

    @Override // h8.m
    public void i() {
        HashMap map = (HashMap) this.f748n;
        H0.b bVar = (H0.b) this.o;
        bVar.getClass();
        C2057b c2057b = (C2057b) this.f750q;
        A7.m.f(map, "arguments");
        boolean zE = false;
        if (c2057b.equals(M7.a.f4215b)) {
            Object obj = map.get(C2061f.e("value"));
            t8.r rVar = obj instanceof t8.r ? (t8.r) obj : null;
            if (rVar != null) {
                Object obj2 = rVar.f22499a;
                t8.p pVar = obj2 instanceof t8.p ? (t8.p) obj2 : null;
                if (pVar != null) {
                    zE = bVar.E(pVar.f22507a.f22497a);
                }
            }
        }
        if (zE || bVar.E(c2057b)) {
            return;
        }
        ((List) this.f751r).add(new R7.c(((InterfaceC0652e) this.f749p).j(), map, (N) this.f752s));
    }

    @Override // h8.m
    public void j(C2061f c2061f, t8.f fVar) {
        ((HashMap) this.f748n).put(c2061f, new t8.r(new t8.p(fVar)));
    }

    @Override // h8.m
    public h8.n k(C2061f c2061f) {
        return new t((H0.b) this.f747m, c2061f, this);
    }

    @Override // h8.m
    public h8.m l(C2057b c2057b, C2061f c2061f) {
        ArrayList arrayList = new ArrayList();
        D dG = ((H0.b) this.f747m).G(c2057b, N.c, arrayList);
        N0.q qVar = new N0.q();
        qVar.f4400n = dG;
        qVar.o = this;
        qVar.f4401p = c2061f;
        qVar.l = arrayList;
        qVar.f4399m = dG;
        return qVar;
    }

    @Override // h8.m
    public void m(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
        ((HashMap) this.f748n).put(c2061f, new t8.i(c2057b, c2061f2));
    }

    @Override // h8.m
    public void q(C2061f c2061f, Object obj) {
        ((HashMap) this.f748n).put(c2061f, H0.b.d((H0.b) this.f747m, c2061f, obj));
    }

    public String toString() {
        String str;
        switch (this.l) {
            case 0:
                StringBuilder sb = new StringBuilder();
                sb.append((String) this.o);
                D d = (D) this.f748n;
                if (d == null) {
                    str = "";
                } else {
                    str = ". Child of " + ((String) d.o);
                }
                sb.append(str);
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public D(k kVar, D d, List list, String str, String str2) {
        Map linkedHashMap;
        this.l = 0;
        A7.m.f(kVar, "c");
        A7.m.f(list, "typeParameterProtos");
        A7.m.f(str, "debugName");
        this.f747m = kVar;
        this.f748n = d;
        this.o = str;
        this.f749p = str2;
        i iVar = (i) kVar.f779a;
        this.f750q = ((E8.l) iVar.f762a).c(new z(this, 0));
        this.f751r = ((E8.l) iVar.f762a).c(new z(this, 1));
        if (list.isEmpty()) {
            linkedHashMap = n7.v.l;
        } else {
            linkedHashMap = new LinkedHashMap();
            Iterator it = list.iterator();
            int i7 = 0;
            while (it.hasNext()) {
                W w10 = (W) it.next();
                linkedHashMap.put(Integer.valueOf(w10.o), new D8.w((k) this.f747m, w10, i7));
                i7++;
            }
        }
        this.f752s = linkedHashMap;
    }

    public D(H0.b bVar, InterfaceC0652e interfaceC0652e, C2057b c2057b, List list, N n10) {
        this.l = 3;
        this.o = bVar;
        this.f749p = interfaceC0652e;
        this.f750q = c2057b;
        this.f751r = list;
        this.f752s = n10;
        this.f747m = bVar;
        this.f748n = new HashMap();
    }

    public D(j4.e eVar, j4.e eVar2, j4.e eVar3, j4.e eVar4, g4.l lVar, g4.l lVar2) {
        this.l = 2;
        this.f752s = B4.d.a(150, new e7.f(this, 3));
        this.f747m = eVar;
        this.f748n = eVar2;
        this.o = eVar3;
        this.f749p = eVar4;
        this.f750q = lVar;
        this.f751r = lVar2;
    }
}
