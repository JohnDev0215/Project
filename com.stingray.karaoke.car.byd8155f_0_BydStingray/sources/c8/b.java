package C8;

import A7.m;
import B2.O;
import B8.i;
import B8.j;
import B8.l;
import B8.t;
import E8.o;
import Q7.E;
import Q7.G;
import Q7.InterfaceC0671y;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import n0.C1886o;
import n7.AbstractC2000o;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class b implements N7.c {
    public E a(o oVar, InterfaceC0671y interfaceC0671y, Iterable iterable, S7.d dVar, S7.b bVar, boolean z6) {
        m.f(oVar, "storageManager");
        m.f(interfaceC0671y, "builtInsModule");
        m.f(iterable, "classDescriptorFactories");
        m.f(dVar, "platformDependentDeclarationFilter");
        m.f(bVar, "additionalClassPartsProvider");
        Set set = N7.o.o;
        m.f(set, "packageFqNames");
        Set<C2058c> set2 = set;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(set2, 10));
        for (C2058c c2058c : set2) {
            a.f1016m.getClass();
            String strA = a.a(c2058c);
            m.f(strA, "p0");
            InputStream inputStreamP = d.p(strA);
            if (inputStreamP == null) {
                throw new IllegalStateException(M5.b.g("Resource not found in classpath: ", strA));
            }
            arrayList.add(com.bumptech.glide.d.u(c2058c, oVar, interfaceC0671y, inputStreamP));
        }
        G g7 = new G(arrayList);
        t tVar = new t(oVar, interfaceC0671y);
        O o = new O(g7, 3);
        a aVar = a.f1016m;
        i iVar = new i(oVar, interfaceC0671y, o, new io.sentry.internal.debugmeta.c(interfaceC0671y, tVar, aVar), g7, l.f786a, j.c, iterable, tVar, bVar, dVar, aVar.f465a, null, new C1886o(oVar), null, 851968);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((c) it.next()).d1(iVar);
        }
        return g7;
    }
}
