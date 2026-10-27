package b.e.b.j.l;

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
        public static final /* synthetic */ int[] f1452a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f1452a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1452a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1452a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.k = dependencyNode;
        this.l = null;
        this.f367h.f354e = DependencyNode.Type.TOP;
        this.f368i.f354e = DependencyNode.Type.BOTTOM;
        dependencyNode.f354e = DependencyNode.Type.BASELINE;
        this.f365f = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.e.b.j.l.d
    public void a(d dVar) {
        float f2;
        float fU;
        float fU2;
        int i2;
        int i3 = a.f1452a[this.j.ordinal()];
        if (i3 == 1) {
            p(dVar);
        } else if (i3 == 2) {
            o(dVar);
        } else if (i3 == 3) {
            ConstraintWidget constraintWidget = this.f361b;
            n(dVar, constraintWidget.F, constraintWidget.H, 1);
            return;
        }
        f fVar = this.f364e;
        if (fVar.f352c && !fVar.j && this.f363d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f361b;
            int i4 = constraintWidget2.n;
            if (i4 == 2) {
                ConstraintWidget constraintWidgetH = constraintWidget2.H();
                if (constraintWidgetH != null) {
                    if (constraintWidgetH.f344g.f364e.j) {
                        this.f364e.d((int) ((r7.f356g * this.f361b.u) + 0.5f));
                    }
                }
            } else if (i4 == 3 && constraintWidget2.f343f.f364e.j) {
                int iV = constraintWidget2.v();
                if (iV == -1) {
                    ConstraintWidget constraintWidget3 = this.f361b;
                    f2 = constraintWidget3.f343f.f364e.f356g;
                    fU = constraintWidget3.u();
                } else if (iV == 0) {
                    fU2 = r7.f343f.f364e.f356g * this.f361b.u();
                    i2 = (int) (fU2 + 0.5f);
                    this.f364e.d(i2);
                } else if (iV != 1) {
                    i2 = 0;
                    this.f364e.d(i2);
                } else {
                    ConstraintWidget constraintWidget4 = this.f361b;
                    f2 = constraintWidget4.f343f.f364e.f356g;
                    fU = constraintWidget4.u();
                }
                fU2 = f2 / fU;
                i2 = (int) (fU2 + 0.5f);
                this.f364e.d(i2);
            }
        }
        DependencyNode dependencyNode = this.f367h;
        if (dependencyNode.f352c) {
            DependencyNode dependencyNode2 = this.f368i;
            if (dependencyNode2.f352c) {
                if (dependencyNode.j && dependencyNode2.j && this.f364e.j) {
                    return;
                }
                if (!this.f364e.j && this.f363d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f361b;
                    if (constraintWidget5.m == 0 && !constraintWidget5.Y()) {
                        DependencyNode dependencyNode3 = this.f367h.l.get(0);
                        DependencyNode dependencyNode4 = this.f368i.l.get(0);
                        int i5 = dependencyNode3.f356g;
                        DependencyNode dependencyNode5 = this.f367h;
                        int i6 = i5 + dependencyNode5.f355f;
                        int i7 = dependencyNode4.f356g + this.f368i.f355f;
                        dependencyNode5.d(i6);
                        this.f368i.d(i7);
                        this.f364e.d(i7 - i6);
                        return;
                    }
                }
                if (!this.f364e.j && this.f363d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f360a == 1 && this.f367h.l.size() > 0 && this.f368i.l.size() > 0) {
                    DependencyNode dependencyNode6 = this.f367h.l.get(0);
                    int i8 = (this.f368i.l.get(0).f356g + this.f368i.f355f) - (dependencyNode6.f356g + this.f367h.f355f);
                    f fVar2 = this.f364e;
                    int i9 = fVar2.m;
                    if (i8 < i9) {
                        fVar2.d(i8);
                    } else {
                        fVar2.d(i9);
                    }
                }
                if (this.f364e.j && this.f367h.l.size() > 0 && this.f368i.l.size() > 0) {
                    DependencyNode dependencyNode7 = this.f367h.l.get(0);
                    DependencyNode dependencyNode8 = this.f368i.l.get(0);
                    int i10 = dependencyNode7.f356g + this.f367h.f355f;
                    int i11 = dependencyNode8.f356g + this.f368i.f355f;
                    float fL = this.f361b.L();
                    if (dependencyNode7 == dependencyNode8) {
                        i10 = dependencyNode7.f356g;
                        i11 = dependencyNode8.f356g;
                        fL = 0.5f;
                    }
                    this.f367h.d((int) (i10 + 0.5f + (((i11 - i10) - this.f364e.f356g) * fL)));
                    this.f368i.d(this.f367h.f356g + this.f364e.f356g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f361b;
        if (constraintWidget.f339b) {
            this.f364e.d(constraintWidget.w());
        }
        if (!this.f364e.j) {
            this.f363d = this.f361b.N();
            if (this.f361b.T()) {
                this.l = new b.e.b.j.l.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f363d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH2 = this.f361b.H()) != null && constraintWidgetH2.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iW = (constraintWidgetH2.w() - this.f361b.F.c()) - this.f361b.H.c();
                    b(this.f367h, constraintWidgetH2.f344g.f367h, this.f361b.F.c());
                    b(this.f368i, constraintWidgetH2.f344g.f368i, -this.f361b.H.c());
                    this.f364e.d(iW);
                    return;
                }
                if (this.f363d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f364e.d(this.f361b.w());
                }
            }
        } else if (this.f363d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetH = this.f361b.H()) != null && constraintWidgetH.N() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.f367h, constraintWidgetH.f344g.f367h, this.f361b.F.c());
            b(this.f368i, constraintWidgetH.f344g.f368i, -this.f361b.H.c());
            return;
        }
        f fVar = this.f364e;
        boolean z = fVar.j;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f361b;
            if (constraintWidget2.f339b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[2].f332d != null && constraintAnchorArr[3].f332d != null) {
                    if (constraintWidget2.Y()) {
                        this.f367h.f355f = this.f361b.M[2].c();
                        this.f368i.f355f = -this.f361b.M[3].c();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f361b.M[2]);
                        if (dependencyNodeH != null) {
                            b(this.f367h, dependencyNodeH, this.f361b.M[2].c());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f361b.M[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.f368i, dependencyNodeH2, -this.f361b.M[3].c());
                        }
                        this.f367h.f351b = true;
                        this.f368i.f351b = true;
                    }
                    if (this.f361b.T()) {
                        b(this.k, this.f367h, this.f361b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f332d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.f367h, dependencyNodeH3, this.f361b.M[2].c());
                        b(this.f368i, this.f367h, this.f364e.f356g);
                        if (this.f361b.T()) {
                            b(this.k, this.f367h, this.f361b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f332d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.f368i, dependencyNodeH4, -this.f361b.M[3].c());
                        b(this.f367h, this.f368i, -this.f364e.f356g);
                    }
                    if (this.f361b.T()) {
                        b(this.k, this.f367h, this.f361b.o());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f332d != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.k, dependencyNodeH5, 0);
                        b(this.f367h, this.k, -this.f361b.o());
                        b(this.f368i, this.f367h, this.f364e.f356g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof b.e.b.j.g) || constraintWidget2.H() == null || this.f361b.n(ConstraintAnchor.Type.CENTER).f332d != null) {
                    return;
                }
                b(this.f367h, this.f361b.H().f344g.f367h, this.f361b.S());
                b(this.f368i, this.f367h, this.f364e.f356g);
                if (this.f361b.T()) {
                    b(this.k, this.f367h, this.f361b.o());
                    return;
                }
                return;
            }
        }
        if (z || this.f363d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f361b;
            int i2 = constraintWidget3.n;
            if (i2 == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.f344g.f364e;
                    this.f364e.l.add(fVar2);
                    fVar2.k.add(this.f364e);
                    f fVar3 = this.f364e;
                    fVar3.f351b = true;
                    fVar3.k.add(this.f367h);
                    this.f364e.k.add(this.f368i);
                }
            } else if (i2 == 3 && !constraintWidget3.Y()) {
                ConstraintWidget constraintWidget4 = this.f361b;
                if (constraintWidget4.m != 3) {
                    f fVar4 = constraintWidget4.f343f.f364e;
                    this.f364e.l.add(fVar4);
                    fVar4.k.add(this.f364e);
                    f fVar5 = this.f364e;
                    fVar5.f351b = true;
                    fVar5.k.add(this.f367h);
                    this.f364e.k.add(this.f368i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f361b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.M;
        if (constraintAnchorArr2[2].f332d != null && constraintAnchorArr2[3].f332d != null) {
            if (constraintWidget5.Y()) {
                this.f367h.f355f = this.f361b.M[2].c();
                this.f368i.f355f = -this.f361b.M[3].c();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f361b.M[2]);
                DependencyNode dependencyNodeH7 = h(this.f361b.M[3]);
                dependencyNodeH6.b(this);
                dependencyNodeH7.b(this);
                this.j = WidgetRun.RunType.CENTER;
            }
            if (this.f361b.T()) {
                c(this.k, this.f367h, 1, this.l);
            }
        } else if (constraintAnchorArr2[2].f332d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.f367h, dependencyNodeH8, this.f361b.M[2].c());
                c(this.f368i, this.f367h, 1, this.f364e);
                if (this.f361b.T()) {
                    c(this.k, this.f367h, 1, this.l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f363d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f361b.u() > 0.0f) {
                    i iVar = this.f361b.f343f;
                    if (iVar.f363d == dimensionBehaviour3) {
                        iVar.f364e.k.add(this.f364e);
                        this.f364e.l.add(this.f361b.f343f.f364e);
                        this.f364e.f350a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f332d != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.f368i, dependencyNodeH9, -this.f361b.M[3].c());
                c(this.f367h, this.f368i, -1, this.f364e);
                if (this.f361b.T()) {
                    c(this.k, this.f367h, 1, this.l);
                }
            }
        } else if (constraintAnchorArr2[4].f332d != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.k, dependencyNodeH10, 0);
                c(this.f367h, this.k, -1, this.l);
                c(this.f368i, this.f367h, 1, this.f364e);
            }
        } else if (!(constraintWidget5 instanceof b.e.b.j.g) && constraintWidget5.H() != null) {
            b(this.f367h, this.f361b.H().f344g.f367h, this.f361b.S());
            c(this.f368i, this.f367h, 1, this.f364e);
            if (this.f361b.T()) {
                c(this.k, this.f367h, 1, this.l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f363d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f361b.u() > 0.0f) {
                i iVar2 = this.f361b.f343f;
                if (iVar2.f363d == dimensionBehaviour5) {
                    iVar2.f364e.k.add(this.f364e);
                    this.f364e.l.add(this.f361b.f343f.f364e);
                    this.f364e.f350a = this;
                }
            }
        }
        if (this.f364e.l.size() == 0) {
            this.f364e.f352c = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f367h;
        if (dependencyNode.j) {
            this.f361b.H0(dependencyNode.f356g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f362c = null;
        this.f367h.c();
        this.f368i.c();
        this.k.c();
        this.f364e.c();
        this.f366g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f363d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f361b.n == 0;
    }

    public void q() {
        this.f366g = false;
        this.f367h.c();
        this.f367h.j = false;
        this.f368i.c();
        this.f368i.j = false;
        this.k.c();
        this.k.j = false;
        this.f364e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f361b.s();
    }
}
