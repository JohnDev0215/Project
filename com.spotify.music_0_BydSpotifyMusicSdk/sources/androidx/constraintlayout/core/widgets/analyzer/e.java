package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import p.e90;
import p.ll;
import p.ty;
import p.xx;
import p.y11;

/* JADX INFO: loaded from: classes.dex */
public final class e extends f {
    public final a k;
    public ll l;

    public e(ty tyVar) {
        super(tyVar);
        a aVar = new a(this);
        this.k = aVar;
        this.l = null;
        this.h.e = DependencyNode$Type.n;
        this.i.e = DependencyNode$Type.o;
        aVar.e = DependencyNode$Type.f13p;
        this.f = 1;
    }

    @Override // p.e90
    public final void a(e90 e90Var) {
        float f;
        float f2;
        float f3;
        int i;
        if (this.j.ordinal() == 3) {
            ty tyVar = this.b;
            l(tyVar.I, tyVar.K, 1);
            return;
        }
        b bVar = this.e;
        boolean z = bVar.c;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.k;
        if (z && !bVar.j && this.d == constraintWidget$DimensionBehaviour) {
            ty tyVar2 = this.b;
            int i2 = tyVar2.s;
            if (i2 == 2) {
                ty tyVar3 = tyVar2.T;
                if (tyVar3 != null) {
                    if (tyVar3.e.e.j) {
                        bVar.d((int) ((r1.g * tyVar2.z) + 0.5f));
                    }
                }
            } else if (i2 == 3) {
                b bVar2 = tyVar2.d.e;
                if (bVar2.j) {
                    int i3 = tyVar2.X;
                    if (i3 == -1) {
                        f = bVar2.g;
                        f2 = tyVar2.W;
                    } else if (i3 == 0) {
                        f3 = bVar2.g * tyVar2.W;
                        i = (int) (f3 + 0.5f);
                        bVar.d(i);
                    } else if (i3 != 1) {
                        i = 0;
                        bVar.d(i);
                    } else {
                        f = bVar2.g;
                        f2 = tyVar2.W;
                    }
                    f3 = f / f2;
                    i = (int) (f3 + 0.5f);
                    bVar.d(i);
                }
            }
        }
        a aVar = this.h;
        if (aVar.c) {
            a aVar2 = this.i;
            if (aVar2.c) {
                if (aVar.j && aVar2.j && bVar.j) {
                    return;
                }
                if (!bVar.j && this.d == constraintWidget$DimensionBehaviour) {
                    ty tyVar4 = this.b;
                    if (tyVar4.r == 0 && !tyVar4.w()) {
                        a aVar3 = (a) aVar.l.get(0);
                        a aVar4 = (a) aVar2.l.get(0);
                        int i4 = aVar3.g + aVar.f;
                        int i5 = aVar4.g + aVar2.f;
                        aVar.d(i4);
                        aVar2.d(i5);
                        bVar.d(i5 - i4);
                        return;
                    }
                }
                if (!bVar.j && this.d == constraintWidget$DimensionBehaviour && this.a == 1 && aVar.l.size() > 0 && aVar2.l.size() > 0) {
                    a aVar5 = (a) aVar.l.get(0);
                    int i6 = (((a) aVar2.l.get(0)).g + aVar2.f) - (aVar5.g + aVar.f);
                    int i7 = bVar.m;
                    if (i6 < i7) {
                        bVar.d(i6);
                    } else {
                        bVar.d(i7);
                    }
                }
                if (bVar.j && aVar.l.size() > 0 && aVar2.l.size() > 0) {
                    a aVar6 = (a) aVar.l.get(0);
                    a aVar7 = (a) aVar2.l.get(0);
                    int i8 = aVar6.g;
                    int i9 = aVar.f + i8;
                    int i10 = aVar7.g;
                    int i11 = aVar2.f + i10;
                    float f4 = this.b.e0;
                    if (aVar6 == aVar7) {
                        f4 = 0.5f;
                    } else {
                        i8 = i9;
                        i10 = i11;
                    }
                    aVar.d((int) ((((i10 - i8) - bVar.g) * f4) + i8 + 0.5f));
                    aVar2.d(aVar.g + bVar.g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void d() {
        ty tyVar;
        ty tyVar2;
        ty tyVar3;
        ty tyVar4;
        ty tyVar5 = this.b;
        boolean z = tyVar5.a;
        b bVar = this.e;
        if (z) {
            bVar.d(tyVar5.i());
        }
        boolean z2 = bVar.j;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.l;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.c;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.k;
        a aVar = this.i;
        a aVar2 = this.h;
        if (!z2) {
            ty tyVar6 = this.b;
            this.d = tyVar6.S[1];
            if (tyVar6.E) {
                this.l = new ll(this);
            }
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = this.d;
            if (constraintWidget$DimensionBehaviour4 != constraintWidget$DimensionBehaviour3) {
                if (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour && (tyVar4 = this.b.T) != null && tyVar4.S[1] == constraintWidget$DimensionBehaviour2) {
                    int i = (tyVar4.i() - this.b.I.d()) - this.b.K.d();
                    f.b(aVar2, tyVar4.e.h, this.b.I.d());
                    f.b(aVar, tyVar4.e.i, -this.b.K.d());
                    bVar.d(i);
                    return;
                }
                if (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour2) {
                    bVar.d(this.b.i());
                }
            }
        } else if (this.d == constraintWidget$DimensionBehaviour && (tyVar2 = (tyVar = this.b).T) != null && tyVar2.S[1] == constraintWidget$DimensionBehaviour2) {
            f.b(aVar2, tyVar2.e.h, tyVar.I.d());
            f.b(aVar, tyVar2.e.i, -this.b.K.d());
            return;
        }
        boolean z3 = bVar.j;
        a aVar3 = this.k;
        if (z3) {
            ty tyVar7 = this.b;
            if (tyVar7.a) {
                xx[] xxVarArr = tyVar7.P;
                xx xxVar = xxVarArr[2];
                xx xxVar2 = xxVar.f;
                if (xxVar2 != null && xxVarArr[3].f != null) {
                    if (tyVar7.w()) {
                        aVar2.f = this.b.P[2].d();
                        aVar.f = -this.b.P[3].d();
                    } else {
                        a aVarH = f.h(this.b.P[2]);
                        if (aVarH != null) {
                            f.b(aVar2, aVarH, this.b.P[2].d());
                        }
                        a aVarH2 = f.h(this.b.P[3]);
                        if (aVarH2 != null) {
                            f.b(aVar, aVarH2, -this.b.P[3].d());
                        }
                        aVar2.b = true;
                        aVar.b = true;
                    }
                    ty tyVar8 = this.b;
                    if (tyVar8.E) {
                        f.b(aVar3, aVar2, tyVar8.a0);
                        return;
                    }
                    return;
                }
                if (xxVar2 != null) {
                    a aVarH3 = f.h(xxVar);
                    if (aVarH3 != null) {
                        f.b(aVar2, aVarH3, this.b.P[2].d());
                        f.b(aVar, aVar2, bVar.g);
                        ty tyVar9 = this.b;
                        if (tyVar9.E) {
                            f.b(aVar3, aVar2, tyVar9.a0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                xx xxVar3 = xxVarArr[3];
                if (xxVar3.f != null) {
                    a aVarH4 = f.h(xxVar3);
                    if (aVarH4 != null) {
                        f.b(aVar, aVarH4, -this.b.P[3].d());
                        f.b(aVar2, aVar, -bVar.g);
                    }
                    ty tyVar10 = this.b;
                    if (tyVar10.E) {
                        f.b(aVar3, aVar2, tyVar10.a0);
                        return;
                    }
                    return;
                }
                xx xxVar4 = xxVarArr[4];
                if (xxVar4.f != null) {
                    a aVarH5 = f.h(xxVar4);
                    if (aVarH5 != null) {
                        f.b(aVar3, aVarH5, 0);
                        f.b(aVar2, aVar3, -this.b.a0);
                        f.b(aVar, aVar2, bVar.g);
                        return;
                    }
                    return;
                }
                if ((tyVar7 instanceof y11) || tyVar7.T == null || tyVar7.g(ConstraintAnchor$Type.n).f != null) {
                    return;
                }
                ty tyVar11 = this.b;
                f.b(aVar2, tyVar11.T.e.h, tyVar11.q());
                f.b(aVar, aVar2, bVar.g);
                ty tyVar12 = this.b;
                if (tyVar12.E) {
                    f.b(aVar3, aVar2, tyVar12.a0);
                    return;
                }
                return;
            }
        }
        if (z3 || this.d != constraintWidget$DimensionBehaviour3) {
            bVar.b(this);
        } else {
            ty tyVar13 = this.b;
            int i2 = tyVar13.s;
            if (i2 == 2) {
                ty tyVar14 = tyVar13.T;
                if (tyVar14 != null) {
                    b bVar2 = tyVar14.e.e;
                    bVar.l.add(bVar2);
                    bVar2.k.add(bVar);
                    bVar.b = true;
                    bVar.k.add(aVar2);
                    bVar.k.add(aVar);
                }
            } else if (i2 == 3 && !tyVar13.w()) {
                ty tyVar15 = this.b;
                if (tyVar15.r != 3) {
                    b bVar3 = tyVar15.d.e;
                    bVar.l.add(bVar3);
                    bVar3.k.add(bVar);
                    bVar.b = true;
                    bVar.k.add(aVar2);
                    bVar.k.add(aVar);
                }
            }
        }
        ty tyVar16 = this.b;
        xx[] xxVarArr2 = tyVar16.P;
        xx xxVar5 = xxVarArr2[2];
        xx xxVar6 = xxVar5.f;
        if (xxVar6 != null && xxVarArr2[3].f != null) {
            if (tyVar16.w()) {
                aVar2.f = this.b.P[2].d();
                aVar.f = -this.b.P[3].d();
            } else {
                a aVarH6 = f.h(this.b.P[2]);
                a aVarH7 = f.h(this.b.P[3]);
                if (aVarH6 != null) {
                    aVarH6.b(this);
                }
                if (aVarH7 != null) {
                    aVarH7.b(this);
                }
                this.j = WidgetRun$RunType.e;
            }
            if (this.b.E) {
                c(aVar3, aVar2, 1, this.l);
            }
        } else if (xxVar6 != null) {
            a aVarH8 = f.h(xxVar5);
            if (aVarH8 != null) {
                f.b(aVar2, aVarH8, this.b.P[2].d());
                c(aVar, aVar2, 1, bVar);
                if (this.b.E) {
                    c(aVar3, aVar2, 1, this.l);
                }
                if (this.d == constraintWidget$DimensionBehaviour3) {
                    ty tyVar17 = this.b;
                    if (tyVar17.W > 0.0f) {
                        d dVar = tyVar17.d;
                        if (dVar.d == constraintWidget$DimensionBehaviour3) {
                            dVar.e.k.add(bVar);
                            bVar.l.add(this.b.d.e);
                            bVar.a = this;
                        }
                    }
                }
            }
        } else {
            xx xxVar7 = xxVarArr2[3];
            if (xxVar7.f != null) {
                a aVarH9 = f.h(xxVar7);
                if (aVarH9 != null) {
                    f.b(aVar, aVarH9, -this.b.P[3].d());
                    c(aVar2, aVar, -1, bVar);
                    if (this.b.E) {
                        c(aVar3, aVar2, 1, this.l);
                    }
                }
            } else {
                xx xxVar8 = xxVarArr2[4];
                if (xxVar8.f != null) {
                    a aVarH10 = f.h(xxVar8);
                    if (aVarH10 != null) {
                        f.b(aVar3, aVarH10, 0);
                        c(aVar2, aVar3, -1, this.l);
                        c(aVar, aVar2, 1, bVar);
                    }
                } else if (!(tyVar16 instanceof y11) && (tyVar3 = tyVar16.T) != null) {
                    f.b(aVar2, tyVar3.e.h, tyVar16.q());
                    c(aVar, aVar2, 1, bVar);
                    if (this.b.E) {
                        c(aVar3, aVar2, 1, this.l);
                    }
                    if (this.d == constraintWidget$DimensionBehaviour3) {
                        ty tyVar18 = this.b;
                        if (tyVar18.W > 0.0f) {
                            d dVar2 = tyVar18.d;
                            if (dVar2.d == constraintWidget$DimensionBehaviour3) {
                                dVar2.e.k.add(bVar);
                                bVar.l.add(this.b.d.e);
                                bVar.a = this;
                            }
                        }
                    }
                }
            }
        }
        if (bVar.l.size() == 0) {
            bVar.c = true;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void e() {
        a aVar = this.h;
        if (aVar.j) {
            this.b.Z = aVar.g;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final boolean k() {
        return this.d != ConstraintWidget$DimensionBehaviour.k || this.b.s == 0;
    }

    public final void m() {
        this.g = false;
        a aVar = this.h;
        aVar.c();
        aVar.j = false;
        a aVar2 = this.i;
        aVar2.c();
        aVar2.j = false;
        a aVar3 = this.k;
        aVar3.c();
        aVar3.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.b.h0;
    }
}
