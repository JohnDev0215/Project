package G8;

import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.C0273h;
import F8.C0286v;
import F8.H;
import F8.K;
import F8.a0;
import io.sentry.C1489n1;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import n7.u;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f2743a = new t();

    public static ArrayList a(AbstractCollection abstractCollection, InterfaceC2785n interfaceC2785n) {
        ArrayList arrayList = new ArrayList(abstractCollection);
        Iterator it = arrayList.iterator();
        A7.m.e(it, "filteredTypes.iterator()");
        while (it.hasNext()) {
            A a10 = (A) it.next();
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    A a11 = (A) it2.next();
                    if (a11 != a10) {
                        A7.m.e(a11, "lower");
                        A7.m.e(a10, "upper");
                        if (((Boolean) interfaceC2785n.invoke(a11, a10)).booleanValue()) {
                            it.remove();
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v8, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [F8.H] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9, types: [F8.H, L8.d, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v19, types: [F8.A] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [F8.A, F8.w, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r8v2 */
    public final A b(ArrayList arrayList) {
        A a10;
        A aB;
        arrayList.size();
        ArrayList<A> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            A a11 = (A) it.next();
            if (a11.B0() instanceof C0286v) {
                Collection collectionO = a11.B0().o();
                A7.m.e(collectionO, "type.constructor.supertypes");
                Collection<AbstractC0287w> collection = collectionO;
                ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(collection, 10));
                for (AbstractC0287w abstractC0287w : collection) {
                    A7.m.e(abstractC0287w, "it");
                    A aY = AbstractC0268c.y(abstractC0287w);
                    if (a11.F0()) {
                        aY = aY.L0(true);
                    }
                    arrayList3.add(aY);
                }
                arrayList2.addAll(arrayList3);
            } else {
                arrayList2.add(a11);
            }
        }
        r rVarA = r.l;
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            rVarA = rVarA.a((a0) it2.next());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (A aL0 : arrayList2) {
            if (rVarA == r.o) {
                if (aL0 instanceof h) {
                    h hVar = (h) aL0;
                    A7.m.f(hVar, "<this>");
                    aL0 = new h(hVar.f2726m, hVar.f2727n, hVar.o, hVar.f2728p, hVar.f2729q, true);
                }
                A7.m.f(aL0, "<this>");
                A aP = C0269d.p(aL0, false);
                aL0 = (aP == null && (aP = AbstractC0268c.m(aL0)) == null) ? aL0.L0(false) : aP;
            }
            linkedHashSet.add(aL0);
        }
        ArrayList arrayList4 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((A) it3.next()).t0());
        }
        Iterator it4 = arrayList4.iterator();
        if (!it4.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        ?? next = it4.next();
        while (true) {
            a10 = null;
            if (!it4.hasNext()) {
                break;
            }
            H h10 = (H) it4.next();
            next = (H) next;
            next.getClass();
            A7.m.f(h10, "other");
            if (!next.isEmpty() || !h10.isEmpty()) {
                ArrayList arrayList5 = new ArrayList();
                Collection collectionValues = ((ConcurrentHashMap) H.f2437m.f16624m).values();
                A7.m.e(collectionValues, "idPerType.values");
                Iterator it5 = collectionValues.iterator();
                while (it5.hasNext()) {
                    int iIntValue = ((Number) it5.next()).intValue();
                    C0273h c0273h = (C0273h) next.l.get(iIntValue);
                    C0273h c0273h2 = (C0273h) h10.l.get(iIntValue);
                    if (c0273h != null) {
                        if (!A7.m.a(c0273h2, c0273h)) {
                            c0273h = null;
                        }
                        c0273h2 = c0273h;
                    } else if (c0273h2 == null || !A7.m.a(c0273h, c0273h2)) {
                        c0273h2 = null;
                    }
                    O8.l.a(arrayList5, c0273h2);
                }
                next = C1489n1.k(arrayList5);
            }
        }
        H h11 = (H) next;
        if (linkedHashSet.size() == 1) {
            aB = (A) AbstractC1998m.q0(linkedHashSet);
        } else {
            int i7 = 2;
            ArrayList arrayListA = a(linkedHashSet, new s(i7, 0, this));
            arrayListA.isEmpty();
            if (!arrayListA.isEmpty()) {
                Iterator it6 = arrayListA.iterator();
                if (!it6.hasNext()) {
                    throw new UnsupportedOperationException("Empty collection can't be reduced.");
                }
                ?? next2 = it6.next();
                while (it6.hasNext()) {
                    A a12 = (A) it6.next();
                    next2 = (A) next2;
                    if (next2 != 0 && a12 != null) {
                        K kB0 = next2.B0();
                        K kB02 = a12.B0();
                        boolean z6 = kB0 instanceof t8.m;
                        if (z6 && (kB02 instanceof t8.m)) {
                            Set set = ((t8.m) kB0).f22504a;
                            Set set2 = ((t8.m) kB02).f22504a;
                            A7.m.f(set, "<this>");
                            A7.m.f(set2, "other");
                            Set setC0 = AbstractC1998m.C0(set);
                            AbstractC2004s.L(setC0, set2);
                            t8.m mVar = new t8.m(setC0);
                            H.f2437m.getClass();
                            H h12 = H.f2438n;
                            A7.m.f(h12, "attributes");
                            next2 = C0269d.s(h12, mVar, u.l, H8.i.a(2, true, "unknown integer literal type"), false);
                        } else if (z6) {
                            if (!((t8.m) kB0).f22504a.contains(a12)) {
                                a12 = null;
                            }
                            next2 = a12;
                        } else if (!(kB02 instanceof t8.m) || !((t8.m) kB02).f22504a.contains(next2)) {
                        }
                    }
                    next2 = 0;
                }
                a10 = (A) next2;
            }
            if (a10 != null) {
                aB = a10;
            } else {
                k.f2736b.getClass();
                ArrayList arrayListA2 = a(arrayListA, new s(i7, 1, j.f2735b));
                arrayListA2.isEmpty();
                aB = arrayListA2.size() < 2 ? (A) AbstractC1998m.q0(arrayListA2) : new C0286v(linkedHashSet).b();
            }
        }
        return aB.N0(h11);
    }
}
