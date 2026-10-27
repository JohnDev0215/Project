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
        static final /* synthetic */ int[] f690a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f690a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f690a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f690a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.k = dependencyNode;
        this.l = null;
        this.h.e = DependencyNode.Type.TOP;
        this.i.e = DependencyNode.Type.BOTTOM;
        dependencyNode.e = DependencyNode.Type.BASELINE;
        this.f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
        float f;
        float fU;
        float fU2;
        int i;
        int i2 = a.f690a[this.j.ordinal()];
        if (i2 == 1) {
            p(dVar);
        } else if (i2 == 2) {
            o(dVar);
        } else if (i2 == 3) {
            ConstraintWidget constraintWidget = this.f669b;
            n(dVar, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        f fVar = this.e;
        if (fVar.f665c && !fVar.j && this.f671d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f669b;
            int i3 = constraintWidget2.n;
            if (i3 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.g.e.j) {
                        this.e.d((int) ((r7.g * this.f669b.u) + 0.5f));
                    }
                }
            } else if (i3 == 3 && constraintWidget2.f.e.j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.f669b;
                    f = constraintWidget3.f.e.g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f.e.g * this.f669b.u();
                    i = (int) (fU2 + 0.5f);
                    this.e.d(i);
                } else if (iV != 1) {
                    i = 0;
                    this.e.d(i);
                } else {
                    ConstraintWidget constraintWidget4 = this.f669b;
                    f = constraintWidget4.f.e.g;
                    fU = constraintWidget4.u();
                }
                fU2 = f / fU;
                i = (int) (fU2 + 0.5f);
                this.e.d(i);
            }
        }
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.f665c) {
            DependencyNode dependencyNode2 = this.i;
            if (dependencyNode2.f665c) {
                if (dependencyNode.j && dependencyNode2.j && this.e.j) {
                    return;
                }
                if (!this.e.j && this.f671d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f669b;
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
                if (!this.e.j && this.f671d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f668a == 1 && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode6 = this.h.l.get(0);
                    int i7 = (this.i.l.get(0).g + this.i.f) - (dependencyNode6.g + this.h.f);
                    f fVar2 = this.e;
                    int i8 = fVar2.m;
                    if (i7 < i8) {
                        fVar2.d(i7);
                    } else {
                        fVar2.d(i8);
                    }
                }
                if (this.e.j && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode7 = this.h.l.get(0);
                    DependencyNode dependencyNode8 = this.i.l.get(0);
                    int i9 = dependencyNode7.g + this.h.f;
                    int i10 = dependencyNode8.g + this.i.f;
                    float fL = this.f669b.L();
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
    void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f669b;
        if (constraintWidget.f657b) {
            this.e.d(constraintWidget.w());
        }
        if (!this.e.j) {
            this.f671d = this.f669b.N();
            if (this.f669b.T()) {
                this.l = new androidx.constraintlayout.solver.widgets.analyzer.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f671d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.f669b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.f669b.F.c()) - this.f669b.H.c();
                    b(this.h, constraintWidgetH2.g.h, this.f669b.F.c());
                    b(this.i, constraintWidgetH2.g.i, -this.f669b.H.c());
                    this.e.d(iW);
                    return;
                }
                if (this.f671d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.e.d(this.f669b.w());
                }
            }
        } else if (this.f671d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.f669b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.h, constraintWidgetH.g.h, this.f669b.F.c());
            b(this.i, constraintWidgetH.g.i, -this.f669b.H.c());
            return;
        }
        f fVar = this.e;
        boolean z = fVar.j;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f669b;
            if (constraintWidget2.f657b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].f653d != null && constraintAnchorArr[3].f653d != null) {
                    if (constraintWidget2.Y()) {
                        this.h.f = this.f669b.M[2].c();
                        this.i.f = -this.f669b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f669b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.h, dependencyNodeH, this.f669b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f669b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.i, dependencyNodeH2, -this.f669b.M[3].c());
                        }
                        this.h.f664b = true;
                        this.i.f664b = true;
                    }
                    if (this.f669b.T()) {
                        b(this.k, this.h, this.f669b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f653d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.h, dependencyNodeH3, this.f669b.M[2].c());
                        b(this.i, this.h, this.e.g);
                        if (this.f669b.T()) {
                            b(this.k, this.h, this.f669b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f653d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.f669b.M[3].c());
                        b(this.h, this.i, -this.e.g);
                    }
                    if (this.f669b.T()) {
                        b(this.k, this.h, this.f669b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f653d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.k, dependencyNodeH5, 0);
                        b(this.h, this.k, -this.f669b.o());
                        b(this.i, this.h, this.e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof androidx.constraintlayout.solver.widgets.g) || constraintWidget2.H() == null || this.f669b.n(ConstraintAnchor.Type.CENTER).f653d != null) {
                    return;
                }
                b(this.h, this.f669b.H().g.h, this.f669b.S());
                b(this.i, this.h, this.e.g);
                if (this.f669b.T()) {
                    b(this.k, this.h, this.f669b.o());
                    return;
                }
                return;
            }
        }
        if (z || this.f671d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f669b;
            int i = constraintWidget3.n;
            if (i == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.g.e;
                    this.e.l.add(fVar2);
                    fVar2.k.add(this.e);
                    f fVar3 = this.e;
                    fVar3.f664b = true;
                    fVar3.k.add(this.h);
                    this.e.k.add(this.i);
                }
            } else if (i == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.f669b;
                if (constraintWidget4.m != 3) {
                    f fVar4 = constraintWidget4.f.e;
                    this.e.l.add(fVar4);
                    fVar4.k.add(this.e);
                    f fVar5 = this.e;
                    fVar5.f664b = true;
                    fVar5.k.add(this.h);
                    this.e.k.add(this.i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f669b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].f653d != null && constraintAnchorArr2[3].f653d != null) {
            if (constraintWidget5.Y()) {
                this.h.f = this.f669b.M[2].c();
                this.i.f = -this.f669b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f669b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.f669b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.j = WidgetRun.RunType.CENTER;
            }
            if (this.f669b.T()) {
                c(this.k, this.h, 1, this.l);
            }
        } else if (constraintAnchorArr2[2].f653d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.h, dependencyNodeH8, this.f669b.M[2].c());
                c(this.i, this.h, 1, this.e);
                if (this.f669b.T()) {
                    c(this.k, this.h, 1, this.l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f671d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f669b.u() > 0.0f) {
                    i iVar = this.f669b.f;
                    if (iVar.f671d == dimensionBehaviour3) {
                        iVar.e.k.add(this.e);
                        this.e.l.add(this.f669b.f.e);
                        this.e.f663a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f653d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.i, dependencyNodeH9, -this.f669b.M[3].c());
                c(this.h, this.i, -1, this.e);
                if (this.f669b.T()) {
                    c(this.k, this.h, 1, this.l);
                }
            }
        } else if (constraintAnchorArr2[4].f653d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.k, dependencyNodeH10, 0);
                c(this.h, this.k, -1, this.l);
                c(this.i, this.h, 1, this.e);
            }
        } else if (!(constraintWidget5 instanceof androidx.constraintlayout.solver.widgets.g) && constraintWidget5.H() != null) {
            b(this.h, this.f669b.H().g.h, this.f669b.S());
            c(this.i, this.h, 1, this.e);
            if (this.f669b.T()) {
                c(this.k, this.h, 1, this.l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f671d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f669b.u() > 0.0f) {
                i iVar2 = this.f669b.f;
                if (iVar2.f671d == dimensionBehaviour5) {
                    iVar2.e.k.add(this.e);
                    this.e.l.add(this.f669b.f.e);
                    this.e.f663a = this;
                }
            }
        }
        if (this.e.l.size() == 0) {
            this.e.f665c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.j) {
            this.f669b.H0(dependencyNode.g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.f670c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f671d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f669b.n == 0;
    }

    void q() {
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
        return "VerticalRun " + this.f669b.s();
    }
}
