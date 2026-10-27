package b.f.b.j.l;

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

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.f.b.j.l.d
    public void a(d dVar) {
        b.f.b.j.a aVar = (b.f.b.j.a) this.f650b;
        int iM0 = aVar.M0();
        Iterator<DependencyNode> it = this.f656h.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = it.next().f645g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (iM0 == 0 || iM0 == 2) {
            this.f656h.d(i3 + aVar.N0());
        } else {
            this.f656h.d(i2 + aVar.N0());
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidget = this.f650b;
        if (constraintWidget instanceof b.f.b.j.a) {
            this.f656h.f640b = true;
            b.f.b.j.a aVar = (b.f.b.j.a) constraintWidget;
            int iM0 = aVar.M0();
            boolean zL0 = aVar.L0();
            int i2 = 0;
            if (iM0 == 0) {
                this.f656h.f643e = DependencyNode.Type.LEFT;
                while (i2 < aVar.H0) {
                    ConstraintWidget constraintWidget2 = aVar.G0[i2];
                    if (zL0 || constraintWidget2.P() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f632f.f656h;
                        dependencyNode.k.add(this.f656h);
                        this.f656h.l.add(dependencyNode);
                    }
                    i2++;
                }
                q(this.f650b.f632f.f656h);
                q(this.f650b.f632f.f657i);
                return;
            }
            if (iM0 == 1) {
                this.f656h.f643e = DependencyNode.Type.RIGHT;
                while (i2 < aVar.H0) {
                    ConstraintWidget constraintWidget3 = aVar.G0[i2];
                    if (zL0 || constraintWidget3.P() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f632f.f657i;
                        dependencyNode2.k.add(this.f656h);
                        this.f656h.l.add(dependencyNode2);
                    }
                    i2++;
                }
                q(this.f650b.f632f.f656h);
                q(this.f650b.f632f.f657i);
                return;
            }
            if (iM0 == 2) {
                this.f656h.f643e = DependencyNode.Type.TOP;
                while (i2 < aVar.H0) {
                    ConstraintWidget constraintWidget4 = aVar.G0[i2];
                    if (zL0 || constraintWidget4.P() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.f633g.f656h;
                        dependencyNode3.k.add(this.f656h);
                        this.f656h.l.add(dependencyNode3);
                    }
                    i2++;
                }
                q(this.f650b.f633g.f656h);
                q(this.f650b.f633g.f657i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.f656h.f643e = DependencyNode.Type.BOTTOM;
            while (i2 < aVar.H0) {
                ConstraintWidget constraintWidget5 = aVar.G0[i2];
                if (zL0 || constraintWidget5.P() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.f633g.f657i;
                    dependencyNode4.k.add(this.f656h);
                    this.f656h.l.add(dependencyNode4);
                }
                i2++;
            }
            q(this.f650b.f633g.f656h);
            q(this.f650b.f633g.f657i);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f650b;
        if (constraintWidget instanceof b.f.b.j.a) {
            int iM0 = ((b.f.b.j.a) constraintWidget).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.f650b.G0(this.f656h.f645g);
            } else {
                this.f650b.H0(this.f656h.f645g);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f651c = null;
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
