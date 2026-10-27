package b.e.b.j.l;

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

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.e.b.j.l.d
    public void a(d dVar) {
        b.e.b.j.a aVar = (b.e.b.j.a) this.f361b;
        int iM0 = aVar.M0();
        Iterator<DependencyNode> it = this.f367h.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = it.next().f356g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (iM0 == 0 || iM0 == 2) {
            this.f367h.d(i3 + aVar.N0());
        } else {
            this.f367h.d(i2 + aVar.N0());
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        ConstraintWidget constraintWidget = this.f361b;
        if (constraintWidget instanceof b.e.b.j.a) {
            this.f367h.f351b = true;
            b.e.b.j.a aVar = (b.e.b.j.a) constraintWidget;
            int iM0 = aVar.M0();
            boolean zL0 = aVar.L0();
            int i2 = 0;
            if (iM0 == 0) {
                this.f367h.f354e = DependencyNode.Type.LEFT;
                while (i2 < aVar.H0) {
                    ConstraintWidget constraintWidget2 = aVar.G0[i2];
                    if (zL0 || constraintWidget2.P() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f343f.f367h;
                        dependencyNode.k.add(this.f367h);
                        this.f367h.l.add(dependencyNode);
                    }
                    i2++;
                }
                q(this.f361b.f343f.f367h);
                q(this.f361b.f343f.f368i);
                return;
            }
            if (iM0 == 1) {
                this.f367h.f354e = DependencyNode.Type.RIGHT;
                while (i2 < aVar.H0) {
                    ConstraintWidget constraintWidget3 = aVar.G0[i2];
                    if (zL0 || constraintWidget3.P() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f343f.f368i;
                        dependencyNode2.k.add(this.f367h);
                        this.f367h.l.add(dependencyNode2);
                    }
                    i2++;
                }
                q(this.f361b.f343f.f367h);
                q(this.f361b.f343f.f368i);
                return;
            }
            if (iM0 == 2) {
                this.f367h.f354e = DependencyNode.Type.TOP;
                while (i2 < aVar.H0) {
                    ConstraintWidget constraintWidget4 = aVar.G0[i2];
                    if (zL0 || constraintWidget4.P() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.f344g.f367h;
                        dependencyNode3.k.add(this.f367h);
                        this.f367h.l.add(dependencyNode3);
                    }
                    i2++;
                }
                q(this.f361b.f344g.f367h);
                q(this.f361b.f344g.f368i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.f367h.f354e = DependencyNode.Type.BOTTOM;
            while (i2 < aVar.H0) {
                ConstraintWidget constraintWidget5 = aVar.G0[i2];
                if (zL0 || constraintWidget5.P() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.f344g.f368i;
                    dependencyNode4.k.add(this.f367h);
                    this.f367h.l.add(dependencyNode4);
                }
                i2++;
            }
            q(this.f361b.f344g.f367h);
            q(this.f361b.f344g.f368i);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f361b;
        if (constraintWidget instanceof b.e.b.j.a) {
            int iM0 = ((b.e.b.j.a) constraintWidget).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.f361b.G0(this.f367h.f356g);
            } else {
                this.f361b.H0(this.f367h.f356g);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f362c = null;
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
