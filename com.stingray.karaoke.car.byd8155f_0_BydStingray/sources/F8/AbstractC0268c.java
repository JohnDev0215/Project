package F8;

import Q7.InterfaceC0655h;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import g1.AbstractC1274a;
import h8.AbstractC1387d;
import io.sentry.C1489n1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: F8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0268c {
    /* JADX WARN: Multi-variable type inference failed */
    public static final a0 A(a0 a0Var, AbstractC0287w abstractC0287w) {
        A7.m.f(a0Var, "<this>");
        if (a0Var instanceof Z) {
            return A(((Z) a0Var).i0(), abstractC0287w);
        }
        if (abstractC0287w == null || abstractC0287w.equals(a0Var)) {
            return a0Var;
        }
        if (a0Var instanceof A) {
            return new D((A) a0Var, abstractC0287w);
        }
        if (a0Var instanceof AbstractC0282q) {
            return new C0283s((AbstractC0282q) a0Var, abstractC0287w);
        }
        throw new A9.k();
    }

    public static /* synthetic */ void a(int i7) {
        String str = i7 != 4 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 4 ? 3 : 2];
        switch (i7) {
            case 1:
            case 6:
                objArr[0] = "originalSubstitution";
                break;
            case 2:
            case 7:
                objArr[0] = "newContainingDeclaration";
                break;
            case 3:
            case 8:
                objArr[0] = "result";
                break;
            case 4:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
                break;
            case 5:
            default:
                objArr[0] = "typeParameters";
                break;
        }
        if (i7 != 4) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
        } else {
            objArr[1] = "substituteTypeParameters";
        }
        if (i7 != 4) {
            objArr[2] = "substituteTypeParameters";
        }
        String str2 = String.format(str, objArr);
        if (i7 == 4) {
            throw new IllegalStateException(str2);
        }
    }

    public static final A b(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        a0 a0VarK0 = abstractC0287w.K0();
        A a10 = a0VarK0 instanceof A ? (A) a0VarK0 : null;
        if (a10 != null) {
            return a10;
        }
        throw new IllegalStateException(("This is should be simple type: " + abstractC0287w).toString());
    }

    public static final AbstractC0287w c(ArrayList arrayList, List list, N7.i iVar) {
        AbstractC0287w abstractC0287wI = new W(new G(arrayList)).i(3, (AbstractC0287w) AbstractC1998m.Y(list));
        return abstractC0287wI == null ? iVar.n() : abstractC0287wI;
    }

    public static final I8.c d(I8.c cVar, HashSet hashSet) {
        I8.c cVarD;
        G8.e eVar = G8.e.f2723m;
        K kE0 = eVar.e0(cVar);
        if (!hashSet.add(kE0)) {
            return null;
        }
        Q7.Q qS = G8.g.s(kE0);
        if (qS != null) {
            I8.c cVarM = AbstractC1274a.M(qS);
            cVarD = d(cVarM, hashSet);
            if (cVarD == null) {
                return null;
            }
            boolean z6 = G8.g.C(eVar.e0(cVarM)) || ((cVarM instanceof I8.d) && G8.g.I((I8.d) cVarM));
            if ((cVarD instanceof I8.d) && G8.g.I((I8.d) cVarD) && G8.g.H(cVar) && z6) {
                return eVar.u(cVarM);
            }
            if (!G8.g.H(cVarD) && (cVar instanceof I8.d) && G8.g.F((I8.d) cVar)) {
                return eVar.u(cVarD);
            }
        } else {
            if (!G8.g.C(kE0)) {
                return cVar;
            }
            A7.m.f(cVar, "$receiver");
            if (!(cVar instanceof AbstractC0287w)) {
                StringBuilder sb = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
                sb.append(cVar);
                sb.append(", ");
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.g(A7.A.f443a, cVar.getClass(), sb).toString());
            }
            A aF = r8.h.f((AbstractC0287w) cVar);
            if (aF == null || (cVarD = d(aF, hashSet)) == null) {
                return null;
            }
            if (G8.g.H(cVar)) {
                return G8.g.H(cVarD) ? cVar : ((cVarD instanceof I8.d) && G8.g.I((I8.d) cVarD)) ? cVar : eVar.u(cVarD);
            }
        }
        return cVarD;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final AbstractC0287w e(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        if (abstractC0287w instanceof Z) {
            return ((Z) abstractC0287w).i();
        }
        return null;
    }

    public static boolean f(J j3, I8.d dVar, AbstractC0268c abstractC0268c) {
        A7.m.f(dVar, "type");
        G8.b bVar = j3.c;
        if ((bVar.N(dVar) && !bVar.g0(dVar)) || bVar.B(dVar)) {
            return true;
        }
        j3.b();
        ArrayDeque arrayDeque = j3.f2445g;
        A7.m.c(arrayDeque);
        O8.i iVar = j3.f2446h;
        A7.m.c(iVar);
        arrayDeque.push(dVar);
        while (!arrayDeque.isEmpty()) {
            if (iVar.f5195m > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + dVar + ". Supertypes = " + AbstractC1998m.f0(iVar, null, null, null, null, 63)).toString());
            }
            I8.d dVar2 = (I8.d) arrayDeque.pop();
            A7.m.e(dVar2, "current");
            if (iVar.add(dVar2)) {
                boolean zG0 = bVar.g0(dVar2);
                I i7 = I.c;
                AbstractC0268c abstractC0268c2 = zG0 ? i7 : abstractC0268c;
                if (abstractC0268c2.equals(i7)) {
                    abstractC0268c2 = null;
                }
                if (abstractC0268c2 == null) {
                    continue;
                } else {
                    Iterator it = bVar.k(bVar.y(dVar2)).iterator();
                    while (it.hasNext()) {
                        I8.d dVarX = abstractC0268c2.x(j3, (I8.c) it.next());
                        if ((bVar.N(dVarX) && !bVar.g0(dVarX)) || bVar.B(dVarX)) {
                            j3.a();
                            return true;
                        }
                        arrayDeque.add(dVarX);
                    }
                }
            }
        }
        j3.a();
        return false;
    }

    public static final a0 g(a0 a0Var, AbstractC0287w abstractC0287w) {
        A7.m.f(a0Var, "<this>");
        A7.m.f(abstractC0287w, "origin");
        return A(a0Var, e(abstractC0287w));
    }

    public static boolean h(J j3, I8.d dVar, I8.f fVar) {
        G8.b bVar = j3.c;
        if (bVar.m(dVar)) {
            return true;
        }
        if (bVar.g0(dVar)) {
            return false;
        }
        if (j3.f2442b) {
            bVar.Z(dVar);
        }
        return bVar.i(bVar.y(dVar), fVar);
    }

    public static final boolean i(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        a0 a0VarK0 = abstractC0287w.K0();
        return (a0VarK0 instanceof H8.f) || ((a0VarK0 instanceof AbstractC0282q) && (((AbstractC0282q) a0VarK0).O0() instanceof H8.f));
    }

    public static final boolean j(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        return abstractC0287w.K0() instanceof AbstractC0282q;
    }

    public static final A k(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        a0 a0VarK0 = abstractC0287w.K0();
        if (a0VarK0 instanceof AbstractC0282q) {
            return ((AbstractC0282q) a0VarK0).f2477m;
        }
        if (a0VarK0 instanceof A) {
            return (A) a0VarK0;
        }
        throw new A9.k();
    }

    public static final a0 l(a0 a0Var, boolean z6) {
        A7.m.f(a0Var, "<this>");
        C0278m c0278mP = C0269d.p(a0Var, z6);
        if (c0278mP != null) {
            return c0278mP;
        }
        A aM = m(a0Var);
        return aM != null ? aM : a0Var.L0(false);
    }

    public static final A m(a0 a0Var) {
        C0286v c0286v;
        K kB0 = a0Var.B0();
        C0286v c0286v2 = kB0 instanceof C0286v ? (C0286v) kB0 : null;
        if (c0286v2 == null) {
            return null;
        }
        LinkedHashSet<AbstractC0287w> linkedHashSet = c0286v2.f2484b;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(linkedHashSet, 10));
        boolean z6 = false;
        for (AbstractC0287w abstractC0287wL : linkedHashSet) {
            if (Y.e(abstractC0287wL)) {
                abstractC0287wL = l(abstractC0287wL.K0(), false);
                z6 = true;
            }
            arrayList.add(abstractC0287wL);
        }
        if (z6) {
            AbstractC0287w abstractC0287wL2 = c0286v2.f2483a;
            if (abstractC0287wL2 == null) {
                abstractC0287wL2 = null;
            } else if (Y.e(abstractC0287wL2)) {
                abstractC0287wL2 = l(abstractC0287wL2.K0(), false);
            }
            arrayList.isEmpty();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(arrayList);
            linkedHashSet2.hashCode();
            c0286v = new C0286v(linkedHashSet2);
            c0286v.f2483a = abstractC0287wL2;
        } else {
            c0286v = null;
        }
        if (c0286v == null) {
            return null;
        }
        return c0286v.b();
    }

    public static final A n(A a10, List list, H h10) {
        A7.m.f(a10, "<this>");
        A7.m.f(list, "newArguments");
        A7.m.f(h10, "newAttributes");
        if (list.isEmpty() && h10 == a10.t0()) {
            return a10;
        }
        if (list.isEmpty()) {
            return a10.N0(h10);
        }
        if (!(a10 instanceof H8.f)) {
            return C0269d.r(h10, a10.B0(), list, a10.F0());
        }
        H8.f fVar = (H8.f) a10;
        String[] strArr = fVar.f2862r;
        return new H8.f(fVar.f2858m, fVar.f2859n, fVar.o, list, fVar.f2861q, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static AbstractC0287w o(AbstractC0287w abstractC0287w, List list, R7.h hVar, int i7) {
        if ((i7 & 2) != 0) {
            hVar = abstractC0287w.k();
        }
        A7.m.f(abstractC0287w, "<this>");
        A7.m.f(hVar, "newAnnotations");
        if ((list.isEmpty() || list == abstractC0287w.o0()) && hVar == abstractC0287w.k()) {
            return abstractC0287w;
        }
        H hT0 = abstractC0287w.t0();
        if ((hVar instanceof R7.l) && hVar.isEmpty()) {
            hVar = R7.g.f7535a;
        }
        H hQ = q(hT0, hVar);
        a0 a0VarK0 = abstractC0287w.K0();
        if (a0VarK0 instanceof AbstractC0282q) {
            AbstractC0282q abstractC0282q = (AbstractC0282q) a0VarK0;
            return C0269d.j(n(abstractC0282q.f2477m, list, hQ), n(abstractC0282q.f2478n, list, hQ));
        }
        if (a0VarK0 instanceof A) {
            return n((A) a0VarK0, list, hQ);
        }
        throw new A9.k();
    }

    public static /* synthetic */ A p(A a10, List list, H h10, int i7) {
        if ((i7 & 1) != 0) {
            list = a10.o0();
        }
        if ((i7 & 2) != 0) {
            h10 = a10.t0();
        }
        return n(a10, list, h10);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final F8.H q(F8.H r5, R7.h r6) {
        /*
            java.lang.String r0 = "<this>"
            A7.m.f(r5, r0)
            R7.h r0 = F8.AbstractC0274i.a(r5)
            if (r0 != r6) goto Lc
            return r5
        Lc:
            H7.w[] r0 = F8.AbstractC0274i.f2469a
            r1 = 0
            r0 = r0[r1]
            L8.p r1 = F8.AbstractC0274i.f2470b
            r1.getClass()
            java.lang.String r2 = "property"
            A7.m.f(r0, r2)
            L8.a r0 = r5.l
            int r1 = r1.l
            java.lang.Object r0 = r0.get(r1)
            F8.h r0 = (F8.C0273h) r0
            if (r0 == 0) goto L6b
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L2f
        L2d:
            r0 = r5
            goto L67
        L2f:
            L8.a r1 = r5.l
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L3a:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L51
            java.lang.Object r3 = r1.next()
            r4 = r3
            F8.h r4 = (F8.C0273h) r4
            boolean r4 = A7.m.a(r4, r0)
            if (r4 != 0) goto L3a
            r2.add(r3)
            goto L3a
        L51:
            int r0 = r2.size()
            L8.a r1 = r5.l
            int r1 = r1.d()
            if (r0 != r1) goto L5e
            goto L2d
        L5e:
            io.sentry.n1 r0 = F8.H.f2437m
            r0.getClass()
            F8.H r0 = io.sentry.C1489n1.k(r2)
        L67:
            if (r0 != 0) goto L6a
            goto L6b
        L6a:
            r5 = r0
        L6b:
            java.util.Iterator r0 = r6.iterator()
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L7c
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L7c
            return r5
        L7c:
            F8.h r0 = new F8.h
            r0.<init>(r6)
            A7.B r6 = A7.A.f443a
            java.lang.Class<F8.h> r1 = F8.C0273h.class
            H7.c r6 = r6.b(r1)
            io.sentry.n1 r1 = F8.H.f2437m
            int r6 = r1.q(r6)
            L8.a r1 = r5.l
            java.lang.Object r6 = r1.get(r6)
            if (r6 == 0) goto L98
            goto Lb4
        L98:
            boolean r6 = r5.isEmpty()
            if (r6 == 0) goto La8
            F8.H r5 = new F8.H
            java.util.List r6 = h8.AbstractC1387d.j(r0)
            r5.<init>(r6)
            goto Lb4
        La8:
            java.util.List r5 = n7.AbstractC1998m.z0(r5)
            java.util.ArrayList r5 = n7.AbstractC1998m.o0(r5, r0)
            F8.H r5 = io.sentry.C1489n1.k(r5)
        Lb4:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.AbstractC0268c.q(F8.H, R7.h):F8.H");
    }

    public static final AbstractC0287w r(Q7.Q q4) {
        A7.m.f(q4, "<this>");
        InterfaceC0657j interfaceC0657jO = q4.o();
        A7.m.e(interfaceC0657jO, "this.containingDeclaration");
        if (interfaceC0657jO instanceof InterfaceC0655h) {
            List listP = ((InterfaceC0655h) interfaceC0657jO).C().p();
            A7.m.e(listP, "descriptor.typeConstructor.parameters");
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(listP, 10));
            Iterator it = listP.iterator();
            while (it.hasNext()) {
                K kC = ((Q7.Q) it.next()).C();
                A7.m.e(kC, "it.typeConstructor");
                arrayList.add(kC);
            }
            List upperBounds = q4.getUpperBounds();
            A7.m.e(upperBounds, "upperBounds");
            return c(arrayList, upperBounds, AbstractC2564d.e(q4));
        }
        if (!(interfaceC0657jO instanceof InterfaceC0666t)) {
            throw new IllegalArgumentException("Unsupported descriptor type to build star projection type based on type parameters of it");
        }
        List listT = ((InterfaceC0666t) interfaceC0657jO).t();
        A7.m.e(listT, "descriptor.typeParameters");
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listT, 10));
        Iterator it2 = listT.iterator();
        while (it2.hasNext()) {
            K kC2 = ((Q7.Q) it2.next()).C();
            A7.m.e(kC2, "it.typeConstructor");
            arrayList2.add(kC2);
        }
        List upperBounds2 = q4.getUpperBounds();
        A7.m.e(upperBounds2, "upperBounds");
        return c(arrayList2, upperBounds2, AbstractC2564d.e(q4));
    }

    public static boolean s(G8.b bVar, I8.d dVar, I8.d dVar2) {
        if (bVar.L(dVar) == bVar.L(dVar2) && bVar.g0(dVar) == bVar.g0(dVar2)) {
            if ((bVar.E(dVar) == null) == (bVar.E(dVar2) == null) && bVar.i(bVar.y(dVar), bVar.y(dVar2))) {
                if (bVar.n(dVar, dVar2)) {
                    return true;
                }
                int iL = bVar.L(dVar);
                for (int i7 = 0; i7 < iL; i7++) {
                    O oX = bVar.X(dVar, i7);
                    O oX2 = bVar.X(dVar2, i7);
                    if (bVar.G(oX) != bVar.G(oX2)) {
                        return false;
                    }
                    if (!bVar.G(oX) && (bVar.R(oX) != bVar.R(oX2) || !t(bVar, bVar.e(oX), bVar.e(oX2)))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean t(G8.b bVar, I8.c cVar, I8.c cVar2) {
        if (cVar == cVar2) {
            return true;
        }
        A aM0 = bVar.m0(cVar);
        A aM02 = bVar.m0(cVar2);
        if (aM0 != null && aM02 != null) {
            return s(bVar, aM0, aM02);
        }
        AbstractC0282q abstractC0282qD = bVar.d(cVar);
        AbstractC0282q abstractC0282qD2 = bVar.d(cVar2);
        if (abstractC0282qD == null || abstractC0282qD2 == null) {
            return false;
        }
        return s(bVar, bVar.c(abstractC0282qD), bVar.c(abstractC0282qD2)) && s(bVar, bVar.f(abstractC0282qD), bVar.f(abstractC0282qD2));
    }

    public static W u(List list, T t10, InterfaceC0657j interfaceC0657j, ArrayList arrayList) {
        if (t10 == null) {
            a(1);
            throw null;
        }
        if (interfaceC0657j == null) {
            a(2);
            throw null;
        }
        if (arrayList == null) {
            a(3);
            throw null;
        }
        W wV = v(list, t10, interfaceC0657j, arrayList, null);
        if (wV != null) {
            return wV;
        }
        throw new AssertionError("Substitution failed");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static F8.W v(java.util.List r17, F8.T r18, Q7.InterfaceC0657j r19, java.util.ArrayList r20, boolean[] r21) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.AbstractC0268c.v(java.util.List, F8.T, Q7.j, java.util.ArrayList, boolean[]):F8.W");
    }

    public static final H w(R7.h hVar) {
        A7.m.f(hVar, "<this>");
        if (hVar.isEmpty()) {
            H.f2437m.getClass();
            return H.f2438n;
        }
        C1489n1 c1489n1 = H.f2437m;
        List listJ = AbstractC1387d.j(new C0273h(hVar));
        c1489n1.getClass();
        return C1489n1.k(listJ);
    }

    public static final A y(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        a0 a0VarK0 = abstractC0287w.K0();
        if (a0VarK0 instanceof AbstractC0282q) {
            return ((AbstractC0282q) a0VarK0).f2478n;
        }
        if (a0VarK0 instanceof A) {
            return (A) a0VarK0;
        }
        throw new A9.k();
    }

    public static final A z(A a10, A a11) {
        A7.m.f(a10, "<this>");
        A7.m.f(a11, "abbreviatedType");
        return i(a10) ? a10 : new C0266a(a10, a11);
    }

    public abstract I8.d x(J j3, I8.c cVar);
}
