package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ConstraintWidget f1188b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    j f1189c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f1190d;
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
        static final /* synthetic */ int[] f1192a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f1192a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1192a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1192a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1192a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1192a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f1188b = constraintWidget;
    }

    private void l(int i, int i2) {
        int i3 = this.f1187a;
        if (i3 == 0) {
            this.e.d(g(i2, i));
            return;
        }
        if (i3 == 1) {
            this.e.d(Math.min(g(this.e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetH = this.f1188b.H();
            if (constraintWidgetH != null) {
                if ((i == 0 ? constraintWidgetH.f : constraintWidgetH.g).e.j) {
                    ConstraintWidget constraintWidget = this.f1188b;
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
        ConstraintWidget constraintWidget2 = this.f1188b;
        WidgetRun widgetRun = constraintWidget2.f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f1190d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f1187a == 3) {
            k kVar = constraintWidget2.g;
            if (kVar.f1190d == dimensionBehaviour2 && kVar.f1187a == 3) {
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
            ConstraintWidget constraintWidget = this.f1188b;
            int i3 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f1188b;
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1172d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f1170b;
        int i = a.f1192a[constraintAnchor2.f1171c.ordinal()];
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1172d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f1170b;
        WidgetRun widgetRun = i == 0 ? constraintWidget.f : constraintWidget.g;
        int i2 = a.f1192a[constraintAnchor2.f1171c.ordinal()];
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
            if (!this.e.j && this.f1190d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            f fVar = this.e;
            if (fVar.j) {
                if (fVar.g == i2) {
                    this.h.d(iC);
                    this.i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f1188b;
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
