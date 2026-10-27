package F8;

import Q7.InterfaceC0652e;
import e8.C1236a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final H8.f f2457a = H8.i.c(H8.h.f2890w, new String[0]);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final H8.f f2458b = H8.i.c(H8.h.f2887t, new String[0]);
    public static final X c = new X("NO_EXPECTED_TYPE");
    public static final X d = new X("UNIT_EXPECTED_TYPE");

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(int r27) {
        /*
            Method dump skipped, instruction units count: 774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.Y.a(int):void");
    }

    public static boolean b(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(28);
            throw null;
        }
        if (abstractC0287w.F0()) {
            return true;
        }
        return AbstractC0268c.j(abstractC0287w) && b(((AbstractC0282q) abstractC0287w.K0()).f2478n);
    }

    public static boolean c(AbstractC0287w abstractC0287w, InterfaceC2782k interfaceC2782k, O8.i iVar) {
        if (abstractC0287w == null) {
            return false;
        }
        a0 a0VarK0 = abstractC0287w.K0();
        if (l(abstractC0287w)) {
            return ((Boolean) interfaceC2782k.b(a0VarK0)).booleanValue();
        }
        if (iVar != null && iVar.contains(abstractC0287w)) {
            return false;
        }
        if (((Boolean) interfaceC2782k.b(a0VarK0)).booleanValue()) {
            return true;
        }
        if (iVar == null) {
            iVar = new O8.i();
        }
        iVar.add(abstractC0287w);
        AbstractC0282q abstractC0282q = a0VarK0 instanceof AbstractC0282q ? (AbstractC0282q) a0VarK0 : null;
        if (abstractC0282q != null && (c(abstractC0282q.f2477m, interfaceC2782k, iVar) || c(abstractC0282q.f2478n, interfaceC2782k, iVar))) {
            return true;
        }
        if ((a0VarK0 instanceof C0278m) && c(((C0278m) a0VarK0).f2473m, interfaceC2782k, iVar)) {
            return true;
        }
        K kB0 = abstractC0287w.B0();
        if (kB0 instanceof C0286v) {
            Iterator it = ((C0286v) kB0).f2484b.iterator();
            while (it.hasNext()) {
                if (c((AbstractC0287w) it.next(), interfaceC2782k, iVar)) {
                    return true;
                }
            }
            return false;
        }
        for (O o : abstractC0287w.o0()) {
            if (!o.c() && c(o.b(), interfaceC2782k, iVar)) {
                return true;
            }
        }
        return false;
    }

    public static List d(List list) {
        if (list == null) {
            a(16);
            throw null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new P(((Q7.Q) it.next()).j()));
        }
        return AbstractC1998m.z0(arrayList);
    }

    public static boolean e(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(27);
            throw null;
        }
        if (abstractC0287w.F0()) {
            return true;
        }
        if (AbstractC0268c.j(abstractC0287w) && e(((AbstractC0282q) abstractC0287w.K0()).f2478n)) {
            return true;
        }
        if (abstractC0287w.K0() instanceof C0278m) {
            return false;
        }
        if (!f(abstractC0287w)) {
            K kB0 = abstractC0287w.B0();
            if (kB0 instanceof C0286v) {
                Iterator it = ((C0286v) kB0).f2484b.iterator();
                while (it.hasNext()) {
                    if (e((AbstractC0287w) it.next())) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (!(abstractC0287w.B0().n() instanceof InterfaceC0652e)) {
            W wD = W.d(abstractC0287w);
            Collection<AbstractC0287w> collectionO = abstractC0287w.B0().o();
            ArrayList arrayList = new ArrayList(collectionO.size());
            for (AbstractC0287w abstractC0287w2 : collectionO) {
                if (abstractC0287w2 == null) {
                    a(21);
                    throw null;
                }
                AbstractC0287w abstractC0287wI = wD.i(1, abstractC0287w2);
                AbstractC0287w abstractC0287wH = abstractC0287wI != null ? h(abstractC0287wI, abstractC0287w.F0()) : null;
                if (abstractC0287wH != null) {
                    arrayList.add(abstractC0287wH);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (e((AbstractC0287w) it2.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean f(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(60);
            throw null;
        }
        if ((abstractC0287w.B0().n() instanceof Q7.Q ? (Q7.Q) abstractC0287w.B0().n() : null) != null) {
            return true;
        }
        abstractC0287w.B0();
        return false;
    }

    public static a0 g(AbstractC0287w abstractC0287w, boolean z6) {
        if (abstractC0287w == null) {
            a(3);
            throw null;
        }
        a0 a0VarL0 = abstractC0287w.K0().L0(z6);
        if (a0VarL0 != null) {
            return a0VarL0;
        }
        a(4);
        throw null;
    }

    public static AbstractC0287w h(AbstractC0287w abstractC0287w, boolean z6) {
        if (abstractC0287w != null) {
            return z6 ? g(abstractC0287w, true) : abstractC0287w;
        }
        a(8);
        throw null;
    }

    public static A i(A a10, boolean z6) {
        if (a10 == null) {
            a(5);
            throw null;
        }
        if (!z6) {
            return a10;
        }
        A aL0 = a10.L0(true);
        if (aL0 != null) {
            return aL0;
        }
        a(6);
        throw null;
    }

    public static F j(Q7.Q q4) {
        if (q4 != null) {
            return new F(q4);
        }
        a(45);
        throw null;
    }

    public static O k(Q7.Q q4, C1236a c1236a) {
        if (q4 != null) {
            return c1236a.f14483a == 1 ? new P(1, AbstractC0268c.r(q4)) : new F(q4);
        }
        a(46);
        throw null;
    }

    public static boolean l(AbstractC0287w abstractC0287w) {
        if (abstractC0287w != null) {
            return abstractC0287w == c || abstractC0287w == d;
        }
        a(0);
        throw null;
    }
}
