package x;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends DependencyNode {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5705m;

    public f(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof i) {
            this.f891e = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.f891e = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.DependencyNode
    public void d(int i3) {
        if (this.f896j) {
            return;
        }
        this.f896j = true;
        this.f893g = i3;
        for (d dVar : this.f897k) {
            dVar.a(dVar);
        }
    }
}
