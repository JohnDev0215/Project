package a0;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.Iterator;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends WidgetRun {
    public h(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, a0.d
    public void a(d dVar) {
        z.a aVar = (z.a) this.f991b;
        int iM0 = aVar.M0();
        Iterator<DependencyNode> it = this.f997h.f989l.iterator();
        int i8 = 0;
        int i9 = -1;
        while (it.hasNext()) {
            int i10 = it.next().f984g;
            if (i9 == -1 || i10 < i9) {
                i9 = i10;
            }
            if (i8 < i10) {
                i8 = i10;
            }
        }
        if (iM0 == 0 || iM0 == 2) {
            this.f997h.d(i9 + aVar.N0());
        } else {
            this.f997h.d(i8 + aVar.N0());
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidget = this.f991b;
        if (constraintWidget instanceof z.a) {
            this.f997h.f979b = true;
            z.a aVar = (z.a) constraintWidget;
            int iM0 = aVar.M0();
            boolean zL0 = aVar.L0();
            int i8 = 0;
            if (iM0 == 0) {
                this.f997h.f982e = DependencyNode.Type.LEFT;
                while (i8 < aVar.H0) {
                    ConstraintWidget constraintWidget2 = aVar.G0[i8];
                    if (zL0 || constraintWidget2.P() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f934f.f997h;
                        dependencyNode.f988k.add(this.f997h);
                        this.f997h.f989l.add(dependencyNode);
                    }
                    i8++;
                }
                q(this.f991b.f934f.f997h);
                q(this.f991b.f934f.f998i);
                return;
            }
            if (iM0 == 1) {
                this.f997h.f982e = DependencyNode.Type.RIGHT;
                while (i8 < aVar.H0) {
                    ConstraintWidget constraintWidget3 = aVar.G0[i8];
                    if (zL0 || constraintWidget3.P() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f934f.f998i;
                        dependencyNode2.f988k.add(this.f997h);
                        this.f997h.f989l.add(dependencyNode2);
                    }
                    i8++;
                }
                q(this.f991b.f934f.f997h);
                q(this.f991b.f934f.f998i);
                return;
            }
            if (iM0 == 2) {
                this.f997h.f982e = DependencyNode.Type.TOP;
                while (i8 < aVar.H0) {
                    ConstraintWidget constraintWidget4 = aVar.G0[i8];
                    if (zL0 || constraintWidget4.P() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.f936g.f997h;
                        dependencyNode3.f988k.add(this.f997h);
                        this.f997h.f989l.add(dependencyNode3);
                    }
                    i8++;
                }
                q(this.f991b.f936g.f997h);
                q(this.f991b.f936g.f998i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.f997h.f982e = DependencyNode.Type.BOTTOM;
            while (i8 < aVar.H0) {
                ConstraintWidget constraintWidget5 = aVar.G0[i8];
                if (zL0 || constraintWidget5.P() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.f936g.f998i;
                    dependencyNode4.f988k.add(this.f997h);
                    this.f997h.f989l.add(dependencyNode4);
                }
                i8++;
            }
            q(this.f991b.f936g.f997h);
            q(this.f991b.f936g.f998i);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f991b;
        if (constraintWidget instanceof z.a) {
            int iM0 = ((z.a) constraintWidget).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.f991b.G0(this.f997h.f984g);
            } else {
                this.f991b.H0(this.f997h.f984g);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f992c = null;
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
