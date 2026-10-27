package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends WidgetRun {
    private static int[] k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f859a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f859a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f859a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f859a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public k(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.h.f834e = DependencyNode.Type.LEFT;
        this.i.f834e = DependencyNode.Type.RIGHT;
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
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.constraintlayout.core.widgets.analyzer.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.k.a(androidx.constraintlayout.core.widgets.analyzer.d):void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidgetM;
        ConstraintWidget constraintWidgetM2;
        ConstraintWidget constraintWidget = this.f837b;
        if (constraintWidget.f823b) {
            this.f840e.d(constraintWidget.Y());
        }
        if (this.f840e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f839d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (constraintWidgetM = this.f837b.M()) != null && (constraintWidgetM.C() == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidgetM.C() == dimensionBehaviour2)) {
                b(this.h, constraintWidgetM.f.h, this.f837b.R.f());
                b(this.i, constraintWidgetM.f.i, -this.f837b.T.f());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourC = this.f837b.C();
            this.f839d = dimensionBehaviourC;
            if (dimensionBehaviourC != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourC == dimensionBehaviour3 && (constraintWidgetM2 = this.f837b.M()) != null && (constraintWidgetM2.C() == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidgetM2.C() == dimensionBehaviour3)) {
                    int iY = (constraintWidgetM2.Y() - this.f837b.R.f()) - this.f837b.T.f();
                    b(this.h, constraintWidgetM2.f.h, this.f837b.R.f());
                    b(this.i, constraintWidgetM2.f.i, -this.f837b.T.f());
                    this.f840e.d(iY);
                    return;
                }
                if (this.f839d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f840e.d(this.f837b.Y());
                }
            }
        }
        f fVar = this.f840e;
        if (fVar.j) {
            ConstraintWidget constraintWidget2 = this.f837b;
            if (constraintWidget2.f823b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.Z;
                if (constraintAnchorArr[0].f != null && constraintAnchorArr[1].f != null) {
                    if (constraintWidget2.k0()) {
                        this.h.f = this.f837b.Z[0].f();
                        this.i.f = -this.f837b.Z[1].f();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f837b.Z[0]);
                    if (dependencyNodeH != null) {
                        b(this.h, dependencyNodeH, this.f837b.Z[0].f());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f837b.Z[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.i, dependencyNodeH2, -this.f837b.Z[1].f());
                    }
                    this.h.f831b = true;
                    this.i.f831b = true;
                    return;
                }
                if (constraintAnchorArr[0].f != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.h, dependencyNodeH3, this.f837b.Z[0].f());
                        b(this.i, this.h, this.f840e.g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.f837b.Z[1].f());
                        b(this.h, this.i, -this.f840e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof androidx.constraintlayout.core.widgets.g) || constraintWidget2.M() == null || this.f837b.q(ConstraintAnchor.Type.CENTER).f != null) {
                    return;
                }
                b(this.h, this.f837b.M().f.h, this.f837b.Z());
                b(this.i, this.h, this.f840e.g);
                return;
            }
        }
        if (this.f839d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f837b;
            int i = constraintWidget3.x;
            if (i == 2) {
                ConstraintWidget constraintWidgetM3 = constraintWidget3.M();
                if (constraintWidgetM3 != null) {
                    f fVar2 = constraintWidgetM3.g.f840e;
                    this.f840e.l.add(fVar2);
                    fVar2.k.add(this.f840e);
                    f fVar3 = this.f840e;
                    fVar3.f831b = true;
                    fVar3.k.add(this.h);
                    this.f840e.k.add(this.i);
                }
            } else if (i == 3) {
                if (constraintWidget3.y == 3) {
                    this.h.f830a = this;
                    this.i.f830a = this;
                    m mVar = constraintWidget3.g;
                    mVar.h.f830a = this;
                    mVar.i.f830a = this;
                    fVar.f830a = this;
                    if (constraintWidget3.m0()) {
                        this.f840e.l.add(this.f837b.g.f840e);
                        this.f837b.g.f840e.k.add(this.f840e);
                        m mVar2 = this.f837b.g;
                        mVar2.f840e.f830a = this;
                        this.f840e.l.add(mVar2.h);
                        this.f840e.l.add(this.f837b.g.i);
                        this.f837b.g.h.k.add(this.f840e);
                        this.f837b.g.i.k.add(this.f840e);
                    } else if (this.f837b.k0()) {
                        this.f837b.g.f840e.l.add(this.f840e);
                        this.f840e.k.add(this.f837b.g.f840e);
                    } else {
                        this.f837b.g.f840e.l.add(this.f840e);
                    }
                } else {
                    f fVar4 = constraintWidget3.g.f840e;
                    fVar.l.add(fVar4);
                    fVar4.k.add(this.f840e);
                    this.f837b.g.h.k.add(this.f840e);
                    this.f837b.g.i.k.add(this.f840e);
                    f fVar5 = this.f840e;
                    fVar5.f831b = true;
                    fVar5.k.add(this.h);
                    this.f840e.k.add(this.i);
                    this.h.l.add(this.f840e);
                    this.i.l.add(this.f840e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f837b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.Z;
        if (constraintAnchorArr2[0].f != null && constraintAnchorArr2[1].f != null) {
            if (constraintWidget4.k0()) {
                this.h.f = this.f837b.Z[0].f();
                this.i.f = -this.f837b.Z[1].f();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f837b.Z[0]);
            DependencyNode dependencyNodeH6 = h(this.f837b.Z[1]);
            if (dependencyNodeH5 != null) {
                dependencyNodeH5.b(this);
            }
            if (dependencyNodeH6 != null) {
                dependencyNodeH6.b(this);
            }
            this.j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.h, dependencyNodeH7, this.f837b.Z[0].f());
                c(this.i, this.h, 1, this.f840e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.i, dependencyNodeH8, -this.f837b.Z[1].f());
                c(this.h, this.i, -1, this.f840e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof androidx.constraintlayout.core.widgets.g) || constraintWidget4.M() == null) {
            return;
        }
        b(this.h, this.f837b.M().f.h, this.f837b.Z());
        c(this.i, this.h, 1, this.f840e);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.j) {
            this.f837b.p1(dependencyNode.g);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f838c = null;
        this.h.c();
        this.i.c();
        this.f840e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f839d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f837b.x == 0;
    }

    void r() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.f840e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f837b.v();
    }
}
