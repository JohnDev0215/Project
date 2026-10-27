package androidx.constraintlayout.solver.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DependencyNode implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    WidgetRun f666d;
    int f;
    public int g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f663a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f664b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f665c = false;
    Type e = Type.UNKNOWN;
    int h = 1;
    f i = null;
    public boolean j = false;
    List<d> k = new ArrayList();
    List<DependencyNode> l = new ArrayList();

    enum Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.f666d = widgetRun;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.d
    public void a(d dVar) {
        Iterator<DependencyNode> it = this.l.iterator();
        while (it.hasNext()) {
            if (!it.next().j) {
                return;
            }
        }
        this.f665c = true;
        d dVar2 = this.f663a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f664b) {
            this.f666d.a(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i = 0;
        for (DependencyNode dependencyNode2 : this.l) {
            if (!(dependencyNode2 instanceof f)) {
                i++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i == 1 && dependencyNode.j) {
            f fVar = this.i;
            if (fVar != null) {
                if (!fVar.j) {
                    return;
                } else {
                    this.f = this.h * fVar.g;
                }
            }
            d(dependencyNode.g + this.f);
        }
        d dVar3 = this.f663a;
        if (dVar3 != null) {
            dVar3.a(this);
        }
    }

    public void b(d dVar) {
        this.k.add(dVar);
        if (this.j) {
            dVar.a(dVar);
        }
    }

    public void c() {
        this.l.clear();
        this.k.clear();
        this.j = false;
        this.g = 0;
        this.f665c = false;
        this.f664b = false;
    }

    public void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (d dVar : this.k) {
            dVar.a(dVar);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f666d.f669b.s());
        sb.append(":");
        sb.append(this.e);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(">");
        return sb.toString();
    }
}
