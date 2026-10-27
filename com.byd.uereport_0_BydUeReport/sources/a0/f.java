package a0;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends DependencyNode {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f26m;

    public f(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof i) {
            this.f982e = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.f982e = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.DependencyNode
    public void d(int i8) {
        if (this.f987j) {
            return;
        }
        this.f987j = true;
        this.f984g = i8;
        for (d dVar : this.f988k) {
            dVar.a(dVar);
        }
    }
}
