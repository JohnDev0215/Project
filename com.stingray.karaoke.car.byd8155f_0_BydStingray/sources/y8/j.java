package y8;

import Q7.InterfaceC0649b;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import e0.C1183w;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import n7.AbstractC1998m;
import o8.C2061f;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class j implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f24207b = 0;
    public final Object c;

    public j(E8.o oVar, InterfaceC2772a interfaceC2772a) {
        A7.m.f(oVar, "storageManager");
        this.c = new E8.i((E8.l) oVar, new C1183w(interfaceC2772a, 20));
    }

    @Override // y8.n
    public Collection a(C2061f c2061f, Y7.b bVar) {
        switch (this.f24207b) {
            case 1:
                A7.m.f(c2061f, "name");
                return r8.m.o(j(c2061f, bVar), k.f24209p);
            default:
                return j(c2061f, bVar);
        }
    }

    @Override // y8.n
    public Collection b(C2061f c2061f, Y7.b bVar) {
        switch (this.f24207b) {
            case 1:
                A7.m.f(c2061f, "name");
                return r8.m.o(k(c2061f, bVar), k.f24210q);
            default:
                return k(c2061f, bVar);
        }
    }

    @Override // y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        return l().c(c2061f, bVar);
    }

    @Override // y8.p
    public Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        switch (this.f24207b) {
            case 1:
                A7.m.f(fVar, "kindFilter");
                A7.m.f(interfaceC2782k, "nameFilter");
                Collection collectionI = i(fVar, interfaceC2782k);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : collectionI) {
                    if (((InterfaceC0657j) obj) instanceof InterfaceC0649b) {
                        arrayList.add(obj);
                    } else {
                        arrayList2.add(obj);
                    }
                }
                return AbstractC1998m.n0(r8.m.o(arrayList, k.o), arrayList2);
            default:
                return i(fVar, interfaceC2782k);
        }
    }

    @Override // y8.n
    public final Set e() {
        return l().e();
    }

    @Override // y8.n
    public final Set f() {
        return l().f();
    }

    @Override // y8.n
    public final Set g() {
        return l().g();
    }

    public final n h() {
        if (!(l() instanceof j)) {
            return l();
        }
        n nVarL = l();
        A7.m.d(nVarL, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.AbstractScopeAdapter");
        return ((j) nVarL).h();
    }

    public final Collection i(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        return l().d(fVar, interfaceC2782k);
    }

    public final Collection j(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return l().a(c2061f, bVar);
    }

    public final Collection k(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return l().b(c2061f, bVar);
    }

    public final n l() {
        switch (this.f24207b) {
            case 0:
                return (n) ((E8.i) this.c).a();
            default:
                return (n) this.c;
        }
    }

    public j(n nVar) {
        this.c = nVar;
    }
}
