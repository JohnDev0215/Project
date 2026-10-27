package A;

import C0.C0117y;
import E0.AbstractC0156f;
import E0.C0153c;
import E0.C0159i;
import F0.AbstractC0227s0;
import F0.C0196c0;
import F0.C0198d0;
import F8.AbstractC0268c;
import F8.AbstractC0272g;
import F8.AbstractC0287w;
import F8.C0270e;
import K.C0331b0;
import N0.C0411f;
import Q7.InterfaceC0657j;
import S8.AbstractC0727y;
import a.AbstractC0904a;
import com.google.accompanist.drawablepainter.DrawablePainter;
import j8.C1565j;
import j8.C1579y;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import l3.C1764u;
import l8.InterfaceC1788f;
import n0.C1863M;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2057b;
import x.C2658y;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class I0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f45m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f46n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ I0(Object obj, int i7) {
        super(0);
        this.f45m = i7;
        this.f46n = obj;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f45m) {
            case 0:
                T.R0 r02 = AbstractC0227s0.f2259f;
                J0 j02 = (J0) this.f46n;
                j02.f52M.f261a = new C2658y(new C1764u((a1.b) AbstractC0156f.i(j02, r02)));
                return m7.q.f19267a;
            case 1:
                return ((C0159i) this.f46n).a();
            case 2:
                Set setKeySet = ((LinkedHashMap) ((C8.c) this.f46n).f1019u.f807m).keySet();
                ArrayList arrayList = new ArrayList();
                for (Object obj : setKeySet) {
                    C2057b c2057b = (C2057b) obj;
                    if (c2057b.f20565b.e().d() && !B8.g.c.contains(c2057b)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((C2057b) it.next()).i());
                }
                return arrayList2;
            case 3:
                C0.G gA = ((C0.f0) this.f46n).a();
                E0.F f10 = gA.l;
                if (gA.f860y != ((V.a) f10.r()).l.f9279n) {
                    Iterator it2 = gA.f852q.entrySet().iterator();
                    while (it2.hasNext()) {
                        ((C0117y) ((Map.Entry) it2.next()).getValue()).d = true;
                    }
                    if (!f10.B()) {
                        E0.F.s0(f10, false, 7);
                    }
                }
                return m7.q.f19267a;
            case 4:
                D0.c cVar = (D0.c) this.f46n;
                int i7 = 0;
                cVar.f1126f = false;
                HashSet hashSet = new HashSet();
                V.d dVar = cVar.d;
                int i10 = dVar.f9279n;
                V.d dVar2 = cVar.f1125e;
                if (i10 > 0) {
                    Object[] objArr = dVar.l;
                    int i11 = 0;
                    do {
                        E0.F f11 = (E0.F) objArr[i11];
                        D0.e eVar = (D0.e) dVar2.l[i11];
                        g0.n nVar = (g0.n) f11.f1469H.f1603f;
                        if (nVar.f14695x) {
                            D0.c.b(nVar, eVar, hashSet);
                        }
                        i11++;
                    } while (i11 < i10);
                }
                dVar.g();
                dVar2.g();
                V.d dVar3 = cVar.f1124b;
                int i12 = dVar3.f9279n;
                V.d dVar4 = cVar.c;
                if (i12 > 0) {
                    Object[] objArr2 = dVar3.l;
                    do {
                        C0153c c0153c = (C0153c) objArr2[i7];
                        D0.e eVar2 = (D0.e) dVar4.l[i7];
                        if (c0153c.f14695x) {
                            D0.c.b(c0153c, eVar2, hashSet);
                        }
                        i7++;
                    } while (i7 < i12);
                }
                dVar3.g();
                dVar4.g();
                Iterator it3 = hashSet.iterator();
                while (it3.hasNext()) {
                    ((C0153c) it3.next()).J0();
                }
                return m7.q.f19267a;
            case 5:
                B8.t tVar = (B8.t) this.f46n;
                tVar.getClass();
                HashSet hashSet2 = new HashSet();
                D8.k kVar = (D8.k) tVar.f809p;
                Iterator it4 = kVar.f1250y.o().iterator();
                while (it4.hasNext()) {
                    for (InterfaceC0657j interfaceC0657j : h6.f.i(((AbstractC0287w) it4.next()).z0(), null, 3)) {
                        if ((interfaceC0657j instanceof T7.K) || (interfaceC0657j instanceof Q7.L)) {
                            hashSet2.add(interfaceC0657j.getName());
                        }
                    }
                }
                C1565j c1565j = kVar.f1241p;
                List list = c1565j.B;
                A7.m.e(list, "classProto.functionList");
                Iterator it5 = list.iterator();
                while (true) {
                    boolean zHasNext = it5.hasNext();
                    B8.k kVar2 = kVar.f1248w;
                    if (!zHasNext) {
                        List list2 = c1565j.f17375C;
                        A7.m.e(list2, "classProto.propertyList");
                        Iterator it6 = list2.iterator();
                        while (it6.hasNext()) {
                            hashSet2.add(AbstractC0904a.F((InterfaceC1788f) kVar2.f780b, ((j8.G) it6.next()).f17120q));
                        }
                        return AbstractC1985B.q(hashSet2, hashSet2);
                    }
                    hashSet2.add(AbstractC0904a.F((InterfaceC1788f) kVar2.f780b, ((C1579y) it5.next()).f17491q));
                }
                break;
            case 6:
                D8.r rVar = (D8.r) this.f46n;
                Set setN = rVar.n();
                if (setN == null) {
                    return null;
                }
                return AbstractC1985B.q(AbstractC1985B.q(rVar.m(), rVar.c.c.keySet()), setN);
            case 7:
                D8.w wVar = (D8.w) this.f46n;
                B8.k kVar3 = wVar.f1294w;
                return AbstractC1998m.z0(((B8.i) kVar3.f779a).f764e.r(wVar.f1295x, (InterfaceC1788f) kVar3.f780b));
            case 8:
                E0.N nT = ((E0.F) this.f46n).t();
                nT.f1568r.f1531H = true;
                E0.K k10 = nT.f1569s;
                if (k10 != null) {
                    k10.f1509E = true;
                }
                return m7.q.f19267a;
            case 9:
                E0.L l = (E0.L) this.f46n;
                E0.N n10 = l.f1542S;
                int i13 = 0;
                n10.f1563k = 0;
                V.d dVarL = n10.f1555a.L();
                int i14 = dVarL.f9279n;
                if (i14 > 0) {
                    Object[] objArr3 = dVarL.l;
                    int i15 = 0;
                    do {
                        E0.L lZ = ((E0.F) objArr3[i15]).z();
                        lZ.f1544r = lZ.f1545s;
                        lZ.f1545s = Integer.MAX_VALUE;
                        lZ.f1528E = false;
                        if (lZ.f1548v == 2) {
                            lZ.f1548v = 3;
                        }
                        i15++;
                    } while (i15 < i14);
                }
                E0.N n11 = l.f1542S;
                V.d dVarL2 = n11.f1555a.L();
                int i16 = dVarL2.f9279n;
                if (i16 > 0) {
                    Object[] objArr4 = dVarL2.l;
                    int i17 = 0;
                    do {
                        ((E0.F) objArr4[i17]).t().f1568r.f1529F.d = false;
                        i17++;
                    } while (i17 < i16);
                }
                l.n().z0().b();
                E0.F f12 = n11.f1555a;
                V.d dVarL3 = f12.L();
                int i18 = dVarL3.f9279n;
                if (i18 > 0) {
                    Object[] objArr5 = dVarL3.l;
                    int i19 = 0;
                    do {
                        E0.F f13 = (E0.F) objArr5[i19];
                        if (f13.z().f1544r != f13.H()) {
                            f12.i0();
                            f12.O();
                            if (f13.H() == Integer.MAX_VALUE) {
                                f13.z().v0();
                            }
                        }
                        i19++;
                    } while (i19 < i18);
                }
                V.d dVarL4 = f12.L();
                int i20 = dVarL4.f9279n;
                if (i20 > 0) {
                    Object[] objArr6 = dVarL4.l;
                    do {
                        E0.G g7 = ((E0.F) objArr6[i13]).t().f1568r.f1529F;
                        g7.f1496e = g7.d;
                        i13++;
                    } while (i13 < i20);
                }
                return m7.q.f19267a;
            case 10:
                E0.N n12 = (E0.N) this.f46n;
                n12.a().b(n12.f1570t);
                return m7.q.f19267a;
            case 11:
                E0.d0 d0Var = ((E0.d0) this.f46n).f1650y;
                if (d0Var != null) {
                    d0Var.W0();
                }
                return m7.q.f19267a;
            case 12:
                C1863M c1863m = E0.d0.f1628Q;
                ((InterfaceC2782k) this.f46n).b(c1863m);
                c1863m.f19386F = c1863m.f19399z.v(c1863m.f19383C, c1863m.f19385E, c1863m.f19384D);
                return m7.q.f19267a;
            case 13:
                AbstractC0727y.d(((C0196c0) this.f46n).f2185n, null);
                return m7.q.f19267a;
            case 14:
                ((C0198d0) this.f46n).f2190b = null;
                return m7.q.f19267a;
            case 15:
                return new C0270e(((AbstractC0272g) this.f46n).b());
            case 16:
                return AbstractC0268c.r(((F8.F) this.f46n).f2435a);
            case 17:
                return H8.i.c(H8.h.f2874J, ((io.sentry.internal.debugmeta.c) this.f46n).toString());
            case 18:
                G.A a10 = ((androidx.compose.foundation.lazy.layout.a) this.f46n).f11495j;
                if (a10 != null) {
                    AbstractC0156f.m(a10);
                }
                return m7.q.f19267a;
            case 19:
                return new G.e0((d0.k) this.f46n, n7.v.l);
            case 20:
                InterfaceC2772a interfaceC2772a = ((G8.i) this.f46n).f2732b;
                if (interfaceC2772a != null) {
                    return (List) interfaceC2772a.a();
                }
                return null;
            case 21:
                ((I.c) this.f46n).f2903T.b(Boolean.valueOf(!r10.f2902S));
                return m7.q.f19267a;
            case 22:
                return new I4.a((DrawablePainter) this.f46n);
            case 23:
                return (C0411f) this.f46n;
            case 24:
                ((z.m) this.f46n).f24251a.setValue(z.j.f24249a);
                return m7.q.f19267a;
            case 25:
                return ((C0331b0) this.f46n).d();
            case 26:
                return new K.E0((EnumC0017h0) this.f46n, 0.0f);
            case 27:
                a1.i iVar = (a1.i) this.f46n;
                return new a1.h(AbstractC2673a.d(iVar.f11035a, iVar.f11036b));
            case 28:
                return K7.s0.a(((K7.E) this.f46n).d());
            default:
                return new K7.H((K7.I) this.f46n);
        }
    }
}
