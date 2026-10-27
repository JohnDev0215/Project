package androidx.constraintlayout.solver.widgets.analyzer;

import b.f.b.j.l.d;
import b.f.b.j.l.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DependencyNode implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WidgetRun f642d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f644f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f645g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f639a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f640b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f641c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Type f643e = Type.UNKNOWN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f646h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f647i = null;
    public boolean j = false;
    public List<d> k = new ArrayList();
    public List<DependencyNode> l = new ArrayList();

    public enum Type {
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
        this.f642d = widgetRun;
    }

    @Override // b.f.b.j.l.d
    public void a(d dVar) {
        Iterator<DependencyNode> it = this.l.iterator();
        while (it.hasNext()) {
            if (!it.next().j) {
                return;
            }
        }
        this.f641c = true;
        d dVar2 = this.f639a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f640b) {
            this.f642d.a(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i2 = 0;
        for (DependencyNode dependencyNode2 : this.l) {
            if (!(dependencyNode2 instanceof f)) {
                i2++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i2 == 1 && dependencyNode.j) {
            f fVar = this.f647i;
            if (fVar != null) {
                if (!fVar.j) {
                    return;
                } else {
                    this.f644f = this.f646h * fVar.f645g;
                }
            }
            d(dependencyNode.f645g + this.f644f);
        }
        d dVar3 = this.f639a;
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
        this.f645g = 0;
        this.f641c = false;
        this.f640b = false;
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f642d.f650b.s());
        sb.append(":");
        sb.append(this.f643e);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.f645g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(">");
        return sb.toString();
    }
}
