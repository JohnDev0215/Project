package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import b.e.b.j.l.d;
import b.e.b.j.l.f;
import b.e.b.j.l.j;
import b.e.b.j.l.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget.DimensionBehaviour f363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f364e = new f(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f365f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f366g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DependencyNode f367h = new DependencyNode(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DependencyNode f368i = new DependencyNode(this);
    public RunType j = RunType.NONE;

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f370a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f370a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f370a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f370a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f370a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f370a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f361b = constraintWidget;
    }

    @Override // b.e.b.j.l.d
    public void a(d dVar) {
    }

    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.f355f = i2;
        dependencyNode2.k.add(dependencyNode);
    }

    public final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, f fVar) {
        dependencyNode.l.add(dependencyNode2);
        dependencyNode.l.add(this.f364e);
        dependencyNode.f357h = i2;
        dependencyNode.f358i = fVar;
        dependencyNode2.k.add(dependencyNode);
        fVar.k.add(dependencyNode);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i2, int i3) {
        int iMax;
        if (i3 == 0) {
            ConstraintWidget constraintWidget = this.f361b;
            int i4 = constraintWidget.q;
            iMax = Math.max(constraintWidget.p, i2);
            if (i4 > 0) {
                iMax = Math.min(i4, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f361b;
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
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f332d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f330b;
        int i2 = a.f370a[constraintAnchor2.f331c.ordinal()];
        if (i2 == 1) {
            return constraintWidget.f343f.f367h;
        }
        if (i2 == 2) {
            return constraintWidget.f343f.f368i;
        }
        if (i2 == 3) {
            return constraintWidget.f344g.f367h;
        }
        if (i2 == 4) {
            return constraintWidget.f344g.k;
        }
        if (i2 != 5) {
            return null;
        }
        return constraintWidget.f344g.f368i;
    }

    public final DependencyNode i(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f332d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f330b;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.f343f : constraintWidget.f344g;
        int i3 = a.f370a[constraintAnchor2.f331c.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.f368i;
        }
        return widgetRun.f367h;
    }

    public long j() {
        if (this.f364e.j) {
            return r0.f356g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f366g;
    }

    public final void l(int i2, int i3) {
        int i4 = this.f360a;
        if (i4 == 0) {
            this.f364e.d(g(i3, i2));
            return;
        }
        if (i4 == 1) {
            this.f364e.d(Math.min(g(this.f364e.m, i2), i3));
            return;
        }
        if (i4 == 2) {
            ConstraintWidget constraintWidgetH = this.f361b.H();
            if (constraintWidgetH != null) {
                if ((i2 == 0 ? constraintWidgetH.f343f : constraintWidgetH.f344g).f364e.j) {
                    ConstraintWidget constraintWidget = this.f361b;
                    this.f364e.d(g((int) ((r9.f356g * (i2 == 0 ? constraintWidget.r : constraintWidget.u)) + 0.5f), i2));
                    return;
                }
                return;
            }
            return;
        }
        if (i4 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f361b;
        WidgetRun widgetRun = constraintWidget2.f343f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f363d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f360a == 3) {
            k kVar = constraintWidget2.f344g;
            if (kVar.f363d == dimensionBehaviour2 && kVar.f360a == 3) {
                return;
            }
        }
        if (i2 == 0) {
            widgetRun = constraintWidget2.f344g;
        }
        if (widgetRun.f364e.j) {
            float fU = constraintWidget2.u();
            this.f364e.d(i2 == 1 ? (int) ((widgetRun.f364e.f356g / fU) + 0.5f) : (int) ((fU * widgetRun.f364e.f356g) + 0.5f));
        }
    }

    public abstract boolean m();

    public void n(d dVar, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.j && dependencyNodeH2.j) {
            int iC = dependencyNodeH.f356g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.f356g - constraintAnchor2.c();
            int i3 = iC2 - iC;
            if (!this.f364e.j && this.f363d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i2, i3);
            }
            f fVar = this.f364e;
            if (fVar.j) {
                if (fVar.f356g == i3) {
                    this.f367h.d(iC);
                    this.f368i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f361b;
                float fX = i2 == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.f356g;
                    iC2 = dependencyNodeH2.f356g;
                    fX = 0.5f;
                }
                this.f367h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f364e.f356g) * fX)));
                this.f368i.d(this.f367h.f356g + this.f364e.f356g);
            }
        }
    }

    public void o(d dVar) {
    }

    public void p(d dVar) {
    }
}
