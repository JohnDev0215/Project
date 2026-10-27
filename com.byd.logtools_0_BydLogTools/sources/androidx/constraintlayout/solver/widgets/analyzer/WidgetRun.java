package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ConstraintWidget f987b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    j f988c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f989d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    f f990e = new f(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f991f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f992g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DependencyNode f993h = new DependencyNode(this);
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
        static final /* synthetic */ int[] f995a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f995a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f995a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f995a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f995a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f995a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f987b = constraintWidget;
    }

    private void l(int i, int i2) {
        int i3 = this.f986a;
        if (i3 == 0) {
            this.f990e.d(g(i2, i));
            return;
        }
        if (i3 == 1) {
            this.f990e.d(Math.min(g(this.f990e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetH = this.f987b.H();
            if (constraintWidgetH != null) {
                if ((i == 0 ? constraintWidgetH.f971f : constraintWidgetH.f972g).f990e.j) {
                    ConstraintWidget constraintWidget = this.f987b;
                    this.f990e.d(g((int) ((r9.f983g * (i == 0 ? constraintWidget.r : constraintWidget.u)) + 0.5f), i));
                    return;
                }
                return;
            }
            return;
        }
        if (i3 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f987b;
        WidgetRun widgetRun = constraintWidget2.f971f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f989d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f986a == 3) {
            k kVar = constraintWidget2.f972g;
            if (kVar.f989d == dimensionBehaviour2 && kVar.f986a == 3) {
                return;
            }
        }
        if (i == 0) {
            widgetRun = constraintWidget2.f972g;
        }
        if (widgetRun.f990e.j) {
            float fU = constraintWidget2.u();
            this.f990e.d(i == 1 ? (int) ((widgetRun.f990e.f983g / fU) + 0.5f) : (int) ((fU * widgetRun.f990e.f983g) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
    }

    protected final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.f982f = i;
        dependencyNode2.k.add(dependencyNode);
    }

    protected final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, f fVar) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.l.add(this.f990e);
        dependencyNode.f984h = i;
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
            ConstraintWidget constraintWidget = this.f987b;
            int i3 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f987b;
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f960d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f958b;
        int i = a.f995a[constraintAnchor2.f959c.ordinal()];
        if (i == 1) {
            return constraintWidget.f971f.f993h;
        }
        if (i == 2) {
            return constraintWidget.f971f.i;
        }
        if (i == 3) {
            return constraintWidget.f972g.f993h;
        }
        if (i == 4) {
            return constraintWidget.f972g.k;
        }
        if (i != 5) {
            return null;
        }
        return constraintWidget.f972g.i;
    }

    protected final DependencyNode i(ConstraintAnchor constraintAnchor, int i) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f960d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f958b;
        WidgetRun widgetRun = i == 0 ? constraintWidget.f971f : constraintWidget.f972g;
        int i2 = a.f995a[constraintAnchor2.f959c.ordinal()];
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
        return widgetRun.f993h;
    }

    public long j() {
        if (this.f990e.j) {
            return r0.f983g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f992g;
    }

    abstract boolean m();

    protected void n(d dVar, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.j && dependencyNodeH2.j) {
            int iC = dependencyNodeH.f983g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.f983g - constraintAnchor2.c();
            int i2 = iC2 - iC;
            if (!this.f990e.j && this.f989d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            f fVar = this.f990e;
            if (fVar.j) {
                if (fVar.f983g == i2) {
                    this.f993h.d(iC);
                    this.i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f987b;
                float fX = i == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.f983g;
                    iC2 = dependencyNodeH2.f983g;
                    fX = 0.5f;
                }
                this.f993h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f990e.f983g) * fX)));
                this.i.d(this.f993h.f983g + this.f990e.f983g);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
