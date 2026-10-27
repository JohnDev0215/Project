package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import p.ty;
import p.xx;
import p.y11;

/* JADX INFO: loaded from: classes.dex */
public final class d extends f {
    public static final int[] k = new int[2];

    public d(ty tyVar) {
        super(tyVar);
        this.h.e = DependencyNode$Type.l;
        this.i.e = DependencyNode$Type.m;
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

    /* JADX WARN: Removed duplicated region for block: B:118:0x026f  */
    @Override // p.e90
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(p.e90 r24) {
        /*
            Method dump skipped, instruction units count: 920
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.d.a(p.e90):void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void d() {
        ty tyVar;
        ty tyVar2;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        ty tyVar3;
        ty tyVar4;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2;
        ty tyVar5 = this.b;
        boolean z = tyVar5.a;
        b bVar = this.e;
        if (z) {
            bVar.d(tyVar5.o());
        }
        boolean z2 = bVar.j;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.l;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.k;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.c;
        a aVar = this.i;
        a aVar2 = this.h;
        if (!z2) {
            ty tyVar6 = this.b;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour6 = tyVar6.S[0];
            this.d = constraintWidget$DimensionBehaviour6;
            if (constraintWidget$DimensionBehaviour6 != constraintWidget$DimensionBehaviour4) {
                if (constraintWidget$DimensionBehaviour6 == constraintWidget$DimensionBehaviour3 && (tyVar4 = tyVar6.T) != null && ((constraintWidget$DimensionBehaviour2 = tyVar4.S[0]) == constraintWidget$DimensionBehaviour5 || constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3)) {
                    int iO = (tyVar4.o() - this.b.H.d()) - this.b.J.d();
                    f.b(aVar2, tyVar4.d.h, this.b.H.d());
                    f.b(aVar, tyVar4.d.i, -this.b.J.d());
                    bVar.d(iO);
                    return;
                }
                if (constraintWidget$DimensionBehaviour6 == constraintWidget$DimensionBehaviour5) {
                    bVar.d(tyVar6.o());
                }
            }
        } else if (this.d == constraintWidget$DimensionBehaviour3 && (tyVar2 = (tyVar = this.b).T) != null && ((constraintWidget$DimensionBehaviour = tyVar2.S[0]) == constraintWidget$DimensionBehaviour5 || constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3)) {
            f.b(aVar2, tyVar2.d.h, tyVar.H.d());
            f.b(aVar, tyVar2.d.i, -this.b.J.d());
            return;
        }
        if (bVar.j) {
            ty tyVar7 = this.b;
            if (tyVar7.a) {
                xx[] xxVarArr = tyVar7.P;
                xx xxVar = xxVarArr[0];
                xx xxVar2 = xxVar.f;
                if (xxVar2 != null && xxVarArr[1].f != null) {
                    if (tyVar7.v()) {
                        aVar2.f = this.b.P[0].d();
                        aVar.f = -this.b.P[1].d();
                        return;
                    }
                    a aVarH = f.h(this.b.P[0]);
                    if (aVarH != null) {
                        f.b(aVar2, aVarH, this.b.P[0].d());
                    }
                    a aVarH2 = f.h(this.b.P[1]);
                    if (aVarH2 != null) {
                        f.b(aVar, aVarH2, -this.b.P[1].d());
                    }
                    aVar2.b = true;
                    aVar.b = true;
                    return;
                }
                if (xxVar2 != null) {
                    a aVarH3 = f.h(xxVar);
                    if (aVarH3 != null) {
                        f.b(aVar2, aVarH3, this.b.P[0].d());
                        f.b(aVar, aVar2, bVar.g);
                        return;
                    }
                    return;
                }
                xx xxVar3 = xxVarArr[1];
                if (xxVar3.f != null) {
                    a aVarH4 = f.h(xxVar3);
                    if (aVarH4 != null) {
                        f.b(aVar, aVarH4, -this.b.P[1].d());
                        f.b(aVar2, aVar, -bVar.g);
                        return;
                    }
                    return;
                }
                if ((tyVar7 instanceof y11) || tyVar7.T == null || tyVar7.g(ConstraintAnchor$Type.n).f != null) {
                    return;
                }
                ty tyVar8 = this.b;
                f.b(aVar2, tyVar8.T.d.h, tyVar8.p());
                f.b(aVar, aVar2, bVar.g);
                return;
            }
        }
        if (this.d == constraintWidget$DimensionBehaviour4) {
            ty tyVar9 = this.b;
            int i = tyVar9.r;
            if (i == 2) {
                ty tyVar10 = tyVar9.T;
                if (tyVar10 != null) {
                    b bVar2 = tyVar10.e.e;
                    bVar.l.add(bVar2);
                    bVar2.k.add(bVar);
                    bVar.b = true;
                    bVar.k.add(aVar2);
                    bVar.k.add(aVar);
                }
            } else if (i == 3) {
                if (tyVar9.s == 3) {
                    aVar2.a = this;
                    aVar.a = this;
                    e eVar = tyVar9.e;
                    eVar.h.a = this;
                    eVar.i.a = this;
                    bVar.a = this;
                    if (tyVar9.w()) {
                        bVar.l.add(this.b.e.e);
                        this.b.e.e.k.add(bVar);
                        e eVar2 = this.b.e;
                        eVar2.e.a = this;
                        bVar.l.add(eVar2.h);
                        bVar.l.add(this.b.e.i);
                        this.b.e.h.k.add(bVar);
                        this.b.e.i.k.add(bVar);
                    } else if (this.b.v()) {
                        this.b.e.e.l.add(bVar);
                        bVar.k.add(this.b.e.e);
                    } else {
                        this.b.e.e.l.add(bVar);
                    }
                } else {
                    b bVar3 = tyVar9.e.e;
                    bVar.l.add(bVar3);
                    bVar3.k.add(bVar);
                    this.b.e.h.k.add(bVar);
                    this.b.e.i.k.add(bVar);
                    bVar.b = true;
                    bVar.k.add(aVar2);
                    bVar.k.add(aVar);
                    aVar2.l.add(bVar);
                    aVar.l.add(bVar);
                }
            }
        }
        ty tyVar11 = this.b;
        xx[] xxVarArr2 = tyVar11.P;
        xx xxVar4 = xxVarArr2[0];
        xx xxVar5 = xxVar4.f;
        if (xxVar5 != null && xxVarArr2[1].f != null) {
            if (tyVar11.v()) {
                aVar2.f = this.b.P[0].d();
                aVar.f = -this.b.P[1].d();
                return;
            }
            a aVarH5 = f.h(this.b.P[0]);
            a aVarH6 = f.h(this.b.P[1]);
            if (aVarH5 != null) {
                aVarH5.b(this);
            }
            if (aVarH6 != null) {
                aVarH6.b(this);
            }
            this.j = WidgetRun$RunType.e;
            return;
        }
        if (xxVar5 != null) {
            a aVarH7 = f.h(xxVar4);
            if (aVarH7 != null) {
                f.b(aVar2, aVarH7, this.b.P[0].d());
                c(aVar, aVar2, 1, bVar);
                return;
            }
            return;
        }
        xx xxVar6 = xxVarArr2[1];
        if (xxVar6.f != null) {
            a aVarH8 = f.h(xxVar6);
            if (aVarH8 != null) {
                f.b(aVar, aVarH8, -this.b.P[1].d());
                c(aVar2, aVar, -1, bVar);
                return;
            }
            return;
        }
        if ((tyVar11 instanceof y11) || (tyVar3 = tyVar11.T) == null) {
            return;
        }
        f.b(aVar2, tyVar3.d.h, tyVar11.p());
        c(aVar, aVar2, 1, bVar);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void e() {
        a aVar = this.h;
        if (aVar.j) {
            this.b.Y = aVar.g;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final boolean k() {
        return this.d != ConstraintWidget$DimensionBehaviour.k || this.b.r == 0;
    }

    public final void n() {
        this.g = false;
        a aVar = this.h;
        aVar.c();
        aVar.j = false;
        a aVar2 = this.i;
        aVar2.c();
        aVar2.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.b.h0;
    }
}
