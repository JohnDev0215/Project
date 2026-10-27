package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends WidgetRun {
    public static int[] k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2255a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f2255a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2255a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2255a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.f656h.f643e = DependencyNode.Type.LEFT;
        this.f657i.f643e = DependencyNode.Type.RIGHT;
        this.f654f = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.f.b.j.l.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(b.f.b.j.l.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.b.j.l.i.a(b.f.b.j.l.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f650b;
        if (constraintWidget.f628b) {
            this.f653e.d(constraintWidget.Q());
        }
        if (this.f653e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f652d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.f650b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.f656h, constraintWidgetH.f632f.f656h, this.f650b.E.c());
                b(this.f657i, constraintWidgetH.f632f.f657i, -this.f650b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.f650b.z();
            this.f652d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.f650b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.f650b.E.c()) - this.f650b.G.c();
                    b(this.f656h, constraintWidgetH2.f632f.f656h, this.f650b.E.c());
                    b(this.f657i, constraintWidgetH2.f632f.f657i, -this.f650b.G.c());
                    this.f653e.d(iQ);
                    return;
                }
                if (this.f652d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f653e.d(this.f650b.Q());
                }
            }
        }
        f fVar = this.f653e;
        if (fVar.j) {
            ConstraintWidget constraintWidget2 = this.f650b;
            if (constraintWidget2.f628b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].f621d != null && constraintAnchorArr[1].f621d != null) {
                    if (constraintWidget2.W()) {
                        this.f656h.f644f = this.f650b.M[0].c();
                        this.f657i.f644f = -this.f650b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f650b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.f656h, dependencyNodeH, this.f650b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f650b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.f657i, dependencyNodeH2, -this.f650b.M[1].c());
                    }
                    this.f656h.f640b = true;
                    this.f657i.f640b = true;
                    return;
                }
                if (constraintAnchorArr[0].f621d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.f656h, dependencyNodeH3, this.f650b.M[0].c());
                        b(this.f657i, this.f656h, this.f653e.f645g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f621d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.f657i, dependencyNodeH4, -this.f650b.M[1].c());
                        b(this.f656h, this.f657i, -this.f653e.f645g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof b.f.b.j.g) || constraintWidget2.H() == null || this.f650b.n(ConstraintAnchor.Type.CENTER).f621d != null) {
                    return;
                }
                b(this.f656h, this.f650b.H().f632f.f656h, this.f650b.R());
                b(this.f657i, this.f656h, this.f653e.f645g);
                return;
            }
        }
        if (this.f652d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f650b;
            int i2 = constraintWidget3.m;
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
            } else if (i2 == 3) {
                if (constraintWidget3.n == 3) {
                    this.f656h.f639a = this;
                    this.f657i.f639a = this;
                    k kVar = constraintWidget3.f633g;
                    kVar.f656h.f639a = this;
                    kVar.f657i.f639a = this;
                    fVar.f639a = this;
                    if (constraintWidget3.Y()) {
                        this.f653e.l.add(this.f650b.f633g.f653e);
                        this.f650b.f633g.f653e.k.add(this.f653e);
                        k kVar2 = this.f650b.f633g;
                        kVar2.f653e.f639a = this;
                        this.f653e.l.add(kVar2.f656h);
                        this.f653e.l.add(this.f650b.f633g.f657i);
                        this.f650b.f633g.f656h.k.add(this.f653e);
                        this.f650b.f633g.f657i.k.add(this.f653e);
                    } else if (this.f650b.W()) {
                        this.f650b.f633g.f653e.l.add(this.f653e);
                        this.f653e.k.add(this.f650b.f633g.f653e);
                    } else {
                        this.f650b.f633g.f653e.l.add(this.f653e);
                    }
                } else {
                    f fVar4 = constraintWidget3.f633g.f653e;
                    fVar.l.add(fVar4);
                    fVar4.k.add(this.f653e);
                    this.f650b.f633g.f656h.k.add(this.f653e);
                    this.f650b.f633g.f657i.k.add(this.f653e);
                    f fVar5 = this.f653e;
                    fVar5.f640b = true;
                    fVar5.k.add(this.f656h);
                    this.f653e.k.add(this.f657i);
                    this.f656h.l.add(this.f653e);
                    this.f657i.l.add(this.f653e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f650b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].f621d != null && constraintAnchorArr2[1].f621d != null) {
            if (constraintWidget4.W()) {
                this.f656h.f644f = this.f650b.M[0].c();
                this.f657i.f644f = -this.f650b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f650b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.f650b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f621d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.f656h, dependencyNodeH7, this.f650b.M[0].c());
                c(this.f657i, this.f656h, 1, this.f653e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f621d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.f657i, dependencyNodeH8, -this.f650b.M[1].c());
                c(this.f656h, this.f657i, -1, this.f653e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof b.f.b.j.g) || constraintWidget4.H() == null) {
            return;
        }
        b(this.f656h, this.f650b.H().f632f.f656h, this.f650b.R());
        c(this.f657i, this.f656h, 1, this.f653e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f656h;
        if (dependencyNode.j) {
            this.f650b.G0(dependencyNode.f645g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f651c = null;
        this.f656h.c();
        this.f657i.c();
        this.f653e.c();
        this.f655g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f652d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f650b.m == 0;
    }

    public final void q(int[] iArr, int i2, int i3, int i4, int i5, float f2, int i6) {
        int i7 = i3 - i2;
        int i8 = i5 - i4;
        if (i6 != -1) {
            if (i6 == 0) {
                iArr[0] = (int) ((i8 * f2) + 0.5f);
                iArr[1] = i8;
                return;
            } else {
                if (i6 != 1) {
                    return;
                }
                iArr[0] = i7;
                iArr[1] = (int) ((i7 * f2) + 0.5f);
                return;
            }
        }
        int i9 = (int) ((i8 * f2) + 0.5f);
        int i10 = (int) ((i7 / f2) + 0.5f);
        if (i9 <= i7 && i8 <= i8) {
            iArr[0] = i9;
            iArr[1] = i8;
        } else {
            if (i7 > i7 || i10 > i8) {
                return;
            }
            iArr[0] = i7;
            iArr[1] = i10;
        }
    }

    public void r() {
        this.f655g = false;
        this.f656h.c();
        this.f656h.j = false;
        this.f657i.c();
        this.f657i.j = false;
        this.f653e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f650b.s();
    }
}
