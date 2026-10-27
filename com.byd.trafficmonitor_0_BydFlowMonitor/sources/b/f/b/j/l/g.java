package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends WidgetRun {
    public g(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f632f.f();
        constraintWidget.f633g.f();
        this.f654f = ((b.f.b.j.f) constraintWidget).L0();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.f.b.j.l.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.f656h;
        if (dependencyNode.f641c && !dependencyNode.j) {
            this.f656h.d((int) ((dependencyNode.l.get(0).f645g * ((b.f.b.j.f) this.f650b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        b.f.b.j.f fVar = (b.f.b.j.f) this.f650b;
        int iM0 = fVar.M0();
        int iN0 = fVar.N0();
        fVar.O0();
        if (fVar.L0() == 1) {
            if (iM0 != -1) {
                this.f656h.l.add(this.f650b.Q.f632f.f656h);
                this.f650b.Q.f632f.f656h.k.add(this.f656h);
                this.f656h.f644f = iM0;
            } else if (iN0 != -1) {
                this.f656h.l.add(this.f650b.Q.f632f.f657i);
                this.f650b.Q.f632f.f657i.k.add(this.f656h);
                this.f656h.f644f = -iN0;
            } else {
                DependencyNode dependencyNode = this.f656h;
                dependencyNode.f640b = true;
                dependencyNode.l.add(this.f650b.Q.f632f.f657i);
                this.f650b.Q.f632f.f657i.k.add(this.f656h);
            }
            q(this.f650b.f632f.f656h);
            q(this.f650b.f632f.f657i);
            return;
        }
        if (iM0 != -1) {
            this.f656h.l.add(this.f650b.Q.f633g.f656h);
            this.f650b.Q.f633g.f656h.k.add(this.f656h);
            this.f656h.f644f = iM0;
        } else if (iN0 != -1) {
            this.f656h.l.add(this.f650b.Q.f633g.f657i);
            this.f650b.Q.f633g.f657i.k.add(this.f656h);
            this.f656h.f644f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.f656h;
            dependencyNode2.f640b = true;
            dependencyNode2.l.add(this.f650b.Q.f633g.f657i);
            this.f650b.Q.f633g.f657i.k.add(this.f656h);
        }
        q(this.f650b.f633g.f656h);
        q(this.f650b.f633g.f657i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((b.f.b.j.f) this.f650b).L0() == 1) {
            this.f650b.G0(this.f656h.f645g);
        } else {
            this.f650b.H0(this.f656h.f645g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f656h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return false;
    }

    public final void q(DependencyNode dependencyNode) {
        this.f656h.k.add(dependencyNode);
        dependencyNode.l.add(this.f656h);
    }
}
