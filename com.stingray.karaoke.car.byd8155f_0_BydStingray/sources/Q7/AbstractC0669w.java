package Q7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import v8.AbstractC2564d;
import v8.C2563c;

/* JADX INFO: renamed from: Q7.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0669w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B2.q f7209a = new B2.q("InvalidModuleNotifier", 1);

    public static final l3.P a(F8.A a10, InterfaceC0655h interfaceC0655h, int i7) {
        if (interfaceC0655h == null || H8.i.f(interfaceC0655h)) {
            return null;
        }
        int size = interfaceC0655h.s().size() + i7;
        if (interfaceC0655h.d0()) {
            List listSubList = a10.o0().subList(i7, size);
            InterfaceC0657j interfaceC0657jO = interfaceC0655h.o();
            return new l3.P(interfaceC0655h, listSubList, a(a10, interfaceC0657jO instanceof InterfaceC0655h ? (InterfaceC0655h) interfaceC0657jO : null, size));
        }
        if (size != a10.o0().size()) {
            r8.d.o(interfaceC0655h);
        }
        return new l3.P(interfaceC0655h, a10.o0().subList(i7, a10.o0().size()), (l3.P) null);
    }

    public static final void b(E e10, C2058c c2058c, ArrayList arrayList) {
        A7.m.f(e10, "<this>");
        A7.m.f(c2058c, "fqName");
        if (e10 instanceof H) {
            ((H) e10).b(c2058c, arrayList);
        } else {
            arrayList.addAll(e10.c(c2058c));
        }
    }

    public static final List c(InterfaceC0655h interfaceC0655h) {
        List listP;
        Object next;
        F8.K kC;
        A7.m.f(interfaceC0655h, "<this>");
        List listS = interfaceC0655h.s();
        A7.m.e(listS, "declaredTypeParameters");
        if (!interfaceC0655h.d0() && !(interfaceC0655h.o() instanceof InterfaceC0649b)) {
            return listS;
        }
        int i7 = AbstractC2564d.f23040a;
        C2563c c2563c = C2563c.f23039m;
        List listI0 = P8.m.i0(new P8.h(new P8.g(new P8.j(P8.m.a0(P8.m.d0(interfaceC0655h, c2563c), 1), r.f7203p), true, r.f7204q), r.f7205r, P8.q.f5483t));
        Iterator it = P8.m.a0(P8.m.d0(interfaceC0655h, c2563c), 1).iterator();
        while (true) {
            listP = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next instanceof InterfaceC0652e) {
                break;
            }
        }
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) next;
        if (interfaceC0652e != null && (kC = interfaceC0652e.C()) != null) {
            listP = kC.p();
        }
        if (listP == null) {
            listP = n7.u.l;
        }
        if (listI0.isEmpty() && listP.isEmpty()) {
            List listS2 = interfaceC0655h.s();
            A7.m.e(listS2, "declaredTypeParameters");
            return listS2;
        }
        ArrayList<Q> arrayListN0 = AbstractC1998m.n0(listI0, listP);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(arrayListN0, 10));
        for (Q q4 : arrayListN0) {
            A7.m.e(q4, "it");
            arrayList.add(new C0651d(q4, interfaceC0655h, listS.size()));
        }
        return AbstractC1998m.n0(listS, arrayList);
    }

    public static final InterfaceC0652e d(InterfaceC0671y interfaceC0671y, C2057b c2057b) {
        A7.m.f(interfaceC0671y, "<this>");
        A7.m.f(c2057b, "classId");
        InterfaceC0654g interfaceC0654gE = e(interfaceC0671y, c2057b);
        if (interfaceC0654gE instanceof InterfaceC0652e) {
            return (InterfaceC0652e) interfaceC0654gE;
        }
        return null;
    }

    public static final InterfaceC0654g e(InterfaceC0671y interfaceC0671y, C2057b c2057b) {
        A7.m.f(interfaceC0671y, "<this>");
        A7.m.f(c2057b, "classId");
        if (interfaceC0671y.P(r8.m.f21890a) != null) {
            throw new ClassCastException();
        }
        C2058c c2058cG = c2057b.g();
        A7.m.e(c2058cG, "classId.packageFqName");
        I iN0 = interfaceC0671y.n0(c2058cG);
        List listE = c2057b.h().f20566a.e();
        y8.j jVar = ((T7.w) iN0).f8916s;
        Object objY = AbstractC1998m.Y(listE);
        A7.m.e(objY, "segments.first()");
        Y7.b bVar = Y7.b.f10802r;
        InterfaceC0654g interfaceC0654gC = jVar.c((C2061f) objY, bVar);
        if (interfaceC0654gC == null) {
            return null;
        }
        for (C2061f c2061f : listE.subList(1, listE.size())) {
            if (!(interfaceC0654gC instanceof InterfaceC0652e)) {
                return null;
            }
            y8.n nVarL0 = ((InterfaceC0652e) interfaceC0654gC).l0();
            A7.m.e(c2061f, "name");
            InterfaceC0654g interfaceC0654gC2 = nVarL0.c(c2061f, bVar);
            interfaceC0654gC = interfaceC0654gC2 instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gC2 : null;
            if (interfaceC0654gC == null) {
                return null;
            }
        }
        return interfaceC0654gC;
    }

    public static final InterfaceC0652e f(InterfaceC0671y interfaceC0671y, C2057b c2057b, B8.t tVar) {
        A7.m.f(interfaceC0671y, "<this>");
        A7.m.f(c2057b, "classId");
        A7.m.f(tVar, "notFoundClasses");
        InterfaceC0652e interfaceC0652eD = d(interfaceC0671y, c2057b);
        return interfaceC0652eD != null ? interfaceC0652eD : tVar.T(c2057b, P8.m.i0(P8.m.g0(P8.m.d0(c2057b, C0664q.f7201t), r.f7202n)));
    }

    public static final InterfaceC0654g g(InterfaceC0657j interfaceC0657j) {
        A7.m.f(interfaceC0657j, "<this>");
        InterfaceC0657j interfaceC0657jO = interfaceC0657j.o();
        if (interfaceC0657jO == null || (interfaceC0657j instanceof D)) {
            return null;
        }
        if (!(interfaceC0657jO.o() instanceof D)) {
            return g(interfaceC0657jO);
        }
        if (interfaceC0657jO instanceof InterfaceC0654g) {
            return (InterfaceC0654g) interfaceC0657jO;
        }
        return null;
    }

    public static final boolean h(E e10, C2058c c2058c) {
        A7.m.f(e10, "<this>");
        A7.m.f(c2058c, "fqName");
        return e10 instanceof H ? ((H) e10).a(c2058c) : i(e10, c2058c).isEmpty();
    }

    public static final ArrayList i(E e10, C2058c c2058c) {
        A7.m.f(e10, "<this>");
        A7.m.f(c2058c, "fqName");
        ArrayList arrayList = new ArrayList();
        b(e10, c2058c, arrayList);
        return arrayList;
    }

    public static final InterfaceC0652e j(T7.z zVar, C2058c c2058c) {
        InterfaceC0654g interfaceC0654gC;
        y8.n nVarL0;
        Y7.b bVar = Y7.b.l;
        A7.m.f(zVar, "<this>");
        A7.m.f(c2058c, "fqName");
        if (c2058c.d()) {
            return null;
        }
        C2058c c2058cE = c2058c.e();
        A7.m.e(c2058cE, "fqName.parent()");
        T7.w wVar = (T7.w) zVar.n0(c2058cE);
        C2061f c2061fF = c2058c.f();
        A7.m.e(c2061fF, "fqName.shortName()");
        InterfaceC0654g interfaceC0654gC2 = wVar.f8916s.c(c2061fF, bVar);
        InterfaceC0652e interfaceC0652e = interfaceC0654gC2 instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gC2 : null;
        if (interfaceC0652e != null) {
            return interfaceC0652e;
        }
        C2058c c2058cE2 = c2058c.e();
        A7.m.e(c2058cE2, "fqName.parent()");
        InterfaceC0652e interfaceC0652eJ = j(zVar, c2058cE2);
        if (interfaceC0652eJ == null || (nVarL0 = interfaceC0652eJ.l0()) == null) {
            interfaceC0654gC = null;
        } else {
            C2061f c2061fF2 = c2058c.f();
            A7.m.e(c2061fF2, "fqName.shortName()");
            interfaceC0654gC = nVarL0.c(c2061fF2, bVar);
        }
        if (interfaceC0654gC instanceof InterfaceC0652e) {
            return (InterfaceC0652e) interfaceC0654gC;
        }
        return null;
    }
}
