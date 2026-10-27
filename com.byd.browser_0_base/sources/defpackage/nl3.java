package defpackage;

import defpackage.nx;
import defpackage.qx;
import java.util.ArrayList;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public final class nl3 extends gt3 {
    public final o80 k;
    public xd l;

    public nl3(qx qxVar) {
        super(qxVar);
        o80 o80Var = new o80(this);
        this.k = o80Var;
        this.l = null;
        this.h.e = 6;
        this.i.e = 7;
        o80Var.e = 8;
        this.f = 1;
    }

    @Override // defpackage.gt3, defpackage.m80
    public final void a(m80 m80Var) {
        float f;
        float f2;
        float f3;
        int i;
        if (gq2.m(this.j) == 3) {
            qx qxVar = this.b;
            l(qxVar.L, qxVar.N, 1);
            return;
        }
        ca0 ca0Var = this.e;
        boolean z = ca0Var.c;
        qx.a aVar = qx.a.MATCH_CONSTRAINT;
        if (z && !ca0Var.j && this.d == aVar) {
            qx qxVar2 = this.b;
            int i2 = qxVar2.t;
            if (i2 == 2) {
                qx qxVar3 = qxVar2.W;
                if (qxVar3 != null) {
                    if (qxVar3.e.e.j) {
                        ca0Var.d((int) ((r1.g * qxVar2.A) + 0.5f));
                    }
                }
            } else if (i2 == 3) {
                ca0 ca0Var2 = qxVar2.d.e;
                if (ca0Var2.j) {
                    int i3 = qxVar2.a0;
                    if (i3 == -1) {
                        f = ca0Var2.g;
                        f2 = qxVar2.Z;
                    } else if (i3 == 0) {
                        f3 = ca0Var2.g * qxVar2.Z;
                        i = (int) (f3 + 0.5f);
                        ca0Var.d(i);
                    } else if (i3 != 1) {
                        i = 0;
                        ca0Var.d(i);
                    } else {
                        f = ca0Var2.g;
                        f2 = qxVar2.Z;
                    }
                    f3 = f / f2;
                    i = (int) (f3 + 0.5f);
                    ca0Var.d(i);
                }
            }
        }
        o80 o80Var = this.h;
        if (o80Var.c) {
            o80 o80Var2 = this.i;
            if (o80Var2.c) {
                if (o80Var.j && o80Var2.j && ca0Var.j) {
                    return;
                }
                boolean z2 = ca0Var.j;
                ArrayList arrayList = o80Var.l;
                ArrayList arrayList2 = o80Var2.l;
                if (!z2 && this.d == aVar) {
                    qx qxVar4 = this.b;
                    if (qxVar4.s == 0 && !qxVar4.C()) {
                        o80 o80Var3 = (o80) arrayList.get(0);
                        o80 o80Var4 = (o80) arrayList2.get(0);
                        int i4 = o80Var3.g + o80Var.f;
                        int i5 = o80Var4.g + o80Var2.f;
                        o80Var.d(i4);
                        o80Var2.d(i5);
                        ca0Var.d(i5 - i4);
                        return;
                    }
                }
                if (!ca0Var.j && this.d == aVar && this.a == 1 && arrayList.size() > 0 && arrayList2.size() > 0) {
                    o80 o80Var5 = (o80) arrayList.get(0);
                    int i6 = (((o80) arrayList2.get(0)).g + o80Var2.f) - (o80Var5.g + o80Var.f);
                    int i7 = ca0Var.m;
                    if (i6 < i7) {
                        ca0Var.d(i6);
                    } else {
                        ca0Var.d(i7);
                    }
                }
                if (ca0Var.j && arrayList.size() > 0 && arrayList2.size() > 0) {
                    o80 o80Var6 = (o80) arrayList.get(0);
                    o80 o80Var7 = (o80) arrayList2.get(0);
                    int i8 = o80Var6.g;
                    int i9 = o80Var.f + i8;
                    int i10 = o80Var7.g;
                    int i11 = o80Var2.f + i10;
                    float f4 = this.b.h0;
                    if (o80Var6 == o80Var7) {
                        f4 = 0.5f;
                    } else {
                        i8 = i9;
                        i10 = i11;
                    }
                    o80Var.d((int) ((((i10 - i8) - ca0Var.g) * f4) + i8 + 0.5f));
                    o80Var2.d(o80Var.g + ca0Var.g);
                }
            }
        }
    }

    @Override // defpackage.gt3
    public final void d() {
        qx qxVar;
        qx qxVar2;
        qx qxVar3;
        qx qxVar4;
        qx qxVar5 = this.b;
        boolean z = qxVar5.a;
        ca0 ca0Var = this.e;
        if (z) {
            ca0Var.d(qxVar5.o());
        }
        boolean z2 = ca0Var.j;
        qx.a aVar = qx.a.MATCH_PARENT;
        qx.a aVar2 = qx.a.FIXED;
        qx.a aVar3 = qx.a.MATCH_CONSTRAINT;
        o80 o80Var = this.i;
        o80 o80Var2 = this.h;
        if (!z2) {
            qx qxVar6 = this.b;
            this.d = qxVar6.V[1];
            if (qxVar6.F) {
                this.l = new xd(this);
            }
            qx.a aVar4 = this.d;
            if (aVar4 != aVar3) {
                if (aVar4 == aVar && (qxVar4 = this.b.W) != null && qxVar4.V[1] == aVar2) {
                    int iO = (qxVar4.o() - this.b.L.e()) - this.b.N.e();
                    gt3.b(o80Var2, qxVar4.e.h, this.b.L.e());
                    gt3.b(o80Var, qxVar4.e.i, -this.b.N.e());
                    ca0Var.d(iO);
                    return;
                }
                if (aVar4 == aVar2) {
                    ca0Var.d(this.b.o());
                }
            }
        } else if (this.d == aVar && (qxVar2 = (qxVar = this.b).W) != null && qxVar2.V[1] == aVar2) {
            gt3.b(o80Var2, qxVar2.e.h, qxVar.L.e());
            gt3.b(o80Var, qxVar2.e.i, -this.b.N.e());
            return;
        }
        boolean z3 = ca0Var.j;
        o80 o80Var3 = this.k;
        if (z3) {
            qx qxVar7 = this.b;
            if (qxVar7.a) {
                nx[] nxVarArr = qxVar7.S;
                nx nxVar = nxVarArr[2];
                nx nxVar2 = nxVar.f;
                if (nxVar2 != null && nxVarArr[3].f != null) {
                    if (qxVar7.C()) {
                        o80Var2.f = this.b.S[2].e();
                        o80Var.f = -this.b.S[3].e();
                    } else {
                        o80 o80VarH = gt3.h(this.b.S[2]);
                        if (o80VarH != null) {
                            gt3.b(o80Var2, o80VarH, this.b.S[2].e());
                        }
                        o80 o80VarH2 = gt3.h(this.b.S[3]);
                        if (o80VarH2 != null) {
                            gt3.b(o80Var, o80VarH2, -this.b.S[3].e());
                        }
                        o80Var2.b = true;
                        o80Var.b = true;
                    }
                    qx qxVar8 = this.b;
                    if (qxVar8.F) {
                        gt3.b(o80Var3, o80Var2, qxVar8.d0);
                        return;
                    }
                    return;
                }
                if (nxVar2 != null) {
                    o80 o80VarH3 = gt3.h(nxVar);
                    if (o80VarH3 != null) {
                        gt3.b(o80Var2, o80VarH3, this.b.S[2].e());
                        gt3.b(o80Var, o80Var2, ca0Var.g);
                        qx qxVar9 = this.b;
                        if (qxVar9.F) {
                            gt3.b(o80Var3, o80Var2, qxVar9.d0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                nx nxVar3 = nxVarArr[3];
                if (nxVar3.f != null) {
                    o80 o80VarH4 = gt3.h(nxVar3);
                    if (o80VarH4 != null) {
                        gt3.b(o80Var, o80VarH4, -this.b.S[3].e());
                        gt3.b(o80Var2, o80Var, -ca0Var.g);
                    }
                    qx qxVar10 = this.b;
                    if (qxVar10.F) {
                        gt3.b(o80Var3, o80Var2, qxVar10.d0);
                        return;
                    }
                    return;
                }
                nx nxVar4 = nxVarArr[4];
                if (nxVar4.f != null) {
                    o80 o80VarH5 = gt3.h(nxVar4);
                    if (o80VarH5 != null) {
                        gt3.b(o80Var3, o80VarH5, 0);
                        gt3.b(o80Var2, o80Var3, -this.b.d0);
                        gt3.b(o80Var, o80Var2, ca0Var.g);
                        return;
                    }
                    return;
                }
                if ((qxVar7 instanceof iu0) || qxVar7.W == null || qxVar7.m(nx.a.CENTER).f != null) {
                    return;
                }
                qx qxVar11 = this.b;
                gt3.b(o80Var2, qxVar11.W.e.h, qxVar11.w());
                gt3.b(o80Var, o80Var2, ca0Var.g);
                qx qxVar12 = this.b;
                if (qxVar12.F) {
                    gt3.b(o80Var3, o80Var2, qxVar12.d0);
                    return;
                }
                return;
            }
        }
        ArrayList arrayList = ca0Var.l;
        if (z3 || this.d != aVar3) {
            ca0Var.b(this);
        } else {
            qx qxVar13 = this.b;
            int i = qxVar13.t;
            ArrayList arrayList2 = ca0Var.k;
            if (i == 2) {
                qx qxVar14 = qxVar13.W;
                if (qxVar14 != null) {
                    ca0 ca0Var2 = qxVar14.e.e;
                    arrayList.add(ca0Var2);
                    ca0Var2.k.add(ca0Var);
                    ca0Var.b = true;
                    arrayList2.add(o80Var2);
                    arrayList2.add(o80Var);
                }
            } else if (i == 3 && !qxVar13.C()) {
                qx qxVar15 = this.b;
                if (qxVar15.s != 3) {
                    ca0 ca0Var3 = qxVar15.d.e;
                    arrayList.add(ca0Var3);
                    ca0Var3.k.add(ca0Var);
                    ca0Var.b = true;
                    arrayList2.add(o80Var2);
                    arrayList2.add(o80Var);
                }
            }
        }
        qx qxVar16 = this.b;
        nx[] nxVarArr2 = qxVar16.S;
        nx nxVar5 = nxVarArr2[2];
        nx nxVar6 = nxVar5.f;
        if (nxVar6 != null && nxVarArr2[3].f != null) {
            if (qxVar16.C()) {
                o80Var2.f = this.b.S[2].e();
                o80Var.f = -this.b.S[3].e();
            } else {
                o80 o80VarH6 = gt3.h(this.b.S[2]);
                o80 o80VarH7 = gt3.h(this.b.S[3]);
                if (o80VarH6 != null) {
                    o80VarH6.b(this);
                }
                if (o80VarH7 != null) {
                    o80VarH7.b(this);
                }
                this.j = 4;
            }
            if (this.b.F) {
                c(o80Var3, o80Var2, 1, this.l);
            }
        } else if (nxVar6 != null) {
            o80 o80VarH8 = gt3.h(nxVar5);
            if (o80VarH8 != null) {
                gt3.b(o80Var2, o80VarH8, this.b.S[2].e());
                c(o80Var, o80Var2, 1, ca0Var);
                if (this.b.F) {
                    c(o80Var3, o80Var2, 1, this.l);
                }
                if (this.d == aVar3) {
                    qx qxVar17 = this.b;
                    if (qxVar17.Z > 0.0f) {
                        tv0 tv0Var = qxVar17.d;
                        if (tv0Var.d == aVar3) {
                            tv0Var.e.k.add(ca0Var);
                            arrayList.add(this.b.d.e);
                            ca0Var.a = this;
                        }
                    }
                }
            }
        } else {
            nx nxVar7 = nxVarArr2[3];
            if (nxVar7.f != null) {
                o80 o80VarH9 = gt3.h(nxVar7);
                if (o80VarH9 != null) {
                    gt3.b(o80Var, o80VarH9, -this.b.S[3].e());
                    c(o80Var2, o80Var, -1, ca0Var);
                    if (this.b.F) {
                        c(o80Var3, o80Var2, 1, this.l);
                    }
                }
            } else {
                nx nxVar8 = nxVarArr2[4];
                if (nxVar8.f != null) {
                    o80 o80VarH10 = gt3.h(nxVar8);
                    if (o80VarH10 != null) {
                        gt3.b(o80Var3, o80VarH10, 0);
                        c(o80Var2, o80Var3, -1, this.l);
                        c(o80Var, o80Var2, 1, ca0Var);
                    }
                } else if (!(qxVar16 instanceof iu0) && (qxVar3 = qxVar16.W) != null) {
                    gt3.b(o80Var2, qxVar3.e.h, qxVar16.w());
                    c(o80Var, o80Var2, 1, ca0Var);
                    if (this.b.F) {
                        c(o80Var3, o80Var2, 1, this.l);
                    }
                    if (this.d == aVar3) {
                        qx qxVar18 = this.b;
                        if (qxVar18.Z > 0.0f) {
                            tv0 tv0Var2 = qxVar18.d;
                            if (tv0Var2.d == aVar3) {
                                tv0Var2.e.k.add(ca0Var);
                                arrayList.add(this.b.d.e);
                                ca0Var.a = this;
                            }
                        }
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            ca0Var.c = true;
        }
    }

    @Override // defpackage.gt3
    public final void e() {
        o80 o80Var = this.h;
        if (o80Var.j) {
            this.b.c0 = o80Var.g;
        }
    }

    @Override // defpackage.gt3
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.gt3
    public final boolean k() {
        return this.d != qx.a.MATCH_CONSTRAINT || this.b.t == 0;
    }

    public final void m() {
        this.g = false;
        o80 o80Var = this.h;
        o80Var.c();
        o80Var.j = false;
        o80 o80Var2 = this.i;
        o80Var2.c();
        o80Var2.j = false;
        o80 o80Var3 = this.k;
        o80Var3.c();
        o80Var3.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.b.l0;
    }
}
