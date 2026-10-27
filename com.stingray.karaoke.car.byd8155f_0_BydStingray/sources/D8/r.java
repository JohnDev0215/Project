package D8;

import A.I0;
import A7.A;
import A7.B;
import Q7.InterfaceC0654g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import n7.AbstractC2003r;
import o8.C2057b;
import o8.C2061f;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class r extends y8.o {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f1266f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B8.k f1267b;
    public final p c;
    public final E8.i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E8.h f1268e;

    static {
        B b10 = A.f443a;
        f1266f = new H7.w[]{b10.h(new A7.s(b10.b(r.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;")), b10.h(new A7.s(b10.b(r.class), "classifierNamesLazy", "getClassifierNamesLazy()Ljava/util/Set;"))};
    }

    public r(B8.k kVar, List list, List list2, List list3, InterfaceC2772a interfaceC2772a) {
        A7.m.f(kVar, "c");
        A7.m.f(list, "functionList");
        A7.m.f(list2, "propertyList");
        A7.m.f(list3, "typeAliasList");
        this.f1267b = kVar;
        B8.i iVar = (B8.i) kVar.f779a;
        iVar.c.getClass();
        this.c = new p(this, list, list2, list3);
        q qVar = new q(interfaceC2772a, 0);
        E8.o oVar = iVar.f762a;
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        this.d = new E8.i(lVar, qVar);
        I0 i02 = new I0(this, 6);
        E8.l lVar2 = (E8.l) oVar;
        lVar2.getClass();
        this.f1268e = new E8.h(lVar2, i02);
    }

    @Override // y8.o, y8.n
    public Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return this.c.a(c2061f, bVar);
    }

    @Override // y8.o, y8.n
    public Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return this.c.b(c2061f, bVar);
    }

    @Override // y8.o, y8.p
    public InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        if (q(c2061f)) {
            return ((B8.i) this.f1267b.f779a).b(l(c2061f));
        }
        p pVar = this.c;
        if (!pVar.c.keySet().contains(c2061f)) {
            return null;
        }
        pVar.getClass();
        return (v) pVar.f1260f.b(c2061f);
    }

    @Override // y8.o, y8.n
    public final Set e() {
        return (Set) C7.a.N(this.c.f1261g, p.f1256j[0]);
    }

    @Override // y8.o, y8.n
    public final Set f() {
        return (Set) C7.a.N(this.c.f1262h, p.f1256j[1]);
    }

    @Override // y8.o, y8.n
    public final Set g() {
        E8.h hVar = this.f1268e;
        H7.w wVar = f1266f[1];
        A7.m.f(hVar, "<this>");
        A7.m.f(wVar, "p");
        return (Set) hVar.a();
    }

    public abstract void h(ArrayList arrayList, InterfaceC2782k interfaceC2782k);

    public final List i(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        Y7.b bVar = Y7.b.o;
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        ArrayList arrayList = new ArrayList(0);
        if (fVar.a(y8.f.f24190f)) {
            h(arrayList, interfaceC2782k);
        }
        p pVar = this.c;
        pVar.getClass();
        boolean zA = fVar.a(y8.f.f24194j);
        r8.i iVar = r8.i.f21885m;
        if (zA) {
            Set<C2061f> set = (Set) C7.a.N(pVar.f1262h, p.f1256j[1]);
            ArrayList arrayList2 = new ArrayList();
            for (C2061f c2061f : set) {
                if (((Boolean) interfaceC2782k.b(c2061f)).booleanValue()) {
                    arrayList2.addAll(pVar.b(c2061f, bVar));
                }
            }
            AbstractC2003r.K(arrayList2, iVar);
            arrayList.addAll(arrayList2);
        }
        if (fVar.a(y8.f.f24193i)) {
            Set<C2061f> set2 = (Set) C7.a.N(pVar.f1261g, p.f1256j[0]);
            ArrayList arrayList3 = new ArrayList();
            for (C2061f c2061f2 : set2) {
                if (((Boolean) interfaceC2782k.b(c2061f2)).booleanValue()) {
                    arrayList3.addAll(pVar.a(c2061f2, bVar));
                }
            }
            AbstractC2003r.K(arrayList3, iVar);
            arrayList.addAll(arrayList3);
        }
        if (fVar.a(y8.f.l)) {
            for (C2061f c2061f3 : m()) {
                if (((Boolean) interfaceC2782k.b(c2061f3)).booleanValue()) {
                    O8.l.a(arrayList, ((B8.i) this.f1267b.f779a).b(l(c2061f3)));
                }
            }
        }
        if (fVar.a(y8.f.f24191g)) {
            for (Object obj : pVar.c.keySet()) {
                if (((Boolean) interfaceC2782k.b(obj)).booleanValue()) {
                    pVar.getClass();
                    A7.m.f(obj, "name");
                    O8.l.a(arrayList, (v) pVar.f1260f.b(obj));
                }
            }
        }
        return O8.l.d(arrayList);
    }

    public void j(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
    }

    public void k(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
    }

    public abstract C2057b l(C2061f c2061f);

    public final Set m() {
        return (Set) C7.a.N(this.d, f1266f[0]);
    }

    public abstract Set n();

    public abstract Set o();

    public abstract Set p();

    public boolean q(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        return m().contains(c2061f);
    }

    public boolean r(u uVar) {
        return true;
    }
}
