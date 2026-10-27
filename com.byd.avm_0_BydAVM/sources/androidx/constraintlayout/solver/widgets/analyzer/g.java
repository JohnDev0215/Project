package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
class g extends WidgetRun {
    public g(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f.f();
        constraintWidget.g.f();
        this.f = ((androidx.constraintlayout.solver.widgets.f) constraintWidget).L0();
    }

    private void q(DependencyNode dependencyNode) {
        this.h.k.add(dependencyNode);
        dependencyNode.l.add(this.h);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.f665c && !dependencyNode.j) {
            this.h.d((int) ((dependencyNode.l.get(0).g * ((androidx.constraintlayout.solver.widgets.f) this.f669b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void d() {
        androidx.constraintlayout.solver.widgets.f fVar = (androidx.constraintlayout.solver.widgets.f) this.f669b;
        int iM0 = fVar.M0();
        int iN0 = fVar.N0();
        fVar.O0();
        if (fVar.L0() == 1) {
            if (iM0 != -1) {
                this.h.l.add(this.f669b.Q.f.h);
                this.f669b.Q.f.h.k.add(this.h);
                this.h.f = iM0;
            } else if (iN0 != -1) {
                this.h.l.add(this.f669b.Q.f.i);
                this.f669b.Q.f.i.k.add(this.h);
                this.h.f = -iN0;
            } else {
                DependencyNode dependencyNode = this.h;
                dependencyNode.f664b = true;
                dependencyNode.l.add(this.f669b.Q.f.i);
                this.f669b.Q.f.i.k.add(this.h);
            }
            q(this.f669b.f.h);
            q(this.f669b.f.i);
            return;
        }
        if (iM0 != -1) {
            this.h.l.add(this.f669b.Q.g.h);
            this.f669b.Q.g.h.k.add(this.h);
            this.h.f = iM0;
        } else if (iN0 != -1) {
            this.h.l.add(this.f669b.Q.g.i);
            this.f669b.Q.g.i.k.add(this.h);
            this.h.f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.h;
            dependencyNode2.f664b = true;
            dependencyNode2.l.add(this.f669b.Q.g.i);
            this.f669b.Q.g.i.k.add(this.h);
        }
        q(this.f669b.g.h);
        q(this.f669b.g.i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((androidx.constraintlayout.solver.widgets.f) this.f669b).L0() == 1) {
            this.f669b.G0(this.h.g);
        } else {
            this.f669b.H0(this.h.g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
