package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends WidgetRun {
    public DependencyNode k;
    f l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f865a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f865a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f865a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f865a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public m(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.k = dependencyNode;
        this.l = null;
        this.h.f834e = DependencyNode.Type.TOP;
        this.i.f834e = DependencyNode.Type.BOTTOM;
        dependencyNode.f834e = DependencyNode.Type.BASELINE;
        this.f = 1;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.d
    public void a(d dVar) {
        float f;
        float fX;
        float fX2;
        int i;
        int i2 = a.f865a[this.j.ordinal()];
        if (i2 == 1) {
            p(dVar);
        } else if (i2 == 2) {
            o(dVar);
        } else if (i2 == 3) {
            ConstraintWidget constraintWidget = this.f837b;
            n(dVar, constraintWidget.S, constraintWidget.U, 1);
            return;
        }
        f fVar = this.f840e;
        if (fVar.f832c && !fVar.j && this.f839d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f837b;
            int i3 = constraintWidget2.y;
            if (i3 == 2) {
                ConstraintWidget constraintWidgetM = constraintWidget2.M();
                if (constraintWidgetM != null) {
                    if (constraintWidgetM.g.f840e.j) {
                        this.f840e.d((int) ((r7.g * this.f837b.F) + 0.5f));
                    }
                }
            } else if (i3 == 3 && constraintWidget2.f.f840e.j) {
                int iY = constraintWidget2.y();
                if (iY == -1) {
                    ConstraintWidget constraintWidget3 = this.f837b;
                    f = constraintWidget3.f.f840e.g;
                    fX = constraintWidget3.x();
                } else if (iY == 0) {
                    fX2 = r7.f.f840e.g * this.f837b.x();
                    i = (int) (fX2 + 0.5f);
                    this.f840e.d(i);
                } else if (iY != 1) {
                    i = 0;
                    this.f840e.d(i);
                } else {
                    ConstraintWidget constraintWidget4 = this.f837b;
                    f = constraintWidget4.f.f840e.g;
                    fX = constraintWidget4.x();
                }
                fX2 = f / fX;
                i = (int) (fX2 + 0.5f);
                this.f840e.d(i);
            }
        }
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.f832c) {
            DependencyNode dependencyNode2 = this.i;
            if (dependencyNode2.f832c) {
                if (dependencyNode.j && dependencyNode2.j && this.f840e.j) {
                    return;
                }
                if (!this.f840e.j && this.f839d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f837b;
                    if (constraintWidget5.x == 0 && !constraintWidget5.m0()) {
                        DependencyNode dependencyNode3 = this.h.l.get(0);
                        DependencyNode dependencyNode4 = this.i.l.get(0);
                        int i4 = dependencyNode3.g;
                        DependencyNode dependencyNode5 = this.h;
                        int i5 = i4 + dependencyNode5.f;
                        int i6 = dependencyNode4.g + this.i.f;
                        dependencyNode5.d(i5);
                        this.i.d(i6);
                        this.f840e.d(i6 - i5);
                        return;
                    }
                }
                if (!this.f840e.j && this.f839d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f836a == 1 && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode6 = this.h.l.get(0);
                    int i7 = (this.i.l.get(0).g + this.i.f) - (dependencyNode6.g + this.h.f);
                    f fVar2 = this.f840e;
                    int i8 = fVar2.m;
                    if (i7 < i8) {
                        fVar2.d(i7);
                    } else {
                        fVar2.d(i8);
                    }
                }
                if (this.f840e.j && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    DependencyNode dependencyNode7 = this.h.l.get(0);
                    DependencyNode dependencyNode8 = this.i.l.get(0);
                    int i9 = dependencyNode7.g + this.h.f;
                    int i10 = dependencyNode8.g + this.i.f;
                    float fT = this.f837b.T();
                    if (dependencyNode7 == dependencyNode8) {
                        i9 = dependencyNode7.g;
                        i10 = dependencyNode8.g;
                        fT = 0.5f;
                    }
                    this.h.d((int) (i9 + 0.5f + (((i10 - i9) - this.f840e.g) * fT)));
                    this.i.d(this.h.g + this.f840e.g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidgetM;
        ConstraintWidget constraintWidgetM2;
        ConstraintWidget constraintWidget = this.f837b;
        if (constraintWidget.f823b) {
            this.f840e.d(constraintWidget.z());
        }
        if (!this.f840e.j) {
            this.f839d = this.f837b.V();
            if (this.f837b.b0()) {
                this.l = new androidx.constraintlayout.core.widgets.analyzer.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f839d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetM2 = this.f837b.M()) != null && constraintWidgetM2.V() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int iZ = (constraintWidgetM2.z() - this.f837b.S.f()) - this.f837b.U.f();
                    b(this.h, constraintWidgetM2.g.h, this.f837b.S.f());
                    b(this.i, constraintWidgetM2.g.i, -this.f837b.U.f());
                    this.f840e.d(iZ);
                    return;
                }
                if (this.f839d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f840e.d(this.f837b.z());
                }
            }
        } else if (this.f839d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (constraintWidgetM = this.f837b.M()) != null && constraintWidgetM.V() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.h, constraintWidgetM.g.h, this.f837b.S.f());
            b(this.i, constraintWidgetM.g.i, -this.f837b.U.f());
            return;
        }
        f fVar = this.f840e;
        boolean z = fVar.j;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f837b;
            if (constraintWidget2.f823b) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.Z;
                if (constraintAnchorArr[2].f != null && constraintAnchorArr[3].f != null) {
                    if (constraintWidget2.m0()) {
                        this.h.f = this.f837b.Z[2].f();
                        this.i.f = -this.f837b.Z[3].f();
                    } else {
                        DependencyNode dependencyNodeH = h(this.f837b.Z[2]);
                        if (dependencyNodeH != null) {
                            b(this.h, dependencyNodeH, this.f837b.Z[2].f());
                        }
                        DependencyNode dependencyNodeH2 = h(this.f837b.Z[3]);
                        if (dependencyNodeH2 != null) {
                            b(this.i, dependencyNodeH2, -this.f837b.Z[3].f());
                        }
                        this.h.f831b = true;
                        this.i.f831b = true;
                    }
                    if (this.f837b.b0()) {
                        b(this.k, this.h, this.f837b.r());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[2].f != null) {
                    DependencyNode dependencyNodeH3 = h(constraintAnchorArr[2]);
                    if (dependencyNodeH3 != null) {
                        b(this.h, dependencyNodeH3, this.f837b.Z[2].f());
                        b(this.i, this.h, this.f840e.g);
                        if (this.f837b.b0()) {
                            b(this.k, this.h, this.f837b.r());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[3].f != null) {
                    DependencyNode dependencyNodeH4 = h(constraintAnchorArr[3]);
                    if (dependencyNodeH4 != null) {
                        b(this.i, dependencyNodeH4, -this.f837b.Z[3].f());
                        b(this.h, this.i, -this.f840e.g);
                    }
                    if (this.f837b.b0()) {
                        b(this.k, this.h, this.f837b.r());
                        return;
                    }
                    return;
                }
                if (constraintAnchorArr[4].f != null) {
                    DependencyNode dependencyNodeH5 = h(constraintAnchorArr[4]);
                    if (dependencyNodeH5 != null) {
                        b(this.k, dependencyNodeH5, 0);
                        b(this.h, this.k, -this.f837b.r());
                        b(this.i, this.h, this.f840e.g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof androidx.constraintlayout.core.widgets.g) || constraintWidget2.M() == null || this.f837b.q(ConstraintAnchor.Type.CENTER).f != null) {
                    return;
                }
                b(this.h, this.f837b.M().g.h, this.f837b.a0());
                b(this.i, this.h, this.f840e.g);
                if (this.f837b.b0()) {
                    b(this.k, this.h, this.f837b.r());
                    return;
                }
                return;
            }
        }
        if (z || this.f839d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            fVar.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f837b;
            int i = constraintWidget3.y;
            if (i == 2) {
                ConstraintWidget constraintWidgetM3 = constraintWidget3.M();
                if (constraintWidgetM3 != null) {
                    f fVar2 = constraintWidgetM3.g.f840e;
                    this.f840e.l.add(fVar2);
                    fVar2.k.add(this.f840e);
                    f fVar3 = this.f840e;
                    fVar3.f831b = true;
                    fVar3.k.add(this.h);
                    this.f840e.k.add(this.i);
                }
            } else if (i == 3 && !constraintWidget3.m0()) {
                ConstraintWidget constraintWidget4 = this.f837b;
                if (constraintWidget4.x != 3) {
                    f fVar4 = constraintWidget4.f.f840e;
                    this.f840e.l.add(fVar4);
                    fVar4.k.add(this.f840e);
                    f fVar5 = this.f840e;
                    fVar5.f831b = true;
                    fVar5.k.add(this.h);
                    this.f840e.k.add(this.i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f837b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.Z;
        if (constraintAnchorArr2[2].f != null && constraintAnchorArr2[3].f != null) {
            if (constraintWidget5.m0()) {
                this.h.f = this.f837b.Z[2].f();
                this.i.f = -this.f837b.Z[3].f();
            } else {
                DependencyNode dependencyNodeH6 = h(this.f837b.Z[2]);
                DependencyNode dependencyNodeH7 = h(this.f837b.Z[3]);
                if (dependencyNodeH6 != null) {
                    dependencyNodeH6.b(this);
                }
                if (dependencyNodeH7 != null) {
                    dependencyNodeH7.b(this);
                }
                this.j = WidgetRun.RunType.CENTER;
            }
            if (this.f837b.b0()) {
                c(this.k, this.h, 1, this.l);
            }
        } else if (constraintAnchorArr2[2].f != null) {
            DependencyNode dependencyNodeH8 = h(constraintAnchorArr2[2]);
            if (dependencyNodeH8 != null) {
                b(this.h, dependencyNodeH8, this.f837b.Z[2].f());
                c(this.i, this.h, 1, this.f840e);
                if (this.f837b.b0()) {
                    c(this.k, this.h, 1, this.l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f839d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f837b.x() > 0.0f) {
                    k kVar = this.f837b.f;
                    if (kVar.f839d == dimensionBehaviour3) {
                        kVar.f840e.k.add(this.f840e);
                        this.f840e.l.add(this.f837b.f.f840e);
                        this.f840e.f830a = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].f != null) {
            DependencyNode dependencyNodeH9 = h(constraintAnchorArr2[3]);
            if (dependencyNodeH9 != null) {
                b(this.i, dependencyNodeH9, -this.f837b.Z[3].f());
                c(this.h, this.i, -1, this.f840e);
                if (this.f837b.b0()) {
                    c(this.k, this.h, 1, this.l);
                }
            }
        } else if (constraintAnchorArr2[4].f != null) {
            DependencyNode dependencyNodeH10 = h(constraintAnchorArr2[4]);
            if (dependencyNodeH10 != null) {
                b(this.k, dependencyNodeH10, 0);
                c(this.h, this.k, -1, this.l);
                c(this.i, this.h, 1, this.f840e);
            }
        } else if (!(constraintWidget5 instanceof androidx.constraintlayout.core.widgets.g) && constraintWidget5.M() != null) {
            b(this.h, this.f837b.M().g.h, this.f837b.a0());
            c(this.i, this.h, 1, this.f840e);
            if (this.f837b.b0()) {
                c(this.k, this.h, 1, this.l);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f839d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f837b.x() > 0.0f) {
                k kVar2 = this.f837b.f;
                if (kVar2.f839d == dimensionBehaviour5) {
                    kVar2.f840e.k.add(this.f840e);
                    this.f840e.l.add(this.f837b.f.f840e);
                    this.f840e.f830a = this;
                }
            }
        }
        if (this.f840e.l.size() == 0) {
            this.f840e.f832c = true;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.j) {
            this.f837b.q1(dependencyNode.g);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f838c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.f840e.c();
        this.g = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f839d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f837b.y == 0;
    }

    void q() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.k.c();
        this.k.j = false;
        this.f840e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f837b.v();
    }
}
