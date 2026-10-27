package androidx.constraintlayout.solver.widgets.analyzer;

import defpackage.h6;
import defpackage.j6;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DependencyNode implements h6 {
    public WidgetRun d;
    public int f;
    public int g;
    public h6 a = null;
    public boolean b = false;
    public boolean c = false;
    public Type e = Type.UNKNOWN;
    public int h = 1;
    public j6 i = null;
    public boolean j = false;
    public List<h6> k = new ArrayList();
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
        this.d = widgetRun;
    }

    @Override // defpackage.h6
    public void a(h6 h6Var) {
        Iterator<DependencyNode> it = this.l.iterator();
        while (it.hasNext()) {
            if (!it.next().j) {
                return;
            }
        }
        this.c = true;
        h6 h6Var2 = this.a;
        if (h6Var2 != null) {
            h6Var2.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i = 0;
        for (DependencyNode dependencyNode2 : this.l) {
            if (!(dependencyNode2 instanceof j6)) {
                i++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i == 1 && dependencyNode.j) {
            j6 j6Var = this.i;
            if (j6Var != null) {
                if (!j6Var.j) {
                    return;
                } else {
                    this.f = this.h * j6Var.g;
                }
            }
            d(dependencyNode.g + this.f);
        }
        h6 h6Var3 = this.a;
        if (h6Var3 != null) {
            h6Var3.a(this);
        }
    }

    public void b(h6 h6Var) {
        this.k.add(h6Var);
        if (this.j) {
            h6Var.a(h6Var);
        }
    }

    public void c() {
        this.l.clear();
        this.k.clear();
        this.j = false;
        this.g = 0;
        this.c = false;
        this.b = false;
    }

    public void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (h6 h6Var : this.k) {
            h6Var.a(h6Var);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.s());
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
