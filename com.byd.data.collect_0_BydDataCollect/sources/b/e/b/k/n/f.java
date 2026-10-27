package b.e.b.k.n;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DependencyNode.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    m f2489d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f2491f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2492g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2486a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2487b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2488c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    a f2490e = a.UNKNOWN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2493h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    g f2494i = null;
    public boolean j = false;
    List<d> k = new ArrayList();
    List<f> l = new ArrayList();

    /* JADX INFO: compiled from: DependencyNode.java */
    enum a {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public f(m mVar) {
        this.f2489d = mVar;
    }

    @Override // b.e.b.k.n.d
    public void a(d dVar) {
        Iterator<f> it = this.l.iterator();
        while (it.hasNext()) {
            if (!it.next().j) {
                return;
            }
        }
        this.f2488c = true;
        d dVar2 = this.f2486a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f2487b) {
            this.f2489d.a(this);
            return;
        }
        f fVar = null;
        int i2 = 0;
        for (f fVar2 : this.l) {
            if (!(fVar2 instanceof g)) {
                i2++;
                fVar = fVar2;
            }
        }
        if (fVar != null && i2 == 1 && fVar.j) {
            g gVar = this.f2494i;
            if (gVar != null) {
                if (!gVar.j) {
                    return;
                } else {
                    this.f2491f = this.f2493h * gVar.f2492g;
                }
            }
            d(fVar.f2492g + this.f2491f);
        }
        d dVar3 = this.f2486a;
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
        this.f2492g = 0;
        this.f2488c = false;
        this.f2487b = false;
    }

    public void d(int i2) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.f2492g = i2;
        for (d dVar : this.k) {
            dVar.a(dVar);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2489d.f2514b.s());
        sb.append(":");
        sb.append(this.f2490e);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.f2492g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(">");
        return sb.toString();
    }
}
