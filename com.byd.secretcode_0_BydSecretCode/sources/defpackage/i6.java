package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import defpackage.f6;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class i6 {
    public w5 a;
    public w5 d;
    public boolean b = true;
    public boolean c = true;
    public ArrayList<WidgetRun> e = new ArrayList<>();
    public ArrayList<n6> f = new ArrayList<>();
    public f6.b g = null;
    public f6.a h = new f6.a();
    public ArrayList<n6> i = new ArrayList<>();

    public i6(w5 w5Var) {
        this.a = w5Var;
        this.d = w5Var;
    }

    public final void a(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<n6> arrayList, n6 n6Var) {
        WidgetRun widgetRun = dependencyNode.d;
        if (widgetRun.c == null) {
            w5 w5Var = this.a;
            if (widgetRun == w5Var.f || widgetRun == w5Var.g) {
                return;
            }
            if (n6Var == null) {
                n6Var = new n6(widgetRun, i2);
                arrayList.add(n6Var);
            }
            widgetRun.c = n6Var;
            n6Var.a(widgetRun);
            for (h6 h6Var : widgetRun.h.k) {
                if (h6Var instanceof DependencyNode) {
                    a((DependencyNode) h6Var, i, 0, dependencyNode2, arrayList, n6Var);
                }
            }
            for (h6 h6Var2 : widgetRun.i.k) {
                if (h6Var2 instanceof DependencyNode) {
                    a((DependencyNode) h6Var2, i, 1, dependencyNode2, arrayList, n6Var);
                }
            }
            if (i == 1 && (widgetRun instanceof o6)) {
                for (h6 h6Var3 : ((o6) widgetRun).k.k) {
                    if (h6Var3 instanceof DependencyNode) {
                        a((DependencyNode) h6Var3, i, 2, dependencyNode2, arrayList, n6Var);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.h.l) {
                if (dependencyNode3 == dependencyNode2) {
                    n6Var.c = true;
                }
                a(dependencyNode3, i, 0, dependencyNode2, arrayList, n6Var);
            }
            for (DependencyNode dependencyNode4 : widgetRun.i.l) {
                if (dependencyNode4 == dependencyNode2) {
                    n6Var.c = true;
                }
                a(dependencyNode4, i, 1, dependencyNode2, arrayList, n6Var);
            }
            if (i == 1 && (widgetRun instanceof o6)) {
                Iterator<DependencyNode> it = ((o6) widgetRun).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i, 2, dependencyNode2, arrayList, n6Var);
                }
            }
        }
    }

    public final boolean b(w5 w5Var) {
        int iQ;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int iW;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        for (ConstraintWidget constraintWidget : w5Var.G0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (constraintWidget.P() == 8) {
                constraintWidget.b = true;
            } else {
                if (constraintWidget.r < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.m = 2;
                }
                if (constraintWidget.u < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.n = 2;
                }
                if (constraintWidget.u() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.m = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.n = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (constraintWidget.m == 0) {
                            constraintWidget.m = 3;
                        }
                        if (constraintWidget.n == 0) {
                            constraintWidget.n = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.m == 1 && (constraintWidget.E.d == null || constraintWidget.G.d == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.n == 1 && (constraintWidget.F.d == null || constraintWidget.H.d == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                m6 m6Var = constraintWidget.f;
                m6Var.d = dimensionBehaviour9;
                int i = constraintWidget.m;
                m6Var.a = i;
                o6 o6Var = constraintWidget.g;
                o6Var.d = dimensionBehaviour10;
                int i2 = constraintWidget.n;
                o6Var.a = i2;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iQ2 = constraintWidget.Q();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iQ = (w5Var.Q() - constraintWidget.E.e) - constraintWidget.G.e;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iQ = iQ2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iW2 = constraintWidget.w();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iW = (w5Var.w() - constraintWidget.F.e) - constraintWidget.H.e;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iW = iW2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iQ, dimensionBehaviour2, iW);
                    constraintWidget.f.e.d(constraintWidget.Q());
                    constraintWidget.g.e.d(constraintWidget.w());
                    constraintWidget.b = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int iW3 = constraintWidget.w();
                            int i3 = (int) ((iW3 * constraintWidget.T) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour12, i3, dimensionBehaviour12, iW3);
                            constraintWidget.f.e.d(constraintWidget.Q());
                            constraintWidget.g.e.d(constraintWidget.w());
                            constraintWidget.b = true;
                        } else if (i == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f.e.m = constraintWidget.Q();
                        } else if (i == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = w5Var.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.r * w5Var.Q()) + 0.5f), dimensionBehaviour10, constraintWidget.w());
                                constraintWidget.f.e.d(constraintWidget.Q());
                                constraintWidget.g.e.d(constraintWidget.w());
                                constraintWidget.b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.M;
                            if (constraintAnchorArr[0].d == null || constraintAnchorArr[1].d == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f.e.d(constraintWidget.Q());
                                constraintWidget.g.e.d(constraintWidget.w());
                                constraintWidget.b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i2 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int iQ3 = constraintWidget.Q();
                            float f = constraintWidget.T;
                            if (constraintWidget.v() == -1) {
                                f = 1.0f / f;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour15, iQ3, dimensionBehaviour15, (int) ((iQ3 * f) + 0.5f));
                            constraintWidget.f.e.d(constraintWidget.Q());
                            constraintWidget.g.e.d(constraintWidget.w());
                            constraintWidget.b = true;
                        } else if (i2 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.g.e.m = constraintWidget.w();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = w5Var.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Q(), dimensionBehaviour17, (int) ((constraintWidget.u * w5Var.w()) + 0.5f));
                                constraintWidget.f.e.d(constraintWidget.Q());
                                constraintWidget.g.e.d(constraintWidget.w());
                                constraintWidget.b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.M;
                            if (constraintAnchorArr2[2].d == null || constraintAnchorArr2[3].d == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f.e.d(constraintWidget.Q());
                                constraintWidget.g.e.d(constraintWidget.w());
                                constraintWidget.b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i == 1 || i2 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f.e.m = constraintWidget.Q();
                            constraintWidget.g.e.m = constraintWidget.w();
                        } else if (i2 == 2 && i == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = w5Var.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.r * w5Var.Q()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.u * w5Var.w()) + 0.5f));
                                    constraintWidget.f.e.d(constraintWidget.Q());
                                    constraintWidget.g.e.d(constraintWidget.w());
                                    constraintWidget.b = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void c() {
        d(this.e);
        this.i.clear();
        n6.a = 0;
        i(this.a.f, 0, this.i);
        i(this.a.g, 1, this.i);
        this.b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.d.f.f();
        this.d.g.f();
        arrayList.add(this.d.f);
        arrayList.add(this.d.g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.d.G0) {
            if (constraintWidget instanceof y5) {
                arrayList.add(new k6(constraintWidget));
            } else {
                if (constraintWidget.W()) {
                    if (constraintWidget.d == null) {
                        constraintWidget.d = new g6(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.d);
                } else {
                    arrayList.add(constraintWidget.f);
                }
                if (constraintWidget.Y()) {
                    if (constraintWidget.e == null) {
                        constraintWidget.e = new g6(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.e);
                } else {
                    arrayList.add(constraintWidget.g);
                }
                if (constraintWidget instanceof a6) {
                    arrayList.add(new l6(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        for (WidgetRun widgetRun : arrayList) {
            if (widgetRun.b != this.d) {
                widgetRun.d();
            }
        }
    }

    public final int e(w5 w5Var, int i) {
        int size = this.i.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jMax = Math.max(jMax, this.i.get(i2).b(w5Var, i));
        }
        return (int) jMax;
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.b || this.c) {
            for (ConstraintWidget constraintWidget : this.a.G0) {
                constraintWidget.b = false;
                constraintWidget.f.r();
                constraintWidget.g.q();
            }
            w5 w5Var = this.a;
            w5Var.b = false;
            w5Var.f.r();
            this.a.g.q();
            this.c = false;
        }
        if (b(this.d)) {
            return false;
        }
        this.a.G0(0);
        this.a.H0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.a.t(1);
        if (this.b) {
            c();
        }
        int iR = this.a.R();
        int iS = this.a.S();
        this.a.f.h.d(iR);
        this.a.g.h.d(iS);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourT == dimensionBehaviour || dimensionBehaviourT2 == dimensionBehaviour) {
            if (z4) {
                Iterator<WidgetRun> it = this.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().m()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                w5 w5Var2 = this.a;
                w5Var2.F0(e(w5Var2, 0));
                w5 w5Var3 = this.a;
                w5Var3.f.e.d(w5Var3.Q());
            }
            if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                w5 w5Var4 = this.a;
                w5Var4.i0(e(w5Var4, 1));
                w5 w5Var5 = this.a;
                w5Var5.g.e.d(w5Var5.w());
            }
        }
        w5 w5Var6 = this.a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = w5Var6.P;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iQ = w5Var6.Q() + iR;
            this.a.f.i.d(iQ);
            this.a.f.e.d(iQ - iR);
            m();
            w5 w5Var7 = this.a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = w5Var7.P;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = w5Var7.w() + iS;
                this.a.g.i.d(iW);
                this.a.g.e.d(iW - iS);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (WidgetRun widgetRun : this.e) {
            if (widgetRun.b != this.a || widgetRun.g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.e) {
            if (z2 || widgetRun2.b != this.a) {
                if (!widgetRun2.h.j || ((!widgetRun2.i.j && !(widgetRun2 instanceof k6)) || (!widgetRun2.e.j && !(widgetRun2 instanceof g6) && !(widgetRun2 instanceof k6)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.a.m0(dimensionBehaviourT);
        this.a.B0(dimensionBehaviourT2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.b) {
            for (ConstraintWidget constraintWidget : this.a.G0) {
                constraintWidget.b = false;
                m6 m6Var = constraintWidget.f;
                m6Var.e.j = false;
                m6Var.g = false;
                m6Var.r();
                o6 o6Var = constraintWidget.g;
                o6Var.e.j = false;
                o6Var.g = false;
                o6Var.q();
            }
            w5 w5Var = this.a;
            w5Var.b = false;
            m6 m6Var2 = w5Var.f;
            m6Var2.e.j = false;
            m6Var2.g = false;
            m6Var2.r();
            o6 o6Var2 = this.a.g;
            o6Var2.e.j = false;
            o6Var2.g = false;
            o6Var2.q();
            c();
        }
        if (b(this.d)) {
            return false;
        }
        this.a.G0(0);
        this.a.H0(0);
        this.a.f.h.d(0);
        this.a.g.h.d(0);
        return true;
    }

    public boolean h(boolean z, int i) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.a.t(1);
        int iR = this.a.R();
        int iS = this.a.S();
        if (z4 && (dimensionBehaviourT == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourT2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.f == i && !next.m()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                    w5 w5Var = this.a;
                    w5Var.F0(e(w5Var, 0));
                    w5 w5Var2 = this.a;
                    w5Var2.f.e.d(w5Var2.Q());
                }
            } else if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                w5 w5Var3 = this.a;
                w5Var3.i0(e(w5Var3, 1));
                w5 w5Var4 = this.a;
                w5Var4.g.e.d(w5Var4.w());
            }
        }
        if (i == 0) {
            w5 w5Var5 = this.a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = w5Var5.P;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iQ = w5Var5.Q() + iR;
                this.a.f.i.d(iQ);
                this.a.f.e.d(iQ - iR);
                z2 = true;
            }
            z2 = false;
        } else {
            w5 w5Var6 = this.a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = w5Var6.P;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = w5Var6.w() + iS;
                this.a.g.i.d(iW);
                this.a.g.e.d(iW - iS);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (WidgetRun widgetRun : this.e) {
            if (widgetRun.f == i && (widgetRun.b != this.a || widgetRun.g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.e) {
            if (widgetRun2.f == i && (z2 || widgetRun2.b != this.a)) {
                if (!widgetRun2.h.j || !widgetRun2.i.j || (!(widgetRun2 instanceof g6) && !widgetRun2.e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.a.m0(dimensionBehaviourT);
        this.a.B0(dimensionBehaviourT2);
        return z3;
    }

    public final void i(WidgetRun widgetRun, int i, ArrayList<n6> arrayList) {
        for (h6 h6Var : widgetRun.h.k) {
            if (h6Var instanceof DependencyNode) {
                a((DependencyNode) h6Var, i, 0, widgetRun.i, arrayList, null);
            } else if (h6Var instanceof WidgetRun) {
                a(((WidgetRun) h6Var).h, i, 0, widgetRun.i, arrayList, null);
            }
        }
        for (h6 h6Var2 : widgetRun.i.k) {
            if (h6Var2 instanceof DependencyNode) {
                a((DependencyNode) h6Var2, i, 1, widgetRun.h, arrayList, null);
            } else if (h6Var2 instanceof WidgetRun) {
                a(((WidgetRun) h6Var2).i, i, 1, widgetRun.h, arrayList, null);
            }
        }
        if (i == 1) {
            for (h6 h6Var3 : ((o6) widgetRun).k.k) {
                if (h6Var3 instanceof DependencyNode) {
                    a((DependencyNode) h6Var3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    public void j() {
        this.b = true;
    }

    public void k() {
        this.c = true;
    }

    public final void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        f6.a aVar = this.h;
        aVar.a = dimensionBehaviour;
        aVar.b = dimensionBehaviour2;
        aVar.c = i;
        aVar.d = i2;
        this.g.a(constraintWidget, aVar);
        constraintWidget.F0(this.h.e);
        constraintWidget.i0(this.h.f);
        constraintWidget.h0(this.h.h);
        constraintWidget.c0(this.h.g);
    }

    public void m() {
        j6 j6Var;
        for (ConstraintWidget constraintWidget : this.a.G0) {
            if (!constraintWidget.b) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i = constraintWidget.m;
                int i2 = constraintWidget.n;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                j6 j6Var2 = constraintWidget.f.e;
                boolean z3 = j6Var2.j;
                j6 j6Var3 = constraintWidget.g.e;
                boolean z4 = j6Var3.j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, j6Var2.g, dimensionBehaviour4, j6Var3.g);
                    constraintWidget.b = true;
                } else if (z3 && z) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, j6Var2.g, dimensionBehaviour3, j6Var3.g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.g.e.m = constraintWidget.w();
                    } else {
                        constraintWidget.g.e.d(constraintWidget.w());
                        constraintWidget.b = true;
                    }
                } else if (z4 && z2) {
                    l(constraintWidget, dimensionBehaviour3, j6Var2.g, ConstraintWidget.DimensionBehaviour.FIXED, j6Var3.g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f.e.m = constraintWidget.Q();
                    } else {
                        constraintWidget.f.e.d(constraintWidget.Q());
                        constraintWidget.b = true;
                    }
                }
                if (constraintWidget.b && (j6Var = constraintWidget.g.l) != null) {
                    j6Var.d(constraintWidget.o());
                }
            }
        }
    }

    public void n(f6.b bVar) {
        this.g = bVar;
    }
}
