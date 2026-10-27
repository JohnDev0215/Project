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
        static final /* synthetic */ int[] f685a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f685a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f685a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f685a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.h.e = DependencyNode.Type.LEFT;
        this.i.e = DependencyNode.Type.RIGHT;
        this.f = 0;
    }

    private void q(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
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
        ConstraintWidget constraintWidget = this.f669b;
        if (constraintWidget.f657b) {
            this.e.d(constraintWidget.Q());
        }
        if (this.e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f671d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.f669b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.h, constraintWidgetH.f.h, this.f669b.E.c());
                b(this.i, constraintWidgetH.f.i, -this.f669b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.f669b.z();
            this.f671d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.f669b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.f669b.E.c()) - this.f669b.G.c();
                    b(this.h, constraintWidgetH2.f.h, this.f669b.E.c());
                    b(this.i, constraintWidgetH2.f.i, -this.f669b.G.c());
                    this.e.d(iQ);
                    return;
                }
                if (this.f671d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.e.d(this.f669b.Q());
                }
            }
        }
        f fVar = this.e;
        if (fVar.j) {
            ConstraintWidget constraintWidget2 = this.f669b;
            if (constraintWidget2.f657b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].f653d != null && constraintAnchorArr[1].f653d != null) {
                    if (constraintWidget2.W()) {
                        this.h.f = this.f669b.M[0].c();
                        this.i.f = -this.f669b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f669b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.h, dependencyNodeH, this.f669b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f669b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.i, dependencyNodeH2, -this.f669b.M[1].c());
                    }
                    this.h.f664b = true;
                    this.i.f664b = true;
                    return;
                }
                if (constraintAnchorArr[0].f653d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.h, dependencyNodeH3, this.f669b.M[0].c());
                        b(this.i, this.h, this.e.g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f653d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.f669b.M[1].c());
                        b(this.h, this.i, -this.e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof androidx.constraintlayout.solver.widgets.g) || constraintWidget2.H() == null || this.f669b.n(ConstraintAnchor.Type.CENTER).f653d != null) {
                    return;
                }
                b(this.h, this.f669b.H().f.h, this.f669b.R());
                b(this.i, this.h, this.e.g);
                return;
            }
        }
        if (this.f671d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f669b;
            int i = constraintWidget3.m;
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
            } else if (i == 3) {
                if (constraintWidget3.n == 3) {
                    this.h.f663a = this;
                    this.i.f663a = this;
                    k kVar = constraintWidget3.g;
                    kVar.h.f663a = this;
                    kVar.i.f663a = this;
                    fVar.f663a = this;
                    if (constraintWidget3.Y()) {
                        this.e.l.add(this.f669b.g.e);
                        this.f669b.g.e.k.add(this.e);
                        k kVar2 = this.f669b.g;
                        kVar2.e.f663a = this;
                        this.e.l.add(kVar2.h);
                        this.e.l.add(this.f669b.g.i);
                        this.f669b.g.h.k.add(this.e);
                        this.f669b.g.i.k.add(this.e);
                    } else if (this.f669b.W()) {
                        this.f669b.g.e.l.add(this.e);
                        this.e.k.add(this.f669b.g.e);
                    } else {
                        this.f669b.g.e.l.add(this.e);
                    }
                } else {
                    f fVar4 = constraintWidget3.g.e;
                    fVar.l.add(fVar4);
                    fVar4.k.add(this.e);
                    this.f669b.g.h.k.add(this.e);
                    this.f669b.g.i.k.add(this.e);
                    f fVar5 = this.e;
                    fVar5.f664b = true;
                    fVar5.k.add(this.h);
                    this.e.k.add(this.i);
                    this.h.l.add(this.e);
                    this.i.l.add(this.e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f669b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].f653d != null && constraintAnchorArr2[1].f653d != null) {
            if (constraintWidget4.W()) {
                this.h.f = this.f669b.M[0].c();
                this.i.f = -this.f669b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f669b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.f669b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f653d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.h, dependencyNodeH7, this.f669b.M[0].c());
                c(this.i, this.h, 1, this.e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f653d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.i, dependencyNodeH8, -this.f669b.M[1].c());
                c(this.h, this.i, -1, this.e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof androidx.constraintlayout.solver.widgets.g) || constraintWidget4.H() == null) {
            return;
        }
        b(this.h, this.f669b.H().f.h, this.f669b.R());
        c(this.i, this.h, 1, this.e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.j) {
            this.f669b.G0(dependencyNode.g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.f670c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f671d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f669b.m == 0;
    }

    void r() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f669b.s();
    }
}
