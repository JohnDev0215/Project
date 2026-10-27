package b.e.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import b.e.b.j.l.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.e.b.j.d f1434a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.e.b.j.d f1437d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1435b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1436c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<WidgetRun> f1438e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<j> f1439f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b.InterfaceC0016b f1440g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b.a f1441h = new b.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<j> f1442i = new ArrayList<>();

    public e(b.e.b.j.d dVar) {
        this.f1434a = dVar;
        this.f1437d = dVar;
    }

    public final void a(DependencyNode dependencyNode, int i2, int i3, DependencyNode dependencyNode2, ArrayList<j> arrayList, j jVar) {
        WidgetRun widgetRun = dependencyNode.f353d;
        if (widgetRun.f362c == null) {
            b.e.b.j.d dVar = this.f1434a;
            if (widgetRun == dVar.f343f || widgetRun == dVar.f344g) {
                return;
            }
            if (jVar == null) {
                jVar = new j(widgetRun, i3);
                arrayList.add(jVar);
            }
            widgetRun.f362c = jVar;
            jVar.a(widgetRun);
            for (d dVar2 : widgetRun.f367h.k) {
                if (dVar2 instanceof DependencyNode) {
                    a((DependencyNode) dVar2, i2, 0, dependencyNode2, arrayList, jVar);
                }
            }
            for (d dVar3 : widgetRun.f368i.k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i2, 1, dependencyNode2, arrayList, jVar);
                }
            }
            if (i2 == 1 && (widgetRun instanceof k)) {
                for (d dVar4 : ((k) widgetRun).k.k) {
                    if (dVar4 instanceof DependencyNode) {
                        a((DependencyNode) dVar4, i2, 2, dependencyNode2, arrayList, jVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.f367h.l) {
                if (dependencyNode3 == dependencyNode2) {
                    jVar.f1446c = true;
                }
                a(dependencyNode3, i2, 0, dependencyNode2, arrayList, jVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.f368i.l) {
                if (dependencyNode4 == dependencyNode2) {
                    jVar.f1446c = true;
                }
                a(dependencyNode4, i2, 1, dependencyNode2, arrayList, jVar);
            }
            if (i2 == 1 && (widgetRun instanceof k)) {
                Iterator<DependencyNode> it = ((k) widgetRun).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i2, 2, dependencyNode2, arrayList, jVar);
                }
            }
        }
    }

    public final boolean b(b.e.b.j.d dVar) {
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
                constraintWidget.f339b = true;
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
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.m == 1 && (constraintWidget.E.f332d == null || constraintWidget.G.f332d == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.n == 1 && (constraintWidget.F.f332d == null || constraintWidget.H.f332d == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                i iVar = constraintWidget.f343f;
                iVar.f363d = dimensionBehaviour9;
                int i2 = constraintWidget.m;
                iVar.f360a = i2;
                k kVar = constraintWidget.f344g;
                kVar.f363d = dimensionBehaviour10;
                int i3 = constraintWidget.n;
                kVar.f360a = i3;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iQ2 = constraintWidget.Q();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iQ = (dVar.Q() - constraintWidget.E.f333e) - constraintWidget.G.f333e;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iQ = iQ2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iW2 = constraintWidget.w();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iW = (dVar.w() - constraintWidget.F.f333e) - constraintWidget.H.f333e;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iW = iW2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iQ, dimensionBehaviour2, iW);
                    constraintWidget.f343f.f364e.d(constraintWidget.Q());
                    constraintWidget.f344g.f364e.d(constraintWidget.w());
                    constraintWidget.f339b = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i2 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int iW3 = constraintWidget.w();
                            int i4 = (int) ((iW3 * constraintWidget.T) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour12, i4, dimensionBehaviour12, iW3);
                            constraintWidget.f343f.f364e.d(constraintWidget.Q());
                            constraintWidget.f344g.f364e.d(constraintWidget.w());
                            constraintWidget.f339b = true;
                        } else if (i2 == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f343f.f364e.m = constraintWidget.Q();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.r * dVar.Q()) + 0.5f), dimensionBehaviour10, constraintWidget.w());
                                constraintWidget.f343f.f364e.d(constraintWidget.Q());
                                constraintWidget.f344g.f364e.d(constraintWidget.w());
                                constraintWidget.f339b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.M;
                            if (constraintAnchorArr[0].f332d == null || constraintAnchorArr[1].f332d == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f343f.f364e.d(constraintWidget.Q());
                                constraintWidget.f344g.f364e.d(constraintWidget.w());
                                constraintWidget.f339b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i3 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int iQ3 = constraintWidget.Q();
                            float f2 = constraintWidget.T;
                            if (constraintWidget.v() == -1) {
                                f2 = 1.0f / f2;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour15, iQ3, dimensionBehaviour15, (int) ((iQ3 * f2) + 0.5f));
                            constraintWidget.f343f.f364e.d(constraintWidget.Q());
                            constraintWidget.f344g.f364e.d(constraintWidget.w());
                            constraintWidget.f339b = true;
                        } else if (i3 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.f344g.f364e.m = constraintWidget.w();
                        } else if (i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Q(), dimensionBehaviour17, (int) ((constraintWidget.u * dVar.w()) + 0.5f));
                                constraintWidget.f343f.f364e.d(constraintWidget.Q());
                                constraintWidget.f344g.f364e.d(constraintWidget.w());
                                constraintWidget.f339b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.M;
                            if (constraintAnchorArr2[2].f332d == null || constraintAnchorArr2[3].f332d == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f343f.f364e.d(constraintWidget.Q());
                                constraintWidget.f344g.f364e.d(constraintWidget.w());
                                constraintWidget.f339b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i2 == 1 || i3 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f343f.f364e.m = constraintWidget.Q();
                            constraintWidget.f344g.f364e.m = constraintWidget.w();
                        } else if (i3 == 2 && i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.r * dVar.Q()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.u * dVar.w()) + 0.5f));
                                    constraintWidget.f343f.f364e.d(constraintWidget.Q());
                                    constraintWidget.f344g.f364e.d(constraintWidget.w());
                                    constraintWidget.f339b = true;
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
        d(this.f1438e);
        this.f1442i.clear();
        j.f1444a = 0;
        i(this.f1434a.f343f, 0, this.f1442i);
        i(this.f1434a.f344g, 1, this.f1442i);
        this.f1435b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.f1437d.f343f.f();
        this.f1437d.f344g.f();
        arrayList.add(this.f1437d.f343f);
        arrayList.add(this.f1437d.f344g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.f1437d.G0) {
            if (constraintWidget instanceof b.e.b.j.f) {
                arrayList.add(new g(constraintWidget));
            } else {
                if (constraintWidget.W()) {
                    if (constraintWidget.f341d == null) {
                        constraintWidget.f341d = new c(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f341d);
                } else {
                    arrayList.add(constraintWidget.f343f);
                }
                if (constraintWidget.Y()) {
                    if (constraintWidget.f342e == null) {
                        constraintWidget.f342e = new c(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f342e);
                } else {
                    arrayList.add(constraintWidget.f344g);
                }
                if (constraintWidget instanceof b.e.b.j.h) {
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
            if (widgetRun.f361b != this.f1437d) {
                widgetRun.d();
            }
        }
    }

    public final int e(b.e.b.j.d dVar, int i2) {
        int size = this.f1442i.size();
        long jMax = 0;
        for (int i3 = 0; i3 < size; i3++) {
            jMax = Math.max(jMax, this.f1442i.get(i3).b(dVar, i2));
        }
        return (int) jMax;
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f1435b || this.f1436c) {
            for (ConstraintWidget constraintWidget : this.f1434a.G0) {
                constraintWidget.f339b = false;
                constraintWidget.f343f.r();
                constraintWidget.f344g.q();
            }
            b.e.b.j.d dVar = this.f1434a;
            dVar.f339b = false;
            dVar.f343f.r();
            this.f1434a.f344g.q();
            this.f1436c = false;
        }
        if (b(this.f1437d)) {
            return false;
        }
        this.f1434a.G0(0);
        this.f1434a.H0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f1434a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f1434a.t(1);
        if (this.f1435b) {
            c();
        }
        int iR = this.f1434a.R();
        int iS = this.f1434a.S();
        this.f1434a.f343f.f367h.d(iR);
        this.f1434a.f344g.f367h.d(iS);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourT == dimensionBehaviour || dimensionBehaviourT2 == dimensionBehaviour) {
            if (z4) {
                Iterator<WidgetRun> it = this.f1438e.iterator();
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
                this.f1434a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                b.e.b.j.d dVar2 = this.f1434a;
                dVar2.F0(e(dVar2, 0));
                b.e.b.j.d dVar3 = this.f1434a;
                dVar3.f343f.f364e.d(dVar3.Q());
            }
            if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f1434a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                b.e.b.j.d dVar4 = this.f1434a;
                dVar4.i0(e(dVar4, 1));
                b.e.b.j.d dVar5 = this.f1434a;
                dVar5.f344g.f364e.d(dVar5.w());
            }
        }
        b.e.b.j.d dVar6 = this.f1434a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar6.P;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iQ = dVar6.Q() + iR;
            this.f1434a.f343f.f368i.d(iQ);
            this.f1434a.f343f.f364e.d(iQ - iR);
            m();
            b.e.b.j.d dVar7 = this.f1434a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar7.P;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar7.w() + iS;
                this.f1434a.f344g.f368i.d(iW);
                this.f1434a.f344g.f364e.d(iW - iS);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (WidgetRun widgetRun : this.f1438e) {
            if (widgetRun.f361b != this.f1434a || widgetRun.f366g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f1438e) {
            if (z2 || widgetRun2.f361b != this.f1434a) {
                if (!widgetRun2.f367h.j || ((!widgetRun2.f368i.j && !(widgetRun2 instanceof g)) || (!widgetRun2.f364e.j && !(widgetRun2 instanceof c) && !(widgetRun2 instanceof g)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f1434a.m0(dimensionBehaviourT);
        this.f1434a.B0(dimensionBehaviourT2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f1435b) {
            for (ConstraintWidget constraintWidget : this.f1434a.G0) {
                constraintWidget.f339b = false;
                i iVar = constraintWidget.f343f;
                iVar.f364e.j = false;
                iVar.f366g = false;
                iVar.r();
                k kVar = constraintWidget.f344g;
                kVar.f364e.j = false;
                kVar.f366g = false;
                kVar.q();
            }
            b.e.b.j.d dVar = this.f1434a;
            dVar.f339b = false;
            i iVar2 = dVar.f343f;
            iVar2.f364e.j = false;
            iVar2.f366g = false;
            iVar2.r();
            k kVar2 = this.f1434a.f344g;
            kVar2.f364e.j = false;
            kVar2.f366g = false;
            kVar2.q();
            c();
        }
        if (b(this.f1437d)) {
            return false;
        }
        this.f1434a.G0(0);
        this.f1434a.H0(0);
        this.f1434a.f343f.f367h.d(0);
        this.f1434a.f344g.f367h.d(0);
        return true;
    }

    public boolean h(boolean z, int i2) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f1434a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f1434a.t(1);
        int iR = this.f1434a.R();
        int iS = this.f1434a.S();
        if (z4 && (dimensionBehaviourT == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourT2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.f1438e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.f365f == i2 && !next.m()) {
                    z4 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z4 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f1434a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                    b.e.b.j.d dVar = this.f1434a;
                    dVar.F0(e(dVar, 0));
                    b.e.b.j.d dVar2 = this.f1434a;
                    dVar2.f343f.f364e.d(dVar2.Q());
                }
            } else if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f1434a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                b.e.b.j.d dVar3 = this.f1434a;
                dVar3.i0(e(dVar3, 1));
                b.e.b.j.d dVar4 = this.f1434a;
                dVar4.f344g.f364e.d(dVar4.w());
            }
        }
        if (i2 == 0) {
            b.e.b.j.d dVar5 = this.f1434a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar5.P;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iQ = dVar5.Q() + iR;
                this.f1434a.f343f.f368i.d(iQ);
                this.f1434a.f343f.f364e.d(iQ - iR);
                z2 = true;
            }
            z2 = false;
        } else {
            b.e.b.j.d dVar6 = this.f1434a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar6.P;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar6.w() + iS;
                this.f1434a.f344g.f368i.d(iW);
                this.f1434a.f344g.f364e.d(iW - iS);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (WidgetRun widgetRun : this.f1438e) {
            if (widgetRun.f365f == i2 && (widgetRun.f361b != this.f1434a || widgetRun.f366g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f1438e) {
            if (widgetRun2.f365f == i2 && (z2 || widgetRun2.f361b != this.f1434a)) {
                if (!widgetRun2.f367h.j || !widgetRun2.f368i.j || (!(widgetRun2 instanceof c) && !widgetRun2.f364e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f1434a.m0(dimensionBehaviourT);
        this.f1434a.B0(dimensionBehaviourT2);
        return z3;
    }

    public final void i(WidgetRun widgetRun, int i2, ArrayList<j> arrayList) {
        for (d dVar : widgetRun.f367h.k) {
            if (dVar instanceof DependencyNode) {
                a((DependencyNode) dVar, i2, 0, widgetRun.f368i, arrayList, null);
            } else if (dVar instanceof WidgetRun) {
                a(((WidgetRun) dVar).f367h, i2, 0, widgetRun.f368i, arrayList, null);
            }
        }
        for (d dVar2 : widgetRun.f368i.k) {
            if (dVar2 instanceof DependencyNode) {
                a((DependencyNode) dVar2, i2, 1, widgetRun.f367h, arrayList, null);
            } else if (dVar2 instanceof WidgetRun) {
                a(((WidgetRun) dVar2).f368i, i2, 1, widgetRun.f367h, arrayList, null);
            }
        }
        if (i2 == 1) {
            for (d dVar3 : ((k) widgetRun).k.k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i2, 2, null, arrayList, null);
                }
            }
        }
    }

    public void j() {
        this.f1435b = true;
    }

    public void k() {
        this.f1436c = true;
    }

    public final void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        b.a aVar = this.f1441h;
        aVar.f1425a = dimensionBehaviour;
        aVar.f1426b = dimensionBehaviour2;
        aVar.f1427c = i2;
        aVar.f1428d = i3;
        this.f1440g.a(constraintWidget, aVar);
        constraintWidget.F0(this.f1441h.f1429e);
        constraintWidget.i0(this.f1441h.f1430f);
        constraintWidget.h0(this.f1441h.f1432h);
        constraintWidget.c0(this.f1441h.f1431g);
    }

    public void m() {
        f fVar;
        for (ConstraintWidget constraintWidget : this.f1434a.G0) {
            if (!constraintWidget.f339b) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i2 = constraintWidget.m;
                int i3 = constraintWidget.n;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i3 == 1)) {
                    z = true;
                }
                f fVar2 = constraintWidget.f343f.f364e;
                boolean z3 = fVar2.j;
                f fVar3 = constraintWidget.f344g.f364e;
                boolean z4 = fVar3.j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, fVar2.f356g, dimensionBehaviour4, fVar3.f356g);
                    constraintWidget.f339b = true;
                } else if (z3 && z) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, fVar2.f356g, dimensionBehaviour3, fVar3.f356g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f344g.f364e.m = constraintWidget.w();
                    } else {
                        constraintWidget.f344g.f364e.d(constraintWidget.w());
                        constraintWidget.f339b = true;
                    }
                } else if (z4 && z2) {
                    l(constraintWidget, dimensionBehaviour3, fVar2.f356g, ConstraintWidget.DimensionBehaviour.FIXED, fVar3.f356g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f343f.f364e.m = constraintWidget.Q();
                    } else {
                        constraintWidget.f343f.f364e.d(constraintWidget.Q());
                        constraintWidget.f339b = true;
                    }
                }
                if (constraintWidget.f339b && (fVar = constraintWidget.f344g.l) != null) {
                    fVar.d(constraintWidget.o());
                }
            }
        }
    }

    public void n(b.InterfaceC0016b interfaceC0016b) {
        this.f1440g = interfaceC0016b;
    }
}
