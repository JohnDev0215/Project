package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public class k6 extends WidgetRun {
    public k6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f.f();
        constraintWidget.g.f();
        this.f = ((y5) constraintWidget).L0();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, defpackage.h6
    public void a(h6 h6Var) {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.c && !dependencyNode.j) {
            this.h.d((int) ((dependencyNode.l.get(0).g * ((y5) this.b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        y5 y5Var = (y5) this.b;
        int iM0 = y5Var.M0();
        int iN0 = y5Var.N0();
        y5Var.O0();
        if (y5Var.L0() == 1) {
            if (iM0 != -1) {
                this.h.l.add(this.b.Q.f.h);
                this.b.Q.f.h.k.add(this.h);
                this.h.f = iM0;
            } else if (iN0 != -1) {
                this.h.l.add(this.b.Q.f.i);
                this.b.Q.f.i.k.add(this.h);
                this.h.f = -iN0;
            } else {
                DependencyNode dependencyNode = this.h;
                dependencyNode.b = true;
                dependencyNode.l.add(this.b.Q.f.i);
                this.b.Q.f.i.k.add(this.h);
            }
            q(this.b.f.h);
            q(this.b.f.i);
            return;
        }
        if (iM0 != -1) {
            this.h.l.add(this.b.Q.g.h);
            this.b.Q.g.h.k.add(this.h);
            this.h.f = iM0;
        } else if (iN0 != -1) {
            this.h.l.add(this.b.Q.g.i);
            this.b.Q.g.i.k.add(this.h);
            this.h.f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.h;
            dependencyNode2.b = true;
            dependencyNode2.l.add(this.b.Q.g.i);
            this.b.Q.g.i.k.add(this.h);
        }
        q(this.b.g.h);
        q(this.b.g.i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((y5) this.b).L0() == 1) {
            this.b.G0(this.h.g);
        } else {
            this.b.H0(this.h.g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return false;
    }

    public final void q(DependencyNode dependencyNode) {
        this.h.k.add(dependencyNode);
        dependencyNode.l.add(this.h);
    }
}
