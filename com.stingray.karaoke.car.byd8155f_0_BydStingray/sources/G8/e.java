package G8;

import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.C0278m;
import F8.C0286v;
import F8.K;
import F8.O;
import F8.P;
import F8.a0;
import Q7.Q;
import g1.AbstractC1274a;
import g8.C1317g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import s8.C2413a;
import s8.C2415c;
import s8.InterfaceC2414b;

/* JADX INFO: loaded from: classes.dex */
public final class e implements b {
    public static final e l = new e();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f2723m = new e();

    public static A T(A a10) {
        AbstractC0287w abstractC0287wB;
        K kB0 = a10.B0();
        C0286v c0286v = null;
        a0VarK0 = null;
        a0 a0VarK0 = null;
        if (kB0 instanceof C2415c) {
            C2415c c2415c = (C2415c) kB0;
            O o = c2415c.f22377a;
            if (o.a() != 2) {
                o = null;
            }
            if (o != null && (abstractC0287wB = o.b()) != null) {
                a0VarK0 = abstractC0287wB.K0();
            }
            a0 a0Var = a0VarK0;
            if (c2415c.f22378b == null) {
                Collection collectionO = c2415c.o();
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(collectionO, 10));
                Iterator it = collectionO.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AbstractC0287w) it.next()).K0());
                }
                O o4 = c2415c.f22377a;
                A7.m.f(o4, "projection");
                c2415c.f22378b = new i(o4, new D8.d(1, arrayList), null, null, 8);
            }
            i iVar = c2415c.f22378b;
            A7.m.c(iVar);
            return new h(1, iVar, a0Var, a10.t0(), a10.F0(), 32);
        }
        if (!(kB0 instanceof C0286v) || !a10.F0()) {
            return a10;
        }
        C0286v c0286v2 = (C0286v) kB0;
        LinkedHashSet linkedHashSet = c0286v2.f2484b;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(linkedHashSet, 10));
        Iterator it2 = linkedHashSet.iterator();
        boolean z6 = false;
        while (it2.hasNext()) {
            arrayList2.add(AbstractC1274a.U((AbstractC0287w) it2.next()));
            z6 = true;
        }
        if (z6) {
            AbstractC0287w abstractC0287w = c0286v2.f2483a;
            a0 a0VarU = abstractC0287w != null ? AbstractC1274a.U(abstractC0287w) : null;
            arrayList2.isEmpty();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(arrayList2);
            linkedHashSet2.hashCode();
            C0286v c0286v3 = new C0286v(linkedHashSet2);
            c0286v3.f2483a = a0VarU;
            c0286v = c0286v3;
        }
        if (c0286v != null) {
            c0286v2 = c0286v;
        }
        return c0286v2.b();
    }

    @Override // G8.b
    public a0 A(I8.c cVar) {
        return g.P(cVar);
    }

    @Override // G8.b
    public boolean B(I8.c cVar) {
        A7.m.f(cVar, "<this>");
        A aH = g.h(cVar);
        return (aH != null ? g.f(aH) : null) != null;
    }

    @Override // G8.b
    public I8.c C(I8.c cVar) {
        return g.a0(this, cVar);
    }

    @Override // G8.b
    public O D(InterfaceC2414b interfaceC2414b) {
        return g.T(interfaceC2414b);
    }

    @Override // G8.b
    public C0278m E(I8.d dVar) {
        return g.f(dVar);
    }

    @Override // G8.b
    public boolean F(I8.f fVar) {
        return g.A(fVar);
    }

    @Override // G8.b
    public boolean G(O o) {
        return g.K(o);
    }

    @Override // G8.b
    public int H(I8.b bVar) {
        return g.k(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // G8.b
    public O I(I8.e eVar, int i7) {
        A7.m.f(eVar, "<this>");
        if (eVar instanceof I8.d) {
            return g.p((I8.c) eVar, i7);
        }
        if (eVar instanceof I8.a) {
            E e10 = ((I8.a) eVar).get(i7);
            A7.m.e(e10, "get(index)");
            return (O) e10;
        }
        throw new IllegalStateException(("unknown type argument list type: " + eVar + ", " + A7.A.f443a.b(eVar.getClass())).toString());
    }

    @Override // G8.b
    public i J(I8.b bVar) {
        return g.X(bVar);
    }

    @Override // G8.b
    public void K(I8.d dVar, I8.f fVar) {
    }

    @Override // G8.b
    public int L(I8.c cVar) {
        return g.c(cVar);
    }

    @Override // G8.b
    public boolean M(I8.d dVar) {
        A7.m.f(dVar, "<this>");
        A aH = g.h(dVar);
        return (aH != null ? g.e(this, aH) : null) != null;
    }

    @Override // G8.b
    public boolean N(I8.d dVar) {
        A7.m.f(dVar, "<this>");
        return g.y(g.W(dVar));
    }

    @Override // G8.b
    public I8.e O(I8.d dVar) {
        return g.d(dVar);
    }

    @Override // G8.b
    public a0 P(I8.b bVar) {
        return g.O(bVar);
    }

    @Override // G8.b
    public I8.d Q(I8.d dVar) {
        A aQ;
        A7.m.f(dVar, "<this>");
        C0278m c0278mF = g.f(dVar);
        return (c0278mF == null || (aQ = g.Q(c0278mF)) == null) ? dVar : aQ;
    }

    @Override // G8.b
    public int R(O o) {
        return g.t(o);
    }

    @Override // G8.b
    public boolean S(I8.c cVar) {
        A7.m.f(cVar, "$receiver");
        return cVar instanceof C1317g;
    }

    @Override // G8.b
    public boolean U(I8.f fVar) {
        return g.G(fVar);
    }

    @Override // G8.b
    public boolean V(I8.f fVar) {
        return g.y(fVar);
    }

    @Override // G8.b
    public A W(I8.c cVar) {
        A aY;
        A7.m.f(cVar, "<this>");
        AbstractC0282q abstractC0282qG = g.g(cVar);
        if (abstractC0282qG != null && (aY = g.Y(abstractC0282qG)) != null) {
            return aY;
        }
        A aH = g.h(cVar);
        A7.m.c(aH);
        return aH;
    }

    @Override // G8.b
    public O X(I8.c cVar, int i7) {
        return g.p(cVar, i7);
    }

    @Override // G8.b
    public void Y(I8.c cVar) {
        A7.m.f(cVar, "<this>");
        g.g(cVar);
    }

    @Override // G8.b
    public void Z(I8.d dVar) {
        g.L(dVar);
    }

    @Override // G8.b
    public A a(C0278m c0278m) {
        return g.Q(c0278m);
    }

    @Override // G8.b
    public A a0(I8.c cVar) {
        A aN;
        A7.m.f(cVar, "<this>");
        AbstractC0282q abstractC0282qG = g.g(cVar);
        if (abstractC0282qG != null && (aN = g.N(abstractC0282qG)) != null) {
            return aN;
        }
        A aH = g.h(cVar);
        A7.m.c(aH);
        return aH;
    }

    @Override // G8.b
    public boolean b(I8.d dVar) {
        A7.m.f(dVar, "<this>");
        return g.D(g.W(dVar));
    }

    @Override // G8.b
    public boolean b0(Q q4, I8.f fVar) {
        return g.v(q4, fVar);
    }

    @Override // G8.b
    public A c(AbstractC0282q abstractC0282q) {
        return g.N(abstractC0282q);
    }

    @Override // G8.b
    public I8.b c0(I8.d dVar) {
        return g.e(this, dVar);
    }

    @Override // G8.b
    public AbstractC0282q d(I8.c cVar) {
        return g.g(cVar);
    }

    @Override // G8.b
    public A d0(I8.d dVar) {
        return g.j(dVar);
    }

    @Override // G8.b
    public a0 e(O o) {
        return g.r(o);
    }

    @Override // G8.b
    public K e0(I8.c cVar) {
        A7.m.f(cVar, "<this>");
        A aH = g.h(cVar);
        if (aH == null) {
            aH = a0(cVar);
        }
        return g.W(aH);
    }

    @Override // G8.b
    public A f(AbstractC0282q abstractC0282q) {
        return g.Y(abstractC0282q);
    }

    @Override // G8.b
    public boolean f0(I8.d dVar) {
        return g.B(dVar);
    }

    @Override // G8.b
    public int g(I8.e eVar) {
        A7.m.f(eVar, "<this>");
        if (eVar instanceof I8.d) {
            return g.c((I8.c) eVar);
        }
        if (eVar instanceof I8.a) {
            return ((I8.a) eVar).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + eVar + ", " + A7.A.f443a.b(eVar.getClass())).toString());
    }

    @Override // G8.b
    public boolean g0(I8.d dVar) {
        return g.F(dVar);
    }

    @Override // G8.b
    public boolean h(I8.f fVar) {
        return g.x(fVar);
    }

    @Override // G8.b
    public P h0(I8.c cVar) {
        return g.i(cVar);
    }

    @Override // G8.b
    public boolean i(I8.f fVar, I8.f fVar2) {
        return g.b(fVar, fVar2);
    }

    @Override // G8.b
    public boolean j(a0 a0Var) {
        A7.m.f(a0Var, "<this>");
        return g.F(a0(a0Var)) != g.F(W(a0Var));
    }

    @Override // G8.b
    public boolean j0(I8.b bVar) {
        return g.J(bVar);
    }

    @Override // G8.b
    public Collection k(I8.f fVar) {
        return g.V(fVar);
    }

    @Override // G8.b
    public int k0(I8.f fVar) {
        return g.R(fVar);
    }

    @Override // G8.b
    public a l(I8.d dVar) {
        return g.U(this, dVar);
    }

    @Override // G8.b
    public boolean l0(I8.f fVar) {
        return g.E(fVar);
    }

    @Override // G8.b
    public boolean m(I8.d dVar) {
        A7.m.f(dVar, "<this>");
        return g.G(e0(dVar)) && !g.H(dVar);
    }

    @Override // G8.b
    public A m0(I8.c cVar) {
        return g.h(cVar);
    }

    @Override // G8.b
    public boolean n(I8.d dVar, I8.d dVar2) {
        return g.w(dVar, dVar2);
    }

    @Override // G8.b
    public A n0(I8.d dVar, boolean z6) {
        return g.Z(dVar, z6);
    }

    @Override // G8.b
    public boolean o(I8.f fVar) {
        return g.z(fVar);
    }

    @Override // G8.b
    public boolean o0(I8.b bVar) {
        A7.m.f(bVar, "$receiver");
        return bVar instanceof C2413a;
    }

    @Override // G8.b
    public Collection p(I8.d dVar) {
        return g.S(this, dVar);
    }

    @Override // G8.b
    public Q q(I8.f fVar, int i7) {
        return g.q(fVar, i7);
    }

    @Override // G8.b
    public void r(I8.d dVar) {
        g.M(dVar);
    }

    @Override // G8.b
    public O s(I8.d dVar, int i7) {
        A7.m.f(dVar, "<this>");
        if (i7 < 0 || i7 >= g.c(dVar)) {
            return null;
        }
        return g.p(dVar, i7);
    }

    @Override // G8.b
    public int t(Q q4) {
        A7.m.f(q4, "$receiver");
        int iE0 = q4.e0();
        A7.k.s(iE0, "this.variance");
        return l9.d.K(iE0);
    }

    public I8.c u(I8.c cVar) {
        A aZ;
        A7.m.f(cVar, "<this>");
        A aH = g.h(cVar);
        return (aH == null || (aZ = g.Z(aH, true)) == null) ? cVar : aZ;
    }

    public a0 v(I8.c cVar) {
        a0 a0VarJ;
        A7.m.f(cVar, "type");
        if (!(cVar instanceof AbstractC0287w)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        a0 a0VarK0 = ((AbstractC0287w) cVar).K0();
        if (a0VarK0 instanceof A) {
            a0VarJ = T((A) a0VarK0);
        } else {
            if (!(a0VarK0 instanceof AbstractC0282q)) {
                throw new A9.k();
            }
            AbstractC0282q abstractC0282q = (AbstractC0282q) a0VarK0;
            A a10 = abstractC0282q.f2477m;
            A aT = T(a10);
            A a11 = abstractC0282q.f2478n;
            A aT2 = T(a11);
            a0VarJ = (aT == a10 && aT2 == a11) ? a0VarK0 : C0269d.j(aT, aT2);
        }
        AbstractC0287w abstractC0287wE = AbstractC0268c.e(a0VarK0);
        return AbstractC0268c.A(a0VarJ, abstractC0287wE != null ? v(abstractC0287wE) : null);
    }

    @Override // G8.b
    public a0 w(ArrayList arrayList) {
        A a10;
        int size = arrayList.size();
        if (size == 0) {
            throw new IllegalStateException("Expected some types");
        }
        if (size == 1) {
            return (a0) AbstractC1998m.r0(arrayList);
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        boolean z6 = false;
        boolean z10 = false;
        while (it.hasNext()) {
            a0 a0Var = (a0) it.next();
            z6 = z6 || AbstractC0268c.i(a0Var);
            if (a0Var instanceof A) {
                a10 = (A) a0Var;
            } else {
                if (!(a0Var instanceof AbstractC0282q)) {
                    throw new A9.k();
                }
                A7.m.f(a0Var, "<this>");
                a10 = ((AbstractC0282q) a0Var).f2477m;
                z10 = true;
            }
            arrayList2.add(a10);
        }
        if (z6) {
            return H8.i.c(H8.h.f2873I, arrayList.toString());
        }
        t tVar = t.f2743a;
        if (!z10) {
            return tVar.b(arrayList2);
        }
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add(AbstractC0268c.y((a0) it2.next()));
        }
        return C0269d.j(tVar.b(arrayList2), tVar.b(arrayList3));
    }

    @Override // G8.b
    public a0 x(I8.d dVar, I8.d dVar2) {
        return g.m(this, dVar, dVar2);
    }

    @Override // G8.b
    public K y(I8.d dVar) {
        return g.W(dVar);
    }

    @Override // G8.b
    public boolean z(I8.f fVar) {
        return g.D(fVar);
    }
}
