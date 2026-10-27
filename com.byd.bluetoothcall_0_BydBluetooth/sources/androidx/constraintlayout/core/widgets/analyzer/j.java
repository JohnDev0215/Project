package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import java.util.Iterator;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
class j extends WidgetRun {
    public j(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private void q(DependencyNode dependencyNode) {
        this.h.k.add(dependencyNode);
        dependencyNode.l.add(this.h);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.d
    public void a(d dVar) {
        androidx.constraintlayout.core.widgets.a aVar = (androidx.constraintlayout.core.widgets.a) this.f837b;
        int iY1 = aVar.y1();
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
        if (iY1 == 0 || iY1 == 2) {
            this.h.d(i2 + aVar.z1());
        } else {
            this.h.d(i + aVar.z1());
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidget = this.f837b;
        if (constraintWidget instanceof androidx.constraintlayout.core.widgets.a) {
            this.h.f831b = true;
            androidx.constraintlayout.core.widgets.a aVar = (androidx.constraintlayout.core.widgets.a) constraintWidget;
            int iY1 = aVar.y1();
            boolean zX1 = aVar.x1();
            int i = 0;
            if (iY1 == 0) {
                this.h.f834e = DependencyNode.Type.LEFT;
                while (i < aVar.V0) {
                    ConstraintWidget constraintWidget2 = aVar.U0[i];
                    if (zX1 || constraintWidget2.X() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f.h;
                        dependencyNode.k.add(this.h);
                        this.h.l.add(dependencyNode);
                    }
                    i++;
                }
                q(this.f837b.f.h);
                q(this.f837b.f.i);
                return;
            }
            if (iY1 == 1) {
                this.h.f834e = DependencyNode.Type.RIGHT;
                while (i < aVar.V0) {
                    ConstraintWidget constraintWidget3 = aVar.U0[i];
                    if (zX1 || constraintWidget3.X() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f.i;
                        dependencyNode2.k.add(this.h);
                        this.h.l.add(dependencyNode2);
                    }
                    i++;
                }
                q(this.f837b.f.h);
                q(this.f837b.f.i);
                return;
            }
            if (iY1 == 2) {
                this.h.f834e = DependencyNode.Type.TOP;
                while (i < aVar.V0) {
                    ConstraintWidget constraintWidget4 = aVar.U0[i];
                    if (zX1 || constraintWidget4.X() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.g.h;
                        dependencyNode3.k.add(this.h);
                        this.h.l.add(dependencyNode3);
                    }
                    i++;
                }
                q(this.f837b.g.h);
                q(this.f837b.g.i);
                return;
            }
            if (iY1 != 3) {
                return;
            }
            this.h.f834e = DependencyNode.Type.BOTTOM;
            while (i < aVar.V0) {
                ConstraintWidget constraintWidget5 = aVar.U0[i];
                if (zX1 || constraintWidget5.X() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.g.i;
                    dependencyNode4.k.add(this.h);
                    this.h.l.add(dependencyNode4);
                }
                i++;
            }
            q(this.f837b.g.h);
            q(this.f837b.g.i);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f837b;
        if (constraintWidget instanceof androidx.constraintlayout.core.widgets.a) {
            int iY1 = ((androidx.constraintlayout.core.widgets.a) constraintWidget).y1();
            if (iY1 == 0 || iY1 == 1) {
                this.f837b.p1(this.h.g);
            } else {
                this.f837b.q1(this.h.g);
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f838c = null;
        this.h.c();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
