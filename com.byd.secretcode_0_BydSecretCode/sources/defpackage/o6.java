package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class o6 extends WidgetRun {
    public DependencyNode k;
    public j6 l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public o6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.k = dependencyNode;
        this.l = null;
        this.h.e = DependencyNode.Type.TOP;
        this.i.e = DependencyNode.Type.BOTTOM;
        dependencyNode.e = DependencyNode.Type.BASELINE;
        this.f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, defpackage.h6
    public void a(h6 h6Var) {
        float f;
        float fU;
        float fU2;
        int i;
        int i2 = a.a[this.j.ordinal()];
        if (i2 == 1) {
            p(h6Var);
        } else if (i2 == 2) {
            o(h6Var);
        } else if (i2 == 3) {
            ConstraintWidget constraintWidget = this.b;
            n(h6Var, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        j6 j6Var = this.e;
        if (j6Var.c && !j6Var.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.b;
            int i3 = constraintWidget2.n;
            if (i3 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.g.e.j) {
                        this.e.d((int) ((r7.g * this.b.u) + 0.5f));
                    }
                }
            } else if (i3 == 3 && constraintWidget2.f.e.j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.b;
                    f = constraintWidget3.f.e.g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f.e.g * this.b.u();
                    i = (int) (fU2 + 0.5f);
                    this.e.d(i);
                } else if (iV != 1) {
                    i = 0;
                    this.e.d(i);
                } else {
                    ConstraintWidget constraintWidget4 = this.b;
                    f = constraintWidget4.f.e.g;
                    fU = constraintWidget4.u();
                }
                fU2 = f / fU;
                i = (int) (fU2 + 0.5f);
                this.e.d(i);
            }
        }
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.c) {
            DependencyNode dependencyNode2 = this.i;
            if (dependencyNode2.c) {
                if (dependencyNode.j && dependencyNode2.j && this.e.j) {
                    return;
                }
                if (!this.e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.b;
                    if (constraintWidget5.m == 0 && !constraintWidget5.Y()) {
                        DependencyNode dependencyNode3 = this.h.l.get(0);
                        DependencyNode dependencyNode4 = this.i.l.get(0);
                        int i4 = dependencyNode3.g;
                        DependencyNode dependencyNode5 = this.h;
                        int i5 = i4 + dependencyNode5.f;
                        int i6 = dependencyNode4.g + this.i.f;
                        dependencyNode5.d(i5);
                        this.i.d(i6);
                        this.e.d(i6 - i5);
                        return;
                    }
                }
                if (!this.e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.a == 1 && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode6 = this.h.l.get(0);
                    int i7 = (this.i.l.get(0).g + this.i.f) - (dependencyNode6.g + this.h.f);
                    j6 j6Var2 = this.e;
                    int i8 = j6Var2.m;
                    if (i7 < i8) {
                        j6Var2.d(i7);
                    } else {
                        j6Var2.d(i8);
                    }
                }
                if (this.e.j && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode7 = this.h.l.get(0);
                    DependencyNode dependencyNode8 = this.i.l.get(0);
                    int i9 = dependencyNode7.g + this.h.f;
                    int i10 = dependencyNode8.g + this.i.f;
                    float fL = this.b.L();
                    if (dependencyNode7 == dependencyNode8) {
                        i9 = dependencyNode7.g;
                        i10 = dependencyNode8.g;
                        fL = 0.5f;
                    }
                    this.h.d((int) (i9 + 0.5f + (((i10 - i9) - this.e.g) * fL)));
                    this.i.d(this.h.g + this.e.g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget.b) {
            this.e.d(constraintWidget.w());
        }
        if (!this.e.j) {
            this.d = this.b.N();
            if (this.b.T()) {
                this.l = new e6(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.b.F.c()) - this.b.H.c();
                    b(this.h, constraintWidgetH2.g.h, this.b.F.c());
                    b(this.i, constraintWidgetH2.g.i, -this.b.H.c());
                    this.e.d(iW);
                    return;
                }
                if (this.d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.e.d(this.b.w());
                }
            }
        } else if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.h, constraintWidgetH.g.h, this.b.F.c());
            b(this.i, constraintWidgetH.g.i, -this.b.H.c());
            return;
        }
        j6 j6Var = this.e;
        boolean z = j6Var.j;
        if (z) {
            ConstraintWidget constraintWidget2 = this.b;
            if (constraintWidget2.b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].d != null && constraintAnchorArr[3].d != null) {
                    if (constraintWidget2.Y()) {
                        this.h.f = this.b.M[2].c();
                        this.i.f = -this.b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.h, dependencyNodeH, this.b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.i, dependencyNodeH2, -this.b.M[3].c());
                        }
                        this.h.b = true;
                        this.i.b = true;
                    }
                    if (this.b.T()) {
                        b(this.k, this.h, this.b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.h, dependencyNodeH3, this.b.M[2].c());
                        b(this.i, this.h, this.e.g);
                        if (this.b.T()) {
                            b(this.k, this.h, this.b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.b.M[3].c());
                        b(this.h, this.i, -this.e.g);
                    }
                    if (this.b.T()) {
                        b(this.k, this.h, this.b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.k, dependencyNodeH5, 0);
                        b(this.h, this.k, -this.b.o());
                        b(this.i, this.h, this.e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof z5) || constraintWidget2.H() == null || this.b.n(ConstraintAnchor.Type.CENTER).d != null) {
                    return;
                }
                b(this.h, this.b.H().g.h, this.b.S());
                b(this.i, this.h, this.e.g);
                if (this.b.T()) {
                    b(this.k, this.h, this.b.o());
                    return;
                }
                return;
            }
        }
        if (z || this.d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            j6Var.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.b;
            int i = constraintWidget3.n;
            if (i == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    j6 j6Var2 = constraintWidgetH3.g.e;
                    this.e.l.add(j6Var2);
                    j6Var2.k.add(this.e);
                    j6 j6Var3 = this.e;
                    j6Var3.b = true;
                    j6Var3.k.add(this.h);
                    this.e.k.add(this.i);
                }
            } else if (i == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.b;
                if (constraintWidget4.m != 3) {
                    j6 j6Var4 = constraintWidget4.f.e;
                    this.e.l.add(j6Var4);
                    j6Var4.k.add(this.e);
                    j6 j6Var5 = this.e;
                    j6Var5.b = true;
                    j6Var5.k.add(this.h);
                    this.e.k.add(this.i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].d != null && constraintAnchorArr2[3].d != null) {
            if (constraintWidget5.Y()) {
                this.h.f = this.b.M[2].c();
                this.i.f = -this.b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.j = WidgetRun.RunType.CENTER;
            }
            if (this.b.T()) {
                c(this.k, this.h, 1, this.l);
            }
        } else if (constraintAnchorArr2[2].d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.h, dependencyNodeH8, this.b.M[2].c());
                c(this.i, this.h, 1, this.e);
                if (this.b.T()) {
                    c(this.k, this.h, 1, this.l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.b.u() > 0.0f) {
                    m6 m6Var = this.b.f;
                    if (m6Var.d == dimensionBehaviour3) {
                        m6Var.e.k.add(this.e);
                        this.e.l.add(this.b.f.e);
                        this.e.a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.i, dependencyNodeH9, -this.b.M[3].c());
                c(this.h, this.i, -1, this.e);
                if (this.b.T()) {
                    c(this.k, this.h, 1, this.l);
                }
            }
        } else if (constraintAnchorArr2[4].d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.k, dependencyNodeH10, 0);
                c(this.h, this.k, -1, this.l);
                c(this.i, this.h, 1, this.e);
            }
        } else if (!(constraintWidget5 instanceof z5) && constraintWidget5.H() != null) {
            b(this.h, this.b.H().g.h, this.b.S());
            c(this.i, this.h, 1, this.e);
            if (this.b.T()) {
                c(this.k, this.h, 1, this.l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.b.u() > 0.0f) {
                m6 m6Var2 = this.b.f;
                if (m6Var2.d == dimensionBehaviour5) {
                    m6Var2.e.k.add(this.e);
                    this.e.l.add(this.b.f.e);
                    this.e.a = this;
                }
            }
        }
        if (this.e.l.size() == 0) {
            this.e.c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.j) {
            this.b.H0(dependencyNode.g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.b.n == 0;
    }

    public void q() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.k.c();
        this.k.j = false;
        this.e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.b.s();
    }
}
