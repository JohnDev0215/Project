package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
class g extends WidgetRun {
    public g(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f971f.f();
        constraintWidget.f972g.f();
        this.f991f = ((androidx.constraintlayout.solver.widgets.f) constraintWidget).L0();
    }

    private void q(DependencyNode dependencyNode) {
        this.f993h.k.add(dependencyNode);
        dependencyNode.l.add(this.f993h);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.f993h;
        if (dependencyNode.f979c && !dependencyNode.j) {
            this.f993h.d((int) ((dependencyNode.l.get(0).f983g * ((androidx.constraintlayout.solver.widgets.f) this.f987b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void d() {
        androidx.constraintlayout.solver.widgets.f fVar = (androidx.constraintlayout.solver.widgets.f) this.f987b;
        int iM0 = fVar.M0();
        int iN0 = fVar.N0();
        fVar.O0();
        if (fVar.L0() == 1) {
            if (iM0 != -1) {
                this.f993h.l.add(this.f987b.Q.f971f.f993h);
                this.f987b.Q.f971f.f993h.k.add(this.f993h);
                this.f993h.f982f = iM0;
            } else if (iN0 != -1) {
                this.f993h.l.add(this.f987b.Q.f971f.i);
                this.f987b.Q.f971f.i.k.add(this.f993h);
                this.f993h.f982f = -iN0;
            } else {
                DependencyNode dependencyNode = this.f993h;
                dependencyNode.f978b = true;
                dependencyNode.l.add(this.f987b.Q.f971f.i);
                this.f987b.Q.f971f.i.k.add(this.f993h);
            }
            q(this.f987b.f971f.f993h);
            q(this.f987b.f971f.i);
            return;
        }
        if (iM0 != -1) {
            this.f993h.l.add(this.f987b.Q.f972g.f993h);
            this.f987b.Q.f972g.f993h.k.add(this.f993h);
            this.f993h.f982f = iM0;
        } else if (iN0 != -1) {
            this.f993h.l.add(this.f987b.Q.f972g.i);
            this.f987b.Q.f972g.i.k.add(this.f993h);
            this.f993h.f982f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.f993h;
            dependencyNode2.f978b = true;
            dependencyNode2.l.add(this.f987b.Q.f972g.i);
            this.f987b.Q.f972g.i.k.add(this.f993h);
        }
        q(this.f987b.f972g.f993h);
        q(this.f987b.f972g.i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((androidx.constraintlayout.solver.widgets.f) this.f987b).L0() == 1) {
            this.f987b.G0(this.f993h.f983g);
        } else {
            this.f987b.H0(this.f993h.f983g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.f993h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
