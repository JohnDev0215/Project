package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import b.f.b.j.l.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.f.b.j.d f2246a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.f.b.j.d f2249d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2247b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2248c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<WidgetRun> f2250e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<j> f2251f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b.InterfaceC0027b f2252g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b.a f2253h = new b.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<j> f2254i = new ArrayList<>();

    public e(b.f.b.j.d dVar) {
        this.f2246a = dVar;
        this.f2249d = dVar;
    }

    public final void a(DependencyNode dependencyNode, int i2, int i3, DependencyNode dependencyNode2, ArrayList<j> arrayList, j jVar) {
        WidgetRun widgetRun = dependencyNode.f642d;
        if (widgetRun.f651c == null) {
            b.f.b.j.d dVar = this.f2246a;
            if (widgetRun == dVar.f632f || widgetRun == dVar.f633g) {
                return;
            }
            if (jVar == null) {
                jVar = new j(widgetRun, i3);
                arrayList.add(jVar);
            }
            widgetRun.f651c = jVar;
            jVar.a(widgetRun);
            for (d dVar2 : widgetRun.f656h.k) {
                if (dVar2 instanceof DependencyNode) {
                    a((DependencyNode) dVar2, i2, 0, dependencyNode2, arrayList, jVar);
                }
            }
            for (d dVar3 : widgetRun.f657i.k) {
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
            for (DependencyNode dependencyNode3 : widgetRun.f656h.l) {
                if (dependencyNode3 == dependencyNode2) {
                    jVar.f2258c = true;
                }
                a(dependencyNode3, i2, 0, dependencyNode2, arrayList, jVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.f657i.l) {
                if (dependencyNode4 == dependencyNode2) {
                    jVar.f2258c = true;
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

    public final boolean b(b.f.b.j.d dVar) {
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
                constraintWidget.f628b = true;
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
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.m == 1 && (constraintWidget.E.f621d == null || constraintWidget.G.f621d == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.n == 1 && (constraintWidget.F.f621d == null || constraintWidget.H.f621d == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                i iVar = constraintWidget.f632f;
                iVar.f652d = dimensionBehaviour9;
                int i2 = constraintWidget.m;
                iVar.f649a = i2;
                k kVar = constraintWidget.f633g;
                kVar.f652d = dimensionBehaviour10;
                int i3 = constraintWidget.n;
                kVar.f649a = i3;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iQ2 = constraintWidget.Q();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iQ = (dVar.Q() - constraintWidget.E.f622e) - constraintWidget.G.f622e;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iQ = iQ2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iW2 = constraintWidget.w();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iW = (dVar.w() - constraintWidget.F.f622e) - constraintWidget.H.f622e;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iW = iW2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iQ, dimensionBehaviour2, iW);
                    constraintWidget.f632f.f653e.d(constraintWidget.Q());
                    constraintWidget.f633g.f653e.d(constraintWidget.w());
                    constraintWidget.f628b = true;
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
                            constraintWidget.f632f.f653e.d(constraintWidget.Q());
                            constraintWidget.f633g.f653e.d(constraintWidget.w());
                            constraintWidget.f628b = true;
                        } else if (i2 == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f632f.f653e.m = constraintWidget.Q();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.r * dVar.Q()) + 0.5f), dimensionBehaviour10, constraintWidget.w());
                                constraintWidget.f632f.f653e.d(constraintWidget.Q());
                                constraintWidget.f633g.f653e.d(constraintWidget.w());
                                constraintWidget.f628b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.M;
                            if (constraintAnchorArr[0].f621d == null || constraintAnchorArr[1].f621d == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f632f.f653e.d(constraintWidget.Q());
                                constraintWidget.f633g.f653e.d(constraintWidget.w());
                                constraintWidget.f628b = true;
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
                            constraintWidget.f632f.f653e.d(constraintWidget.Q());
                            constraintWidget.f633g.f653e.d(constraintWidget.w());
                            constraintWidget.f628b = true;
                        } else if (i3 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.f633g.f653e.m = constraintWidget.w();
                        } else if (i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Q(), dimensionBehaviour17, (int) ((constraintWidget.u * dVar.w()) + 0.5f));
                                constraintWidget.f632f.f653e.d(constraintWidget.Q());
                                constraintWidget.f633g.f653e.d(constraintWidget.w());
                                constraintWidget.f628b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.M;
                            if (constraintAnchorArr2[2].f621d == null || constraintAnchorArr2[3].f621d == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f632f.f653e.d(constraintWidget.Q());
                                constraintWidget.f633g.f653e.d(constraintWidget.w());
                                constraintWidget.f628b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i2 == 1 || i3 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f632f.f653e.m = constraintWidget.Q();
                            constraintWidget.f633g.f653e.m = constraintWidget.w();
                        } else if (i3 == 2 && i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.r * dVar.Q()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.u * dVar.w()) + 0.5f));
                                    constraintWidget.f632f.f653e.d(constraintWidget.Q());
                                    constraintWidget.f633g.f653e.d(constraintWidget.w());
                                    constraintWidget.f628b = true;
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
        d(this.f2250e);
        this.f2254i.clear();
        j.f2256a = 0;
        i(this.f2246a.f632f, 0, this.f2254i);
        i(this.f2246a.f633g, 1, this.f2254i);
        this.f2247b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.f2249d.f632f.f();
        this.f2249d.f633g.f();
        arrayList.add(this.f2249d.f632f);
        arrayList.add(this.f2249d.f633g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.f2249d.G0) {
            if (constraintWidget instanceof b.f.b.j.f) {
                arrayList.add(new g(constraintWidget));
            } else {
                if (constraintWidget.W()) {
                    if (constraintWidget.f630d == null) {
                        constraintWidget.f630d = new c(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f630d);
                } else {
                    arrayList.add(constraintWidget.f632f);
                }
                if (constraintWidget.Y()) {
                    if (constraintWidget.f631e == null) {
                        constraintWidget.f631e = new c(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f631e);
                } else {
                    arrayList.add(constraintWidget.f633g);
                }
                if (constraintWidget instanceof b.f.b.j.h) {
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
            if (widgetRun.f650b != this.f2249d) {
                widgetRun.d();
            }
        }
    }

    public final int e(b.f.b.j.d dVar, int i2) {
        int size = this.f2254i.size();
        long jMax = 0;
        for (int i3 = 0; i3 < size; i3++) {
            jMax = Math.max(jMax, this.f2254i.get(i3).b(dVar, i2));
        }
        return (int) jMax;
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f2247b || this.f2248c) {
            for (ConstraintWidget constraintWidget : this.f2246a.G0) {
                constraintWidget.f628b = false;
                constraintWidget.f632f.r();
                constraintWidget.f633g.q();
            }
            b.f.b.j.d dVar = this.f2246a;
            dVar.f628b = false;
            dVar.f632f.r();
            this.f2246a.f633g.q();
            this.f2248c = false;
        }
        if (b(this.f2249d)) {
            return false;
        }
        this.f2246a.G0(0);
        this.f2246a.H0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f2246a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f2246a.t(1);
        if (this.f2247b) {
            c();
        }
        int iR = this.f2246a.R();
        int iS = this.f2246a.S();
        this.f2246a.f632f.f656h.d(iR);
        this.f2246a.f633g.f656h.d(iS);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourT == dimensionBehaviour || dimensionBehaviourT2 == dimensionBehaviour) {
            if (z4) {
                Iterator<WidgetRun> it = this.f2250e.iterator();
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
                this.f2246a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                b.f.b.j.d dVar2 = this.f2246a;
                dVar2.F0(e(dVar2, 0));
                b.f.b.j.d dVar3 = this.f2246a;
                dVar3.f632f.f653e.d(dVar3.Q());
            }
            if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f2246a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                b.f.b.j.d dVar4 = this.f2246a;
                dVar4.i0(e(dVar4, 1));
                b.f.b.j.d dVar5 = this.f2246a;
                dVar5.f633g.f653e.d(dVar5.w());
            }
        }
        b.f.b.j.d dVar6 = this.f2246a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar6.P;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iQ = dVar6.Q() + iR;
            this.f2246a.f632f.f657i.d(iQ);
            this.f2246a.f632f.f653e.d(iQ - iR);
            m();
            b.f.b.j.d dVar7 = this.f2246a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar7.P;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar7.w() + iS;
                this.f2246a.f633g.f657i.d(iW);
                this.f2246a.f633g.f653e.d(iW - iS);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (WidgetRun widgetRun : this.f2250e) {
            if (widgetRun.f650b != this.f2246a || widgetRun.f655g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f2250e) {
            if (z2 || widgetRun2.f650b != this.f2246a) {
                if (!widgetRun2.f656h.j || ((!widgetRun2.f657i.j && !(widgetRun2 instanceof g)) || (!widgetRun2.f653e.j && !(widgetRun2 instanceof c) && !(widgetRun2 instanceof g)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f2246a.m0(dimensionBehaviourT);
        this.f2246a.B0(dimensionBehaviourT2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f2247b) {
            for (ConstraintWidget constraintWidget : this.f2246a.G0) {
                constraintWidget.f628b = false;
                i iVar = constraintWidget.f632f;
                iVar.f653e.j = false;
                iVar.f655g = false;
                iVar.r();
                k kVar = constraintWidget.f633g;
                kVar.f653e.j = false;
                kVar.f655g = false;
                kVar.q();
            }
            b.f.b.j.d dVar = this.f2246a;
            dVar.f628b = false;
            i iVar2 = dVar.f632f;
            iVar2.f653e.j = false;
            iVar2.f655g = false;
            iVar2.r();
            k kVar2 = this.f2246a.f633g;
            kVar2.f653e.j = false;
            kVar2.f655g = false;
            kVar2.q();
            c();
        }
        if (b(this.f2249d)) {
            return false;
        }
        this.f2246a.G0(0);
        this.f2246a.H0(0);
        this.f2246a.f632f.f656h.d(0);
        this.f2246a.f633g.f656h.d(0);
        return true;
    }

    public boolean h(boolean z, int i2) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f2246a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f2246a.t(1);
        int iR = this.f2246a.R();
        int iS = this.f2246a.S();
        if (z4 && (dimensionBehaviourT == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourT2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.f2250e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.f654f == i2 && !next.m()) {
                    z4 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z4 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f2246a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                    b.f.b.j.d dVar = this.f2246a;
                    dVar.F0(e(dVar, 0));
                    b.f.b.j.d dVar2 = this.f2246a;
                    dVar2.f632f.f653e.d(dVar2.Q());
                }
            } else if (z4 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f2246a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                b.f.b.j.d dVar3 = this.f2246a;
                dVar3.i0(e(dVar3, 1));
                b.f.b.j.d dVar4 = this.f2246a;
                dVar4.f633g.f653e.d(dVar4.w());
            }
        }
        if (i2 == 0) {
            b.f.b.j.d dVar5 = this.f2246a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar5.P;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iQ = dVar5.Q() + iR;
                this.f2246a.f632f.f657i.d(iQ);
                this.f2246a.f632f.f653e.d(iQ - iR);
                z2 = true;
            }
            z2 = false;
        } else {
            b.f.b.j.d dVar6 = this.f2246a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar6.P;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar6.w() + iS;
                this.f2246a.f633g.f657i.d(iW);
                this.f2246a.f633g.f653e.d(iW - iS);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (WidgetRun widgetRun : this.f2250e) {
            if (widgetRun.f654f == i2 && (widgetRun.f650b != this.f2246a || widgetRun.f655g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f2250e) {
            if (widgetRun2.f654f == i2 && (z2 || widgetRun2.f650b != this.f2246a)) {
                if (!widgetRun2.f656h.j || !widgetRun2.f657i.j || (!(widgetRun2 instanceof c) && !widgetRun2.f653e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f2246a.m0(dimensionBehaviourT);
        this.f2246a.B0(dimensionBehaviourT2);
        return z3;
    }

    public final void i(WidgetRun widgetRun, int i2, ArrayList<j> arrayList) {
        for (d dVar : widgetRun.f656h.k) {
            if (dVar instanceof DependencyNode) {
                a((DependencyNode) dVar, i2, 0, widgetRun.f657i, arrayList, null);
            } else if (dVar instanceof WidgetRun) {
                a(((WidgetRun) dVar).f656h, i2, 0, widgetRun.f657i, arrayList, null);
            }
        }
        for (d dVar2 : widgetRun.f657i.k) {
            if (dVar2 instanceof DependencyNode) {
                a((DependencyNode) dVar2, i2, 1, widgetRun.f656h, arrayList, null);
            } else if (dVar2 instanceof WidgetRun) {
                a(((WidgetRun) dVar2).f657i, i2, 1, widgetRun.f656h, arrayList, null);
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
        this.f2247b = true;
    }

    public void k() {
        this.f2248c = true;
    }

    public final void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        b.a aVar = this.f2253h;
        aVar.f2237a = dimensionBehaviour;
        aVar.f2238b = dimensionBehaviour2;
        aVar.f2239c = i2;
        aVar.f2240d = i3;
        this.f2252g.a(constraintWidget, aVar);
        constraintWidget.F0(this.f2253h.f2241e);
        constraintWidget.i0(this.f2253h.f2242f);
        constraintWidget.h0(this.f2253h.f2244h);
        constraintWidget.c0(this.f2253h.f2243g);
    }

    public void m() {
        f fVar;
        for (ConstraintWidget constraintWidget : this.f2246a.G0) {
            if (!constraintWidget.f628b) {
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
                f fVar2 = constraintWidget.f632f.f653e;
                boolean z3 = fVar2.j;
                f fVar3 = constraintWidget.f633g.f653e;
                boolean z4 = fVar3.j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, fVar2.f645g, dimensionBehaviour4, fVar3.f645g);
                    constraintWidget.f628b = true;
                } else if (z3 && z) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, fVar2.f645g, dimensionBehaviour3, fVar3.f645g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f633g.f653e.m = constraintWidget.w();
                    } else {
                        constraintWidget.f633g.f653e.d(constraintWidget.w());
                        constraintWidget.f628b = true;
                    }
                } else if (z4 && z2) {
                    l(constraintWidget, dimensionBehaviour3, fVar2.f645g, ConstraintWidget.DimensionBehaviour.FIXED, fVar3.f645g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f632f.f653e.m = constraintWidget.Q();
                    } else {
                        constraintWidget.f632f.f653e.d(constraintWidget.Q());
                        constraintWidget.f628b = true;
                    }
                }
                if (constraintWidget.f628b && (fVar = constraintWidget.f633g.l) != null) {
                    fVar.d(constraintWidget.o());
                }
            }
        }
    }

    public void n(b.InterfaceC0027b interfaceC0027b) {
        this.f2252g = interfaceC0027b;
    }
}
