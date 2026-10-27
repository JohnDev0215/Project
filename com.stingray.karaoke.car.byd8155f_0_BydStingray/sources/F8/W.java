package F8;

import e8.C1240e;
import g1.AbstractC1274a;
import java.util.ArrayList;
import java.util.List;
import s8.InterfaceC2414b;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class W {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final W f2454b = new W(T.f2453a);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f2455a;

    public W(T t10) {
        this.f2455a = t10;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(int r13) {
        /*
            Method dump skipped, instruction units count: 660
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.W.a(int):void");
    }

    public static int b(int i7, int i10) {
        if (i7 == 0) {
            a(38);
            throw null;
        }
        if (i10 == 0) {
            a(39);
            throw null;
        }
        if (i7 == 1) {
            if (i10 != 0) {
                return i10;
            }
            a(40);
            throw null;
        }
        if (i10 == 1) {
            if (i7 != 0) {
                return i7;
            }
            a(41);
            throw null;
        }
        if (i7 == i10) {
            if (i10 != 0) {
                return i10;
            }
            a(42);
            throw null;
        }
        throw new AssertionError("Variance conflict: type parameter variance '" + A7.k.x(i7) + "' and projection kind '" + A7.k.x(i10) + "' cannot be combined");
    }

    public static int c(int i7, int i10) {
        if (i7 == 2 && i10 == 3) {
            return 3;
        }
        return (i7 == 3 && i10 == 2) ? 2 : 1;
    }

    public static W d(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(6);
            throw null;
        }
        return new W(M.f2447b.f(abstractC0287w.B0(), abstractC0287w.o0()));
    }

    public static W e(T t10, T t11) {
        if (t10 == null) {
            a(3);
            throw null;
        }
        if (t11 == null) {
            a(4);
            throw null;
        }
        if (t10.e()) {
            t10 = t11;
        } else if (!t11.e()) {
            t10 = new C0281p(t10, t11);
        }
        return new W(t10);
    }

    public static String h(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (O8.l.h(th)) {
                throw th;
            }
            return "[Exception while computing toString(): " + th + "]";
        }
    }

    public final T f() {
        T t10 = this.f2455a;
        if (t10 != null) {
            return t10;
        }
        a(8);
        throw null;
    }

    public final AbstractC0287w g(int i7, AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(9);
            throw null;
        }
        if (i7 == 0) {
            a(10);
            throw null;
        }
        if (this.f2455a.e()) {
            return abstractC0287w;
        }
        try {
            AbstractC0287w abstractC0287wB = j(new P(i7, abstractC0287w), null, 0).b();
            if (abstractC0287wB != null) {
                return abstractC0287wB;
            }
            a(12);
            throw null;
        } catch (V e10) {
            return H8.i.c(H8.h.f2889v, e10.getMessage());
        }
    }

    public final AbstractC0287w i(int i7, AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(14);
            throw null;
        }
        if (i7 == 0) {
            a(15);
            throw null;
        }
        O p10 = new P(i7, f().f(i7, abstractC0287w));
        T t10 = this.f2455a;
        if (!t10.e()) {
            try {
                p10 = j(p10, null, 0);
            } catch (V unused) {
                p10 = null;
            }
        }
        if (t10.a() || t10.b()) {
            boolean zB = t10.b();
            if (p10 == null) {
                p10 = null;
            } else if (!p10.c()) {
                AbstractC0287w abstractC0287wB = p10.b();
                A7.m.e(abstractC0287wB, "typeProjection.type");
                if (Y.c(abstractC0287wB, K8.b.f3860m, null)) {
                    int iA = p10.a();
                    A7.k.s(iA, "typeProjection.projectionKind");
                    if (iA == 3) {
                        p10 = new P(iA, (AbstractC0287w) C7.a.u(abstractC0287wB).f3859b);
                    } else if (zB) {
                        p10 = new P(iA, (AbstractC0287w) C7.a.u(abstractC0287wB).f3858a);
                    } else {
                        K8.c cVar = new K8.c();
                        W w10 = new W(cVar);
                        if (!cVar.e()) {
                            try {
                                p10 = w10.j(p10, null, 0);
                            } catch (V unused2) {
                                p10 = null;
                            }
                        }
                    }
                }
            }
        }
        if (p10 == null) {
            return null;
        }
        return p10.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    public final O j(O o, Q7.Q q4, int i7) throws V {
        char c;
        int i10;
        W w10;
        int i11 = 0;
        AbstractC0287w abstractC0287wI = null;
        if (o == null) {
            a(18);
            throw null;
        }
        T t10 = this.f2455a;
        if (i7 > 100) {
            throw new IllegalStateException("Recursion too deep. Most likely infinite loop while substituting " + h(o) + "; substitution: " + h(t10));
        }
        if (o.c()) {
            return o;
        }
        AbstractC0287w abstractC0287wB = o.b();
        if (abstractC0287wB instanceof Z) {
            Z z6 = (Z) abstractC0287wB;
            a0 a0VarI0 = z6.i0();
            AbstractC0287w abstractC0287wI2 = z6.i();
            O oJ = j(new P(o.a(), a0VarI0), q4, i7 + 1);
            return oJ.c() ? oJ : new P(oJ.a(), AbstractC0268c.A(oJ.b().K0(), i(o.a(), abstractC0287wI2)));
        }
        A7.m.f(abstractC0287wB, "<this>");
        abstractC0287wB.K0();
        if (abstractC0287wB.K0() instanceof C1240e) {
            return o;
        }
        O oD = t10.d(abstractC0287wB);
        if (oD == null) {
            oD = null;
        } else if (abstractC0287wB.k().k(N7.n.f4866y)) {
            K kB0 = oD.b().B0();
            if (kB0 instanceof G8.i) {
                O o4 = ((G8.i) kB0).f2731a;
                int iA = o4.a();
                if (c(o.a(), iA) == 3) {
                    oD = new P(o4.b());
                } else if (q4 != null && c(q4.e0(), iA) == 3) {
                    oD = new P(o4.b());
                }
            }
        }
        int iA2 = o.a();
        if (oD == null && AbstractC0268c.j(abstractC0287wB)) {
            I8.c cVarK0 = abstractC0287wB.K0();
            InterfaceC0276k interfaceC0276k = cVarK0 instanceof InterfaceC0276k ? (InterfaceC0276k) cVarK0 : null;
            if (!(interfaceC0276k != null ? interfaceC0276k.W() : false)) {
                AbstractC0282q abstractC0282q = (AbstractC0282q) abstractC0287wB.K0();
                A a10 = abstractC0282q.f2477m;
                int i12 = i7 + 1;
                O oJ2 = j(new P(iA2, a10), q4, i12);
                A a11 = abstractC0282q.f2478n;
                O oJ3 = j(new P(iA2, a11), q4, i12);
                return (oJ2.b() == a10 && oJ3.b() == a11) ? o : new P(oJ2.a(), C0269d.j(AbstractC0268c.b(oJ2.b()), AbstractC0268c.b(oJ3.b())));
            }
        }
        if (N7.i.D(abstractC0287wB) || AbstractC0268c.i(abstractC0287wB)) {
            return o;
        }
        if (oD != null) {
            int iC = c(iA2, oD.a());
            if (!(abstractC0287wB.B0() instanceof InterfaceC2414b)) {
                int iC2 = AbstractC2641j.c(iC);
                if (iC2 == 1) {
                    return new P(3, abstractC0287wB.B0().l().n());
                }
                if (iC2 == 2) {
                    throw new V("Out-projection in in-position");
                }
            }
            I8.c cVarK02 = abstractC0287wB.K0();
            InterfaceC0276k interfaceC0276k2 = cVarK02 instanceof InterfaceC0276k ? (InterfaceC0276k) cVarK02 : null;
            if (interfaceC0276k2 == null || !interfaceC0276k2.W()) {
                interfaceC0276k2 = null;
            }
            if (oD.c()) {
                return oD;
            }
            AbstractC0287w abstractC0287wA = interfaceC0276k2 != null ? interfaceC0276k2.A(oD.b()) : Y.h(oD.b(), abstractC0287wB.F0());
            if (!abstractC0287wB.k().isEmpty()) {
                R7.h hVarC = t10.c(abstractC0287wB.k());
                if (hVarC == null) {
                    a(33);
                    throw null;
                }
                if (hVarC.k(N7.n.f4866y)) {
                    hVarC = new R7.l(hVarC, new U());
                }
                abstractC0287wA = AbstractC1274a.Y(abstractC0287wA, new R7.i(new R7.h[]{abstractC0287wA.k(), hVarC}));
            }
            if (iC == 1) {
                iA2 = b(iA2, oD.a());
            }
            return new P(iA2, abstractC0287wA);
        }
        AbstractC0287w abstractC0287wB2 = o.b();
        int iA3 = o.a();
        if (abstractC0287wB2.B0().n() instanceof Q7.Q) {
            return o;
        }
        a0 a0VarK0 = abstractC0287wB2.K0();
        C0266a c0266a = a0VarK0 instanceof C0266a ? (C0266a) a0VarK0 : null;
        A a12 = c0266a != null ? c0266a.f2460n : null;
        if (a12 != null) {
            if ((t10 instanceof C0284t) && t10.b()) {
                C0284t c0284t = (C0284t) t10;
                w10 = new W(new C0284t(c0284t.f2480b, c0284t.c, false));
            } else {
                w10 = this;
            }
            abstractC0287wI = w10.i(1, a12);
        }
        List listP = abstractC0287wB2.B0().p();
        List listO0 = abstractC0287wB2.o0();
        ArrayList arrayList = new ArrayList(listP.size());
        int i13 = 0;
        while (i11 < listP.size()) {
            Q7.Q q10 = (Q7.Q) listP.get(i11);
            O o9 = (O) listO0.get(i11);
            O oJ4 = j(o9, q10, i7 + 1);
            int iC3 = AbstractC2641j.c(c(q10.e0(), oJ4.a()));
            if (iC3 != 0) {
                if (iC3 != 1) {
                    c = 2;
                    if (iC3 == 2) {
                    }
                    i10 = 1;
                } else {
                    c = 2;
                }
                oJ4 = Y.j(q10);
                i10 = 1;
            } else {
                c = 2;
                int iE0 = q10.e0();
                i10 = 1;
                if (iE0 != 1 && !oJ4.c()) {
                    oJ4 = new P(1, oJ4.b());
                }
            }
            if (oJ4 != o9) {
                i13 = i10;
            }
            arrayList.add(oJ4);
            i11 += i10;
        }
        if (i13 != 0) {
            listO0 = arrayList;
        }
        R7.h hVarC2 = t10.c(abstractC0287wB2.k());
        A7.m.f(listO0, "newArguments");
        A7.m.f(hVarC2, "newAnnotations");
        AbstractC0287w abstractC0287wO = AbstractC0268c.o(abstractC0287wB2, listO0, hVarC2, 4);
        if ((abstractC0287wO instanceof A) && (abstractC0287wI instanceof A)) {
            abstractC0287wO = AbstractC0268c.z((A) abstractC0287wO, (A) abstractC0287wI);
        }
        return new P(iA3, abstractC0287wO);
    }
}
