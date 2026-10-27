package N7;

import F8.C0275j;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0666t;
import T7.A;
import T7.C0790i;
import T7.O;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final A f4879a;

    static {
        H8.i iVar = H8.i.f2895a;
        P7.k kVar = new P7.k(H8.i.f2896b, o.f4870e, 1);
        C2061f c2061fF = o.f4871f.f();
        E8.b bVar = E8.l.f1859e;
        A a10 = new A(kVar, c2061fF, bVar);
        a10.f8761s = 4;
        C0661n c0661n = AbstractC0662o.f7191e;
        if (c0661n == null) {
            A.W(9);
            throw null;
        }
        a10.f8762t = c0661n;
        List listJ = AbstractC1387d.j(O.f1(a10, 2, C2061f.e("T"), 0, bVar));
        if (a10.f8764v != null) {
            throw new IllegalStateException("Type parameters are already set for " + a10.getName());
        }
        ArrayList arrayList = new ArrayList(listJ);
        a10.f8764v = arrayList;
        a10.f8763u = new C0275j(a10, arrayList, a10.f8765w, a10.f8766x);
        Set setEmptySet = Collections.emptySet();
        if (setEmptySet == null) {
            A.W(13);
            throw null;
        }
        Iterator it = setEmptySet.iterator();
        while (it.hasNext()) {
            ((C0790i) ((InterfaceC0666t) it.next())).f8900s = a10.j();
        }
        f4879a = a10;
    }
}
