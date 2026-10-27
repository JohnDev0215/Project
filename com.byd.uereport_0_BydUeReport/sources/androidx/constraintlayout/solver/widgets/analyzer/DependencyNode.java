package androidx.constraintlayout.solver.widgets.analyzer;

import a0.d;
import a0.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DependencyNode implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WidgetRun f981d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f983f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f984g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f978a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f979b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f980c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Type f982e = Type.UNKNOWN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f985h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f986i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f987j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List<d> f988k = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List<DependencyNode> f989l = new ArrayList();

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
        this.f981d = widgetRun;
    }

    @Override // a0.d
    public void a(d dVar) {
        Iterator<DependencyNode> it = this.f989l.iterator();
        while (it.hasNext()) {
            if (!it.next().f987j) {
                return;
            }
        }
        this.f980c = true;
        d dVar2 = this.f978a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f979b) {
            this.f981d.a(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i8 = 0;
        for (DependencyNode dependencyNode2 : this.f989l) {
            if (!(dependencyNode2 instanceof f)) {
                i8++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i8 == 1 && dependencyNode.f987j) {
            f fVar = this.f986i;
            if (fVar != null) {
                if (!fVar.f987j) {
                    return;
                } else {
                    this.f983f = this.f985h * fVar.f984g;
                }
            }
            d(dependencyNode.f984g + this.f983f);
        }
        d dVar3 = this.f978a;
        if (dVar3 != null) {
            dVar3.a(this);
        }
    }

    public void b(d dVar) {
        this.f988k.add(dVar);
        if (this.f987j) {
            dVar.a(dVar);
        }
    }

    public void c() {
        this.f989l.clear();
        this.f988k.clear();
        this.f987j = false;
        this.f984g = 0;
        this.f980c = false;
        this.f979b = false;
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f981d.f991b.s());
        sb.append(":");
        sb.append(this.f982e);
        sb.append("(");
        sb.append(this.f987j ? Integer.valueOf(this.f984g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.f989l.size());
        sb.append(":d=");
        sb.append(this.f988k.size());
        sb.append(">");
        return sb.toString();
    }
}
