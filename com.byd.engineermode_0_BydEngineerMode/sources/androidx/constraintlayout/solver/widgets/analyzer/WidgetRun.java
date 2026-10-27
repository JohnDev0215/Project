package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f658a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ConstraintWidget f659b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    j f660c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f661d;
    f e = new f(this);
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
        static final /* synthetic */ int[] f663a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f663a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f663a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f663a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f663a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f663a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f659b = constraintWidget;
    }

    private void l(int i, int i2) {
        int i3 = this.f658a;
        if (i3 == 0) {
            this.e.d(g(i2, i));
            return;
        }
        if (i3 == 1) {
            this.e.d(Math.min(g(this.e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetH = this.f659b.H();
            if (constraintWidgetH != null) {
                if ((i == 0 ? constraintWidgetH.f : constraintWidgetH.g).e.j) {
                    ConstraintWidget constraintWidget = this.f659b;
                    this.e.d(g((int) ((r9.g * (i == 0 ? constraintWidget.r : constraintWidget.u)) + 0.5f), i));
                    return;
                }
                return;
            }
            return;
        }
        if (i3 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f659b;
        WidgetRun widgetRun = constraintWidget2.f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f661d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f658a == 3) {
            k kVar = constraintWidget2.g;
            if (kVar.f661d == dimensionBehaviour2 && kVar.f658a == 3) {
                return;
            }
        }
        if (i == 0) {
            widgetRun = constraintWidget2.g;
        }
        if (widgetRun.e.j) {
            float fU = constraintWidget2.u();
            this.e.d(i == 1 ? (int) ((widgetRun.e.g / fU) + 0.5f) : (int) ((fU * widgetRun.e.g) + 0.5f));
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
        dependencyNode.l.add(this.e);
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
            ConstraintWidget constraintWidget = this.f659b;
            int i3 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f659b;
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f643d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f641b;
        int i = a.f663a[constraintAnchor2.f642c.ordinal()];
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f643d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f641b;
        WidgetRun widgetRun = i == 0 ? constraintWidget.f : constraintWidget.g;
        int i2 = a.f663a[constraintAnchor2.f642c.ordinal()];
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
        if (this.e.j) {
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
            if (!this.e.j && this.f661d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            f fVar = this.e;
            if (fVar.j) {
                if (fVar.g == i2) {
                    this.h.d(iC);
                    this.i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f659b;
                float fX = i == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.g;
                    iC2 = dependencyNodeH2.g;
                    fX = 0.5f;
                }
                this.h.d((int) (iC + 0.5f + (((iC2 - iC) - this.e.g) * fX)));
                this.i.d(this.h.g + this.e.g);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
