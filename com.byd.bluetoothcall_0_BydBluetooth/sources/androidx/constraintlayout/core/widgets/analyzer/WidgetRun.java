package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ConstraintWidget f837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    l f838c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f839d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    f f840e = new f(this);
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
        static final /* synthetic */ int[] f842a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f842a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f842a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f842a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f842a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f842a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f837b = constraintWidget;
    }

    private void l(int i, int i2) {
        int i3 = this.f836a;
        if (i3 == 0) {
            this.f840e.d(g(i2, i));
            return;
        }
        if (i3 == 1) {
            this.f840e.d(Math.min(g(this.f840e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetM = this.f837b.M();
            if (constraintWidgetM != null) {
                if ((i == 0 ? constraintWidgetM.f : constraintWidgetM.g).f840e.j) {
                    ConstraintWidget constraintWidget = this.f837b;
                    this.f840e.d(g((int) ((r9.g * (i == 0 ? constraintWidget.C : constraintWidget.F)) + 0.5f), i));
                    return;
                }
                return;
            }
            return;
        }
        if (i3 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f837b;
        WidgetRun widgetRun = constraintWidget2.f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f839d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f836a == 3) {
            m mVar = constraintWidget2.g;
            if (mVar.f839d == dimensionBehaviour2 && mVar.f836a == 3) {
                return;
            }
        }
        if (i == 0) {
            widgetRun = constraintWidget2.g;
        }
        if (widgetRun.f840e.j) {
            float fX = constraintWidget2.x();
            this.f840e.d(i == 1 ? (int) ((widgetRun.f840e.g / fX) + 0.5f) : (int) ((fX * widgetRun.f840e.g) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.d
    public void a(d dVar) {
    }

    protected final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.f = i;
        dependencyNode2.k.add(dependencyNode);
    }

    protected final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, f fVar) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.l.add(this.f840e);
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
            ConstraintWidget constraintWidget = this.f837b;
            int i3 = constraintWidget.B;
            iMax = Math.max(constraintWidget.A, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f837b;
            int i4 = constraintWidget2.E;
            iMax = Math.max(constraintWidget2.D, i);
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f818d;
        int i = a.f842a[constraintAnchor2.f819e.ordinal()];
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f818d;
        WidgetRun widgetRun = i == 0 ? constraintWidget.f : constraintWidget.g;
        int i2 = a.f842a[constraintAnchor2.f819e.ordinal()];
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
        if (this.f840e.j) {
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
            int iF = dependencyNodeH.g + constraintAnchor.f();
            int iF2 = dependencyNodeH2.g - constraintAnchor2.f();
            int i2 = iF2 - iF;
            if (!this.f840e.j && this.f839d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            f fVar = this.f840e;
            if (fVar.j) {
                if (fVar.g == i2) {
                    this.h.d(iF);
                    this.i.d(iF2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f837b;
                float fA = i == 0 ? constraintWidget.A() : constraintWidget.T();
                if (dependencyNodeH == dependencyNodeH2) {
                    iF = dependencyNodeH.g;
                    iF2 = dependencyNodeH2.g;
                    fA = 0.5f;
                }
                this.h.d((int) (iF + 0.5f + (((iF2 - iF) - this.f840e.g) * fA)));
                this.i.d(this.h.g + this.f840e.g);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
