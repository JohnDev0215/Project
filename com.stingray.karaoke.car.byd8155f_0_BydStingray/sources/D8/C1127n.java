package d8;

import F8.AbstractC0287w;
import F8.Y;
import F8.a0;
import H7.H;
import Q7.AbstractC0662o;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0665s;
import Q7.InterfaceC0666t;
import Q7.L;
import T.C0741g;
import T7.I;
import T7.J;
import T7.K;
import T7.Q;
import W7.AbstractC0839c;
import Z7.AbstractC0901d;
import Z7.C0902e;
import Z7.C0903f;
import Z7.F;
import a.AbstractC0904a;
import b8.C1007b;
import b8.C1009d;
import b8.C1011f;
import b8.C1012g;
import c8.C1036a;
import c8.C1038c;
import e8.C1236a;
import f8.InterfaceC1265a;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import l3.P;
import n7.AbstractC1985B;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import o8.C2058c;
import o8.C2061f;
import v8.AbstractC2564d;
import w8.C2606a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1127n extends z {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC0652e f13605n;
    public final W7.n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f13606p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final E8.i f13607q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final E8.i f13608r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final E8.i f13609s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E8.i f13610t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final E8.j f13611u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1127n(N0.q qVar, InterfaceC0652e interfaceC0652e, W7.n nVar, boolean z6, C1127n c1127n) {
        super(qVar, c1127n);
        A7.m.f(qVar, "c");
        A7.m.f(interfaceC0652e, "ownerDescriptor");
        A7.m.f(nVar, "jClass");
        this.f13605n = interfaceC0652e;
        this.o = nVar;
        this.f13606p = z6;
        E8.l lVar = ((C1036a) qVar.f4399m).f12411a;
        C1125l c1125l = new C1125l(this, qVar);
        lVar.getClass();
        this.f13607q = new E8.i(lVar, c1125l);
        C1126m c1126m = new C1126m(this, 1);
        lVar.getClass();
        this.f13608r = new E8.i(lVar, c1126m);
        C1125l c1125l2 = new C1125l(qVar, this);
        lVar.getClass();
        this.f13609s = new E8.i(lVar, c1125l2);
        C1126m c1126m2 = new C1126m(this, 0);
        lVar.getClass();
        this.f13610t = new E8.i(lVar, c1126m2);
        this.f13611u = lVar.c(new C0741g(this, 8, qVar));
    }

    public static K C(K k10, InterfaceC0666t interfaceC0666t, AbstractCollection abstractCollection) {
        if (abstractCollection.isEmpty()) {
            return k10;
        }
        Iterator it = abstractCollection.iterator();
        while (it.hasNext()) {
            K k11 = (K) it.next();
            if (!k10.equals(k11) && k11.f8896N == null && F(k11, interfaceC0666t)) {
                InterfaceC0666t interfaceC0666tMo0a = k10.v0().r().mo0a();
                A7.m.c(interfaceC0666tMo0a);
                return (K) interfaceC0666tMo0a;
            }
        }
        return k10;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static T7.K D(T7.K r5) {
        /*
            java.util.List r0 = r5.D0()
            java.lang.String r1 = "valueParameters"
            A7.m.e(r0, r1)
            java.lang.Object r0 = n7.AbstractC1998m.i0(r0)
            T7.Q r0 = (T7.Q) r0
            r2 = 0
            if (r0 == 0) goto L7d
            r3 = r0
            T7.S r3 = (T7.S) r3
            F8.w r3 = r3.b()
            F8.K r3 = r3.B0()
            Q7.g r3 = r3.n()
            if (r3 == 0) goto L36
            o8.e r3 = v8.AbstractC2564d.h(r3)
            boolean r4 = r3.d()
            if (r4 == 0) goto L2e
            goto L2f
        L2e:
            r3 = r2
        L2f:
            if (r3 == 0) goto L36
            o8.c r3 = r3.g()
            goto L37
        L36:
            r3 = r2
        L37:
            o8.c r4 = N7.o.f4871f
            boolean r3 = A7.m.a(r3, r4)
            if (r3 == 0) goto L40
            goto L41
        L40:
            r0 = r2
        L41:
            if (r0 != 0) goto L44
            goto L7d
        L44:
            Q7.s r2 = r5.v0()
            java.util.List r5 = r5.D0()
            A7.m.e(r5, r1)
            java.util.List r5 = n7.AbstractC1998m.W(r5)
            Q7.s r5 = r2.h(r5)
            T7.S r0 = (T7.S) r0
            F8.w r0 = r0.b()
            java.util.List r0 = r0.o0()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            F8.O r0 = (F8.O) r0
            F8.w r0 = r0.b()
            Q7.s r5 = r5.E(r0)
            Q7.t r5 = r5.mo0a()
            T7.K r5 = (T7.K) r5
            if (r5 != 0) goto L79
            goto L7c
        L79:
            r0 = 1
            r5.f8889G = r0
        L7c:
            return r5
        L7d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: d8.C1127n.D(T7.K):T7.K");
    }

    public static boolean F(InterfaceC0666t interfaceC0666t, InterfaceC0666t interfaceC0666t2) {
        int iC = r8.l.c.n(interfaceC0666t2, interfaceC0666t, true).c();
        A7.k.s(iC, "DEFAULT.isOverridableByW…iptor, this, true).result");
        return iC == 1 && !H.w(interfaceC0666t2, interfaceC0666t);
    }

    public static boolean G(K k10, K k11) {
        int i7 = AbstractC0901d.l;
        A7.m.f(k10, "<this>");
        if (A7.m.a(k10.getName().b(), "removeAt") && A7.m.a(h6.f.d(k10), F.f10917g.f10908b)) {
            k11 = k11.b1();
        }
        A7.m.e(k11, "if (superDescriptor.isRe…iginal else subDescriptor");
        return F(k11, k10);
    }

    public static K H(L l, String str, InterfaceC2782k interfaceC2782k) {
        K k10;
        Iterator it = ((Iterable) interfaceC2782k.b(C2061f.e(str))).iterator();
        do {
            k10 = null;
            if (!it.hasNext()) {
                break;
            }
            K k11 = (K) it.next();
            if (k11.D0().size() == 0) {
                G8.l lVar = G8.d.f2722a;
                AbstractC0287w abstractC0287w = k11.f8900s;
                if (abstractC0287w == null ? false : lVar.b(abstractC0287w, l.b())) {
                    k10 = k11;
                }
            }
        } while (k10 == null);
        return k10;
    }

    public static K J(L l, InterfaceC2782k interfaceC2782k) {
        K k10;
        AbstractC0287w abstractC0287w;
        String strB = l.getName().b();
        A7.m.e(strB, "name.asString()");
        Iterator it = ((Iterable) interfaceC2782k.b(C2061f.e(Z7.w.b(strB)))).iterator();
        do {
            k10 = null;
            if (!it.hasNext()) {
                break;
            }
            K k11 = (K) it.next();
            if (k11.D0().size() == 1 && (abstractC0287w = k11.f8900s) != null) {
                C2061f c2061f = N7.i.f4796e;
                if (N7.i.C(abstractC0287w, N7.n.d)) {
                    G8.l lVar = G8.d.f2722a;
                    List listD0 = k11.D0();
                    A7.m.e(listD0, "descriptor.valueParameters");
                    if (lVar.a(((Q) AbstractC1998m.r0(listD0)).b(), l.b())) {
                        k10 = k11;
                    }
                }
            }
        } while (k10 == null);
        return k10;
    }

    public static boolean M(K k10, InterfaceC0666t interfaceC0666t) {
        String strC = h6.f.c(k10, 2);
        InterfaceC0666t interfaceC0666tB1 = interfaceC0666t.b1();
        A7.m.e(interfaceC0666tB1, "builtinWithErasedParameters.original");
        return strC.equals(h6.f.c(interfaceC0666tB1, 2)) && !F(k10, interfaceC0666t);
    }

    public static final ArrayList v(C1127n c1127n, C2061f c2061f) {
        Collection collectionA = ((InterfaceC1116c) c1127n.f13636e.a()).a(c2061f);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(collectionA, 10));
        Iterator it = collectionA.iterator();
        while (it.hasNext()) {
            arrayList.add(c1127n.t((W7.w) it.next()));
        }
        return arrayList;
    }

    public static final ArrayList w(C1127n c1127n, C2061f c2061f) {
        LinkedHashSet linkedHashSetK = c1127n.K(c2061f);
        ArrayList arrayList = new ArrayList();
        for (Object obj : linkedHashSetK) {
            K k10 = (K) obj;
            A7.m.f(k10, "<this>");
            if (P8.n.y(k10) == null && C0903f.a(k10) == null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void A(Set set, AbstractCollection abstractCollection, O8.i iVar, InterfaceC2782k interfaceC2782k) {
        K kJ;
        J jM;
        C1009d c1009d;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            L l = (L) it.next();
            if (E(l, interfaceC2782k)) {
                K kI = I(l, interfaceC2782k);
                A7.m.c(kI);
                if (l.I()) {
                    kJ = J(l, interfaceC2782k);
                    A7.m.c(kJ);
                } else {
                    kJ = null;
                }
                if (kJ != null) {
                    kJ.h();
                    kI.h();
                }
                InterfaceC0652e interfaceC0652e = this.f13605n;
                A7.m.f(interfaceC0652e, "ownerDescriptor");
                C1009d c1009d2 = new C1009d(interfaceC0652e, R7.g.f7535a, kI.h(), kI.e(), kJ != null, l.getName(), kI.g(), null, 1, false, null);
                AbstractC0287w abstractC0287w = kI.f8900s;
                A7.m.c(abstractC0287w);
                n7.u uVar = n7.u.l;
                c1009d2.j1(abstractC0287w, uVar, p(), null, uVar);
                I iL = r8.m.l(c1009d2, kI.k(), false, kI.g());
                iL.f8780x = kI;
                iL.f1(c1009d2.b());
                if (kJ != null) {
                    List listD0 = kJ.D0();
                    A7.m.e(listD0, "setterMethod.valueParameters");
                    Q q4 = (Q) AbstractC1998m.a0(listD0);
                    if (q4 == null) {
                        throw new AssertionError("No parameter found for " + kJ);
                    }
                    jM = r8.m.m(c1009d2, kJ.k(), q4.k(), false, kJ.e(), kJ.g());
                    jM.f8780x = kJ;
                } else {
                    jM = null;
                }
                c1009d2.g1(iL, jM, null, null);
                c1009d = c1009d2;
            } else {
                c1009d = null;
            }
            if (c1009d != null) {
                abstractCollection.add(c1009d);
                if (iVar != null) {
                    iVar.add(l);
                    return;
                }
                return;
            }
        }
    }

    public final Collection B() {
        boolean z6 = this.f13606p;
        InterfaceC0652e interfaceC0652e = this.f13605n;
        if (z6) {
            Collection collectionO = interfaceC0652e.C().o();
            A7.m.e(collectionO, "ownerDescriptor.typeConstructor.supertypes");
            return collectionO;
        }
        ((C1036a) this.f13635b.f4399m).f12427u.getClass();
        A7.m.f(interfaceC0652e, "classDescriptor");
        Collection collectionO2 = interfaceC0652e.C().o();
        A7.m.e(collectionO2, "classDescriptor.typeConstructor.supertypes");
        return collectionO2;
    }

    public final boolean E(L l, InterfaceC2782k interfaceC2782k) {
        if (P8.n.F(l)) {
            return false;
        }
        K kI = I(l, interfaceC2782k);
        K kJ = J(l, interfaceC2782k);
        if (kI == null) {
            return false;
        }
        if (l.I()) {
            return kJ != null && kJ.h() == kI.h();
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, java.util.Map] */
    public final K I(L l, InterfaceC2782k interfaceC2782k) {
        C2061f c2061f;
        I iC = l.c();
        String strB = null;
        I i7 = iC != null ? (I) P8.n.y(iC) : null;
        if (i7 != null) {
            N7.i.y(i7);
            InterfaceC0650c interfaceC0650cB = AbstractC2564d.b(AbstractC2564d.k(i7), C0902e.f10935p);
            if (interfaceC0650cB != null && (c2061f = (C2061f) Z7.g.f10941a.get(AbstractC2564d.g(interfaceC0650cB))) != null) {
                strB = c2061f.b();
            }
        }
        if (strB != null && !P8.n.B(this.f13605n, i7)) {
            return H(l, strB, interfaceC2782k);
        }
        String strB2 = l.getName().b();
        A7.m.e(strB2, "name.asString()");
        return H(l, Z7.w.a(strB2), interfaceC2782k);
    }

    public final LinkedHashSet K(C2061f c2061f) {
        Collection collectionB = B();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = collectionB.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(linkedHashSet, ((AbstractC0287w) it.next()).z0().a(c2061f, Y7.b.f10800p));
        }
        return linkedHashSet;
    }

    public final Set L(C2061f c2061f) {
        Collection collectionB = B();
        ArrayList arrayList = new ArrayList();
        Iterator it = collectionB.iterator();
        while (it.hasNext()) {
            Collection collectionB2 = ((AbstractC0287w) it.next()).z0().b(c2061f, Y7.b.f10800p);
            ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(collectionB2, 10));
            Iterator it2 = collectionB2.iterator();
            while (it2.hasNext()) {
                arrayList2.add((L) it2.next());
            }
            AbstractC2004s.L(arrayList, arrayList2);
        }
        return AbstractC1998m.D0(arrayList);
    }

    public final boolean N(K k10) {
        Collection collectionE;
        C2061f name = k10.getName();
        A7.m.e(name, "function.name");
        String strB = name.b();
        A7.m.e(strB, "name.asString()");
        C2058c c2058c = Z7.w.f10961a;
        if (Q8.o.z0(strB, "get", false) || Q8.o.z0(strB, "is", false)) {
            C2061f c2061fP = AbstractC0904a.P(name, "get", null, 12);
            if (c2061fP == null) {
                c2061fP = AbstractC0904a.P(name, "is", null, 8);
            }
            collectionE = AbstractC1999n.E(c2061fP);
        } else if (Q8.o.z0(strB, "set", false)) {
            collectionE = AbstractC1996k.M(new C2061f[]{AbstractC0904a.P(name, "set", null, 4), AbstractC0904a.P(name, "set", "is", 4)});
        } else {
            collectionE = (List) Z7.g.f10942b.get(name);
            if (collectionE == null) {
                collectionE = n7.u.l;
            }
        }
        if (!collectionE.isEmpty()) {
            Iterator it = collectionE.iterator();
            while (it.hasNext()) {
                Set<L> setL = L((C2061f) it.next());
                if (!(setL instanceof Collection) || !setL.isEmpty()) {
                    for (L l : setL) {
                        if (E(l, new C0741g(k10, 7, this))) {
                            if (!l.I()) {
                                String strB2 = k10.getName().b();
                                A7.m.e(strB2, "function.name.asString()");
                                if (!Q8.o.z0(strB2, "set", false)) {
                                }
                            }
                            return false;
                        }
                    }
                }
            }
        }
        ArrayList arrayList = F.f10913a;
        C2061f name2 = k10.getName();
        A7.m.e(name2, "name");
        C2061f c2061f = (C2061f) F.f10921k.get(name2);
        if (c2061f != null) {
            LinkedHashSet linkedHashSetK = K(c2061f);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : linkedHashSetK) {
                K k11 = (K) obj;
                A7.m.f(k11, "<this>");
                if (P8.n.y(k11) != null) {
                    arrayList2.add(obj);
                }
            }
            if (!arrayList2.isEmpty()) {
                InterfaceC0665s interfaceC0665sV0 = k10.v0();
                interfaceC0665sV0.s(c2061f);
                interfaceC0665sV0.Q();
                interfaceC0665sV0.y();
                InterfaceC0666t interfaceC0666tMo0a = interfaceC0665sV0.mo0a();
                A7.m.c(interfaceC0666tMo0a);
                K k12 = (K) interfaceC0666tMo0a;
                if (!arrayList2.isEmpty()) {
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        if (G((K) it2.next(), k12)) {
                            return false;
                        }
                    }
                }
            }
        }
        int i7 = C0903f.l;
        C2061f name3 = k10.getName();
        A7.m.e(name3, "name");
        if (C0903f.b(name3)) {
            C2061f name4 = k10.getName();
            A7.m.e(name4, "name");
            LinkedHashSet linkedHashSetK2 = K(name4);
            ArrayList arrayList3 = new ArrayList();
            Iterator it3 = linkedHashSetK2.iterator();
            while (it3.hasNext()) {
                InterfaceC0666t interfaceC0666tA = C0903f.a((K) it3.next());
                if (interfaceC0666tA != null) {
                    arrayList3.add(interfaceC0666tA);
                }
            }
            if (!arrayList3.isEmpty()) {
                Iterator it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    if (M(k10, (InterfaceC0666t) it4.next())) {
                        return false;
                    }
                }
            }
        }
        K kD = D(k10);
        if (kD != null) {
            C2061f name5 = k10.getName();
            A7.m.e(name5, "name");
            LinkedHashSet<K> linkedHashSetK3 = K(name5);
            if (!linkedHashSetK3.isEmpty()) {
                for (K k13 : linkedHashSetK3) {
                    if (k13.m() && F(kD, k13)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final void O(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        A7.m.f(((C1036a) this.f13635b.f4399m).f12421n, "<this>");
        A7.m.f(this.f13605n, "scopeOwner");
    }

    @Override // d8.z, y8.o, y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        O(c2061f, bVar);
        return super.a(c2061f, bVar);
    }

    @Override // d8.z, y8.o, y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        O(c2061f, bVar);
        return super.b(c2061f, bVar);
    }

    @Override // y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        E8.j jVar;
        InterfaceC0652e interfaceC0652e;
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        O(c2061f, bVar);
        C1127n c1127n = (C1127n) this.c;
        return (c1127n == null || (jVar = c1127n.f13611u) == null || (interfaceC0652e = (InterfaceC0652e) jVar.b(c2061f)) == null) ? (InterfaceC0654g) this.f13611u.b(c2061f) : interfaceC0652e;
    }

    @Override // d8.z
    public final Set h(y8.f fVar, y8.k kVar) {
        A7.m.f(fVar, "kindFilter");
        return AbstractC1985B.q((Set) this.f13608r.a(), ((Map) this.f13610t.a()).keySet());
    }

    @Override // d8.z
    public final Set i(y8.f fVar, y8.k kVar) {
        A7.m.f(fVar, "kindFilter");
        InterfaceC0652e interfaceC0652e = this.f13605n;
        Collection collectionO = interfaceC0652e.C().o();
        A7.m.e(collectionO, "ownerDescriptor.typeConstructor.supertypes");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = collectionO.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(linkedHashSet, ((AbstractC0287w) it.next()).z0().e());
        }
        E8.i iVar = this.f13636e;
        linkedHashSet.addAll(((InterfaceC1116c) iVar.a()).b());
        linkedHashSet.addAll(((InterfaceC1116c) iVar.a()).d());
        linkedHashSet.addAll(h(fVar, kVar));
        N0.q qVar = this.f13635b;
        ((C2606a) ((C1036a) qVar.f4399m).f12430x).getClass();
        A7.m.f(qVar, "_context_receiver_0");
        A7.m.f(interfaceC0652e, "thisDescriptor");
        linkedHashSet.addAll(new ArrayList());
        return linkedHashSet;
    }

    @Override // d8.z
    public final void j(ArrayList arrayList, C2061f c2061f) throws IllegalAccessException, InvocationTargetException {
        A7.m.f(c2061f, "name");
        boolean zF = this.o.f();
        InterfaceC0652e interfaceC0652e = this.f13605n;
        N0.q qVar = this.f13635b;
        if (zF) {
            E8.i iVar = this.f13636e;
            if (((InterfaceC1116c) iVar.a()).f(c2061f) != null) {
                if (arrayList.isEmpty()) {
                    W7.z zVarF = ((InterfaceC1116c) iVar.a()).f(c2061f);
                    A7.m.c(zVarF);
                    C1038c c1038cY = l9.l.Y(qVar, zVarF);
                    C2061f c2061fC = zVarF.c();
                    C1036a c1036a = (C1036a) qVar.f4399m;
                    C1011f c1011fR1 = C1011f.r1(interfaceC0652e, c1038cY, c2061fC, c1036a.f12418j.b(zVarF), true);
                    C1236a c1236aS = AbstractC0904a.S(2, false, false, null, 6);
                    AbstractC0287w abstractC0287wG = ((P) qVar.l).G(zVarF.f(), c1236aS);
                    T7.u uVarP = p();
                    n7.u uVar = n7.u.l;
                    c1011fR1.q1(null, uVarP, uVar, uVar, uVar, abstractC0287wG, 3, AbstractC0662o.f7191e, null);
                    c1011fR1.f12329P = 1;
                    c1036a.f12415g.getClass();
                    arrayList.add(c1011fR1);
                } else {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (((K) it.next()).D0().isEmpty()) {
                            break;
                        }
                    }
                    W7.z zVarF2 = ((InterfaceC1116c) iVar.a()).f(c2061f);
                    A7.m.c(zVarF2);
                    C1038c c1038cY2 = l9.l.Y(qVar, zVarF2);
                    C2061f c2061fC2 = zVarF2.c();
                    C1036a c1036a2 = (C1036a) qVar.f4399m;
                    C1011f c1011fR12 = C1011f.r1(interfaceC0652e, c1038cY2, c2061fC2, c1036a2.f12418j.b(zVarF2), true);
                    C1236a c1236aS2 = AbstractC0904a.S(2, false, false, null, 6);
                    AbstractC0287w abstractC0287wG2 = ((P) qVar.l).G(zVarF2.f(), c1236aS2);
                    T7.u uVarP2 = p();
                    n7.u uVar2 = n7.u.l;
                    c1011fR12.q1(null, uVarP2, uVar2, uVar2, uVar2, abstractC0287wG2, 3, AbstractC0662o.f7191e, null);
                    c1011fR12.f12329P = 1;
                    c1036a2.f12415g.getClass();
                    arrayList.add(c1011fR12);
                }
            }
        }
        ((C2606a) ((C1036a) qVar.f4399m).f12430x).getClass();
        A7.m.f(qVar, "_context_receiver_0");
        A7.m.f(interfaceC0652e, "thisDescriptor");
        A7.m.f(c2061f, "name");
    }

    @Override // d8.z
    public final InterfaceC1116c k() {
        return new C1114a(this.o, C1123j.f13594n);
    }

    @Override // d8.z
    public final void m(LinkedHashSet linkedHashSet, C2061f c2061f) {
        A7.m.f(c2061f, "name");
        LinkedHashSet linkedHashSetK = K(c2061f);
        ArrayList arrayList = F.f10913a;
        if (!F.f10920j.contains(c2061f)) {
            int i7 = C0903f.l;
            if (!C0903f.b(c2061f)) {
                if (!linkedHashSetK.isEmpty()) {
                    Iterator it = linkedHashSetK.iterator();
                    while (it.hasNext()) {
                        if (((InterfaceC0666t) it.next()).m()) {
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : linkedHashSetK) {
                    if (N((K) obj)) {
                        arrayList2.add(obj);
                    }
                }
                y(linkedHashSet, c2061f, arrayList2, false);
                return;
            }
        }
        O8.i iVar = new O8.i();
        LinkedHashSet linkedHashSetC0 = l9.d.c0(c2061f, linkedHashSetK, n7.u.l, this.f13605n, B8.l.f786a, ((C1036a) this.f13635b.f4399m).f12427u.d);
        z(c2061f, linkedHashSet, linkedHashSetC0, linkedHashSet, new D8.j(1, 1, this));
        z(c2061f, linkedHashSet, linkedHashSetC0, iVar, new D8.j(1, 2, this));
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : linkedHashSetK) {
            if (N((K) obj2)) {
                arrayList3.add(obj2);
            }
        }
        y(linkedHashSet, c2061f, AbstractC1998m.n0(arrayList3, iVar), true);
    }

    /* JADX WARN: Type inference failed for: r8v10, types: [java.lang.Object, m7.f] */
    @Override // d8.z
    public final void n(ArrayList arrayList, C2061f c2061f) {
        Set setD0;
        W7.w wVar;
        A7.m.f(c2061f, "name");
        boolean zIsAnnotation = this.o.f9891a.isAnnotation();
        N0.q qVar = this.f13635b;
        if (zIsAnnotation && (wVar = (W7.w) AbstractC1998m.s0(((InterfaceC1116c) this.f13636e.a()).a(c2061f))) != null) {
            C1012g c1012gK1 = C1012g.k1(this.f13605n, l9.l.Y(qVar, wVar), com.bumptech.glide.c.D0(wVar.e()), false, wVar.c(), ((C1036a) qVar.f4399m).f12418j.b(wVar), false);
            I iF = r8.m.f(c1012gK1, R7.g.f7535a);
            c1012gK1.g1(iF, null, null, null);
            A7.m.f(qVar, "<this>");
            AbstractC0287w abstractC0287wL = z.l(wVar, new N0.q((C1036a) qVar.f4399m, new W2.j(qVar, c1012gK1, wVar, 0), qVar.o));
            n7.u uVar = n7.u.l;
            c1012gK1.j1(abstractC0287wL, uVar, p(), null, uVar);
            iF.f8810y = abstractC0287wL;
            arrayList.add(c1012gK1);
        }
        Set setL = L(c2061f);
        if (setL.isEmpty()) {
            return;
        }
        O8.i iVar = new O8.i();
        AbstractCollection iVar2 = new O8.i();
        A(setL, arrayList, iVar, new C1124k(this, 0));
        if (iVar.isEmpty()) {
            setD0 = AbstractC1998m.D0(setL);
        } else if (iVar instanceof Set) {
            Set linkedHashSet = new LinkedHashSet();
            for (Object obj : setL) {
                if (!iVar.contains(obj)) {
                    linkedHashSet.add(obj);
                }
            }
            setD0 = linkedHashSet;
        } else {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(setL);
            linkedHashSet2.removeAll(iVar);
            setD0 = linkedHashSet2;
        }
        A(setD0, iVar2, null, new C1124k(this, 1));
        LinkedHashSet linkedHashSetQ = AbstractC1985B.q(setL, iVar2);
        C1036a c1036a = (C1036a) qVar.f4399m;
        arrayList.addAll(l9.d.c0(c2061f, linkedHashSetQ, arrayList, this.f13605n, c1036a.f12414f, c1036a.f12427u.d));
    }

    @Override // d8.z
    public final Set o(y8.f fVar) {
        A7.m.f(fVar, "kindFilter");
        if (this.o.f9891a.isAnnotation()) {
            return e();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(((InterfaceC1116c) this.f13636e.a()).e());
        Collection collectionO = this.f13605n.C().o();
        A7.m.e(collectionO, "ownerDescriptor.typeConstructor.supertypes");
        Iterator it = collectionO.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(linkedHashSet, ((AbstractC0287w) it.next()).z0().f());
        }
        return linkedHashSet;
    }

    @Override // d8.z
    public final T7.u p() {
        InterfaceC0652e interfaceC0652e = this.f13605n;
        if (interfaceC0652e != null) {
            int i7 = r8.d.f21880a;
            return interfaceC0652e.J0();
        }
        r8.d.a(0);
        throw null;
    }

    @Override // d8.z
    public final InterfaceC0657j q() {
        return this.f13605n;
    }

    @Override // d8.z
    public final boolean r(C1011f c1011f) {
        if (this.o.f9891a.isAnnotation()) {
            return false;
        }
        return N(c1011f);
    }

    @Override // d8.z
    public final C1136w s(W7.w wVar, ArrayList arrayList, AbstractC0287w abstractC0287w, List list) {
        A7.m.f(wVar, "method");
        ((C1036a) this.f13635b.f4399m).f12413e.getClass();
        if (this.f13605n != null) {
            List listEmptyList = Collections.emptyList();
            if (listEmptyList != null) {
                return new C1136w(abstractC0287w, list, arrayList, listEmptyList);
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signatureErrors", "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature", "<init>"));
        }
        Object[] objArr = new Object[3];
        switch (1) {
            case 1:
                objArr[0] = "owner";
                break;
            case 2:
                objArr[0] = "returnType";
                break;
            case 3:
                objArr[0] = "valueParameters";
                break;
            case 4:
                objArr[0] = "typeParameters";
                break;
            case 5:
                objArr[0] = "descriptor";
                break;
            case 6:
                objArr[0] = "signatureErrors";
                break;
            default:
                objArr[0] = "method";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$1";
        objArr[2] = "resolvePropagatedSignature";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // d8.z
    public final String toString() {
        return "Lazy Java member scope for " + this.o.c();
    }

    public final void x(ArrayList arrayList, C1007b c1007b, int i7, W7.w wVar, AbstractC0287w abstractC0287w, AbstractC0287w abstractC0287w2) {
        InterfaceC1265a sVar;
        C1127n c1127n;
        a0 a0VarG;
        R7.f fVar = R7.g.f7535a;
        C2061f c2061fC = wVar.c();
        if (abstractC0287w == null) {
            Y.a(2);
            throw null;
        }
        a0 a0VarG2 = Y.g(abstractC0287w, false);
        Object defaultValue = wVar.f9899a.getDefaultValue();
        if (defaultValue != null) {
            Class<?> cls = defaultValue.getClass();
            List list = AbstractC0839c.f9878a;
            sVar = Enum.class.isAssignableFrom(cls) ? new W7.s(null, (Enum) defaultValue) : defaultValue instanceof Annotation ? new W7.f(null, (Annotation) defaultValue) : defaultValue instanceof Object[] ? new W7.g(null, (Object[]) defaultValue) : defaultValue instanceof Class ? new W7.o(null, (Class) defaultValue) : new W7.u(null, defaultValue);
        } else {
            sVar = null;
        }
        boolean z6 = sVar != null;
        if (abstractC0287w2 != null) {
            a0VarG = Y.g(abstractC0287w2, false);
            c1127n = this;
        } else {
            c1127n = this;
            a0VarG = null;
        }
        arrayList.add(new Q(c1007b, null, i7, fVar, c2061fC, a0VarG2, z6, false, false, a0VarG, ((C1036a) c1127n.f13635b.f4399m).f12418j.b(wVar)));
    }

    public final void y(LinkedHashSet linkedHashSet, C2061f c2061f, ArrayList arrayList, boolean z6) {
        C1036a c1036a = (C1036a) this.f13635b.f4399m;
        LinkedHashSet<K> linkedHashSetC0 = l9.d.c0(c2061f, arrayList, linkedHashSet, this.f13605n, c1036a.f12414f, c1036a.f12427u.d);
        if (!z6) {
            linkedHashSet.addAll(linkedHashSetC0);
            return;
        }
        ArrayList arrayListN0 = AbstractC1998m.n0(linkedHashSet, linkedHashSetC0);
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(linkedHashSetC0, 10));
        for (K kC : linkedHashSetC0) {
            K k10 = (K) P8.n.z(kC);
            if (k10 != null) {
                kC = C(kC, k10, arrayListN0);
            }
            arrayList2.add(kC);
        }
        linkedHashSet.addAll(arrayList2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z(o8.C2061f r9, java.util.LinkedHashSet r10, java.util.LinkedHashSet r11, java.util.AbstractSet r12, z7.InterfaceC2782k r13) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d8.C1127n.z(o8.f, java.util.LinkedHashSet, java.util.LinkedHashSet, java.util.AbstractSet, z7.k):void");
    }
}
