package x;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends WidgetRun {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int[] f5706k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5707a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f5707a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5707a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5707a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.f906h.f891e = DependencyNode.Type.LEFT;
        this.f907i.f891e = DependencyNode.Type.RIGHT;
        this.f904f = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, x.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(x.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x.i.a(x.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f900b;
        if (constraintWidget.f835b) {
            this.f903e.d(constraintWidget.Q());
        }
        if (this.f903e.f896j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f902d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.f900b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.f906h, constraintWidgetH.f843f.f906h, this.f900b.E.c());
                b(this.f907i, constraintWidgetH.f843f.f907i, -this.f900b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.f900b.z();
            this.f902d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.f900b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.f900b.E.c()) - this.f900b.G.c();
                    b(this.f906h, constraintWidgetH2.f843f.f906h, this.f900b.E.c());
                    b(this.f907i, constraintWidgetH2.f843f.f907i, -this.f900b.G.c());
                    this.f903e.d(iQ);
                    return;
                }
                if (this.f902d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f903e.d(this.f900b.Q());
                }
            }
        }
        f fVar = this.f903e;
        if (fVar.f896j) {
            ConstraintWidget constraintWidget2 = this.f900b;
            if (constraintWidget2.f835b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].f828d != null && constraintAnchorArr[1].f828d != null) {
                    if (constraintWidget2.W()) {
                        this.f906h.f892f = this.f900b.M[0].c();
                        this.f907i.f892f = -this.f900b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f900b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.f906h, dependencyNodeH, this.f900b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f900b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.f907i, dependencyNodeH2, -this.f900b.M[1].c());
                    }
                    this.f906h.f888b = true;
                    this.f907i.f888b = true;
                    return;
                }
                if (constraintAnchorArr[0].f828d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.f906h, dependencyNodeH3, this.f900b.M[0].c());
                        b(this.f907i, this.f906h, this.f903e.f893g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f828d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.f907i, dependencyNodeH4, -this.f900b.M[1].c());
                        b(this.f906h, this.f907i, -this.f903e.f893g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof w.g) || constraintWidget2.H() == null || this.f900b.n(ConstraintAnchor.Type.CENTER).f828d != null) {
                    return;
                }
                b(this.f906h, this.f900b.H().f843f.f906h, this.f900b.R());
                b(this.f907i, this.f906h, this.f903e.f893g);
                return;
            }
        }
        if (this.f902d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f900b;
            int i3 = constraintWidget3.f857m;
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
            } else if (i3 == 3) {
                if (constraintWidget3.f859n == 3) {
                    this.f906h.f887a = this;
                    this.f907i.f887a = this;
                    k kVar = constraintWidget3.f845g;
                    kVar.f906h.f887a = this;
                    kVar.f907i.f887a = this;
                    fVar.f887a = this;
                    if (constraintWidget3.Y()) {
                        this.f903e.f898l.add(this.f900b.f845g.f903e);
                        this.f900b.f845g.f903e.f897k.add(this.f903e);
                        k kVar2 = this.f900b.f845g;
                        kVar2.f903e.f887a = this;
                        this.f903e.f898l.add(kVar2.f906h);
                        this.f903e.f898l.add(this.f900b.f845g.f907i);
                        this.f900b.f845g.f906h.f897k.add(this.f903e);
                        this.f900b.f845g.f907i.f897k.add(this.f903e);
                    } else if (this.f900b.W()) {
                        this.f900b.f845g.f903e.f898l.add(this.f903e);
                        this.f903e.f897k.add(this.f900b.f845g.f903e);
                    } else {
                        this.f900b.f845g.f903e.f898l.add(this.f903e);
                    }
                } else {
                    f fVar4 = constraintWidget3.f845g.f903e;
                    fVar.f898l.add(fVar4);
                    fVar4.f897k.add(this.f903e);
                    this.f900b.f845g.f906h.f897k.add(this.f903e);
                    this.f900b.f845g.f907i.f897k.add(this.f903e);
                    f fVar5 = this.f903e;
                    fVar5.f888b = true;
                    fVar5.f897k.add(this.f906h);
                    this.f903e.f897k.add(this.f907i);
                    this.f906h.f898l.add(this.f903e);
                    this.f907i.f898l.add(this.f903e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f900b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].f828d != null && constraintAnchorArr2[1].f828d != null) {
            if (constraintWidget4.W()) {
                this.f906h.f892f = this.f900b.M[0].c();
                this.f907i.f892f = -this.f900b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f900b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.f900b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.f908j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f828d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.f906h, dependencyNodeH7, this.f900b.M[0].c());
                c(this.f907i, this.f906h, 1, this.f903e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f828d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.f907i, dependencyNodeH8, -this.f900b.M[1].c());
                c(this.f906h, this.f907i, -1, this.f903e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof w.g) || constraintWidget4.H() == null) {
            return;
        }
        b(this.f906h, this.f900b.H().f843f.f906h, this.f900b.R());
        c(this.f907i, this.f906h, 1, this.f903e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f906h;
        if (dependencyNode.f896j) {
            this.f900b.G0(dependencyNode.f893g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f901c = null;
        this.f906h.c();
        this.f907i.c();
        this.f903e.c();
        this.f905g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f902d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f900b.f857m == 0;
    }

    public final void q(int[] iArr, int i3, int i4, int i5, int i6, float f3, int i7) {
        int i8 = i4 - i3;
        int i9 = i6 - i5;
        if (i7 != -1) {
            if (i7 == 0) {
                iArr[0] = (int) ((i9 * f3) + 0.5f);
                iArr[1] = i9;
                return;
            } else {
                if (i7 != 1) {
                    return;
                }
                iArr[0] = i8;
                iArr[1] = (int) ((i8 * f3) + 0.5f);
                return;
            }
        }
        int i10 = (int) ((i9 * f3) + 0.5f);
        int i11 = (int) ((i8 / f3) + 0.5f);
        if (i10 <= i8 && i9 <= i9) {
            iArr[0] = i10;
            iArr[1] = i9;
        } else {
            if (i8 > i8 || i11 > i9) {
                return;
            }
            iArr[0] = i8;
            iArr[1] = i11;
        }
    }

    public void r() {
        this.f905g = false;
        this.f906h.c();
        this.f906h.f896j = false;
        this.f907i.c();
        this.f907i.f896j = false;
        this.f903e.f896j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f900b.s();
    }
}
