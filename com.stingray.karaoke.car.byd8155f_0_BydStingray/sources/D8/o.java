package D8;

import A.C0024l;
import B8.D;
import F8.A;
import Q7.InterfaceC0657j;
import T.C0746i0;
import a.AbstractC0904a;
import h8.C1391h;
import j8.C1556a;
import j8.C1562g;
import j8.C1579y;
import j8.G;
import j8.Q;
import j8.T;
import j8.f0;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import l8.AbstractC1783a;
import l8.AbstractC1787e;
import l8.C1790h;
import l8.InterfaceC1788f;
import n7.AbstractC2000o;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f1255n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(p pVar, int i7) {
        super(1);
        this.f1254m = i7;
        this.f1255n = pVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Q qC;
        Q qC2;
        switch (this.f1254m) {
            case 0:
                C2061f c2061f = (C2061f) obj;
                A7.m.f(c2061f, "it");
                p pVar = this.f1255n;
                LinkedHashMap linkedHashMap = pVar.f1257a;
                C1556a c1556a = C1579y.f17483G;
                A7.m.e(c1556a, "PARSER");
                byte[] bArr = (byte[]) linkedHashMap.get(c2061f);
                r rVar = pVar.f1263i;
                Collection<C1579y> collectionI0 = bArr != null ? P8.m.i0(P8.m.e0(new C0024l(c1556a, new ByteArrayInputStream(bArr), rVar, 1))) : n7.u.l;
                ArrayList arrayList = new ArrayList(collectionI0.size());
                for (C1579y c1579y : collectionI0) {
                    B8.s sVar = (B8.s) rVar.f1267b.f785i;
                    A7.m.e(c1579y, "it");
                    u uVarE = sVar.e(c1579y);
                    if (!rVar.r(uVarE)) {
                        uVarE = null;
                    }
                    if (uVarE != null) {
                        arrayList.add(uVarE);
                    }
                }
                rVar.j(arrayList, c2061f);
                return O8.l.d(arrayList);
            case 1:
                C2061f c2061f2 = (C2061f) obj;
                A7.m.f(c2061f2, "it");
                p pVar2 = this.f1255n;
                LinkedHashMap linkedHashMap2 = pVar2.f1258b;
                C1556a c1556a2 = G.f17112G;
                A7.m.e(c1556a2, "PARSER");
                byte[] bArr2 = (byte[]) linkedHashMap2.get(c2061f2);
                r rVar2 = pVar2.f1263i;
                Collection<G> collectionI02 = bArr2 != null ? P8.m.i0(P8.m.e0(new C0024l(c1556a2, new ByteArrayInputStream(bArr2), rVar2, 1))) : n7.u.l;
                ArrayList arrayList2 = new ArrayList(collectionI02.size());
                for (G g7 : collectionI02) {
                    B8.s sVar2 = (B8.s) rVar2.f1267b.f785i;
                    A7.m.e(g7, "it");
                    arrayList2.add(sVar2.f(g7));
                }
                rVar2.k(arrayList2, c2061f2);
                return O8.l.d(arrayList2);
            default:
                C2061f c2061f3 = (C2061f) obj;
                A7.m.f(c2061f3, "it");
                p pVar3 = this.f1255n;
                byte[] bArr3 = (byte[]) pVar3.c.get(c2061f3);
                v vVar = null;
                if (bArr3 != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr3);
                    r rVar3 = pVar3.f1263i;
                    T t10 = (T) T.f17205A.b(byteArrayInputStream, ((B8.i) rVar3.f1267b.f779a).f773p);
                    if (t10 != null) {
                        B8.s sVar3 = (B8.s) rVar3.f1267b.f785i;
                        sVar3.getClass();
                        List list = t10.f17215v;
                        A7.m.e(list, "proto.annotationList");
                        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(list, 10));
                        Iterator it = list.iterator();
                        while (true) {
                            boolean zHasNext = it.hasNext();
                            B8.k kVar = sVar3.f805a;
                            if (zHasNext) {
                                C1562g c1562g = (C1562g) it.next();
                                A7.m.e(c1562g, "it");
                                arrayList3.add(sVar3.f806b.z(c1562g, (InterfaceC1788f) kVar.f780b));
                            } else {
                                R7.h iVar = arrayList3.isEmpty() ? R7.g.f7535a : new R7.i(0, arrayList3);
                                vVar = new v(((B8.i) kVar.f779a).f762a, (InterfaceC0657j) kVar.c, iVar, AbstractC0904a.F((InterfaceC1788f) kVar.f780b, t10.f17209p), P8.n.t((f0) AbstractC1787e.d.c(t10.o)), t10, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, (C1790h) kVar.f781e, (C1391h) kVar.f783g);
                                List list2 = t10.f17210q;
                                A7.m.e(list2, "proto.typeParameterList");
                                D d = (D) kVar.a(vVar, list2, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, (C1790h) kVar.f781e, (AbstractC1783a) kVar.f782f).f784h;
                                List listB = d.b();
                                C0746i0 c0746i0 = (C0746i0) kVar.d;
                                A7.m.f(c0746i0, "typeTable");
                                int i7 = t10.f17208n;
                                if ((i7 & 4) == 4) {
                                    qC = t10.f17211r;
                                    A7.m.e(qC, "underlyingType");
                                } else {
                                    if ((i7 & 8) != 8) {
                                        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias");
                                    }
                                    qC = c0746i0.c(t10.f17212s);
                                }
                                A aD = d.d(qC, false);
                                int i10 = t10.f17208n;
                                if ((i10 & 16) == 16) {
                                    qC2 = t10.f17213t;
                                    A7.m.e(qC2, "expandedType");
                                } else {
                                    if ((i10 & 32) != 32) {
                                        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias");
                                    }
                                    qC2 = c0746i0.c(t10.f17214u);
                                }
                                vVar.f1(listB, aD, d.d(qC2, false));
                            }
                        }
                    }
                }
                return vVar;
        }
    }
}
