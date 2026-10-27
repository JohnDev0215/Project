package F8;

import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import T7.C0786e;
import h8.AbstractC1387d;
import io.sentry.C1489n1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0269d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0269d f2461a = new C0269d();

    public static final boolean b(G8.b bVar, I8.d dVar) {
        if (!bVar.b(dVar)) {
            if (dVar instanceof I8.b) {
                O oD = bVar.D(bVar.J((I8.b) dVar));
                if (bVar.G(oD) || !bVar.b(bVar.W(bVar.e(oD)))) {
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean c(G8.b bVar, J j3, I8.d dVar, I8.d dVar2, boolean z6) {
        Collection<I8.c> collectionP = bVar.p(dVar);
        if ((collectionP instanceof Collection) && collectionP.isEmpty()) {
            return false;
        }
        for (I8.c cVar : collectionP) {
            if (A7.m.a(bVar.e0(cVar), bVar.y(dVar2)) || (z6 && n(f2461a, j3, dVar2, cVar))) {
                return true;
            }
        }
        return false;
    }

    public static List d(J j3, I8.d dVar, I8.f fVar) {
        AbstractC0268c abstractC0268cL;
        G8.b bVar = j3.c;
        bVar.K(dVar, fVar);
        boolean zV = bVar.V(fVar);
        n7.u uVar = n7.u.l;
        if (!zV && bVar.N(dVar)) {
            return uVar;
        }
        if (bVar.o(fVar)) {
            if (!bVar.i(bVar.y(dVar), fVar)) {
                return uVar;
            }
            A aD0 = bVar.d0(dVar);
            if (aD0 != null) {
                dVar = aD0;
            }
            return AbstractC1387d.j(dVar);
        }
        O8.g gVar = new O8.g();
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
                I8.d dVarD0 = bVar.d0(dVar2);
                if (dVarD0 == null) {
                    dVarD0 = dVar2;
                }
                boolean zI = bVar.i(bVar.y(dVarD0), fVar);
                I i7 = I.c;
                if (zI) {
                    gVar.add(dVarD0);
                    abstractC0268cL = i7;
                } else {
                    abstractC0268cL = bVar.L(dVarD0) == 0 ? I.f2439b : bVar.l(dVarD0);
                }
                if (abstractC0268cL.equals(i7)) {
                    abstractC0268cL = null;
                }
                if (abstractC0268cL != null) {
                    Iterator it = bVar.k(bVar.y(dVar2)).iterator();
                    while (it.hasNext()) {
                        arrayDeque.add(abstractC0268cL.x(j3, (I8.c) it.next()));
                    }
                }
            }
        }
        j3.a();
        return gVar;
    }

    public static List e(J j3, I8.d dVar, I8.f fVar) {
        int i7;
        List listD = d(j3, dVar, fVar);
        if (listD.size() < 2) {
            return listD;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listD) {
            G8.b bVar = j3.c;
            I8.e eVarO = bVar.O((I8.d) obj);
            int iG = bVar.g(eVarO);
            while (true) {
                if (i7 >= iG) {
                    arrayList.add(obj);
                    break;
                }
                i7 = bVar.d(bVar.e(bVar.I(eVarO, i7))) == null ? i7 + 1 : 0;
            }
        }
        return !arrayList.isEmpty() ? arrayList : listD;
    }

    public static boolean g(J j3, I8.c cVar, I8.c cVar2) {
        A7.m.f(cVar, "a");
        A7.m.f(cVar2, "b");
        if (cVar == cVar2) {
            return true;
        }
        C0269d c0269d = f2461a;
        G8.b bVar = j3.c;
        if (l(bVar, cVar) && l(bVar, cVar2)) {
            a0 a0VarC = j3.c(j3.d(cVar));
            a0 a0VarC2 = j3.c(j3.d(cVar2));
            A aA0 = bVar.a0(a0VarC);
            if (!bVar.i(bVar.e0(a0VarC), bVar.e0(a0VarC2))) {
                return false;
            }
            if (bVar.L(aA0) == 0) {
                return bVar.j(a0VarC) || bVar.j(a0VarC2) || bVar.g0(aA0) == bVar.g0(bVar.a0(a0VarC2));
            }
        }
        return n(c0269d, j3, cVar, cVar2) && n(c0269d, j3, cVar2, cVar);
    }

    public static final a0 j(A a10, A a11) {
        A7.m.f(a10, "lowerBound");
        A7.m.f(a11, "upperBound");
        return a10.equals(a11) ? a10 : new r(a10, a11);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
    
        return r6.q(r6.e0(r7), r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static Q7.Q k(G8.b r6, I8.c r7, I8.d r8) {
        /*
            int r0 = r6.L(r7)
            r1 = 0
            r2 = r1
        L6:
            r3 = 0
            if (r2 >= r0) goto L66
            F8.O r4 = r6.X(r7, r2)
            boolean r5 = r6.G(r4)
            if (r5 != 0) goto L14
            r3 = r4
        L14:
            if (r3 == 0) goto L63
            F8.a0 r3 = r6.e(r3)
            if (r3 != 0) goto L1d
            goto L63
        L1d:
            F8.A r4 = r6.a0(r3)
            I8.d r4 = r6.Q(r4)
            boolean r4 = r6.M(r4)
            if (r4 == 0) goto L3b
            F8.A r4 = r6.a0(r8)
            I8.d r4 = r6.Q(r4)
            boolean r4 = r6.M(r4)
            if (r4 == 0) goto L3b
            r4 = 1
            goto L3c
        L3b:
            r4 = r1
        L3c:
            boolean r5 = r3.equals(r8)
            if (r5 != 0) goto L5a
            if (r4 == 0) goto L53
            F8.K r4 = r6.e0(r3)
            F8.K r5 = r6.e0(r8)
            boolean r4 = A7.m.a(r4, r5)
            if (r4 == 0) goto L53
            goto L5a
        L53:
            Q7.Q r3 = k(r6, r3, r8)
            if (r3 == 0) goto L63
            return r3
        L5a:
            F8.K r7 = r6.e0(r7)
            Q7.Q r6 = r6.q(r7, r2)
            return r6
        L63:
            int r2 = r2 + 1
            goto L6
        L66:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.C0269d.k(G8.b, I8.c, I8.d):Q7.Q");
    }

    public static boolean l(G8.b bVar, I8.c cVar) {
        if (bVar.F(bVar.e0(cVar))) {
            bVar.Y(cVar);
            if (!bVar.B(cVar) && !bVar.S(cVar) && A7.m.a(bVar.y(bVar.a0(cVar)), bVar.y(bVar.W(cVar)))) {
                return true;
            }
        }
        return false;
    }

    public static boolean m(J j3, I8.e eVar, I8.d dVar) {
        boolean zN;
        A7.m.f(eVar, "capturedSubArguments");
        G8.b bVar = j3.c;
        K kY = bVar.y(dVar);
        int iG = bVar.g(eVar);
        int iK0 = bVar.k0(kY);
        if (iG != iK0 || iG != bVar.L(dVar)) {
            return false;
        }
        for (int i7 = 0; i7 < iK0; i7++) {
            O oX = bVar.X(dVar, i7);
            if (!bVar.G(oX)) {
                a0 a0VarE = bVar.e(oX);
                O oI = bVar.I(eVar, i7);
                bVar.R(oI);
                a0 a0VarE2 = bVar.e(oI);
                int iT = bVar.t(bVar.q(kY, i7));
                int iR = bVar.R(oX);
                A7.k.p(iT, "declared");
                A7.k.p(iR, "useSite");
                if (iT == 3) {
                    iT = iR;
                } else if (iR != 3 && iT != iR) {
                    iT = 0;
                }
                if (iT == 0) {
                    return j3.f2441a;
                }
                C0269d c0269d = f2461a;
                if (iT == 3) {
                    o(bVar, a0VarE2, a0VarE);
                    o(bVar, a0VarE, a0VarE2);
                }
                int i10 = j3.f2444f;
                if (i10 > 100) {
                    throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + a0VarE2).toString());
                }
                j3.f2444f = i10 + 1;
                int iC = AbstractC2641j.c(iT);
                if (iC == 0) {
                    zN = n(c0269d, j3, a0VarE, a0VarE2);
                } else if (iC == 1) {
                    zN = n(c0269d, j3, a0VarE2, a0VarE);
                } else {
                    if (iC != 2) {
                        throw new A9.k();
                    }
                    zN = g(j3, a0VarE2, a0VarE);
                }
                j3.f2444f--;
                if (!zN) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x010b  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean n(F8.C0269d r25, F8.J r26, I8.c r27, I8.c r28) {
        /*
            Method dump skipped, instruction units count: 1444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.C0269d.n(F8.d, F8.J, I8.c, I8.c):boolean");
    }

    public static void o(G8.b bVar, I8.c cVar, I8.c cVar2) {
        I8.e eVarM0 = bVar.m0(cVar);
        if (eVarM0 instanceof I8.b) {
            I8.b bVar2 = (I8.b) eVarM0;
            if (!bVar.o0(bVar2) && bVar.G(bVar.D(bVar.J(bVar2))) && bVar.H(bVar2) == 1) {
                bVar.e0(cVar2);
            }
        }
    }

    public static C0278m p(a0 a0Var, boolean z6) {
        boolean zE;
        A7.m.f(a0Var, "type");
        if (a0Var instanceof C0278m) {
            return (C0278m) a0Var;
        }
        a0Var.B0();
        if ((a0Var.B0().n() instanceof Q7.Q) || (a0Var instanceof G8.h)) {
            InterfaceC0654g interfaceC0654gN = a0Var.B0().n();
            T7.O o = interfaceC0654gN instanceof T7.O ? (T7.O) interfaceC0654gN : null;
            zE = true;
            if (o == null || o.f8820x) {
                zE = (z6 && (a0Var.B0().n() instanceof Q7.Q)) ? Y.e(a0Var) : true ^ AbstractC0268c.f(G8.g.l(false, true, G8.e.f2723m, null, null, 24), AbstractC0268c.k(a0Var), I.f2439b);
            }
        } else {
            zE = false;
        }
        if (!zE) {
            return null;
        }
        if (a0Var instanceof AbstractC0282q) {
            AbstractC0282q abstractC0282q = (AbstractC0282q) a0Var;
            A7.m.a(abstractC0282q.f2477m.B0(), abstractC0282q.f2478n.B0());
        }
        return new C0278m(AbstractC0268c.k(a0Var).L0(false), z6);
    }

    public static final A q(H h10, InterfaceC0652e interfaceC0652e, List list) {
        A7.m.f(h10, "attributes");
        A7.m.f(interfaceC0652e, "descriptor");
        A7.m.f(list, "arguments");
        K kC = interfaceC0652e.C();
        A7.m.e(kC, "descriptor.typeConstructor");
        return r(h10, kC, list, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0074 A[PHI: r1
  0x0074: PHI (r1v11 y8.n) = (r1v10 y8.n), (r1v13 y8.n) binds: [B:31:0x0098, B:22:0x0071] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static F8.A r(F8.H r7, F8.K r8, java.util.List r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.C0269d.r(F8.H, F8.K, java.util.List, boolean):F8.A");
    }

    public static final A s(H h10, K k10, List list, y8.n nVar, boolean z6) {
        A7.m.f(h10, "attributes");
        A7.m.f(k10, "constructor");
        A7.m.f(list, "arguments");
        A7.m.f(nVar, "memberScope");
        B b10 = new B(k10, list, z6, nVar, new C0288x(h10, k10, list, nVar, z6));
        return h10.isEmpty() ? b10 : new C(b10, h10);
    }

    public static final A t(H h10, K k10, List list, boolean z6, y8.n nVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(h10, "attributes");
        A7.m.f(k10, "constructor");
        A7.m.f(list, "arguments");
        A7.m.f(nVar, "memberScope");
        B b10 = new B(k10, list, z6, nVar, interfaceC2782k);
        return h10.isEmpty() ? b10 : new C(b10, h10);
    }

    public void a(R7.h hVar, R7.h hVar2) {
        HashSet hashSet = new HashSet();
        Iterator it = hVar.iterator();
        while (it.hasNext()) {
            hashSet.add(((R7.b) it.next()).a());
        }
        Iterator it2 = hVar2.iterator();
        while (it2.hasNext()) {
            hashSet.contains(((R7.b) it2.next()).a());
        }
    }

    public T f(K k10, List list) {
        A7.m.f(k10, "typeConstructor");
        A7.m.f(list, "arguments");
        List listP = k10.p();
        A7.m.e(listP, "typeConstructor.parameters");
        Q7.Q q4 = (Q7.Q) AbstractC1998m.i0(listP);
        if (q4 == null || !q4.Q()) {
            return new C0284t((Q7.Q[]) listP.toArray(new Q7.Q[0]), (O[]) list.toArray(new O[0]), false);
        }
        List listP2 = k10.p();
        A7.m.e(listP2, "typeConstructor.parameters");
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listP2, 10));
        Iterator it = listP2.iterator();
        while (it.hasNext()) {
            arrayList.add(((Q7.Q) it.next()).C());
        }
        return new L(n7.z.x(AbstractC1998m.F0(arrayList, list)), false);
    }

    public A h(B8.t tVar, H h10, boolean z6, int i7, boolean z10) {
        H hK;
        D8.v vVar = (D8.v) tVar.o;
        O oI = i(new P(1, vVar.e1()), tVar, null, i7);
        AbstractC0287w abstractC0287wB = oI.b();
        A7.m.e(abstractC0287wB, "expandedProjection.type");
        A aB = AbstractC0268c.b(abstractC0287wB);
        if (AbstractC0268c.i(aB)) {
            return aB;
        }
        oI.a();
        a(aB.k(), AbstractC0274i.a(h10));
        if (!AbstractC0268c.i(aB)) {
            if (AbstractC0268c.i(aB)) {
                hK = aB.t0();
            } else {
                H hT0 = aB.t0();
                A7.m.f(hT0, "other");
                if (h10.isEmpty() && hT0.isEmpty()) {
                    hK = h10;
                } else {
                    ArrayList arrayList = new ArrayList();
                    Collection collectionValues = ((ConcurrentHashMap) H.f2437m.f16624m).values();
                    A7.m.e(collectionValues, "idPerType.values");
                    Iterator it = collectionValues.iterator();
                    while (it.hasNext()) {
                        int iIntValue = ((Number) it.next()).intValue();
                        C0273h c0273h = (C0273h) h10.l.get(iIntValue);
                        C0273h c0273h2 = (C0273h) hT0.l.get(iIntValue);
                        if (c0273h != null) {
                            if (c0273h2 != null) {
                                c0273h = new C0273h(com.bumptech.glide.c.T(c0273h.f2468a, c0273h2.f2468a));
                            }
                            c0273h2 = c0273h;
                        } else if (c0273h2 == null) {
                            c0273h2 = null;
                        } else if (c0273h != null) {
                            c0273h2 = new C0273h(com.bumptech.glide.c.T(c0273h2.f2468a, c0273h.f2468a));
                        }
                        O8.l.a(arrayList, c0273h2);
                    }
                    hK = C1489n1.k(arrayList);
                }
            }
            aB = AbstractC0268c.p(aB, null, hK, 1);
        }
        A aI = Y.i(aB, z6);
        if (!z10) {
            return aI;
        }
        C0786e c0786e = vVar.f1286s;
        A7.m.e(c0786e, "descriptor.typeConstructor");
        return AbstractC0268c.z(aI, s(h10, c0786e, (List) tVar.f809p, y8.m.f24213b, z6));
    }

    public O i(O o, B8.t tVar, Q7.Q q4, int i7) {
        int iE0;
        O p10;
        D8.v vVar = (D8.v) tVar.o;
        if (i7 > 100) {
            throw new AssertionError("Too deep recursion while expanding type alias " + vVar.getName());
        }
        if (o.c()) {
            A7.m.c(q4);
            return Y.j(q4);
        }
        AbstractC0287w abstractC0287wB = o.b();
        A7.m.e(abstractC0287wB, "underlyingProjection.type");
        K kB0 = abstractC0287wB.B0();
        A7.m.f(kB0, "constructor");
        InterfaceC0654g interfaceC0654gN = kB0.n();
        O o4 = interfaceC0654gN instanceof Q7.Q ? (O) ((Map) tVar.f807m).get(interfaceC0654gN) : null;
        if (o4 != null) {
            if (o4.c()) {
                A7.m.c(q4);
                return Y.j(q4);
            }
            a0 a0VarK0 = o4.b().K0();
            int iA = o4.a();
            A7.k.s(iA, "argument.projectionKind");
            int iA2 = o.a();
            A7.k.s(iA2, "underlyingProjection.projectionKind");
            if (iA2 != iA && iA2 != 1) {
                if (iA == 1) {
                    iA = iA2;
                } else {
                    A7.m.f(vVar, "typeAlias");
                }
            }
            if (q4 == null || (iE0 = q4.e0()) == 0) {
                iE0 = 1;
            }
            if (iE0 != iA && iE0 != 1) {
                if (iA == 1) {
                    iA = 1;
                } else {
                    A7.m.f(vVar, "typeAlias");
                }
            }
            a(abstractC0287wB.k(), a0VarK0.k());
            A aI = Y.i(AbstractC0268c.b(a0VarK0), abstractC0287wB.F0());
            H hT0 = abstractC0287wB.t0();
            if (!AbstractC0268c.i(aI)) {
                if (AbstractC0268c.i(aI)) {
                    hT0 = aI.t0();
                } else {
                    H hT02 = aI.t0();
                    hT0.getClass();
                    A7.m.f(hT02, "other");
                    if (!hT0.isEmpty() || !hT02.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        Collection collectionValues = ((ConcurrentHashMap) H.f2437m.f16624m).values();
                        A7.m.e(collectionValues, "idPerType.values");
                        Iterator it = collectionValues.iterator();
                        while (it.hasNext()) {
                            int iIntValue = ((Number) it.next()).intValue();
                            C0273h c0273h = (C0273h) hT0.l.get(iIntValue);
                            C0273h c0273h2 = (C0273h) hT02.l.get(iIntValue);
                            if (c0273h != null) {
                                if (c0273h2 != null) {
                                    c0273h = new C0273h(com.bumptech.glide.c.T(c0273h.f2468a, c0273h2.f2468a));
                                }
                                c0273h2 = c0273h;
                            } else if (c0273h2 == null) {
                                c0273h2 = null;
                            } else if (c0273h != null) {
                                c0273h2 = new C0273h(com.bumptech.glide.c.T(c0273h2.f2468a, c0273h.f2468a));
                            }
                            O8.l.a(arrayList, c0273h2);
                        }
                        hT0 = C1489n1.k(arrayList);
                    }
                }
                aI = AbstractC0268c.p(aI, null, hT0, 1);
            }
            return new P(iA, aI);
        }
        A aB = AbstractC0268c.b(o.b().K0());
        if (AbstractC0268c.i(aB) || !Y.c(aB, J8.a.o, null)) {
            return o;
        }
        K kB02 = aB.B0();
        InterfaceC0654g interfaceC0654gN2 = kB02.n();
        kB02.p().size();
        aB.o0().size();
        if (interfaceC0654gN2 instanceof Q7.Q) {
            p10 = o;
        } else {
            int i10 = 0;
            if (interfaceC0654gN2 instanceof D8.v) {
                D8.v vVar2 = (D8.v) interfaceC0654gN2;
                if (tVar.c0(vVar2)) {
                    H8.h hVar = H8.h.f2884q;
                    String str = vVar2.getName().l;
                    A7.m.e(str, "typeDescriptor.name.toString()");
                    return new P(1, H8.i.c(hVar, str));
                }
                List listO0 = aB.o0();
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listO0, 10));
                for (Object obj : listO0) {
                    int i11 = i10 + 1;
                    if (i10 < 0) {
                        AbstractC1999n.I();
                        throw null;
                    }
                    arrayList2.add(i((O) obj, tVar, (Q7.Q) kB02.p().get(i10), i7 + 1));
                    i10 = i11;
                }
                List listP = vVar2.f1286s.p();
                ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(listP, 10));
                Iterator it2 = listP.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(((Q7.Q) it2.next()).b1());
                }
                p10 = new P(o.a(), AbstractC0268c.z(h(new B8.t(tVar, vVar2, arrayList2, n7.z.x(AbstractC1998m.F0(arrayList3, arrayList2)), 2), aB.t0(), aB.F0(), i7 + 1, false), u(aB, tVar, i7)));
            } else {
                A aU = u(aB, tVar, i7);
                W.d(aU);
                for (Object obj2 : aU.o0()) {
                    int i12 = i10 + 1;
                    if (i10 < 0) {
                        AbstractC1999n.I();
                        throw null;
                    }
                    O o9 = (O) obj2;
                    if (!o9.c()) {
                        AbstractC0287w abstractC0287wB2 = o9.b();
                        A7.m.e(abstractC0287wB2, "substitutedArgument.type");
                        if (!Y.c(abstractC0287wB2, J8.a.f3111n, null)) {
                        }
                    }
                    i10 = i12;
                }
                p10 = new P(o.a(), aU);
            }
        }
        return p10;
    }

    public A u(A a10, B8.t tVar, int i7) {
        K kB0 = a10.B0();
        List listO0 = a10.o0();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listO0, 10));
        int i10 = 0;
        for (Object obj : listO0) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            O o = (O) obj;
            O oI = i(o, tVar, (Q7.Q) kB0.p().get(i10), i7 + 1);
            if (!oI.c()) {
                oI = new P(oI.a(), Y.h(oI.b(), o.b().F0()));
            }
            arrayList.add(oI);
            i10 = i11;
        }
        return AbstractC0268c.p(a10, arrayList, null, 2);
    }
}
