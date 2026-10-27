package T7;

import Q7.InterfaceC0671y;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import o8.C2058c;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class L extends y8.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0671y f8814b;
    public final C2058c c;

    public L(InterfaceC0671y interfaceC0671y, C2058c c2058c) {
        A7.m.f(interfaceC0671y, "moduleDescriptor");
        A7.m.f(c2058c, "fqName");
        this.f8814b = interfaceC0671y;
        this.c = c2058c;
    }

    @Override // y8.o, y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        boolean zA = fVar.a(y8.f.f24192h);
        n7.u uVar = n7.u.l;
        if (!zA) {
            return uVar;
        }
        C2058c c2058c = this.c;
        if (c2058c.d()) {
            if (fVar.f24202a.contains(y8.c.f24186a)) {
                return uVar;
            }
        }
        InterfaceC0671y interfaceC0671y = this.f8814b;
        Collection collectionR = interfaceC0671y.r(c2058c, interfaceC2782k);
        ArrayList arrayList = new ArrayList(collectionR.size());
        Iterator it = collectionR.iterator();
        while (it.hasNext()) {
            C2061f c2061fF = ((C2058c) it.next()).f();
            A7.m.e(c2061fF, "subFqName.shortName()");
            if (((Boolean) interfaceC2782k.b(c2061fF)).booleanValue()) {
                w wVar = null;
                if (!c2061fF.f20573m) {
                    w wVar2 = (w) interfaceC0671y.n0(c2058c.c(c2061fF));
                    if (!((Boolean) C7.a.N(wVar2.f8915r, w.f8912t[1])).booleanValue()) {
                        wVar = wVar2;
                    }
                }
                O8.l.a(arrayList, wVar);
            }
        }
        return arrayList;
    }

    @Override // y8.o, y8.n
    public final Set g() {
        return n7.w.l;
    }

    public final String toString() {
        return "subpackages of " + this.c + " from " + this.f8814b;
    }
}
