package T7;

import F8.AbstractC0272g;
import F8.AbstractC0287w;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: T7.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0797p extends y8.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E8.e f8857b;
    public final E8.e c;
    public final E8.i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C0798q f8858e;

    public C0797p(C0798q c0798q, E8.o oVar) {
        if (oVar == null) {
            h(0);
            throw null;
        }
        this.f8858e = c0798q;
        E8.l lVar = (E8.l) oVar;
        this.f8857b = lVar.b(new C0796o(this, 0));
        this.c = lVar.b(new C0796o(this, 1));
        this.d = new E8.i(lVar, new P5.w(this, 2));
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void h(int r13) {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T7.C0797p.h(int):void");
    }

    @Override // y8.o, y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        if (c2061f != null) {
            return (Collection) this.f8857b.b(c2061f);
        }
        h(5);
        throw null;
    }

    @Override // y8.o, y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        if (c2061f != null) {
            return (Collection) this.c.b(c2061f);
        }
        h(1);
        throw null;
    }

    @Override // y8.o, y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        if (fVar == null) {
            h(13);
            throw null;
        }
        if (interfaceC2782k == null) {
            h(14);
            throw null;
        }
        Collection collection = (Collection) this.d.a();
        if (collection != null) {
            return collection;
        }
        h(15);
        throw null;
    }

    @Override // y8.o, y8.n
    public final Set e() {
        Set set = (Set) this.f8858e.f8861t.a();
        if (set != null) {
            return set;
        }
        h(17);
        throw null;
    }

    @Override // y8.o, y8.n
    public final Set f() {
        Set set = (Set) this.f8858e.f8861t.a();
        if (set != null) {
            return set;
        }
        h(19);
        throw null;
    }

    @Override // y8.o, y8.n
    public final Set g() {
        Set setEmptySet = Collections.emptySet();
        if (setEmptySet != null) {
            return setEmptySet;
        }
        h(18);
        throw null;
    }

    public final y8.n i() {
        y8.n nVarZ0 = ((AbstractC0287w) ((AbstractC0272g) this.f8858e.C()).o().iterator().next()).z0();
        if (nVarZ0 != null) {
            return nVarZ0;
        }
        h(9);
        throw null;
    }

    public final LinkedHashSet j(C2061f c2061f, Collection collection) {
        if (c2061f == null) {
            h(10);
            throw null;
        }
        if (collection == null) {
            h(11);
            throw null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        r8.l.c.h(c2061f, collection, Collections.emptySet(), this.f8858e, new D8.f(linkedHashSet, 1));
        return linkedHashSet;
    }
}
