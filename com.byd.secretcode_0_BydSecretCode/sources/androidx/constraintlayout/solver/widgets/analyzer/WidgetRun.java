package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import defpackage.h6;
import defpackage.j6;
import defpackage.n6;
import defpackage.o6;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements h6 {
    public int a;
    public ConstraintWidget b;
    public n6 c;
    public ConstraintWidget.DimensionBehaviour d;
    public j6 e = new j6(this);
    public int f = 0;
    public boolean g = false;
    public DependencyNode h = new DependencyNode(this);
    public DependencyNode i = new DependencyNode(this);
    public RunType j = RunType.NONE;

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.b = constraintWidget;
    }

    @Override // defpackage.h6
    public void a(h6 h6Var) {
    }

    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.f = i;
        dependencyNode2.k.add(dependencyNode);
    }

    public final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, j6 j6Var) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.l.add(this.e);
        dependencyNode.h = i;
        dependencyNode.i = j6Var;
        dependencyNode2.k.add(dependencyNode);
        j6Var.k.add(dependencyNode);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        int iMax;
        if (i2 == 0) {
            ConstraintWidget constraintWidget = this.b;
            int i3 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.b;
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

    public final DependencyNode h(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.b;
        int i = a.a[constraintAnchor2.c.ordinal()];
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

    public final DependencyNode i(ConstraintAnchor constraintAnchor, int i) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.b;
        WidgetRun widgetRun = i == 0 ? constraintWidget.f : constraintWidget.g;
        int i2 = a.a[constraintAnchor2.c.ordinal()];
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

    public final void l(int i, int i2) {
        int i3 = this.a;
        if (i3 == 0) {
            this.e.d(g(i2, i));
            return;
        }
        if (i3 == 1) {
            this.e.d(Math.min(g(this.e.m, i), i2));
            return;
        }
        if (i3 == 2) {
            ConstraintWidget constraintWidgetH = this.b.H();
            if (constraintWidgetH != null) {
                if ((i == 0 ? constraintWidgetH.f : constraintWidgetH.g).e.j) {
                    ConstraintWidget constraintWidget = this.b;
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
        ConstraintWidget constraintWidget2 = this.b;
        WidgetRun widgetRun = constraintWidget2.f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.a == 3) {
            o6 o6Var = constraintWidget2.g;
            if (o6Var.d == dimensionBehaviour2 && o6Var.a == 3) {
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

    public abstract boolean m();

    public void n(h6 h6Var, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.j && dependencyNodeH2.j) {
            int iC = dependencyNodeH.g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.g - constraintAnchor2.c();
            int i2 = iC2 - iC;
            if (!this.e.j && this.d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            j6 j6Var = this.e;
            if (j6Var.j) {
                if (j6Var.g == i2) {
                    this.h.d(iC);
                    this.i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.b;
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

    public void o(h6 h6Var) {
    }

    public void p(h6 h6Var) {
    }
}
