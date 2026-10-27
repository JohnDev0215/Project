package y8;

import D8.v;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import n7.u;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class i extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f24206b;

    public i(n nVar) {
        A7.m.f(nVar, "workerScope");
        this.f24206b = nVar;
    }

    @Override // y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        InterfaceC0654g interfaceC0654gC = this.f24206b.c(c2061f, bVar);
        if (interfaceC0654gC == null) {
            return null;
        }
        InterfaceC0652e interfaceC0652e = interfaceC0654gC instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gC : null;
        if (interfaceC0652e != null) {
            return interfaceC0652e;
        }
        if (interfaceC0654gC instanceof v) {
            return (v) interfaceC0654gC;
        }
        return null;
    }

    @Override // y8.o, y8.p
    public final Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        int i7 = f.l & fVar.f24203b;
        f fVar2 = i7 == 0 ? null : new f(i7, fVar.f24202a);
        if (fVar2 == null) {
            return u.l;
        }
        Collection collectionD = this.f24206b.d(fVar2, interfaceC2782k);
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionD) {
            if (obj instanceof InterfaceC0655h) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // y8.o, y8.n
    public final Set e() {
        return this.f24206b.e();
    }

    @Override // y8.o, y8.n
    public final Set f() {
        return this.f24206b.f();
    }

    @Override // y8.o, y8.n
    public final Set g() {
        return this.f24206b.g();
    }

    public final String toString() {
        return "Classes from " + this.f24206b;
    }
}
