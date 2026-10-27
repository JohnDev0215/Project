package y0;

import A.C0006c;
import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.d0;
import E0.n0;
import u.C2472l;
import u.C2482v;

/* JADX INFO: renamed from: y0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2729g extends C0006c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0.n f24059b;
    public final h2.p c;
    public final C2472l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d0 f24060e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C2730h f24061f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f24062g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f24063h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f24064i;

    public C2729g(g0.n nVar) {
        super(2);
        this.f24059b = nVar;
        h2.p pVar = new h2.p();
        pVar.f15230b = new long[2];
        this.c = pVar;
        this.d = new C2472l(2);
        this.f24063h = true;
        this.f24064i = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0148  */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v0, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v50, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v51 */
    /* JADX WARN: Type inference failed for: r5v52 */
    /* JADX WARN: Type inference failed for: r5v53 */
    /* JADX WARN: Type inference failed for: r5v54 */
    /* JADX WARN: Type inference failed for: r5v55 */
    /* JADX WARN: Type inference failed for: r5v56 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [int] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // A.C0006c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(u.C2472l r42, C0.r r43, t4.e r44, boolean r45) {
        /*
            Method dump skipped, instruction units count: 633
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.C2729g.a(u.l, C0.r, t4.e, boolean):boolean");
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // A.C0006c
    public final void c(t4.e eVar) {
        super.c(eVar);
        C2730h c2730h = this.f24061f;
        if (c2730h == null) {
            return;
        }
        this.f24062g = this.f24063h;
        ?? r12 = c2730h.f24065a;
        int size = r12.size();
        for (int i7 = 0; i7 < size; i7++) {
            q qVar = (q) r12.get(i7);
            boolean z6 = qVar.d;
            long j3 = qVar.f24077a;
            boolean zD = eVar.d(j3);
            boolean z10 = this.f24063h;
            if ((!z6 && !zD) || (!z6 && !z10)) {
                h2.p pVar = this.c;
                int i10 = pVar.f15229a;
                int i11 = 0;
                while (true) {
                    if (i11 >= i10) {
                        break;
                    }
                    if (j3 == pVar.f15230b[i11]) {
                        pVar.f(i11);
                        break;
                    }
                    i11++;
                }
            }
        }
        this.f24063h = false;
        this.f24064i = AbstractC2737o.d(c2730h.d, 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [V.d] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [V.d] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r8v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final void f() {
        V.d dVar = this.f158a;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVar.l;
            int i10 = 0;
            do {
                ((C2729g) objArr[i10]).f();
                i10++;
            } while (i10 < i7);
        }
        ?? F9 = this.f24059b;
        ?? dVar2 = 0;
        while (F9 != 0) {
            if (F9 instanceof n0) {
                ((n0) F9).e0();
            } else if ((F9.f14686n & 16) != 0 && (F9 instanceof AbstractC0164n)) {
                g0.n nVar = ((AbstractC0164n) F9).f1690z;
                int i11 = 0;
                dVar2 = dVar2;
                F9 = F9;
                while (nVar != null) {
                    if ((nVar.f14686n & 16) != 0) {
                        i11++;
                        dVar2 = dVar2;
                        if (i11 == 1) {
                            F9 = nVar;
                        } else {
                            if (dVar2 == 0) {
                                dVar2 = new V.d(new g0.n[16]);
                            }
                            if (F9 != 0) {
                                dVar2.b(F9);
                                F9 = 0;
                            }
                            dVar2.b(nVar);
                        }
                    }
                    nVar = nVar.f14688q;
                    dVar2 = dVar2;
                    F9 = F9;
                }
                if (i11 == 1) {
                }
            }
            F9 = AbstractC0156f.f(dVar2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public final boolean g(t4.e eVar) {
        V.d dVar;
        int i7;
        C2472l c2472l = this.d;
        boolean z6 = false;
        int i10 = 0;
        z6 = false;
        if (!(c2472l.g() == 0)) {
            g0.n nVar = this.f24059b;
            if (nVar.f14695x) {
                C2730h c2730h = this.f24061f;
                A7.m.c(c2730h);
                d0 d0Var = this.f24060e;
                A7.m.c(d0Var);
                long j3 = d0Var.f872n;
                ?? F9 = nVar;
                ?? dVar2 = 0;
                while (F9 != 0) {
                    if (F9 instanceof n0) {
                        ((n0) F9).R(c2730h, EnumC2731i.f24068n, j3);
                    } else if ((F9.f14686n & 16) != 0 && (F9 instanceof AbstractC0164n)) {
                        g0.n nVar2 = ((AbstractC0164n) F9).f1690z;
                        int i11 = 0;
                        F9 = F9;
                        dVar2 = dVar2;
                        while (nVar2 != null) {
                            if ((nVar2.f14686n & 16) != 0) {
                                i11++;
                                dVar2 = dVar2;
                                if (i11 == 1) {
                                    F9 = nVar2;
                                } else {
                                    if (dVar2 == 0) {
                                        dVar2 = new V.d(new g0.n[16]);
                                    }
                                    if (F9 != 0) {
                                        dVar2.b(F9);
                                        F9 = 0;
                                    }
                                    dVar2.b(nVar2);
                                }
                            }
                            nVar2 = nVar2.f14688q;
                            F9 = F9;
                            dVar2 = dVar2;
                        }
                        if (i11 == 1) {
                        }
                    }
                    F9 = AbstractC0156f.f(dVar2);
                }
                if (nVar.f14695x && (i7 = (dVar = this.f158a).f9279n) > 0) {
                    Object[] objArr = dVar.l;
                    do {
                        ((C2729g) objArr[i10]).g(eVar);
                        i10++;
                    } while (i10 < i7);
                }
                z6 = true;
            }
        }
        c(eVar);
        c2472l.a();
        this.f24060e = null;
        return z6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r0v5, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [V.d] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8, types: [V.d] */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v10, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final boolean h(t4.e eVar, boolean z6) {
        V.d dVar;
        int i7;
        if (this.d.g() == 0) {
            return false;
        }
        ?? F9 = this.f24059b;
        if (!F9.f14695x) {
            return false;
        }
        C2730h c2730h = this.f24061f;
        A7.m.c(c2730h);
        d0 d0Var = this.f24060e;
        A7.m.c(d0Var);
        long j3 = d0Var.f872n;
        ?? F10 = F9;
        ?? dVar2 = 0;
        while (F10 != 0) {
            if (F10 instanceof n0) {
                ((n0) F10).R(c2730h, EnumC2731i.l, j3);
            } else if ((F10.f14686n & 16) != 0 && (F10 instanceof AbstractC0164n)) {
                g0.n nVar = ((AbstractC0164n) F10).f1690z;
                int i10 = 0;
                F10 = F10;
                dVar2 = dVar2;
                while (nVar != null) {
                    if ((nVar.f14686n & 16) != 0) {
                        i10++;
                        dVar2 = dVar2;
                        if (i10 == 1) {
                            F10 = nVar;
                        } else {
                            if (dVar2 == 0) {
                                dVar2 = new V.d(new g0.n[16]);
                            }
                            if (F10 != 0) {
                                dVar2.b(F10);
                                F10 = 0;
                            }
                            dVar2.b(nVar);
                        }
                    }
                    nVar = nVar.f14688q;
                    F10 = F10;
                    dVar2 = dVar2;
                }
                if (i10 == 1) {
                }
            }
            F10 = AbstractC0156f.f(dVar2);
        }
        if (F9.f14695x && (i7 = (dVar = this.f158a).f9279n) > 0) {
            Object[] objArr = dVar.l;
            int i11 = 0;
            do {
                C2729g c2729g = (C2729g) objArr[i11];
                A7.m.c(this.f24060e);
                c2729g.h(eVar, z6);
                i11++;
            } while (i11 < i7);
        }
        if (F9.f14695x) {
            ?? dVar3 = 0;
            while (F9 != 0) {
                if (F9 instanceof n0) {
                    ((n0) F9).R(c2730h, EnumC2731i.f24067m, j3);
                } else if ((F9.f14686n & 16) != 0 && (F9 instanceof AbstractC0164n)) {
                    g0.n nVar2 = ((AbstractC0164n) F9).f1690z;
                    int i12 = 0;
                    F9 = F9;
                    dVar3 = dVar3;
                    while (nVar2 != null) {
                        if ((nVar2.f14686n & 16) != 0) {
                            i12++;
                            dVar3 = dVar3;
                            if (i12 == 1) {
                                F9 = nVar2;
                            } else {
                                if (dVar3 == 0) {
                                    dVar3 = new V.d(new g0.n[16]);
                                }
                                if (F9 != 0) {
                                    dVar3.b(F9);
                                    F9 = 0;
                                }
                                dVar3.b(nVar2);
                            }
                        }
                        nVar2 = nVar2.f14688q;
                        F9 = F9;
                        dVar3 = dVar3;
                    }
                    if (i12 == 1) {
                    }
                }
                F9 = AbstractC0156f.f(dVar3);
            }
        }
        return true;
    }

    public final void i(long j3, C2482v c2482v) {
        h2.p pVar = this.c;
        int i7 = 0;
        if (pVar.d(j3) && c2482v.b(this) < 0) {
            int i10 = pVar.f15229a;
            int i11 = 0;
            while (true) {
                if (i11 >= i10) {
                    break;
                }
                if (j3 == pVar.f15230b[i11]) {
                    pVar.f(i11);
                    break;
                }
                i11++;
            }
            this.d.f(j3);
        }
        V.d dVar = this.f158a;
        int i12 = dVar.f9279n;
        if (i12 > 0) {
            Object[] objArr = dVar.l;
            do {
                ((C2729g) objArr[i7]).i(j3, c2482v);
                i7++;
            } while (i7 < i12);
        }
    }

    public final String toString() {
        return "Node(pointerInputFilter=" + this.f24059b + ", children=" + this.f158a + ", pointerIds=" + this.c + ')';
    }
}
