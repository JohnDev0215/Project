package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class m6 extends WidgetRun {
    public static int[] k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
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

    public m6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.h.e = DependencyNode.Type.LEFT;
        this.i.e = DependencyNode.Type.RIGHT;
        this.f = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, defpackage.h6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(defpackage.h6 r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.m6.a(h6):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget.b) {
            this.e.d(constraintWidget.Q());
        }
        if (this.e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.h, constraintWidgetH.f.h, this.b.E.c());
                b(this.i, constraintWidgetH.f.i, -this.b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.b.z();
            this.d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.b.E.c()) - this.b.G.c();
                    b(this.h, constraintWidgetH2.f.h, this.b.E.c());
                    b(this.i, constraintWidgetH2.f.i, -this.b.G.c());
                    this.e.d(iQ);
                    return;
                }
                if (this.d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.e.d(this.b.Q());
                }
            }
        }
        j6 j6Var = this.e;
        if (j6Var.j) {
            ConstraintWidget constraintWidget2 = this.b;
            if (constraintWidget2.b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].d != null && constraintAnchorArr[1].d != null) {
                    if (constraintWidget2.W()) {
                        this.h.f = this.b.M[0].c();
                        this.i.f = -this.b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.h, dependencyNodeH, this.b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.i, dependencyNodeH2, -this.b.M[1].c());
                    }
                    this.h.b = true;
                    this.i.b = true;
                    return;
                }
                if (constraintAnchorArr[0].d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.h, dependencyNodeH3, this.b.M[0].c());
                        b(this.i, this.h, this.e.g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.b.M[1].c());
                        b(this.h, this.i, -this.e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof z5) || constraintWidget2.H() == null || this.b.n(ConstraintAnchor.Type.CENTER).d != null) {
                    return;
                }
                b(this.h, this.b.H().f.h, this.b.R());
                b(this.i, this.h, this.e.g);
                return;
            }
        }
        if (this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.b;
            int i = constraintWidget3.m;
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
            } else if (i == 3) {
                if (constraintWidget3.n == 3) {
                    this.h.a = this;
                    this.i.a = this;
                    o6 o6Var = constraintWidget3.g;
                    o6Var.h.a = this;
                    o6Var.i.a = this;
                    j6Var.a = this;
                    if (constraintWidget3.Y()) {
                        this.e.l.add(this.b.g.e);
                        this.b.g.e.k.add(this.e);
                        o6 o6Var2 = this.b.g;
                        o6Var2.e.a = this;
                        this.e.l.add(o6Var2.h);
                        this.e.l.add(this.b.g.i);
                        this.b.g.h.k.add(this.e);
                        this.b.g.i.k.add(this.e);
                    } else if (this.b.W()) {
                        this.b.g.e.l.add(this.e);
                        this.e.k.add(this.b.g.e);
                    } else {
                        this.b.g.e.l.add(this.e);
                    }
                } else {
                    j6 j6Var4 = constraintWidget3.g.e;
                    j6Var.l.add(j6Var4);
                    j6Var4.k.add(this.e);
                    this.b.g.h.k.add(this.e);
                    this.b.g.i.k.add(this.e);
                    j6 j6Var5 = this.e;
                    j6Var5.b = true;
                    j6Var5.k.add(this.h);
                    this.e.k.add(this.i);
                    this.h.l.add(this.e);
                    this.i.l.add(this.e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].d != null && constraintAnchorArr2[1].d != null) {
            if (constraintWidget4.W()) {
                this.h.f = this.b.M[0].c();
                this.i.f = -this.b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.h, dependencyNodeH7, this.b.M[0].c());
                c(this.i, this.h, 1, this.e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.i, dependencyNodeH8, -this.b.M[1].c());
                c(this.h, this.i, -1, this.e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof z5) || constraintWidget4.H() == null) {
            return;
        }
        b(this.h, this.b.H().f.h, this.b.R());
        c(this.i, this.h, 1, this.e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.j) {
            this.b.G0(dependencyNode.g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.b.m == 0;
    }

    public final void q(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
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

    public void r() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.b.s();
    }
}
