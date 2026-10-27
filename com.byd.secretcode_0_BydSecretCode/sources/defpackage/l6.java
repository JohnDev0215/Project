package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.Iterator;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
public class l6 extends WidgetRun {
    public l6(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, defpackage.h6
    public void a(h6 h6Var) {
        t5 t5Var = (t5) this.b;
        int iM0 = t5Var.M0();
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
            this.h.d(i2 + t5Var.N0());
        } else {
            this.h.d(i + t5Var.N0());
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget instanceof t5) {
            this.h.b = true;
            t5 t5Var = (t5) constraintWidget;
            int iM0 = t5Var.M0();
            boolean zL0 = t5Var.L0();
            int i = 0;
            if (iM0 == 0) {
                this.h.e = DependencyNode.Type.LEFT;
                while (i < t5Var.H0) {
                    ConstraintWidget constraintWidget2 = t5Var.G0[i];
                    if (zL0 || constraintWidget2.P() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f.h;
                        dependencyNode.k.add(this.h);
                        this.h.l.add(dependencyNode);
                    }
                    i++;
                }
                q(this.b.f.h);
                q(this.b.f.i);
                return;
            }
            if (iM0 == 1) {
                this.h.e = DependencyNode.Type.RIGHT;
                while (i < t5Var.H0) {
                    ConstraintWidget constraintWidget3 = t5Var.G0[i];
                    if (zL0 || constraintWidget3.P() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f.i;
                        dependencyNode2.k.add(this.h);
                        this.h.l.add(dependencyNode2);
                    }
                    i++;
                }
                q(this.b.f.h);
                q(this.b.f.i);
                return;
            }
            if (iM0 == 2) {
                this.h.e = DependencyNode.Type.TOP;
                while (i < t5Var.H0) {
                    ConstraintWidget constraintWidget4 = t5Var.G0[i];
                    if (zL0 || constraintWidget4.P() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.g.h;
                        dependencyNode3.k.add(this.h);
                        this.h.l.add(dependencyNode3);
                    }
                    i++;
                }
                q(this.b.g.h);
                q(this.b.g.i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.h.e = DependencyNode.Type.BOTTOM;
            while (i < t5Var.H0) {
                ConstraintWidget constraintWidget5 = t5Var.G0[i];
                if (zL0 || constraintWidget5.P() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.g.i;
                    dependencyNode4.k.add(this.h);
                    this.h.l.add(dependencyNode4);
                }
                i++;
            }
            q(this.b.g.h);
            q(this.b.g.i);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.b;
        if (constraintWidget instanceof t5) {
            int iM0 = ((t5) constraintWidget).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.b.G0(this.h.g);
            } else {
                this.b.H0(this.h.g);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.c = null;
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
