package y8;

import Q7.InterfaceC0654g;
import T7.K;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import n7.u;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class o implements n {
    @Override // y8.n
    public Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return u.l;
    }

    @Override // y8.n
    public Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return u.l;
    }

    @Override // y8.p
    public InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        return null;
    }

    @Override // y8.p
    public Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        return u.l;
    }

    @Override // y8.n
    public Set e() {
        Collection collectionD = d(f.f24198p, O8.c.f5188m);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : collectionD) {
            if (obj instanceof K) {
                C2061f name = ((K) obj).getName();
                A7.m.e(name, "it.name");
                linkedHashSet.add(name);
            }
        }
        return linkedHashSet;
    }

    @Override // y8.n
    public Set f() {
        Collection collectionD = d(f.f24199q, O8.c.f5188m);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : collectionD) {
            if (obj instanceof K) {
                C2061f name = ((K) obj).getName();
                A7.m.e(name, "it.name");
                linkedHashSet.add(name);
            }
        }
        return linkedHashSet;
    }

    @Override // y8.n
    public Set g() {
        return null;
    }
}
