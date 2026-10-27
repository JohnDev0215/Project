package a0;

import a0.b;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z.d f17a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public z.d f20d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f19c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<WidgetRun> f21e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<j> f22f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b.InterfaceC0000b f23g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b.a f24h = new b.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<j> f25i = new ArrayList<>();

    public e(z.d dVar) {
        this.f17a = dVar;
        this.f20d = dVar;
    }

    public final void a(DependencyNode dependencyNode, int i8, int i9, DependencyNode dependencyNode2, ArrayList<j> arrayList, j jVar) {
        WidgetRun widgetRun = dependencyNode.f981d;
        if (widgetRun.f992c == null) {
            z.d dVar = this.f17a;
            if (widgetRun == dVar.f934f || widgetRun == dVar.f936g) {
                return;
            }
            if (jVar == null) {
                jVar = new j(widgetRun, i9);
                arrayList.add(jVar);
            }
            widgetRun.f992c = jVar;
            jVar.a(widgetRun);
            for (d dVar2 : widgetRun.f997h.f988k) {
                if (dVar2 instanceof DependencyNode) {
                    a((DependencyNode) dVar2, i8, 0, dependencyNode2, arrayList, jVar);
                }
            }
            for (d dVar3 : widgetRun.f998i.f988k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i8, 1, dependencyNode2, arrayList, jVar);
                }
            }
            if (i8 == 1 && (widgetRun instanceof k)) {
                for (d dVar4 : ((k) widgetRun).f37k.f988k) {
                    if (dVar4 instanceof DependencyNode) {
                        a((DependencyNode) dVar4, i8, 2, dependencyNode2, arrayList, jVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.f997h.f989l) {
                if (dependencyNode3 == dependencyNode2) {
                    jVar.f31c = true;
                }
                a(dependencyNode3, i8, 0, dependencyNode2, arrayList, jVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.f998i.f989l) {
                if (dependencyNode4 == dependencyNode2) {
                    jVar.f31c = true;
                }
                a(dependencyNode4, i8, 1, dependencyNode2, arrayList, jVar);
            }
            if (i8 == 1 && (widgetRun instanceof k)) {
                Iterator<DependencyNode> it = ((k) widgetRun).f37k.f989l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i8, 2, dependencyNode2, arrayList, jVar);
                }
            }
        }
    }

    public final boolean b(z.d dVar) {
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
                constraintWidget.f926b = true;
            } else {
                if (constraintWidget.f958r < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.f948m = 2;
                }
                if (constraintWidget.f964u < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.f950n = 2;
                }
                if (constraintWidget.u() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.f948m = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.f950n = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (constraintWidget.f948m == 0) {
                            constraintWidget.f948m = 3;
                        }
                        if (constraintWidget.f950n == 0) {
                            constraintWidget.f950n = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.f948m == 1 && (constraintWidget.E.f919d == null || constraintWidget.G.f919d == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.f950n == 1 && (constraintWidget.F.f919d == null || constraintWidget.H.f919d == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                i iVar = constraintWidget.f934f;
                iVar.f993d = dimensionBehaviour9;
                int i8 = constraintWidget.f948m;
                iVar.f990a = i8;
                k kVar = constraintWidget.f936g;
                kVar.f993d = dimensionBehaviour10;
                int i9 = constraintWidget.f950n;
                kVar.f990a = i9;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iQ2 = constraintWidget.Q();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iQ = (dVar.Q() - constraintWidget.E.f920e) - constraintWidget.G.f920e;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iQ = iQ2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iW2 = constraintWidget.w();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iW = (dVar.w() - constraintWidget.F.f920e) - constraintWidget.H.f920e;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iW = iW2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iQ, dimensionBehaviour2, iW);
                    constraintWidget.f934f.f994e.d(constraintWidget.Q());
                    constraintWidget.f936g.f994e.d(constraintWidget.w());
                    constraintWidget.f926b = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i8 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int iW3 = constraintWidget.w();
                            int i10 = (int) ((iW3 * constraintWidget.T) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour12, i10, dimensionBehaviour12, iW3);
                            constraintWidget.f934f.f994e.d(constraintWidget.Q());
                            constraintWidget.f936g.f994e.d(constraintWidget.w());
                            constraintWidget.f926b = true;
                        } else if (i8 == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f934f.f994e.f26m = constraintWidget.Q();
                        } else if (i8 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.f958r * dVar.Q()) + 0.5f), dimensionBehaviour10, constraintWidget.w());
                                constraintWidget.f934f.f994e.d(constraintWidget.Q());
                                constraintWidget.f936g.f994e.d(constraintWidget.w());
                                constraintWidget.f926b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.M;
                            if (constraintAnchorArr[0].f919d == null || constraintAnchorArr[1].f919d == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f934f.f994e.d(constraintWidget.Q());
                                constraintWidget.f936g.f994e.d(constraintWidget.w());
                                constraintWidget.f926b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i9 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int iQ3 = constraintWidget.Q();
                            float f8 = constraintWidget.T;
                            if (constraintWidget.v() == -1) {
                                f8 = 1.0f / f8;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour15, iQ3, dimensionBehaviour15, (int) ((iQ3 * f8) + 0.5f));
                            constraintWidget.f934f.f994e.d(constraintWidget.Q());
                            constraintWidget.f936g.f994e.d(constraintWidget.w());
                            constraintWidget.f926b = true;
                        } else if (i9 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.f936g.f994e.f26m = constraintWidget.w();
                        } else if (i9 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Q(), dimensionBehaviour17, (int) ((constraintWidget.f964u * dVar.w()) + 0.5f));
                                constraintWidget.f934f.f994e.d(constraintWidget.Q());
                                constraintWidget.f936g.f994e.d(constraintWidget.w());
                                constraintWidget.f926b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.M;
                            if (constraintAnchorArr2[2].f919d == null || constraintAnchorArr2[3].f919d == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f934f.f994e.d(constraintWidget.Q());
                                constraintWidget.f936g.f994e.d(constraintWidget.w());
                                constraintWidget.f926b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i8 == 1 || i9 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f934f.f994e.f26m = constraintWidget.Q();
                            constraintWidget.f936g.f994e.f26m = constraintWidget.w();
                        } else if (i9 == 2 && i8 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.f958r * dVar.Q()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.f964u * dVar.w()) + 0.5f));
                                    constraintWidget.f934f.f994e.d(constraintWidget.Q());
                                    constraintWidget.f936g.f994e.d(constraintWidget.w());
                                    constraintWidget.f926b = true;
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
        d(this.f21e);
        this.f25i.clear();
        j.f29a = 0;
        i(this.f17a.f934f, 0, this.f25i);
        i(this.f17a.f936g, 1, this.f25i);
        this.f18b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.f20d.f934f.f();
        this.f20d.f936g.f();
        arrayList.add(this.f20d.f934f);
        arrayList.add(this.f20d.f936g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.f20d.G0) {
            if (constraintWidget instanceof z.f) {
                arrayList.add(new g(constraintWidget));
            } else {
                if (constraintWidget.W()) {
                    if (constraintWidget.f930d == null) {
                        constraintWidget.f930d = new c(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f930d);
                } else {
                    arrayList.add(constraintWidget.f934f);
                }
                if (constraintWidget.Y()) {
                    if (constraintWidget.f932e == null) {
                        constraintWidget.f932e = new c(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f932e);
                } else {
                    arrayList.add(constraintWidget.f936g);
                }
                if (constraintWidget instanceof z.h) {
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
            if (widgetRun.f991b != this.f20d) {
                widgetRun.d();
            }
        }
    }

    public final int e(z.d dVar, int i8) {
        int size = this.f25i.size();
        long jMax = 0;
        for (int i9 = 0; i9 < size; i9++) {
            jMax = Math.max(jMax, this.f25i.get(i9).b(dVar, i8));
        }
        return (int) jMax;
    }

    public boolean f(boolean z7) {
        boolean z8;
        boolean z9 = true;
        boolean z10 = z7 & true;
        if (this.f18b || this.f19c) {
            for (ConstraintWidget constraintWidget : this.f17a.G0) {
                constraintWidget.f926b = false;
                constraintWidget.f934f.r();
                constraintWidget.f936g.q();
            }
            z.d dVar = this.f17a;
            dVar.f926b = false;
            dVar.f934f.r();
            this.f17a.f936g.q();
            this.f19c = false;
        }
        if (b(this.f20d)) {
            return false;
        }
        this.f17a.G0(0);
        this.f17a.H0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f17a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f17a.t(1);
        if (this.f18b) {
            c();
        }
        int iR = this.f17a.R();
        int iS = this.f17a.S();
        this.f17a.f934f.f997h.d(iR);
        this.f17a.f936g.f997h.d(iS);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourT == dimensionBehaviour || dimensionBehaviourT2 == dimensionBehaviour) {
            if (z10) {
                Iterator<WidgetRun> it = this.f21e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().m()) {
                        z10 = false;
                        break;
                    }
                }
            }
            if (z10 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f17a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                z.d dVar2 = this.f17a;
                dVar2.F0(e(dVar2, 0));
                z.d dVar3 = this.f17a;
                dVar3.f934f.f994e.d(dVar3.Q());
            }
            if (z10 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f17a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                z.d dVar4 = this.f17a;
                dVar4.i0(e(dVar4, 1));
                z.d dVar5 = this.f17a;
                dVar5.f936g.f994e.d(dVar5.w());
            }
        }
        z.d dVar6 = this.f17a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar6.P;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iQ = dVar6.Q() + iR;
            this.f17a.f934f.f998i.d(iQ);
            this.f17a.f934f.f994e.d(iQ - iR);
            m();
            z.d dVar7 = this.f17a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar7.P;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar7.w() + iS;
                this.f17a.f936g.f998i.d(iW);
                this.f17a.f936g.f994e.d(iW - iS);
            }
            m();
            z8 = true;
        } else {
            z8 = false;
        }
        for (WidgetRun widgetRun : this.f21e) {
            if (widgetRun.f991b != this.f17a || widgetRun.f996g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f21e) {
            if (z8 || widgetRun2.f991b != this.f17a) {
                if (!widgetRun2.f997h.f987j || ((!widgetRun2.f998i.f987j && !(widgetRun2 instanceof g)) || (!widgetRun2.f994e.f987j && !(widgetRun2 instanceof c) && !(widgetRun2 instanceof g)))) {
                    z9 = false;
                    break;
                }
            }
        }
        this.f17a.m0(dimensionBehaviourT);
        this.f17a.B0(dimensionBehaviourT2);
        return z9;
    }

    public boolean g(boolean z7) {
        if (this.f18b) {
            for (ConstraintWidget constraintWidget : this.f17a.G0) {
                constraintWidget.f926b = false;
                i iVar = constraintWidget.f934f;
                iVar.f994e.f987j = false;
                iVar.f996g = false;
                iVar.r();
                k kVar = constraintWidget.f936g;
                kVar.f994e.f987j = false;
                kVar.f996g = false;
                kVar.q();
            }
            z.d dVar = this.f17a;
            dVar.f926b = false;
            i iVar2 = dVar.f934f;
            iVar2.f994e.f987j = false;
            iVar2.f996g = false;
            iVar2.r();
            k kVar2 = this.f17a.f936g;
            kVar2.f994e.f987j = false;
            kVar2.f996g = false;
            kVar2.q();
            c();
        }
        if (b(this.f20d)) {
            return false;
        }
        this.f17a.G0(0);
        this.f17a.H0(0);
        this.f17a.f934f.f997h.d(0);
        this.f17a.f936g.f997h.d(0);
        return true;
    }

    public boolean h(boolean z7, int i8) {
        boolean z8;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z9 = true;
        boolean z10 = z7 & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f17a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f17a.t(1);
        int iR = this.f17a.R();
        int iS = this.f17a.S();
        if (z10 && (dimensionBehaviourT == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourT2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.f21e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.f995f == i8 && !next.m()) {
                    z10 = false;
                    break;
                }
            }
            if (i8 == 0) {
                if (z10 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f17a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                    z.d dVar = this.f17a;
                    dVar.F0(e(dVar, 0));
                    z.d dVar2 = this.f17a;
                    dVar2.f934f.f994e.d(dVar2.Q());
                }
            } else if (z10 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f17a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                z.d dVar3 = this.f17a;
                dVar3.i0(e(dVar3, 1));
                z.d dVar4 = this.f17a;
                dVar4.f936g.f994e.d(dVar4.w());
            }
        }
        if (i8 == 0) {
            z.d dVar5 = this.f17a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar5.P;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iQ = dVar5.Q() + iR;
                this.f17a.f934f.f998i.d(iQ);
                this.f17a.f934f.f994e.d(iQ - iR);
                z8 = true;
            }
            z8 = false;
        } else {
            z.d dVar6 = this.f17a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar6.P;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar6.w() + iS;
                this.f17a.f936g.f998i.d(iW);
                this.f17a.f936g.f994e.d(iW - iS);
                z8 = true;
            }
            z8 = false;
        }
        m();
        for (WidgetRun widgetRun : this.f21e) {
            if (widgetRun.f995f == i8 && (widgetRun.f991b != this.f17a || widgetRun.f996g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f21e) {
            if (widgetRun2.f995f == i8 && (z8 || widgetRun2.f991b != this.f17a)) {
                if (!widgetRun2.f997h.f987j || !widgetRun2.f998i.f987j || (!(widgetRun2 instanceof c) && !widgetRun2.f994e.f987j)) {
                    z9 = false;
                    break;
                }
            }
        }
        this.f17a.m0(dimensionBehaviourT);
        this.f17a.B0(dimensionBehaviourT2);
        return z9;
    }

    public final void i(WidgetRun widgetRun, int i8, ArrayList<j> arrayList) {
        for (d dVar : widgetRun.f997h.f988k) {
            if (dVar instanceof DependencyNode) {
                a((DependencyNode) dVar, i8, 0, widgetRun.f998i, arrayList, null);
            } else if (dVar instanceof WidgetRun) {
                a(((WidgetRun) dVar).f997h, i8, 0, widgetRun.f998i, arrayList, null);
            }
        }
        for (d dVar2 : widgetRun.f998i.f988k) {
            if (dVar2 instanceof DependencyNode) {
                a((DependencyNode) dVar2, i8, 1, widgetRun.f997h, arrayList, null);
            } else if (dVar2 instanceof WidgetRun) {
                a(((WidgetRun) dVar2).f998i, i8, 1, widgetRun.f997h, arrayList, null);
            }
        }
        if (i8 == 1) {
            for (d dVar3 : ((k) widgetRun).f37k.f988k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i8, 2, null, arrayList, null);
                }
            }
        }
    }

    public void j() {
        this.f18b = true;
    }

    public void k() {
        this.f19c = true;
    }

    public final void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i8, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i9) {
        b.a aVar = this.f24h;
        aVar.f5a = dimensionBehaviour;
        aVar.f6b = dimensionBehaviour2;
        aVar.f7c = i8;
        aVar.f8d = i9;
        this.f23g.a(constraintWidget, aVar);
        constraintWidget.F0(this.f24h.f9e);
        constraintWidget.i0(this.f24h.f10f);
        constraintWidget.h0(this.f24h.f12h);
        constraintWidget.c0(this.f24h.f11g);
    }

    public void m() {
        f fVar;
        for (ConstraintWidget constraintWidget : this.f17a.G0) {
            if (!constraintWidget.f926b) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                boolean z7 = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i8 = constraintWidget.f948m;
                int i9 = constraintWidget.f950n;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z8 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i8 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i9 == 1)) {
                    z7 = true;
                }
                f fVar2 = constraintWidget.f934f.f994e;
                boolean z9 = fVar2.f987j;
                f fVar3 = constraintWidget.f936g.f994e;
                boolean z10 = fVar3.f987j;
                if (z9 && z10) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, fVar2.f984g, dimensionBehaviour4, fVar3.f984g);
                    constraintWidget.f926b = true;
                } else if (z9 && z7) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, fVar2.f984g, dimensionBehaviour3, fVar3.f984g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f936g.f994e.f26m = constraintWidget.w();
                    } else {
                        constraintWidget.f936g.f994e.d(constraintWidget.w());
                        constraintWidget.f926b = true;
                    }
                } else if (z10 && z8) {
                    l(constraintWidget, dimensionBehaviour3, fVar2.f984g, ConstraintWidget.DimensionBehaviour.FIXED, fVar3.f984g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f934f.f994e.f26m = constraintWidget.Q();
                    } else {
                        constraintWidget.f934f.f994e.d(constraintWidget.Q());
                        constraintWidget.f926b = true;
                    }
                }
                if (constraintWidget.f926b && (fVar = constraintWidget.f936g.f38l) != null) {
                    fVar.d(constraintWidget.o());
                }
            }
        }
    }

    public void n(b.InterfaceC0000b interfaceC0000b) {
        this.f23g = interfaceC0000b;
    }
}
