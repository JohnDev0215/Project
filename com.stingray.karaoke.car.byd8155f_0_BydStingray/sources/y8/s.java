package y8;

import F8.T;
import F8.W;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.P;
import e0.C1183w;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class s implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f24219b;
    public final W c;
    public HashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m7.n f24220e;

    public s(n nVar, W w10) {
        A7.m.f(nVar, "workerScope");
        A7.m.f(w10, "givenSubstitutor");
        this.f24219b = nVar;
        h6.r.o(new C1183w(w10, 22));
        T tF = w10.f();
        A7.m.e(tF, "givenSubstitutor.substitution");
        this.c = new W(h6.n.v(tF));
        this.f24220e = h6.r.o(new C1183w(this, 21));
    }

    @Override // y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return i(this.f24219b.a(c2061f, bVar));
    }

    @Override // y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return i(this.f24219b.b(c2061f, bVar));
    }

    @Override // y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        InterfaceC0654g interfaceC0654gC = this.f24219b.c(c2061f, bVar);
        if (interfaceC0654gC != null) {
            return (InterfaceC0654g) h(interfaceC0654gC);
        }
        return null;
    }

    @Override // y8.p
    public final Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        return (Collection) this.f24220e.getValue();
    }

    @Override // y8.n
    public final Set e() {
        return this.f24219b.e();
    }

    @Override // y8.n
    public final Set f() {
        return this.f24219b.f();
    }

    @Override // y8.n
    public final Set g() {
        return this.f24219b.g();
    }

    public final InterfaceC0657j h(InterfaceC0657j interfaceC0657j) {
        W w10 = this.c;
        if (w10.f2455a.e()) {
            return interfaceC0657j;
        }
        if (this.d == null) {
            this.d = new HashMap();
        }
        HashMap map = this.d;
        A7.m.c(map);
        Object objF = map.get(interfaceC0657j);
        if (objF == null) {
            if (!(interfaceC0657j instanceof P)) {
                throw new IllegalStateException(("Unknown descriptor in scope: " + interfaceC0657j).toString());
            }
            objF = ((P) interfaceC0657j).f(w10);
            if (objF == null) {
                throw new AssertionError("We expect that no conflict should happen while substitution is guaranteed to generate invariant projection, but " + interfaceC0657j + " substitution fails");
            }
            map.put(interfaceC0657j, objF);
        }
        return (InterfaceC0657j) objF;
    }

    public final Collection i(Collection collection) {
        if (this.c.f2455a.e() || collection.isEmpty()) {
            return collection;
        }
        int size = collection.size();
        LinkedHashSet linkedHashSet = new LinkedHashSet(size >= 3 ? (size / 3) + size + 1 : 3);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(h((InterfaceC0657j) it.next()));
        }
        return linkedHashSet;
    }
}
