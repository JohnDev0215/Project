package d8;

import A.C0050y0;
import F8.AbstractC0287w;
import F8.a0;
import Q7.InterfaceC0657j;
import T7.Q;
import a.AbstractC0904a;
import b8.C1011f;
import c8.C1036a;
import c8.C1038c;
import e8.C1236a;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import l3.P;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class z extends y8.o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f13634m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N0.q f13635b;
    public final z c;
    public final E8.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E8.i f13636e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final E8.e f13637f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final E8.j f13638g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final E8.e f13639h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final E8.i f13640i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final E8.i f13641j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final E8.i f13642k;
    public final E8.e l;

    static {
        A7.B b10 = A7.A.f443a;
        f13634m = new H7.w[]{b10.h(new A7.s(b10.b(z.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), b10.h(new A7.s(b10.b(z.class), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), b10.h(new A7.s(b10.b(z.class), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};
    }

    public z(N0.q qVar, z zVar) {
        A7.m.f(qVar, "c");
        this.f13635b = qVar;
        this.c = zVar;
        E8.l lVar = ((C1036a) qVar.f4399m).f12411a;
        C1137x c1137x = new C1137x(this, 0);
        lVar.getClass();
        this.d = new E8.c(lVar, c1137x);
        C1137x c1137x2 = new C1137x(this, 2);
        lVar.getClass();
        this.f13636e = new E8.i(lVar, c1137x2);
        this.f13637f = lVar.b(new y(this, 1));
        this.f13638g = lVar.c(new y(this, 0));
        this.f13639h = lVar.b(new y(this, 2));
        C1137x c1137x3 = new C1137x(this, 3);
        lVar.getClass();
        this.f13640i = new E8.i(lVar, c1137x3);
        C1137x c1137x4 = new C1137x(this, 4);
        lVar.getClass();
        this.f13641j = new E8.i(lVar, c1137x4);
        C1137x c1137x5 = new C1137x(this, 1);
        lVar.getClass();
        this.f13642k = new E8.i(lVar, c1137x5);
        this.l = lVar.b(new y(this, 3));
    }

    public static AbstractC0287w l(W7.w wVar, N0.q qVar) {
        A7.m.f(wVar, "method");
        Class<?> declaringClass = ((Method) wVar.b()).getDeclaringClass();
        A7.m.e(declaringClass, "member.declaringClass");
        C1236a c1236aS = AbstractC0904a.S(2, declaringClass.isAnnotation(), false, null, 6);
        return ((P) qVar.l).G(wVar.f(), c1236aS);
    }

    public static C0050y0 u(N0.q qVar, T7.t tVar, List list) {
        m7.i iVar;
        C2061f c2061f;
        C2061f c2061fE;
        P8.p pVarE0 = AbstractC1998m.E0(list);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(pVarE0, 10));
        Iterator it = pVarE0.iterator();
        boolean z6 = false;
        while (true) {
            P8.b bVar = (P8.b) it;
            if (!bVar.f5459m.hasNext()) {
                return new C0050y0(4, AbstractC1998m.z0(arrayList), z6);
            }
            n7.x xVar = (n7.x) bVar.next();
            int i7 = xVar.f20159a;
            W7.C c = (W7.C) xVar.f20160b;
            C1038c c1038cY = l9.l.Y(qVar, c);
            C1236a c1236aS = AbstractC0904a.S(2, false, false, null, 7);
            C1036a c1036a = (C1036a) qVar.f4399m;
            W7.A a10 = c.f9869a;
            boolean z10 = c.d;
            P p10 = (P) qVar.l;
            T7.z zVar = c1036a.o;
            if (z10) {
                W7.h hVar = a10 instanceof W7.h ? (W7.h) a10 : null;
                if (hVar == null) {
                    throw new AssertionError("Vararg parameter should be an array: " + c);
                }
                a0 a0VarF = p10.F(hVar, c1236aS, true);
                iVar = new m7.i(a0VarF, zVar.f8921p.f(a0VarF));
            } else {
                iVar = new m7.i(p10.G(a10, c1236aS), null);
            }
            AbstractC0287w abstractC0287w = (AbstractC0287w) iVar.l;
            AbstractC0287w abstractC0287w2 = (AbstractC0287w) iVar.f19259m;
            if (A7.m.a(tVar.getName().b(), "equals") && list.size() == 1 && zVar.f8921p.n().equals(abstractC0287w)) {
                c2061fE = C2061f.e("other");
            } else {
                String str = c.c;
                C2061f c2061fD = str != null ? C2061f.d(str) : null;
                if (c2061fD == null) {
                    z6 = true;
                }
                if (c2061fD == null) {
                    c2061fE = C2061f.e("p" + i7);
                } else {
                    c2061f = c2061fD;
                    arrayList.add(new Q(tVar, null, i7, c1038cY, c2061f, abstractC0287w, false, false, false, abstractC0287w2, c1036a.f12418j.b(c)));
                }
            }
            c2061f = c2061fE;
            arrayList.add(new Q(tVar, null, i7, c1038cY, c2061f, abstractC0287w, false, false, false, abstractC0287w2, c1036a.f12418j.b(c)));
        }
    }

    @Override // y8.o, y8.n
    public Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        return !e().contains(c2061f) ? n7.u.l : (Collection) this.f13639h.b(c2061f);
    }

    @Override // y8.o, y8.n
    public Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return !f().contains(c2061f) ? n7.u.l : (Collection) this.l.b(c2061f);
    }

    @Override // y8.o, y8.p
    public Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        return (Collection) this.d.a();
    }

    @Override // y8.o, y8.n
    public final Set e() {
        return (Set) C7.a.N(this.f13640i, f13634m[0]);
    }

    @Override // y8.o, y8.n
    public final Set f() {
        return (Set) C7.a.N(this.f13641j, f13634m[1]);
    }

    @Override // y8.o, y8.n
    public final Set g() {
        return (Set) C7.a.N(this.f13642k, f13634m[2]);
    }

    public abstract Set h(y8.f fVar, y8.k kVar);

    public abstract Set i(y8.f fVar, y8.k kVar);

    public void j(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
    }

    public abstract InterfaceC1116c k();

    public abstract void m(LinkedHashSet linkedHashSet, C2061f c2061f);

    public abstract void n(ArrayList arrayList, C2061f c2061f);

    public abstract Set o(y8.f fVar);

    public abstract T7.u p();

    public abstract InterfaceC0657j q();

    public boolean r(C1011f c1011f) {
        return true;
    }

    public abstract C1136w s(W7.w wVar, ArrayList arrayList, AbstractC0287w abstractC0287w, List list);

    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object, m7.f] */
    public final C1011f t(W7.w wVar) {
        A7.m.f(wVar, "method");
        N0.q qVar = this.f13635b;
        int i7 = 1;
        C1011f c1011fR1 = C1011f.r1(q(), l9.l.Y(qVar, wVar), wVar.c(), ((C1036a) qVar.f4399m).f12418j.b(wVar), ((InterfaceC1116c) this.f13636e.a()).f(wVar.c()) != null && ((ArrayList) wVar.g()).isEmpty());
        A7.m.f(qVar, "<this>");
        N0.q qVar2 = new N0.q((C1036a) qVar.f4399m, new W2.j(qVar, c1011fR1, wVar, 0), qVar.o);
        ArrayList arrayListT = wVar.t();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(arrayListT, 10));
        Iterator it = arrayListT.iterator();
        while (it.hasNext()) {
            Q7.Q qB = ((c8.e) qVar2.f4400n).b((W7.B) it.next());
            A7.m.c(qB);
            arrayList.add(qB);
        }
        C0050y0 c0050y0U = u(qVar2, c1011fR1, wVar.g());
        C1136w c1136wS = s(wVar, arrayList, l(wVar, qVar2), (List) c0050y0U.f301n);
        T7.u uVarP = p();
        n7.u uVar = n7.u.l;
        boolean zIsAbstract = Modifier.isAbstract(((Method) wVar.b()).getModifiers());
        boolean zIsFinal = Modifier.isFinal(((Method) wVar.b()).getModifiers());
        if (zIsAbstract) {
            i7 = 4;
        } else if (!zIsFinal) {
            i7 = 3;
        }
        c1011fR1.q1(null, uVarP, uVar, c1136wS.c, c1136wS.f13629b, c1136wS.f13628a, i7, com.bumptech.glide.c.D0(wVar.e()), n7.v.l);
        c1011fR1.s1(false, c0050y0U.f300m);
        if (c1136wS.d.isEmpty()) {
            return c1011fR1;
        }
        ((C1036a) qVar2.f4399m).f12413e.getClass();
        throw new UnsupportedOperationException("Should not be called");
    }

    public String toString() {
        return "Lazy scope for " + q();
    }
}
