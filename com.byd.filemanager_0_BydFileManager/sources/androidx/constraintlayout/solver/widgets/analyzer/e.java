package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private androidx.constraintlayout.solver.widgets.d f764a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private androidx.constraintlayout.solver.widgets.d f767d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f765b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f766c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<WidgetRun> f768e = new ArrayList<>();
    private ArrayList<j> f = new ArrayList<>();
    private b.InterfaceC0009b g = null;
    private b.a h = new b.a();
    ArrayList<j> i = new ArrayList<>();

    public e(androidx.constraintlayout.solver.widgets.d dVar) {
        this.f764a = dVar;
        this.f767d = dVar;
    }

    private void a(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<j> arrayList, j jVar) {
        WidgetRun widgetRun = dependencyNode.f746d;
        if (widgetRun.f751c == null) {
            androidx.constraintlayout.solver.widgets.d dVar = this.f764a;
            if (widgetRun == dVar.f || widgetRun == dVar.g) {
                return;
            }
            if (jVar == null) {
                jVar = new j(widgetRun, i2);
                arrayList.add(jVar);
            }
            widgetRun.f751c = jVar;
            jVar.a(widgetRun);
            for (d dVar2 : widgetRun.h.k) {
                if (dVar2 instanceof DependencyNode) {
                    a((DependencyNode) dVar2, i, 0, dependencyNode2, arrayList, jVar);
                }
            }
            for (d dVar3 : widgetRun.i.k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i, 1, dependencyNode2, arrayList, jVar);
                }
            }
            if (i == 1 && (widgetRun instanceof k)) {
                for (d dVar4 : ((k) widgetRun).k.k) {
                    if (dVar4 instanceof DependencyNode) {
                        a((DependencyNode) dVar4, i, 2, dependencyNode2, arrayList, jVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.h.l) {
                if (dependencyNode3 == dependencyNode2) {
                    jVar.f772c = true;
                }
                a(dependencyNode3, i, 0, dependencyNode2, arrayList, jVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.i.l) {
                if (dependencyNode4 == dependencyNode2) {
                    jVar.f772c = true;
                }
                a(dependencyNode4, i, 1, dependencyNode2, arrayList, jVar);
            }
            if (i == 1 && (widgetRun instanceof k)) {
                Iterator<DependencyNode> it = ((k) widgetRun).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i, 2, dependencyNode2, arrayList, jVar);
                }
            }
        }
    }

    private boolean b(androidx.constraintlayout.solver.widgets.d dVar) {
        int iQ;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int iW;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        for (ConstraintWidget constraintWidget : dVar.G0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (constraintWidget.P() == 8) {
                constraintWidget.f736b = true;
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
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.m == 1 && (constraintWidget.E.f731d == null || constraintWidget.G.f731d == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.n == 1 && (constraintWidget.F.f731d == null || constraintWidget.H.f731d == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                i iVar = constraintWidget.f;
                iVar.f752d = dimensionBehaviour9;
                int i = constraintWidget.m;
                iVar.f749a = i;
                k kVar = constraintWidget.g;
                kVar.f752d = dimensionBehaviour10;
                int i2 = constraintWidget.n;
                kVar.f749a = i2;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iQ2 = constraintWidget.Q();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iQ = (dVar.Q() - constraintWidget.E.f732e) - constraintWidget.G.f732e;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iQ = iQ2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iW2 = constraintWidget.w();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iW = (dVar.w() - constraintWidget.F.f732e) - constraintWidget.H.f732e;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iW = iW2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iQ, dimensionBehaviour2, iW);
                    constraintWidget.f.f753e.d(constraintWidget.Q());
                    constraintWidget.g.f753e.d(constraintWidget.w());
                    constraintWidget.f736b = true;
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
                            constraintWidget.f.f753e.d(constraintWidget.Q());
                            constraintWidget.g.f753e.d(constraintWidget.w());
                            constraintWidget.f736b = true;
                        } else if (i == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f.f753e.m = constraintWidget.Q();
                        } else if (i == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.r * dVar.Q()) + 0.5f), dimensionBehaviour10, constraintWidget.w());
                                constraintWidget.f.f753e.d(constraintWidget.Q());
                                constraintWidget.g.f753e.d(constraintWidget.w());
                                constraintWidget.f736b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.M;
                            if (constraintAnchorArr[0].f731d == null || constraintAnchorArr[1].f731d == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f.f753e.d(constraintWidget.Q());
                                constraintWidget.g.f753e.d(constraintWidget.w());
                                constraintWidget.f736b = true;
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
                            constraintWidget.f.f753e.d(constraintWidget.Q());
                            constraintWidget.g.f753e.d(constraintWidget.w());
                            constraintWidget.f736b = true;
                        } else if (i2 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.g.f753e.m = constraintWidget.w();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Q(), dimensionBehaviour17, (int) ((constraintWidget.u * dVar.w()) + 0.5f));
                                constraintWidget.f.f753e.d(constraintWidget.Q());
                                constraintWidget.g.f753e.d(constraintWidget.w());
                                constraintWidget.f736b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.M;
                            if (constraintAnchorArr2[2].f731d == null || constraintAnchorArr2[3].f731d == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f.f753e.d(constraintWidget.Q());
                                constraintWidget.g.f753e.d(constraintWidget.w());
                                constraintWidget.f736b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i == 1 || i2 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f.f753e.m = constraintWidget.Q();
                            constraintWidget.g.f753e.m = constraintWidget.w();
                        } else if (i2 == 2 && i == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.r * dVar.Q()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.u * dVar.w()) + 0.5f));
                                    constraintWidget.f.f753e.d(constraintWidget.Q());
                                    constraintWidget.g.f753e.d(constraintWidget.w());
                                    constraintWidget.f736b = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int e(androidx.constraintlayout.solver.widgets.d dVar, int i) {
        int size = this.i.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jMax = Math.max(jMax, this.i.get(i2).b(dVar, i));
        }
        return (int) jMax;
    }

    private void i(WidgetRun widgetRun, int i, ArrayList<j> arrayList) {
        for (d dVar : widgetRun.h.k) {
            if (dVar instanceof DependencyNode) {
                a((DependencyNode) dVar, i, 0, widgetRun.i, arrayList, null);
            } else if (dVar instanceof WidgetRun) {
                a(((WidgetRun) dVar).h, i, 0, widgetRun.i, arrayList, null);
            }
        }
        for (d dVar2 : widgetRun.i.k) {
            if (dVar2 instanceof DependencyNode) {
                a((DependencyNode) dVar2, i, 1, widgetRun.h, arrayList, null);
            } else if (dVar2 instanceof WidgetRun) {
                a(((WidgetRun) dVar2).i, i, 1, widgetRun.h, arrayList, null);
            }
        }
        if (i == 1) {
            for (d dVar3 : ((k) widgetRun).k.k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    private void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        b.a aVar = this.h;
        aVar.f759a = dimensionBehaviour;
        aVar.f760b = dimensionBehaviour2;
        aVar.f761c = i;
        aVar.f762d = i2;
        this.g.a(constraintWidget, aVar);
        constraintWidget.F0(this.h.f763e);
        constraintWidget.i0(this.h.f);
        constraintWidget.h0(this.h.h);
        constraintWidget.c0(this.h.g);
    }

    public void c() {
        d(this.f768e);
        this.i.clear();
        j.f770a = 0;
        i(this.f764a.f, 0, this.i);
        i(this.f764a.g, 1, this.i);
        this.f765b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.f767d.f.f();
        this.f767d.g.f();
        arrayList.add(this.f767d.f);
        arrayList.add(this.f767d.g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.f767d.G0) {
            if (constraintWidget instanceof androidx.constraintlayout.solver.widgets.f) {
                arrayList.add(new g(constraintWidget));
            } else {
                if (constraintWidget.W()) {
                    if (constraintWidget.f738d == null) {
                        constraintWidget.f738d = new c(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f738d);
                } else {
                    arrayList.add(constraintWidget.f);
                }
                if (constraintWidget.Y()) {
                    if (constraintWidget.f739e == null) {
                        constraintWidget.f739e = new c(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f739e);
                } else {
                    arrayList.add(constraintWidget.g);
                }
                if (constraintWidget instanceof androidx.constraintlayout.solver.widgets.h) {
                    arrayList.add(new h(constraintWidget));
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
            if (widgetRun.f750b != this.f767d) {
                widgetRun.d();
            }
        }
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f765b || this.f766c) {
            for (ConstraintWidget constraintWidget : this.f764a.G0) {
                constraintWidget.f736b = false;
                constraintWidget.f.r();
                constraintWidget.g.q();
            }
            androidx.constraintlayout.solver.widgets.d dVar = this.f764a;
            dVar.f736b = false;
            dVar.f.r();
            this.f764a.g.q();
            this.f766c = false;
        }
        if (b(this.f767d)) {
            return false;
        }
        this.f764a.G0(0);
        this.f764a.H0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f764a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f764a.t(1);
        if (this.f765b) {
            c();
        }
        int iR = this.f764a.R();
        int iS = this.f764a.S();
        this.f764a.f.h.d(iR);
        this.f764a.g.h.d(iS);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourT == dimensionBehaviour || dimensionBehaviourT2 == dimensionBehaviour) {
            if (z4) {
                Iterator<WidgetRun> it = this.f768e.iterator();
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
                this.f764a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                androidx.constraintlayout.solver.widgets.d dVar2 = this.f764a;
                dVar2.F0(e(dVar2, 0));
                androidx.constraintlayout.solver.widgets.d dVar3 = this.f764a;
                dVar3.f.f753e.d(dVar3.Q());
            }
            if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f764a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                androidx.constraintlayout.solver.widgets.d dVar4 = this.f764a;
                dVar4.i0(e(dVar4, 1));
                androidx.constraintlayout.solver.widgets.d dVar5 = this.f764a;
                dVar5.g.f753e.d(dVar5.w());
            }
        }
        androidx.constraintlayout.solver.widgets.d dVar6 = this.f764a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar6.P;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iQ = dVar6.Q() + iR;
            this.f764a.f.i.d(iQ);
            this.f764a.f.f753e.d(iQ - iR);
            m();
            androidx.constraintlayout.solver.widgets.d dVar7 = this.f764a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar7.P;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar7.w() + iS;
                this.f764a.g.i.d(iW);
                this.f764a.g.f753e.d(iW - iS);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (WidgetRun widgetRun : this.f768e) {
            if (widgetRun.f750b != this.f764a || widgetRun.g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f768e) {
            if (z2 || widgetRun2.f750b != this.f764a) {
                if (!widgetRun2.h.j || ((!widgetRun2.i.j && !(widgetRun2 instanceof g)) || (!widgetRun2.f753e.j && !(widgetRun2 instanceof c) && !(widgetRun2 instanceof g)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f764a.m0(dimensionBehaviourT);
        this.f764a.B0(dimensionBehaviourT2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f765b) {
            for (ConstraintWidget constraintWidget : this.f764a.G0) {
                constraintWidget.f736b = false;
                i iVar = constraintWidget.f;
                iVar.f753e.j = false;
                iVar.g = false;
                iVar.r();
                k kVar = constraintWidget.g;
                kVar.f753e.j = false;
                kVar.g = false;
                kVar.q();
            }
            androidx.constraintlayout.solver.widgets.d dVar = this.f764a;
            dVar.f736b = false;
            i iVar2 = dVar.f;
            iVar2.f753e.j = false;
            iVar2.g = false;
            iVar2.r();
            k kVar2 = this.f764a.g;
            kVar2.f753e.j = false;
            kVar2.g = false;
            kVar2.q();
            c();
        }
        if (b(this.f767d)) {
            return false;
        }
        this.f764a.G0(0);
        this.f764a.H0(0);
        this.f764a.f.h.d(0);
        this.f764a.g.h.d(0);
        return true;
    }

    public boolean h(boolean z, int i) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f764a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f764a.t(1);
        int iR = this.f764a.R();
        int iS = this.f764a.S();
        if (z4 && (dimensionBehaviourT == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourT2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.f768e.iterator();
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
                    this.f764a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                    androidx.constraintlayout.solver.widgets.d dVar = this.f764a;
                    dVar.F0(e(dVar, 0));
                    androidx.constraintlayout.solver.widgets.d dVar2 = this.f764a;
                    dVar2.f.f753e.d(dVar2.Q());
                }
            } else if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f764a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                androidx.constraintlayout.solver.widgets.d dVar3 = this.f764a;
                dVar3.i0(e(dVar3, 1));
                androidx.constraintlayout.solver.widgets.d dVar4 = this.f764a;
                dVar4.g.f753e.d(dVar4.w());
            }
        }
        if (i == 0) {
            androidx.constraintlayout.solver.widgets.d dVar5 = this.f764a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar5.P;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iQ = dVar5.Q() + iR;
                this.f764a.f.i.d(iQ);
                this.f764a.f.f753e.d(iQ - iR);
                z2 = true;
            }
            z2 = false;
        } else {
            androidx.constraintlayout.solver.widgets.d dVar6 = this.f764a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar6.P;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar6.w() + iS;
                this.f764a.g.i.d(iW);
                this.f764a.g.f753e.d(iW - iS);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (WidgetRun widgetRun : this.f768e) {
            if (widgetRun.f == i && (widgetRun.f750b != this.f764a || widgetRun.g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f768e) {
            if (widgetRun2.f == i && (z2 || widgetRun2.f750b != this.f764a)) {
                if (!widgetRun2.h.j || !widgetRun2.i.j || (!(widgetRun2 instanceof c) && !widgetRun2.f753e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f764a.m0(dimensionBehaviourT);
        this.f764a.B0(dimensionBehaviourT2);
        return z3;
    }

    public void j() {
        this.f765b = true;
    }

    public void k() {
        this.f766c = true;
    }

    public void m() {
        f fVar;
        for (ConstraintWidget constraintWidget : this.f764a.G0) {
            if (!constraintWidget.f736b) {
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
                f fVar2 = constraintWidget.f.f753e;
                boolean z3 = fVar2.j;
                f fVar3 = constraintWidget.g.f753e;
                boolean z4 = fVar3.j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, fVar2.g, dimensionBehaviour4, fVar3.g);
                    constraintWidget.f736b = true;
                } else if (z3 && z) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, fVar2.g, dimensionBehaviour3, fVar3.g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.g.f753e.m = constraintWidget.w();
                    } else {
                        constraintWidget.g.f753e.d(constraintWidget.w());
                        constraintWidget.f736b = true;
                    }
                } else if (z4 && z2) {
                    l(constraintWidget, dimensionBehaviour3, fVar2.g, ConstraintWidget.DimensionBehaviour.FIXED, fVar3.g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f.f753e.m = constraintWidget.Q();
                    } else {
                        constraintWidget.f.f753e.d(constraintWidget.Q());
                        constraintWidget.f736b = true;
                    }
                }
                if (constraintWidget.f736b && (fVar = constraintWidget.g.l) != null) {
                    fVar.d(constraintWidget.o());
                }
            }
        }
    }

    public void n(b.InterfaceC0009b interfaceC0009b) {
        this.g = interfaceC0009b;
    }
}
