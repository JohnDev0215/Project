package B8;

import B5.K;
import F8.AbstractC0287w;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.N;
import Q7.O;
import T.C0746i0;
import T7.I;
import T7.J;
import a.AbstractC0904a;
import h8.AbstractC1387d;
import h8.C1391h;
import j8.C1567l;
import j8.C1579y;
import j8.EnumC1555A;
import j8.EnumC1580z;
import j8.G;
import j8.Q;
import j8.Z;
import j8.f0;
import java.util.ArrayList;
import java.util.List;
import l8.AbstractC1783a;
import l8.AbstractC1787e;
import l8.C1784b;
import l8.C1785c;
import l8.C1790h;
import l8.InterfaceC1788f;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2058c;
import o8.C2061f;
import p8.AbstractC2136k;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M.t f806b;

    public s(k kVar) {
        A7.m.f(kVar, "c");
        this.f805a = kVar;
        i iVar = (i) kVar.f779a;
        this.f806b = new M.t(iVar.f763b, iVar.l);
    }

    public final K a(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j instanceof Q7.D) {
            C2058c c2058c = ((T7.B) ((Q7.D) interfaceC0657j)).f8767q;
            k kVar = this.f805a;
            return new v(c2058c, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, (C1391h) kVar.f783g);
        }
        if (interfaceC0657j instanceof D8.k) {
            return ((D8.k) interfaceC0657j).f1239G;
        }
        return null;
    }

    public final R7.h b(AbstractC2136k abstractC2136k, int i7, int i10) {
        return !AbstractC1787e.c.c(i7).booleanValue() ? R7.g.f7535a : new D8.x(((i) this.f805a.f779a).f762a, new o(this, abstractC2136k, i10, 0));
    }

    public final R7.h c(G g7, boolean z6) {
        return !AbstractC1787e.c.c(g7.o).booleanValue() ? R7.g.f7535a : new D8.x(((i) this.f805a.f779a).f762a, new p(this, z6, g7));
    }

    public final D8.c d(C1567l c1567l, boolean z6) {
        k kVar = this.f805a;
        InterfaceC0657j interfaceC0657j = (InterfaceC0657j) kVar.c;
        A7.m.d(interfaceC0657j, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) interfaceC0657j;
        D8.c cVar = new D8.c(interfaceC0652e, null, b(c1567l, c1567l.o, 1), z6, 1, c1567l, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, (C1790h) kVar.f781e, (C1391h) kVar.f783g, null);
        k kVarA = kVar.a(cVar, n7.u.l, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, (C1790h) kVar.f781e, (AbstractC1783a) kVar.f782f);
        List list = c1567l.f17413p;
        A7.m.e(list, "proto.valueParameterList");
        cVar.q1(((s) kVarA.f785i).g(list, c1567l, 1), P8.n.t((f0) AbstractC1787e.d.c(c1567l.o)));
        cVar.m1(interfaceC0652e.j());
        cVar.f8886D = interfaceC0652e.c0();
        cVar.f8890H = !AbstractC1787e.f18849n.c(c1567l.o).booleanValue();
        return cVar;
    }

    public final D8.u e(C1579y c1579y) {
        int i7;
        AbstractC0287w abstractC0287wG;
        A7.m.f(c1579y, "proto");
        int i10 = 1;
        if ((c1579y.f17489n & 1) == 1) {
            i7 = c1579y.o;
        } else {
            int i11 = c1579y.f17490p;
            i7 = ((i11 >> 8) << 6) + (i11 & 63);
        }
        int i12 = i7;
        R7.h hVarB = b(c1579y, i12, 1);
        boolean zP = c1579y.p();
        R7.f fVar = R7.g.f7535a;
        k kVar = this.f805a;
        R7.h aVar = (zP || (c1579y.f17489n & 64) == 64) ? new D8.a(((i) kVar.f779a).f762a, new o(this, c1579y, i10, 1)) : fVar;
        C2058c c2058cG = AbstractC2564d.g((InterfaceC0657j) kVar.c);
        int i13 = c1579y.f17491q;
        InterfaceC1788f interfaceC1788f = (InterfaceC1788f) kVar.f780b;
        R7.h hVar = aVar;
        D8.u uVar = new D8.u((InterfaceC0657j) kVar.c, null, hVarB, AbstractC0904a.F(interfaceC1788f, c1579y.f17491q), P8.n.J((EnumC1580z) AbstractC1787e.o.c(i12)), c1579y, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, c2058cG.c(AbstractC0904a.F(interfaceC1788f, i13)).equals(y.f820a) ? C1790h.f18862a : (C1790h) kVar.f781e, (C1391h) kVar.f783g, null);
        List list = c1579y.f17494t;
        A7.m.e(list, "proto.typeParameterList");
        k kVarA = kVar.a(uVar, list, (InterfaceC1788f) kVar.f780b, (C0746i0) kVar.d, (C1790h) kVar.f781e, (AbstractC1783a) kVar.f782f);
        C0746i0 c0746i0 = (C0746i0) kVar.d;
        Q qO = AbstractC1387d.o(c1579y, c0746i0);
        D d = (D) kVarA.f784h;
        T7.u uVarK = (qO == null || (abstractC0287wG = d.g(qO)) == null) ? null : r8.m.k(uVar, abstractC0287wG, hVar);
        InterfaceC0657j interfaceC0657j = (InterfaceC0657j) kVar.c;
        InterfaceC0652e interfaceC0652e = interfaceC0657j instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657j : null;
        T7.u uVarJ0 = interfaceC0652e != null ? interfaceC0652e.J0() : null;
        A7.m.f(c0746i0, "typeTable");
        List list2 = c1579y.f17497w;
        if (list2.isEmpty()) {
            list2 = null;
        }
        if (list2 == null) {
            List<Integer> list3 = c1579y.f17498x;
            A7.m.e(list3, "contextReceiverTypeIdList");
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list3, 10));
            for (Integer num : list3) {
                A7.m.e(num, "it");
                arrayList.add(c0746i0.c(num.intValue()));
            }
            list2 = arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        int i14 = 0;
        for (Object obj : list2) {
            int i15 = i14 + 1;
            if (i14 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            T7.u uVarE = r8.m.e(uVar, d.g((Q) obj), null, fVar, i14);
            if (uVarE != null) {
                arrayList2.add(uVarE);
            }
            i14 = i15;
        }
        List listB = d.b();
        List list4 = c1579y.f17500z;
        A7.m.e(list4, "proto.valueParameterList");
        uVar.q1(uVarK, uVarJ0, arrayList2, listB, ((s) kVarA.f785i).g(list4, c1579y, 1), d.g(AbstractC1387d.q(c1579y, c0746i0)), j.e((EnumC1555A) AbstractC1787e.f18841e.c(i12)), P8.n.t((f0) AbstractC1787e.d.c(i12)), n7.v.l);
        uVar.f8906y = AbstractC1787e.f18850p.c(i12).booleanValue();
        uVar.f8907z = AbstractC1787e.f18851q.c(i12).booleanValue();
        uVar.f8884A = AbstractC1787e.f18854t.c(i12).booleanValue();
        uVar.B = AbstractC1787e.f18852r.c(i12).booleanValue();
        uVar.f8885C = AbstractC1787e.f18853s.c(i12).booleanValue();
        uVar.f8889G = AbstractC1787e.f18855u.c(i12).booleanValue();
        uVar.f8886D = AbstractC1787e.f18856v.c(i12).booleanValue();
        uVar.f8890H = !AbstractC1787e.f18857w.c(i12).booleanValue();
        ((i) kVar.f779a).f771m.getClass();
        A7.m.f(c0746i0, "typeTable");
        return uVar;
    }

    public final D8.t f(G g7) {
        int i7;
        G g10;
        s sVar;
        R7.h aVar;
        k kVar;
        Q qC;
        k kVar2;
        C1784b c1784b;
        C1784b c1784b2;
        C1784b c1784b3;
        C1785c c1785c;
        C1785c c1785c2;
        G g11;
        I i10;
        I i11;
        J jG;
        I iF;
        AbstractC0287w abstractC0287wG;
        A7.m.f(g7, "proto");
        if ((g7.f17118n & 1) == 1) {
            i7 = g7.o;
        } else {
            int i12 = g7.f17119p;
            i7 = ((i12 >> 8) << 6) + (i12 & 63);
        }
        int i13 = i7;
        k kVar3 = this.f805a;
        D8.t tVar = new D8.t((InterfaceC0657j) kVar3.c, null, b(g7, i13, 2), j.e((EnumC1555A) AbstractC1787e.f18841e.c(i13)), P8.n.t((f0) AbstractC1787e.d.c(i13)), AbstractC1787e.f18858x.c(i13).booleanValue(), AbstractC0904a.F((InterfaceC1788f) kVar3.f780b, g7.f17120q), P8.n.J((EnumC1580z) AbstractC1787e.o.c(i13)), AbstractC1787e.B.c(i13).booleanValue(), AbstractC1787e.f18827A.c(i13).booleanValue(), AbstractC1787e.f18829D.c(i13).booleanValue(), AbstractC1787e.f18830E.c(i13).booleanValue(), AbstractC1787e.f18831F.c(i13).booleanValue(), g7, (InterfaceC1788f) kVar3.f780b, (C0746i0) kVar3.d, (C1790h) kVar3.f781e, (C1391h) kVar3.f783g);
        List list = g7.f17123t;
        A7.m.e(list, "proto.typeParameterList");
        k kVarA = kVar3.a(tVar, list, (InterfaceC1788f) kVar3.f780b, (C0746i0) kVar3.d, (C1790h) kVar3.f781e, (AbstractC1783a) kVar3.f782f);
        boolean zBooleanValue = AbstractC1787e.f18859y.c(i13).booleanValue();
        R7.f fVar = R7.g.f7535a;
        int i14 = 3;
        if (zBooleanValue && (g7.p() || (g7.f17118n & 64) == 64)) {
            E8.o oVar = ((i) kVar3.f779a).f762a;
            g10 = g7;
            sVar = this;
            aVar = new D8.a(oVar, new o(sVar, g10, i14, 1));
        } else {
            g10 = g7;
            sVar = this;
            aVar = fVar;
        }
        C0746i0 c0746i0 = (C0746i0) kVar3.d;
        Q qR = AbstractC1387d.r(g10, c0746i0);
        D d = (D) kVarA.f784h;
        AbstractC0287w abstractC0287wG2 = d.g(qR);
        List listB = d.b();
        InterfaceC0657j interfaceC0657j = (InterfaceC0657j) kVar3.c;
        InterfaceC0652e interfaceC0652e = interfaceC0657j instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657j : null;
        T7.u uVarJ0 = interfaceC0652e != null ? interfaceC0652e.J0() : null;
        A7.m.f(c0746i0, "typeTable");
        if (g7.p()) {
            qC = g10.f17124u;
            kVar = kVar3;
        } else {
            kVar = kVar3;
            qC = (g10.f17118n & 64) == 64 ? c0746i0.c(g10.f17125v) : null;
        }
        T7.u uVarK = (qC == null || (abstractC0287wG = d.g(qC)) == null) ? null : r8.m.k(tVar, abstractC0287wG, aVar);
        A7.m.f(c0746i0, "typeTable");
        List list2 = g10.f17126w;
        if (list2.isEmpty()) {
            list2 = null;
        }
        if (list2 == null) {
            List<Integer> list3 = g10.f17127x;
            A7.m.e(list3, "contextReceiverTypeIdList");
            kVar2 = kVarA;
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list3, 10));
            for (Integer num : list3) {
                A7.m.e(num, "it");
                arrayList.add(c0746i0.c(num.intValue()));
            }
            list2 = arrayList;
        } else {
            kVar2 = kVarA;
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(list2, 10));
        int i15 = 0;
        for (Object obj : list2) {
            int i16 = i15 + 1;
            if (i15 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            arrayList2.add(r8.m.e(tVar, d.g((Q) obj), null, fVar, i15));
            i15 = i16;
        }
        tVar.j1(abstractC0287wG2, listB, uVarJ0, uVarK, arrayList2);
        C1784b c1784b4 = AbstractC1787e.c;
        boolean zBooleanValue2 = c1784b4.c(i13).booleanValue();
        C1785c c1785c3 = AbstractC1787e.d;
        f0 f0Var = (f0) c1785c3.c(i13);
        C1785c c1785c4 = AbstractC1787e.f18841e;
        EnumC1555A enumC1555A = (EnumC1555A) c1785c4.c(i13);
        if (f0Var == null) {
            AbstractC1787e.a(10);
            throw null;
        }
        if (enumC1555A == null) {
            AbstractC1787e.a(11);
            throw null;
        }
        int iA = (zBooleanValue2 ? 1 << c1784b4.f18825a : 0) | (enumC1555A.a() << c1785c4.f18825a) | (f0Var.a() << c1785c3.f18825a);
        C1784b c1784b5 = AbstractC1787e.f18835J;
        c1784b5.getClass();
        C1784b c1784b6 = AbstractC1787e.f18836K;
        c1784b6.getClass();
        C1784b c1784b7 = AbstractC1787e.f18837L;
        c1784b7.getClass();
        O o = N.c;
        if (zBooleanValue) {
            int i17 = (g10.f17118n & 256) == 256 ? g10.f17113A : iA;
            boolean zBooleanValue3 = c1784b5.c(i17).booleanValue();
            boolean zBooleanValue4 = c1784b6.c(i17).booleanValue();
            boolean zBooleanValue5 = c1784b7.c(i17).booleanValue();
            R7.h hVarB = sVar.b(g10, i17, 3);
            if (zBooleanValue3) {
                c1784b = c1784b7;
                c1784b2 = c1784b6;
                c1784b3 = c1784b5;
                c1785c2 = c1785c3;
                c1785c = c1785c4;
                g11 = g10;
                iF = new I(tVar, hVarB, j.e((EnumC1555A) c1785c4.c(i17)), P8.n.t((f0) c1785c3.c(i17)), !zBooleanValue3, zBooleanValue4, zBooleanValue5, tVar.N(), null, o);
            } else {
                c1784b = c1784b7;
                c1784b2 = c1784b6;
                c1784b3 = c1784b5;
                c1785c = c1785c4;
                c1785c2 = c1785c3;
                g11 = g10;
                iF = r8.m.f(tVar, hVarB);
            }
            iF.f1(tVar.p());
            i10 = iF;
        } else {
            c1784b = c1784b7;
            c1784b2 = c1784b6;
            c1784b3 = c1784b5;
            c1785c = c1785c4;
            c1785c2 = c1785c3;
            g11 = g10;
            i10 = null;
        }
        if (AbstractC1787e.f18860z.c(i13).booleanValue()) {
            int i18 = (g11.f17118n & 512) == 512 ? g11.B : iA;
            boolean zBooleanValue6 = c1784b3.c(i18).booleanValue();
            boolean zBooleanValue7 = c1784b2.c(i18).booleanValue();
            boolean zBooleanValue8 = c1784b.c(i18).booleanValue();
            R7.h hVarB2 = sVar.b(g11, i18, 4);
            if (zBooleanValue6) {
                i11 = i10;
                J j3 = new J(tVar, hVarB2, j.e((EnumC1555A) c1785c.c(i18)), P8.n.t((f0) c1785c2.c(i18)), !zBooleanValue6, zBooleanValue7, zBooleanValue8, tVar.N(), null, o);
                k kVar4 = kVar2;
                T7.Q q4 = (T7.Q) AbstractC1998m.r0(((s) kVar4.a(j3, n7.u.l, (InterfaceC1788f) kVar4.f780b, (C0746i0) kVar4.d, (C1790h) kVar4.f781e, (AbstractC1783a) kVar4.f782f).f785i).g(AbstractC1387d.j(g11.f17129z), g11, 4));
                if (q4 == null) {
                    J.K0(6);
                    throw null;
                }
                j3.f8812y = q4;
                jG = j3;
            } else {
                i11 = i10;
                jG = r8.m.g(tVar, hVarB2);
            }
        } else {
            i11 = i10;
            jG = null;
        }
        if (AbstractC1787e.f18828C.c(i13).booleanValue()) {
            tVar.h1(null, new q(sVar, g11, tVar, 1));
        }
        InterfaceC0657j interfaceC0657j2 = (InterfaceC0657j) kVar.c;
        InterfaceC0652e interfaceC0652e2 = interfaceC0657j2 instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657j2 : null;
        if ((interfaceC0652e2 != null ? interfaceC0652e2.E() : 0) == 5) {
            tVar.h1(null, new q(sVar, g11, tVar, 3));
        }
        tVar.g1(i11, jG, new T7.r(sVar.c(g11, false)), new T7.r(sVar.c(g11, true)));
        return tVar;
    }

    public final List g(List list, AbstractC2136k abstractC2136k, int i7) {
        k kVar = this.f805a;
        InterfaceC0657j interfaceC0657j = (InterfaceC0657j) kVar.c;
        A7.m.d(interfaceC0657j, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        InterfaceC0649b interfaceC0649b = (InterfaceC0649b) interfaceC0657j;
        InterfaceC0657j interfaceC0657jO = interfaceC0649b.o();
        A7.m.e(interfaceC0657jO, "callableDescriptor.containingDeclaration");
        K kA = a(interfaceC0657jO);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        int i10 = 0;
        for (Object obj : list) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            Z z6 = (Z) obj;
            int i12 = (z6.f17255n & 1) == 1 ? z6.o : 0;
            R7.h xVar = (kA == null || !AbstractC1787e.c.c(i12).booleanValue()) ? R7.g.f7535a : new D8.x(((i) kVar.f779a).f762a, new r(this, kA, abstractC2136k, i7, i10, z6));
            C2061f c2061fF = AbstractC0904a.F((InterfaceC1788f) kVar.f780b, z6.f17256p);
            C0746i0 c0746i0 = (C0746i0) kVar.d;
            Q qY = AbstractC1387d.y(z6, c0746i0);
            D d = (D) kVar.f784h;
            AbstractC0287w abstractC0287wG = d.g(qY);
            boolean zBooleanValue = AbstractC1787e.f18832G.c(i12).booleanValue();
            boolean zBooleanValue2 = AbstractC1787e.f18833H.c(i12).booleanValue();
            boolean zBooleanValue3 = AbstractC1787e.f18834I.c(i12).booleanValue();
            A7.m.f(c0746i0, "typeTable");
            int i13 = z6.f17255n;
            Q qC = (i13 & 16) == 16 ? z6.f17259s : (i13 & 32) == 32 ? c0746i0.c(z6.f17260t) : null;
            AbstractC0287w abstractC0287wG2 = qC != null ? d.g(qC) : null;
            ArrayList arrayList2 = arrayList;
            arrayList2.add(new T7.Q(interfaceC0649b, null, i10, xVar, c2061fF, abstractC0287wG, zBooleanValue, zBooleanValue2, zBooleanValue3, abstractC0287wG2, N.c));
            arrayList = arrayList2;
            i10 = i11;
        }
        return AbstractC1998m.z0(arrayList);
    }
}
