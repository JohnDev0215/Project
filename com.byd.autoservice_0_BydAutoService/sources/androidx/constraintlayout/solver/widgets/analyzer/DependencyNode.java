package androidx.constraintlayout.solver.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import x.d;
import x.f;

/* JADX INFO: loaded from: classes.dex */
public class DependencyNode implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WidgetRun f890d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f893g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f887a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f888b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f889c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Type f891e = Type.UNKNOWN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f894h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f895i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f896j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List<d> f897k = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List<DependencyNode> f898l = new ArrayList();

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
        this.f890d = widgetRun;
    }

    @Override // x.d
    public void a(d dVar) {
        Iterator<DependencyNode> it = this.f898l.iterator();
        while (it.hasNext()) {
            if (!it.next().f896j) {
                return;
            }
        }
        this.f889c = true;
        d dVar2 = this.f887a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f888b) {
            this.f890d.a(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i3 = 0;
        for (DependencyNode dependencyNode2 : this.f898l) {
            if (!(dependencyNode2 instanceof f)) {
                i3++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i3 == 1 && dependencyNode.f896j) {
            f fVar = this.f895i;
            if (fVar != null) {
                if (!fVar.f896j) {
                    return;
                } else {
                    this.f892f = this.f894h * fVar.f893g;
                }
            }
            d(dependencyNode.f893g + this.f892f);
        }
        d dVar3 = this.f887a;
        if (dVar3 != null) {
            dVar3.a(this);
        }
    }

    public void b(d dVar) {
        this.f897k.add(dVar);
        if (this.f896j) {
            dVar.a(dVar);
        }
    }

    public void c() {
        this.f898l.clear();
        this.f897k.clear();
        this.f896j = false;
        this.f893g = 0;
        this.f889c = false;
        this.f888b = false;
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f890d.f900b.s());
        sb.append(":");
        sb.append(this.f891e);
        sb.append("(");
        sb.append(this.f896j ? Integer.valueOf(this.f893g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.f898l.size());
        sb.append(":d=");
        sb.append(this.f897k.size());
        sb.append(">");
        return sb.toString();
    }
}
