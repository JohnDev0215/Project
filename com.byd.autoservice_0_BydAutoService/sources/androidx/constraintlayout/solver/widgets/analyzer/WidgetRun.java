package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import x.d;
import x.f;
import x.j;
import x.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f900b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f901c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget.DimensionBehaviour f902d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f903e = new f(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f904f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f905g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DependencyNode f906h = new DependencyNode(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DependencyNode f907i = new DependencyNode(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public RunType f908j = RunType.NONE;

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f909a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f909a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f909a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f909a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f909a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f909a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f900b = constraintWidget;
    }

    @Override // x.d
    public void a(d dVar) {
    }

    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i3) {
        dependencyNode.f898l.add(dependencyNode2);
        dependencyNode.f892f = i3;
        dependencyNode2.f897k.add(dependencyNode);
    }

    public final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i3, f fVar) {
        dependencyNode.f898l.add(dependencyNode2);
        dependencyNode.f898l.add(this.f903e);
        dependencyNode.f894h = i3;
        dependencyNode.f895i = fVar;
        dependencyNode2.f897k.add(dependencyNode);
        fVar.f897k.add(dependencyNode);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i3, int i4) {
        int iMax;
        if (i4 == 0) {
            ConstraintWidget constraintWidget = this.f900b;
            int i5 = constraintWidget.f865q;
            iMax = Math.max(constraintWidget.f863p, i3);
            if (i5 > 0) {
                iMax = Math.min(i5, i3);
            }
            if (iMax == i3) {
                return i3;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f900b;
            int i6 = constraintWidget2.f871t;
            iMax = Math.max(constraintWidget2.f869s, i3);
            if (i6 > 0) {
                iMax = Math.min(i6, i3);
            }
            if (iMax == i3) {
                return i3;
            }
        }
        return iMax;
    }

    public final DependencyNode h(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f828d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f826b;
        int i3 = a.f909a[constraintAnchor2.f827c.ordinal()];
        if (i3 == 1) {
            return constraintWidget.f843f.f906h;
        }
        if (i3 == 2) {
            return constraintWidget.f843f.f907i;
        }
        if (i3 == 3) {
            return constraintWidget.f845g.f906h;
        }
        if (i3 == 4) {
            return constraintWidget.f845g.f5716k;
        }
        if (i3 != 5) {
            return null;
        }
        return constraintWidget.f845g.f907i;
    }

    public final DependencyNode i(ConstraintAnchor constraintAnchor, int i3) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f828d;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f826b;
        WidgetRun widgetRun = i3 == 0 ? constraintWidget.f843f : constraintWidget.f845g;
        int i4 = a.f909a[constraintAnchor2.f827c.ordinal()];
        if (i4 != 1) {
            if (i4 != 2) {
                if (i4 != 3) {
                    if (i4 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.f907i;
        }
        return widgetRun.f906h;
    }

    public long j() {
        if (this.f903e.f896j) {
            return r0.f893g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f905g;
    }

    public final void l(int i3, int i4) {
        int i5 = this.f899a;
        if (i5 == 0) {
            this.f903e.d(g(i4, i3));
            return;
        }
        if (i5 == 1) {
            this.f903e.d(Math.min(g(this.f903e.f5705m, i3), i4));
            return;
        }
        if (i5 == 2) {
            ConstraintWidget constraintWidgetH = this.f900b.H();
            if (constraintWidgetH != null) {
                if ((i3 == 0 ? constraintWidgetH.f843f : constraintWidgetH.f845g).f903e.f896j) {
                    ConstraintWidget constraintWidget = this.f900b;
                    this.f903e.d(g((int) ((r9.f893g * (i3 == 0 ? constraintWidget.f867r : constraintWidget.f873u)) + 0.5f), i3));
                    return;
                }
                return;
            }
            return;
        }
        if (i5 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.f900b;
        WidgetRun widgetRun = constraintWidget2.f843f;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f902d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f899a == 3) {
            k kVar = constraintWidget2.f845g;
            if (kVar.f902d == dimensionBehaviour2 && kVar.f899a == 3) {
                return;
            }
        }
        if (i3 == 0) {
            widgetRun = constraintWidget2.f845g;
        }
        if (widgetRun.f903e.f896j) {
            float fU = constraintWidget2.u();
            this.f903e.d(i3 == 1 ? (int) ((widgetRun.f903e.f893g / fU) + 0.5f) : (int) ((fU * widgetRun.f903e.f893g) + 0.5f));
        }
    }

    public abstract boolean m();

    public void n(d dVar, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i3) {
        DependencyNode dependencyNodeH = h(constraintAnchor);
        DependencyNode dependencyNodeH2 = h(constraintAnchor2);
        if (dependencyNodeH.f896j && dependencyNodeH2.f896j) {
            int iC = dependencyNodeH.f893g + constraintAnchor.c();
            int iC2 = dependencyNodeH2.f893g - constraintAnchor2.c();
            int i4 = iC2 - iC;
            if (!this.f903e.f896j && this.f902d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i3, i4);
            }
            f fVar = this.f903e;
            if (fVar.f896j) {
                if (fVar.f893g == i4) {
                    this.f906h.d(iC);
                    this.f907i.d(iC2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f900b;
                float fX = i3 == 0 ? constraintWidget.x() : constraintWidget.L();
                if (dependencyNodeH == dependencyNodeH2) {
                    iC = dependencyNodeH.f893g;
                    iC2 = dependencyNodeH2.f893g;
                    fX = 0.5f;
                }
                this.f906h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f903e.f893g) * fX)));
                this.f907i.d(this.f906h.f893g + this.f903e.f893g);
            }
        }
    }

    public void o(d dVar) {
    }

    public void p(d dVar) {
    }
}
