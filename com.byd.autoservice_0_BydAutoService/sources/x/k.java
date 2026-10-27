package x;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends WidgetRun {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public DependencyNode f5716k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f f5717l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5718a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f5718a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5718a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5718a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.f5716k = dependencyNode;
        this.f5717l = null;
        this.f906h.f891e = DependencyNode.Type.TOP;
        this.f907i.f891e = DependencyNode.Type.BOTTOM;
        dependencyNode.f891e = DependencyNode.Type.BASELINE;
        this.f904f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, x.d
    public void a(d dVar) {
        float f3;
        float fU;
        float fU2;
        int i3;
        int i4 = a.f5718a[this.f908j.ordinal()];
        if (i4 == 1) {
            p(dVar);
        } else if (i4 == 2) {
            o(dVar);
        } else if (i4 == 3) {
            ConstraintWidget constraintWidget = this.f900b;
            n(dVar, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        f fVar = this.f903e;
        if (fVar.f889c && !fVar.f896j && this.f902d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f900b;
            int i5 = constraintWidget2.f859n;
            if (i5 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.f845g.f903e.f896j) {
                        this.f903e.d((int) ((r7.f893g * this.f900b.f873u) + 0.5f));
                    }
                }
            } else if (i5 == 3 && constraintWidget2.f843f.f903e.f896j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.f900b;
                    f3 = constraintWidget3.f843f.f903e.f893g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f843f.f903e.f893g * this.f900b.u();
                    i3 = (int) (fU2 + 0.5f);
                    this.f903e.d(i3);
                } else if (iV != 1) {
                    i3 = 0;
                    this.f903e.d(i3);
                } else {
                    ConstraintWidget constraintWidget4 = this.f900b;
                    f3 = constraintWidget4.f843f.f903e.f893g;
                    fU = constraintWidget4.u();
                }
                fU2 = f3 / fU;
                i3 = (int) (fU2 + 0.5f);
                this.f903e.d(i3);
            }
        }
        DependencyNode dependencyNode = this.f906h;
        if (dependencyNode.f889c) {
            DependencyNode dependencyNode2 = this.f907i;
            if (dependencyNode2.f889c) {
                if (dependencyNode.f896j && dependencyNode2.f896j && this.f903e.f896j) {
                    return;
                }
                if (!this.f903e.f896j && this.f902d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f900b;
                    if (constraintWidget5.f857m == 0 && !constraintWidget5.Y()) {
                        DependencyNode dependencyNode3 = this.f906h.f898l.get(0);
                        DependencyNode dependencyNode4 = this.f907i.f898l.get(0);
                        int i6 = dependencyNode3.f893g;
                        DependencyNode dependencyNode5 = this.f906h;
                        int i7 = i6 + dependencyNode5.f892f;
                        int i8 = dependencyNode4.f893g + this.f907i.f892f;
                        dependencyNode5.d(i7);
                        this.f907i.d(i8);
                        this.f903e.d(i8 - i7);
                        return;
                    }
                }
                if (!this.f903e.f896j && this.f902d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f899a == 1 && this.f906h.f898l.size() > 0 && this.f907i.f898l.size() > 0) {
                    DependencyNode dependencyNode6 = this.f906h.f898l.get(0);
                    int i9 = (this.f907i.f898l.get(0).f893g + this.f907i.f892f) - (dependencyNode6.f893g + this.f906h.f892f);
                    f fVar2 = this.f903e;
                    int i10 = fVar2.f5705m;
                    if (i9 < i10) {
                        fVar2.d(i9);
                    } else {
                        fVar2.d(i10);
                    }
                }
                if (this.f903e.f896j && this.f906h.f898l.size() > 0 && this.f907i.f898l.size() > 0) {
                    DependencyNode dependencyNode7 = this.f906h.f898l.get(0);
                    DependencyNode dependencyNode8 = this.f907i.f898l.get(0);
                    int i11 = dependencyNode7.f893g + this.f906h.f892f;
                    int i12 = dependencyNode8.f893g + this.f907i.f892f;
                    float fL = this.f900b.L();
                    if (dependencyNode7 == dependencyNode8) {
                        i11 = dependencyNode7.f893g;
                        i12 = dependencyNode8.f893g;
                        fL = 0.5f;
                    }
                    this.f906h.d((int) (i11 + 0.5f + (((i12 - i11) - this.f903e.f893g) * fL)));
                    this.f907i.d(this.f906h.f893g + this.f903e.f893g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f900b;
        if (constraintWidget.f835b) {
            this.f903e.d(constraintWidget.w());
        }
        if (!this.f903e.f896j) {
            this.f902d = this.f900b.N();
            if (this.f900b.T()) {
                this.f5717l = new x.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f902d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.f900b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.f900b.F.c()) - this.f900b.H.c();
                    b(this.f906h, constraintWidgetH2.f845g.f906h, this.f900b.F.c());
                    b(this.f907i, constraintWidgetH2.f845g.f907i, -this.f900b.H.c());
                    this.f903e.d(iW);
                    return;
                }
                if (this.f902d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f903e.d(this.f900b.w());
                }
            }
        } else if (this.f902d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.f900b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.f906h, constraintWidgetH.f845g.f906h, this.f900b.F.c());
            b(this.f907i, constraintWidgetH.f845g.f907i, -this.f900b.H.c());
            return;
        }
        f fVar = this.f903e;
        boolean z3 = fVar.f896j;
        if (z3) {
            ConstraintWidget constraintWidget2 = this.f900b;
            if (constraintWidget2.f835b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].f828d != null && constraintAnchorArr[3].f828d != null) {
                    if (constraintWidget2.Y()) {
                        this.f906h.f892f = this.f900b.M[2].c();
                        this.f907i.f892f = -this.f900b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f900b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.f906h, dependencyNodeH, this.f900b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f900b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.f907i, dependencyNodeH2, -this.f900b.M[3].c());
                        }
                        this.f906h.f888b = true;
                        this.f907i.f888b = true;
                    }
                    if (this.f900b.T()) {
                        b(this.f5716k, this.f906h, this.f900b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f828d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.f906h, dependencyNodeH3, this.f900b.M[2].c());
                        b(this.f907i, this.f906h, this.f903e.f893g);
                        if (this.f900b.T()) {
                            b(this.f5716k, this.f906h, this.f900b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f828d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.f907i, dependencyNodeH4, -this.f900b.M[3].c());
                        b(this.f906h, this.f907i, -this.f903e.f893g);
                    }
                    if (this.f900b.T()) {
                        b(this.f5716k, this.f906h, this.f900b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f828d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.f5716k, dependencyNodeH5, 0);
                        b(this.f906h, this.f5716k, -this.f900b.o());
                        b(this.f907i, this.f906h, this.f903e.f893g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof w.g) || constraintWidget2.H() == null || this.f900b.n(ConstraintAnchor.Type.CENTER).f828d != null) {
                    return;
                }
                b(this.f906h, this.f900b.H().f845g.f906h, this.f900b.S());
                b(this.f907i, this.f906h, this.f903e.f893g);
                if (this.f900b.T()) {
                    b(this.f5716k, this.f906h, this.f900b.o());
                    return;
                }
                return;
            }
        }
        if (z3 || this.f902d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f900b;
            int i3 = constraintWidget3.f859n;
            if (i3 == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.f845g.f903e;
                    this.f903e.f898l.add(fVar2);
                    fVar2.f897k.add(this.f903e);
                    f fVar3 = this.f903e;
                    fVar3.f888b = true;
                    fVar3.f897k.add(this.f906h);
                    this.f903e.f897k.add(this.f907i);
                }
            } else if (i3 == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.f900b;
                if (constraintWidget4.f857m != 3) {
                    f fVar4 = constraintWidget4.f843f.f903e;
                    this.f903e.f898l.add(fVar4);
                    fVar4.f897k.add(this.f903e);
                    f fVar5 = this.f903e;
                    fVar5.f888b = true;
                    fVar5.f897k.add(this.f906h);
                    this.f903e.f897k.add(this.f907i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f900b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].f828d != null && constraintAnchorArr2[3].f828d != null) {
            if (constraintWidget5.Y()) {
                this.f906h.f892f = this.f900b.M[2].c();
                this.f907i.f892f = -this.f900b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f900b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.f900b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.f908j = WidgetRun.RunType.CENTER;
            }
            if (this.f900b.T()) {
                c(this.f5716k, this.f906h, 1, this.f5717l);
            }
        } else if (constraintAnchorArr2[2].f828d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.f906h, dependencyNodeH8, this.f900b.M[2].c());
                c(this.f907i, this.f906h, 1, this.f903e);
                if (this.f900b.T()) {
                    c(this.f5716k, this.f906h, 1, this.f5717l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f902d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f900b.u() > 0.0f) {
                    i iVar = this.f900b.f843f;
                    if (iVar.f902d == dimensionBehaviour3) {
                        iVar.f903e.f897k.add(this.f903e);
                        this.f903e.f898l.add(this.f900b.f843f.f903e);
                        this.f903e.f887a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f828d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.f907i, dependencyNodeH9, -this.f900b.M[3].c());
                c(this.f906h, this.f907i, -1, this.f903e);
                if (this.f900b.T()) {
                    c(this.f5716k, this.f906h, 1, this.f5717l);
                }
            }
        } else if (constraintAnchorArr2[4].f828d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.f5716k, dependencyNodeH10, 0);
                c(this.f906h, this.f5716k, -1, this.f5717l);
                c(this.f907i, this.f906h, 1, this.f903e);
            }
        } else if (!(constraintWidget5 instanceof w.g) && constraintWidget5.H() != null) {
            b(this.f906h, this.f900b.H().f845g.f906h, this.f900b.S());
            c(this.f907i, this.f906h, 1, this.f903e);
            if (this.f900b.T()) {
                c(this.f5716k, this.f906h, 1, this.f5717l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f902d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f900b.u() > 0.0f) {
                i iVar2 = this.f900b.f843f;
                if (iVar2.f902d == dimensionBehaviour5) {
                    iVar2.f903e.f897k.add(this.f903e);
                    this.f903e.f898l.add(this.f900b.f843f.f903e);
                    this.f903e.f887a = this;
                }
            }
        }
        if (this.f903e.f898l.size() == 0) {
            this.f903e.f889c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f906h;
        if (dependencyNode.f896j) {
            this.f900b.H0(dependencyNode.f893g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f901c = null;
        this.f906h.c();
        this.f907i.c();
        this.f5716k.c();
        this.f903e.c();
        this.f905g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f902d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f900b.f859n == 0;
    }

    public void q() {
        this.f905g = false;
        this.f906h.c();
        this.f906h.f896j = false;
        this.f907i.c();
        this.f907i.f896j = false;
        this.f5716k.c();
        this.f5716k.f896j = false;
        this.f903e.f896j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f900b.s();
    }
}
