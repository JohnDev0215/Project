package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends WidgetRun {
    public DependencyNode k;
    f l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1024a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f1024a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1024a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1024a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.k = dependencyNode;
        this.l = null;
        this.f993h.f981e = DependencyNode.Type.TOP;
        this.i.f981e = DependencyNode.Type.BOTTOM;
        dependencyNode.f981e = DependencyNode.Type.BASELINE;
        this.f991f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
        float f2;
        float fU;
        float fU2;
        int i;
        int i2 = a.f1024a[this.j.ordinal()];
        if (i2 == 1) {
            p(dVar);
        } else if (i2 == 2) {
            o(dVar);
        } else if (i2 == 3) {
            ConstraintWidget constraintWidget = this.f987b;
            n(dVar, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        f fVar = this.f990e;
        if (fVar.f979c && !fVar.j && this.f989d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f987b;
            int i3 = constraintWidget2.n;
            if (i3 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.f972g.f990e.j) {
                        this.f990e.d((int) ((r7.f983g * this.f987b.u) + 0.5f));
                    }
                }
            } else if (i3 == 3 && constraintWidget2.f971f.f990e.j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.f987b;
                    f2 = constraintWidget3.f971f.f990e.f983g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f971f.f990e.f983g * this.f987b.u();
                    i = (int) (fU2 + 0.5f);
                    this.f990e.d(i);
                } else if (iV != 1) {
                    i = 0;
                    this.f990e.d(i);
                } else {
                    ConstraintWidget constraintWidget4 = this.f987b;
                    f2 = constraintWidget4.f971f.f990e.f983g;
                    fU = constraintWidget4.u();
                }
                fU2 = f2 / fU;
                i = (int) (fU2 + 0.5f);
                this.f990e.d(i);
            }
        }
        DependencyNode dependencyNode = this.f993h;
        if (dependencyNode.f979c) {
            DependencyNode dependencyNode2 = this.i;
            if (dependencyNode2.f979c) {
                if (dependencyNode.j && dependencyNode2.j && this.f990e.j) {
                    return;
                }
                if (!this.f990e.j && this.f989d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f987b;
                    if (constraintWidget5.m == 0 && !constraintWidget5.Y()) {
                        DependencyNode dependencyNode3 = this.f993h.l.get(0);
                        DependencyNode dependencyNode4 = this.i.l.get(0);
                        int i4 = dependencyNode3.f983g;
                        DependencyNode dependencyNode5 = this.f993h;
                        int i5 = i4 + dependencyNode5.f982f;
                        int i6 = dependencyNode4.f983g + this.i.f982f;
                        dependencyNode5.d(i5);
                        this.i.d(i6);
                        this.f990e.d(i6 - i5);
                        return;
                    }
                }
                if (!this.f990e.j && this.f989d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f986a == 1 && this.f993h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode6 = this.f993h.l.get(0);
                    int i7 = (this.i.l.get(0).f983g + this.i.f982f) - (dependencyNode6.f983g + this.f993h.f982f);
                    f fVar2 = this.f990e;
                    int i8 = fVar2.m;
                    if (i7 < i8) {
                        fVar2.d(i7);
                    } else {
                        fVar2.d(i8);
                    }
                }
                if (this.f990e.j && this.f993h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode7 = this.f993h.l.get(0);
                    DependencyNode dependencyNode8 = this.i.l.get(0);
                    int i9 = dependencyNode7.f983g + this.f993h.f982f;
                    int i10 = dependencyNode8.f983g + this.i.f982f;
                    float fL = this.f987b.L();
                    if (dependencyNode7 == dependencyNode8) {
                        i9 = dependencyNode7.f983g;
                        i10 = dependencyNode8.f983g;
                        fL = 0.5f;
                    }
                    this.f993h.d((int) (i9 + 0.5f + (((i10 - i9) - this.f990e.f983g) * fL)));
                    this.i.d(this.f993h.f983g + this.f990e.f983g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f987b;
        if (constraintWidget.f967b) {
            this.f990e.d(constraintWidget.w());
        }
        if (!this.f990e.j) {
            this.f989d = this.f987b.N();
            if (this.f987b.T()) {
                this.l = new androidx.constraintlayout.solver.widgets.analyzer.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f989d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.f987b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.f987b.F.c()) - this.f987b.H.c();
                    b(this.f993h, constraintWidgetH2.f972g.f993h, this.f987b.F.c());
                    b(this.i, constraintWidgetH2.f972g.i, -this.f987b.H.c());
                    this.f990e.d(iW);
                    return;
                }
                if (this.f989d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f990e.d(this.f987b.w());
                }
            }
        } else if (this.f989d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.f987b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.f993h, constraintWidgetH.f972g.f993h, this.f987b.F.c());
            b(this.i, constraintWidgetH.f972g.i, -this.f987b.H.c());
            return;
        }
        f fVar = this.f990e;
        boolean z = fVar.j;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f987b;
            if (constraintWidget2.f967b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].f960d != null && constraintAnchorArr[3].f960d != null) {
                    if (constraintWidget2.Y()) {
                        this.f993h.f982f = this.f987b.M[2].c();
                        this.i.f982f = -this.f987b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f987b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.f993h, dependencyNodeH, this.f987b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f987b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.i, dependencyNodeH2, -this.f987b.M[3].c());
                        }
                        this.f993h.f978b = true;
                        this.i.f978b = true;
                    }
                    if (this.f987b.T()) {
                        b(this.k, this.f993h, this.f987b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f960d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.f993h, dependencyNodeH3, this.f987b.M[2].c());
                        b(this.i, this.f993h, this.f990e.f983g);
                        if (this.f987b.T()) {
                            b(this.k, this.f993h, this.f987b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f960d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.f987b.M[3].c());
                        b(this.f993h, this.i, -this.f990e.f983g);
                    }
                    if (this.f987b.T()) {
                        b(this.k, this.f993h, this.f987b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f960d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.k, dependencyNodeH5, 0);
                        b(this.f993h, this.k, -this.f987b.o());
                        b(this.i, this.f993h, this.f990e.f983g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof androidx.constraintlayout.solver.widgets.g) || constraintWidget2.H() == null || this.f987b.n(ConstraintAnchor.Type.CENTER).f960d != null) {
                    return;
                }
                b(this.f993h, this.f987b.H().f972g.f993h, this.f987b.S());
                b(this.i, this.f993h, this.f990e.f983g);
                if (this.f987b.T()) {
                    b(this.k, this.f993h, this.f987b.o());
                    return;
                }
                return;
            }
        }
        if (z || this.f989d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f987b;
            int i = constraintWidget3.n;
            if (i == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.f972g.f990e;
                    this.f990e.l.add(fVar2);
                    fVar2.k.add(this.f990e);
                    f fVar3 = this.f990e;
                    fVar3.f978b = true;
                    fVar3.k.add(this.f993h);
                    this.f990e.k.add(this.i);
                }
            } else if (i == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.f987b;
                if (constraintWidget4.m != 3) {
                    f fVar4 = constraintWidget4.f971f.f990e;
                    this.f990e.l.add(fVar4);
                    fVar4.k.add(this.f990e);
                    f fVar5 = this.f990e;
                    fVar5.f978b = true;
                    fVar5.k.add(this.f993h);
                    this.f990e.k.add(this.i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f987b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].f960d != null && constraintAnchorArr2[3].f960d != null) {
            if (constraintWidget5.Y()) {
                this.f993h.f982f = this.f987b.M[2].c();
                this.i.f982f = -this.f987b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f987b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.f987b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.j = WidgetRun.RunType.CENTER;
            }
            if (this.f987b.T()) {
                c(this.k, this.f993h, 1, this.l);
            }
        } else if (constraintAnchorArr2[2].f960d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.f993h, dependencyNodeH8, this.f987b.M[2].c());
                c(this.i, this.f993h, 1, this.f990e);
                if (this.f987b.T()) {
                    c(this.k, this.f993h, 1, this.l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f989d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f987b.u() > 0.0f) {
                    i iVar = this.f987b.f971f;
                    if (iVar.f989d == dimensionBehaviour3) {
                        iVar.f990e.k.add(this.f990e);
                        this.f990e.l.add(this.f987b.f971f.f990e);
                        this.f990e.f977a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f960d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.i, dependencyNodeH9, -this.f987b.M[3].c());
                c(this.f993h, this.i, -1, this.f990e);
                if (this.f987b.T()) {
                    c(this.k, this.f993h, 1, this.l);
                }
            }
        } else if (constraintAnchorArr2[4].f960d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.k, dependencyNodeH10, 0);
                c(this.f993h, this.k, -1, this.l);
                c(this.i, this.f993h, 1, this.f990e);
            }
        } else if (!(constraintWidget5 instanceof androidx.constraintlayout.solver.widgets.g) && constraintWidget5.H() != null) {
            b(this.f993h, this.f987b.H().f972g.f993h, this.f987b.S());
            c(this.i, this.f993h, 1, this.f990e);
            if (this.f987b.T()) {
                c(this.k, this.f993h, 1, this.l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f989d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f987b.u() > 0.0f) {
                i iVar2 = this.f987b.f971f;
                if (iVar2.f989d == dimensionBehaviour5) {
                    iVar2.f990e.k.add(this.f990e);
                    this.f990e.l.add(this.f987b.f971f.f990e);
                    this.f990e.f977a = this;
                }
            }
        }
        if (this.f990e.l.size() == 0) {
            this.f990e.f979c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f993h;
        if (dependencyNode.j) {
            this.f987b.H0(dependencyNode.f983g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.f988c = null;
        this.f993h.c();
        this.i.c();
        this.k.c();
        this.f990e.c();
        this.f992g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f989d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f987b.n == 0;
    }

    void q() {
        this.f992g = false;
        this.f993h.c();
        this.f993h.j = false;
        this.i.c();
        this.i.j = false;
        this.k.c();
        this.k.j = false;
        this.f990e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f987b.s();
    }
}
