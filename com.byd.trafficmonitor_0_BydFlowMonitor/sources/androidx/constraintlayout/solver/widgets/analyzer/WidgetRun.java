package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import b.f.b.j.l.d;
import b.f.b.j.l.f;
import b.f.b.j.l.j;
import b.f.b.j.l.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f650b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f651c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget.DimensionBehaviour f652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f653e = new f(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f654f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f655g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DependencyNode f656h = new DependencyNode(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DependencyNode f657i = new DependencyNode(this);
    public RunType j = RunType.NONE;

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f659a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f659a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f659a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f659a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f659a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f659a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f650b = constraintWidget;
    }

    @Override // b.f.b.j.l.d
    public void a(d dVar) {
    }

    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.f644f = i2;
        dependencyNode2.k.add(dependencyNode);
    }

    public final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, f fVar) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.l.add(this.f653e);
        dependencyNode.f646h = i2;
        dependencyNode.f647i = fVar;
        dependencyNode2.k.add(dependencyNode);
        fVar.k.add(dependencyNode);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i2, int i3) {
        int iMax;
        if (i3 == 0) {
            ConstraintWidget constraintWidget = this.f650b;
            int i4 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i2);
            if (i4 > 0) {
                iMax = Math.min(i4, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f650b;
            int i5 = constraintWidget2.t;
            iMax = Math.max(constraintWidget2.s, i2);
            if (i5 > 0) {
                iMax = Math.min(i5, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        }
        return iMax;
    }

    public final DependencyNode h(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f621d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f619b;
        int i2 = a.f659a[constraintAnchor2.f620c.ordinal()];
        if (i2 == 1) {
            return constraintWidget.f632f.f656h;
        }
        if (i2 == 2) {
            return constraintWidget.f632f.f657i;
        }
        if (i2 == 3) {
            return constraintWidget.f633g.f656h;
        }
        if (i2 == 4) {
            return constraintWidget.f633g.k;
        }
        if (i2 != 5) {
            return null;
        }
        return constraintWidget.f633g.f657i;
    }

    public final DependencyNode i(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f621d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f619b;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.f632f : constraintWidget.f633g;
        int i3 = a.f659a[constraintAnchor2.f620c.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.f657i;
        }
        return widgetRun.f656h;
    }

    public long j() {
        if (this.f653e.j) {
            return r0.f645g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f655g;
    }

    public final void l(int i2, int i3) {
        int i4 = this.f649a;
        if (i4 == 0) {
            this.f653e.d(g(i3, i2));
            return;
        }
        if (i4 == 1) {
            this.f653e.d(Math.min(g(this.f653e.m, i2), i3));
            return;
        }
        if (i4 == 2) {
            ConstraintWidget constraintWidgetH = this.f650b.H();
            if (constraintWidgetH != null) {
                if ((i2 == 0 ? constraintWidgetH.f632f : constraintWidgetH.f633g).f653e.j) {
                    ConstraintWidget constraintWidget = this.f650b;
                    this.f653e.d(g((int) ((r9.f645g * (i2 == 0 ? constraintWidget.r : constraintWidget.u)) + 0.5f), i2));
                    return;
                }
                return;
            }
            return;
        }
        if (i4 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f650b;
        WidgetRun widgetRun = constraintWidget2.f632f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f652d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f649a == 3) {
            k kVar = constraintWidget2.f633g;
            if (kVar.f652d == dimensionBehaviour2 && kVar.f649a == 3) {
                return;
            }
        }
        if (i2 == 0) {
            widgetRun = constraintWidget2.f633g;
        }
        if (widgetRun.f653e.j) {
            float fU = constraintWidget2.u();
            this.f653e.d(i2 == 1 ? (int) ((widgetRun.f653e.f645g / fU) + 0.5f) : (int) ((fU * widgetRun.f653e.f645g) + 0.5f));
        }
    }

    public abstract boolean m();

    public void n(d dVar, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.j && dependencyNodeH2.j) {
            int iC = dependencyNodeH.f645g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.f645g - constraintAnchor2.c();
            int i3 = iC2 - iC;
            if (!this.f653e.j && this.f652d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i2, i3);
            }
            f fVar = this.f653e;
            if (fVar.j) {
                if (fVar.f645g == i3) {
                    this.f656h.d(iC);
                    this.f657i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f650b;
                float fX = i2 == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.f645g;
                    iC2 = dependencyNodeH2.f645g;
                    fX = 0.5f;
                }
                this.f656h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f653e.f645g) * fX)));
                this.f657i.d(this.f656h.f645g + this.f653e.f645g);
            }
        }
    }

    public void o(d dVar) {
    }

    public void p(d dVar) {
    }
}
