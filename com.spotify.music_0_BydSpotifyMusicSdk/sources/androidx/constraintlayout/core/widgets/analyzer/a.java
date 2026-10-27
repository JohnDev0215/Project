package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import p.e90;

/* JADX INFO: loaded from: classes.dex */
public class a implements e90 {
    public final f d;
    public int f;
    public int g;
    public f a = null;
    public boolean b = false;
    public boolean c = false;
    public DependencyNode$Type e = DependencyNode$Type.c;
    public int h = 1;
    public b i = null;
    public boolean j = false;
    public final ArrayList k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public a(f fVar) {
        this.d = fVar;
    }

    @Override // p.e90
    public final void a(e90 e90Var) {
        ArrayList<a> arrayList = this.l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((a) it.next()).j) {
                return;
            }
        }
        this.c = true;
        f fVar = this.a;
        if (fVar != null) {
            fVar.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        a aVar = null;
        int i = 0;
        for (a aVar2 : arrayList) {
            if (!(aVar2 instanceof b)) {
                i++;
                aVar = aVar2;
            }
        }
        if (aVar != null && i == 1 && aVar.j) {
            b bVar = this.i;
            if (bVar != null) {
                if (!bVar.j) {
                    return;
                } else {
                    this.f = this.h * bVar.g;
                }
            }
            d(aVar.g + this.f);
        }
        f fVar2 = this.a;
        if (fVar2 != null) {
            fVar2.a(this);
        }
    }

    public final void b(e90 e90Var) {
        this.k.add(e90Var);
        if (this.j) {
            e90Var.a(e90Var);
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
        for (e90 e90Var : this.k) {
            e90Var.a(e90Var);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.h0);
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
