package x;

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

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, x.d
    public void a(d dVar) {
        w.a aVar = (w.a) this.f900b;
        int iM0 = aVar.M0();
        Iterator<DependencyNode> it = this.f906h.f898l.iterator();
        int i3 = 0;
        int i4 = -1;
        while (it.hasNext()) {
            int i5 = it.next().f893g;
            if (i4 == -1 || i5 < i4) {
                i4 = i5;
            }
            if (i3 < i5) {
                i3 = i5;
            }
        }
        if (iM0 == 0 || iM0 == 2) {
            this.f906h.d(i4 + aVar.N0());
        } else {
            this.f906h.d(i3 + aVar.N0());
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidget = this.f900b;
        if (constraintWidget instanceof w.a) {
            this.f906h.f888b = true;
            w.a aVar = (w.a) constraintWidget;
            int iM0 = aVar.M0();
            boolean zL0 = aVar.L0();
            int i3 = 0;
            if (iM0 == 0) {
                this.f906h.f891e = DependencyNode.Type.LEFT;
                while (i3 < aVar.H0) {
                    ConstraintWidget constraintWidget2 = aVar.G0[i3];
                    if (zL0 || constraintWidget2.P() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f843f.f906h;
                        dependencyNode.f897k.add(this.f906h);
                        this.f906h.f898l.add(dependencyNode);
                    }
                    i3++;
                }
                q(this.f900b.f843f.f906h);
                q(this.f900b.f843f.f907i);
                return;
            }
            if (iM0 == 1) {
                this.f906h.f891e = DependencyNode.Type.RIGHT;
                while (i3 < aVar.H0) {
                    ConstraintWidget constraintWidget3 = aVar.G0[i3];
                    if (zL0 || constraintWidget3.P() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f843f.f907i;
                        dependencyNode2.f897k.add(this.f906h);
                        this.f906h.f898l.add(dependencyNode2);
                    }
                    i3++;
                }
                q(this.f900b.f843f.f906h);
                q(this.f900b.f843f.f907i);
                return;
            }
            if (iM0 == 2) {
                this.f906h.f891e = DependencyNode.Type.TOP;
                while (i3 < aVar.H0) {
                    ConstraintWidget constraintWidget4 = aVar.G0[i3];
                    if (zL0 || constraintWidget4.P() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.f845g.f906h;
                        dependencyNode3.f897k.add(this.f906h);
                        this.f906h.f898l.add(dependencyNode3);
                    }
                    i3++;
                }
                q(this.f900b.f845g.f906h);
                q(this.f900b.f845g.f907i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.f906h.f891e = DependencyNode.Type.BOTTOM;
            while (i3 < aVar.H0) {
                ConstraintWidget constraintWidget5 = aVar.G0[i3];
                if (zL0 || constraintWidget5.P() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.f845g.f907i;
                    dependencyNode4.f897k.add(this.f906h);
                    this.f906h.f898l.add(dependencyNode4);
                }
                i3++;
            }
            q(this.f900b.f845g.f906h);
            q(this.f900b.f845g.f907i);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f900b;
        if (constraintWidget instanceof w.a) {
            int iM0 = ((w.a) constraintWidget).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.f900b.G0(this.f906h.f893g);
            } else {
                this.f900b.H0(this.f906h.f893g);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f901c = null;
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
