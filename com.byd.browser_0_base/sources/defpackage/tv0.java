package defpackage;

import defpackage.nx;
import defpackage.qx;
import java.util.ArrayList;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public final class tv0 extends gt3 {
    public static final int[] k = new int[2];

    public tv0(qx qxVar) {
        super(qxVar);
        this.h.e = 4;
        this.i.e = 5;
        this.f = 0;
    }

    public static void m(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
        if (i8 <= i6) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else if (i9 <= i7) {
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x026e  */
    @Override // defpackage.gt3, defpackage.m80
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(defpackage.m80 r24) {
        /*
            Method dump skipped, instruction units count: 907
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tv0.a(m80):void");
    }

    @Override // defpackage.gt3
    public final void d() {
        qx qxVar;
        qx qxVar2;
        qx.a aVar;
        qx qxVar3;
        qx qxVar4;
        qx.a aVar2;
        qx qxVar5 = this.b;
        boolean z = qxVar5.a;
        ca0 ca0Var = this.e;
        if (z) {
            ca0Var.d(qxVar5.u());
        }
        boolean z2 = ca0Var.j;
        qx.a aVar3 = qx.a.MATCH_CONSTRAINT;
        qx.a aVar4 = qx.a.MATCH_PARENT;
        qx.a aVar5 = qx.a.FIXED;
        o80 o80Var = this.i;
        o80 o80Var2 = this.h;
        if (!z2) {
            qx qxVar6 = this.b;
            qx.a aVar6 = qxVar6.V[0];
            this.d = aVar6;
            if (aVar6 != aVar3) {
                if (aVar6 == aVar4 && (qxVar4 = qxVar6.W) != null && ((aVar2 = qxVar4.V[0]) == aVar5 || aVar2 == aVar4)) {
                    int iU = (qxVar4.u() - this.b.K.e()) - this.b.M.e();
                    gt3.b(o80Var2, qxVar4.d.h, this.b.K.e());
                    gt3.b(o80Var, qxVar4.d.i, -this.b.M.e());
                    ca0Var.d(iU);
                    return;
                }
                if (aVar6 == aVar5) {
                    ca0Var.d(qxVar6.u());
                }
            }
        } else if (this.d == aVar4 && (qxVar2 = (qxVar = this.b).W) != null && ((aVar = qxVar2.V[0]) == aVar5 || aVar == aVar4)) {
            gt3.b(o80Var2, qxVar2.d.h, qxVar.K.e());
            gt3.b(o80Var, qxVar2.d.i, -this.b.M.e());
            return;
        }
        if (ca0Var.j) {
            qx qxVar7 = this.b;
            if (qxVar7.a) {
                nx[] nxVarArr = qxVar7.S;
                nx nxVar = nxVarArr[0];
                nx nxVar2 = nxVar.f;
                if (nxVar2 != null && nxVarArr[1].f != null) {
                    if (qxVar7.B()) {
                        o80Var2.f = this.b.S[0].e();
                        o80Var.f = -this.b.S[1].e();
                        return;
                    }
                    o80 o80VarH = gt3.h(this.b.S[0]);
                    if (o80VarH != null) {
                        gt3.b(o80Var2, o80VarH, this.b.S[0].e());
                    }
                    o80 o80VarH2 = gt3.h(this.b.S[1]);
                    if (o80VarH2 != null) {
                        gt3.b(o80Var, o80VarH2, -this.b.S[1].e());
                    }
                    o80Var2.b = true;
                    o80Var.b = true;
                    return;
                }
                if (nxVar2 != null) {
                    o80 o80VarH3 = gt3.h(nxVar);
                    if (o80VarH3 != null) {
                        gt3.b(o80Var2, o80VarH3, this.b.S[0].e());
                        gt3.b(o80Var, o80Var2, ca0Var.g);
                        return;
                    }
                    return;
                }
                nx nxVar3 = nxVarArr[1];
                if (nxVar3.f != null) {
                    o80 o80VarH4 = gt3.h(nxVar3);
                    if (o80VarH4 != null) {
                        gt3.b(o80Var, o80VarH4, -this.b.S[1].e());
                        gt3.b(o80Var2, o80Var, -ca0Var.g);
                        return;
                    }
                    return;
                }
                if ((qxVar7 instanceof iu0) || qxVar7.W == null || qxVar7.m(nx.a.CENTER).f != null) {
                    return;
                }
                qx qxVar8 = this.b;
                gt3.b(o80Var2, qxVar8.W.d.h, qxVar8.v());
                gt3.b(o80Var, o80Var2, ca0Var.g);
                return;
            }
        }
        if (this.d == aVar3) {
            qx qxVar9 = this.b;
            int i = qxVar9.s;
            ArrayList arrayList = ca0Var.k;
            ArrayList arrayList2 = ca0Var.l;
            if (i == 2) {
                qx qxVar10 = qxVar9.W;
                if (qxVar10 != null) {
                    ca0 ca0Var2 = qxVar10.e.e;
                    arrayList2.add(ca0Var2);
                    ca0Var2.k.add(ca0Var);
                    ca0Var.b = true;
                    arrayList.add(o80Var2);
                    arrayList.add(o80Var);
                }
            } else if (i == 3) {
                if (qxVar9.t == 3) {
                    o80Var2.a = this;
                    o80Var.a = this;
                    nl3 nl3Var = qxVar9.e;
                    nl3Var.h.a = this;
                    nl3Var.i.a = this;
                    ca0Var.a = this;
                    if (qxVar9.C()) {
                        arrayList2.add(this.b.e.e);
                        this.b.e.e.k.add(ca0Var);
                        nl3 nl3Var2 = this.b.e;
                        nl3Var2.e.a = this;
                        arrayList2.add(nl3Var2.h);
                        arrayList2.add(this.b.e.i);
                        this.b.e.h.k.add(ca0Var);
                        this.b.e.i.k.add(ca0Var);
                    } else if (this.b.B()) {
                        this.b.e.e.l.add(ca0Var);
                        arrayList.add(this.b.e.e);
                    } else {
                        this.b.e.e.l.add(ca0Var);
                    }
                } else {
                    ca0 ca0Var3 = qxVar9.e.e;
                    arrayList2.add(ca0Var3);
                    ca0Var3.k.add(ca0Var);
                    this.b.e.h.k.add(ca0Var);
                    this.b.e.i.k.add(ca0Var);
                    ca0Var.b = true;
                    arrayList.add(o80Var2);
                    arrayList.add(o80Var);
                    o80Var2.l.add(ca0Var);
                    o80Var.l.add(ca0Var);
                }
            }
        }
        qx qxVar11 = this.b;
        nx[] nxVarArr2 = qxVar11.S;
        nx nxVar4 = nxVarArr2[0];
        nx nxVar5 = nxVar4.f;
        if (nxVar5 != null && nxVarArr2[1].f != null) {
            if (qxVar11.B()) {
                o80Var2.f = this.b.S[0].e();
                o80Var.f = -this.b.S[1].e();
                return;
            }
            o80 o80VarH5 = gt3.h(this.b.S[0]);
            o80 o80VarH6 = gt3.h(this.b.S[1]);
            if (o80VarH5 != null) {
                o80VarH5.b(this);
            }
            if (o80VarH6 != null) {
                o80VarH6.b(this);
            }
            this.j = 4;
            return;
        }
        if (nxVar5 != null) {
            o80 o80VarH7 = gt3.h(nxVar4);
            if (o80VarH7 != null) {
                gt3.b(o80Var2, o80VarH7, this.b.S[0].e());
                c(o80Var, o80Var2, 1, ca0Var);
                return;
            }
            return;
        }
        nx nxVar6 = nxVarArr2[1];
        if (nxVar6.f != null) {
            o80 o80VarH8 = gt3.h(nxVar6);
            if (o80VarH8 != null) {
                gt3.b(o80Var, o80VarH8, -this.b.S[1].e());
                c(o80Var2, o80Var, -1, ca0Var);
                return;
            }
            return;
        }
        if ((qxVar11 instanceof iu0) || (qxVar3 = qxVar11.W) == null) {
            return;
        }
        gt3.b(o80Var2, qxVar3.d.h, qxVar11.v());
        c(o80Var, o80Var2, 1, ca0Var);
    }

    @Override // defpackage.gt3
    public final void e() {
        o80 o80Var = this.h;
        if (o80Var.j) {
            this.b.b0 = o80Var.g;
        }
    }

    @Override // defpackage.gt3
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.gt3
    public final boolean k() {
        return this.d != qx.a.MATCH_CONSTRAINT || this.b.s == 0;
    }

    public final void n() {
        this.g = false;
        o80 o80Var = this.h;
        o80Var.c();
        o80Var.j = false;
        o80 o80Var2 = this.i;
        o80Var2.c();
        o80Var2.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.b.l0;
    }
}
