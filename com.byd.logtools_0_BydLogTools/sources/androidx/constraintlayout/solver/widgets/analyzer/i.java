package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends WidgetRun {
    private static int[] k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1015a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f1015a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1015a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1015a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.f993h.f981e = DependencyNode.Type.LEFT;
        this.i.f981e = DependencyNode.Type.RIGHT;
        this.f991f = 0;
    }

    private void q(int[] iArr, int i, int i2, int i3, int i4, float f2, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f2) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f2) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f2) + 0.5f);
        int i9 = (int) ((i6 / f2) + 0.5f);
        if (i8 <= i6 && i7 <= i7) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else {
            if (i6 > i6 || i9 > i7) {
                return;
            }
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.constraintlayout.solver.widgets.analyzer.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.i.a(androidx.constraintlayout.solver.widgets.analyzer.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f987b;
        if (constraintWidget.f967b) {
            this.f990e.d(constraintWidget.Q());
        }
        if (this.f990e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f989d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.f987b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.f993h, constraintWidgetH.f971f.f993h, this.f987b.E.c());
                b(this.i, constraintWidgetH.f971f.i, -this.f987b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.f987b.z();
            this.f989d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.f987b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.f987b.E.c()) - this.f987b.G.c();
                    b(this.f993h, constraintWidgetH2.f971f.f993h, this.f987b.E.c());
                    b(this.i, constraintWidgetH2.f971f.i, -this.f987b.G.c());
                    this.f990e.d(iQ);
                    return;
                }
                if (this.f989d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f990e.d(this.f987b.Q());
                }
            }
        }
        f fVar = this.f990e;
        if (fVar.j) {
            ConstraintWidget constraintWidget2 = this.f987b;
            if (constraintWidget2.f967b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].f960d != null && constraintAnchorArr[1].f960d != null) {
                    if (constraintWidget2.W()) {
                        this.f993h.f982f = this.f987b.M[0].c();
                        this.i.f982f = -this.f987b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f987b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.f993h, dependencyNodeH, this.f987b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f987b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.i, dependencyNodeH2, -this.f987b.M[1].c());
                    }
                    this.f993h.f978b = true;
                    this.i.f978b = true;
                    return;
                }
                if (constraintAnchorArr[0].f960d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.f993h, dependencyNodeH3, this.f987b.M[0].c());
                        b(this.i, this.f993h, this.f990e.f983g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f960d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.f987b.M[1].c());
                        b(this.f993h, this.i, -this.f990e.f983g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof androidx.constraintlayout.solver.widgets.g) || constraintWidget2.H() == null || this.f987b.n(ConstraintAnchor.Type.CENTER).f960d != null) {
                    return;
                }
                b(this.f993h, this.f987b.H().f971f.f993h, this.f987b.R());
                b(this.i, this.f993h, this.f990e.f983g);
                return;
            }
        }
        if (this.f989d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f987b;
            int i = constraintWidget3.m;
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
            } else if (i == 3) {
                if (constraintWidget3.n == 3) {
                    this.f993h.f977a = this;
                    this.i.f977a = this;
                    k kVar = constraintWidget3.f972g;
                    kVar.f993h.f977a = this;
                    kVar.i.f977a = this;
                    fVar.f977a = this;
                    if (constraintWidget3.Y()) {
                        this.f990e.l.add(this.f987b.f972g.f990e);
                        this.f987b.f972g.f990e.k.add(this.f990e);
                        k kVar2 = this.f987b.f972g;
                        kVar2.f990e.f977a = this;
                        this.f990e.l.add(kVar2.f993h);
                        this.f990e.l.add(this.f987b.f972g.i);
                        this.f987b.f972g.f993h.k.add(this.f990e);
                        this.f987b.f972g.i.k.add(this.f990e);
                    } else if (this.f987b.W()) {
                        this.f987b.f972g.f990e.l.add(this.f990e);
                        this.f990e.k.add(this.f987b.f972g.f990e);
                    } else {
                        this.f987b.f972g.f990e.l.add(this.f990e);
                    }
                } else {
                    f fVar4 = constraintWidget3.f972g.f990e;
                    fVar.l.add(fVar4);
                    fVar4.k.add(this.f990e);
                    this.f987b.f972g.f993h.k.add(this.f990e);
                    this.f987b.f972g.i.k.add(this.f990e);
                    f fVar5 = this.f990e;
                    fVar5.f978b = true;
                    fVar5.k.add(this.f993h);
                    this.f990e.k.add(this.i);
                    this.f993h.l.add(this.f990e);
                    this.i.l.add(this.f990e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f987b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].f960d != null && constraintAnchorArr2[1].f960d != null) {
            if (constraintWidget4.W()) {
                this.f993h.f982f = this.f987b.M[0].c();
                this.i.f982f = -this.f987b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f987b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.f987b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f960d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.f993h, dependencyNodeH7, this.f987b.M[0].c());
                c(this.i, this.f993h, 1, this.f990e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f960d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.i, dependencyNodeH8, -this.f987b.M[1].c());
                c(this.f993h, this.i, -1, this.f990e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof androidx.constraintlayout.solver.widgets.g) || constraintWidget4.H() == null) {
            return;
        }
        b(this.f993h, this.f987b.H().f971f.f993h, this.f987b.R());
        c(this.i, this.f993h, 1, this.f990e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f993h;
        if (dependencyNode.j) {
            this.f987b.G0(dependencyNode.f983g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.f988c = null;
        this.f993h.c();
        this.i.c();
        this.f990e.c();
        this.f992g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f989d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f987b.m == 0;
    }

    void r() {
        this.f992g = false;
        this.f993h.c();
        this.f993h.j = false;
        this.i.c();
        this.i.j = false;
        this.f990e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f987b.s();
    }
}
