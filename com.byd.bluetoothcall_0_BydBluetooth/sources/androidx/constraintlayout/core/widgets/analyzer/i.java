package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
class i extends WidgetRun {
    public i(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f.f();
        constraintWidget.g.f();
        this.f = ((androidx.constraintlayout.core.widgets.f) constraintWidget).v1();
    }

    private void q(DependencyNode dependencyNode) {
        this.h.k.add(dependencyNode);
        dependencyNode.l.add(this.h);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.d
    public void a(d dVar) {
        DependencyNode dependencyNode = this.h;
        if (dependencyNode.f832c && !dependencyNode.j) {
            this.h.d((int) ((dependencyNode.l.get(0).g * ((androidx.constraintlayout.core.widgets.f) this.f837b).y1()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        androidx.constraintlayout.core.widgets.f fVar = (androidx.constraintlayout.core.widgets.f) this.f837b;
        int iW1 = fVar.w1();
        int iX1 = fVar.x1();
        fVar.y1();
        if (fVar.v1() == 1) {
            if (iW1 != -1) {
                this.h.l.add(this.f837b.d0.f.h);
                this.f837b.d0.f.h.k.add(this.h);
                this.h.f = iW1;
            } else if (iX1 != -1) {
                this.h.l.add(this.f837b.d0.f.i);
                this.f837b.d0.f.i.k.add(this.h);
                this.h.f = -iX1;
            } else {
                DependencyNode dependencyNode = this.h;
                dependencyNode.f831b = true;
                dependencyNode.l.add(this.f837b.d0.f.i);
                this.f837b.d0.f.i.k.add(this.h);
            }
            q(this.f837b.f.h);
            q(this.f837b.f.i);
            return;
        }
        if (iW1 != -1) {
            this.h.l.add(this.f837b.d0.g.h);
            this.f837b.d0.g.h.k.add(this.h);
            this.h.f = iW1;
        } else if (iX1 != -1) {
            this.h.l.add(this.f837b.d0.g.i);
            this.f837b.d0.g.i.k.add(this.h);
            this.h.f = -iX1;
        } else {
            DependencyNode dependencyNode2 = this.h;
            dependencyNode2.f831b = true;
            dependencyNode2.l.add(this.f837b.d0.g.i);
            this.f837b.d0.g.i.k.add(this.h);
        }
        q(this.f837b.g.h);
        q(this.f837b.g.i);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        if (((androidx.constraintlayout.core.widgets.f) this.f837b).v1() == 1) {
            this.f837b.p1(this.h.g);
        } else {
            this.f837b.q1(this.h.g);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.h.c();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
