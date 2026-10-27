package a0;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends WidgetRun {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int[] f27k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f28a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f28a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f28a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f28a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.f997h.f982e = DependencyNode.Type.LEFT;
        this.f998i.f982e = DependencyNode.Type.RIGHT;
        this.f995f = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, a0.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(a0.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a0.i.a(a0.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f991b;
        if (constraintWidget.f926b) {
            this.f994e.d(constraintWidget.Q());
        }
        if (this.f994e.f987j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f993d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.f991b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.f997h, constraintWidgetH.f934f.f997h, this.f991b.E.c());
                b(this.f998i, constraintWidgetH.f934f.f998i, -this.f991b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.f991b.z();
            this.f993d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.f991b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.f991b.E.c()) - this.f991b.G.c();
                    b(this.f997h, constraintWidgetH2.f934f.f997h, this.f991b.E.c());
                    b(this.f998i, constraintWidgetH2.f934f.f998i, -this.f991b.G.c());
                    this.f994e.d(iQ);
                    return;
                }
                if (this.f993d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f994e.d(this.f991b.Q());
                }
            }
        }
        f fVar = this.f994e;
        if (fVar.f987j) {
            ConstraintWidget constraintWidget2 = this.f991b;
            if (constraintWidget2.f926b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].f919d != null && constraintAnchorArr[1].f919d != null) {
                    if (constraintWidget2.W()) {
                        this.f997h.f983f = this.f991b.M[0].c();
                        this.f998i.f983f = -this.f991b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f991b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.f997h, dependencyNodeH, this.f991b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f991b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.f998i, dependencyNodeH2, -this.f991b.M[1].c());
                    }
                    this.f997h.f979b = true;
                    this.f998i.f979b = true;
                    return;
                }
                if (constraintAnchorArr[0].f919d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.f997h, dependencyNodeH3, this.f991b.M[0].c());
                        b(this.f998i, this.f997h, this.f994e.f984g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f919d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.f998i, dependencyNodeH4, -this.f991b.M[1].c());
                        b(this.f997h, this.f998i, -this.f994e.f984g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof z.g) || constraintWidget2.H() == null || this.f991b.n(ConstraintAnchor.Type.CENTER).f919d != null) {
                    return;
                }
                b(this.f997h, this.f991b.H().f934f.f997h, this.f991b.R());
                b(this.f998i, this.f997h, this.f994e.f984g);
                return;
            }
        }
        if (this.f993d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f991b;
            int i8 = constraintWidget3.f948m;
            if (i8 == 2) {
                ConstraintWidget constraintWidgetH3 = constraintWidget3.H();
                if (constraintWidgetH3 != null) {
                    f fVar2 = constraintWidgetH3.f936g.f994e;
                    this.f994e.f989l.add(fVar2);
                    fVar2.f988k.add(this.f994e);
                    f fVar3 = this.f994e;
                    fVar3.f979b = true;
                    fVar3.f988k.add(this.f997h);
                    this.f994e.f988k.add(this.f998i);
                }
            } else if (i8 == 3) {
                if (constraintWidget3.f950n == 3) {
                    this.f997h.f978a = this;
                    this.f998i.f978a = this;
                    k kVar = constraintWidget3.f936g;
                    kVar.f997h.f978a = this;
                    kVar.f998i.f978a = this;
                    fVar.f978a = this;
                    if (constraintWidget3.Y()) {
                        this.f994e.f989l.add(this.f991b.f936g.f994e);
                        this.f991b.f936g.f994e.f988k.add(this.f994e);
                        k kVar2 = this.f991b.f936g;
                        kVar2.f994e.f978a = this;
                        this.f994e.f989l.add(kVar2.f997h);
                        this.f994e.f989l.add(this.f991b.f936g.f998i);
                        this.f991b.f936g.f997h.f988k.add(this.f994e);
                        this.f991b.f936g.f998i.f988k.add(this.f994e);
                    } else if (this.f991b.W()) {
                        this.f991b.f936g.f994e.f989l.add(this.f994e);
                        this.f994e.f988k.add(this.f991b.f936g.f994e);
                    } else {
                        this.f991b.f936g.f994e.f989l.add(this.f994e);
                    }
                } else {
                    f fVar4 = constraintWidget3.f936g.f994e;
                    fVar.f989l.add(fVar4);
                    fVar4.f988k.add(this.f994e);
                    this.f991b.f936g.f997h.f988k.add(this.f994e);
                    this.f991b.f936g.f998i.f988k.add(this.f994e);
                    f fVar5 = this.f994e;
                    fVar5.f979b = true;
                    fVar5.f988k.add(this.f997h);
                    this.f994e.f988k.add(this.f998i);
                    this.f997h.f989l.add(this.f994e);
                    this.f998i.f989l.add(this.f994e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f991b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].f919d != null && constraintAnchorArr2[1].f919d != null) {
            if (constraintWidget4.W()) {
                this.f997h.f983f = this.f991b.M[0].c();
                this.f998i.f983f = -this.f991b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f991b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.f991b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.f999j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f919d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.f997h, dependencyNodeH7, this.f991b.M[0].c());
                c(this.f998i, this.f997h, 1, this.f994e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f919d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.f998i, dependencyNodeH8, -this.f991b.M[1].c());
                c(this.f997h, this.f998i, -1, this.f994e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof z.g) || constraintWidget4.H() == null) {
            return;
        }
        b(this.f997h, this.f991b.H().f934f.f997h, this.f991b.R());
        c(this.f998i, this.f997h, 1, this.f994e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f997h;
        if (dependencyNode.f987j) {
            this.f991b.G0(dependencyNode.f984g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f992c = null;
        this.f997h.c();
        this.f998i.c();
        this.f994e.c();
        this.f996g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f993d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f991b.f948m == 0;
    }

    public final void q(int[] iArr, int i8, int i9, int i10, int i11, float f8, int i12) {
        int i13 = i9 - i8;
        int i14 = i11 - i10;
        if (i12 != -1) {
            if (i12 == 0) {
                iArr[0] = (int) ((i14 * f8) + 0.5f);
                iArr[1] = i14;
                return;
            } else {
                if (i12 != 1) {
                    return;
                }
                iArr[0] = i13;
                iArr[1] = (int) ((i13 * f8) + 0.5f);
                return;
            }
        }
        int i15 = (int) ((i14 * f8) + 0.5f);
        int i16 = (int) ((i13 / f8) + 0.5f);
        if (i15 <= i13 && i14 <= i14) {
            iArr[0] = i15;
            iArr[1] = i14;
        } else {
            if (i13 > i13 || i16 > i14) {
                return;
            }
            iArr[0] = i13;
            iArr[1] = i16;
        }
    }

    public void r() {
        this.f996g = false;
        this.f997h.c();
        this.f997h.f987j = false;
        this.f998i.c();
        this.f998i.f987j = false;
        this.f994e.f987j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f991b.s();
    }
}
