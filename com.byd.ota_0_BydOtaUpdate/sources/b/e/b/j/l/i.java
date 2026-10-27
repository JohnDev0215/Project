package b.e.b.j.l;

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
        public static final /* synthetic */ int[] f1443a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f1443a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1443a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1443a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.f367h.f354e = DependencyNode.Type.LEFT;
        this.f368i.f354e = DependencyNode.Type.RIGHT;
        this.f365f = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.e.b.j.l.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(b.e.b.j.l.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.j.l.i.a(b.e.b.j.l.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidgetH;
        ConstraintWidget constraintWidgetH2;
        ConstraintWidget constraintWidget = this.f361b;
        if (constraintWidget.f339b) {
            this.f364e.d(constraintWidget.Q());
        }
        if (this.f364e.j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f363d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((constraintWidgetH = this.f361b.H()) != null && constraintWidgetH.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH.z() == dimensionBehaviour2)) {
                b(this.f367h, constraintWidgetH.f343f.f367h, this.f361b.E.c());
                b(this.f368i, constraintWidgetH.f343f.f368i, -this.f361b.G.c());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = this.f361b.z();
            this.f363d = dimensionBehaviourZ;
            if (dimensionBehaviourZ != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviourZ == dimensionBehaviour3 && (((constraintWidgetH2 = this.f361b.H()) != null && constraintWidgetH2.z() == ConstraintWidget.DimensionBehaviour.FIXED) || constraintWidgetH2.z() == dimensionBehaviour3)) {
                    int iQ = (constraintWidgetH2.Q() - this.f361b.E.c()) - this.f361b.G.c();
                    b(this.f367h, constraintWidgetH2.f343f.f367h, this.f361b.E.c());
                    b(this.f368i, constraintWidgetH2.f343f.f368i, -this.f361b.G.c());
                    this.f364e.d(iQ);
                    return;
                }
                if (this.f363d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f364e.d(this.f361b.Q());
                }
            }
        }
        f fVar = this.f364e;
        if (fVar.j) {
            ConstraintWidget constraintWidget2 = this.f361b;
            if (constraintWidget2.f339b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.M;
                if (constraintAnchorArr[0].f332d != null && constraintAnchorArr[1].f332d != null) {
                    if (constraintWidget2.W()) {
                        this.f367h.f355f = this.f361b.M[0].c();
                        this.f368i.f355f = -this.f361b.M[1].c();
                        return;
                    }
                    DependencyNode dependencyNodeH = h(this.f361b.M[0]);
                    if (dependencyNodeH != null) {
                        b(this.f367h, dependencyNodeH, this.f361b.M[0].c());
                    }
                    DependencyNode dependencyNodeH2 = h(this.f361b.M[1]);
                    if (dependencyNodeH2 != null) {
                        b(this.f368i, dependencyNodeH2, -this.f361b.M[1].c());
                    }
                    this.f367h.f351b = true;
                    this.f368i.f351b = true;
                    return;
                }
                if (constraintAnchorArr[0].f332d != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[0]);
                    if (dependencyNodeH3 != null) {
                        b(this.f367h, dependencyNodeH3, this.f361b.M[0].c());
                        b(this.f368i, this.f367h, this.f364e.f356g);
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[1].f332d != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[1]);
                    if (dependencyNodeH4 != null) {
                        b(this.f368i, dependencyNodeH4, -this.f361b.M[1].c());
                        b(this.f367h, this.f368i, -this.f364e.f356g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof b.e.b.j.g) || constraintWidget2.H() == null || this.f361b.n(ConstraintAnchor.Type.CENTER).f332d != null) {
                    return;
                }
                b(this.f367h, this.f361b.H().f343f.f367h, this.f361b.R());
                b(this.f368i, this.f367h, this.f364e.f356g);
                return;
            }
        }
        if (this.f363d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f361b;
            int i2 = constraintWidget3.m;
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
            } else if (i2 == 3) {
                if (constraintWidget3.n == 3) {
                    this.f367h.f350a = this;
                    this.f368i.f350a = this;
                    k kVar = constraintWidget3.f344g;
                    kVar.f367h.f350a = this;
                    kVar.f368i.f350a = this;
                    fVar.f350a = this;
                    if (constraintWidget3.Y()) {
                        this.f364e.l.add(this.f361b.f344g.f364e);
                        this.f361b.f344g.f364e.k.add(this.f364e);
                        k kVar2 = this.f361b.f344g;
                        kVar2.f364e.f350a = this;
                        this.f364e.l.add(kVar2.f367h);
                        this.f364e.l.add(this.f361b.f344g.f368i);
                        this.f361b.f344g.f367h.k.add(this.f364e);
                        this.f361b.f344g.f368i.k.add(this.f364e);
                    } else if (this.f361b.W()) {
                        this.f361b.f344g.f364e.l.add(this.f364e);
                        this.f364e.k.add(this.f361b.f344g.f364e);
                    } else {
                        this.f361b.f344g.f364e.l.add(this.f364e);
                    }
                } else {
                    f fVar4 = constraintWidget3.f344g.f364e;
                    fVar.l.add(fVar4);
                    fVar4.k.add(this.f364e);
                    this.f361b.f344g.f367h.k.add(this.f364e);
                    this.f361b.f344g.f368i.k.add(this.f364e);
                    f fVar5 = this.f364e;
                    fVar5.f351b = true;
                    fVar5.k.add(this.f367h);
                    this.f364e.k.add(this.f368i);
                    this.f367h.l.add(this.f364e);
                    this.f368i.l.add(this.f364e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f361b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.M;
        if (constraintAnchorArr2[0].f332d != null && constraintAnchorArr2[1].f332d != null) {
            if (constraintWidget4.W()) {
                this.f367h.f355f = this.f361b.M[0].c();
                this.f368i.f355f = -this.f361b.M[1].c();
                return;
            }
            DependencyNode dependencyNodeH5 = h(this.f361b.M[0]);
            DependencyNode dependencyNodeH6 = h(this.f361b.M[1]);
            dependencyNodeH5.b(this);
            dependencyNodeH6.b(this);
            this.j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].f332d != null) {
            DependencyNode dependencyNodeH7 = h(constraintAnchorArr2[0]);
            if (dependencyNodeH7 != null) {
                b(this.f367h, dependencyNodeH7, this.f361b.M[0].c());
                c(this.f368i, this.f367h, 1, this.f364e);
                return;
            }
            return;
        }
        if (constraintAnchorArr2[1].f332d != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[1]);
            if (dependencyNodeH8 != null) {
                b(this.f368i, dependencyNodeH8, -this.f361b.M[1].c());
                c(this.f367h, this.f368i, -1, this.f364e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof b.e.b.j.g) || constraintWidget4.H() == null) {
            return;
        }
        b(this.f367h, this.f361b.H().f343f.f367h, this.f361b.R());
        c(this.f368i, this.f367h, 1, this.f364e);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f367h;
        if (dependencyNode.j) {
            this.f361b.G0(dependencyNode.f356g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f362c = null;
        this.f367h.c();
        this.f368i.c();
        this.f364e.c();
        this.f366g = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return this.f363d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f361b.m == 0;
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
        this.f366g = false;
        this.f367h.c();
        this.f367h.j = false;
        this.f368i.c();
        this.f368i.j = false;
        this.f364e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f361b.s();
    }
}
