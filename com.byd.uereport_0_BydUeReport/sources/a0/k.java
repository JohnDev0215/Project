package a0;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends WidgetRun {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public DependencyNode f37k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f f38l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f39a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f39a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f39a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f39a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.f37k = dependencyNode;
        this.f38l = null;
        this.f997h.f982e = DependencyNode.Type.TOP;
        this.f998i.f982e = DependencyNode.Type.BOTTOM;
        dependencyNode.f982e = DependencyNode.Type.BASELINE;
        this.f995f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, a0.d
    public void a(d dVar) {
        float f8;
        float fU;
        float fU2;
        int i8;
        int i9 = a.f39a[this.f999j.ordinal()];
        if (i9 == 1) {
            p(dVar);
        } else if (i9 == 2) {
            o(dVar);
        } else if (i9 == 3) {
            ConstraintWidget constraintWidget = this.f991b;
            n(dVar, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        f fVar = this.f994e;
        if (fVar.f980c && !fVar.f987j && this.f993d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f991b;
            int i10 = constraintWidget2.f950n;
            if (i10 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.f936g.f994e.f987j) {
                        this.f994e.d((int) ((r7.f984g * this.f991b.f964u) + 0.5f));
                    }
                }
            } else if (i10 == 3 && constraintWidget2.f934f.f994e.f987j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.f991b;
                    f8 = constraintWidget3.f934f.f994e.f984g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f934f.f994e.f984g * this.f991b.u();
                    i8 = (int) (fU2 + 0.5f);
                    this.f994e.d(i8);
                } else if (iV != 1) {
                    i8 = 0;
                    this.f994e.d(i8);
                } else {
                    ConstraintWidget constraintWidget4 = this.f991b;
                    f8 = constraintWidget4.f934f.f994e.f984g;
                    fU = constraintWidget4.u();
                }
                fU2 = f8 / fU;
                i8 = (int) (fU2 + 0.5f);
                this.f994e.d(i8);
            }
        }
        DependencyNode dependencyNode = this.f997h;
        if (dependencyNode.f980c) {
            DependencyNode dependencyNode2 = this.f998i;
            if (dependencyNode2.f980c) {
                if (dependencyNode.f987j && dependencyNode2.f987j && this.f994e.f987j) {
                    return;
                }
                if (!this.f994e.f987j && this.f993d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f991b;
                    if (constraintWidget5.f948m == 0 && !constraintWidget5.Y()) {
                        DependencyNode dependencyNode3 = this.f997h.f989l.get(0);
                        DependencyNode dependencyNode4 = this.f998i.f989l.get(0);
                        int i11 = dependencyNode3.f984g;
                        DependencyNode dependencyNode5 = this.f997h;
                        int i12 = i11 + dependencyNode5.f983f;
                        int i13 = dependencyNode4.f984g + this.f998i.f983f;
                        dependencyNode5.d(i12);
                        this.f998i.d(i13);
                        this.f994e.d(i13 - i12);
                        return;
                    }
                }
                if (!this.f994e.f987j && this.f993d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f990a == 1 && this.f997h.f989l.size() > 0 && this.f998i.f989l.size() > 0) {
                    DependencyNode dependencyNode6 = this.f997h.f989l.get(0);
                    int i14 = (this.f998i.f989l.get(0).f984g + this.f998i.f983f) - (dependencyNode6.f984g + this.f997h.f983f);
                    f fVar2 = this.f994e;
                    int i15 = fVar2.f26m;
                    if (i14 < i15) {
                        fVar2.d(i14);
                    } else {
                        fVar2.d(i15);
                    }
                }
                if (this.f994e.f987j && this.f997h.f989l.size() > 0 && this.f998i.f989l.size() > 0) {
                    DependencyNode dependencyNode7 = this.f997h.f989l.get(0);
                    DependencyNode dependencyNode8 = this.f998i.f989l.get(0);
                    int i16 = dependencyNode7.f984g + this.f997h.f983f;
                    int i17 = dependencyNode8.f984g + this.f998i.f983f;
                    float fL = this.f991b.L();
                    if (dependencyNode7 == dependencyNode8) {
                        i16 = dependencyNode7.f984g;
                        i17 = dependencyNode8.f984g;
                        fL = 0.5f;
                    }
                    this.f997h.d((int) (i16 + 0.5f + (((i17 - i16) - this.f994e.f984g) * fL)));
                    this.f998i.d(this.f997h.f984g + this.f994e.f984g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f991b;
        if (constraintWidget.f926b) {
            this.f994e.d(constraintWidget.w());
        }
        if (!this.f994e.f987j) {
            this.f993d = this.f991b.N();
            if (this.f991b.T()) {
                this.f38l = new a0.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f993d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.f991b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.f991b.F.c()) - this.f991b.H.c();
                    b(this.f997h, constraintWidgetH2.f936g.f997h, this.f991b.F.c());
                    b(this.f998i, constraintWidgetH2.f936g.f998i, -this.f991b.H.c());
                    this.f994e.d(iW);
                    return;
                }
                if (this.f993d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f994e.d(this.f991b.w());
                }
            }
        } else if (this.f993d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.f991b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.f997h, constraintWidgetH.f936g.f997h, this.f991b.F.c());
            b(this.f998i, constraintWidgetH.f936g.f998i, -this.f991b.H.c());
            return;
        }
        f fVar = this.f994e;
        boolean z7 = fVar.f987j;
        if (z7) {
            ConstraintWidget constraintWidget2 = this.f991b;
            if (constraintWidget2.f926b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].f919d != null && constraintAnchorArr[3].f919d != null) {
                    if (constraintWidget2.Y()) {
                        this.f997h.f983f = this.f991b.M[2].c();
                        this.f998i.f983f = -this.f991b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f991b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.f997h, dependencyNodeH, this.f991b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f991b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.f998i, dependencyNodeH2, -this.f991b.M[3].c());
                        }
                        this.f997h.f979b = true;
                        this.f998i.f979b = true;
                    }
                    if (this.f991b.T()) {
                        b(this.f37k, this.f997h, this.f991b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f919d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.f997h, dependencyNodeH3, this.f991b.M[2].c());
                        b(this.f998i, this.f997h, this.f994e.f984g);
                        if (this.f991b.T()) {
                            b(this.f37k, this.f997h, this.f991b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f919d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.f998i, dependencyNodeH4, -this.f991b.M[3].c());
                        b(this.f997h, this.f998i, -this.f994e.f984g);
                    }
                    if (this.f991b.T()) {
                        b(this.f37k, this.f997h, this.f991b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f919d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.f37k, dependencyNodeH5, 0);
                        b(this.f997h, this.f37k, -this.f991b.o());
                        b(this.f998i, this.f997h, this.f994e.f984g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof z.g) || constraintWidget2.H() == null || this.f991b.n(ConstraintAnchor.Type.CENTER).f919d != null) {
                    return;
                }
                b(this.f997h, this.f991b.H().f936g.f997h, this.f991b.S());
                b(this.f998i, this.f997h, this.f994e.f984g);
                if (this.f991b.T()) {
                    b(this.f37k, this.f997h, this.f991b.o());
                    return;
                }
                return;
            }
        }
        if (z7 || this.f993d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f991b;
            int i8 = constraintWidget3.f950n;
            if (i8 == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.f936g.f994e;
                    this.f994e.f989l.add(fVar2);
                    fVar2.f988k.add(this.f994e);
                    f fVar3 = this.f994e;
                    fVar3.f979b = true;
                    fVar3.f988k.add(this.f997h);
                    this.f994e.f988k.add(this.f998i);
                }
            } else if (i8 == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.f991b;
                if (constraintWidget4.f948m != 3) {
                    f fVar4 = constraintWidget4.f934f.f994e;
                    this.f994e.f989l.add(fVar4);
                    fVar4.f988k.add(this.f994e);
                    f fVar5 = this.f994e;
                    fVar5.f979b = true;
                    fVar5.f988k.add(this.f997h);
                    this.f994e.f988k.add(this.f998i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f991b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].f919d != null && constraintAnchorArr2[3].f919d != null) {
            if (constraintWidget5.Y()) {
                this.f997h.f983f = this.f991b.M[2].c();
                this.f998i.f983f = -this.f991b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f991b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.f991b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.f999j = WidgetRun.RunType.CENTER;
            }
            if (this.f991b.T()) {
                c(this.f37k, this.f997h, 1, this.f38l);
            }
        } else if (constraintAnchorArr2[2].f919d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.f997h, dependencyNodeH8, this.f991b.M[2].c());
                c(this.f998i, this.f997h, 1, this.f994e);
                if (this.f991b.T()) {
                    c(this.f37k, this.f997h, 1, this.f38l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f993d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f991b.u() > 0.0f) {
                    i iVar = this.f991b.f934f;
                    if (iVar.f993d == dimensionBehaviour3) {
                        iVar.f994e.f988k.add(this.f994e);
                        this.f994e.f989l.add(this.f991b.f934f.f994e);
                        this.f994e.f978a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f919d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.f998i, dependencyNodeH9, -this.f991b.M[3].c());
                c(this.f997h, this.f998i, -1, this.f994e);
                if (this.f991b.T()) {
                    c(this.f37k, this.f997h, 1, this.f38l);
                }
            }
        } else if (constraintAnchorArr2[4].f919d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.f37k, dependencyNodeH10, 0);
                c(this.f997h, this.f37k, -1, this.f38l);
                c(this.f998i, this.f997h, 1, this.f994e);
            }
        } else if (!(constraintWidget5 instanceof z.g) && constraintWidget5.H() != null) {
            b(this.f997h, this.f991b.H().f936g.f997h, this.f991b.S());
            c(this.f998i, this.f997h, 1, this.f994e);
            if (this.f991b.T()) {
                c(this.f37k, this.f997h, 1, this.f38l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f993d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f991b.u() > 0.0f) {
                i iVar2 = this.f991b.f934f;
                if (iVar2.f993d == dimensionBehaviour5) {
                    iVar2.f994e.f988k.add(this.f994e);
                    this.f994e.f989l.add(this.f991b.f934f.f994e);
                    this.f994e.f978a = this;
                }
            }
        }
        if (this.f994e.f989l.size() == 0) {
            this.f994e.f980c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f997h;
        if (dependencyNode.f987j) {
            this.f991b.H0(dependencyNode.f984g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f992c = null;
        this.f997h.c();
        this.f998i.c();
        this.f37k.c();
        this.f994e.c();
        this.f996g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f993d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f991b.f950n == 0;
    }

    public void q() {
        this.f996g = false;
        this.f997h.c();
        this.f997h.f987j = false;
        this.f998i.c();
        this.f998i.f987j = false;
        this.f37k.c();
        this.f37k.f987j = false;
        this.f994e.f987j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f991b.s();
    }
}
