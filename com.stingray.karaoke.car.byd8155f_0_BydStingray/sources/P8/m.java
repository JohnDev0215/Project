package P8;

import A7.F;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import n7.u;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class m extends n {
    public static k X(Iterator it) {
        A7.m.f(it, "<this>");
        return Y(new o(it, 0));
    }

    public static k Y(k kVar) {
        return kVar instanceof a ? kVar : new a(kVar);
    }

    public static int Z(k kVar) {
        Iterator it = kVar.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            it.next();
            i7++;
            if (i7 < 0) {
                throw new ArithmeticException("Count overflow has happened.");
            }
        }
        return i7;
    }

    public static k a0(k kVar, int i7) {
        if (i7 >= 0) {
            return i7 == 0 ? kVar : kVar instanceof d ? ((d) kVar).a(i7) : new c(kVar, i7);
        }
        throw new IllegalArgumentException(A7.k.g("Requested element count ", i7, " is less than zero.").toString());
    }

    public static Object b0(g gVar) {
        f fVar = new f(gVar);
        if (fVar.hasNext()) {
            return fVar.next();
        }
        return null;
    }

    public static final h c0(k kVar) {
        A3.c cVar = new A3.c(16);
        if (!(kVar instanceof r)) {
            return new h(kVar, new A3.c(17), cVar);
        }
        r rVar = (r) kVar;
        return new h(rVar.f5484a, rVar.f5485b, cVar);
    }

    public static k d0(Object obj, InterfaceC2782k interfaceC2782k) {
        A7.m.f(interfaceC2782k, "nextFunction");
        return obj == null ? e.f5463a : new j(new A3.g(obj, 3), interfaceC2782k);
    }

    public static k e0(InterfaceC2772a interfaceC2772a) {
        return Y(new j(interfaceC2772a, new F(interfaceC2772a)));
    }

    public static Object f0(k kVar) {
        Iterator it = kVar.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Sequence is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static r g0(k kVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(interfaceC2782k, "transform");
        return new r(kVar, interfaceC2782k);
    }

    public static g h0(k kVar, InterfaceC2782k interfaceC2782k) {
        return new g(new r(kVar, interfaceC2782k), false, new A3.c(18));
    }

    public static List i0(k kVar) {
        Iterator it = kVar.iterator();
        if (!it.hasNext()) {
            return u.l;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return AbstractC1387d.j(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
