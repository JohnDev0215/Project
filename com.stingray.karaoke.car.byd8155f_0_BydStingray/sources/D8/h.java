package D8;

import F8.A;
import Q7.AbstractC0662o;
import Q7.AbstractC0669w;
import Q7.C0661n;
import Q7.C0667u;
import Q7.C0672z;
import Q7.D;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.N;
import T.C0746i0;
import T7.C0790i;
import a.AbstractC0904a;
import j8.C1565j;
import j8.C1567l;
import j8.Q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import l8.AbstractC1787e;
import l8.InterfaceC1788f;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2061f;
import r8.C2310c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1230m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ k f1231n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(k kVar, int i7) {
        super(0);
        this.f1230m = i7;
        this.f1231n = kVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v48, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v49, types: [java.lang.Iterable, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v52, types: [java.util.ArrayList] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        Object next;
        C0661n c0661n;
        Object c0667u;
        A aT0;
        ?? arrayList;
        int i7 = 1;
        k kVar = this.f1231n;
        switch (this.f1230m) {
            case 0:
                return AbstractC0669w.c(kVar);
            case 1:
                return AbstractC1998m.z0(((B8.i) kVar.f1248w.f779a).f764e.f(kVar.f1239G));
            case 2:
                C1565j c1565j = kVar.f1241p;
                if (!((c1565j.f17394n & 4) == 4)) {
                    return null;
                }
                InterfaceC0654g interfaceC0654gC = kVar.o0().c(AbstractC0904a.F((InterfaceC1788f) kVar.f1248w.f780b, c1565j.f17396q), Y7.b.f10802r);
                if (interfaceC0654gC instanceof InterfaceC0652e) {
                    return (InterfaceC0652e) interfaceC0654gC;
                }
                return null;
            case 3:
                List list = kVar.f1241p.f17374A;
                A7.m.e(list, "classProto.constructorList");
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : list) {
                    if (AbstractC1787e.f18848m.c(((C1567l) obj).o).booleanValue()) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    B8.k kVar2 = kVar.f1248w;
                    if (!zHasNext) {
                        return AbstractC1998m.n0(AbstractC1998m.n0(arrayList3, AbstractC1999n.E(kVar.p0())), ((B8.i) kVar2.f779a).f772n.b(kVar));
                    }
                    C1567l c1567l = (C1567l) it.next();
                    B8.s sVar = (B8.s) kVar2.f785i;
                    A7.m.e(c1567l, "it");
                    arrayList3.add(sVar.d(c1567l, false));
                }
                break;
            case 4:
                k kVar3 = this.f1231n;
                if (!M5.b.a(kVar3.f1247v)) {
                    List list2 = kVar3.f1241p.f17374A;
                    A7.m.e(list2, "classProto.constructorList");
                    Iterator it2 = list2.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            next = it2.next();
                            if (!AbstractC1787e.f18848m.c(((C1567l) next).o).booleanValue()) {
                            }
                        } else {
                            next = null;
                        }
                    }
                    C1567l c1567l2 = (C1567l) next;
                    return c1567l2 != null ? ((B8.s) kVar3.f1248w.f785i).d(c1567l2, true) : null;
                }
                C2310c c2310c = new C2310c(kVar3, null, R7.g.f7535a, true, 1, N.c);
                List listEmptyList = Collections.emptyList();
                int i10 = r8.d.f21880a;
                int i11 = kVar3.f1247v;
                if (i11 == 3 || M5.b.a(i11)) {
                    c0661n = AbstractC0662o.f7189a;
                    if (c0661n == null) {
                        r8.d.a(49);
                        throw null;
                    }
                } else if (r8.d.q(kVar3)) {
                    c0661n = AbstractC0662o.f7189a;
                    if (c0661n == null) {
                        r8.d.a(51);
                        throw null;
                    }
                } else if (r8.d.k(kVar3)) {
                    c0661n = AbstractC0662o.f7196j;
                    if (c0661n == null) {
                        r8.d.a(52);
                        throw null;
                    }
                } else {
                    c0661n = AbstractC0662o.f7191e;
                    if (c0661n == null) {
                        r8.d.a(53);
                        throw null;
                    }
                }
                c2310c.q1(listEmptyList, c0661n);
                c2310c.f8900s = kVar3.j();
                return c2310c;
            case 5:
                n7.u uVar = n7.u.l;
                if (kVar.f1245t != 2) {
                    return uVar;
                }
                List<Integer> list3 = kVar.f1241p.f17378F;
                A7.m.e(list3, "fqNames");
                if (list3.isEmpty()) {
                    if (kVar.f1245t != 2) {
                        return uVar;
                    }
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    InterfaceC0657j interfaceC0657j = kVar.B;
                    if (interfaceC0657j instanceof D) {
                        r8.m.c(kVar, linkedHashSet, ((D) interfaceC0657j).z0(), false);
                    }
                    r8.m.c(kVar, linkedHashSet, kVar.l0(), true);
                    return AbstractC1998m.u0(linkedHashSet, new r8.i(i7));
                }
                ArrayList arrayList4 = new ArrayList();
                for (Integer num : list3) {
                    B8.k kVar4 = kVar.f1248w;
                    B8.i iVar = (B8.i) kVar4.f779a;
                    A7.m.e(num, "index");
                    InterfaceC0652e interfaceC0652eB = iVar.b(AbstractC0904a.D((InterfaceC1788f) kVar4.f780b, num.intValue()));
                    if (interfaceC0652eB != null) {
                        arrayList4.add(interfaceC0652eB);
                    }
                }
                return arrayList4;
            default:
                if (!kVar.n() && !kVar.b0()) {
                    return null;
                }
                B8.k kVar5 = kVar.f1248w;
                InterfaceC1788f interfaceC1788f = (InterfaceC1788f) kVar5.f780b;
                C1565j c1565j2 = kVar.f1241p;
                A7.m.f(c1565j2, "<this>");
                A7.m.f(interfaceC1788f, "nameResolver");
                C0746i0 c0746i0 = (C0746i0) kVar5.d;
                A7.m.f(c0746i0, "typeTable");
                int size = c1565j2.f17383K.size();
                B8.D d = (B8.D) kVar5.f784h;
                if (size > 0) {
                    List<Integer> list4 = c1565j2.f17383K;
                    A7.m.e(list4, "multiFieldValueClassUnderlyingNameList");
                    ArrayList arrayList5 = new ArrayList(AbstractC2000o.J(list4, 10));
                    for (Integer num2 : list4) {
                        A7.m.e(num2, "it");
                        arrayList5.add(AbstractC0904a.F(interfaceC1788f, num2.intValue()));
                    }
                    m7.i iVar2 = new m7.i(Integer.valueOf(c1565j2.f17386N.size()), Integer.valueOf(c1565j2.f17385M.size()));
                    if (iVar2.equals(new m7.i(Integer.valueOf(arrayList5.size()), 0))) {
                        List<Integer> list5 = c1565j2.f17386N;
                        A7.m.e(list5, "multiFieldValueClassUnderlyingTypeIdList");
                        arrayList = new ArrayList(AbstractC2000o.J(list5, 10));
                        for (Integer num3 : list5) {
                            A7.m.e(num3, "it");
                            arrayList.add(c0746i0.c(num3.intValue()));
                        }
                    } else {
                        if (!iVar2.equals(new m7.i(0, Integer.valueOf(arrayList5.size())))) {
                            throw new IllegalStateException(("class " + AbstractC0904a.F(interfaceC1788f, c1565j2.f17395p) + " has illegal multi-field value class representation").toString());
                        }
                        arrayList = c1565j2.f17385M;
                    }
                    A7.m.e(arrayList, "when (typeIdCount to typ…epresentation\")\n        }");
                    ArrayList arrayList6 = new ArrayList(AbstractC2000o.J(arrayList, 10));
                    for (Q q4 : arrayList) {
                        A7.m.f(q4, "p0");
                        arrayList6.add(d.d(q4, true));
                    }
                    c0667u = new C0672z(AbstractC1998m.F0(arrayList5, arrayList6));
                } else if ((c1565j2.f17394n & 8) == 8) {
                    C2061f c2061fF = AbstractC0904a.F(interfaceC1788f, c1565j2.f17380H);
                    int i12 = c1565j2.f17394n;
                    Q qC = (i12 & 16) == 16 ? c1565j2.f17381I : (i12 & 32) == 32 ? c0746i0.c(c1565j2.f17382J) : null;
                    if ((qC == null || (aT0 = d.d(qC, true)) == null) && (aT0 = kVar.t0(c2061fF)) == null) {
                        throw new IllegalStateException(("cannot determine underlying type for value class " + AbstractC0904a.F(interfaceC1788f, c1565j2.f17395p) + " with property " + c2061fF).toString());
                    }
                    c0667u = new C0667u(c2061fF, aT0);
                } else {
                    c0667u = null;
                }
                if (c0667u != null) {
                    return c0667u;
                }
                if (kVar.f1242q.a(1, 5, 1)) {
                    return null;
                }
                C0790i c0790iP0 = kVar.p0();
                if (c0790iP0 == null) {
                    throw new IllegalStateException(("Inline class has no primary constructor: " + kVar).toString());
                }
                List listD0 = c0790iP0.D0();
                A7.m.e(listD0, "constructor.valueParameters");
                C2061f name = ((T7.Q) AbstractC1998m.Y(listD0)).getName();
                A7.m.e(name, "constructor.valueParameters.first().name");
                A aT02 = kVar.t0(name);
                if (aT02 != null) {
                    return new C0667u(name, aT02);
                }
                throw new IllegalStateException(("Value class has no underlying property: " + kVar).toString());
        }
    }
}
