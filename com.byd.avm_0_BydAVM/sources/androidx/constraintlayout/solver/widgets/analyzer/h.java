package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import java.util.Iterator;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
class h extends WidgetRun {
    public h(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private void q(DependencyNode dependencyNode) {
        this.h.k.add(dependencyNode);
        dependencyNode.l.add(this.h);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
        androidx.constraintlayout.solver.widgets.a aVar = (androidx.constraintlayout.solver.widgets.a) this.f669b;
        int iM0 = aVar.M0();
        Iterator<DependencyNode> it = this.h.l.iterator();
        int i = 0;
        int i2 = -1;
        while (it.hasNext()) {
            int i3 = it.next().g;
            if (i2 == -1 || i3 < i2) {
                i2 = i3;
            }
            if (i < i3) {
                i = i3;
            }
        }
        if (iM0 == 0 || iM0 == 2) {
            this.h.d(i2 + aVar.N0());
        } else {
            this.h.d(i + aVar.N0());
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidget = this.f669b;
        if (constraintWidget instanceof androidx.constraintlayout.solver.widgets.a) {
            this.h.f664b = true;
            androidx.constraintlayout.solver.widgets.a aVar = (androidx.constraintlayout.solver.widgets.a) constraintWidget;
            int iM0 = aVar.M0();
            boolean zL0 = aVar.L0();
            int i = 0;
            if (iM0 == 0) {
                this.h.e = DependencyNode.Type.LEFT;
                while (i < aVar.H0) {
                    ConstraintWidget constraintWidget2 = aVar.G0[i];
                    if (zL0 || constraintWidget2.P() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f.h;
                        dependencyNode.k.add(this.h);
                        this.h.l.add(dependencyNode);
                    }
                    i++;
                }
                q(this.f669b.f.h);
                q(this.f669b.f.i);
                return;
            }
            if (iM0 == 1) {
                this.h.e = DependencyNode.Type.RIGHT;
                while (i < aVar.H0) {
                    ConstraintWidget constraintWidget3 = aVar.G0[i];
                    if (zL0 || constraintWidget3.P() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f.i;
                        dependencyNode2.k.add(this.h);
                        this.h.l.add(dependencyNode2);
                    }
                    i++;
                }
                q(this.f669b.f.h);
                q(this.f669b.f.i);
                return;
            }
            if (iM0 == 2) {
                this.h.e = DependencyNode.Type.TOP;
                while (i < aVar.H0) {
                    ConstraintWidget constraintWidget4 = aVar.G0[i];
                    if (zL0 || constraintWidget4.P() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.g.h;
                        dependencyNode3.k.add(this.h);
                        this.h.l.add(dependencyNode3);
                    }
                    i++;
                }
                q(this.f669b.g.h);
                q(this.f669b.g.i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.h.e = DependencyNode.Type.BOTTOM;
            while (i < aVar.H0) {
                ConstraintWidget constraintWidget5 = aVar.G0[i];
                if (zL0 || constraintWidget5.P() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.g.i;
                    dependencyNode4.k.add(this.h);
                    this.h.l.add(dependencyNode4);
                }
                i++;
            }
            q(this.f669b.g.h);
            q(this.f669b.g.i);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f669b;
        if (constraintWidget instanceof androidx.constraintlayout.solver.widgets.a) {
            int iM0 = ((androidx.constraintlayout.solver.widgets.a) constraintWidget).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.f669b.G0(this.h.g);
            } else {
                this.f669b.H0(this.h.g);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void f() {
        this.f670c = null;
        this.h.c();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
