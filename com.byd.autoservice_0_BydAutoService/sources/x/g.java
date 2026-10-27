package x;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends WidgetRun {
    public g(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f843f.f();
        constraintWidget.f845g.f();
        this.f904f = ((w.f) constraintWidget).L0();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, x.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.f906h;
        if (dependencyNode.f889c && !dependencyNode.f896j) {
            this.f906h.d((int) ((dependencyNode.f898l.get(0).f893g * ((w.f) this.f900b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        w.f fVar = (w.f) this.f900b;
        int iM0 = fVar.M0();
        int iN0 = fVar.N0();
        fVar.O0();
        if (fVar.L0() == 1) {
            if (iM0 != -1) {
                this.f906h.f898l.add(this.f900b.Q.f843f.f906h);
                this.f900b.Q.f843f.f906h.f897k.add(this.f906h);
                this.f906h.f892f = iM0;
            } else if (iN0 != -1) {
                this.f906h.f898l.add(this.f900b.Q.f843f.f907i);
                this.f900b.Q.f843f.f907i.f897k.add(this.f906h);
                this.f906h.f892f = -iN0;
            } else {
                DependencyNode dependencyNode = this.f906h;
                dependencyNode.f888b = true;
                dependencyNode.f898l.add(this.f900b.Q.f843f.f907i);
                this.f900b.Q.f843f.f907i.f897k.add(this.f906h);
            }
            q(this.f900b.f843f.f906h);
            q(this.f900b.f843f.f907i);
            return;
        }
        if (iM0 != -1) {
            this.f906h.f898l.add(this.f900b.Q.f845g.f906h);
            this.f900b.Q.f845g.f906h.f897k.add(this.f906h);
            this.f906h.f892f = iM0;
        } else if (iN0 != -1) {
            this.f906h.f898l.add(this.f900b.Q.f845g.f907i);
            this.f900b.Q.f845g.f907i.f897k.add(this.f906h);
            this.f906h.f892f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.f906h;
            dependencyNode2.f888b = true;
            dependencyNode2.f898l.add(this.f900b.Q.f845g.f907i);
            this.f900b.Q.f845g.f907i.f897k.add(this.f906h);
        }
        q(this.f900b.f845g.f906h);
        q(this.f900b.f845g.f907i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((w.f) this.f900b).L0() == 1) {
            this.f900b.G0(this.f906h.f893g);
        } else {
            this.f900b.H0(this.f906h.f893g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f906h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return false;
    }

    public final void q(DependencyNode dependencyNode) {
        this.f906h.f897k.add(dependencyNode);
        dependencyNode.f898l.add(this.f906h);
    }
}
