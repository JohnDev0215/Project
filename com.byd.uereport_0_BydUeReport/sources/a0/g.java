package a0;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends WidgetRun {
    public g(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f934f.f();
        constraintWidget.f936g.f();
        this.f995f = ((z.f) constraintWidget).L0();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, a0.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.f997h;
        if (dependencyNode.f980c && !dependencyNode.f987j) {
            this.f997h.d((int) ((dependencyNode.f989l.get(0).f984g * ((z.f) this.f991b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        z.f fVar = (z.f) this.f991b;
        int iM0 = fVar.M0();
        int iN0 = fVar.N0();
        fVar.O0();
        if (fVar.L0() == 1) {
            if (iM0 != -1) {
                this.f997h.f989l.add(this.f991b.Q.f934f.f997h);
                this.f991b.Q.f934f.f997h.f988k.add(this.f997h);
                this.f997h.f983f = iM0;
            } else if (iN0 != -1) {
                this.f997h.f989l.add(this.f991b.Q.f934f.f998i);
                this.f991b.Q.f934f.f998i.f988k.add(this.f997h);
                this.f997h.f983f = -iN0;
            } else {
                DependencyNode dependencyNode = this.f997h;
                dependencyNode.f979b = true;
                dependencyNode.f989l.add(this.f991b.Q.f934f.f998i);
                this.f991b.Q.f934f.f998i.f988k.add(this.f997h);
            }
            q(this.f991b.f934f.f997h);
            q(this.f991b.f934f.f998i);
            return;
        }
        if (iM0 != -1) {
            this.f997h.f989l.add(this.f991b.Q.f936g.f997h);
            this.f991b.Q.f936g.f997h.f988k.add(this.f997h);
            this.f997h.f983f = iM0;
        } else if (iN0 != -1) {
            this.f997h.f989l.add(this.f991b.Q.f936g.f998i);
            this.f991b.Q.f936g.f998i.f988k.add(this.f997h);
            this.f997h.f983f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.f997h;
            dependencyNode2.f979b = true;
            dependencyNode2.f989l.add(this.f991b.Q.f936g.f998i);
            this.f991b.Q.f936g.f998i.f988k.add(this.f997h);
        }
        q(this.f991b.f936g.f997h);
        q(this.f991b.f936g.f998i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((z.f) this.f991b).L0() == 1) {
            this.f991b.G0(this.f997h.f984g);
        } else {
            this.f991b.H0(this.f997h.f984g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f997h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return false;
    }

    public final void q(DependencyNode dependencyNode) {
        this.f997h.f988k.add(dependencyNode);
        dependencyNode.f989l.add(this.f997h);
    }
}
