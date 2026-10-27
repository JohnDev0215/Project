package b.e.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends WidgetRun {
    public g(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f343f.f();
        constraintWidget.f344g.f();
        this.f365f = ((b.e.b.j.f) constraintWidget).L0();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.e.b.j.l.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.f367h;
        if (dependencyNode.f352c && !dependencyNode.j) {
            this.f367h.d((int) ((dependencyNode.l.get(0).f356g * ((b.e.b.j.f) this.f361b).O0()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        b.e.b.j.f fVar = (b.e.b.j.f) this.f361b;
        int iM0 = fVar.M0();
        int iN0 = fVar.N0();
        fVar.O0();
        if (fVar.L0() == 1) {
            if (iM0 != -1) {
                this.f367h.l.add(this.f361b.Q.f343f.f367h);
                this.f361b.Q.f343f.f367h.k.add(this.f367h);
                this.f367h.f355f = iM0;
            } else if (iN0 != -1) {
                this.f367h.l.add(this.f361b.Q.f343f.f368i);
                this.f361b.Q.f343f.f368i.k.add(this.f367h);
                this.f367h.f355f = -iN0;
            } else {
                DependencyNode dependencyNode = this.f367h;
                dependencyNode.f351b = true;
                dependencyNode.l.add(this.f361b.Q.f343f.f368i);
                this.f361b.Q.f343f.f368i.k.add(this.f367h);
            }
            q(this.f361b.f343f.f367h);
            q(this.f361b.f343f.f368i);
            return;
        }
        if (iM0 != -1) {
            this.f367h.l.add(this.f361b.Q.f344g.f367h);
            this.f361b.Q.f344g.f367h.k.add(this.f367h);
            this.f367h.f355f = iM0;
        } else if (iN0 != -1) {
            this.f367h.l.add(this.f361b.Q.f344g.f368i);
            this.f361b.Q.f344g.f368i.k.add(this.f367h);
            this.f367h.f355f = -iN0;
        } else {
            DependencyNode dependencyNode2 = this.f367h;
            dependencyNode2.f351b = true;
            dependencyNode2.l.add(this.f361b.Q.f344g.f368i);
            this.f361b.Q.f344g.f368i.k.add(this.f367h);
        }
        q(this.f361b.f344g.f367h);
        q(this.f361b.f344g.f368i);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        if (((b.e.b.j.f) this.f361b).L0() == 1) {
            this.f361b.G0(this.f367h.f356g);
        } else {
            this.f361b.H0(this.f367h.f356g);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f367h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        return false;
    }

    public final void q(DependencyNode dependencyNode) {
        this.f367h.k.add(dependencyNode);
        dependencyNode.l.add(this.f367h);
    }
}
