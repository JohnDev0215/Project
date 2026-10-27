package x;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import x.b;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w.d f5696a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w.d f5699d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5697b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5698c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<WidgetRun> f5700e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<j> f5701f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b.InterfaceC0072b f5702g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b.a f5703h = new b.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<j> f5704i = new ArrayList<>();

    public e(w.d dVar) {
        this.f5696a = dVar;
        this.f5699d = dVar;
    }

    public final void a(DependencyNode dependencyNode, int i3, int i4, DependencyNode dependencyNode2, ArrayList<j> arrayList, j jVar) {
        WidgetRun widgetRun = dependencyNode.f890d;
        if (widgetRun.f901c == null) {
            w.d dVar = this.f5696a;
            if (widgetRun == dVar.f843f || widgetRun == dVar.f845g) {
                return;
            }
            if (jVar == null) {
                jVar = new j(widgetRun, i4);
                arrayList.add(jVar);
            }
            widgetRun.f901c = jVar;
            jVar.a(widgetRun);
            for (d dVar2 : widgetRun.f906h.f897k) {
                if (dVar2 instanceof DependencyNode) {
                    a((DependencyNode) dVar2, i3, 0, dependencyNode2, arrayList, jVar);
                }
            }
            for (d dVar3 : widgetRun.f907i.f897k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i3, 1, dependencyNode2, arrayList, jVar);
                }
            }
            if (i3 == 1 && (widgetRun instanceof k)) {
                for (d dVar4 : ((k) widgetRun).f5716k.f897k) {
                    if (dVar4 instanceof DependencyNode) {
                        a((DependencyNode) dVar4, i3, 2, dependencyNode2, arrayList, jVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.f906h.f898l) {
                if (dependencyNode3 == dependencyNode2) {
                    jVar.f5710c = true;
                }
                a(dependencyNode3, i3, 0, dependencyNode2, arrayList, jVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.f907i.f898l) {
                if (dependencyNode4 == dependencyNode2) {
                    jVar.f5710c = true;
                }
                a(dependencyNode4, i3, 1, dependencyNode2, arrayList, jVar);
            }
            if (i3 == 1 && (widgetRun instanceof k)) {
                Iterator<DependencyNode> it = ((k) widgetRun).f5716k.f898l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i3, 2, dependencyNode2, arrayList, jVar);
                }
            }
        }
    }

    public final boolean b(w.d dVar) {
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
                constraintWidget.f835b = true;
            } else {
                if (constraintWidget.f867r < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.f857m = 2;
                }
                if (constraintWidget.f873u < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.f859n = 2;
                }
                if (constraintWidget.u() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.f857m = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.f859n = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (constraintWidget.f857m == 0) {
                            constraintWidget.f857m = 3;
                        }
                        if (constraintWidget.f859n == 0) {
                            constraintWidget.f859n = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.f857m == 1 && (constraintWidget.E.f828d == null || constraintWidget.G.f828d == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.f859n == 1 && (constraintWidget.F.f828d == null || constraintWidget.H.f828d == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                i iVar = constraintWidget.f843f;
                iVar.f902d = dimensionBehaviour9;
                int i3 = constraintWidget.f857m;
                iVar.f899a = i3;
                k kVar = constraintWidget.f845g;
                kVar.f902d = dimensionBehaviour10;
                int i4 = constraintWidget.f859n;
                kVar.f899a = i4;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iQ2 = constraintWidget.Q();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iQ = (dVar.Q() - constraintWidget.E.f829e) - constraintWidget.G.f829e;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iQ = iQ2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iW2 = constraintWidget.w();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iW = (dVar.w() - constraintWidget.F.f829e) - constraintWidget.H.f829e;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iW = iW2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iQ, dimensionBehaviour2, iW);
                    constraintWidget.f843f.f903e.d(constraintWidget.Q());
                    constraintWidget.f845g.f903e.d(constraintWidget.w());
                    constraintWidget.f835b = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i3 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int iW3 = constraintWidget.w();
                            int i5 = (int) ((iW3 * constraintWidget.T) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour12, i5, dimensionBehaviour12, iW3);
                            constraintWidget.f843f.f903e.d(constraintWidget.Q());
                            constraintWidget.f845g.f903e.d(constraintWidget.w());
                            constraintWidget.f835b = true;
                        } else if (i3 == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f843f.f903e.f5705m = constraintWidget.Q();
                        } else if (i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.f867r * dVar.Q()) + 0.5f), dimensionBehaviour10, constraintWidget.w());
                                constraintWidget.f843f.f903e.d(constraintWidget.Q());
                                constraintWidget.f845g.f903e.d(constraintWidget.w());
                                constraintWidget.f835b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.M;
                            if (constraintAnchorArr[0].f828d == null || constraintAnchorArr[1].f828d == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f843f.f903e.d(constraintWidget.Q());
                                constraintWidget.f845g.f903e.d(constraintWidget.w());
                                constraintWidget.f835b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i4 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int iQ3 = constraintWidget.Q();
                            float f3 = constraintWidget.T;
                            if (constraintWidget.v() == -1) {
                                f3 = 1.0f / f3;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour15, iQ3, dimensionBehaviour15, (int) ((iQ3 * f3) + 0.5f));
                            constraintWidget.f843f.f903e.d(constraintWidget.Q());
                            constraintWidget.f845g.f903e.d(constraintWidget.w());
                            constraintWidget.f835b = true;
                        } else if (i4 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.f845g.f903e.f5705m = constraintWidget.w();
                        } else if (i4 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Q(), dimensionBehaviour17, (int) ((constraintWidget.f873u * dVar.w()) + 0.5f));
                                constraintWidget.f843f.f903e.d(constraintWidget.Q());
                                constraintWidget.f845g.f903e.d(constraintWidget.w());
                                constraintWidget.f835b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.M;
                            if (constraintAnchorArr2[2].f828d == null || constraintAnchorArr2[3].f828d == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f843f.f903e.d(constraintWidget.Q());
                                constraintWidget.f845g.f903e.d(constraintWidget.w());
                                constraintWidget.f835b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i3 == 1 || i4 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f843f.f903e.f5705m = constraintWidget.Q();
                            constraintWidget.f845g.f903e.f5705m = constraintWidget.w();
                        } else if (i4 == 2 && i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = dVar.P;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.f867r * dVar.Q()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.f873u * dVar.w()) + 0.5f));
                                    constraintWidget.f843f.f903e.d(constraintWidget.Q());
                                    constraintWidget.f845g.f903e.d(constraintWidget.w());
                                    constraintWidget.f835b = true;
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
        d(this.f5700e);
        this.f5704i.clear();
        j.f5708a = 0;
        i(this.f5696a.f843f, 0, this.f5704i);
        i(this.f5696a.f845g, 1, this.f5704i);
        this.f5697b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.f5699d.f843f.f();
        this.f5699d.f845g.f();
        arrayList.add(this.f5699d.f843f);
        arrayList.add(this.f5699d.f845g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.f5699d.G0) {
            if (constraintWidget instanceof w.f) {
                arrayList.add(new g(constraintWidget));
            } else {
                if (constraintWidget.W()) {
                    if (constraintWidget.f839d == null) {
                        constraintWidget.f839d = new c(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f839d);
                } else {
                    arrayList.add(constraintWidget.f843f);
                }
                if (constraintWidget.Y()) {
                    if (constraintWidget.f841e == null) {
                        constraintWidget.f841e = new c(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f841e);
                } else {
                    arrayList.add(constraintWidget.f845g);
                }
                if (constraintWidget instanceof w.h) {
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
            if (widgetRun.f900b != this.f5699d) {
                widgetRun.d();
            }
        }
    }

    public final int e(w.d dVar, int i3) {
        int size = this.f5704i.size();
        long jMax = 0;
        for (int i4 = 0; i4 < size; i4++) {
            jMax = Math.max(jMax, this.f5704i.get(i4).b(dVar, i3));
        }
        return (int) jMax;
    }

    public boolean f(boolean z3) {
        boolean z4;
        boolean z5 = true;
        boolean z6 = z3 & true;
        if (this.f5697b || this.f5698c) {
            for (ConstraintWidget constraintWidget : this.f5696a.G0) {
                constraintWidget.f835b = false;
                constraintWidget.f843f.r();
                constraintWidget.f845g.q();
            }
            w.d dVar = this.f5696a;
            dVar.f835b = false;
            dVar.f843f.r();
            this.f5696a.f845g.q();
            this.f5698c = false;
        }
        if (b(this.f5699d)) {
            return false;
        }
        this.f5696a.G0(0);
        this.f5696a.H0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f5696a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f5696a.t(1);
        if (this.f5697b) {
            c();
        }
        int iR = this.f5696a.R();
        int iS = this.f5696a.S();
        this.f5696a.f843f.f906h.d(iR);
        this.f5696a.f845g.f906h.d(iS);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourT == dimensionBehaviour || dimensionBehaviourT2 == dimensionBehaviour) {
            if (z6) {
                Iterator<WidgetRun> it = this.f5700e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().m()) {
                        z6 = false;
                        break;
                    }
                }
            }
            if (z6 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f5696a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                w.d dVar2 = this.f5696a;
                dVar2.F0(e(dVar2, 0));
                w.d dVar3 = this.f5696a;
                dVar3.f843f.f903e.d(dVar3.Q());
            }
            if (z6 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f5696a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                w.d dVar4 = this.f5696a;
                dVar4.i0(e(dVar4, 1));
                w.d dVar5 = this.f5696a;
                dVar5.f845g.f903e.d(dVar5.w());
            }
        }
        w.d dVar6 = this.f5696a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar6.P;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iQ = dVar6.Q() + iR;
            this.f5696a.f843f.f907i.d(iQ);
            this.f5696a.f843f.f903e.d(iQ - iR);
            m();
            w.d dVar7 = this.f5696a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar7.P;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar7.w() + iS;
                this.f5696a.f845g.f907i.d(iW);
                this.f5696a.f845g.f903e.d(iW - iS);
            }
            m();
            z4 = true;
        } else {
            z4 = false;
        }
        for (WidgetRun widgetRun : this.f5700e) {
            if (widgetRun.f900b != this.f5696a || widgetRun.f905g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f5700e) {
            if (z4 || widgetRun2.f900b != this.f5696a) {
                if (!widgetRun2.f906h.f896j || ((!widgetRun2.f907i.f896j && !(widgetRun2 instanceof g)) || (!widgetRun2.f903e.f896j && !(widgetRun2 instanceof c) && !(widgetRun2 instanceof g)))) {
                    z5 = false;
                    break;
                }
            }
        }
        this.f5696a.m0(dimensionBehaviourT);
        this.f5696a.B0(dimensionBehaviourT2);
        return z5;
    }

    public boolean g(boolean z3) {
        if (this.f5697b) {
            for (ConstraintWidget constraintWidget : this.f5696a.G0) {
                constraintWidget.f835b = false;
                i iVar = constraintWidget.f843f;
                iVar.f903e.f896j = false;
                iVar.f905g = false;
                iVar.r();
                k kVar = constraintWidget.f845g;
                kVar.f903e.f896j = false;
                kVar.f905g = false;
                kVar.q();
            }
            w.d dVar = this.f5696a;
            dVar.f835b = false;
            i iVar2 = dVar.f843f;
            iVar2.f903e.f896j = false;
            iVar2.f905g = false;
            iVar2.r();
            k kVar2 = this.f5696a.f845g;
            kVar2.f903e.f896j = false;
            kVar2.f905g = false;
            kVar2.q();
            c();
        }
        if (b(this.f5699d)) {
            return false;
        }
        this.f5696a.G0(0);
        this.f5696a.H0(0);
        this.f5696a.f843f.f906h.d(0);
        this.f5696a.f845g.f906h.d(0);
        return true;
    }

    public boolean h(boolean z3, int i3) {
        boolean z4;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z5 = true;
        boolean z6 = z3 & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT = this.f5696a.t(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = this.f5696a.t(1);
        int iR = this.f5696a.R();
        int iS = this.f5696a.S();
        if (z6 && (dimensionBehaviourT == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourT2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.f5700e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.f904f == i3 && !next.m()) {
                    z6 = false;
                    break;
                }
            }
            if (i3 == 0) {
                if (z6 && dimensionBehaviourT == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f5696a.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                    w.d dVar = this.f5696a;
                    dVar.F0(e(dVar, 0));
                    w.d dVar2 = this.f5696a;
                    dVar2.f843f.f903e.d(dVar2.Q());
                }
            } else if (z6 && dimensionBehaviourT2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f5696a.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                w.d dVar3 = this.f5696a;
                dVar3.i0(e(dVar3, 1));
                w.d dVar4 = this.f5696a;
                dVar4.f845g.f903e.d(dVar4.w());
            }
        }
        if (i3 == 0) {
            w.d dVar5 = this.f5696a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar5.P;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iQ = dVar5.Q() + iR;
                this.f5696a.f843f.f907i.d(iQ);
                this.f5696a.f843f.f903e.d(iQ - iR);
                z4 = true;
            }
            z4 = false;
        } else {
            w.d dVar6 = this.f5696a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar6.P;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iW = dVar6.w() + iS;
                this.f5696a.f845g.f907i.d(iW);
                this.f5696a.f845g.f903e.d(iW - iS);
                z4 = true;
            }
            z4 = false;
        }
        m();
        for (WidgetRun widgetRun : this.f5700e) {
            if (widgetRun.f904f == i3 && (widgetRun.f900b != this.f5696a || widgetRun.f905g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f5700e) {
            if (widgetRun2.f904f == i3 && (z4 || widgetRun2.f900b != this.f5696a)) {
                if (!widgetRun2.f906h.f896j || !widgetRun2.f907i.f896j || (!(widgetRun2 instanceof c) && !widgetRun2.f903e.f896j)) {
                    z5 = false;
                    break;
                }
            }
        }
        this.f5696a.m0(dimensionBehaviourT);
        this.f5696a.B0(dimensionBehaviourT2);
        return z5;
    }

    public final void i(WidgetRun widgetRun, int i3, ArrayList<j> arrayList) {
        for (d dVar : widgetRun.f906h.f897k) {
            if (dVar instanceof DependencyNode) {
                a((DependencyNode) dVar, i3, 0, widgetRun.f907i, arrayList, null);
            } else if (dVar instanceof WidgetRun) {
                a(((WidgetRun) dVar).f906h, i3, 0, widgetRun.f907i, arrayList, null);
            }
        }
        for (d dVar2 : widgetRun.f907i.f897k) {
            if (dVar2 instanceof DependencyNode) {
                a((DependencyNode) dVar2, i3, 1, widgetRun.f906h, arrayList, null);
            } else if (dVar2 instanceof WidgetRun) {
                a(((WidgetRun) dVar2).f907i, i3, 1, widgetRun.f906h, arrayList, null);
            }
        }
        if (i3 == 1) {
            for (d dVar3 : ((k) widgetRun).f5716k.f897k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i3, 2, null, arrayList, null);
                }
            }
        }
    }

    public void j() {
        this.f5697b = true;
    }

    public void k() {
        this.f5698c = true;
    }

    public final void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i3, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i4) {
        b.a aVar = this.f5703h;
        aVar.f5684a = dimensionBehaviour;
        aVar.f5685b = dimensionBehaviour2;
        aVar.f5686c = i3;
        aVar.f5687d = i4;
        this.f5702g.a(constraintWidget, aVar);
        constraintWidget.F0(this.f5703h.f5688e);
        constraintWidget.i0(this.f5703h.f5689f);
        constraintWidget.h0(this.f5703h.f5691h);
        constraintWidget.c0(this.f5703h.f5690g);
    }

    public void m() {
        f fVar;
        for (ConstraintWidget constraintWidget : this.f5696a.G0) {
            if (!constraintWidget.f835b) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                boolean z3 = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i3 = constraintWidget.f857m;
                int i4 = constraintWidget.f859n;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z4 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i3 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i4 == 1)) {
                    z3 = true;
                }
                f fVar2 = constraintWidget.f843f.f903e;
                boolean z5 = fVar2.f896j;
                f fVar3 = constraintWidget.f845g.f903e;
                boolean z6 = fVar3.f896j;
                if (z5 && z6) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, fVar2.f893g, dimensionBehaviour4, fVar3.f893g);
                    constraintWidget.f835b = true;
                } else if (z5 && z3) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, fVar2.f893g, dimensionBehaviour3, fVar3.f893g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f845g.f903e.f5705m = constraintWidget.w();
                    } else {
                        constraintWidget.f845g.f903e.d(constraintWidget.w());
                        constraintWidget.f835b = true;
                    }
                } else if (z6 && z4) {
                    l(constraintWidget, dimensionBehaviour3, fVar2.f893g, ConstraintWidget.DimensionBehaviour.FIXED, fVar3.f893g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f843f.f903e.f5705m = constraintWidget.Q();
                    } else {
                        constraintWidget.f843f.f903e.d(constraintWidget.Q());
                        constraintWidget.f835b = true;
                    }
                }
                if (constraintWidget.f835b && (fVar = constraintWidget.f845g.f5717l) != null) {
                    fVar.d(constraintWidget.o());
                }
            }
        }
    }

    public void n(b.InterfaceC0072b interfaceC0072b) {
        this.f5702g = interfaceC0072b;
    }
}
