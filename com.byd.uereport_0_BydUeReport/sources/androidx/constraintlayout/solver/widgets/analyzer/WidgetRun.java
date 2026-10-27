package androidx.constraintlayout.solver.widgets.analyzer;

import a0.d;
import a0.f;
import a0.j;
import a0.k;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f990a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f991b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f992c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget.DimensionBehaviour f993d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f994e = new f(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f995f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f996g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DependencyNode f997h = new DependencyNode(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DependencyNode f998i = new DependencyNode(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public RunType f999j = RunType.NONE;

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1000a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f1000a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1000a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1000a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1000a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1000a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f991b = constraintWidget;
    }

    @Override // a0.d
    public void a(d dVar) {
    }

    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i8) {
        dependencyNode.f989l.add(dependencyNode2);
        dependencyNode.f983f = i8;
        dependencyNode2.f988k.add(dependencyNode);
    }

    public final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i8, f fVar) {
        dependencyNode.f989l.add(dependencyNode2);
        dependencyNode.f989l.add(this.f994e);
        dependencyNode.f985h = i8;
        dependencyNode.f986i = fVar;
        dependencyNode2.f988k.add(dependencyNode);
        fVar.f988k.add(dependencyNode);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i8, int i9) {
        int iMax;
        if (i9 == 0) {
            ConstraintWidget constraintWidget = this.f991b;
            int i10 = constraintWidget.f956q;
            iMax = Math.max(constraintWidget.f954p, i8);
            if (i10 > 0) {
                iMax = Math.min(i10, i8);
            }
            if (iMax == i8) {
                return i8;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f991b;
            int i11 = constraintWidget2.f962t;
            iMax = Math.max(constraintWidget2.f960s, i8);
            if (i11 > 0) {
                iMax = Math.min(i11, i8);
            }
            if (iMax == i8) {
                return i8;
            }
        }
        return iMax;
    }

    public final DependencyNode h(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f919d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f917b;
        int i8 = a.f1000a[constraintAnchor2.f918c.ordinal()];
        if (i8 == 1) {
            return constraintWidget.f934f.f997h;
        }
        if (i8 == 2) {
            return constraintWidget.f934f.f998i;
        }
        if (i8 == 3) {
            return constraintWidget.f936g.f997h;
        }
        if (i8 == 4) {
            return constraintWidget.f936g.f37k;
        }
        if (i8 != 5) {
            return null;
        }
        return constraintWidget.f936g.f998i;
    }

    public final DependencyNode i(ConstraintAnchor constraintAnchor, int i8) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f919d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f917b;
        WidgetRun widgetRun = i8 == 0 ? constraintWidget.f934f : constraintWidget.f936g;
        int i9 = a.f1000a[constraintAnchor2.f918c.ordinal()];
        if (i9 != 1) {
            if (i9 != 2) {
                if (i9 != 3) {
                    if (i9 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.f998i;
        }
        return widgetRun.f997h;
    }

    public long j() {
        if (this.f994e.f987j) {
            return r0.f984g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f996g;
    }

    public final void l(int i8, int i9) {
        int i10 = this.f990a;
        if (i10 == 0) {
            this.f994e.d(g(i9, i8));
            return;
        }
        if (i10 == 1) {
            this.f994e.d(Math.min(g(this.f994e.f26m, i8), i9));
            return;
        }
        if (i10 == 2) {
            ConstraintWidget constraintWidgetH = this.f991b.H();
            if (constraintWidgetH != null) {
                if ((i8 == 0 ? constraintWidgetH.f934f : constraintWidgetH.f936g).f994e.f987j) {
                    ConstraintWidget constraintWidget = this.f991b;
                    this.f994e.d(g((int) ((r9.f984g * (i8 == 0 ? constraintWidget.f958r : constraintWidget.f964u)) + 0.5f), i8));
                    return;
                }
                return;
            }
            return;
        }
        if (i10 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f991b;
        WidgetRun widgetRun = constraintWidget2.f934f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f993d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f990a == 3) {
            k kVar = constraintWidget2.f936g;
            if (kVar.f993d == dimensionBehaviour2 && kVar.f990a == 3) {
                return;
            }
        }
        if (i8 == 0) {
            widgetRun = constraintWidget2.f936g;
        }
        if (widgetRun.f994e.f987j) {
            float fU = constraintWidget2.u();
            this.f994e.d(i8 == 1 ? (int) ((widgetRun.f994e.f984g / fU) + 0.5f) : (int) ((fU * widgetRun.f994e.f984g) + 0.5f));
        }
    }

    public abstract boolean m();

    public void n(d dVar, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i8) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.f987j && dependencyNodeH2.f987j) {
            int iC = dependencyNodeH.f984g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.f984g - constraintAnchor2.c();
            int i9 = iC2 - iC;
            if (!this.f994e.f987j && this.f993d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i8, i9);
            }
            f fVar = this.f994e;
            if (fVar.f987j) {
                if (fVar.f984g == i9) {
                    this.f997h.d(iC);
                    this.f998i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f991b;
                float fX = i8 == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.f984g;
                    iC2 = dependencyNodeH2.f984g;
                    fX = 0.5f;
                }
                this.f997h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f994e.f984g) * fX)));
                this.f998i.d(this.f997h.f984g + this.f994e.f984g);
            }
        }
    }

    public void o(d dVar) {
    }

    public void p(d dVar) {
    }
}
