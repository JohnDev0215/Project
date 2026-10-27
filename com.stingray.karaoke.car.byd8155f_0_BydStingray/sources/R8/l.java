package r8;

import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.J;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.K;
import Q7.L;
import T7.F;
import T7.H;
import T7.Q;
import T7.t;
import T7.u;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2061f;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List f21888b = AbstractC1998m.z0(ServiceLoader.load(g.class, g.class.getClassLoader()));
    public static final l c;
    public static final C2309b d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G8.c f21889a;

    static {
        C2309b c2309b = new C2309b();
        d = c2309b;
        c = new l(c2309b);
    }

    public l(G8.c cVar) {
        if (cVar != null) {
            this.f21889a = cVar;
        } else {
            a(5);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(int r25) {
        /*
            Method dump skipped, instruction units count: 1320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r8.l.a(int):void");
    }

    public static boolean b(AbstractC0287w abstractC0287w, AbstractC0287w abstractC0287w2, J j3) {
        if (abstractC0287w == null) {
            a(46);
            throw null;
        }
        if (abstractC0287w2 == null) {
            a(47);
            throw null;
        }
        if (AbstractC0268c.i(abstractC0287w) && AbstractC0268c.i(abstractC0287w2)) {
            return true;
        }
        return C0269d.g(j3, abstractC0287w.K0(), abstractC0287w2.K0());
    }

    public static void c(InterfaceC0650c interfaceC0650c, LinkedHashSet linkedHashSet) {
        if (interfaceC0650c == null) {
            a(17);
            throw null;
        }
        if (interfaceC0650c.N() != 2) {
            linkedHashSet.add(interfaceC0650c);
            return;
        }
        if (interfaceC0650c.q().isEmpty()) {
            throw new IllegalStateException("No overridden descriptors found for (fake override) " + interfaceC0650c);
        }
        Iterator it = interfaceC0650c.q().iterator();
        while (it.hasNext()) {
            c((InterfaceC0650c) it.next(), linkedHashSet);
        }
    }

    public static ArrayList d(InterfaceC0649b interfaceC0649b) {
        u uVarK = interfaceC0649b.K();
        ArrayList arrayList = new ArrayList();
        if (uVarK != null) {
            arrayList.add(uVarK.b());
        }
        Iterator it = interfaceC0649b.D0().iterator();
        while (it.hasNext()) {
            arrayList.add(((Q) it.next()).b());
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x016f, code lost:
    
        if (r1 == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0171, code lost:
    
        r0 = Q7.AbstractC0662o.f7194h;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0174, code lost:
    
        r0 = Q7.AbstractC0662o.f7193g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0176, code lost:
    
        r12 = ((Q7.InterfaceC0650c) s(r11, new r8.j(0))).a0(r12, r7, r0);
        r13.p(r12, r11);
        r13.b(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x018c, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(java.util.Collection r11, Q7.InterfaceC0652e r12, r8.m r13) {
        /*
            Method dump skipped, instruction units count: 421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r8.l.e(java.util.Collection, Q7.e, r8.m):void");
    }

    public static ArrayList g(Object obj, LinkedList linkedList, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        if (obj == null) {
            a(99);
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        InterfaceC0649b interfaceC0649b = (InterfaceC0649b) interfaceC2782k.b(obj);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            InterfaceC0649b interfaceC0649b2 = (InterfaceC0649b) interfaceC2782k.b(next);
            if (obj == next) {
                it.remove();
            } else {
                int iJ = j(interfaceC0649b, interfaceC0649b2);
                if (iJ == 1) {
                    arrayList.add(next);
                    it.remove();
                } else if (iJ == 3) {
                    interfaceC2782k2.b(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public static k i(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2) {
        boolean z6;
        if (interfaceC0649b == null) {
            a(40);
            throw null;
        }
        if (interfaceC0649b2 == null) {
            a(41);
            throw null;
        }
        boolean z10 = interfaceC0649b instanceof InterfaceC0666t;
        if ((z10 && !(interfaceC0649b2 instanceof InterfaceC0666t)) || (((z6 = interfaceC0649b instanceof L)) && !(interfaceC0649b2 instanceof L))) {
            return k.d("Member kind mismatch");
        }
        if (!z10 && !z6) {
            throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: " + interfaceC0649b);
        }
        if (!interfaceC0649b.getName().equals(interfaceC0649b2.getName())) {
            return k.d("Name mismatch");
        }
        k kVarD = (interfaceC0649b.K() == null) != (interfaceC0649b2.K() == null) ? k.d("Receiver presence mismatch") : interfaceC0649b.D0().size() != interfaceC0649b2.D0().size() ? k.d("Value parameter number mismatch") : null;
        if (kVarD != null) {
            return kVarD;
        }
        return null;
    }

    public static int j(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2) {
        l lVar = c;
        int iC = lVar.l(interfaceC0649b2, interfaceC0649b, null).c();
        int iC2 = lVar.m(interfaceC0649b, interfaceC0649b2, null, false).c();
        if (iC == 1 && iC2 == 1) {
            return 1;
        }
        return (iC == 3 || iC2 == 3) ? 3 : 2;
    }

    public static boolean k(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2) {
        if (interfaceC0649b == null) {
            a(67);
            throw null;
        }
        if (interfaceC0649b2 == null) {
            a(68);
            throw null;
        }
        AbstractC0287w abstractC0287wP = interfaceC0649b.p();
        AbstractC0287w abstractC0287wP2 = interfaceC0649b2.p();
        if (!p(interfaceC0649b, interfaceC0649b2)) {
            return false;
        }
        J jF = c.f(interfaceC0649b.t(), interfaceC0649b2.t());
        if (interfaceC0649b instanceof InterfaceC0666t) {
            return o(interfaceC0649b, abstractC0287wP, interfaceC0649b2, abstractC0287wP2, jF);
        }
        if (!(interfaceC0649b instanceof L)) {
            throw new IllegalArgumentException("Unexpected callable: " + interfaceC0649b.getClass());
        }
        L l = (L) interfaceC0649b;
        L l10 = (L) interfaceC0649b2;
        T7.J jD = l.d();
        T7.J jD2 = l10.d();
        if ((jD == null || jD2 == null) ? true : p(jD, jD2)) {
            return (l.I() && l10.I()) ? C0269d.g(jF, abstractC0287wP.K0(), abstractC0287wP2.K0()) : (l.I() || !l10.I()) && o(interfaceC0649b, abstractC0287wP, interfaceC0649b2, abstractC0287wP2, jF);
        }
        return false;
    }

    public static boolean o(InterfaceC0649b interfaceC0649b, AbstractC0287w abstractC0287w, InterfaceC0649b interfaceC0649b2, AbstractC0287w abstractC0287w2, J j3) {
        if (interfaceC0649b == null) {
            a(73);
            throw null;
        }
        if (abstractC0287w == null) {
            a(74);
            throw null;
        }
        if (interfaceC0649b2 == null) {
            a(75);
            throw null;
        }
        if (abstractC0287w2 != null) {
            return C0269d.n(C0269d.f2461a, j3, abstractC0287w.K0(), abstractC0287w2.K0());
        }
        a(76);
        throw null;
    }

    public static boolean p(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2) {
        if (interfaceC0649b == null) {
            a(69);
            throw null;
        }
        if (interfaceC0649b2 != null) {
            Integer numB = AbstractC0662o.b(interfaceC0649b.e(), interfaceC0649b2.e());
            return numB == null || numB.intValue() >= 0;
        }
        a(70);
        throw null;
    }

    public static boolean q(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2) {
        if (interfaceC0649b == null) {
            a(13);
            throw null;
        }
        if (interfaceC0649b2 == null) {
            a(14);
            throw null;
        }
        boolean zEquals = interfaceC0649b.equals(interfaceC0649b2);
        C2309b c2309b = C2309b.l;
        if (!zEquals && c2309b.c(interfaceC0649b.b1(), interfaceC0649b2.b1(), false, true)) {
            return true;
        }
        InterfaceC0649b interfaceC0649bB1 = interfaceC0649b2.b1();
        int i7 = d.f21880a;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        d.b(interfaceC0649b.b1(), linkedHashSet);
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            if (c2309b.c(interfaceC0649bB1, (InterfaceC0649b) it.next(), false, true)) {
                return true;
            }
        }
        return false;
    }

    public static void r(InterfaceC0650c interfaceC0650c, InterfaceC2782k interfaceC2782k) {
        C0661n c0661n;
        C0661n c0661nF;
        C0661n c0661n2;
        if (interfaceC0650c == null) {
            a(107);
            throw null;
        }
        for (InterfaceC0650c interfaceC0650c2 : interfaceC0650c.q()) {
            if (interfaceC0650c2.e() == AbstractC0662o.f7193g) {
                r(interfaceC0650c2, interfaceC2782k);
            }
        }
        if (interfaceC0650c.e() != AbstractC0662o.f7193g) {
            return;
        }
        Collection<InterfaceC0650c> collectionQ = interfaceC0650c.q();
        if (collectionQ == null) {
            a(109);
            throw null;
        }
        if (collectionQ.isEmpty()) {
            c0661nF = AbstractC0662o.f7196j;
        } else {
            Iterator it = collectionQ.iterator();
            loop3: while (true) {
                c0661n = null;
                while (it.hasNext()) {
                    C0661n c0661nE = ((InterfaceC0650c) it.next()).e();
                    if (c0661n != null) {
                        Integer numB = AbstractC0662o.b(c0661nE, c0661n);
                        if (numB == null) {
                            break;
                        } else if (numB.intValue() > 0) {
                        }
                    }
                    c0661n = c0661nE;
                }
            }
            if (c0661n == null) {
                c0661nF = null;
                break;
            }
            Iterator it2 = collectionQ.iterator();
            while (it2.hasNext()) {
                Integer numB2 = AbstractC0662o.b(c0661n, ((InterfaceC0650c) it2.next()).e());
                if (numB2 == null || numB2.intValue() < 0) {
                    c0661nF = null;
                    break;
                }
            }
            c0661nF = c0661n;
        }
        if (c0661nF == null) {
            c0661nF = null;
            break;
        }
        if (interfaceC0650c.N() == 2) {
            for (InterfaceC0650c interfaceC0650c3 : collectionQ) {
                if (interfaceC0650c3.h() != 4 && !interfaceC0650c3.e().equals(c0661nF)) {
                    c0661nF = null;
                    break;
                }
            }
        } else {
            c0661nF = AbstractC0662o.f(c0661nF.f7187a.c());
        }
        if (c0661nF == null) {
            if (interfaceC2782k != null) {
                interfaceC2782k.b(interfaceC0650c);
            }
            c0661n2 = AbstractC0662o.f7191e;
        } else {
            c0661n2 = c0661nF;
        }
        if (interfaceC0650c instanceof H) {
            H h10 = (H) interfaceC0650c;
            if (c0661n2 == null) {
                H.K0(20);
                throw null;
            }
            h10.f8805v = c0661n2;
            Iterator it3 = ((L) interfaceC0650c).u().iterator();
            while (it3.hasNext()) {
                r((K) it3.next(), c0661nF == null ? null : interfaceC2782k);
            }
            return;
        }
        if (interfaceC0650c instanceof t) {
            t tVar = (t) interfaceC0650c;
            if (c0661n2 != null) {
                tVar.f8905x = c0661n2;
                return;
            } else {
                t.K0(10);
                throw null;
            }
        }
        F f10 = (F) interfaceC0650c;
        f10.f8779w = c0661n2;
        if (c0661n2 != f10.c1().e()) {
            f10.f8773q = false;
        }
    }

    public static Object s(Collection collection, InterfaceC2782k interfaceC2782k) {
        Object next;
        if (collection.size() == 1) {
            Object objX = AbstractC1998m.X(collection);
            if (objX != null) {
                return objX;
            }
            a(80);
            throw null;
        }
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList2.add(interfaceC2782k.b(it.next()));
        }
        Object objX2 = AbstractC1998m.X(collection);
        InterfaceC0649b interfaceC0649b = (InterfaceC0649b) interfaceC2782k.b(objX2);
        for (Object obj : collection) {
            InterfaceC0649b interfaceC0649b2 = (InterfaceC0649b) interfaceC2782k.b(obj);
            if (interfaceC0649b2 == null) {
                a(71);
                throw null;
            }
            Iterator it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    arrayList.add(obj);
                    break;
                }
                if (!k(interfaceC0649b2, (InterfaceC0649b) it2.next())) {
                    break;
                }
            }
            if (k(interfaceC0649b2, interfaceC0649b) && !k(interfaceC0649b, interfaceC0649b2)) {
                objX2 = obj;
            }
        }
        if (arrayList.isEmpty()) {
            if (objX2 != null) {
                return objX2;
            }
            a(81);
            throw null;
        }
        if (arrayList.size() == 1) {
            Object objX3 = AbstractC1998m.X(arrayList);
            if (objX3 != null) {
                return objX3;
            }
            a(82);
            throw null;
        }
        Iterator it3 = arrayList.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
            if (!AbstractC0268c.j(((InterfaceC0649b) interfaceC2782k.b(next)).p())) {
                break;
            }
        }
        if (next != null) {
            return next;
        }
        Object objX4 = AbstractC1998m.X(arrayList);
        if (objX4 != null) {
            return objX4;
        }
        a(84);
        throw null;
    }

    public final J f(List list, List list2) {
        if (list == null) {
            a(42);
            throw null;
        }
        if (list2 == null) {
            a(43);
            throw null;
        }
        boolean zIsEmpty = list.isEmpty();
        G8.e eVar = G8.e.l;
        G8.f fVar = G8.f.f2724a;
        G8.c cVar = this.f21889a;
        if (zIsEmpty) {
            return new J(true, true, new m2.b((HashMap) null, cVar), eVar, fVar);
        }
        HashMap map = new HashMap();
        for (int i7 = 0; i7 < list.size(); i7++) {
            map.put(((Q7.Q) list.get(i7)).C(), ((Q7.Q) list2.get(i7)).C());
        }
        return new J(true, true, new m2.b(map, cVar), eVar, fVar);
    }

    public final void h(C2061f c2061f, Collection collection, Collection collection2, InterfaceC0652e interfaceC0652e, m mVar) {
        Integer numB;
        if (c2061f == null) {
            a(52);
            throw null;
        }
        if (collection == null) {
            a(53);
            throw null;
        }
        if (collection2 == null) {
            a(54);
            throw null;
        }
        if (interfaceC0652e == null) {
            a(55);
            throw null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            InterfaceC0650c interfaceC0650c = (InterfaceC0650c) it.next();
            if (interfaceC0650c == null) {
                a(59);
                throw null;
            }
            ArrayList arrayList = new ArrayList(collection.size());
            O8.i iVar = new O8.i();
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                InterfaceC0650c interfaceC0650c2 = (InterfaceC0650c) it2.next();
                int iC = l(interfaceC0650c2, interfaceC0650c, interfaceC0652e).c();
                boolean z6 = !AbstractC0662o.e(interfaceC0650c2.e()) && AbstractC0662o.c(AbstractC0662o.l, interfaceC0650c2, interfaceC0650c) == null;
                int iC2 = AbstractC2641j.c(iC);
                if (iC2 == 0) {
                    if (z6) {
                        iVar.add(interfaceC0650c2);
                    }
                    arrayList.add(interfaceC0650c2);
                } else if (iC2 == 2) {
                    if (z6) {
                        mVar.d(interfaceC0650c2, interfaceC0650c);
                    }
                    arrayList.add(interfaceC0650c2);
                }
            }
            mVar.p(interfaceC0650c, iVar);
            linkedHashSet.removeAll(arrayList);
        }
        if (linkedHashSet.size() >= 2) {
            InterfaceC0657j interfaceC0657jO = ((InterfaceC0650c) linkedHashSet.iterator().next()).o();
            if (!linkedHashSet.isEmpty()) {
                Iterator it3 = linkedHashSet.iterator();
                while (it3.hasNext()) {
                    if (((InterfaceC0650c) it3.next()).o() != interfaceC0657jO) {
                        LinkedList<InterfaceC0650c> linkedList = new LinkedList(linkedHashSet);
                        while (!linkedList.isEmpty()) {
                            linkedList.isEmpty();
                            InterfaceC0650c interfaceC0650c3 = null;
                            for (InterfaceC0650c interfaceC0650c4 : linkedList) {
                                if (interfaceC0650c3 == null || ((numB = AbstractC0662o.b(interfaceC0650c3.e(), interfaceC0650c4.e())) != null && numB.intValue() < 0)) {
                                    interfaceC0650c3 = interfaceC0650c4;
                                }
                            }
                            A7.m.c(interfaceC0650c3);
                            e(g(interfaceC0650c3, linkedList, new j(1), new N3.e(mVar, 1, interfaceC0650c3)), interfaceC0652e, mVar);
                        }
                        return;
                    }
                }
            }
        }
        Iterator it4 = linkedHashSet.iterator();
        while (it4.hasNext()) {
            e(Collections.singleton((InterfaceC0650c) it4.next()), interfaceC0652e, mVar);
        }
    }

    public final k l(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2, InterfaceC0652e interfaceC0652e) {
        if (interfaceC0649b == null) {
            a(19);
            throw null;
        }
        if (interfaceC0649b2 != null) {
            return m(interfaceC0649b, interfaceC0649b2, interfaceC0652e, false);
        }
        a(20);
        throw null;
    }

    public final k m(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2, InterfaceC0652e interfaceC0652e, boolean z6) {
        if (interfaceC0649b == null) {
            a(22);
            throw null;
        }
        if (interfaceC0649b2 == null) {
            a(23);
            throw null;
        }
        k kVarN = n(interfaceC0649b, interfaceC0649b2, z6);
        boolean z10 = kVarN.c() == 1;
        List<g> list = f21888b;
        Iterator it = list.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            e eVar = e.l;
            if (!zHasNext) {
                if (!z10) {
                    return kVarN;
                }
                for (g gVar : list) {
                    if (gVar.b() == eVar) {
                        int iOrdinal = gVar.a(interfaceC0649b, interfaceC0649b2, interfaceC0652e).ordinal();
                        if (iOrdinal == 0) {
                            throw new IllegalStateException("Contract violation in " + gVar.getClass().getName() + " condition. It's not supposed to end with success");
                        }
                        if (iOrdinal == 1) {
                            return k.b("External condition failed");
                        }
                        if (iOrdinal == 2) {
                            return k.d("External condition");
                        }
                    }
                }
                k kVar = k.f21886b;
                if (kVar != null) {
                    return kVar;
                }
                k.a(0);
                throw null;
            }
            g gVar2 = (g) it.next();
            if (gVar2.b() != eVar && (!z10 || gVar2.b() != e.f21881m)) {
                int iOrdinal2 = gVar2.a(interfaceC0649b, interfaceC0649b2, interfaceC0652e).ordinal();
                if (iOrdinal2 == 0) {
                    z10 = true;
                } else {
                    if (iOrdinal2 == 1) {
                        return k.b("External condition failed");
                    }
                    if (iOrdinal2 == 2) {
                        return k.d("External condition");
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a9, code lost:
    
        r10.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final r8.k n(Q7.InterfaceC0649b r13, Q7.InterfaceC0649b r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r8.l.n(Q7.b, Q7.b, boolean):r8.k");
    }
}
