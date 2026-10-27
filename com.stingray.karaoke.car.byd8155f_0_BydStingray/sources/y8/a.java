package y8;

import H7.H;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import n7.AbstractC1996k;
import n7.AbstractC2004s;
import n7.u;
import n7.w;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class a implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f24183b;
    public final n[] c;

    public a(String str, n[] nVarArr) {
        this.f24183b = str;
        this.c = nVarArr;
    }

    @Override // y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        n[] nVarArr = this.c;
        int length = nVarArr.length;
        if (length == 0) {
            return u.l;
        }
        if (length == 1) {
            return nVarArr[0].a(c2061f, bVar);
        }
        Collection collectionS = null;
        for (n nVar : nVarArr) {
            collectionS = H.s(collectionS, nVar.a(c2061f, bVar));
        }
        return collectionS == null ? w.l : collectionS;
    }

    @Override // y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        n[] nVarArr = this.c;
        int length = nVarArr.length;
        if (length == 0) {
            return u.l;
        }
        if (length == 1) {
            return nVarArr[0].b(c2061f, bVar);
        }
        Collection collectionS = null;
        for (n nVar : nVarArr) {
            collectionS = H.s(collectionS, nVar.b(c2061f, bVar));
        }
        return collectionS == null ? w.l : collectionS;
    }

    @Override // y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        InterfaceC0654g interfaceC0654g = null;
        for (n nVar : this.c) {
            InterfaceC0654g interfaceC0654gC = nVar.c(c2061f, bVar);
            if (interfaceC0654gC != null) {
                if (!(interfaceC0654gC instanceof InterfaceC0655h) || !((InterfaceC0655h) interfaceC0654gC).c0()) {
                    return interfaceC0654gC;
                }
                if (interfaceC0654g == null) {
                    interfaceC0654g = interfaceC0654gC;
                }
            }
        }
        return interfaceC0654g;
    }

    @Override // y8.p
    public final Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        n[] nVarArr = this.c;
        int length = nVarArr.length;
        if (length == 0) {
            return u.l;
        }
        if (length == 1) {
            return nVarArr[0].d(fVar, interfaceC2782k);
        }
        Collection collectionS = null;
        for (n nVar : nVarArr) {
            collectionS = H.s(collectionS, nVar.d(fVar, interfaceC2782k));
        }
        return collectionS == null ? w.l : collectionS;
    }

    @Override // y8.n
    public final Set e() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (n nVar : this.c) {
            AbstractC2004s.L(linkedHashSet, nVar.e());
        }
        return linkedHashSet;
    }

    @Override // y8.n
    public final Set f() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (n nVar : this.c) {
            AbstractC2004s.L(linkedHashSet, nVar.f());
        }
        return linkedHashSet;
    }

    @Override // y8.n
    public final Set g() {
        return h6.e.j(AbstractC1996k.w(this.c));
    }

    public final String toString() {
        return this.f24183b;
    }
}
