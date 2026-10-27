package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyNode.java */
/* JADX INFO: loaded from: classes.dex */
public class o80 implements m80 {
    public final gt3 d;
    public int f;
    public int g;
    public gt3 a = null;
    public boolean b = false;
    public boolean c = false;
    public int e = 1;
    public int h = 1;
    public ca0 i = null;
    public boolean j = false;
    public final ArrayList k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public o80(gt3 gt3Var) {
        this.d = gt3Var;
    }

    @Override // defpackage.m80
    public final void a(m80 m80Var) {
        ArrayList<o80> arrayList = this.l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((o80) it.next()).j) {
                return;
            }
        }
        this.c = true;
        gt3 gt3Var = this.a;
        if (gt3Var != null) {
            gt3Var.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        o80 o80Var = null;
        int i = 0;
        for (o80 o80Var2 : arrayList) {
            if (!(o80Var2 instanceof ca0)) {
                i++;
                o80Var = o80Var2;
            }
        }
        if (o80Var != null && i == 1 && o80Var.j) {
            ca0 ca0Var = this.i;
            if (ca0Var != null) {
                if (!ca0Var.j) {
                    return;
                } else {
                    this.f = this.h * ca0Var.g;
                }
            }
            d(o80Var.g + this.f);
        }
        gt3 gt3Var2 = this.a;
        if (gt3Var2 != null) {
            gt3Var2.a(this);
        }
    }

    public final void b(m80 m80Var) {
        this.k.add(m80Var);
        if (this.j) {
            m80Var.a(m80Var);
        }
    }

    public final void c() {
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
        for (m80 m80Var : this.k) {
            m80Var.a(m80Var);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.l0);
        sb.append(":");
        sb.append(gq2.n(this.e));
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
