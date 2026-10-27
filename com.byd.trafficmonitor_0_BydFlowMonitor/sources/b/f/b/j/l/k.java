package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends WidgetRun {
    public DependencyNode k;
    public f l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2264a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f2264a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2264a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2264a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.k = dependencyNode;
        this.l = null;
        this.f656h.f643e = DependencyNode.Type.TOP;
        this.f657i.f643e = DependencyNode.Type.BOTTOM;
        dependencyNode.f643e = DependencyNode.Type.BASELINE;
        this.f654f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.f.b.j.l.d
    public void a(d dVar) {
        float f2;
        float fU;
        float fU2;
        int i2;
        int i3 = a.f2264a[this.j.ordinal()];
        if (i3 == 1) {
            p(dVar);
        } else if (i3 == 2) {
            o(dVar);
        } else if (i3 == 3) {
            ConstraintWidget constraintWidget = this.f650b;
            n(dVar, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        f fVar = this.f653e;
        if (fVar.f641c && !fVar.j && this.f652d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f650b;
            int i4 = constraintWidget2.n;
            if (i4 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.f633g.f653e.j) {
                        this.f653e.d((int) ((r7.f645g * this.f650b.u) + 0.5f));
                    }
                }
            } else if (i4 == 3 && constraintWidget2.f632f.f653e.j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.f650b;
                    f2 = constraintWidget3.f632f.f653e.f645g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f632f.f653e.f645g * this.f650b.u();
                    i2 = (int) (fU2 + 0.5f);
                    this.f653e.d(i2);
                } else if (iV != 1) {
                    i2 = 0;
                    this.f653e.d(i2);
                } else {
                    ConstraintWidget constraintWidget4 = this.f650b;
                    f2 = constraintWidget4.f632f.f653e.f645g;
                    fU = constraintWidget4.u();
                }
                fU2 = f2 / fU;
                i2 = (int) (fU2 + 0.5f);
                this.f653e.d(i2);
            }
        }
        DependencyNode dependencyNode = this.f656h;
        if (dependencyNode.f641c) {
            DependencyNode dependencyNode2 = this.f657i;
            if (dependencyNode2.f641c) {
                if (dependencyNode.j && dependencyNode2.j && this.f653e.j) {
                    return;
                }
                if (!this.f653e.j && this.f652d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f650b;
                    if (constraintWidget5.m == 0 && !constraintWidget5.Y()) {
                        DependencyNode dependencyNode3 = this.f656h.l.get(0);
                        DependencyNode dependencyNode4 = this.f657i.l.get(0);
                        int i5 = dependencyNode3.f645g;
                        DependencyNode dependencyNode5 = this.f656h;
                        int i6 = i5 + dependencyNode5.f644f;
                        int i7 = dependencyNode4.f645g + this.f657i.f644f;
                        dependencyNode5.d(i6);
                        this.f657i.d(i7);
                        this.f653e.d(i7 - i6);
                        return;
                    }
                }
                if (!this.f653e.j && this.f652d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f649a == 1 && this.f656h.l.size() > 0 && this.f657i.l.size() > 0) {
                    DependencyNode dependencyNode6 = this.f656h.l.get(0);
                    int i8 = (this.f657i.l.get(0).f645g + this.f657i.f644f) - (dependencyNode6.f645g + this.f656h.f644f);
                    f fVar2 = this.f653e;
                    int i9 = fVar2.m;
                    if (i8 < i9) {
                        fVar2.d(i8);
                    } else {
                        fVar2.d(i9);
                    }
                }
                if (this.f653e.j && this.f656h.l.size() > 0 && this.f657i.l.size() > 0) {
                    DependencyNode dependencyNode7 = this.f656h.l.get(0);
                    DependencyNode dependencyNode8 = this.f657i.l.get(0);
                    int i10 = dependencyNode7.f645g + this.f656h.f644f;
                    int i11 = dependencyNode8.f645g + this.f657i.f644f;
                    float fL = this.f650b.L();
                    if (dependencyNode7 == dependencyNode8) {
                        i10 = dependencyNode7.f645g;
                        i11 = dependencyNode8.f645g;
                        fL = 0.5f;
                    }
                    this.f656h.d((int) (i10 + 0.5f + (((i11 - i10) - this.f653e.f645g) * fL)));
                    this.f657i.d(this.f656h.f645g + this.f653e.f645g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f650b;
        if (constraintWidget.f628b) {
            this.f653e.d(constraintWidget.w());
        }
        if (!this.f653e.j) {
            this.f652d = this.f650b.N();
            if (this.f650b.T()) {
                this.l = new b.f.b.j.l.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f652d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.f650b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.f650b.F.c()) - this.f650b.H.c();
                    b(this.f656h, constraintWidgetH2.f633g.f656h, this.f650b.F.c());
                    b(this.f657i, constraintWidgetH2.f633g.f657i, -this.f650b.H.c());
                    this.f653e.d(iW);
                    return;
                }
                if (this.f652d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f653e.d(this.f650b.w());
                }
            }
        } else if (this.f652d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.f650b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.f656h, constraintWidgetH.f633g.f656h, this.f650b.F.c());
            b(this.f657i, constraintWidgetH.f633g.f657i, -this.f650b.H.c());
            return;
        }
        f fVar = this.f653e;
        boolean z = fVar.j;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f650b;
            if (constraintWidget2.f628b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].f621d != null && constraintAnchorArr[3].f621d != null) {
                    if (constraintWidget2.Y()) {
                        this.f656h.f644f = this.f650b.M[2].c();
                        this.f657i.f644f = -this.f650b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f650b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.f656h, dependencyNodeH, this.f650b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f650b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.f657i, dependencyNodeH2, -this.f650b.M[3].c());
                        }
                        this.f656h.f640b = true;
                        this.f657i.f640b = true;
                    }
                    if (this.f650b.T()) {
                        b(this.k, this.f656h, this.f650b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f621d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.f656h, dependencyNodeH3, this.f650b.M[2].c());
                        b(this.f657i, this.f656h, this.f653e.f645g);
                        if (this.f650b.T()) {
                            b(this.k, this.f656h, this.f650b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f621d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.f657i, dependencyNodeH4, -this.f650b.M[3].c());
                        b(this.f656h, this.f657i, -this.f653e.f645g);
                    }
                    if (this.f650b.T()) {
                        b(this.k, this.f656h, this.f650b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f621d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.k, dependencyNodeH5, 0);
                        b(this.f656h, this.k, -this.f650b.o());
                        b(this.f657i, this.f656h, this.f653e.f645g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof b.f.b.j.g) || constraintWidget2.H() == null || this.f650b.n(ConstraintAnchor.Type.CENTER).f621d != null) {
                    return;
                }
                b(this.f656h, this.f650b.H().f633g.f656h, this.f650b.S());
                b(this.f657i, this.f656h, this.f653e.f645g);
                if (this.f650b.T()) {
                    b(this.k, this.f656h, this.f650b.o());
                    return;
                }
                return;
            }
        }
        if (z || this.f652d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f650b;
            int i2 = constraintWidget3.n;
            if (i2 == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.f633g.f653e;
                    this.f653e.l.add(fVar2);
                    fVar2.k.add(this.f653e);
                    f fVar3 = this.f653e;
                    fVar3.f640b = true;
                    fVar3.k.add(this.f656h);
                    this.f653e.k.add(this.f657i);
                }
            } else if (i2 == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.f650b;
                if (constraintWidget4.m != 3) {
                    f fVar4 = constraintWidget4.f632f.f653e;
                    this.f653e.l.add(fVar4);
                    fVar4.k.add(this.f653e);
                    f fVar5 = this.f653e;
                    fVar5.f640b = true;
                    fVar5.k.add(this.f656h);
                    this.f653e.k.add(this.f657i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f650b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].f621d != null && constraintAnchorArr2[3].f621d != null) {
            if (constraintWidget5.Y()) {
                this.f656h.f644f = this.f650b.M[2].c();
                this.f657i.f644f = -this.f650b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f650b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.f650b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.j = WidgetRun.RunType.CENTER;
            }
            if (this.f650b.T()) {
                c(this.k, this.f656h, 1, this.l);
            }
        } else if (constraintAnchorArr2[2].f621d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.f656h, dependencyNodeH8, this.f650b.M[2].c());
                c(this.f657i, this.f656h, 1, this.f653e);
                if (this.f650b.T()) {
                    c(this.k, this.f656h, 1, this.l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f652d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f650b.u() > 0.0f) {
                    i iVar = this.f650b.f632f;
                    if (iVar.f652d == dimensionBehaviour3) {
                        iVar.f653e.k.add(this.f653e);
                        this.f653e.l.add(this.f650b.f632f.f653e);
                        this.f653e.f639a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f621d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.f657i, dependencyNodeH9, -this.f650b.M[3].c());
                c(this.f656h, this.f657i, -1, this.f653e);
                if (this.f650b.T()) {
                    c(this.k, this.f656h, 1, this.l);
                }
            }
        } else if (constraintAnchorArr2[4].f621d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.k, dependencyNodeH10, 0);
                c(this.f656h, this.k, -1, this.l);
                c(this.f657i, this.f656h, 1, this.f653e);
            }
        } else if (!(constraintWidget5 instanceof b.f.b.j.g) && constraintWidget5.H() != null) {
            b(this.f656h, this.f650b.H().f633g.f656h, this.f650b.S());
            c(this.f657i, this.f656h, 1, this.f653e);
            if (this.f650b.T()) {
                c(this.k, this.f656h, 1, this.l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f652d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f650b.u() > 0.0f) {
                i iVar2 = this.f650b.f632f;
                if (iVar2.f652d == dimensionBehaviour5) {
                    iVar2.f653e.k.add(this.f653e);
                    this.f653e.l.add(this.f650b.f632f.f653e);
                    this.f653e.f639a = this;
                }
            }
        }
        if (this.f653e.l.size() == 0) {
            this.f653e.f641c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f656h;
        if (dependencyNode.j) {
            this.f650b.H0(dependencyNode.f645g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f651c = null;
        this.f656h.c();
        this.f657i.c();
        this.k.c();
        this.f653e.c();
        this.f655g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f652d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f650b.n == 0;
    }

    public void q() {
        this.f655g = false;
        this.f656h.c();
        this.f656h.j = false;
        this.f657i.c();
        this.f657i.j = false;
        this.k.c();
        this.k.j = false;
        this.f653e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f650b.s();
    }
}
