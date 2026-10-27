package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ConstraintWidget f750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    j f751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    f f753e = new f(this);
    public int f = 0;
    boolean g = false;
    public DependencyNode h = new DependencyNode(this);
    public DependencyNode i = new DependencyNode(this);
    protected RunType j = RunType.NONE;

    enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f755a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f755a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f755a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f755a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f755a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f755a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f750b = constraintWidget;
    }

    private void l(int i, int i2) {
        int i3 = this.f749a;
        if (i3 == 0) {
            this.f753e.d(g(i2, i));
            return;
        }
        if (i3 == 1) {
            this.f753e.d(Math.min(g(this.f753e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetH = this.f750b.H();
            if (constraintWidgetH != null) {
                if ((i == 0 ? constraintWidgetH.f : constraintWidgetH.g).f753e.j) {
                    ConstraintWidget constraintWidget = this.f750b;
                    this.f753e.d(g((int) ((r9.g * (i == 0 ? constraintWidget.r : constraintWidget.u)) + 0.5f), i));
                    return;
                }
                return;
            }
            return;
        }
        if (i3 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f750b;
        WidgetRun widgetRun = constraintWidget2.f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f752d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f749a == 3) {
            k kVar = constraintWidget2.g;
            if (kVar.f752d == dimensionBehaviour2 && kVar.f749a == 3) {
                return;
            }
        }
        if (i == 0) {
            widgetRun = constraintWidget2.g;
        }
        if (widgetRun.f753e.j) {
            float fU = constraintWidget2.u();
            this.f753e.d(i == 1 ? (int) ((widgetRun.f753e.g / fU) + 0.5f) : (int) ((fU * widgetRun.f753e.g) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
    }

    protected final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.f = i;
        dependencyNode2.k.add(dependencyNode);
    }

    protected final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, f fVar) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.l.add(this.f753e);
        dependencyNode.h = i;
        dependencyNode.i = fVar;
        dependencyNode2.k.add(dependencyNode);
        fVar.k.add(dependencyNode);
    }

    abstract void d();

    abstract void e();

    abstract void f();

    protected final int g(int i, int i2) {
        int iMax;
        if (i2 == 0) {
            ConstraintWidget constraintWidget = this.f750b;
            int i3 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f750b;
            int i4 = constraintWidget2.t;
            iMax = Math.max(constraintWidget2.s, i);
            if (i4 > 0) {
                iMax = Math.min(i4, i);
            }
            if (iMax == i) {
                return i;
            }
        }
        return iMax;
    }

    protected final DependencyNode h(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f731d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f729b;
        int i = a.f755a[constraintAnchor2.f730c.ordinal()];
        if (i == 1) {
            return constraintWidget.f.h;
        }
        if (i == 2) {
            return constraintWidget.f.i;
        }
        if (i == 3) {
            return constraintWidget.g.h;
        }
        if (i == 4) {
            return constraintWidget.g.k;
        }
        if (i != 5) {
            return null;
        }
        return constraintWidget.g.i;
    }

    protected final DependencyNode i(ConstraintAnchor constraintAnchor, int i) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f731d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f729b;
        WidgetRun widgetRun = i == 0 ? constraintWidget.f : constraintWidget.g;
        int i2 = a.f755a[constraintAnchor2.f730c.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.i;
        }
        return widgetRun.h;
    }

    public long j() {
        if (this.f753e.j) {
            return r0.g;
        }
        return 0L;
    }

    public boolean k() {
        return this.g;
    }

    abstract boolean m();

    protected void n(d dVar, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.j && dependencyNodeH2.j) {
            int iC = dependencyNodeH.g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.g - constraintAnchor2.c();
            int i2 = iC2 - iC;
            if (!this.f753e.j && this.f752d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            f fVar = this.f753e;
            if (fVar.j) {
                if (fVar.g == i2) {
                    this.h.d(iC);
                    this.i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f750b;
                float fX = i == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.g;
                    iC2 = dependencyNodeH2.g;
                    fX = 0.5f;
                }
                this.h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f753e.g) * fX)));
                this.i.d(this.h.g + this.f753e.g);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
