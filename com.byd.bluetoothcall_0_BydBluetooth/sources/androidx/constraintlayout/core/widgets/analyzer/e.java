package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private androidx.constraintlayout.core.widgets.d f851a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private androidx.constraintlayout.core.widgets.d f854d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f852b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f853c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<WidgetRun> f855e = new ArrayList<>();
    private ArrayList<l> f = new ArrayList<>();
    private b.InterfaceC0016b g = null;
    private b.a h = new b.a();
    ArrayList<l> i = new ArrayList<>();

    public e(androidx.constraintlayout.core.widgets.d dVar) {
        this.f851a = dVar;
        this.f854d = dVar;
    }

    private void a(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<l> arrayList, l lVar) {
        WidgetRun widgetRun = dependencyNode.f833d;
        if (widgetRun.f838c == null) {
            androidx.constraintlayout.core.widgets.d dVar = this.f851a;
            if (widgetRun == dVar.f || widgetRun == dVar.g) {
                return;
            }
            if (lVar == null) {
                lVar = new l(widgetRun, i2);
                arrayList.add(lVar);
            }
            widgetRun.f838c = lVar;
            lVar.a(widgetRun);
            for (d dVar2 : widgetRun.h.k) {
                if (dVar2 instanceof DependencyNode) {
                    a((DependencyNode) dVar2, i, 0, dependencyNode2, arrayList, lVar);
                }
            }
            for (d dVar3 : widgetRun.i.k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i, 1, dependencyNode2, arrayList, lVar);
                }
            }
            if (i == 1 && (widgetRun instanceof m)) {
                for (d dVar4 : ((m) widgetRun).k.k) {
                    if (dVar4 instanceof DependencyNode) {
                        a((DependencyNode) dVar4, i, 2, dependencyNode2, arrayList, lVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.h.l) {
                if (dependencyNode3 == dependencyNode2) {
                    lVar.f862c = true;
                }
                a(dependencyNode3, i, 0, dependencyNode2, arrayList, lVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.i.l) {
                if (dependencyNode4 == dependencyNode2) {
                    lVar.f862c = true;
                }
                a(dependencyNode4, i, 1, dependencyNode2, arrayList, lVar);
            }
            if (i == 1 && (widgetRun instanceof m)) {
                Iterator<DependencyNode> it = ((m) widgetRun).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i, 2, dependencyNode2, arrayList, lVar);
                }
            }
        }
    }

    private boolean b(androidx.constraintlayout.core.widgets.d dVar) {
        int iY;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int iZ;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        for (ConstraintWidget constraintWidget : dVar.U0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.c0;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (constraintWidget.X() == 8) {
                constraintWidget.f823b = true;
            } else {
                if (constraintWidget.C < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.x = 2;
                }
                if (constraintWidget.F < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.y = 2;
                }
                if (constraintWidget.x() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.x = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.y = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (constraintWidget.x == 0) {
                            constraintWidget.x = 3;
                        }
                        if (constraintWidget.y == 0) {
                            constraintWidget.y = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.x == 1 && (constraintWidget.R.f == null || constraintWidget.T.f == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.y == 1 && (constraintWidget.S.f == null || constraintWidget.U.f == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                k kVar = constraintWidget.f;
                kVar.f839d = dimensionBehaviour9;
                int i = constraintWidget.x;
                kVar.f836a = i;
                m mVar = constraintWidget.g;
                mVar.f839d = dimensionBehaviour10;
                int i2 = constraintWidget.y;
                mVar.f836a = i2;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int iY2 = constraintWidget.Y();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        iY = (dVar.Y() - constraintWidget.R.g) - constraintWidget.T.g;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iY = iY2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int iZ2 = constraintWidget.z();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        iZ = (dVar.z() - constraintWidget.S.g) - constraintWidget.U.g;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        iZ = iZ2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, iY, dimensionBehaviour2, iZ);
                    constraintWidget.f.f840e.d(constraintWidget.Y());
                    constraintWidget.g.f840e.d(constraintWidget.z());
                    constraintWidget.f823b = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int iZ3 = constraintWidget.z();
                            int i3 = (int) ((iZ3 * constraintWidget.g0) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour12, i3, dimensionBehaviour12, iZ3);
                            constraintWidget.f.f840e.d(constraintWidget.Y());
                            constraintWidget.g.f840e.d(constraintWidget.z());
                            constraintWidget.f823b = true;
                        } else if (i == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f.f840e.m = constraintWidget.Y();
                        } else if (i == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar.c0;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.C * dVar.Y()) + 0.5f), dimensionBehaviour10, constraintWidget.z());
                                constraintWidget.f.f840e.d(constraintWidget.Y());
                                constraintWidget.g.f840e.d(constraintWidget.z());
                                constraintWidget.f823b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.Z;
                            if (constraintAnchorArr[0].f == null || constraintAnchorArr[1].f == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f.f840e.d(constraintWidget.Y());
                                constraintWidget.g.f840e.d(constraintWidget.z());
                                constraintWidget.f823b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i2 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int iY3 = constraintWidget.Y();
                            float f = constraintWidget.g0;
                            if (constraintWidget.y() == -1) {
                                f = 1.0f / f;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour15, iY3, dimensionBehaviour15, (int) ((iY3 * f) + 0.5f));
                            constraintWidget.f.f840e.d(constraintWidget.Y());
                            constraintWidget.g.f840e.d(constraintWidget.z());
                            constraintWidget.f823b = true;
                        } else if (i2 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.g.f840e.m = constraintWidget.z();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = dVar.c0;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Y(), dimensionBehaviour17, (int) ((constraintWidget.F * dVar.z()) + 0.5f));
                                constraintWidget.f.f840e.d(constraintWidget.Y());
                                constraintWidget.g.f840e.d(constraintWidget.z());
                                constraintWidget.f823b = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.Z;
                            if (constraintAnchorArr2[2].f == null || constraintAnchorArr2[3].f == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f.f840e.d(constraintWidget.Y());
                                constraintWidget.g.f840e.d(constraintWidget.z());
                                constraintWidget.f823b = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i == 1 || i2 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f.f840e.m = constraintWidget.Y();
                            constraintWidget.g.f840e.m = constraintWidget.z();
                        } else if (i2 == 2 && i == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = dVar.c0;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 && dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.C * dVar.Y()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.F * dVar.z()) + 0.5f));
                                constraintWidget.f.f840e.d(constraintWidget.Y());
                                constraintWidget.g.f840e.d(constraintWidget.z());
                                constraintWidget.f823b = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int e(androidx.constraintlayout.core.widgets.d dVar, int i) {
        int size = this.i.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jMax = Math.max(jMax, this.i.get(i2).b(dVar, i));
        }
        return (int) jMax;
    }

    private void i(WidgetRun widgetRun, int i, ArrayList<l> arrayList) {
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
            for (d dVar3 : ((m) widgetRun).k.k) {
                if (dVar3 instanceof DependencyNode) {
                    a((DependencyNode) dVar3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    private void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        b.a aVar = this.h;
        aVar.f849d = dimensionBehaviour;
        aVar.f850e = dimensionBehaviour2;
        aVar.f = i;
        aVar.g = i2;
        this.g.b(constraintWidget, aVar);
        constraintWidget.n1(this.h.h);
        constraintWidget.O0(this.h.i);
        constraintWidget.N0(this.h.k);
        constraintWidget.D0(this.h.j);
    }

    public void c() {
        d(this.f855e);
        this.i.clear();
        l.f860a = 0;
        i(this.f851a.f, 0, this.i);
        i(this.f851a.g, 1, this.i);
        this.f852b = false;
    }

    public void d(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.f854d.f.f();
        this.f854d.g.f();
        arrayList.add(this.f854d.f);
        arrayList.add(this.f854d.g);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.f854d.U0) {
            if (constraintWidget instanceof androidx.constraintlayout.core.widgets.f) {
                arrayList.add(new i(constraintWidget));
            } else {
                if (constraintWidget.k0()) {
                    if (constraintWidget.f825d == null) {
                        constraintWidget.f825d = new c(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f825d);
                } else {
                    arrayList.add(constraintWidget.f);
                }
                if (constraintWidget.m0()) {
                    if (constraintWidget.f826e == null) {
                        constraintWidget.f826e = new c(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f826e);
                } else {
                    arrayList.add(constraintWidget.g);
                }
                if (constraintWidget instanceof androidx.constraintlayout.core.widgets.h) {
                    arrayList.add(new j(constraintWidget));
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
            if (widgetRun.f837b != this.f854d) {
                widgetRun.d();
            }
        }
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f852b || this.f853c) {
            for (ConstraintWidget constraintWidget : this.f851a.U0) {
                constraintWidget.p();
                constraintWidget.f823b = false;
                constraintWidget.f.r();
                constraintWidget.g.q();
            }
            this.f851a.p();
            androidx.constraintlayout.core.widgets.d dVar = this.f851a;
            dVar.f823b = false;
            dVar.f.r();
            this.f851a.g.q();
            this.f853c = false;
        }
        if (b(this.f854d)) {
            return false;
        }
        this.f851a.p1(0);
        this.f851a.q1(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourW = this.f851a.w(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourW2 = this.f851a.w(1);
        if (this.f852b) {
            c();
        }
        int iZ = this.f851a.Z();
        int iA0 = this.f851a.a0();
        this.f851a.f.h.d(iZ);
        this.f851a.g.h.d(iA0);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviourW == dimensionBehaviour || dimensionBehaviourW2 == dimensionBehaviour) {
            if (z4) {
                Iterator<WidgetRun> it = this.f855e.iterator();
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
            if (z4 && dimensionBehaviourW == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f851a.S0(ConstraintWidget.DimensionBehaviour.FIXED);
                androidx.constraintlayout.core.widgets.d dVar2 = this.f851a;
                dVar2.n1(e(dVar2, 0));
                androidx.constraintlayout.core.widgets.d dVar3 = this.f851a;
                dVar3.f.f840e.d(dVar3.Y());
            }
            if (z4 && dimensionBehaviourW2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f851a.j1(ConstraintWidget.DimensionBehaviour.FIXED);
                androidx.constraintlayout.core.widgets.d dVar4 = this.f851a;
                dVar4.O0(e(dVar4, 1));
                androidx.constraintlayout.core.widgets.d dVar5 = this.f851a;
                dVar5.g.f840e.d(dVar5.z());
            }
        }
        androidx.constraintlayout.core.widgets.d dVar6 = this.f851a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar6.c0;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int iY = dVar6.Y() + iZ;
            this.f851a.f.i.d(iY);
            this.f851a.f.f840e.d(iY - iZ);
            m();
            androidx.constraintlayout.core.widgets.d dVar7 = this.f851a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar7.c0;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour3 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iZ2 = dVar7.z() + iA0;
                this.f851a.g.i.d(iZ2);
                this.f851a.g.f840e.d(iZ2 - iA0);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (WidgetRun widgetRun : this.f855e) {
            if (widgetRun.f837b != this.f851a || widgetRun.g) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f855e) {
            if (z2 || widgetRun2.f837b != this.f851a) {
                if (!widgetRun2.h.j || ((!widgetRun2.i.j && !(widgetRun2 instanceof i)) || (!widgetRun2.f840e.j && !(widgetRun2 instanceof c) && !(widgetRun2 instanceof i)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f851a.S0(dimensionBehaviourW);
        this.f851a.j1(dimensionBehaviourW2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f852b) {
            for (ConstraintWidget constraintWidget : this.f851a.U0) {
                constraintWidget.p();
                constraintWidget.f823b = false;
                k kVar = constraintWidget.f;
                kVar.f840e.j = false;
                kVar.g = false;
                kVar.r();
                m mVar = constraintWidget.g;
                mVar.f840e.j = false;
                mVar.g = false;
                mVar.q();
            }
            this.f851a.p();
            androidx.constraintlayout.core.widgets.d dVar = this.f851a;
            dVar.f823b = false;
            k kVar2 = dVar.f;
            kVar2.f840e.j = false;
            kVar2.g = false;
            kVar2.r();
            m mVar2 = this.f851a.g;
            mVar2.f840e.j = false;
            mVar2.g = false;
            mVar2.q();
            c();
        }
        if (b(this.f854d)) {
            return false;
        }
        this.f851a.p1(0);
        this.f851a.q1(0);
        this.f851a.f.h.d(0);
        this.f851a.g.h.d(0);
        return true;
    }

    public boolean h(boolean z, int i) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourW = this.f851a.w(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourW2 = this.f851a.w(1);
        int iZ = this.f851a.Z();
        int iA0 = this.f851a.a0();
        if (z4 && (dimensionBehaviourW == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviourW2 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.f855e.iterator();
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
                if (z4 && dimensionBehaviourW == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f851a.S0(ConstraintWidget.DimensionBehaviour.FIXED);
                    androidx.constraintlayout.core.widgets.d dVar = this.f851a;
                    dVar.n1(e(dVar, 0));
                    androidx.constraintlayout.core.widgets.d dVar2 = this.f851a;
                    dVar2.f.f840e.d(dVar2.Y());
                }
            } else if (z4 && dimensionBehaviourW2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f851a.j1(ConstraintWidget.DimensionBehaviour.FIXED);
                androidx.constraintlayout.core.widgets.d dVar3 = this.f851a;
                dVar3.O0(e(dVar3, 1));
                androidx.constraintlayout.core.widgets.d dVar4 = this.f851a;
                dVar4.g.f840e.d(dVar4.z());
            }
        }
        if (i == 0) {
            androidx.constraintlayout.core.widgets.d dVar5 = this.f851a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = dVar5.c0;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iY = dVar5.Y() + iZ;
                this.f851a.f.i.d(iY);
                this.f851a.f.f840e.d(iY - iZ);
                z2 = true;
            }
            z2 = false;
        } else {
            androidx.constraintlayout.core.widgets.d dVar6 = this.f851a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = dVar6.c0;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int iZ2 = dVar6.z() + iA0;
                this.f851a.g.i.d(iZ2);
                this.f851a.g.f840e.d(iZ2 - iA0);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (WidgetRun widgetRun : this.f855e) {
            if (widgetRun.f == i && (widgetRun.f837b != this.f851a || widgetRun.g)) {
                widgetRun.e();
            }
        }
        for (WidgetRun widgetRun2 : this.f855e) {
            if (widgetRun2.f == i && (z2 || widgetRun2.f837b != this.f851a)) {
                if (!widgetRun2.h.j || !widgetRun2.i.j || (!(widgetRun2 instanceof c) && !widgetRun2.f840e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f851a.S0(dimensionBehaviourW);
        this.f851a.j1(dimensionBehaviourW2);
        return z3;
    }

    public void j() {
        this.f852b = true;
    }

    public void k() {
        this.f853c = true;
    }

    public void m() {
        f fVar;
        for (ConstraintWidget constraintWidget : this.f851a.U0) {
            if (!constraintWidget.f823b) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.c0;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i = constraintWidget.x;
                int i2 = constraintWidget.y;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                f fVar2 = constraintWidget.f.f840e;
                boolean z3 = fVar2.j;
                f fVar3 = constraintWidget.g.f840e;
                boolean z4 = fVar3.j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, fVar2.g, dimensionBehaviour4, fVar3.g);
                    constraintWidget.f823b = true;
                } else if (z3 && z) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, fVar2.g, dimensionBehaviour3, fVar3.g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.g.f840e.m = constraintWidget.z();
                    } else {
                        constraintWidget.g.f840e.d(constraintWidget.z());
                        constraintWidget.f823b = true;
                    }
                } else if (z4 && z2) {
                    l(constraintWidget, dimensionBehaviour3, fVar2.g, ConstraintWidget.DimensionBehaviour.FIXED, fVar3.g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f.f840e.m = constraintWidget.Y();
                    } else {
                        constraintWidget.f.f840e.d(constraintWidget.Y());
                        constraintWidget.f823b = true;
                    }
                }
                if (constraintWidget.f823b && (fVar = constraintWidget.g.l) != null) {
                    fVar.d(constraintWidget.r());
                }
            }
        }
    }

    public void n(b.InterfaceC0016b interfaceC0016b) {
        this.g = interfaceC0016b;
    }
}
