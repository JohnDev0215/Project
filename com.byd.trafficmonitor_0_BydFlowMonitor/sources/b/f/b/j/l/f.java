package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends DependencyNode {
    public int m;

    public f(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof i) {
            this.f643e = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.f643e = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.DependencyNode
    public void d(int i2) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.f645g = i2;
        for (d dVar : this.k) {
            dVar.a(dVar);
        }
    }
}
